package server.board.distributor.controller;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.swing.JTextArea;

import org.json.simple.JSONObject;

import server.board.distributor.ServerDistributor;
import server.board.model.domain.Member;
import server.board.model.repository.MemberDAO;
import server.board.util.WriterDistributor;

public class CheckIdController {
	private ServerDistributor serverDistributor;
	private JTextArea area;
	private	MemberDAO memberDAO;
	private BufferedWriter buffw;
	private WriterDistributor writerDistributor;
	
	public CheckIdController(ServerDistributor serverDistributor) {
		this.serverDistributor = serverDistributor;
		area = serverDistributor.getServerThread().getArea();
		memberDAO = new MemberDAO();
		writerDistributor = new WriterDistributor();
		buffw = writerDistributor.getBuffw();
		
	}
	public void checkId(JSONObject obj ) {
		area.append("CheckIdController 진입\n");
		String id = (String) obj.get("id");
		Member member = new Member();
		member.setId(id);
		Member result = memberDAO.checkId(member);
		
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("requestType", "checkId");
		if(result ==null) {
			area.append("CheckIdController / DB통과 / 해당 아이디는 사용할 수 있습니다.\n");
			jsonObject.put("result", "false");
		}else {
			area.append("CheckIdController / DB통과 / 해당 아이디 : "+result.getId()+"는 이미 사용중입니다.\n");
			jsonObject.put("result", "true");
		}
		
		try {
			buffw.write(jsonObject.toString()+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
