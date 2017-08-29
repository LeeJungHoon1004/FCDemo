package free;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

import team_project0807.BasicShape;

//오브젝트 스트림 = 객체직렬화 / 객체직렬화 소켓통신
//<a target="_blank" href="http://nicebury.tistory.com/15" class="tx-link">http://nicebury.tistory.com/15</a>;

public class AddPictureBoard extends JDialog {

	private Socket client;
	private DataInputStream dis;
	private DataOutputStream dos;

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
				picturePath.setText(img.getPath());
				
				System.out.println(img.getPath());
				
				imgIcon = new ImageIcon(img.getPath());
				picture = new JLabel(imgIcon);
				picturePan.add(picture, BorderLayout.NORTH);
				add(picturePan, BorderLayout.NORTH);
				repaint();
				
				new SavePictureBoard(img, img.getName(),
						title.getText(),comment.getText(),
						new BasicShape().getId(),new BasicShape().getPw());
				System.out.println("파일찾기완료");
			}
		});

		commit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//
				try {
					dos.writeUTF("추가");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				marshalling();
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
			//img = fc.getSelectedFile(); // img에 선택한 파일 넣음.

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
		File f = new File("C://Users//4WeeksWorkOut");
		try {
		FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        dos.writeInt(spb.size());//파일 갯수 먼저 보냄.
        
        oos.writeObject(spb);//spb 파일 보냄.
        oos.close();
		}catch(Exception e1) {
			System.out.println("커뮤니티 마샬링 오류");
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