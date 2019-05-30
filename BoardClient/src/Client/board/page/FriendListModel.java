package Client.board.page;

import javax.swing.table.AbstractTableModel;

public class FriendListModel extends AbstractTableModel {
	private FriendList friendList;
	private String[] columnName = new String[1];
	private String[][] data = new String[1][1];

	public FriendListModel(FriendList friendList) {
		this.friendList = friendList;
	}

	@Override
	public int getColumnCount() {

		return columnName.length;
	}

	@Override
	public int getRowCount() {

		return data.length;
	}

	@Override
	public String getColumnName(int col) {
		return columnName[col];
	}

	@Override
	public String getValueAt(int row, int col) {

		return data[row][col];
	}

	public FriendList getFriendList() {
		return friendList;
	}

	public void setFriendList(FriendList friendList) {
		this.friendList = friendList;
	}

	public String[] getColumnName() {
		return columnName;
	}

	public void setColumnName(String[] columnName) {
		this.columnName = columnName;
	}

	public String[][] getData() {
		return data;
	}

	public void setData(String[][] data) {
		this.data = data;
	}
	
	
	
	
}
