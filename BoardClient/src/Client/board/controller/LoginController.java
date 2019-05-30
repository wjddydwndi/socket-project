package Client.board.controller;

import java.io.BufferedWriter;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;

import Client.board.connect.ClientThread;
import Client.board.distributor.ClientDistributor;
import Client.board.login.Login;
import Client.board.main.MainFrame;
import Client.board.model.domain.Member;
import Client.board.page.BoardMain;
import Client.board.registMember.RegistMember;
import Client.board.util.GetMain;

public class LoginController {
	private ClientThread clientThread;
	private ClientDistributor clientDistributor;
	private RegistMember registMember;
	private BufferedWriter buffw;
	private MainFrame mainFrame;
	private Login login;
	public LoginController(ClientDistributor clientDistributor) {
		this.clientDistributor = clientDistributor;
		

	}

	public void login(JSONObject obj) {
		
		mainFrame = GetMain.getMainFrame();
		login = (Login) mainFrame.getPage(0);
		clientDistributor.getClientThread();
		clientThread = clientDistributor.getClientThread();
		buffw = clientThread.getBuffw();
		
		String result = (String) obj.get("result");

		if (result.equals("fail")) {
			JOptionPane.showMessageDialog(login, "해당 로그인 정보는 없는 아이디이거나, 잘못된 비밀번호입니다.");
		} else {
			JOptionPane.showMessageDialog(login, "환영합니다.");
			Member member =mainFrame.getMember();
		
			
			  member.setMember_id(Integer.parseInt((String)obj.get("member_id")));
			  member.setId(((String)obj.get("id")));
			  member.setPass(((String)obj.get("pass")));
			  member.setName(((String)obj.get("name")));
			 
			
			
			
			 mainFrame.showPage(2); 
			 mainFrame.distributeMemberInfo(member);
		}
	}

}
