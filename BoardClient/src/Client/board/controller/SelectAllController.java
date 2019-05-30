package Client.board.controller;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Client.board.connect.ClientThread;
import Client.board.distributor.ClientDistributor;
import Client.board.main.MainFrame;
import Client.board.model.domain.Board;
import Client.board.model.domain.Member;
import Client.board.page.BoardMain;
import Client.board.page.Content;
import Client.board.registMember.RegistMember;
import Client.board.util.GetMain;

public class SelectAllController {
	private ClientThread clientThread;
	private ClientDistributor clientDistributor;
	private RegistMember registMember;
	private BufferedWriter buffw;
	private List list;
	private MainFrame mainFrame;

	public SelectAllController(ClientDistributor clientDistributor) {
		this.clientDistributor = clientDistributor;
		clientDistributor.getClientThread();
		clientThread = clientDistributor.getClientThread();
		buffw = clientThread.getBuffw();
		list = new ArrayList();
		mainFrame = GetMain.getMainFrame();

	}

	public void selectAll(JSONObject obj) {
		String result = (String) obj.get("result");
		if (result.equals("true")) {
			JSONArray array = (JSONArray) obj.get("array");
			Iterator it = array.iterator();
			while (it.hasNext()) {
				JSONObject json = (JSONObject) it.next();
				String board_id = (String) json.get("board_id");
				String content = (String) json.get("content");
				String credate = (String) json.get("credate");
				String filename = (String) json.get("filename");
				String member_id = (String) json.get("member_id");
				String id = (String) json.get("id");
				String name = (String) json.get("name");

				Board board = new Board();
				board.setBoard_id(Integer.parseInt(board_id));
				board.setContent(content);
				board.setCredate(credate);
				board.setFilename(filename);
				Member member = new Member();
				member.setMember_id(Integer.parseInt(member_id));
				member.setName(name);
				member.setId(id);
				board.setMember(member);

				list.add(board);
			}
		}

		BoardMain boardMain = (BoardMain) mainFrame.getPage(2);
		boardMain.setList(list);
		boardMain.getBoardList();
		
	
		JOptionPane.showMessageDialog(boardMain, "목록을 불러왔습니다.");
		
	}

}
