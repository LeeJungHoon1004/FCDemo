package teamP_0901;

public class VideoFileList {
	private String url;
	private String filename;//파일의 이름. 
	private int fileSize;//파일사이즈
	private byte[] filecontents;//파일내용물
	
	public VideoFileList(String url, String filename, int fileSize, byte[] filecontents) {
		super();
		this.url = url;
		this.filename = filename;
		this.fileSize = fileSize;
		this.filecontents = filecontents;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public byte[] getFilecontents() {
		return filecontents;
	}

	public void setFilecontents(byte[] filecontents) {
		this.filecontents = filecontents;
	}
	
	
	
}
