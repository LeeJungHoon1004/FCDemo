package free;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


//오브젝트 스트림 = 객체직렬화 / 객체직렬화 소켓통신
//<a target="_blank" href="http://nicebury.tistory.com/15" class="tx-link">http://nicebury.tistory.com/15</a>;

public class AddPictureBoard extends JDialog {

	private Socket client;
	private FileOutputStream fos = null;
	private BufferedOutputStream bos = null;
	private ObjectOutputStream oos = null;
	private DataOutputStream dos = null;
	//인풋스트림
	private FileInputStream fis = null;
	private BufferedInputStream bis = null;
	private DataInputStream dis = null;
	private ObjectInputStream ois = null;

	private AddPictureBoard self = this;
	private TitledBorder tborder = new TitledBorder("");
	// ======FILE CHOOSER=======================
	private JFileChooser fc = new JFileChooser();
	private File img;
	private ArrayList<SavePictureBoard> spb = new ArrayList<SavePictureBoard>();

	private ImageIcon imgIcon;
	private JLabel picture = new JLabel(imgIcon);
	private JButton findPicture = new JButton("사진");
	private JTextField picturePath = new JTextField();
	private JTextField comment = new JTextField();
	private JTextField title = new JTextField();

	private JPanel picturePan = new JPanel();
	private JPanel pathPan = new JPanel();
	private JPanel buttonPan = new JPanel();

	private JButton commit = new JButton("올리기");
	private JButton cancel = new JButton("돌아가기");

	public void compInit() {
		setLayout(new BorderLayout(1, 1));
		picture.setPreferredSize(new Dimension(520, 565));
		picturePath.setPreferredSize(new Dimension(455, 30));
		comment.setPreferredSize(new Dimension(520, 100));

		picture.setBorder(tborder);
		picturePan.add(picture, BorderLayout.NORTH);
		pathPan.add(findPicture, BorderLayout.WEST);
		pathPan.add(picturePath, BorderLayout.EAST);
		pathPan.add(comment, BorderLayout.SOUTH);
		buttonPan.add(commit);
		buttonPan.add(cancel);

		add(picturePan, BorderLayout.NORTH);
		add(pathPan, BorderLayout.CENTER);
		add(buttonPan, BorderLayout.SOUTH);
	}

	public void eventInit() {
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		findPicture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 로컬파일찾은후 텍스트필드에 경로 붙이기.
				fileChooser();
				img = fc.getSelectedFile();// 이미지 file형으로 return.
				picturePath.setText(img.getPath() + "/" + img.getName());

				System.out.println(img.getPath() + "/");

				imgIcon = new ImageIcon(img.getPath() + "/" + img.getName());
				picture = new JLabel(imgIcon);
				picturePan.add(picture, BorderLayout.NORTH);
				add(picturePan, BorderLayout.NORTH);
				repaint();

				System.out.println("파일찾기완료");
			}
		});

		commit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//
				try {
					dos.writeUTF("추가");
					marshalling();
					JOptionPane.showMessageDialog(null, "게시물 올리기 완료");
					dispose();
				} catch (IOException e1) {
					System.out.println("커뮤니티 마셜링 실패");
				}
				
			}
		});
	}

	public void fileChooser() {
		fc.setAccessory(new ImagePreview(fc));
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		switch (fc.showOpenDialog(AddPictureBoard.this)) { // △파일열기.
		case JFileChooser.APPROVE_OPTION:// 열기버튼
			// img = fc.getSelectedFile(); // img에 선택한 파일 넣음.

			break;

		// case JFileChooser.CANCEL_OPTION:
		// JOptionPane.showMessageDialog(AddPictureBoard.this, "Cancelled", "FCDemo",
		// JOptionPane.OK_OPTION);
		// break;

		case JFileChooser.ERROR_OPTION:
			JOptionPane.showMessageDialog(AddPictureBoard.this, "error", "FCDemo", JOptionPane.OK_OPTION);
		}

		// return img;
	}

	public void marshalling() {
		
		try {
		
		System.out.println("클라이언트 소켓연결");
		dos = new DataOutputStream(client.getOutputStream());
		dis = new DataInputStream(client.getInputStream());
		dos.writeUTF("소켓연결후데이터수신");
		System.out.println("소켓연결후데이터수신요청성공");
	

		String title = null;
		String contents = null;
		String fileName = null;
		int fileSize = 0;
		byte[] fileContents = null;
		File home = new File("L:/김현수/클라이언트");
		File[] files = home.listFiles();
	
		for(File tmp:files)	{
			System.out.println(tmp.getAbsolutePath() + " : " + tmp.length());
			System.out.println(tmp.getAbsolutePath() + " : " + tmp.getName());
		}

		fileName="클라이언트1.txt";
		File targetFile = new File(home.getPath() + "/" + fileName);
		// System.out.println(home.getPath()+"/"+fileName);
		title="클라이언트1.txt";
		contents="클라이언트1.txt";
		fileName=targetFile.getName();
		fileSize=(int)targetFile.length();
		fileContents=new byte[fileSize];
		// 파일컨텐츠에 실제 파일을 담아준다.
		fis=new FileInputStream(targetFile);fis.read(fileContents);fis.close();
		// System.out.println("1번째 파일 :" + fileName + "의 파일 사이즈 :" +fileSize + " : " +
		// "의 파일 내용물 :" + fileContents );
		oos=new ObjectOutputStream(client.getOutputStream());
		// 	파일a제목 , 파일a내용 , 타겟팅한 파일의 이름 , 파일크기 , 파일을 바이트배열로 담아서 내용묶음
		FileList fl1 = new FileList(title, contents, fileName, fileSize, fileContents);
		oos.writeObject(fl1);fileName="d1.JPG";targetFile=new File(home.getPath()+"/"+fileName);
		//====================================================파일1개 보내기
//		
//		fileName="d1.JPG";
//		targetFile = new File(home.getPath() + "/" + fileName);
//		title="d1.JPG";
//		contents="d1.JPG내용";
//		fileName=targetFile.getName();
//		fileSize=(int)targetFile.length();
//		fileContents=new byte[fileSize];
//		// 파일컨텐츠에 실제 파일을 담아준다.
//		fis=new FileInputStream(targetFile);fis.read(fileContents);fis.close();
//		FileList fl2 = new FileList(title, contents, fileName, fileSize, fileContents);
//		oos.writeObject(fl2);
//		//====================================================파일2개 보내기
		
		}catch(Exception e1) {
			System.out.println("마셜링 실패");
		}
	}

	public AddPictureBoard(PictureBoardPan parent) {
		setSize(600, 800);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		compInit();
		eventInit();

		// pack();
		// 왜 pack을하면 지정한 사이즈로 나오지 않고 달라질까..?
		setModal(true);
	}

	public AddPictureBoard(Socket client, DataInputStream dis, DataOutputStream dos) {
		this.client = client;
		this.dis = dis;
		this.dos = dos;
	}
}