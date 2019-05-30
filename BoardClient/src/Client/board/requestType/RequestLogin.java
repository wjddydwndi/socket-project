package Client.board.requestType;

import java.io.BufferedWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

import Client.board.connect.ClientThread;
import Client.board.login.Login;
import Client.board.util.GetMain;
import Client.board.util.WriterDistributor;

public class RequestLogin {
	private Login login;
	private ClientThread clientThread;
	private WriterDistributor writerDistributor;
	
	public RequestLogin(Login login) {
		this.login = login;
		writerDistributor = new WriterDistributor();
	}
	
	public void login(String id, String pass) {
		clientThread = GetMain.getMainFrame().getConnector().getClientThread();
		BufferedWriter buffw =  clientThread.getBuffw();
		
		JSONObject obj = new JSONObject();
		obj.put("requestType", "login");
		obj.put("id", id);
		obj.put("pass", pass);
		
		try {
			buffw.write(obj.toString()+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}	
