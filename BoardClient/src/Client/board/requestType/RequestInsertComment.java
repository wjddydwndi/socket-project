package Client.board.requestType;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

import Client.board.main.MainFrame;
import Client.board.page.Content;
import Client.board.util.GetMain;

public class RequestInsertComment {
	private Content content;
	private MainFrame mainFrame;
	private BufferedReader buffr;
	private BufferedWriter buffw;

	public RequestInsertComment(Content content) {
		this.content = content;
		mainFrame = GetMain.getMainFrame();
		buffr = mainFrame.getConnector().getClientThread().getBuffr();
		buffw = mainFrame.getConnector().getClientThread().getBuffw();
	}

	public String insertComment(String comment,int board_id,int member_id) {
		String result = null;
		JSONObject obj = new JSONObject();
		obj.put("requestType", "insertComment");
		obj.put("comment", comment);
		obj.put("board_id", ((Integer)board_id).toString());
		obj.put("member_id", ((Integer)member_id).toString());
	
		
		
		try {
			buffw.write(obj.toString()+"\n");
			buffw.flush();
			result = "SUCCESS";
			System.out.println("Client : RequstInsertComment / insertComment() / 서버 전송완료"+result);
		} catch (IOException e) {

			e.printStackTrace();
			System.out.println("Client : RequstInsertComment / insertComment() / 서버 실패"+result);
		}
		return result;
	}



}
