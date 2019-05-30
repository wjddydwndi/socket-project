package Client.board.registMember;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import Client.board.main.MainFrame;
import Client.board.requestType.RequestCheckId;
import Client.board.requestType.RequestRegist;

public class RegistMember extends JPanel {
	private MainFrame mainFrame;

	// 전체패널
	private JPanel p_top;
	private JPanel p_center;
	private JPanel p_bottom;

	// p_center
	private JPanel p_center_title;
	private JPanel p_center_content;

	// p_center_content
	private JPanel p_center_content1;
	private JPanel p_center_content2;

	// p_p_center_content1
	private JPanel p_id;
	private JPanel p_password;
	private JPanel p_password2;
	private JPanel p_name;

	private JLabel la_id;
	private JTextField t_id;
	private JLabel la_password;
	private JTextField t_password;
	private JLabel la_password2;
	private JTextField t_password2;
	private JLabel la_name;
	private JTextField t_name;

	// p_center_content2
	private JButton bt_check;

	// p_bottom
	private JButton bt_regist;
	private JButton bt_back;

	// 아이디 패스워드
	private 	String id;
	private String password;
	private 	String password2;
	private 	String name;

	// request
	private 	RequestCheckId checkId;
	private RequestRegist regist;

	private boolean flag = false;// 아이디 중복체크 여부

	public RegistMember(MainFrame mainFrame) {
		this.mainFrame = mainFrame;

		p_top = new JPanel();
		p_center = new JPanel();
		p_bottom = new JPanel();
		p_center_title = new JPanel();
		p_id = new JPanel();
		p_password = new JPanel();
		p_password2 = new JPanel();
		p_name = new JPanel();
		bt_regist = new JButton("가입");
		bt_back = new JButton("취소");
		la_id = new JLabel("ID");
		t_id = new JTextField();
		la_password = new JLabel("PW");
		t_password = new JTextField();
		la_password2 = new JLabel("PW2");
		t_password2 = new JTextField();
		la_name = new JLabel("NAME");
		t_name = new JTextField();
		bt_check = new JButton("중복체크");
		p_center_content = new JPanel();
		p_center_content1 = new JPanel();
		p_center_content2 = new JPanel();

		// request
		checkId = new RequestCheckId(this);
		regist = new RequestRegist(this);

		// size
		// 전체 패널 사이즈
		p_top.setPreferredSize(new Dimension(500, 60));
		p_center.setPreferredSize(new Dimension(500, 420));
		p_bottom.setPreferredSize(new Dimension(500, 100));

		p_top.setBackground(Color.WHITE);
		p_center.setBackground(Color.BLUE);
		p_bottom.setBackground(Color.RED);

		// p_center 패널 사이즈/ 컬러
		p_center_title.setPreferredSize(new Dimension(500, 150));
		p_center_content.setPreferredSize(new Dimension(500, 270));

		// p_center_content 패널 사이즈/ 컬러
		p_center_content1.setPreferredSize(new Dimension(300, 270));
		p_center_content2.setPreferredSize(new Dimension(180, 270));

		// p_center_content1
		p_id.setPreferredSize(new Dimension(300, 50));
		p_password.setPreferredSize(new Dimension(300, 50));
		p_password2.setPreferredSize(new Dimension(300, 50));
		p_name.setPreferredSize(new Dimension(300, 50));

		// p_center_content2

		bt_check.setPreferredSize(new Dimension(100, 40));
		// p_center 사이즈/컬러
		la_id.setPreferredSize(new Dimension(50, 40));
		t_id.setPreferredSize(new Dimension(200, 40));
		la_password.setPreferredSize(new Dimension(50, 40));
		t_password.setPreferredSize(new Dimension(200, 40));
		la_password2.setPreferredSize(new Dimension(50, 40));
		t_password2.setPreferredSize(new Dimension(200, 40));
		la_name.setPreferredSize(new Dimension(50, 40));
		t_name.setPreferredSize(new Dimension(200, 40));

		// p_bt 사이즈/컬러
		bt_regist.setPreferredSize(new Dimension(100, 50));
		bt_back.setPreferredSize(new Dimension(100, 50));

		// 부착

		// p_center_content1에 부착 패널
		// p_center_content1.setLayout(new BorderLayout());

		p_center_content1.add(p_id);
		p_center_content1.add(p_password);
		p_center_content1.add(p_password2);
		p_center_content1.add(p_name);

		// p_center_content1의 panel 에 부착
		p_id.add(la_id);
		p_id.add(t_id);
		p_password.add(la_password);
		p_password.add(t_password);
		p_password2.add(la_password2);
		p_password2.add(t_password2);
		p_name.add(la_name);
		p_name.add(t_name);

		// p_center_content2
		p_center_content2.add(bt_check);

		// p_center_content에 부착
		p_center_content.add(p_center_content1);
		p_center_content.add(p_center_content2);

		// p_center
		p_center.setLayout(new BorderLayout());
		p_center.add(p_center_title, BorderLayout.NORTH);
		p_center.add(p_center_content);

		// p_bottom에 부착
		p_bottom.add(bt_regist);
		p_bottom.add(bt_back);

		// 전체패널 부착
		setLayout(new BorderLayout());
		add(p_top, BorderLayout.NORTH);
		add(p_center);
		add(p_bottom, BorderLayout.SOUTH);

		// 아이디 중복성 체크
		bt_check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkId();// 아이디 중복성 체크 요청

			}
		});

		// 회원가입하기 버튼
		bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emptyCheck();// 공백 여부
				passwordCheck();// 패스워드 1 패스워드 2 일치여부
				if (flag == true) {
					System.out.println("Client : registMember.bt_regist.flag==true");
					regist(); // 회원가입 요청
				} else {
					System.out.println("Client : registMember.bt_regist.flag==false");
					return;
				}
			}
		});

		// 취소버튼
		bt_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.showPage(0);

			}
		});

		// 중복성 체크 후 아이디 변경하는 것을 막기 위해서....
		Document doc = t_id.getDocument();
		doc.addDocumentListener(new DocumentListener() {

			public void removeUpdate(DocumentEvent arg0) {
				System.out.println("removeUpdate");
				flag = false;
			}

			public void insertUpdate(DocumentEvent arg0) {
				System.out.println("insertUpdate");
				flag = false;
			}

			public void changedUpdate(DocumentEvent arg0) {
				System.out.println("changedUpdate");
			}
		});

		setBackground(Color.BLUE);
		setPreferredSize(new Dimension(500, 600));
		setVisible(false);

	}

	public void emptyCheck() {
		getInfo();

		// 회원가입란 공백 방지.
		if (id.length() == 0) {
			JOptionPane.showMessageDialog(this, "아이디를 입력해주세요.");
			t_id.requestFocus();
			return;
		} else if (password.length() == 0) {
			JOptionPane.showMessageDialog(this, "패스워드를 입력해주세요.");
			t_password.requestFocus();
			return;
		} else if (password2.length() == 0) {
			JOptionPane.showMessageDialog(this, "패스워드2를 입력해주세요.");
			t_password2.requestFocus();
			return;
		} else if (name.length() == 0) {
			JOptionPane.showMessageDialog(this, "이름을 입력해주세요.");
			t_name.requestFocus();
			return;
		}

	}

	public void passwordCheck() {
		getInfo();

		if (password.equals(password2)) {
			System.out.println("Client: RegistMember.regist.password=password2");
			flag = true;
		} else {
			JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");
			t_password2.requestFocus();
			flag = false;
		}
	}

	// 회원가입 요청
	public void regist() {
		getInfo();
		regist.Regist(id, password, name);

	}

	// id중복성 체크 요청
	public void checkId() {
		String id = t_id.getText();
		checkId.CheckId(id);
	}

	// 정보 구해오기
	public void getInfo() {
		id = t_id.getText();
		password = t_password.getText();
		password2 = t_password2.getText();
		name = t_name.getText();
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public JTextField getT_id() {
		return t_id;
	}

	public void setT_id(JTextField t_id) {
		this.t_id = t_id;
	}

	public JTextField getT_password() {
		return t_password;
	}

	public void setT_password(JTextField t_password) {
		this.t_password = t_password;
	}

	public JTextField getT_password2() {
		return t_password2;
	}

	public void setT_password2(JTextField t_password2) {
		this.t_password2 = t_password2;
	}

	public JTextField getT_name() {
		return t_name;
	}

	public void setT_name(JTextField t_name) {
		this.t_name = t_name;
	}

}
