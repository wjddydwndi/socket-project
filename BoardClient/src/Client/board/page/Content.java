package Client.board.page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import Client.board.model.domain.B_Comment;
import Client.board.model.domain.Board;
import Client.board.requestType.RequestInsertComment;
import Client.board.requestType.RequestSelectAllComment;

public class Content extends JPanel {
	private static final Object Content = null;
	private BoardMain boardMain;
	private Board board;
	private int num;
	
	private JPanel p_time;// 날짜 들어갈 패널
	private JLabel la_time;
	private JLabel la_writer;// 작성자

	private JPanel p_image;// 게시물 사진

	// 게시물 내용
	private JTextArea area;
	private JLabel content;
	private JScrollPane scroll;

	private JPanel p_hit;// 좋아요 버튼
	private JButton bt_hit;// 좋아요 버튼
	private JLabel la_hit;// 좋아요 개수 카운팅.

	// 댓글
	private JTextField t_table;
	private JButton bt_table;
	private JPanel p_table;
	private JTable table; 
	private TableModel tableModel;
	private JScrollPane m_scroll;
	private int selected_id;
	
	//해당 글의 전체 댓글
	List commentList = new ArrayList();
	
	//Requst
	private RequestInsertComment requestInsertComment;
	private RequestSelectAllComment requestSelectAllComment;
	
	public Content(BoardMain boardMain, Board board,int num) {
		this.boardMain = boardMain;
		this.board = board;
		this.num = num;
		
	
		
		p_time = new JPanel();
		la_time = new JLabel();
		la_writer = new JLabel();
		p_image = new JPanel();

		content = new JLabel();
		scroll = new JScrollPane(content);

		p_hit = new JPanel();
		bt_hit = new JButton("♡ 좋아요");
		la_hit = new JLabel();// 좋아요 개수
		
		t_table = new JTextField();
		bt_table = new JButton("ok");
		p_table = new JPanel();
		table = new JTable();
		tableModel = new TableModel(this);
		m_scroll = new JScrollPane(table);

		//request
		requestInsertComment = new RequestInsertComment(this);
		requestSelectAllComment = new RequestSelectAllComment(this);
		
		
		// Size
		p_time.setPreferredSize(new Dimension(350, 50));
		la_time.setPreferredSize(new Dimension(150, 40));
		la_writer.setPreferredSize(new Dimension(150, 40));

		p_image.setPreferredSize(new Dimension(300, 300));
		p_image.setBackground(Color.RED);

		scroll.setPreferredSize(new Dimension(300, 80));

		p_hit.setPreferredSize(new Dimension(300, 25));
		bt_hit.setPreferredSize(new Dimension(100, 25));
		la_hit.setPreferredSize(new Dimension(100, 25));

		
		t_table.setPreferredSize(new Dimension(250, 20));
		bt_table.setPreferredSize(new Dimension(50, 20));
		p_table.setPreferredSize(new Dimension(350, 90));
		p_table.setBackground(Color.BLUE);
		m_scroll.setPreferredSize(new Dimension(350, 80));

		// 부착!!
		p_time.setLayout(new BorderLayout());
		p_time.add(la_time, BorderLayout.WEST);
		p_time.add(la_writer, BorderLayout.EAST);
		add(p_time);

		add(p_image);

		add(scroll);

		p_hit.setLayout(new BorderLayout());
		p_hit.add(bt_hit, BorderLayout.WEST);
		p_hit.add(la_hit);
		add(p_hit);
		add(t_table);
		add(bt_table);
		p_table.add(m_scroll);
		add(p_table);
		p_table.repaint();

		//댓글 한건 등록
		bt_table.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				insertComment();
			}
		});
		
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int col = 0;

				int select = (int) table.getValueAt(row, col);

				//////////////////////////////////// 처리할 것.!!!/////////
				selected_id = (int) table.getValueAt(row, 3);
//////////////////////////////////처리할 것.!!!/////////

			}

		});

		// ***** 내용 주입 *********
		la_time.setText(editTime(board.getCredate()));
		la_writer.setText("작성자 : " + board.getMember().getName());
		content.setText(board.getContent());
		///////////////////////////////////////////////////////
		List list = boardMain.getContentList();
		list.add(this);
		System.out.println("Content///// contentList.size===="+list.size());
		setPreferredSize(new Dimension(350, 600));
		setVisible(true);
	}

	public void insertComment() {
		String comment = t_table.getText();
		int board_id = board.getBoard_id();
		int member_id=boardMain.getMember().getMember_id();
		String result =requestInsertComment.insertComment(comment,board_id,member_id);
		System.out.println("Client : Content / insertComment / 댓글 등록 /"+result);
	}
	
	
	
	public void selectAllComment() {
		int board_id=board.getBoard_id();
		requestSelectAllComment.selectAllComment(board_id,num);
		
	}
	
	
	
	public void getComment() {
		
		Object[][] data = new Object[commentList.size()][4];
		String[] columnName = new String[4];
		
		columnName[0]="작성자";
		columnName[1]="내용";
		columnName[2]="작성일";
		columnName[3]="고유키";
		
		
		for(int i = 0; i<commentList.size(); i++) {
			B_Comment b_Comment = (B_Comment)commentList.get(i);
			int b_Comment_id=b_Comment.getB_comment_id();
			String content = b_Comment.getContent();
			String credate = b_Comment.getCredate();
			String id = b_Comment.getMember().getId();
			
		
			
			data[i][0]=id;
			data[i][1]=content;
			data[i][2]=credate;
			data[i][3]=b_Comment_id;
		}

		tableModel.columnName=columnName;
		tableModel.data=data;
		table.setModel(tableModel);
		tableResize();
		table.updateUI();
		
	}
	
	public void tableResize() {
		// table size
		table.setRowHeight(50);
		DefaultTableCellRenderer cellCenter = new DefaultTableCellRenderer();
		cellCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer cellRight = new DefaultTableCellRenderer();
		cellRight.setHorizontalAlignment(JLabel.RIGHT);

		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(210);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(10);
	

		table.getColumnModel().getColumn(0).setCellRenderer(cellCenter);
		table.getColumnModel().getColumn(1).setCellRenderer(cellCenter);
		table.getColumnModel().getColumn(2).setCellRenderer(cellCenter);
		table.getColumnModel().getColumn(3).setCellRenderer(cellCenter);
		
		table.updateUI();
		
	}

	public String editTime(String time) {
		int index = time.lastIndexOf(".");
		return time.substring(0, index);
	}

	
	
	//************GETTER   SETTER *************
	public BoardMain getBoardMain() {
		return boardMain;
	}

	public void setBoardMain(BoardMain boardMain) {
		this.boardMain = boardMain;
	}

	public JPanel getP_time() {
		return p_time;
	}

	public void setP_time(JPanel p_time) {
		this.p_time = p_time;
	}

	public JLabel getLa_time() {
		return la_time;
	}

	public void setLa_time(JLabel la_time) {
		this.la_time = la_time;
	}

	public JPanel getP_image() {
		return p_image;
	}

	public void setP_image(JPanel p_image) {
		this.p_image = p_image;
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

	public JPanel getP_hit() {
		return p_hit;
	}

	public void setP_hit(JPanel p_hit) {
		this.p_hit = p_hit;
	}

	public JButton getBt_hit() {
		return bt_hit;
	}

	public void setBt_hit(JButton bt_hit) {
		this.bt_hit = bt_hit;
	}

	public JLabel getLa_hit() {
		return la_hit;
	}

	public void setLa_hit(JLabel la_hit) {
		this.la_hit = la_hit;
	}

	public JLabel getLa_writer() {
		return la_writer;
	}

	public void setLa_writer(JLabel la_writer) {
		this.la_writer = la_writer;
	}

	public JLabel getContent() {
		return content;
	}

	public void setContent(JLabel content) {
		this.content = content;
	}


	public TableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(TableModel tableModel) {
		this.tableModel = tableModel;
	}

	public JScrollPane getM_scroll() {
		return m_scroll;
	}

	public void setM_scroll(JScrollPane m_scroll) {
		this.m_scroll = m_scroll;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public int getSelected_id() {
		return selected_id;
	}

	public void setSelected_id(int selected_id) {
		this.selected_id = selected_id;
	}

	public List getCommentList() {
		return commentList;
	}

	public void setCommentList(List commentList) {
		this.commentList = commentList;
	}
	

}
