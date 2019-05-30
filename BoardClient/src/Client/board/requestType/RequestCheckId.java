package Client.board.requestType;

import java.io.BufferedWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

import Client.board.connect.ClientThread;
import Client.board.registMember.RegistMember;
import Client.board.util.GetMain;
import Client.board.util.WriterDistributor;


public class RequestCheckId {
	private RegistMember registMember;
	private ClientThread clientThread;
	private WriterDistributor writerDistributor;
	
	public RequestCheckId(RegistMember registMembedr) {
		this.registMember = registMember;
		writerDistributor = new WriterDistributor();
	}
	public void CheckId(String id) {
		clientThread = GetMain.getMainFrame().getConnector().getClientThread();
		
		BufferedWriter buffw = writerDistributor.getBuffw();
		System.out.println("RequestCheckId / buffw .."+buffw);
		JSONObject obj = new JSONObject();
		obj.put("requestType", "checkId");
		obj.put("id", id);
		try {
			buffw.write(obj.toString()+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
