package server.board.distributor.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JTextArea;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import server.board.distributor.ServerDistributor;
import server.board.model.domain.B_Comment;
import server.board.model.repository.B_CommentDAO;
import server.board.model.repository.MemberDAO;
import server.board.util.WriterDistributor;

public class SelectAllCommentController {
	private ServerDistributor serverDistributor;
	private JTextArea area;
	private MemberDAO memberDAO;
	private B_CommentDAO b_CommentDAO;
	private WriterDistributor writerDistributor;
	private BufferedWriter buffw;
	private String serverData = "C:/java_developer/javaSE/BoardServer/serverdata";
	private String filename;
	private BufferedWriter fos = null;

	public SelectAllCommentController(ServerDistributor serverDistributor) {
		this.serverDistributor = serverDistributor;
		area = serverDistributor.getServerThread().getArea();
		memberDAO = new MemberDAO();
		b_CommentDAO = new B_CommentDAO();
		writerDistributor = new WriterDistributor();
		buffw = writerDistributor.getBuffw();

	}

	public void selectAllComment(JSONObject obj) {

		int board_id = Integer.parseInt((String) obj.get("board_id"));
		String num = (String) obj.get("num");

		B_Comment b_Comment = new B_Comment();
		b_Comment.setBoard_id(board_id);

		List result = b_CommentDAO.selectAll(b_Comment);

		JSONObject jsonObject = new JSONObject();
		JSONArray array = new JSONArray();
		jsonObject.put("requestType", "selectAllComment");
		if (result != null) {
			for (int i = 0; i < result.size(); i++) {

				B_Comment object = (B_Comment) result.get(i);
				String b_Comment_id = ((Integer) object.getB_comment_id()).toString();
				String boardid = ((Integer) object.getBoard_id()).toString();
				String content = object.getContent();
				String credate = object.getCredate();
				String id = object.getMember().getId();
				String name = object.getMember().getName();
				String member_id = ((Integer) object.getMember().getMember_id()).toString();
				JSONObject json = new JSONObject();
				json.put("b_Comment_id", b_Comment_id);
				json.put("board_id", boardid);
				json.put("content", content);
				json.put("credate", credate);
				json.put("id", id);
				json.put("name", name);
				json.put("member_id", member_id);
				array.add(json);

			}
			jsonObject.put("array",array);
			jsonObject.put("num", num);
		} else {
			jsonObject.put("result", result);
			jsonObject.put("num", num);
		}

		try {
			buffw.write(jsonObject.toString() + "\n");
			buffw.flush();
			area.append("Server : selectAllCommentController / selectAllComment() / 전체댓글 조회  성공");
		} catch (IOException e) {

			e.printStackTrace();
			area.append("Server : selectAllCommentController / selectAllComment() / 전체댓글 조회  실패");
		}

	}
}
