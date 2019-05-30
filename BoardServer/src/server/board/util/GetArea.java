package server.board.util;

import javax.swing.JTextArea;

//Area를 얻어오기 위한 클래스.
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
