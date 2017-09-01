package teamP_0901;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class BMI extends JPanel {

	private  TitledBorder tborder = new TitledBorder("");
	
	private JLabel stature = new JLabel("키 : ");
	private JLabel weight = new JLabel("체중 : ");
	private JLabel gender = new JLabel("성별 : ");
	private JTextField inputStature = new JTextField();
	private JTextField inputWeight = new JTextField();
	private JRadioButton male = new JRadioButton("남자");
	private JRadioButton female = new JRadioButton("여자");
	private ButtonGroup group = new ButtonGroup();
	private JButton resultButton = new JButton("결과");
	private JPanel genderPan = new JPanel();
	private JPanel radioPan = new JPanel(new GridLayout(1, 2));
	// ==============△△△BMI측정 데이터입력UI△△△=========================
	private String name;
	private double result;
	private String grade;
	private ImageIcon img = new ImageIcon("bmi비만도.jpg");
	private JLabel imgResult = new JLabel(img);
	private JLabel result1 = new JLabel();
	private JLabel result2 = new JLabel();
	private JLabel result3 = new JLabel();
	private JPanel resultPan = new JPanel(new GridLayout(1, 2));
	private Font f = new Font("바탕", Font.ITALIC, 21);

	
	//================서버로부터 이름받기=======================
	
	private Socket client;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public void compInit() {
		setLayout(null);
		
		imgResult.setBounds(35, 16, 800, 400);	
		stature.setBounds(112, 430, 44, 40);
		inputStature.setBounds(171,430, 255, 40);
		weight.setBounds(492,430, 56, 40);
		inputWeight.setBounds(562,430,255, 40);
		gender.setBounds(112,480, 60, 50);
		radioPan.setBounds(178,480, 252, 50);
		resultButton.setBounds(650, 480, 84, 40);
		
		add(imgResult);
		add(stature);
		add(inputStature);
		add(weight);
		add(inputWeight);
		add(gender);
		group.add(male);
		group.add(female);
		radioPan.setBackground(Color.white);
		radioPan.add(male);
		radioPan.add(female);
		add(radioPan);

		add(resultButton);
		
		
		
	}// end

	public void eventInit() {
		
		resultButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultBMI();
				gradeBMI();
				
				resultPan.setBounds(100, 530, 720, 100);
				
				result1.setFont(f);
				result2.setFont(f);
				result1.setHorizontalTextPosition(SwingConstants.CENTER);
				result1.setText(name + " 님의 BMI 결과는"+result + " % 이므로 "+grade + " 입니다.");
				
				resultPan.setBorder(tborder);
				resultPan.add(result1);
				
				resultPan.setBackground(new Color(255, 209, 43));
				add(resultPan);
			}
		});
	}
	
	public double resultBMI() {
		double stature = Integer.parseInt(inputStature.getText())*0.1;
		double weight = Double.parseDouble(inputWeight.getText());
		double resultdouble = (weight) / (stature * stature);
		return result = resultdouble*100;
	}// end

	public String gradeBMI() {
		
		if (result < 18.5) {
			grade = "저체중";
		} else if (result >= 18.5 && result <= 24.9) {
			grade = "정상";
		} else if (result >= 25 && result <= 29.9) {
			grade = "과체중";
		} else if (result <= 30 && result <= 34.9) {
			grade = "비만";
		} else if (result > 35) {
			grade = "고도비만";
		}
		return grade;
	}// end

	public String ConnectClient() {
		try {
			client = new Socket("192.168.53.4",40000);
			dis = new DataInputStream(client.getInputStream());
		} catch (Exception e) {
			System.out.println("bmi 초기연결 실패");
		}
		
		try {
			name = dis.readUTF();
		}catch(Exception e1) {
			System.out.println("bmi 이름데이터받기 실패");
		}
		
		return name;
	}
	

	public BMI() {
		this.setBackground(Color.WHITE);
		compInit();
		eventInit();
	}// conductor end
}