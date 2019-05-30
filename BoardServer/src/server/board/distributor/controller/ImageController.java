package server.board.distributor.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;

import javax.swing.JTextArea;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import server.board.distributor.ServerDistributor;
import server.board.distributor.ServerImageDistributor;

public class ImageController {
	private ServerDistributor serverDistributor;

	private String uploadPath;//���� ���ε�� ���
	private String path = "C:/java_developer/javaSE/BoardServer/serverdata";//���ε� �� ������ ���
	private BufferedWriter buffw;//Ŭ���̾�Ʈ �۽� ��Ʈ��.
	
	private JTextArea area;
	
	public ImageController(ServerDistributor serverDistributor) {
		this.serverDistributor = serverDistributor;
		area = serverDistributor.getArea();
		buffw = serverDistributor.getServerThread().getBuffw();

	}

	public String imageTransaction(JSONObject obj) {
		String filename = (String) obj.get("filename");
		JSONArray array = (JSONArray) obj.get("array");

		String result = fileUpload(array, filename);

		if (result.equals("SUCCESS")) {
			distributeClient(uploadPath, filename);

		}
		return null;
	}

	public String fileUpload(JSONArray array, String filename) {
		area.append("ImageController / fileUpload() ����\n");
		String result = null;
		BufferedWriter writer = null;
		
		uploadPath = path + File.separator + filename;

		try {
			if (writer == null) {

				writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(uploadPath)));
				area.append("Server / ImageController / FileUpload() / ���ε带 ���� �̹��� ��Ʈ�� ����.........\n");

			}

			String data = null;

			Iterator it = array.iterator();

			area.append("Server / ImageController / FileUpload() / Ŭ���̾�Ʈ�κ��� ������ ���� ���ε� ��.......\n");

			while (it.hasNext()) {
				JSONObject obj = (JSONObject) it.next();
				String datas = (String) obj.get("data");
				writer.write(datas);
			}
			
			writer.flush();
			result = "SUCCESS";
			
			area.append("Server / ImageController / FileUpload() / Ŭ���̾�Ʈ�κ��� ������ ���� ���ε� �Ϸ�!  : " + result+" \n");
		} catch (FileNotFoundException e) {
			result = "FAIL";
			area.append("Server / ImageController / FileUpload() / Ŭ���̾�Ʈ�κ��� ������ ���� ���ε� ����!  : " + result+" \n");
			e.printStackTrace();
		} catch (IOException e) {
			result = "FAIL";
			area.append("Server / ImageController / FileUpload() / Ŭ���̾�Ʈ�κ��� ������ ���� ���ε� ����!  : " + result+" \n");
			
			
			e.printStackTrace();
		}finally {
			if(writer!=null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
		

		return result;
	}

	// ���ε� �� �̹����� �о�鿩, Ŭ���̾�Ʈ�鿡�� �۽��Ѵ�.
	// ServerImgThread�� ���ǵ� �޼��� ȣ��.
	public void distributeClient(String uploadPath, String filename) {
		area.append("ImageController / distributeClient() ����\n");
		String result = null;//�۽� ����� ���� ����
		JSONArray array = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		BufferedReader reader = null;
		
		jsonObject.put("requestType", "image");
		jsonObject.put("filename", filename);
		area.append("ImageController / distributeClient() / requestType : image \n");
		try {
			if(reader==null) {
		
				reader= new BufferedReader(new InputStreamReader(new FileInputStream(uploadPath)));
				area.append("ImageController / distributeClient() / ���ε� ������ �д� ��Ʈ�� ���� \n");
			}
			
			area.append("ImageController / distributeClient() / "+filename+" �� Ŭ���̾�Ʈ�� �۽����Դϴ�..........\n");
			String data = null;
			while(true) {
				data =reader.readLine();
				if(data==null) {
					break;
				}
				JSONObject obj = new JSONObject();
				obj.put("data", data);
				array.add(obj);
			}
			
			jsonObject.put("array", array);
			
			buffw.write(jsonObject.toString()+"\n");
			buffw.flush();
			result = "SUCCESS";
			area.append("ImageController / distributeClient() / "+filename+" Ŭ���̾�Ʈ�� �Ϸ�.........."+result+"\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			result = "FAIL";
			area.append("ImageController / distributeClient() / "+filename+" Ŭ���̾�Ʈ�� ����.........."+result+"\n");
		} catch (IOException e) {
			e.printStackTrace();
			result = "FAIL";
			area.append("ImageController / distributeClient() / "+filename+" Ŭ���̾�Ʈ�� ����.........."+result+"\n");
		}finally {
			if(reader!=null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
	}
}
