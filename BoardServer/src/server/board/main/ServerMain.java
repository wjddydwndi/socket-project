package server.board.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import server.board.util.GetArea;

public class ServerMain extends JFrame {
	JPanel p_north;
	JPanel p_south;
	JButton bt;
	JTextField t_port;
	private JTextArea area;// 로그를 남기기위해서.
	JScrollPane scroll;

	Thread thread;
	private ServerSocket server;
	private ServerThread serverThread;

	private Vector list = new Vector();

	private int port = 9999;

	public ServerMain() {

		p_north = new JPanel();
		p_south = new JPanel();
		bt = new JButton("서버가동");
		t_port = new JTextField();
		area = new JTextArea();

		// Area를 다른 클래스와 공유
		GetArea.setArea(area);

		scroll = new JScrollPane(area);

		thread = new Thread() {
			public void run() {
				runServer();
			};
		};

		t_port.setText(Integer.toString(port));
		p_north.setBackground(Color.YELLOW);
		p_north.setPreferredSize(new Dimension(500, 100));
		p_south.setBackground(Color.BLUE);
		p_south.setPreferredSize(new Dimension(500, 500));
		scroll.setPreferredSize(new Dimension(500, 500));

		p_south.setLayout(new BorderLayout());

		p_north.add(t_port);
		p_north.add(bt);
		p_south.add(scroll);

		add(p_north, BorderLayout.NORTH);
		add(p_south);
		bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thread.start();
				System.out.println("ServerMain constructor : pressed bt / runServer....\n");
			}
		});

		setSize(500, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setVisible(true);

	}

	public void runServer() {

		try {

			server = new ServerSocket(port);
			System.out.println("ServerMain.runServer() : new ServerSocket \n");
			area.append("서버소켓 생성\n");

			while (true) {
				area.append("접속자 감지중...\n");
				Socket client = server.accept();

				serverThread = new ServerThread(client, this);
				serverThread.start();

				String ip = client.getInetAddress().getHostAddress();

				area.append("ServerMain / client.ip==" + ip + "\n");

				list.add(serverThread);

				area.append("접속자 발견" + ip + "\n");
				area.append("현재 접속자 " + list.size() + "명 입니다.\n");

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	////////////// GETTER / SETTER //////////////////
	public JPanel getP_north() {
		return p_north;
	}

	public void setP_north(JPanel p_north) {
		this.p_north = p_north;
	}

	public JPanel getP_south() {
		return p_south;
	}

	public void setP_south(JPanel p_south) {
		this.p_south = p_south;
	}

	public JButton getBt() {
		return bt;
	}

	public void setBt(JButton bt) {
		this.bt = bt;
	}

	public JTextField getT_port() {
		return t_port;
	}

	public void setT_port(JTextField t_port) {
		this.t_port = t_port;
	}

	public JTextArea getArea() {
		return area;
	}

	public void setArea(JTextArea area) {
		this.area = area;
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public ServerSocket getServer() {
		return server;
	}

	public void setServer(ServerSocket server) {
		this.server = server;
	}

	public ServerThread getServerThread() {
		return serverThread;
	}

	public void setServerThread(ServerThread serverThread) {
		this.serverThread = serverThread;
	}

	public Vector getList() {
		return list;
	}

	public void setList(Vector list) {
		this.list = list;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public static void main(String[] args) {
		new ServerMain();
	}

}
