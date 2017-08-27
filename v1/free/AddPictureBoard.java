package free;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.Icon;
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
//<a target="_blank" href="http://egloos.zum.com/dojeun/v/317825" class="tx-link">http://egloos.zum.com/dojeun/v/317825</a>;
//

public class AddPictureBoard extends JDialog {

	private Socket client;
	private DataInputStream dis;
	private DataOutputStream dos;

	private AddPictureBoard self = this;
	private TitledBorder tborder = new TitledBorder("");
	// ======FILE CHOOSER=======================
	private JFileChooser fc = new JFileChooser();
	private File img;
	
	private ImageIcon imgIcon;
	private JLabel picture = new JLabel(imgIcon);
	private JButton findPicture = new JButton("사진");
	private JTextField picturePath = new JTextField();
	private JTextField comment = new JTextField();

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
				picturePath.setText(img.getPath());
				
				imgIcon = new ImageIcon(img.getPath());
				picture = new JLabel(imgIcon);
				picturePan.add(picture, BorderLayout.NORTH);
				add(picturePan, BorderLayout.NORTH);
			}
		});


		commit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//sendData();
				img = fc.getSelectedFile();
				SetRenderer sr = new SetRenderer(img.getPath());
				JOptionPane.showMessageDialog(null, "upload complete");
				dispose();
			}
		});
	}

	
	public void fileChooser() {
		fc.setAccessory(new ImagePreview(fc));
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		switch (fc.showOpenDialog(AddPictureBoard.this)) { // △파일열기.
		case JFileChooser.APPROVE_OPTION:// 열기버튼
			img = fc.getSelectedFile();
			System.out.println(img);// ←선택한 파일주소

			break;

//		case JFileChooser.CANCEL_OPTION:
//			JOptionPane.showMessageDialog(AddPictureBoard.this, "Cancelled", "FCDemo", JOptionPane.OK_OPTION);
//			break;

		case JFileChooser.ERROR_OPTION:
			JOptionPane.showMessageDialog(AddPictureBoard.this, "error", "FCDemo", JOptionPane.OK_OPTION);
		}

	}// 선택한 img를 파일형으로 return.

	
	public void sendData() {
		//connection();
		img = fc.getSelectedFile(); //꺼내올 파일 경로.
		long fileSize = img.length();//파일사이즈를 long형으로 저장.

		try{
			FileInputStream fis = new FileInputStream(img);//파일인풋스트림 fis에 보낼파일경로저장.
			DataInputStream dis = new DataInputStream(fis);//데이터인풋스트림 dis에 fis저장.
			
			byte[] fileContents = new byte[(int)fileSize];//byte형 배열에 파일 사이즈 저장.
			dis.readFully(fileContents);//파일 끝까지 잘 읽어라.
			
			System.out.println(client.getInetAddress()+"에게 파일사이즈..");
			
			this.dos.writeInt((int)fileSize);
			this.dos.write(fileContents);
			this.dos.flush();
			
		}catch(Exception e){
			System.out.println("파일을 찾을 수 없습니다.");
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
	
	public AddPictureBoard(Socket client,DataInputStream dis,DataOutputStream dos) {
		this.client = client;
		this.dis= dis;
		this.dos = dos;
	}
}