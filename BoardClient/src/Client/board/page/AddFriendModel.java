package Client.board.page;

import javax.swing.table.AbstractTableModel;

public class AddFriendModel extends AbstractTableModel{
	AddFriend addFriend;
	String[] columnName= new String[3];
	String[][] data= new String[0][3];
	
public AddFriendModel(AddFriend addFriend) {
	this.addFriend = addFriend;

}

	public int getColumnCount() {
	
		return columnName.length;
	}

	public int getRowCount() {

		return data.length;
	}
	public String getColumnName(int col) {
	
		return  columnName[col];
	}
	public String getValueAt(int row, int col) {

		return data[row][col];
	}

}
