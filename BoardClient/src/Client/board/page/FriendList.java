package Client.board.page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Client.board.main.MainFrame;
import Client.board.model.domain.Member;

public class FriendList extends JPanel {
	private MainFrame mainFrame;

	JPanel p_title;
	JPanel p_center;
	JPanel p_bottom;

	// p_center
	private JTable table;
	private FriendListModel friendListModel;
	JScrollPane scroll;

	// p_bottom
	JButton bt_search, bt_del, bt_back;

	//로그인 정보를 담음.
	private Member member=new Member();
	
	public FriendList(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		p_title = new JPanel();

		p_center = new JPanel();
		p_bottom = new JPanel();
		friendListModel = new FriendListModel(this);
		table = new JTable();
		scroll = new JScrollPane(table);
		bt_search = new JButton("친구검색");
		bt_del = new JButton("친구삭제");
		bt_back = new JButton("뒤로가기");

		// 전체 패널 사이즈 & 색상
		p_title.setPreferredSize(new Dimension(490, 80));
		p_center.setPreferredSize(new Dimension(490, 445));
		p_bottom.setPreferredSize(new Dimension(490, 70));

		// p_center
		scroll.setPreferredSize(new Dimension(490, 445));

		// p_bottom
		Dimension d = new Dimension(150, 50);
		bt_search.setPreferredSize(d);
		bt_del.setPreferredSize(d);
		bt_back.setPreferredSize(d);

		// 부착
		// p_center
		p_center.add(scroll);

		// p_bottom
		p_bottom.add(bt_search);
		p_bottom.add(bt_del);
		p_bottom.add(bt_back);
		// 전체 패널 부착
		setLayout(new BorderLayout());
		add(p_title, BorderLayout.NORTH);
		add(p_center);
		add(p_bottom, BorderLayout.SOUTH);

		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(500, 600));
		setVisible(false);
		
		bt_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.showPage(6);
			}
		});
		bt_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.showPage(2);
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
