package server.board.distributor.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JTextArea;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import server.board.distributor.ServerDistributor;
import server.board.model.domain.Board;
import server.board.model.domain.Member;
import server.board.model.repository.BoardDAO;
import server.board.util.WriterDistributor;

public class SelectAllController {
	private ServerDistributor serverDistributor;
	private JTextArea area;
	private BoardDAO boardDAO;
	private BufferedWriter buffw;
	private WriterDistributor writerDistributor;

	public SelectAllController(ServerDistributor serverDistributor) {
		this.serverDistributor = serverDistributor;
		area = serverDistributor.getServerThread().getArea();
		boardDAO = new BoardDAO();
		writerDistributor = new WriterDistributor();
		buffw = writerDistributor.getBuffw();

	}

	public void selectAll(JSONObject obj) {
		area.append("SelectAllController 진입\n");

		List list = boardDAO.selectAll();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("requestType", "selectAll");

		
		if (list == null) {
			area.append("SelectAllController / DB통과 / result = false.\n");
			jsonObject.put("result", "false");
	
		} else {
			area.append("SelectAllController / DB통과 / result = true.\n");
			jsonObject.put("result", "true");

			for (int i = 0; i < list.size(); i++) {
				JSONObject json = new JSONObject();
				Board board = (Board) list.get(i);
				json.put("board_id", ((Integer)board.getBoard_id()).toString());
				json.put("content", board.getContent());
				json.put("credate", board.getCredate());
				json.put("filename", board.getFilename());
				json.put("member_id", ((Integer)board.getMember().getMember_id()).toString());
				json.put("id", board.getMember().getId());
				json.put("name", board.getMember().getName());

				jsonArray.add(json);
			}
		}
		jsonObject.put("array", jsonArray);
		try {
			buffw.write(jsonObject.toString() + "\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
