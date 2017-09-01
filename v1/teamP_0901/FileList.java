package teamP_0901;

import java.awt.Image;
import java.io.Serializable;

import javax.swing.ImageIcon;
//파일 완성
public class FileList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String title; //파일과 같이보낼 제목
	private String contents; //파일과 같이보낼 코멘트
	private String fileName; //파일의 이름. 
	private int fileSize; //파일사이즈
	private byte [] fileContents; //파일내용물
	private String userID;
	private String userPW;

	public FileList(String title, String contents, String fileName, int fileSize , byte[] fileContents,String userID,String userPW) {
		super();
		this.title = title;
		this.contents = contents;
		this.fileName = fileName;
		this.fileContents = fileContents;
		this.fileSize = fileSize;
		this.userID = userID;
		this.userPW = userPW;		
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public byte[] getFileContents() {
		return fileContents;
	}
	public void setFileContents(byte[] fileContents) {
		this.fileContents = fileContents;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPW() {
		return userPW;
	}
	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}
	
}
