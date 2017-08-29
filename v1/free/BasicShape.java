package free;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class BasicShape extends JFrame {

	

	private CardLayout c = new CardLayout();
	private JPanel p1 = new JPanel (c);
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	private JPanel p4 = new JPanel();
	private PictureBoardPan pbp = new PictureBoardPan();
	private BasicShape self = this;
	private JButton bt = new JButton("커뮤니티");
	
	private Socket client;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	

	private void comp() {
		
		p4.add(bt);
		p3.add(p4);
		p2.add(pbp);
		p1.add(p4,"bt");
		p1.add(p2,"pivcture");
		
		add(p1);
		add(bt);
	}
	
	private void eventInit() {
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new PictureBoardPan(client,dis,dos);
				c.show(p2, "pivcture");
				
			}
		});
		
	}

	public BasicShape() {
		setTitle("기본shape테스트");
		setSize(1200, 750);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		comp();
		eventInit();
		setVisible(true);
	}


	public static void main(String[] args) {
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
		new BasicShape();
	}// main end
}