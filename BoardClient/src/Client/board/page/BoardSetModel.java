package Client.board.page;

import javax.swing.table.AbstractTableModel;

public class BoardSetModel extends AbstractTableModel {
	private BoardSet setBoard;
	private String[] columnName = new String[1];
	private Object[][] data =new  Object[1][1];

	public BoardSetModel(BoardSet setBoard) {
		this.setBoard = setBoard;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnName.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public String getColumnName(int col) {

		return columnName[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		return data[row][col];
	}

	public BoardSet getSetBoard() {
		return setBoard;
	}

	public void setSetBoard(BoardSet setBoard) {
		this.setBoard = setBoard;
	}

	public Object[] getColumnName() {
		return columnName;
	}

	public void setColumnName(String[] columnName) {
		this.columnName = columnName;
	}

	public Object[][] getData() {
		return data;
	}

	public void setData(Object[][] data) {
		this.data = data;
	}

}
