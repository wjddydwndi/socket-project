package server.board.util;

import javax.swing.JTextArea;

//Area�� ������ ���� Ŭ����.
public class GetArea {
	private static JTextArea area;

	public static JTextArea getArea() {
		return area;
	}

	public static void setArea(JTextArea area) {
		System.out.println("GetArea // "+area);
		GetArea.area = area;
	}

}
