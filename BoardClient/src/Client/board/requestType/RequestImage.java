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
	private BufferedReader reader;// Ŭ���̾�Ʈ�� ���ε��� ������ �о���̴� ��Ʈ��.
	private BufferedWriter writer; // �̹��� ���ε�� ��Ʈ��.
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

	// ���ε� �� ������ ������ ����.
	public String sendServer(String path, String filename) {
		String result = null;
		JSONArray array = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		try {

			reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
			
			// �����ϴ� requestType ���
			jsonObject.put("requestType", "image");
			jsonObject.put("filename", filename);
			

			System.out.println("Client : RequestImage / sendServer / requestType / image ");
			System.out.println("Client : RequestImage / sendServer / requestType / filename =  " + filename);
			System.out.println("Client : RequestImage / sendServer / requestType / upload Path =  " + path);
			System.out.println("Client : RequestImage /���ε� �� �̹��� ������ �о� ���̴� ���Դϴ�...\n �̹��������� ������ �����ϴ� ���Դϴ�.");

			// ���ε� �� ������ �о� �鿩, ������ ������.
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
			System.out.println("Client : RequestImage / sendServer  /���ε� �� �̹��� ������ ������ �����Ͽ����ϴ�.");

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
