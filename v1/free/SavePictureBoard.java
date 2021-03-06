package free;

import java.io.File;
import java.io.Serializable;

public class SavePictureBoard implements Serializable{

	private File img=null;
	private String imaName=null;
	private String writer=null;
	private String title=null;
	private String comment=null;
	private String userId=null;
	private String userPw=null;
	
	public SavePictureBoard(File img, String imaName,String writer ,String title, String comment, String userId, String userPw) {
		super();
		this.img = img;
		this.imaName = imaName;
		this.writer = writer;
		this.title = title;
		this.comment = comment;
		this.userId = userId;
		this.userPw = userPw;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImaName() {
		return imaName;
	}

	public void setImaName(String imaName) {
		this.imaName = imaName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	
}
