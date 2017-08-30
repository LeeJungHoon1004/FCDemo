package free;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
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
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import File.FileList;

//스트링에 콤마 추가 제거
//http://yonoo88.tistory.com/230

public class PictureBoardPan extends JFrame {

	private PictureBoardPan self = this;
	private Socket client;
	// 아웃 스트림
	private FileOutputStream fos = null;
	private BufferedOutputStream bos = null;
	private ObjectOutputStream oos = null;
	private DataOutputStream dos = null;
	// 인풋스트림
	private FileInputStream fis = null;
	private BufferedInputStream bis = null;
	private DataInputStream dis = null;
	private ObjectInputStream ois = null;

	
	private int index;// jlist 선택한 인덱스 번호

	private TitledBorder tborder = new TitledBorder("");
	private CardLayout card = new CardLayout();
	private JPanel mainboardPan = new JPanel();

	private JPanel board1 = new JPanel();
	private JPanel board2 = new JPanel();
	private JPanel board3 = new JPanel();
	private JPanel board4 = new JPanel();
	private JPanel board5 = new JPanel();

	private JPanel floor3 = new JPanel();
	private JPanel floor4 = new JPanel();

	private JLabel lb = new JLabel("1,2,3,4");
	private JButton upload = new JButton("글올리기");
	private JButton remove = new JButton("글삭제");

	private DefaultListModel dlm = new DefaultListModel();
	private JList list;
	private JScrollPane sc;
	private CellRenderer cellrender;
	private int cnt = 0;

	public void compInit() {
		System.out.println("4");
		setLayout(new BorderLayout(1, 1));

		board1.setPreferredSize(new Dimension(600, 640));
		board2.setPreferredSize(new Dimension(600, 640));
		board3.setPreferredSize(new Dimension(600, 640));
		board4.setPreferredSize(new Dimension(600, 640));
		board5.setPreferredSize(new Dimension(600, 640));

		floor3.setPreferredSize(new Dimension(600, 50));
		floor4.setPreferredSize(new Dimension(600, 50));

		board1.setBorder(tborder);
		board2.setBorder(tborder);
		board3.setBorder(tborder);
		board4.setBorder(tborder);
		board5.setBorder(tborder);

		floor3.add(upload);
		floor3.add(remove);
		floor4.add(lb);


		list = new JList(dlm);
		sc = new JScrollPane(list);
		list.setCellRenderer(new CellRenderer());

		sc.setPreferredSize(new Dimension(580, 630));

		board1.add(sc);

		mainboardPan.add(board1, "page1");
		mainboardPan.add(board2, "page2");
		mainboardPan.add(board3, "page3");
		mainboardPan.add(board4, "page4");
		mainboardPan.add(board5, "page5");

		add(mainboardPan, BorderLayout.NORTH);
		add(floor3, BorderLayout.CENTER);
		add(floor4, BorderLayout.SOUTH);
		System.out.println("5");

	}

	public void addList() {
		
		for(int i = 0;i<cnt;i++) {
			new CellRenderer();
		}
//https://m.blog.naver.com/PostView.nhn?blogId=heoguni&logNo=130170350764&proxyReferer=https%3A%2F%2Fwww.google.co.kr%2F

	}

	public void eventInit() {
		upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cnt++;

				new AddPictureBoard(self).setVisible(true);// JDialog 보이기!
				new AddPictureBoard(client, dis, dos);// 소켓통신 전달(?)
				
				unmarshalling();
				repaint();

			}
		});
		
		 remove.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 int index = list.getSelectedIndex(); // 선택된 항목의 인덱스를 가져온다.
				 // 인덱스는 0부터 시작
		
				 try {
					 dos.writeUTF("리스트 삭제");
					 dos.writeInt(index);
				 } catch (Exception e1) {
					 System.out.println("삭제 실패");
				 } // 서버에 게시글 없앤다고 보냄.
		
				 list.remove(index); // 리스트모델에서 선택된 항목을 지운다.
				 if (dlm.getSize() == 0) { // 리스트모델의 사이즈가 0이되면 삭제버튼을 누를 수 없게 한다.
					 // btnDel.setEnabled(false);
				 }
				 if (index == dlm.getSize()) { // 인덱스와 리스트모델의 마지막항목이 같으면
					 index--; // 즉,선택된 인덱스가 리스트의 마지막 항목이었으면
				 } // 인덱스를 -1해서 인덱스를 옮겨준다.
				 list.setSelectedIndex(index); //
				 list.ensureIndexIsVisible(index);
		
				 JOptionPane.showMessageDialog(null, "게시글이 삭제되었습니다");
		 		}
		 });

		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				index = list.getSelectedIndex();
				FileList[] fl = new FileList[index];
				String img = ""+"/"+fl[index].getFileName();
				String title = fl[index].getTitle();
				String comment = fl[index].getContents();
				
				new ListSelected(img,title,comment);
				new ListSelected(self).setVisible(true);
			}
		});

	}

	public void unmarshalling() {
		try {
		// 전송하려는 파일의 이름 , 크기 , 내용물(파일자체) , 파일의 타이틀 ,파일의 내용
		String title = null;
		String contents = null;
		String fileName = null;
		int fileSize = 0;
		byte[] fileContents = null;

		// 2.클라이언트에서 데이터를 받습니다 (2.ClientRam to ServerRam)
		ois = new ObjectInputStream(client.getInputStream());
		FileList fl1 = (FileList) ois.readObject();
		//FileList fl2 = (FileList) ois.readObject();
		// FileList fl3 = (FileList) ois.readObject();

		System.out.println(fl1.getTitle()); // 제목
		System.out.println(fl1.getContents());// 코멘트
		System.out.println(fl1.getFileName());// 파일이름
		System.out.println(fl1.getFileSize());// 파일의 크기(int)
		System.out.println(fl1.getFileContents());// 파일의 내용물(byte [])

		fileContents = fl1.getFileContents();
		File f = new File("L:/김현수/서버/" + fl1.getFileName());
		fos = new FileOutputStream(f);
		bos = new BufferedOutputStream(fos);
		dos = new DataOutputStream(bos);
		dos.write(fileContents);
		dos.flush();
		//===========================파일1개 언마셜링
//		fileContents = fl2.getFileContents();
//		File f2 = new File("L:/김현수/서버/" + fl2.getFileName());
//		fos = new FileOutputStream(f2);
//		dos = new DataOutputStream(fos);
//		dos.write(fileContents);
//		dos.flush();
		//===========================파일2개 언마셜링
		System.out.println("클라이언트에서 받은 데이터를 하드디스크로 저장완료.");

		dos.close();
		}catch(Exception e ) {
			System.out.println("커뮤니티 데이터 받기 실패");
		}
	}



	public PictureBoardPan() {
		try {
			client = new Socket("192.168.53.129", 40000);
			dis = new DataInputStream(client.getInputStream());
			dos = new DataOutputStream(client.getOutputStream());
			System.out.println("연결성공");
		} catch (Exception e) {
			System.out.println("초기연결 실패");
		}

		setSize(700, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		compInit();
		System.out.println("아");
		eventInit();
		System.out.println("아");
		setVisible(true);
	}

	public static void main(String[] args) {
		new PictureBoardPan();

	}
}