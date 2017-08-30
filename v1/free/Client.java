package free;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import File.FileList;

public class Client {
	
	
	//소켓변수
	private Socket socket = null;
	//아웃 스트림
	private FileOutputStream fos = null;
	private BufferedOutputStream bos = null;
	private ObjectOutputStream oos = null;
	private DataOutputStream dos = null;
	//인풋스트림
	private FileInputStream fis = null;
	private BufferedInputStream bis = null;
	private DataInputStream dis = null;
	private ObjectInputStream ois = null;

	public void func() {

		try {
			System.out.println("클라이언트 소켓연결");
			socket = new Socket("127.0.0.1", 40000);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			dos.writeUTF("소켓연결후데이터수신");
			System.out.println("소켓연결후데이터수신요청성공");
			//DataOutputstream 개방후 파일만 우선 전송.
			
			
			
			
			
			//1.ObjectOutputStream 개방후 사용자정의형데이터 fileList 로 전송.
			String title = null;
			String contents = null;
			String fileName = null;
			int fileSize = 0;
			byte[] fileContents = null;
			File home = new File("L:/김현수/클라이언트");
			File[] files = home.listFiles();
			for(File tmp : files) {
				System.out.println(tmp.getAbsolutePath() + " : "+ tmp.length());
				System.out.println(tmp.getAbsolutePath() + " : " + tmp.getName());
			}
			
			//실습시참고.
			// 여기 fileName,title,contents는 클라이언트쪽 컴포넌트에서 값을 가져와서 넣어줘야된당.
			//fileName 값을 클라이언트 에서 가져와서 입력한다 fileName = Component.gettext()..
			//title 값을 클라이언트 에서 가져와서 입력한다	title = Component.gettext()..
			//contents 값을 클라이언트 에서 가져와서 입력한다. contents = Component.gettext()..
			
			fileName = "클라이언트1.txt";
			File targetFile = new File(home.getPath() + "/" + fileName);
			//	System.out.println(home.getPath()+"/"+fileName);
			
				title = "클라이언트1.txt";
				contents = "클라이언트1.txt";
				fileName = targetFile.getName();
				fileSize = (int)targetFile.length();
				fileContents = new byte[fileSize];
				
				//파일컨텐츠에 실제 파일을 담아준다.
				fis = new FileInputStream(targetFile);
				fis.read(fileContents);
				fis.close();
				
				//	System.out.println("1번째 파일 :" + fileName + "의 파일 사이즈 :" +fileSize + " : " + "의 파일 내용물 :" + fileContents );
			oos = new ObjectOutputStream(socket.getOutputStream());
			//파일a제목 , 파일a내용 , 타겟팅한 파일의 이름 , 파일크기 , 파일을 바이트배열로 담아서 내용묶음
			FileList fl1 = new FileList(title , contents , fileName , fileSize , fileContents);
			oos.writeObject(fl1);
			
			//===========================================================
			
			fileName = "d1.JPG";
			targetFile = new File(home.getPath() + "/" + fileName);
				
				title = "d1.JPG";
				contents ="d1.JPG내용";
				fileName = targetFile.getName();
				fileSize = (int)targetFile.length();
				fileContents = new byte[fileSize];
				
				//파일컨텐츠에 실제 파일을 담아준다.
				fis = new FileInputStream(targetFile);
				fis.read(fileContents);
				fis.close();
			
			FileList fl2 =  new FileList(title , contents , fileName , fileSize , fileContents);
		
			oos.writeObject(fl2);
			
			//=============================================================
			
//			fileName = "a.txt";
//			targetFile = new File(home.getPath() + "/" + fileName);
//				
//				title = "a의제목";
//				contents ="a의내용";
//				fileName = targetFile.getName();
//				fileSize = (int)targetFile.length();
//				fileContents = new byte[fileSize];
//			
//			FileList fl3 =  new FileList(title , contents , fileName , fileSize , fileContents);
//			oos.writeObject(fl3);
			
			
			
			
			System.out.println("직렬화후 오브젝트 전송 성공");
			
			oos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("클라이언트 전송중 에러발생.");
		}

	}

	public Client() {
		this.func();

	}

	public static void main(String[] args) throws Exception {
		System.out.println("클라이언트생성");
		new Client();
	}

}
