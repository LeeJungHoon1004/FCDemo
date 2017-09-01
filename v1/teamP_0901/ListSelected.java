package teamP_0901;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class ListSelected extends JDialog {

	private TitledBorder tborder = new TitledBorder("");
	
	private ImageIcon imgIcon;
	private JLabel img = new JLabel(imgIcon);
	//private JLabel writer = new JLabel();
	private JLabel title = new JLabel();
	private JLabel comment = new JLabel();
	
	private JScrollPane sc = new JScrollPane(comment);
	private JPanel pan = new JPanel();
	
	private String userID;
	private String userPW;
	
	private void compInit() {
		setLayout(new BorderLayout(3, 3));
		
		img.setPreferredSize(new Dimension(500,250));
		//writer.setPreferredSize(new Dimension(50,50));
		title.setPreferredSize(new Dimension(120, 50));
		comment.setPreferredSize(new Dimension(500,150));
		
		img.setBorder(tborder);
		pan.setBorder(tborder);
		comment.setBorder(tborder);
		
		//pan.add(writer);
		pan.add(title);
		
		add(img,BorderLayout.NORTH);
		add(pan,BorderLayout.CENTER);
		add(sc,BorderLayout.SOUTH);
		
	}
	
	public ListSelected(PictureBoardPan parent,String img,String title,String comment,String userID,String userPW) {
		
		
		this.imgIcon = new ImageIcon("C:/Users/Administrator/4weeksWorkout"+img);
		this.title.setText(title);
		this.comment.setText(comment);
		this.userID = userID;
		this.userPW = userPW;
		
		this.setBackground(Color.WHITE);
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		compInit();

		setModal(true);
	}
	
}
