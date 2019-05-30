package server.board.distributor.controller;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.swing.JTextArea;

import org.json.simple.JSONObject;

import server.board.distributor.ServerDistributor;
import server.board.model.domain.Member;
import server.board.model.repository.MemberDAO;
import server.board.util.GetArea;
import server.board.util.WriterDistributor;

public class LoginController {
	private ServerDistributor serverDistributor;
	private MemberDAO memberDAO;
	private BufferedWriter buffw;
	private JTextArea area;
	private WriterDistributor writerDistributor;
	
	public LoginController(ServerDistributor serverDistributor) {
		this.serverDistributor =serverDistributor; 
		memberDAO = new MemberDAO();
		area =GetArea.getArea();
		writerDistributor = new WriterDistributor();
		
	}

	public void login(JSONObject obj) {
		buffw =writerDistributor.getBuffw();
		
		String id = (String)obj.get("id");
		String pass = (String)obj.get("pass");
		
		Member member = new Member();
		member.setId(id);
		member.setPass(pass);
		
		Member result = memberDAO.login(member);
		
		area.append("LoginController / MemberInfo requested / "+result.getId()+"/"+result.getName()+"\n");
		
		
		JSONObject jsonObject = new JSONObject();
		if(member==null) {
			jsonObject.put("requestType", "login");
			jsonObject.put("result", "fail");
		}else {
			jsonObject.put("requestType", "login");			
			jsonObject.put("result", "success");
			
			String member_id=((Integer)result.getMember_id()).toString();
			
			jsonObject.put("member_id", member_id);
			jsonObject.put("id", result.getId());
			jsonObject.put("pass", result.getPass());
			jsonObject.put("name", result.getName());			
		}
		
		try {
			buffw.write(jsonObject.toString()+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
