package Client.board.requestType;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.json.simple.JSONObject;

import Client.board.connect.ClientThread;
import Client.board.page.BoardWrite;
import Client.board.util.GetMain;
import Client.board.util.WriterDistributor;

public class RequestWrite {
	private BoardWrite boardWrite;
	private ClientThread clientThread;
	private WriterDistributor writerDistributor;
	private BufferedWriter buffw;
	private BufferedWriter imgw;

	public RequestWrite(BoardWrite boardWrite) {
		this.boardWrite = boardWrite;
		writerDistributor = new WriterDistributor();
	}

	public String write(int member_id, String content, String dataPath, String filename) {
		
		String result = null;
		
		clientThread = GetMain.getMainFrame().getConnector().getClientThread();

		buffw = writerDistributor.getBuffw();
		imgw = writerDistributor.getBuffwi();

		System.out.println("RequestWrite / buffw .." + buffw);
		JSONObject obj = new JSONObject();
		obj.put("requestType", "write");

		obj.put("member_id", ((Integer) member_id).toString());
		obj.put("content", content);
		obj.put("filename", filename);

		try {
			buffw.write(obj.toString() + "\n");
			buffw.flush();
			result ="SUCCESS";
		} catch (IOException e) {
			e.printStackTrace();
			result ="FAIL";
		}
		return result;
	}

}
