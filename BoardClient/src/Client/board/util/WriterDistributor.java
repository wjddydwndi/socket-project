package Client.board.util;

import java.io.BufferedWriter;
import java.io.IOException;

public class WriterDistributor {
	private static BufferedWriter buffw;
	private static BufferedWriter buffwi;

	public static BufferedWriter getBuffw() {
		return buffw;
	}

	public static void setBuffw(BufferedWriter buffw) {
		WriterDistributor.buffw = buffw;
	}

	public static BufferedWriter getBuffwi() {
		return buffwi;
	}

	public static void setBuffwi(BufferedWriter buffwi) {
		WriterDistributor.buffwi = buffwi;
	}

	public void close(BufferedWriter buffw) {
		if (buffw != null) {
			try {
				buffw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
