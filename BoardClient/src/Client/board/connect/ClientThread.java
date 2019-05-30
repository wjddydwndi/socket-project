package Client.board.connect;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import Client.board.distributor.ClientDistributor;
import Client.board.util.WriterDistributor;


public class ClientThread extends Thread {
	private Socket client;
	private ClientConnector clientConnector;
	private ClientDistributor clientDistributor;
	private BufferedReader buffr;
	private BufferedWriter buffw;
	private WriterDistributor writerDistributor;
	boolean flag = true;
	
	
	public ClientThread(Socket client, ClientConnector clientConnector) {
		this.client = client;
		this.clientConnector = clientConnector;
		writerDistributor = new WriterDistributor();
		clientDistributor = new ClientDistributor(this);
		try {
			buffr = new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			
			writerDistributor.setBuffw(buffw);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

	public void send(String msg) {
		try {
			System.out.println("ConnectThread.send()");
			buffw.write(msg + "\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void listen() {
		try {
			String msg;
			msg = buffr.readLine();
			System.out.println("ClientThread.listen().msg :"+msg.length());
			clientDistributor.distribute(msg);
		} catch (IOException e) {

		}
	
	}

	public void run() {
		while (flag) {
			listen();
		}
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

	public Socket getClient() {
		return client;
	}

	public void setClient(Socket client) {
		this.client = client;
	}

	public ClientConnector getClientConnector() {
		return clientConnector;
	}

	public void setClientConnector(ClientConnector clientConnector) {
		this.clientConnector = clientConnector;
	}

	public ClientDistributor getClientDistributor() {
		return clientDistributor;
	}

	public void setClientDistributor(ClientDistributor clientDistributor) {
		this.clientDistributor = clientDistributor;
	}
	
}
