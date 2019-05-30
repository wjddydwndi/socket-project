package Client.board.controller;

import java.io.BufferedWriter;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;

import Client.board.connect.ClientThread;
import Client.board.distributor.ClientDistributor;
import Client.board.registMember.RegistMember;


public class CheckIdController {
	private ClientThread clientThread;
	private ClientDistributor clientDistributor;
	private RegistMember registMember;
	private BufferedWriter buffw;
	
	
	public CheckIdController(ClientDistributor clientDistributor) {
		this.clientDistributor = clientDistributor;
		clientDistributor.getClientThread();
		clientThread = clientDistributor.getClientThread();
		buffw = clientThread.getBuffw();
		
		
	}
	public void checkId(JSONObject obj) {
		String result = (String) obj.get("result");
		registMember = (RegistMember)clientThread.getClientConnector().getMainFrame().getPage(1);
		
		if(result.equals("true")) {
			registMember.setFlag(false);
			JOptionPane.showMessageDialog(registMember, "�̹� ������� ���̵��Դϴ�.");
		}else {
			registMember.setFlag(true);
			JOptionPane.showMessageDialog(registMember, "����� �� �ִ� ���̵��Դϴ�.");
		}
	}
	
	
}
