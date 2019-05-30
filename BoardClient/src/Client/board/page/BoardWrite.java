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

	JPanel p_title;// 글쓰기 제목이 들어갈 곳
	Image title;

	JLabel imageLabel;// 글쓰기 이미지 올라오는 곳

	JPanel p_load;// 사진 불러오기 버튼
	JButton bt_load;

	JTextArea area;
	JScrollPane scroll;

	JPanel p_bt;// 버튼이 들어갈 패널
	JButton bt_regist;// 등록
	JButton bt_cancel; // 취소

	private String filePath;// 선택한 이미지 경로
	private File file;// 내가 선택한 파일
	private String userData = "C:/java_developer/javaSE/BoardClient/data";// 업로드될 폴더의 경로
	private FileManager fileManager;// 확장자 추출.
	private String filename; // 업로드된 파일 명
	private String dataPath;//RequestWrite에서 Server로 보낼 업로드된 이미지 경로

	// 로그인 정보를 담음.
	private Member member = new Member();

	private RequestWrite requestWrite;// 글쓰기 요청
	private RequestImage requestImage; // 서버에 이미지 전송

	
	
	
	/*글 내용, 작성자, 파일 이름이 DB에 등록된 후, 서버측에서 결과값이 전해진 후에,
	 *클라이언트 측, 파일 업로드 된 경로에서 파일을 읽어들여, 다시 서버로 보내준다.
	 *why) 서버에서도 DB에 등록된 파일명에 해당하는 파일을 업로드 해줘야 하기 때문이다.
	 *- 그래야 모든 클라이언트 측에 그 파일을 다 보내 업로드 시킬 수 있기 때문이다.
	 그렇기 때문에 글을 등록하는 클라이언트 측 파일업로드가 완료된 시점에 미리, 서버로부터 결과값을 받는 클래스에 업로드 파일 경로를 주입시켜 놓는다.*/
	private WriteController writeController;
	
	
	public BoardWrite(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		fileManager = new FileManager();
		requestWrite = new RequestWrite(this);
		requestImage = new RequestImage(this);
		//dataPath를 보내기 위해.
		writeController = mainFrame.getConnector().getClientThread().getClientDistributor().getWriteController();
		
		p_title = new JPanel();
		imageLabel = new JLabel();
		p_load = new JPanel();
		bt_load = new JButton("불러오기");
		area = new JTextArea();
		scroll = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		p_bt = new JPanel();
		bt_regist = new JButton("등록");
		bt_cancel = new JButton("취소");

		// 사이즈 & 컬러

		p_title.setPreferredSize(new Dimension(300, 100));

		imageLabel.setPreferredSize(new Dimension(300, 300));

		p_load.setPreferredSize(new Dimension(300, 20));
		bt_load.setPreferredSize(new Dimension(100, 20));

		scroll.setPreferredSize(new Dimension(300, 100));

		p_bt.setPreferredSize(new Dimension(500, 60));
		bt_regist.setPreferredSize(new Dimension(140, 50));
		bt_cancel.setPreferredSize(new Dimension(140, 50));

		// 부착
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
				// 사진 경로도 받아 넣기
				// 사진 업로드 후, 그사진을 게시판에 올리기.
				if (file != null) {
					upload();
				}
				//게시글 등록
				String result=write();
				
				if(result.equals("SUCCESS")) {
					
					//////////////////////////////////////////////////////////////////////////////////////////
					//String result2 = requestImage.sendImage(dataPath, filename);
					//System.out.println("BoardWrite / 업로드 된 파일 전송 결과  :  "+result2);
					
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
		System.out.println("BoardWrite 초기화 완료");
	}

	public void openFile() {
		JFileChooser chooser = new JFileChooser();
		// 파일 필터*(파일 이름 창에 출력될 문자열, 파일 필터로 사용될 확장자)
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".jpg , .png , .gif images", "jpg", "png", "gif");

		chooser.setFileFilter(filter);// 적용

		// 파일 다이얼로그 출력.
		int index = chooser.showOpenDialog(null);
		if (index == JFileChooser.APPROVE_OPTION) {// 사용자가 창을 강제로 닫았거나, 취소버튼을 눌렀을 경우
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
			
			byte[] b = new byte[1024];// 1kbyte씩 읽어들임.
			int data = -1;// byte[]가 없었다면, 데이터를 이곳에 담았지만, byte[]를 만들었기 때문에 데이터는 byte에 담게 된다.
			// data의 역할은 끝에 도달했는지를 알려주기만 한다.
			while (true) {
				data = fis.read(b);// 입력
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
