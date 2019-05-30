package Client.board.util;

import Client.board.main.MainFrame;

public class GetMain {
	private 	static MainFrame mainFrame;

	public static MainFrame getMainFrame() {
		return mainFrame;
	}

	public static void setMainFrame(MainFrame mainFrame) {
		GetMain.mainFrame = mainFrame;
	}
	

}
