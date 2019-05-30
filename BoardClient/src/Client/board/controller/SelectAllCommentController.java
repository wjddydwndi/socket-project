package Client.board.controller;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Client.board.connect.ClientThread;
import Client.board.distributor.ClientDistributor;
import Client.board.main.MainFrame;
import Client.board.model.domain.B_Comment;
import Client.board.model.domain.Member;
import Client.board.page.BoardMain;
import Client.board.page.BoardWrite;
import Client.board.page.Content;
import Client.board.requestType.RequestWrite;
import Client.board.util.GetMain;


public class SelectAllCommentController {
	private ClientThread clientThread;
	private ClientDistributor clientDistributor;
	private BoardMain boardMain;
	private BufferedWriter buffw;
	private String dataPath;
	private MainFrame mainFrame;
	
	private BoardWrite boardWrite;
	private RequestWrite requestWrite;
	
	
	public SelectAllCommentController(ClientDistributor clientDistributor) {
		this.clientDistributor = clientDistributor;
		clientDistributor.getClientThread();
		clientThread = clientDistributor.getClientThread();
		buffw = clientThread.getBuffw();
		mainFrame=GetMain.getMainFrame();
		
		
	}
	public void selectAllComment(JSONObject obj) {
		JSONArray array =(JSONArray)obj.get("array");
		int num =Integer.parseInt((String)obj.get("num"));
		List list = new ArrayList();
		
		
		
		
		if(obj.get("array")!=null) {
			System.out.println("전체 댓글 조회 성공");
			BoardMain boardMain = (BoardMain)mainFrame.getPage(2);
			
			Iterator it = array.iterator();
		
			
			while(it.hasNext()) {
				JSONObject json = (JSONObject)it.next();
				int b_Comment_id=Integer.parseInt((String)json.get("b_Comment_id"));
				int board_id =Integer.parseInt((String)json.get("board_id"));
				String content2 = (String)json.get("content");
				String credate = (String)json.get("credate");
				String id = (String)json.get("id");
				String name = (String)json.get("name");
				int member_id = Integer.parseInt((String)json.get("member_id"));
				
				B_Comment b_Comment = new B_Comment();
				b_Comment.setB_comment_id(b_Comment_id);
				b_Comment.setBoard_id(board_id);
				b_Comment.setContent(content2);
				b_Comment.setCredate(credate);
				Member member= new Member();
				member.setId(id);
				member.setMember_id(member_id);
				member.setName(name);
				b_Comment.setMember(member);
				
				
				list.add(b_Comment);
			}
			List contentList = boardMain.getContentList();
			Content content = (Content)contentList.get(num);
			content.setCommentList(list);
			content.getComment();
			mainFrame.showPage(2);
			
			
		
		}else {
			System.out.println("해당 댓글이 없습니다.");
		}
		
		
		
	}
	
	
	
	public ClientThread getClientThread() {
		return clientThread;
	}
	public void setClientThread(ClientThread clientThread) {
		this.clientThread = clientThread;
	}
	public ClientDistributor getClientDistributor() {
		return clientDistributor;
	}
	public void setClientDistributor(ClientDistributor clientDistributor) {
		this.clientDistributor = clientDistributor;
	}
	public BoardMain getBoardMain() {
		return boardMain;
	}
	public void setBoardMain(BoardMain boardMain) {
		this.boardMain = boardMain;
	}
	public BufferedWriter getBuffw() {
		return buffw;
	}
	public void setBuffw(BufferedWriter buffw) {
		this.buffw = buffw;
	}
	public String getDataPath() {
		return dataPath;
	}
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	
	
}
