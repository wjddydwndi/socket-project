package Client.board.page;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Client.board.main.MainFrame;
import Client.board.model.domain.Board;
import Client.board.model.domain.Member;
import Client.board.requestType.RequestSelectAll;

public class BoardMain extends JPanel {
	private MainFrame mainFrame;

	private List contentList;
	// �α��� ������ ����.
	private Member member = new Member();

	JPanel wrapper;

	JPanel p_wrapper;
	JScrollPane scroll;

	JPanel p_title;

	JPanel p_content;

	JPanel p_bottom;
	JButton bt_write;
	JButton bt_setting;
	JButton bt_friend;

	int n;

	private RequestSelectAll requestSelectAll;

	private List list = new ArrayList();

	public BoardMain(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		contentList = new ArrayList();

		BoardWrite boardWrite = (BoardWrite) mainFrame.getPage(3);
		requestSelectAll = new RequestSelectAll(this);
		wrapper = new JPanel();

		p_wrapper = new JPanel();
		p_title = new JPanel();
		p_content = new JPanel();

		scroll = new JScrollPane(p_content);

		p_bottom = new JPanel();
		bt_write = new JButton("�۾���");
		bt_setting = new JButton("�Խù�����");
		bt_friend = new JButton("ģ������");

		wrapper.setPreferredSize(new Dimension(500, 600));

		// wrapper ������
		p_wrapper.setPreferredSize(new Dimension(480, 470));
		scroll.setPreferredSize(new Dimension(480, 470));
		p_bottom.setPreferredSize(new Dimension(500, 60));

		// p_bottom ��ư ������ ����,
		bt_write.setPreferredSize(new Dimension(140, 50));
		bt_setting.setPreferredSize(new Dimension(140, 50));
		bt_friend.setPreferredSize(new Dimension(140, 50));

		// =======================================

		bt_write.setFont(new Font("����", Font.BOLD, 15));
		bt_setting.setFont(new Font("����", Font.BOLD, 15));
		bt_friend.setFont(new Font("����", Font.BOLD, 15));

		// p_bottom
		// ============================================

		// ============================================
		p_bottom.add(bt_write);
		p_bottom.add(bt_setting);
		p_bottom.add(bt_friend);

		// ===========================================

		// ū Ʋ ����
		wrapper.setLayout(new BorderLayout());

		p_content.setLayout(new GridLayout(n, 1));
		p_wrapper.add(scroll);

		wrapper.add(p_wrapper);
		wrapper.add(p_bottom, BorderLayout.SOUTH);
		add(wrapper);

		bt_write.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.showPage(3);
			}
		});

		bt_setting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.showPage(4);
			}
		});

		bt_friend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.showPage(5);
			}
		});

		updateUI();
		repaint();
		setPreferredSize(new Dimension(500, 600));
		setVisible(true);
		System.out.println("BoardMain �ʱ�ȭ �Ϸ�");

	}

	public void selectAll() {

		requestSelectAll.selectAll();

	}

	public void getBoardList() {

		if (list != null) {
		
					Component[] components= p_content.getComponents();
					p_content.removeAll();
					contentList.removeAll(contentList);
		
			

			for (int i = 0; i < list.size(); i++) {

				System.out.println(i + " ��° ��� ������ �ҷ��Խ��ϴ�.");
				Board board = (Board) list.get(i);

				Content content = new Content(this, board, i);
				System.out.println("BoardMain=====contentList.size()//" + contentList.size());
				content.selectAllComment();
				p_content.add(content);
				p_content.repaint();

			}
		}
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public List getContentList() {
		return contentList;
	}

	public void setContentList(List contentList) {
		this.contentList = contentList;
	}

}
