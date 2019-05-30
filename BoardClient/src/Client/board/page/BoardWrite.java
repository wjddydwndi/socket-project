package Client.board.page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import Client.board.controller.WriteController;
import Client.board.main.MainFrame;
import Client.board.model.domain.Member;
import Client.board.requestType.RequestImage;
import Client.board.requestType.RequestWrite;
import Client.board.util.FileManager;

public class BoardWrite extends JPanel {
	private MainFrame mainFrame;
	Content content;

	JPanel p_title;// �۾��� ������ �� ��
	Image title;

	JLabel imageLabel;// �۾��� �̹��� �ö���� ��

	JPanel p_load;// ���� �ҷ����� ��ư
	JButton bt_load;

	JTextArea area;
	JScrollPane scroll;

	JPanel p_bt;// ��ư�� �� �г�
	JButton bt_regist;// ���
	JButton bt_cancel; // ���

	private String filePath;// ������ �̹��� ���
	private File file;// ���� ������ ����
	private String userData = "C:/java_developer/javaSE/BoardClient/data";// ���ε�� ������ ���
	private FileManager fileManager;// Ȯ���� ����.
	private String filename; // ���ε�� ���� ��
	private String dataPath;//RequestWrite���� Server�� ���� ���ε�� �̹��� ���

	// �α��� ������ ����.
	private Member member = new Member();

	private RequestWrite requestWrite;// �۾��� ��û
	private RequestImage requestImage; // ������ �̹��� ����

	
	
	
	/*�� ����, �ۼ���, ���� �̸��� DB�� ��ϵ� ��, ���������� ������� ������ �Ŀ�,
	 *Ŭ���̾�Ʈ ��, ���� ���ε� �� ��ο��� ������ �о�鿩, �ٽ� ������ �����ش�.
	 *why) ���������� DB�� ��ϵ� ���ϸ� �ش��ϴ� ������ ���ε� ����� �ϱ� �����̴�.
	 *- �׷��� ��� Ŭ���̾�Ʈ ���� �� ������ �� ���� ���ε� ��ų �� �ֱ� �����̴�.
	 �׷��� ������ ���� ����ϴ� Ŭ���̾�Ʈ �� ���Ͼ��ε尡 �Ϸ�� ������ �̸�, �����κ��� ������� �޴� Ŭ������ ���ε� ���� ��θ� ���Խ��� ���´�.*/
	private WriteController writeController;
	
	
	public BoardWrite(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		fileManager = new FileManager();
		requestWrite = new RequestWrite(this);
		requestImage = new RequestImage(this);
		//dataPath�� ������ ����.
		writeController = mainFrame.getConnector().getClientThread().getClientDistributor().getWriteController();
		
		p_title = new JPanel();
		imageLabel = new JLabel();
		p_load = new JPanel();
		bt_load = new JButton("�ҷ�����");
		area = new JTextArea();
		scroll = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		p_bt = new JPanel();
		bt_regist = new JButton("���");
		bt_cancel = new JButton("���");

		// ������ & �÷�

		p_title.setPreferredSize(new Dimension(300, 100));

		imageLabel.setPreferredSize(new Dimension(300, 300));

		p_load.setPreferredSize(new Dimension(300, 20));
		bt_load.setPreferredSize(new Dimension(100, 20));

		scroll.setPreferredSize(new Dimension(300, 100));

		p_bt.setPreferredSize(new Dimension(500, 60));
		bt_regist.setPreferredSize(new Dimension(140, 50));
		bt_cancel.setPreferredSize(new Dimension(140, 50));

		// ����
		add(p_title);
		add(imageLabel);
		p_load.setLayout(new BorderLayout());
		p_load.add(bt_load, BorderLayout.EAST);
		add(p_load);
		add(scroll);
		p_bt.add(bt_regist);
		p_bt.add(bt_cancel);
		add(p_bt);

		repaint();
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(500, 600));
		setVisible(false);
		

		bt_load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});

		bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ���� ��ε� �޾� �ֱ�
				// ���� ���ε� ��, �׻����� �Խ��ǿ� �ø���.
				if (file != null) {
					upload();
				}
				//�Խñ� ���
				String result=write();
				
				if(result.equals("SUCCESS")) {
					
					//////////////////////////////////////////////////////////////////////////////////////////
					//String result2 = requestImage.sendImage(dataPath, filename);
					//System.out.println("BoardWrite / ���ε� �� ���� ���� ���  :  "+result2);
					
					////////////////////////////////////////////////////////////////////////////////////////////
				}
				

			}
		});

		bt_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.showPage(2);
				BoardMain boardMain= (BoardMain)mainFrame.getPage(2);
				area.setText("");
				imageLabel.setIcon(null);
				
				boardMain.selectAll();
			}
		});
		System.out.println("BoardWrite �ʱ�ȭ �Ϸ�");
	}

	public void openFile() {
		JFileChooser chooser = new JFileChooser();
		// ���� ����*(���� �̸� â�� ��µ� ���ڿ�, ���� ���ͷ� ���� Ȯ����)
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".jpg , .png , .gif images", "jpg", "png", "gif");

		chooser.setFileFilter(filter);// ����

		// ���� ���̾�α� ���.
		int index = chooser.showOpenDialog(null);
		if (index == JFileChooser.APPROVE_OPTION) {// ����ڰ� â�� ������ �ݾҰų�, ��ҹ�ư�� ������ ���
			imageLabel.repaint();
			file = chooser.getSelectedFile();
			filePath = file.getAbsolutePath();
			imageLabel.setIcon(new ImageIcon(filePath));
		}

	}

	public void upload() {
		System.out.println("// BoardWrite / upload / filePath ==" + filePath);

		filename = fileManager.getFilename(filePath);

		System.out.println("// BoardWrite / upload / filename ==" + filename);
		System.out.println("// BoardWrite / upload / userData ==" + userData);

		dataPath=userData + File.separator + filename;
		
		FileInputStream fis = null;
		FileOutputStream fos = null;

		try {
			fis = new FileInputStream(filePath);
			fos = new FileOutputStream(dataPath);
			
			byte[] b = new byte[1024];// 1kbyte�� �о����.
			int data = -1;// byte[]�� �����ٸ�, �����͸� �̰��� �������, byte[]�� ������� ������ �����ʹ� byte�� ��� �ȴ�.
			// data�� ������ ���� �����ߴ����� �˷��ֱ⸸ �Ѵ�.
			while (true) {
				data = fis.read(b);// �Է�
				if (data == -1) {
					break;
				}
				fos.write(b);
				fos.flush();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public String  write() {
		int member_id = member.getMember_id();
		String content = area.getText();
		
		//writeController.setDataPath(dataPath);
		String result =requestWrite.write(member_id, content, dataPath,filename);
		
		return result ;
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public JPanel getP_title() {
		return p_title;
	}

	public void setP_title(JPanel p_title) {
		this.p_title = p_title;
	}

	public Image getTitle() {
		return title;
	}

	public void setTitle(Image title) {
		this.title = title;
	}

	public JLabel getImageLabel() {
		return imageLabel;
	}

	public void setImageLabel(JLabel imageLabel) {
		this.imageLabel = imageLabel;
	}

	public JPanel getP_load() {
		return p_load;
	}

	public void setP_load(JPanel p_load) {
		this.p_load = p_load;
	}

	public JButton getBt_load() {
		return bt_load;
	}

	public void setBt_load(JButton bt_load) {
		this.bt_load = bt_load;
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

	public JPanel getP_bt() {
		return p_bt;
	}

	public void setP_bt(JPanel p_bt) {
		this.p_bt = p_bt;
	}

	public JButton getBt_regist() {
		return bt_regist;
	}

	public void setBt_regist(JButton bt_regist) {
		this.bt_regist = bt_regist;
	}

	public JButton getBt_cancel() {
		return bt_cancel;
	}

	public void setBt_cancel(JButton bt_cancel) {
		this.bt_cancel = bt_cancel;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getUserData() {
		return userData;
	}

	public void setUserData(String userData) {
		this.userData = userData;
	}

	public FileManager getFileManager() {
		return fileManager;
	}

	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	public RequestWrite getRequestWrite() {
		return requestWrite;
	}

	public void setRequestWrite(RequestWrite requestWrite) {
		this.requestWrite = requestWrite;
	}

	public WriteController getWriteController() {
		return writeController;
	}

	public void setWriteController(WriteController writeController) {
		this.writeController = writeController;
	}

}
