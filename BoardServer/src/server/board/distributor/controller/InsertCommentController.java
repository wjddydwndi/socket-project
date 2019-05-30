package server.board.distributor.controller;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.swing.JTextArea;

import org.json.simple.JSONObject;

import server.board.distributor.ServerDistributor;
import server.board.model.domain.B_Comment;
import server.board.model.domain.Member;
import server.board.model.repository.B_CommentDAO;
import server.board.model.repository.BoardDAO;
import server.board.model.repository.MemberDAO;
import server.board.util.WriterDistributor;

public class InsertCommentController {
	private ServerDistributor serverDistributor;
	private JTextArea area;
	private MemberDAO memberDAO;
	private B_CommentDAO b_CommentDAO;
	private WriterDistributor writerDistributor;
	private BufferedWriter buffw;
	private String serverData = "C:/java_developer/javaSE/BoardServer/serverdata";
	private String filename;
	private BufferedWriter fos = null;

	public InsertCommentController(ServerDistributor serverDistributor) {
		this.serverDistributor = serverDistributor;
		area = serverDistributor.getServerThread().getArea();
		memberDAO = new MemberDAO();
		b_CommentDAO = new B_CommentDAO();
		writerDistributor = new WriterDistributor();
		buffw = writerDistributor.getBuffw();

	}

	public void insertComment(JSONObject obj) {
		String comment =(String)obj.get("comment");
		int board_id =Integer.parseInt((String)obj.get("board_id"));
		int member_id =Integer.parseInt((String)obj.get("member_id"));
		
		
		B_Comment b_Comment = new B_Comment();
		b_Comment.setBoard_id(board_id);
		b_Comment.setContent(comment);
		Member member = new Member();
		member.setMember_id(member_id);
		b_Comment.setMember(member);
		
		int result =b_CommentDAO.insert(b_Comment);
		JSONObject json = new JSONObject();
		json.put("requestType", "insertComment");
		if(result ==0) {
			json.put("result", "true");

		}else {
			json.put("result", "false");

		}
		
		try {
			buffw.write(json.toString()+"\n");
			buffw.flush();
			area.append("Server : InsertCommentController / insertComment() / 댓글  등록 성공"); 
		} catch (IOException e) {

			e.printStackTrace();
			area.append("Server : InsertCommentController / insertComment() / 댓글  등록 실패");
		}
		
		
	
	}
}
