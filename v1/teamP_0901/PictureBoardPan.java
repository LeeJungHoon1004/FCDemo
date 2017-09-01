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

//��Ʈ���� �޸� �߰� ����
//http://yonoo88.tistory.com/230

public class PictureBoardPan extends JPanel {

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

	
	private String userID;
	private String userPW;
	private int index;// jlist ������ �ε��� ��ȣ
	private TitledBorder tborder = new TitledBorder("");
	
	private JPanel floor1 = new JPanel();
	private JPanel floor2 = new JPanel();
	
	private JButton upload = new JButton("�ۿø���");
	private JButton remove = new JButton("�ۻ���");

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
				// JDialog ���̱�!
				
				renew();//renew �ȿ� unmarshalling�� �������.
				//repaint();

			}
		});
		
		 remove.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 int index = list.getSelectedIndex(); // ���õ� �׸��� �ε����� �����´�.
				 // �ε����� 0���� ����
		
				 try {
					 dos.writeUTF("����Ʈ ����");
					 dos.writeInt(index);//������ ����Ʈ�� �����̸Ӹ� ��ȣ
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
				 
				 renew();
				 //������ ����
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

	public void renew() {//���� �޼ҵ�
		unmarshalling();
		list = new JList(dlm);
		sc = new JScrollPane(list);
		list.setCellRenderer(new CellRenderer());
	}
	
	public void unmarshalling() {
		try {
			
			dos.writeUTF("Ŀ�´�Ƽ�г� ����");
		// �����Ϸ��� ������ �̸� , ũ�� , ���빰(������ü) , ������ Ÿ��Ʋ ,������ ����
		String title = null;
		String contents = null;
		String fileName = null;
		int fileSize = 0;
		byte[] fileContents = null;

		// 2.Ŭ���̾�Ʈ���� �����͸� �޽��ϴ� (2.ClientRam to ServerRam)
		ois = new ObjectInputStream(client.getInputStream());
		FileList fl1 = (FileList) ois.readObject();

		System.out.println(fl1.getTitle()); // ����
		System.out.println(fl1.getContents());// �ڸ�Ʈ
		System.out.println(fl1.getFileName());// �����̸�
		System.out.println(fl1.getFileSize());// ������ ũ��(int)
		System.out.println(fl1.getFileContents());// ������ ���빰(byte [])

		fileContents = fl1.getFileContents();
		File f = new File("C:/Users/Administrator/4weeksWorkout/" + fl1.getFileName());
		fos = new FileOutputStream(f);
		bos = new BufferedOutputStream(fos);
		dos = new DataOutputStream(bos);
		dos.write(fileContents);
		dos.flush();
		//===========================����1�� �𸶼ȸ�
		System.out.println("Ŭ���̾�Ʈ���� ���� �����͸� �ϵ��ũ�� ����Ϸ�.");

		dos.close();
		}catch(Exception e ) {
			System.out.println("Ŀ�´�Ƽ ������ �ޱ� ����");
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