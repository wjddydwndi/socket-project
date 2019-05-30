package Client.board.requestType;

import java.io.BufferedWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

import Client.board.connect.ClientThread;
import Client.board.page.BoardMain;
import Client.board.registMember.RegistMember;
import Client.board.util.GetMain;
import Client.board.util.WriterDistributor;


public class RequestSelectAll {
	private RegistMember registMember;
	private WriterDistributor writerDistributor;
	private BoardMain boardMain;
	
	public RequestSelectAll(BoardMain boardMain) {
		this.boardMain = boardMain;
		writerDistributor = new WriterDistributor();
	}
	public void selectAll() {
		
		BufferedWriter buffw = writerDistributor.getBuffw();
		
		JSONObject obj = new JSONObject();
		obj.put("requestType", "selectAll");
	
		try {
			buffw.write(obj.toString()+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
