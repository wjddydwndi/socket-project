package server.board.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.Socket;

import javax.swing.JTextArea;

import server.board.distributor.ServerDistributor;
import server.board.util.GetArea;
import server.board.util.WriterDistributor;

public class ServerThread extends Thread {
	private Socket client;
	private ServerMain serverMain;
	private BufferedReader buffr;
	private BufferedWriter buffw;
	private JTextArea area;
	private String msg;
	boolean flag = true;
	private ServerDistributor serverDistributor;
	private WriterDistributor writerDistributor;

	public ServerThread(Socket client, ServerMain serverMain) {
		this.client = client;
		this.serverMain = serverMain;
		area = serverMain.getArea();
		writerDistributor = new WriterDistributor();

		try {
			buffr = new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			writerDistributor.setBuffw(buffw);
			serverDistributor = new ServerDistributor(this);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void send(String msg) {
		try {
			buffw.write(msg + "\n");
			buffw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void listen() {
		
		try {
				msg = buffr.readLine();
				area.append("ServerThread.listen()" + msg.length() + "\n");// 로그를 남긴다.
				
			serverDistributor.distribute(msg);
		} catch (IOException e) {
			serverMain.getList().remove(this);
			flag = false;
			area.append("퇴장하셨습니다. \n");// 로그를 남긴다.

			System.out.println("ServerThread.listen().catch(IOException e ) : flag = false \n");

			e.printStackTrace();
		}
	}

	public void run() {
		while (flag) {
			listen();
		}
	}

	public Socket getClient() {
		return client;
	}

	public void setClient(Socket client) {
		this.client = client;
	}

	public ServerMain getServerMain() {
		return serverMain;
	}

	public void setServerMain(ServerMain serverMain) {
		this.serverMain = serverMain;
	}

	public BufferedReader getBuffr() {
		return buffr;
	}

	public void setBuffr(BufferedReader buffr) {
		this.buffr = buffr;
	}

	public BufferedWriter getBuffw() {
		return buffw;
	}

	public void setBuffw(BufferedWriter buffw) {
		this.buffw = buffw;
	}

	public JTextArea getArea() {
		return area;
	}

	public void setArea(JTextArea area) {
		this.area = area;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public ServerDistributor getServerDistributor() {
		return serverDistributor;
	}

	public void setServerDistributor(ServerDistributor serverDistributor) {
		this.serverDistributor = serverDistributor;
	}

}
