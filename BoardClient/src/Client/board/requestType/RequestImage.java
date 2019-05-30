package Client.board.requestType;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Client.board.main.MainFrame;
import Client.board.page.BoardWrite;
import Client.board.util.GetMain;

public class RequestImage {
	private BoardWrite boardWrite;
	private MainFrame mainFrame;
	private BufferedReader buffr;
	private BufferedWriter buffw;

//************************************************************************
	private BufferedReader reader;// 클라이언트의 업로드한 파일을 읽어들이는 스트림.
	private BufferedWriter writer; // 이미지 업로드용 스트림.
//************************************************************************	

	public RequestImage(BoardWrite boardWrite) {
		this.boardWrite = boardWrite;
		mainFrame = GetMain.getMainFrame();
		buffr = mainFrame.getConnector().getClientThread().getBuffr();
		buffw = mainFrame.getConnector().getClientThread().getBuffw();
	}

	public String sendImage(String dataPath, String filename) {
		String result = sendServer(dataPath, filename);

		return result;
	}

	// 업로드 된 파일을 서버로 전송.
	public String sendServer(String path, String filename) {
		String result = null;
		JSONArray array = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		try {

			reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
			
			// 전송하는 requestType 명시
			jsonObject.put("requestType", "image");
			jsonObject.put("filename", filename);
			

			System.out.println("Client : RequestImage / sendServer / requestType / image ");
			System.out.println("Client : RequestImage / sendServer / requestType / filename =  " + filename);
			System.out.println("Client : RequestImage / sendServer / requestType / upload Path =  " + path);
			System.out.println("Client : RequestImage /업로드 된 이미지 파일을 읽어 들이는 중입니다...\n 이미지파일을 서버로 전송하는 중입니다.");

			// 업로드 된 파일을 읽어 들여, 서버로 전송함.
			String data = null;

			while (true) {
				data = reader.readLine();

				if (data == null) {break;}

				JSONObject obj = new JSONObject();
				obj.put("data", data);
				array.add(obj);
			}

			jsonObject.put("array", array);
			
			
			buffw.write(jsonObject.toString()+"\n");
			buffw.flush();

			result = "SUCCESS";
			System.out.println("Client : RequestImage / sendServer  /업로드 된 이미지 파일을 서버로 전송하였습니다.");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			result = "FAIL";
		} catch (IOException e) {
			e.printStackTrace();
			result = "FAIL";
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;

	}

}
