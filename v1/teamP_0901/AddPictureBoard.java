package teamP_0901;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
	// 인풋스트림
	private FileInputStream fis = null;
	private BufferedInputStream bis = null;
	private DataInputStream dis = null;
	private ObjectInputStream ois = null;

	private AddPictureBoard self = this;
	private TitledBorder tborder = new TitledBorder("");
	// ======FILE CHOOSER=======================
	private JFileChooser fc = new JFileChooser();
	private File file;
	private int returnVal;
	private String userID;
	private String userPW;
	
	private JLabel picture = new JLabel();
	private JButton findPicture = new JButton("사진");
	private JLabel titleLabel = new JLabel("글제목:");
	private JTextField picturePath = new JTextField();
	private JTextField comment = new JTextField();
	private JTextField titleField = new JTextField();

	private JPanel picturePan = new JPanel();
	private JPanel PahtPan = new JPanel();
	private JPanel titlePan = new JPanel();
	private JPanel dataPan = new JPanel();
	private JPanel buttonPan = new JPanel();

	private JButton commit = new JButton("올리기");
	private JButton cancel = new JButton("돌아가기");

	public void compInit() {
		setLayout(new BorderLayout(0, 0));
		picturePan.setPreferredSize(new Dimension(510, 510));
		picturePath.setPreferredSize(new Dimension(455, 30));
		titleField.setPreferredSize(new Dimension(455, 30));
		comment.setPreferredSize(new Dimension(520, 110));

		picturePan.setBorder(tborder);
		picturePan.add(picture);

		PahtPan.add(findPicture, BorderLayout.WEST);
		PahtPan.add(picturePath, BorderLayout.EAST);

		titlePan.add(titleLabel, BorderLayout.WEST);
		titlePan.add(titleField, BorderLayout.EAST);

		dataPan.add(PahtPan, BorderLayout.NORTH);
		dataPan.add(titlePan, BorderLayout.CENTER);
		dataPan.add(comment, BorderLayout.SOUTH);
		buttonPan.add(commit);
		buttonPan.add(cancel);

		picturePan.setBackground(Color.white);
		dataPan.setBackground(Color.white);
		buttonPan.setBackground(Color.white);
		PahtPan.setBackground(Color.white);
		titlePan.setBackground(Color.white);

		add(picturePan, BorderLayout.NORTH);
		add(dataPan, BorderLayout.CENTER);
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

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();
					picture.setIcon(new ImageIcon(file.getPath()));
					picturePath.setText(file.getPath());
					repaint();

				} else if (returnVal == JFileChooser.ERROR_OPTION) {
					JOptionPane.showMessageDialog(AddPictureBoard.this, "error", "FCDemo", JOptionPane.OK_OPTION);
				}

				System.out.println("파일찾기완료");
			}
		});

		commit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					marshalling();

					JOptionPane.showMessageDialog(null, "커뮤니티 업로드 완료");
					dispose();

			}
		});
	}

	public void fileChooser() {
		fc.setAccessory(new ImagePreview(fc));
		fc.setMultiSelectionEnabled(true);
		// 여러개 선택 가능.
		fc.setFileFilter(
				new javax.swing.filechooser.FileNameExtensionFilter("JPEG", "jpeg", "JPG", "jpg", "PNG", "png"));
		// 사진 파일 필터
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		returnVal = fc.showOpenDialog(AddPictureBoard.this); // △파일열기.
	}

	public void marshalling() {

		try {

			dos.writeUTF("커뮤니티에 게시글 추가");

			String title = null;
			String contents = null;
			String fileName = null;
			int fileSize = 0;
			byte[] fileContents = null;
			File home = new File("L:/김현수/클라이언트");
			File[] files = home.listFiles();

			for (File tmp : files) {
				System.out.println(tmp.getAbsolutePath() + " : " + tmp.length());
				System.out.println(tmp.getAbsolutePath() + " : " + tmp.getName());
			}

			File targetFile = new File(picturePath.getText());
			title = titleField.getText();
			contents = comment.getText();
			fileName = targetFile.getName();
			fileSize = (int) targetFile.length();
			fileContents = new byte[fileSize];
			// 파일컨텐츠에 실제 파일을 담아준다.
			fis = new FileInputStream(targetFile);
			fis.read(fileContents);
			fis.close();
			// System.out.println("1번째 파일 :" + fileName + "의 파일 사이즈 :" +fileSize + " : " +
			// "의 파일 내용물 :" + fileContents );
			oos = new ObjectOutputStream(client.getOutputStream());
			// 파일a제목 , 파일a내용 , 타겟팅한 파일의 이름 , 파일크기 , 파일을 바이트배열로 담아서 내용묶음
			FileList fl1 = new FileList(title, contents, fileName, fileSize, fileContents,userID,userPW);
			oos.writeObject(fl1);
			fileName = "d1.JPG";
			targetFile = new File(home.getPath() + "/" + fileName);
			// ====================================================파일1개 보내기

		} catch (Exception e1) {
			System.out.println("마셜링 실패");
		}
	}

	public AddPictureBoard(PictureBoardPan parent, Socket client, DataInputStream dis, DataOutputStream dos,String userID,String userPW) {
		this.client = client;
		this.dis = dis;
		this.dos = dos;
		this.userID = userID;
		this.userPW = userPW;
		
		this.setBackground(Color.white);
		setSize(600, 800);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.client = client;
		this.dis = dis;
		this.dos = dos;
		compInit();
		eventInit();

		// pack();
		// 왜 pack을하면 지정한 사이즈로 나오지 않고 달라질까..?
		setModal(true);
	}

}