package Client.board.connect;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import Client.board.main.MainFrame;

public class ClientConnector {
	private MainFrame mainFrame;// ������ ����.
	private Socket client;
	private ClientThread clientThread;
	private int port = 9999;

	private InetAddress serverIP;
	private String ip;
	private Vector list = new Vector();

	public ClientConnector(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		getIP();
		// �̹����� ���� ���� ������.

		connect();

	}

	public void connect() {
		try {
			client = new Socket(ip, port);
			System.out.println("ClientConnectot.connect() : new Socket");
			clientThread = new ClientThread(client, this);
			clientThread.start();

			// �̹����� ���� ���� ������.

			list.add(clientThread);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// ������ �����ϱ� ���� ip�� ���ϴ� method
	// ���� �ϳ��� ��ǻ�Ϳ��� Client, Server�� ���������� LocalHost �� IP�� ���Ѵ�
	public void getIP() {
		try {
			serverIP = serverIP.getLocalHost();
			ip = serverIP.getHostAddress();
			System.out.println("ip" + ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.println("ClientConnector.getIp() : ���� serverIP =" + ip);
	}

//////////////////////////GETTER  / SETTER /////////////////////////

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public Socket getClient() {
		return client;
	}

	public void setClient(Socket client) {
		this.client = client;
	}

	public ClientThread getClientThread() {
		return clientThread;
	}

	public void setClientThread(ClientThread clientThread) {
		this.clientThread = clientThread;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public InetAddress getServerIP() {
		return serverIP;
	}

	public void setServerIP(InetAddress serverIP) {
		this.serverIP = serverIP;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Vector getList() {
		return list;
	}

	public void setList(Vector list) {
		this.list = list;
	}

}
