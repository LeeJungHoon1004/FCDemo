package free;

import java.io.File;

public class Instances {
	private File img;
	private String imaName;
	private String title;
	private String comment;
	private String userId;
	private String userPw;
	
	public Instances(File img, String imaName, String title, String comment, String userId, String userPw) {
		super();
		this.img = img;
		this.imaName = imaName;
		this.title = title;
		this.comment = comment;
		this.userId = userId;
		this.userPw = userPw;
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
