package server.board.model.domain;

public class Friend {
	private int friend_id;
	private int member_id;
	private int f_member_id;
	public int getFriend_id() {
		return friend_id;
	}
	public void setFriend_id(int friend_id) {
		this.friend_id = friend_id;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public int getF_member_id() {
		return f_member_id;
	}
	public void setF_member_id(int f_member_id) {
		this.f_member_id = f_member_id;
	}
	
	
}
