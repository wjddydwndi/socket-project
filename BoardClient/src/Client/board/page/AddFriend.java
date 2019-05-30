package Client.board.page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import Client.board.main.MainFrame;
import Client.board.model.domain.Member;


public class AddFriend extends JPanel {
	private MainFrame mainFrame;

	JPanel p_title, p_center, p_south;

	// p_center
	JTable table;
	AddFriendModel addFriendModel;

	// p_south
	JPanel p_s_north, p_s_south;

	// p_s_north
	JButton bt_regist, bt_back;

	// p_s_south
	TextField t_input;
	JButton bt_search;
	
	//로그인 정보를 담음.
	private Member member=new Member();

	public AddFriend(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		p_title=new JPanel();
		p_center = new JPanel();
		p_south = new JPanel();
		addFriendModel = new AddFriendModel(this);
		table = new JTable();
		p_s_north = new JPanel();
		p_s_south = new JPanel();
		bt_regist = new JButton("친구추가");
		bt_back = new JButton("뒤로가기");
		t_input = new TextField();
		bt_search = new JButton("검색");

		// 전체 패널 크기 설정
		p_center.setPreferredSize(new Dimension(400, 350));
		p_south.setPreferredSize(new Dimension(400, 150));
		p_south.setBackground(Color.YELLOW);

		table.setPreferredSize(new Dimension(395, 345));

		p_s_north.setPreferredSize(new Dimension(400, 60));
		p_s_south.setPreferredSize(new Dimension(400, 90));
		bt_regist.setPreferredSize(new Dimension(100, 55));
		bt_back.setPreferredSize(new Dimension(100, 55));
		t_input.setPreferredSize(new Dimension(250, 30));
		t_input.setFont(new Font("돋움", Font.BOLD, 25));
		bt_search.setPreferredSize(new Dimension(80, 30));

		setLayout(new BorderLayout());

		p_center.add(table);

		p_s_north.add(bt_regist);
		p_s_north.add(bt_back);

		p_s_south.add(t_input);
		p_s_south.add(bt_search);

		p_south.setLayout(new BorderLayout());
		p_south.add(p_s_north, BorderLayout.NORTH);
		p_south.add(p_s_south);

	
		add(p_center);
		add(p_south, BorderLayout.SOUTH);

		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(400, 600));
		setVisible(false);
		
		bt_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.showPage(5);
			}
		});
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	
}
