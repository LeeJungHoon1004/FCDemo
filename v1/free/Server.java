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
import java.net.ServerSocket;
import java.net.Socket;

import File.FileList;

class ConnectionThread extends Thread {
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

	public ConnectionThread(Socket socket) {
		try {
			this.socket = socket;
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			System.out.println("서버:소켓초기화에 실패했다");
		}
	}

	public void run() {

		System.out.println(socket.getInetAddress() + "님이 접속했습니다");
		try {
			
		

			

				String cmd = dis.readUTF();

				if (cmd.equals("소켓연결후데이터수신")) {
					// 전송하려는 파일의 이름 , 크기 , 내용물(파일자체) , 파일의 타이틀 ,파일의 내용
					String title = null;
					String contents = null;
					String fileName = null;
					int fileSize = 0;
					byte[] fileContents = null;
					
					//2.클라이언트에서 데이터를 받습니다 (2.ClientRam to ServerRam)
					ois = new ObjectInputStream(socket.getInputStream());
					FileList fl1 = (FileList) ois.readObject();
					FileList fl2 = (FileList) ois.readObject();
//					FileList fl3 = (FileList) ois.readObject();
					
					
					
					
					System.out.println(fl1.getTitle()); //제목
					System.out.println(fl1.getContents());//코멘트
					System.out.println(fl1.getFileName());//파일이름
					System.out.println(fl1.getFileSize());//파일의 크기(int)
					System.out.println(fl1.getFileContents());//파일의 내용물(byte [])
					
					
					System.out.println(fl2.getTitle());
					System.out.println(fl2.getContents());
					System.out.println(fl2.getFileName());
					System.out.println(fl2.getFileSize());
					System.out.println(fl2.getFileContents());
					
					
//					System.out.println(fl3.getTitle());
//					System.out.println(fl3.getContents());
//					System.out.println(fl3.getFileName());
//					System.out.println(fl3.getFileSize());
//					System.out.println(fl3.getFileContents());
					
					//3.클라이언트에서 받은 데이터를 경로에 저장합니다 (3.Ram to Hdd)
					fileContents = fl1.getFileContents();
					File f = new File("L:/김현수/서버/" + fl1.getFileName());
					fos = new FileOutputStream(f);
					bos = new BufferedOutputStream(fos);
					dos = new DataOutputStream(bos);
					dos.write(fileContents);
					dos.flush();
					
					fileContents = fl2.getFileContents();
					File f2 = new File("L:/김현수/서버/" + fl2.getFileName());
					fos = new FileOutputStream(f2);
					dos = new DataOutputStream(fos);
					dos.write(fileContents);
					dos.flush();
					
//					fileContents = fl3.getFileContents();
//					File f3 = new File("E:/프로그래밍/Java언어/자바기반웹프로그래머양성_7월/Server/" + fl3.getFileName());
//					fos = new FileOutputStream(f3);
//					dos = new DataOutputStream(fos);
//					dos.write(fileContents);
//					dos.flush();
					
					System.out.println("클라이언트에서 받은 데이터를 하드디스크로 저장완료.");
					
					dos.close();
					
					
				}
				
//				else if() {
//					
//				}
				
			 

			}
		catch (Exception e) {
			System.out.println("Server에서 파일전송중 에러발생.");
			e.printStackTrace();
		
		} // - while
	}
}

public class Server {
	public static void main(String[] args) throws Exception {

		ServerSocket server = new ServerSocket(40000);
		new Server();
		while (true) {
			new ConnectionThread(server.accept()).start();
		}

	}
}
