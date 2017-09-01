package teamP_0901;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
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
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//스트링에 콤마 추가 제거
//http://yonoo88.tistory.com/230

public class PictureBoardPan extends JPanel {

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

	
	private String userID;
	private String userPW;
	private int index;// jlist 선택한 인덱스 번호
	private TitledBorder tborder = new TitledBorder("");
	
	private JPanel floor1 = new JPanel();
	private JPanel floor2 = new JPanel();
	
	private JButton upload = new JButton("글올리기");
	private JButton remove = new JButton("글삭제");

	private DefaultListModel dlm = new DefaultListModel();
	private JList list;
	private JScrollPane sc;
	private CellRenderer cellrender;
	private int cnt = 0;

	public void compInit() {
		setLayout(new BorderLayout(1, 1));

//		list = new JList(dlm);
//		sc = new JScrollPane(list);
//		list.setCellRenderer(new CellRenderer());
		renew();
		
		sc.setPreferredSize(new Dimension(970, 500));
		floor2.setPreferredSize(new Dimension(970,90));
		sc.setBorder(tborder);
		
		floor1.add(sc);
		floor2.add(upload);
		floor2.add(remove);

		floor1.setBackground(Color.white);
		floor2.setBackground(Color.white);
		
		add(floor1, BorderLayout.CENTER);
		add(floor2, BorderLayout.SOUTH);

	}

	public void eventInit() {
		upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cnt++;
				
				new AddPictureBoard(self,client, dis, dos,userID,userPW).setVisible(true);
				// JDialog 보이기!
				
				renew();//renew 안에 unmarshalling도 들어있음.
				//repaint();

			}
		});
		
		 remove.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 int index = list.getSelectedIndex(); // 선택된 항목의 인덱스를 가져온다.
				 // 인덱스는 0부터 시작
		
				 try {
					 dos.writeUTF("리스트 삭제");
					 dos.writeInt(index);//삭제할 리스트의 프라이머리 번호
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
				 
				 renew();
				 //삭제후 갱신
		 		}
		 });

		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				index = list.getSelectedIndex();
				ArrayList<FileList> fl = new ArrayList<FileList>();
				String img = ""+"/"+fl.get(index).getFileName();
				String title = fl.get(index).getTitle();
				String comment = fl.get(index).getContents();
				
				new ListSelected(self, img,title,comment,userID,userPW).setVisible(true);
			}
		});

	}

	public void renew() {//갱신 메소드
		unmarshalling();
		list = new JList(dlm);
		sc = new JScrollPane(list);
		list.setCellRenderer(new CellRenderer());
	}
	
	public void unmarshalling() {
		try {
			
			dos.writeUTF("커뮤니티패널 갱신");
		// 전송하려는 파일의 이름 , 크기 , 내용물(파일자체) , 파일의 타이틀 ,파일의 내용
		String title = null;
		String contents = null;
		String fileName = null;
		int fileSize = 0;
		byte[] fileContents = null;

		// 2.클라이언트에서 데이터를 받습니다 (2.ClientRam to ServerRam)
		ois = new ObjectInputStream(client.getInputStream());
		FileList fl1 = (FileList) ois.readObject();

		System.out.println(fl1.getTitle()); // 제목
		System.out.println(fl1.getContents());// 코멘트
		System.out.println(fl1.getFileName());// 파일이름
		System.out.println(fl1.getFileSize());// 파일의 크기(int)
		System.out.println(fl1.getFileContents());// 파일의 내용물(byte [])

		fileContents = fl1.getFileContents();
		File f = new File("C:/Users/Administrator/4weeksWorkout/" + fl1.getFileName());
		fos = new FileOutputStream(f);
		bos = new BufferedOutputStream(fos);
		dos = new DataOutputStream(bos);
		dos.write(fileContents);
		dos.flush();
		//===========================파일1개 언마셜링
		System.out.println("클라이언트에서 받은 데이터를 하드디스크로 저장완료.");

		dos.close();
		}catch(Exception e ) {
			System.out.println("커뮤니티 데이터 받기 실패");
		}
	}


	public PictureBoardPan(Socket client,DataInputStream dis,DataOutputStream dos,String userID,String userPW) {
		this.client = client;
		this.dis = dis;
		this.dos = dos;
		this.userID = userID;
		this.userPW = userPW;
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
		}
		
		this.setBackground(Color.white);
		setSize(700, 800);
		compInit();
		eventInit();
	}

}