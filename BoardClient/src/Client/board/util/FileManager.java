package Client.board.util;

import java.io.File;

public class FileManager {
	
	public String getExt(String path) {
		int index= path.lastIndexOf(".");
		String ext = path.substring(index+1, path.length());
		return ext;
	}
	
	public String getFilename(String path) {
		System.out.println("FileManager / 인수로 받은 path =="+path);
		String filename1 = ((Integer)(int)System.currentTimeMillis()).toString();
		String ext = getExt(path);
		String filename = filename1+"."+ext;
		System.out.println("FileManager / filename=="+filename);
		
		return filename;
	}
}
