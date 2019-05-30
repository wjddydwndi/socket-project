package Client.board.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Client.board.main.MainFrame;
import Client.board.page.BoardMain;
import Client.board.requestType.RequestLogin;

public class Login extends JPanel {
	MainFrame mainFrame;

	JPanel p_wrapper;

	JPanel p_top;
	JPanel p_center;
	JPanel p_bottom;

	// p_center
	JPanel p_center_top;
	JPanel p_center_bottom;

	// p_center_top
	JPanel p_west;
	JPanel p_east;
	JPanel p_contents;

	// p_center_bottom
	// p_side
	JPanel p_side;
	// p_login
	JPanel p_login;
	JLabel la_id;
	JTextField t_id;
	JLabel la_pw;
	JPasswordField t_pass;

	// p_bt
	JPanel p_bt;
	JButton bt_login;

	// p_bottom
	JButton bt_regist;

	//Login Request
	private RequestLogin requestLogin;
	
	public Login(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		requestLogin = new RequestLogin(this);
		
		
		p_wrapper = new JPanel();
		p_top = new JPanel();
		p_center = new JPanel();

		p_center_top = new JPanel();
		p_center_bottom = new JPanel();
		p_west = new JPanel();
		p_east = new JPanel();
		p_contents = new JPanel();
		t_id = new JTextField();
		t_pass = new JPasswordField();
		p_bottom = new JPanel();
		p_login = new JPanel();
		la_id = new JLabel("ID");
		la_pw = new JLabel("PW");
		p_bt = new JPanel();
		bt_login = new JButton("로그인");
		bt_regist = new JButton("회원가입");
		p_side = new JPanel();

		// wrapper size& color
		p_wrapper.setPreferredSize(new Dimension(450, 550));
		p_wrapper.setBackground(Color.WHITE);
		p_top.setPreferredSize(new Dimension(450, 100));
		p_top.setBackground(Color.WHITE);
		p_center.setPreferredSize(new Dimension(450, 350));
		// p_center.setBackground(Color.WHITE);
		p_bottom.setPreferredSize(new Dimension(450, 70));
		p_bottom.setBackground(Color.WHITE);

		// p_conter size&color
		p_center_top.setPreferredSize(new Dimension(450, 250));
		p_center_top.setBackground(Color.WHITE);
		p_center_bottom.setPreferredSize(new Dimension(450, 100));
		p_center_bottom.setBackground(Color.WHITE);

		// p_center_bottom
		p_side.setPreferredSize(new Dimension(20, 100));
		p_login.setPreferredSize(new Dimension(300, 100));
		p_bt.setPreferredSize(new Dimension(80, 70));

		p_side.setBackground(Color.WHITE);
		p_login.setBackground(Color.WHITE);
		p_bt.setBackground(Color.WHITE);

		// p_center_top
		p_contents.setPreferredSize(new Dimension(330, 200));
		p_contents.setBackground(Color.WHITE);
		p_west.setPreferredSize(new Dimension(30, 200));
		p_east.setPreferredSize(new Dimension(30, 200));

		// p_login
		la_id.setPreferredSize(new Dimension(80, 30));
		t_id.setPreferredSize(new Dimension(200, 30));
		la_pw.setPreferredSize(new Dimension(80, 30));
		t_pass.setPreferredSize(new Dimension(200, 30));

		// p_bt
		bt_login.setPreferredSize(new Dimension(80, 65));

		// p_bottom
		bt_regist.setPreferredSize(new Dimension(200, 50));
		// 패널 부착
		// p_login
		p_login.add(la_id, BorderLayout.NORTH);
		p_login.add(t_id, BorderLayout.NORTH);
		p_login.add(la_pw);
		p_login.add(t_pass);

		// p_bt
		p_bt.add(bt_login);

		// p_center_top
		p_center_top.setLayout(new BorderLayout());
		p_center_top.add(p_west, BorderLayout.WEST);
		p_center_top.add(p_contents);
		p_center_top.add(p_east, BorderLayout.EAST);

		// p_center_bottom
		p_center_bottom.setLayout(new BorderLayout());
		p_center_bottom.add(p_side, BorderLayout.WEST);
		p_center_bottom.add(p_login);
		p_center_bottom.add(p_bt, BorderLayout.EAST);

		// p_center
		p_center.setLayout(new BorderLayout());
		p_center.add(p_center_top, BorderLayout.NORTH);
		p_center.add(p_center_bottom);

		// p_bottom
		p_bottom.add(bt_regist);

		// 전체패널
		p_wrapper.setLayout(new BorderLayout());
		p_wrapper.add(p_top, BorderLayout.NORTH);
		p_wrapper.add(p_center);
		p_wrapper.add(p_bottom, BorderLayout.SOUTH);

		add(p_wrapper);

		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(500, 600));
		setVisible(false);
		
		bt_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=t_id.getText();
				/* String pass=(t_pass.getPassword()).toString(); */
				String pass=t_pass.getText();
				System.out.println("Login / bt_login / pass=="+pass);
				
				
				requestLogin.login(id, pass);
				
				
				mainFrame.showPage(2);
				BoardMain boardMain = (BoardMain)mainFrame.getPage(2);
				
				boardMain.selectAll();
				System.out.println("CHECK =================boardMain="+ boardMain);
				
				System.out.println("CHECK ================="+ boardMain.getContentList().size());
			}
		});
		bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.showPage(1);
			}
		});
	
	
	}
	



	

	
}
