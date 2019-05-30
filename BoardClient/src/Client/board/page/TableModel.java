package Client.board.page;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
	Content content;
	Object[][] data = new Object[1][1];
	String[] columnName = new String[1];

	public TableModel(Content content) {
		this.content = content;
	}

	public int getColumnCount() {
		return columnName.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public String getColumnName(int col) {
		return columnName[col];
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

}
