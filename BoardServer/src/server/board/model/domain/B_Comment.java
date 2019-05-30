package server.board.model.domain;

public class B_Comment {
	private int b_comment_id;
	private int board_id;
	private Member member;
	private String content;
	private String credate;
	public int getB_comment_id() {
		return b_comment_id;
	}
	public void setB_comment_id(int b_comment_id) {
		this.b_comment_id = b_comment_id;
	}
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCredate() {
		return credate;
	}
	public void setCredate(String credate) {
		this.credate = credate;
	}
	
	
}
