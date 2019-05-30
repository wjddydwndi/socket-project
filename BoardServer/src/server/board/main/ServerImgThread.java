package server.board.main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JTextArea;

import server.board.distributor.ServerImageDistributor;
import server.board.util.GetArea;

public class ServerImgThread extends Thread {
	/*
	 * //******************************************************* private
	 * DataInputStream dis; private DataOutputStream dos;
	 * //******************************************************* private
	 * BufferedInputStream bis;
	 * 
	 * //*******************************************************
	 * 
	 * private JTextArea area; private boolean flag = true; private Socket client;
	 * private ServerMain serverMain; private ServerImageDistributor
	 * serverImageDistributor; private String filename; private String uploadPath;
	 * 
	 * 
	 * public ServerImgThread(Socket client, ServerMain serverMain) { this.client =
	 * client; this.serverMain = serverMain; area = serverMain.getArea();
	 * serverImageDistributor = new ServerImageDistributor(this);
	 * 
	 * try {
	 * 
	 * dos = new DataOutputStream(client.getOutputStream()); dis = new
	 * DataInputStream(client.getInputStream());
	 * 
	 * } catch (IOException e) { e.printStackTrace(); } }
	 * 
	 * public void send(String uploadPath, String filename) {
	 * distributeClient(uploadPath, filename);
	 * 
	 * }
	 * 
	 * public void listen() { try {
	 * 
	 * String type = dis.readUTF();
	 * area.append("ServerImgThread / listen() / type==" + type+'\n');
	 * 
	 * if (type.equals("image")) { //serverImageDistributor.distribute(dis);
	 * area.append("시동시동 \n"); fileUpload(dis); send(uploadPath, filename); } }
	 * catch (IOException e) { e.printStackTrace(); } }
	 * 
	 * public void distributeClient(String uploadPath, String filename) {
	 * 
	 * if (uploadPath != null) {
	 * area.append("ServerImgThread / distributeClient  / uploadPath  :  "
	 * +uploadPath+"\n");
	 * System.out.println("ServerImgThread / distributeClient  / uploadPath  :  "
	 * +uploadPath);
	 * 
	 * try { bis = new BufferedInputStream(new FileInputStream(uploadPath));
	 * 
	 * int len = -1; int size = 4096; byte[] data = new byte[size];
	 * 
	 * // 각 클라이언트마다, DATA 타입을 전송. dos.writeUTF("image"); dos.flush();
	 * 
	 * dos.writeUTF(filename); dos.flush();
	 * 
	 * while (true) { len = bis.read(data); if (len == -1) { break; }
	 * dos.write(data, 0, len); } dos.flush();
	 * 
	 * area.append("ServerImageThread  / distributeClient() / 클라이언트들에게 송신 SUCCESS\n"
	 * ); System.out.
	 * println("ServerImageThread  / distributeClient() / 클라이언트들에게 송신 SUCCESS");
	 * 
	 * 
	 * } catch (FileNotFoundException e) {
	 * area.append("ServerImageThread  / distributeClient() / 클라이언트들에게 송신 FAIL\n");
	 * System.out.
	 * println("ServerImageThread  / distributeClient() / 클라이언트들에게 송신 FAIL");
	 * e.printStackTrace(); } catch (IOException e) {
	 * area.append("ServerImageThread  / distributeClient() / 클라이언트들에게 송신 FAIL\n");
	 * e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * public void fileUpload(DataInputStream dis) {
	 * area.append("ImageController / fileUpload() 진입\n"); filename = null; String
	 * path = "C:/java_developer/javaSE/BoardServer/serverdata"; DataOutputStream
	 * bos = null;
	 * 
	 * try { filename = dis.readUTF();
	 * 
	 * uploadPath = path + File.separator + filename; bos = new DataOutputStream(new
	 * FileOutputStream(uploadPath));
	 * 
	 * int len; byte[] data = new byte[4096];
	 * 
	 * while (true) { len = dis.read(data); if (len == -1) { break; }
	 * bos.write(data, 0, len);
	 * 
	 * }
	 * 
	 * bos.flush(); } catch (FileNotFoundException e) { e.printStackTrace(); } catch
	 * (IOException e) { e.printStackTrace(); } finally { if (bos!= null) { try {
	 * bos.close(); } catch (IOException e) { e.printStackTrace(); } } }
	 * 
	 * }
	 * 
	 * public void run() { while (flag) { listen(); } }
	 * 
	 * public DataInputStream getDis() { return dis; }
	 * 
	 * public void setDis(DataInputStream dis) { this.dis = dis; }
	 * 
	 * public DataOutputStream getDos() { return dos; }
	 * 
	 * public void setDos(DataOutputStream dos) { this.dos = dos; }
	 * 
	 * public BufferedInputStream getBis() { return bis; }
	 * 
	 * public void setBis(BufferedInputStream bis) { this.bis = bis; }
	 * 
	 * public JTextArea getArea() { return area; }
	 * 
	 * public void setArea(JTextArea area) { this.area = area; }
	 * 
	 * public boolean isFlag() { return flag; }
	 * 
	 * public void setFlag(boolean flag) { this.flag = flag; }
	 * 
	 * public Socket getClient() { return client; }
	 * 
	 * public void setClient(Socket client) { this.client = client; }
	 * 
	 * public ServerMain getServerMain() { return serverMain; }
	 * 
	 * public void setServerMain(ServerMain serverMain) { this.serverMain =
	 * serverMain; }
	 * 
	 * public ServerImageDistributor getServerImageDistributor() { return
	 * serverImageDistributor; }
	 * 
	 * public void setServerImageDistributor(ServerImageDistributor
	 * serverImageDistributor) { this.serverImageDistributor =
	 * serverImageDistributor; }
	 */
}
