package Client.board.util;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


// DATA STREAM�� ������ ���� ���� GETTER/SETTER Ŭ����
public class ImgStreamDistributor {
	private static DataInputStream dis;
	private static DataOutputStream dos;
	
	
	public static DataInputStream getDis() {
		return dis;
	}
	public static void setDis(DataInputStream dis) {
		ImgStreamDistributor.dis = dis;
	}
	public static DataOutputStream getDos() {
		return dos;
	}
	public static void setDos(DataOutputStream dos) {
		ImgStreamDistributor.dos = dos;
	}



}
