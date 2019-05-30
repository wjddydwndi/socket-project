package server.board.distributor.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.JTextArea;

import org.json.simple.JSONObject;

import server.board.distributor.ServerDistributor;
import server.board.model.domain.Board;
import server.board.model.domain.Member;
import server.board.model.repository.BoardDAO;
import server.board.model.repository.MemberDAO;
import server.board.util.WriterDistributor;

public class WriteController {
	private ServerDistributor serverDistributor;
	private JTextArea area;
	private MemberDAO memberDAO;
	private BoardDAO boardDAO;
	private WriterDistributor writerDistributor;
	private BufferedWriter buffw;
	private String serverData = "C:/java_developer/javaSE/BoardServer/serverdata";
	private String filename;
	private BufferedWriter fos = null;

	public WriteController(ServerDistributor serverDistributor) {
		this.serverDistributor = serverDistributor;
		area = serverDistributor.getServerThread().getArea();
		memberDAO = new MemberDAO();
		boardDAO = new BoardDAO();
		writerDistributor = new WriterDistributor();
		buffw = writerDistributor.getBuffw();

	}

	public void write(JSONObject obj) {
		area.append("WriteController 진입\n");
		JSONObject jsonObject = new JSONObject();
		if (obj.get("content") != null) {
			String member_id = (String) obj.get("member_id");
			String content = (String) obj.get("content");
			String filename = (String) obj.get("filename");

			area.append("WriteController / member_id==" + member_id + "\n");

			Member member = new Member();
			member.setMember_id(Integer.parseInt(member_id));
			Member result = memberDAO.select(member);

			area.append("WriteController / DAO 통과 / result==" + result.getMember_id() + "/" + result.getId() + "\n");

			Board board = new Board();
			board.setContent(content);
			board.setFilename(filename);
			board.setMember(result);

			System.out.println("Server/ WriteController / board / ==" + board.getContent() + "/" + board.getFilename()
					+ "/" + board.getMember());

			int result2 = boardDAO.insert(board);// 글 한건 등록

		
			jsonObject.put("requestType", "write");
			if (result2 == 0) {
				area.append("WriteController / DB통과 / 글등록에 실패하였습니다..\n");
				jsonObject.put("result", "false");
			} else {
				area.append("WriteController / DB통과 / 글등록에 성공하였습니다.\n");
				area.append("Filename = =" + board.getFilename());
				jsonObject.put("result", "true");

			}
		}else {
			jsonObject.put("requestType", "write");
			jsonObject.put("result","error");
			
		}
		try {

			buffw.write(jsonObject.toString() + "\n");
			buffw.flush();
			System.out.println("WriteController / write() / 송신 성공");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
