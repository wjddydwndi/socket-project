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

public class BoardSet extends JPanel {
	private MainFrame mainFrame;

	JPanel p_title;
	JPanel p_center;
	JPanel p_bottom;

	// p_center
	private JTable table;
	private JScrollPane scroll;
	private BoardSetModel boardSetModel;

	// p_bottom
	JButton bt_del;
	JButton bt_edit;
	JButton bt_back;

	
	//�α��� ������ ����.
	private Member member=new Member();
	
	
	public BoardSet(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		p_title = new JPanel();

		p_center = new JPanel();
		p_bottom = new JPanel();
		boardSetModel = new BoardSetModel(this);
		table = new JTable();
		scroll = new JScrollPane(table);
		bt_del = new JButton("����");
		bt_edit = new JButton("����");
		bt_back = new JButton("�ڷΰ���");

		// ��ü �г� ������ / ����
		p_title.setPreferredSize(new Dimension(490, 80));
		p_center.setPreferredSize(new Dimension(490, 440));
		p_bottom.setPreferredSize(new Dimension(490, 70));

		// p_center

		scroll.setPreferredSize(new Dimension(480, 430));

		// p_bottom
		Dimension d = new Dimension(150, 50);
		bt_del.setPreferredSize(d);
		bt_edit.setPreferredSize(d);
		bt_back.setPreferredSize(d);

		// ����

		// ��ü �г� ����

		// p_center
		p_center.add(scroll);

		// p_bottom
		p_bottom.add(bt_del);
		p_bottom.add(bt_edit);
		p_bottom.add(bt_back);

		setLayout(new BorderLayout());
		add(p_title, BorderLayout.NORTH);
		add(p_center);
		add(p_bottom, BorderLayout.SOUTH);

		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(500, 600));
		setVisible(false);
		
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
