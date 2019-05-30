package Client.board.main;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Client.board.connect.ClientConnector;
import Client.board.login.Login;
import Client.board.model.domain.Member;
import Client.board.page.AddFriend;
import Client.board.page.BoardMain;
import Client.board.page.BoardSet;
import Client.board.page.BoardWrite;
import Client.board.page.FriendList;
import Client.board.registMember.RegistMember;
import Client.board.util.GetMain;

public class MainFrame extends JFrame {

	JPanel p_wrapper;
	JPanel[] pages = new JPanel[7];

	private Login login;
	private RegistMember registMember;
	private BoardMain boardMain;
	private BoardWrite boardWrite;
	private BoardSet boardSet;
	private FriendList friendList;
	private AddFriend addFriend;

	private ClientConnector connector;
	private Member member;

	public MainFrame() {
		GetMain.setMainFrame(this);
		connector = new ClientConnector(this);

		member = new Member();// 로그인 후에 로그인 정보를 담아놓음.

	
		p_wrapper = new JPanel();

		// 페이지가 될 클래스들 생성.
		login = new Login(this);
		registMember = new RegistMember(this);
		boardMain = new BoardMain(this);
		boardWrite = new BoardWrite(this);
		boardSet = new BoardSet(this);
		friendList = new FriendList(this);
		addFriend = new AddFriend(this);

		// 패널 배열에 주입.
		setPanel();

		// size
		p_wrapper.setPreferredSize(new Dimension(500, 600));

		// 패널 부착
		add(p_wrapper);

		for (int i = 0; i < pages.length; i++) {
			p_wrapper.add(pages[i]);
		}

		showPage(0);
		setSize(500, 660);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	// 패널 배열에 각 페이지들 주입.
	public void setPanel() {
		pages[2] = boardMain;
		pages[0] = login;
		pages[1] = registMember;
		pages[3] = boardWrite;
		pages[4] = boardSet;
		pages[5] = friendList;
		pages[6] = addFriend;

	}

	// 각 페이지에 로그인 정보를 주입.
	public void distributeMemberInfo(Member member) {

		// 각 페이지에 맴버 정보 주입.
		if (member.getMember_id() != 0) {

			boardMain.setMember(member);
			boardWrite.setMember(member);
			boardSet.setMember(member);
			friendList.setMember(member);
			addFriend.setMember(member);
			System.out.println("/BoardMain / Login성공 / Member 정보 /" + member.getMember_id());
			System.out.println("/BoardMain / Login성공 / Member 정보 /" + member.getId());
			System.out.println("/BoardMain / Login성공 / Member 정보 /" + member.getName());

			System.out.println("/BoardMain / distributeMemberInfo / 각 페이지에 맴버 정보 주입 완료.");
		}else {
			showPage(0);
			
		}

	}

	// 페이지 오픈
	public void showPage(int page) {
		for (int i = 0; i < pages.length; i++) {
			if (i == page) {
				pages[i].setVisible(true);
			} else {
				pages[i].setVisible(false);
			}
		}
	}

	// 페이지의 정보를 얻어온다.	
	public JPanel getPage(int page) {
		return pages[page];
		
	}

	public ClientConnector getConnector() {
		return connector;
	}

	public void setConnector(ClientConnector connector) {
		this.connector = connector;
	}

	public static void main(String[] args) {
		new MainFrame();
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public RegistMember getRegistMember() {
		return registMember;
	}

	public void setRegistMember(RegistMember registMember) {
		this.registMember = registMember;
	}

	public BoardMain getBoardMain() {
		return boardMain;
	}

	public void setBoardMain(BoardMain boardMain) {
		this.boardMain = boardMain;
	}

	public BoardWrite getBoardWrite() {
		return boardWrite;
	}

	public void setBoardWrite(BoardWrite boardWrite) {
		this.boardWrite = boardWrite;
	}

	public BoardSet getBoardSet() {
		return boardSet;
	}

	public void setBoardSet(BoardSet boardSet) {
		this.boardSet = boardSet;
	}

	public FriendList getFriendList() {
		return friendList;
	}

	public void setFriendList(FriendList friendList) {
		this.friendList = friendList;
	}

	public AddFriend getAddFriend() {
		return addFriend;
	}

	public void setAddFriend(AddFriend addFriend) {
		this.addFriend = addFriend;
	}

}
