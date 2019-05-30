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

	private String uploadPath;//파일 업로드된 경로
	private String path = "C:/java_developer/javaSE/BoardServer/serverdata";//업로드 할 파일의 경로
	private BufferedWriter buffw;//클라이언트 송신 스트림.
	
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
		area.append("ImageController / fileUpload() 진입\n");
		String result = null;
		BufferedWriter writer = null;
		
		uploadPath = path + File.separator + filename;

		try {
			if (writer == null) {

				writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(uploadPath)));
				area.append("Server / ImageController / FileUpload() / 업로드를 위한 이미지 스트림 생성.........\n");

			}

			String data = null;

			Iterator it = array.iterator();

			area.append("Server / ImageController / FileUpload() / 클라이언트로부터 수신한 파일 업로드 중.......\n");

			while (it.hasNext()) {
				JSONObject obj = (JSONObject) it.next();
				String datas = (String) obj.get("data");
				writer.write(datas);
			}
			
			writer.flush();
			result = "SUCCESS";
			
			area.append("Server / ImageController / FileUpload() / 클라이언트로부터 수신한 파일 업로드 완료!  : " + result+" \n");
		} catch (FileNotFoundException e) {
			result = "FAIL";
			area.append("Server / ImageController / FileUpload() / 클라이언트로부터 수신한 파일 업로드 실패!  : " + result+" \n");
			e.printStackTrace();
		} catch (IOException e) {
			result = "FAIL";
			area.append("Server / ImageController / FileUpload() / 클라이언트로부터 수신한 파일 업로드 실패!  : " + result+" \n");
			
			
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

	// 업로드 된 이미지를 읽어들여, 클라이언트들에게 송신한다.
	// ServerImgThread에 정의된 메서드 호출.
	public void distributeClient(String uploadPath, String filename) {
		area.append("ImageController / distributeClient() 진입\n");
		String result = null;//송신 결과를 담을 변수
		JSONArray array = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		BufferedReader reader = null;
		
		jsonObject.put("requestType", "image");
		jsonObject.put("filename", filename);
		area.append("ImageController / distributeClient() / requestType : image \n");
		try {
			if(reader==null) {
		
				reader= new BufferedReader(new InputStreamReader(new FileInputStream(uploadPath)));
				area.append("ImageController / distributeClient() / 업로드 파일을 읽는 스트림 생성 \n");
			}
			
			area.append("ImageController / distributeClient() / "+filename+" 을 클라이언트로 송신중입니다..........\n");
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
			area.append("ImageController / distributeClient() / "+filename+" 클라이언트로 완료.........."+result+"\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			result = "FAIL";
			area.append("ImageController / distributeClient() / "+filename+" 클라이언트로 실패.........."+result+"\n");
		} catch (IOException e) {
			e.printStackTrace();
			result = "FAIL";
			area.append("ImageController / distributeClient() / "+filename+" 클라이언트로 실패.........."+result+"\n");
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
