package server.board.distributor.controller;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.swing.JTextArea;

import org.json.simple.JSONObject;

import server.board.distributor.ServerDistributor;
import server.board.model.domain.Member;
import server.board.model.repository.MemberDAO;
import server.board.util.WriterDistributor;

public class RegistController {
	private ServerDistributor serverDistributor;
	private JTextArea area;
	private	MemberDAO memberDAO;
	private BufferedWriter buffw;
	private WriterDistributor writerDistributor;
	
	public RegistController(ServerDistributor serverDistributor) {
		this.serverDistributor = serverDistributor;
		area = serverDistributor.getServerThread().getArea();
		memberDAO = new MemberDAO();
		writerDistributor = new WriterDistributor();
		buffw = writerDistributor.getBuffw();
	
	}
	public void regist(JSONObject obj ) {
		area.append("RegistController 진입 \n");
		String id = (String) obj.get("id");
		String password = (String) obj.get("password");
		String name = (String) obj.get("name");
		
		area.append("RegistController / id = "+id+"\n");
		area.append("RegistController / password = "+password+"\n");
		area.append("RegistController / name = "+name+"\n");

		
		
		Member member = new Member();
		member.setId(id);
		member.setPass(password);
		member.setName(name);
		
		int result = memberDAO.insert(member);
		
		area.append("RegistController / DB통과 / result = "+result+"\n");
		
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("requestType", "regist");
		if(result ==1) {
			jsonObject.put("result", "1");
		}else {
			jsonObject.put("result", "0");
		}
		
		try {
			buffw.write(jsonObject.toString()+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
