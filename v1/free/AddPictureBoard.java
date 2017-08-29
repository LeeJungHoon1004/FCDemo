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

//������Ʈ ��Ʈ�� = ��ü����ȭ / ��ü����ȭ �������
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
	private JButton findPicture = new JButton("����");
	private JTextField picturePath = new JTextField();
	private JTextField comment = new JTextField();
	private JTextField title = new JTextField();

	private JPanel picturePan = new JPanel();
	private JPanel pathPan = new JPanel();
	private JPanel buttonPan = new JPanel();

	private JButton commit = new JButton("�ø���");
	private JButton cancel = new JButton("���ư���");

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
				// ��������ã���� �ؽ�Ʈ�ʵ忡 ��� ���̱�.
				fileChooser();
				img = fc.getSelectedFile();// �̹��� file������ return.
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
				System.out.println("����ã��Ϸ�");
			}
		});

		commit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//
				try {
					dos.writeUTF("�߰�");
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
		switch (fc.showOpenDialog(AddPictureBoard.this)) { // �����Ͽ���.
		case JFileChooser.APPROVE_OPTION:// �����ư
			//img = fc.getSelectedFile(); // img�� ������ ���� ����.

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
        
        dos.writeInt(spb.size());//���� ���� ���� ����.
        
        oos.writeObject(spb);//spb ���� ����.
        oos.close();
		}catch(Exception e1) {
			System.out.println("Ŀ�´�Ƽ ������ ����");
		}

	}

	public AddPictureBoard(PictureBoardPan parent) {
		setSize(600, 800);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		compInit();
		eventInit();

		// pack();
		// �� pack���ϸ� ������ ������� ������ �ʰ� �޶�����..?
		setModal(true);
	}

	public AddPictureBoard(Socket client, DataInputStream dis, DataOutputStream dos) {
		this.client = client;
		this.dis = dis;
		this.dos = dos;
	}
}