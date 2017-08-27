package free;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class PictureBoardPan extends JFrame {

	private PictureBoardPan self = this;

	private File[] imgF;
	private String comment;
	private String img;
	private String title;
	
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

	private JList list;
	private JScrollPane sc;
	private CellRenderer cellrender;
	private SetRenderer[] renderer;
	private int cnt;
	
	public void compInit() {
		setLayout(new BorderLayout(1,1));

		board1.setPreferredSize(new Dimension(600,640));
		board2.setPreferredSize(new Dimension(600,640));
		board3.setPreferredSize(new Dimension(600,640));
		board4.setPreferredSize(new Dimension(600,640));
		board5.setPreferredSize(new Dimension(600,640));
		
		floor3.setPreferredSize(new Dimension(600,50));
		floor4.setPreferredSize(new Dimension(600,50));
		
		
		board1.setBorder(tborder);
		board2.setBorder(tborder);
		board3.setBorder(tborder);
		board4.setBorder(tborder);
		board5.setBorder(tborder);

		floor3.add(upload);
		floor3.add(remove);
		floor4.add(lb);

		insertList();
		
		list = new JList(renderer);
		sc = new JScrollPane(list);
		list.setCellRenderer(new CellRenderer());
		
		sc.setPreferredSize(new Dimension(580,630));
		
		board1.add(sc);
		
		mainboardPan.add(board1,"page1");
		mainboardPan.add(board2,"page2");
		mainboardPan.add(board3,"page3");
		mainboardPan.add(board4,"page4");
		mainboardPan.add(board5,"page5");
		
		add(mainboardPan,BorderLayout.NORTH);
		add(floor3,BorderLayout.CENTER);
		add(floor4,BorderLayout.SOUTH);
		
		
	}

	public SetRenderer[] insertList() {
		
//		for(int i=0;i<cnt;i++) {
//			renderer = new SetRenderer[i];
//			renderer[i] = new SetRenderer(img,title);
//		}
		renderer = new SetRenderer[10];
		renderer[0] = new SetRenderer(new ImageIcon("KakaoTalk_20170808_111848043.jpg"),"나야나");
		renderer[1] = new SetRenderer(new ImageIcon("설현1.jpg"),"나야나1");
		renderer[2] = new SetRenderer(new ImageIcon("KakaoTalk_20170808_111848677.jpg"),"나야나2");
		renderer[3] = new SetRenderer(new ImageIcon("KakaoTalk_20170808_111851622.jpg"),"나야나3");	
		renderer[4] = new SetRenderer(new ImageIcon("KakaoTalk_20170808_111851622.jpg"),"나야나3");
		renderer[5] = new SetRenderer(new ImageIcon("KakaoTalk_20170808_111851622.jpg"),"나야나3");
		renderer[6] = new SetRenderer(new ImageIcon("KakaoTalk_20170808_111851622.jpg"),"나야나3");
		renderer[7] = new SetRenderer(new ImageIcon("KakaoTalk_20170808_111851622.jpg"),"나야나3");
		renderer[8] = new SetRenderer(new ImageIcon("KakaoTalk_20170808_111851622.jpg"),"나야나3");
		renderer[9] = new SetRenderer(new ImageIcon("KakaoTalk_20170808_111851622.jpg"),"나야나3");
		
		return renderer;
	}
	
	
	public void eventInit() {
		upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cnt++;
				new AddPictureBoard(self).setVisible(true);
				
				repaint();
				
			}
		});
	}

//	public void getImgData() {// 썸네일 받아오기
//		try {
//			dos.writeUTF("사진게시판 썸네일");
//			
//			while (true) {
//				int fileSize = dis.readInt();// 스트림에서 받아온파일을 인트형으로 읽어서 인트형 파일사이즈에 저장.
//				byte[] fileContents = new byte[fileSize];// 바이트형 배열에 파일사이즈 저장(?)
//
//				dis.readFully(fileContents);// 내용을 끝까지 읽어내게함(readFully()메소드)
//				// 여기까지가 서버에서 받아온 파일을 임시변수에 저장하여 가져옴.
//
//				File f = new File("D:/");// 파일형 f 에 저장 위치와 저장할 이름정함.
//				FileOutputStream fos = new FileOutputStream(f);// f를 파일아웃풋스트림형 fos인스턴스에 저장.
//				DataOutputStream dos = new DataOutputStream(fos);// fos를 데이터아웃풋스트림형 dos인스턴스테 저장.
//
//				dos.write(fileContents);// 파일컨텐츠를 읽음.
//				dos.flush();// 버퍼 날리기.
//				// 여기까지가 임시변수에 저장되어있던 서버에서 받아온 파일을 로컬하드디스크로 쓰는것=저장.
//			}
//		} catch (Exception e) {
//			System.out.println("영상썸네일 데이터 받기 실패");
//		}
//	}
//
//	public void getUrlData() {
//		try {
//			dos.writeUTF("사진게시판 url");
//			
//			for(int i = 0;i<url.length;i++) {
//				url[i] = dis.readUTF();
//			}
//		} catch (Exception e1) {
//			System.out.println("영상url 데이터 받기 실패");
//		}
//	}


	public PictureBoardPan() {
		setSize(700, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		compInit();
		eventInit();

		setVisible(true);
	}

	public static void main(String[] args) {
		new PictureBoardPan();
	}
}