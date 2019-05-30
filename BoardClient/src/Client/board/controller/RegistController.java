package Client.board.controller;

import java.io.BufferedWriter;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;

import Client.board.connect.ClientThread;
import Client.board.distributor.ClientDistributor;
import Client.board.login.Login;
import Client.board.main.MainFrame;
import Client.board.registMember.RegistMember;
import Client.board.util.GetMain;


public class RegistController {
	private ClientThread clientThread;
	private ClientDistributor clientDistributor;
	private RegistMember registMember;
	private BufferedWriter buffw;
	
	
	public RegistController(ClientDistributor clientDistributor) {
		this.clientDistributor = clientDistributor;
		clientDistributor.getClientThread();
		clientThread = clientDistributor.getClientThread();
		buffw = clientThread.getBuffw();
		
		
	}
	public void regist(JSONObject obj) {
		String result = (String) obj.get("result");
		
		MainFrame mainFrame = GetMain.getMainFrame();
		Login login = (Login)mainFrame.getPage(0);
		RegistMember registMember = (RegistMember)mainFrame.getPage(1);
		
		if(result.equals("1")) {
			mainFrame.showPage(0);
			
			System.out.println("Client : RegistController / result =1 / ȸ������ ����");
			JOptionPane.showMessageDialog(login, "ȸ������ �Ǿ����ϴ�.");
			
		}else {
			mainFrame.showPage(0);
			
			System.out.println("Client : RegistController / result =0 / ȸ������ ����");
			JOptionPane.showMessageDialog(login, "ȸ�������� �ٽ� �õ� ���ּ���.");
		}
	}
	
	
}
