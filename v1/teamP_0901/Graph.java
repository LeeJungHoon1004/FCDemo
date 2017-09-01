package teamP_0901;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Graph {
	public static void main(String args[]) {
		JFrame frame = new JFrame("몸무게");
		frame.setLocation(500, 200);
		frame.setPreferredSize(new Dimension(920, 350));
		Container contentPane = frame.getContentPane();

		DrawingPanel drawingPanel = new DrawingPanel();
		contentPane.add(drawingPanel, BorderLayout.CENTER);
		
		// 그래프를 그릴 패널

		JPanel controlPanel = new JPanel();
		
		JTextField text1 = new JTextField(3);
		JTextField text2 = new JTextField(3);
		JTextField text3 = new JTextField(3);
		JTextField text4 = new JTextField(3);
		JTextField text5 = new JTextField(3);
		JTextField text6 = new JTextField(3);
		JTextField text7 = new JTextField(3);
		JTextField text8 = new JTextField(3);
		JTextField text9 = new JTextField(3);
		JTextField text10 = new JTextField(3);
		JTextField text11 = new JTextField(3);
		JTextField text12 = new JTextField(3);

		
		JButton button1 = new JButton("입력");
		JButton button2 = new JButton("저장");

		
		controlPanel.add(new JLabel("1월"));
		controlPanel.add(text1);
		controlPanel.add(new JLabel("2월"));
		controlPanel.add(text2);
		controlPanel.add(new JLabel("3월"));
		controlPanel.add(text3);
		controlPanel.add(new JLabel("4월"));
		controlPanel.add(text4);
		controlPanel.add(new JLabel("5월"));
		controlPanel.add(text5);
		controlPanel.add(new JLabel("6월"));
		controlPanel.add(text6);
		controlPanel.add(new JLabel("7월"));
		controlPanel.add(text7);
		controlPanel.add(new JLabel("8월"));
		controlPanel.add(text8);
		controlPanel.add(new JLabel("9월"));
		controlPanel.add(text9);
		controlPanel.add(new JLabel("10월"));
		controlPanel.add(text10);
		controlPanel.add(new JLabel("11월"));
		controlPanel.add(text11);
		controlPanel.add(new JLabel("12월"));
		controlPanel.add(text12);
		
		
		controlPanel.add(button1);
	//	controlPanel.add(button2);

		
		contentPane.add(controlPanel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		button1.addActionListener(new DrawActionListener(text1, text2, text3, text4, text5, text6, text7,text8,text9,text10,text11,text12, drawingPanel));
		
		// "입력" 버튼을 눌렀을때 작동 할 리스터등록
		frame.pack();
		frame.setVisible(true);
	}
}
// 그래피를 그리는 패널 클래스

class DrawingPanel extends JPanel {
	int January, February, March, April, May, June, July, August, September, October, November, December ;

	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawLine(50, 250, 850, 250);
		for (int cnt = 1; cnt < 11; cnt++) {
			g.drawString(cnt * 10 + "", 25, 255 - 20 * cnt);
			g.drawLine(50, 250 - 20 * cnt, 850, 250 - 20 * cnt);
		}
		g.drawLine(50, 20, 50, 250);
		g.drawString("1월", 70, 270);
		g.drawString("2월", 140, 270);
		g.drawString("3월", 210, 270);
		g.drawString("4월", 280, 270);
		g.drawString("5월", 350, 270);
		g.drawString("6월", 420, 270);
		g.drawString("7월", 490, 270);
		g.drawString("8월", 560, 270);
		g.drawString("9월", 630, 270);
		g.drawString("10월",700, 270);
		g.drawString("11월",770, 270);
		g.drawString("12월",840, 270);
		g.setColor(Color.RED);
		if (January >= 0)
			g.fillRect(70, 250 - January * 2, 10, January * 2);
		if (February > 0)
			g.fillRect(140, 250 - February * 2, 10, February * 2);
		if (March > 0)
			g.fillRect(210, 250 - March * 2, 10, March * 2);
		if (April > 0)
			g.fillRect(280, 250 - April * 2, 10, April * 2);
		if (May > 0)
			g.fillRect(350, 250 - May * 2, 10, May * 2);
		if (June > 0)
			g.fillRect(420, 250 - June * 2, 10, June * 2);
		if (July > 0)
			g.fillRect(490, 250 - July * 2, 10, July * 2);
		if (August > 0)
			g.fillRect(560, 250 - August * 2, 10, August * 2);
		if (September > 0)
			g.fillRect(630, 250 - September * 2, 10, September * 2);
		if (October > 0)
			g.fillRect(700, 250 - October * 2, 10, October * 2);
		if (November > 0)
			g.fillRect(770, 250 - November * 2, 10, November * 2);
		if (December > 0)
			g.fillRect(840, 250 - December * 2, 10, December * 2);
	}

	void setScores(int January, int February, int March, int April, int May, int June, int July, int August, int September, int October, int November,int December) {
		this.January = January;
		this.February = February;
		this.March = March;
		this.April = April;
		this.May = May;
		this.June = June;
		this.July = July;
		
		this.August = August;
		this.September = September;
		this.October = October;
		this.November = November;
		this.December = December;
	}
}

// 버튼 눌렀을때 동작하는 리스너
class DrawActionListener implements ActionListener {
	JTextField text1;
	JTextField text2;
	JTextField text3;
	JTextField text4;
	JTextField text5;
	JTextField text6;
	JTextField text7;
	JTextField text8;
	JTextField text9;
	JTextField text10;
	JTextField text11;
	JTextField text12;
	DrawingPanel drawingPanel;

	DrawActionListener(JTextField text1, JTextField text2, JTextField text3, JTextField text4, JTextField text5, JTextField text6, JTextField text7 ,JTextField text8,JTextField text9, JTextField text10, JTextField text11, JTextField text12, DrawingPanel drawingPanel) 
	{		
		this.text1 = text1;
		this.text2 = text2;
		this.text3 = text3;
		this.text4 = text4;
		this.text5 = text5;
		this.text6 = text6;
		this.text7 = text7;
		this.text8 = text8;
		this.text9 = text9;
		this.text10 = text10;
		this.text11 = text11;
		this.text12 = text12;
		
		this.drawingPanel = drawingPanel;
	}

	public void actionPerformed(ActionEvent e) {  //텍스트 비워도 작동하게하는 기능
//  1	
		int January;
		if( text1.getText().equals("")) {
			January =0;
		} 
		else {
			January = Integer.parseInt( text1.getText());
		}
//	2	----------------------------------------------------------		
		int February;
		if( text2.getText().equals("")) {
			February =0;
		} 
		else {
			February = Integer.parseInt( text2.getText());
		}
//	3	----------------------------------------------------------		
		int March;
		if( text3.getText().equals("")) {
			March =0;
		} 
		else {
			March = Integer.parseInt( text3.getText());
		}
//	4	----------------------------------------------------------	
		int April;
		if( text4.getText().equals("")) {
			April =0;
		} 
		else {
			April = Integer.parseInt( text4.getText());
		}
//	5   ----------------------------------------------------------
		int May;
		if( text5.getText().equals("")) {
			May =0;
		} 
		else {
			May = Integer.parseInt( text5.getText());
		}
//	6	----------------------------------------------------------
		int June;
		if( text6.getText().equals("")) {
			June =0;
		} 
		else {
			June = Integer.parseInt( text6.getText());
		}
		
//	7	----------------------------------------------------------
		int July;
		if( text7.getText().equals("")) {
			July =0;
		} 
		else {
			July = Integer.parseInt( text7.getText());
		}
//	8	----------------------------------------------------------
		int August;
		if( text8.getText().equals("")) {
			August =0;
		} 
		else {
			August = Integer.parseInt( text8.getText());
		}
//	9	----------------------------------------------------------
		int September ;
		if( text9.getText().equals("")) {
			September  =0;
		} 
		else {
			September  = Integer.parseInt( text9.getText());
		}
//	10	----------------------------------------------------------
		int October ;
		if( text10.getText().equals("")) {
			October  =0;
		} 
		else {
			October  = Integer.parseInt( text10.getText());
		}
//	11	----------------------------------------------------------
		int November ;
		if( text11.getText().equals("")) {
			November  =0;
		} 
		else {
			November  = Integer.parseInt( text11.getText());
		}
//	12	----------------------------------------------------------
		int December;
		if( text12.getText().equals("")) {
			December =0;
		} 
		else {
			December = Integer.parseInt( text12.getText());
		}

		drawingPanel.setScores(January,	February, March, April,	May, June, July, August, September,	October, November, December);
		drawingPanel.repaint();

		}

	}