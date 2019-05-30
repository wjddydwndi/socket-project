package Client.board.controller;

import java.io.BufferedWriter;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;

import Client.board.connect.ClientThread;
import Client.board.distributor.ClientDistributor;
import Client.board.main.MainFrame;
import Client.board.page.BoardMain;
import Client.board.page.BoardWrite;
import Client.board.requestType.RequestWrite;
import Client.board.util.GetMain;


public class InsertCommentController {
	private ClientThread clientThread;
	private ClientDistributor clientDistributor;
	private BoardMain boardMain;
	private BufferedWriter buffw;
	private String dataPath;
	private MainFrame mainFrame;
	
	private BoardWrite boardWrite;
	private RequestWrite requestWrite;
	
	
	public InsertCommentController(ClientDistributor clientDistributor) {
		this.clientDistributor = clientDistributor;
		clientDistributor.getClientThread();
		clientThread = clientDistributor.getClientThread();
		buffw = clientThread.getBuffw();
		mainFrame=GetMain.getMainFrame();
		
		
	}
	public void insertComment(JSONObject obj) {
		String result = (String)obj.get("result");
		if(result.equals("true")) {
			System.out.println("댓글 등록 완료");
		}else {
			System.out.println("댓글 등록 실패");
		}
		BoardMain boardMain = (BoardMain)mainFrame.getPage(2);
		boardMain.selectAll();
		//전체 댓글 가져오는 메서드 추가할 것.
		
		
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
