package Client.board.controller;

import java.io.BufferedWriter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.json.simple.JSONObject;

import Client.board.connect.ClientThread;
import Client.board.distributor.ClientDistributor;
import Client.board.main.MainFrame;
import Client.board.page.BoardMain;
import Client.board.page.BoardWrite;
import Client.board.requestType.RequestWrite;
import Client.board.util.GetMain;


public class WriteController {
	private ClientThread clientThread;
	private ClientDistributor clientDistributor;
	private BoardMain boardMain;
	private BufferedWriter buffw;
	private String dataPath;
	private MainFrame mainFrame;
	
	private BoardWrite boardWrite;
	private RequestWrite requestWrite;
	
	
	public WriteController(ClientDistributor clientDistributor) {
		this.clientDistributor = clientDistributor;
		clientDistributor.getClientThread();
		clientThread = clientDistributor.getClientThread();
		buffw = clientThread.getBuffw();
		mainFrame=GetMain.getMainFrame();
	
		
	}
	public void write(JSONObject obj) {
		String result = (String) obj.get("result");
		System.out.println("WriteController / result :  "+result);
		boardMain = (BoardMain)mainFrame.getPage(2);
		BoardWrite boardWrite = (BoardWrite)mainFrame.getPage(3);
		if(result.equals("true")) {

			
			mainFrame.showPage(2);
			boardMain.selectAll();
			System.out.println("showPage();");
			JOptionPane.showMessageDialog(boardMain, "한 건의 게시물이 등록되었습니다.");
		}else if(result.equals("false")) {
			mainFrame.showPage(2);
			JLabel imageLabel= boardWrite.getImageLabel();
			imageLabel.setIcon(null);
			JTextArea area =boardWrite.getArea();
			area.setText("");
			
			JOptionPane.showMessageDialog(boardMain, "게시물 등록을 다시 시도해주세요.");
		}else if(result.equals("error")){
			
			JOptionPane.showMessageDialog(boardMain, "내용을 입력해주세요.");
			
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
