package free;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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

//��Ʈ���� �޸� �߰� ����
//http://yonoo88.tistory.com/230

public class PictureBoardPan extends JFrame {

	private PictureBoardPan self = this;
	private Socket client;
	private DataInputStream dis;
	private DataOutputStream dos;

	private ArrayList<SavePictureBoard> spb = new ArrayList<SavePictureBoard>();
	private File f = new File("C://Users//4weeksWorkOut");
	// private File[] imgFile;
	private String comment;
	// private String img;
	private String imgPath;
	private String writer;
	private String title;
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

	private JList list;
	private JScrollPane sc;
	
	private CellRenderer cellrender;
	private SetRenderer[] renderer = new SetRenderer[10];

	private int cnt = 0;
	private Object user;

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

		list = new JList(addList());
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
	
	public SetRenderer[] addList() {
		System.out.println("7");
//		for (int i = 0; i < cnt; i++) {
//
//			String inputimgPath = spb.get(i).getImg().getPath();
//			String inputcomment = spb.get(i).getTitle();
//
//			renderer = new SetRenderer[i];
//			renderer[i] = new SetRenderer(new ImageIcon(inputimgPath), inputcomment);
//		}

		 renderer = new SetRenderer[10];
		 renderer[0] = new SetRenderer(new ImageIcon("img1.jpg"), "���߳�");
		 renderer[1] = new SetRenderer(new ImageIcon("����1.jpg"), "���߳�1");
		 renderer[2] = new SetRenderer(new ImageIcon("img11.jpg"), "���߳�2");
		 renderer[3] = new SetRenderer(new ImageIcon("img13.jpg"), "���߳�3");
		 renderer[4] = new SetRenderer(new ImageIcon("img1.jpg"), "���߳�3");
		 renderer[5] = new SetRenderer(new ImageIcon("img11.jpg"), "���߳�3");
		 renderer[6] = new SetRenderer(new ImageIcon("img13.jpg"), "���߳�3");
		 renderer[7] = new SetRenderer(new ImageIcon("img1.jpg"), "���߳�3");
		 renderer[8] = new SetRenderer(new ImageIcon("img11.jpg"), "���߳�3");
		 renderer[9] = new SetRenderer(new ImageIcon("img13.jpg"), "���߳�3");
		
		System.out.println("8");
		return renderer;
		
	}

	public void eventInit() {
		upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cnt++;
				
				new AddPictureBoard(self).setVisible(true);// JDialog ���̱�!
				new AddPictureBoard(client, dis, dos);// ������� ����(?)
				
				try {
					dos.writeUTF("���ε�");
				} catch (IOException e1) {
					System.out.println("�����Խ��� ���ε� ������ ����");
				}
				unmarshalling();
				repaint();

			}
		});
//
//		remove.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int index = list.getSelectedIndex(); // ���õ� �׸��� �ε����� �����´�.
//				// �ε����� 0���� ����
//
//				try {
//					dos.writeUTF("����Ʈ ����");
//					dos.writeInt(index);
//				} catch (Exception e1) {
//					System.out.println("���� ����");
//				} // ������ �Խñ� ���شٰ� ����.
//
//				list.remove(index); // ����Ʈ�𵨿��� ���õ� �׸��� �����.
//				if (listmodel.getSize() == 0) { // ����Ʈ���� ����� 0�̵Ǹ� ������ư�� ���� �� ���� �Ѵ�.
//					// btnDel.setEnabled(false);
//				}
//				if (index == listmodel.getSize()) { // �ε����� ����Ʈ���� �������׸��� ������
//					index--; // ��,���õ� �ε����� ����Ʈ�� ������ �׸��̾�����
//				} // �ε����� -1�ؼ� �ε����� �Ű��ش�.
//				list.setSelectedIndex(index); //
//				list.ensureIndexIsVisible(index);
//
//				JOptionPane.showMessageDialog(null, "�Խñ��� �����Ǿ����ϴ�");
//			}
//		});

		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				index = list.getSelectedIndex();
				imgPath = "C://Users//4weeksWorkOut//"+spb.get(index).getImaName();
				
				new ListSelected(imgPath , spb.get(index).getWriter()
									, spb.get(index).getTitle() , spb.get(index).getComment());
				new ListSelected(self).setVisible(true);
			}
		});

	}

	public Object unmarshalling() {
		System.out.println("9");
		try {
			for (int i = 0; i < cnt; i++) {
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);
				SavePictureBoard spb = (SavePictureBoard) ois.readObject();
				ois.close();
			}
		} catch (Exception e1) {
			System.out.println("�����Խ��� unmarshalling ����");
		}
		System.out.println("10");
		return spb;		
	}

//	public PictureBoardPan(Socket client, DataInputStream dis, DataOutputStream dos) {
//		this.client = client;
//		this.dis = dis;
//		this.dos = dos;
//	}

	public PictureBoardPan() {
		try {
		client = new Socket("192.168.53.129",40000);
		dis = new DataInputStream(client.getInputStream());
		dos = new DataOutputStream(client.getOutputStream());
		System.out.println("���Ἲ��");
		}catch(Exception e) {
			System.out.println("�ʱ⿬�� ����");
		}
		
		System.out.println("����");
		setSize(700, 800);
		
		System.out.println("1");
		
		setLocationRelativeTo(null);
		
		System.out.println("2");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		System.out.println("3");
		
		compInit();
		
		System.out.println("6");
		
		eventInit();
		
		System.out.println("11");

		setVisible(true);
		System.out.println("12");
	}

	public static void main(String[] args) {
		new PictureBoardPan();
		
		// if (new BasicShape().getID != null && new BasicShape().getPW != null) {
		//
		// } else {
		// JOptionPane.showMessageDialog(null, "�α����� �ϰ� �̿��� �ּ���.");
		// }
	}
}