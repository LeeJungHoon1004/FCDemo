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

//��Ʈ���� �޸� �߰� ����
//http://yonoo88.tistory.com/230

public class PictureBoardPan extends JFrame {

	private PictureBoardPan self = this;
	private Socket client;
	// �ƿ� ��Ʈ��
	private FileOutputStream fos = null;
	private BufferedOutputStream bos = null;
	private ObjectOutputStream oos = null;
	private DataOutputStream dos = null;
	// ��ǲ��Ʈ��
	private FileInputStream fis = null;
	private BufferedInputStream bis = null;
	private DataInputStream dis = null;
	private ObjectInputStream ois = null;

	
	private int index;// jlist ������ �ε��� ��ȣ

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
	private JButton upload = new JButton("�ۿø���");
	private JButton remove = new JButton("�ۻ���");

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

				new AddPictureBoard(self).setVisible(true);// JDialog ���̱�!
				new AddPictureBoard(client, dis, dos);// ������� ����(?)
				
				unmarshalling();
				repaint();

			}
		});
		
		 remove.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 int index = list.getSelectedIndex(); // ���õ� �׸��� �ε����� �����´�.
				 // �ε����� 0���� ����
		
				 try {
					 dos.writeUTF("����Ʈ ����");
					 dos.writeInt(index);
				 } catch (Exception e1) {
					 System.out.println("���� ����");
				 } // ������ �Խñ� ���شٰ� ����.
		
				 list.remove(index); // ����Ʈ�𵨿��� ���õ� �׸��� �����.
				 if (dlm.getSize() == 0) { // ����Ʈ���� ����� 0�̵Ǹ� ������ư�� ���� �� ���� �Ѵ�.
					 // btnDel.setEnabled(false);
				 }
				 if (index == dlm.getSize()) { // �ε����� ����Ʈ���� �������׸��� ������
					 index--; // ��,���õ� �ε����� ����Ʈ�� ������ �׸��̾�����
				 } // �ε����� -1�ؼ� �ε����� �Ű��ش�.
				 list.setSelectedIndex(index); //
				 list.ensureIndexIsVisible(index);
		
				 JOptionPane.showMessageDialog(null, "�Խñ��� �����Ǿ����ϴ�");
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
		// �����Ϸ��� ������ �̸� , ũ�� , ���빰(������ü) , ������ Ÿ��Ʋ ,������ ����
		String title = null;
		String contents = null;
		String fileName = null;
		int fileSize = 0;
		byte[] fileContents = null;

		// 2.Ŭ���̾�Ʈ���� �����͸� �޽��ϴ� (2.ClientRam to ServerRam)
		ois = new ObjectInputStream(client.getInputStream());
		FileList fl1 = (FileList) ois.readObject();
		//FileList fl2 = (FileList) ois.readObject();
		// FileList fl3 = (FileList) ois.readObject();

		System.out.println(fl1.getTitle()); // ����
		System.out.println(fl1.getContents());// �ڸ�Ʈ
		System.out.println(fl1.getFileName());// �����̸�
		System.out.println(fl1.getFileSize());// ������ ũ��(int)
		System.out.println(fl1.getFileContents());// ������ ���빰(byte [])

		fileContents = fl1.getFileContents();
		File f = new File("L:/������/����/" + fl1.getFileName());
		fos = new FileOutputStream(f);
		bos = new BufferedOutputStream(fos);
		dos = new DataOutputStream(bos);
		dos.write(fileContents);
		dos.flush();
		//===========================����1�� �𸶼ȸ�
//		fileContents = fl2.getFileContents();
//		File f2 = new File("L:/������/����/" + fl2.getFileName());
//		fos = new FileOutputStream(f2);
//		dos = new DataOutputStream(fos);
//		dos.write(fileContents);
//		dos.flush();
		//===========================����2�� �𸶼ȸ�
		System.out.println("Ŭ���̾�Ʈ���� ���� �����͸� �ϵ��ũ�� ����Ϸ�.");

		dos.close();
		}catch(Exception e ) {
			System.out.println("Ŀ�´�Ƽ ������ �ޱ� ����");
		}
	}



	public PictureBoardPan() {
		try {
			client = new Socket("192.168.53.129", 40000);
			dis = new DataInputStream(client.getInputStream());
			dos = new DataOutputStream(client.getOutputStream());
			System.out.println("���Ἲ��");
		} catch (Exception e) {
			System.out.println("�ʱ⿬�� ����");
		}

		setSize(700, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		compInit();
		System.out.println("��");
		eventInit();
		System.out.println("��");
		setVisible(true);
	}

	public static void main(String[] args) {
		new PictureBoardPan();

	}
}