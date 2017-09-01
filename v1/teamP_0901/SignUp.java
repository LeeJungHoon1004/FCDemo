package teamP_0901;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;

public class SignUp extends JDialog {
	
	private SignUp self = this;
	private BasicShape parent ;
	
	private  TitledBorder tborder = new TitledBorder("");
	private JLabel name = new JLabel("이름 : ");
	private JTextField inputName = new JTextField();
	private JLabel gender = new JLabel("성별 : ");
	private JRadioButton male = new JRadioButton("남자");
	private JRadioButton female = new JRadioButton("여자");

	private JLabel id = new JLabel("ID : ");
	private JTextField inputId = new JTextField();
	private JLabel pw = new JLabel("PW : ");
	private JPasswordField inputPw = new JPasswordField();
	private JLabel pwcheck = new JLabel("PW확인 : ");
	private JPasswordField inputCheck = new JPasswordField();
	private JButton cancel = new JButton("닫기");
	private JButton sign = new JButton("가입하기");
	private JPanel radioPan = new JPanel();
	private ButtonGroup group = new ButtonGroup();
	private JPanel panName = new JPanel();
	private JPanel panGender = new JPanel();
	private JPanel panID = new JPanel();
	private JPanel panPW = new JPanel();
	private JPanel panCheck = new JPanel();
	private JPanel panButtons = new JPanel();
	// =========================UI관련△=================================
	private Socket client;
	private DataOutputStream dos;
	private DataInputStream dis;

	
	public void compInit() {
		
		//setBackground(Color.white);
		setLayout(new GridBagLayout());
		
		panName.setBorder(tborder);
		panGender.setBorder(tborder);
		panID.setBorder(tborder);
		panPW.setBorder(tborder);
		panCheck.setBorder(tborder);
		
		GridBagConstraints c = new GridBagConstraints();
		name.setPreferredSize(new Dimension(100, 40));
		inputName.setPreferredSize(new Dimension(200, 40));
		gender.setPreferredSize(new Dimension(100, 40));
		radioPan.setPreferredSize(new Dimension(200, 40));
		id.setPreferredSize(new Dimension(100, 40));
		inputId.setPreferredSize(new Dimension(200, 40));
		pw.setPreferredSize(new Dimension(100, 40));
		inputPw.setPreferredSize(new Dimension(200, 40));
		pwcheck.setPreferredSize(new Dimension(100, 40));
		inputCheck.setPreferredSize(new Dimension(200, 40));
		cancel.setPreferredSize(new Dimension(75, 35));
		sign.setPreferredSize(new Dimension(100, 35));
		c.insets = new Insets(5, 0, 0, 0);
		panName.add(name);
		panName.add(inputName);
		c.gridy = 1;
		c.gridx = 1;
		add(panName, c);
		panGender.add(gender);
		group.add(male);
		group.add(female);
		radioPan.add(male);
		radioPan.add(female);
		panGender.add(radioPan);
		c.gridy = 2;
		c.gridx = 1;
		add(panGender, c);
		panID.add(id);
		panID.add(inputId);
		c.gridy = 3;
		c.gridx = 1;
		add(panID, c);
		panPW.add(pw);
		panPW.add(inputPw);
		c.gridy = 4;
		c.gridx = 1;
		add(panPW, c);
		panCheck.add(pwcheck);
		panCheck.add(inputCheck);
		c.gridy = 5;
		c.gridx = 1;
		add(panCheck, c);
		panButtons.add(cancel);
		panButtons.add(sign);
		c.gridy = 6;
		c.gridx = 1;
		add(panButtons, c);
		
		
	}// end

	public void eventInit() {
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		sign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String userName = inputName.getText();
				String userID = inputId.getText();
				String userPW = inputPw.getText();
				String userCheck = inputCheck.getText();
				
				//선택된 남자,여자의 성별을 if문으로 분기하여 유효성검사 필요함. **미구현**
				String malegender ;
				String femalegender;
				if(male.isSelected()){
					 malegender = male.getLabel(); //남자
					System.out.println(malegender);
				}
				else if(female.isSelected()){
					 femalegender = female.getLabel(); //여자
					System.out.println(femalegender);
				}
					
				// 유효성 검사 1. 빈칸체크
			//	System.out.println(userName.isEmpty());
				
				
				if ( userName.isEmpty() || userID.isEmpty() || userPW.isEmpty() || userCheck.isEmpty() ) {
					
						
						JOptionPane.showMessageDialog(null, "빈칸이 없게 해주세요");
						if (male.isSelected() == false && female.isSelected() == false){
							// 공란일때		
							JOptionPane.showMessageDialog(null,"성별란을 체크해주세요.");
							return;
						}
						return;
					
				}

				// 유효성 검사2. 비번과 비번확인번호가 일치하는지 확인
				if (userCheck.equals(userPW)) {
					//비번과 비번확인번호가 일치할때 -> 소켓생성후 서버쪽에 데이터 송신
					try {
						// client = new ("", 40000);
//						client = new Socket("127.0.0.1", 40000);
//						dos = new DataOutputStream(client.getOutputStream());
//						dis = new DataInputStream(client.getInputStream());
						
						client = self.parent.getClient();
						dos =self.parent.getDos(); 
					//	dos = new DataOutputStream(client.getOutputStream());
						dis =self.parent.getDis();
					//	dis = new DataInputStream(client.getInputStream());
						
						
						dos.writeUTF("회원가입");
						//if( 남자 성별 선택) //서버 보낼것 String 이름 , String id ,String pw , String gender 
						
						System.out.println("회원가입시도");
					} catch (Exception e1) {
						System.out.println("초기연결실패");
					}
					
					try {//userName,gender,id,pw 순서대로보냅니다. 
					
						dos.writeUTF(userName);
						if (male.isSelected()) {
							String userGender = male.getText();
							dos.writeUTF(userGender);
						} else if (female.isSelected()) {
							String userGender = female.getText();
							dos.writeUTF(userGender);
						}
						dos.writeUTF(userID);
						dos.writeUTF(userPW);
						
				//		self.dispose();
						System.out.println("회원가입 데이터 보내기 성공");
						
					
					} 
					catch (Exception e1) {
						System.out.println("회원가입 데이터 보내기 실패");
					}
				} else if (!userCheck.equals(userPW)) {
				//	JOptionPane.showMessageDialog(null, "회원가입이 비정상 처리 되었습니다.");
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지않습니다.");
					return;
				}
				
				

				try {
					String response = dis.readUTF();
					System.out.println(response);
					if (response.equals("회원가입성공")) {
						JOptionPane.showMessageDialog(self, "회원가입 되셨습니다.");
					} else if(response.equals("회원가입실패")){
						JOptionPane.showMessageDialog(null, "이미 존재하는 ID입니다.");
					}
				//	System.out.println("데이터 받기 성공!");
				} catch (Exception e2) {
				//	System.out.println("데이터 받기 실패");
				}
			
			
			}
			
		});
	}// end

	public SignUp(BasicShape parent) {
		//부모의 주소를 받아서 parent변수에 담아주고 
		//필요한 상황이 생겼을떄 parent.getdis / parent.getdos 로 불러사용.
		this.parent = parent;
		
		setTitle("회원가입");
		setSize(500, 500);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		compInit();
		this.eventInit();
		setModal(true);

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
	}// end

	

}// class end
	// <a target="_blank"
	// href="https://m.blog.naver.com/PostView.nhn?blogId=ndb796&amp;logNo=220547775994&amp;proxyReferer=https%3A%2F%2Fwww.google.co.kr%2F"
	// class="tx-link">https://m.blog.naver.com/PostView.nhn?blogId=ndb796&amp;logNo=220547775994&amp;proxyReferer=https://www.google.co.kr/</a>;;
	// 체크박스&amp;라디오버튼 비교
	// ****라디오 버튼은 버튼그룹에 넣어야지 하나만 선탣된다.****
	// <a target="_blank"
	// href="http://blog.naver.com/PostView.nhn?blogId=bestheroz&amp;logNo=103957162"
	// class="tx-link">http://blog.naver.com/PostView.nhn?blogId=bestheroz&amp;logNo=103957162</a>;;
	// 입력값에 문제 있을시에 경고문 보여준후 다시 입력할수 있도록 focus를 맞출수 있다.△