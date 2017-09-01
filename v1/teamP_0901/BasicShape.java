package teamP_0901;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;

public class BasicShape extends JFrame {

	String receivedComboListData;
	String tmpComboListData[];
	String receiveaction[] = null;
	int tmpcombo1;
	String tmpComboString1;
	int tmpcombo2;
	String tmpComboString2;
	int tmpcombo3;
	String tmpComboString3;
	String tmpComboStringList;
	// ========ComboList Variable====================
	private Socket client;
	private DataOutputStream dos;
	private DataInputStream dis;
	// =======SOCKET========================
	private Container cp = this.getContentPane();
	private JLabel title = new JLabel();


	private Font font = new Font("바탕", Font.ITALIC, 30);
	private JButton homeBt = new JButton("홈");
	//private JButton dailyBt = new JButton("목표");
	private JButton planBt = new JButton("목표");
	private JButton videoBt = new JButton("운동");
	private JButton imgBoardBt = new JButton("커뮤니티");
	private JButton calandarBt = new JButton("캘린더");
	private JButton GraphBt = new JButton("그래프");
	private JPanel category = new JPanel(new GridLayout(5, 1));
	private JPanel titlePan = new JPanel();
	private JPanel sidepan = new JPanel(new GridLayout(5, 1));

	private JLabel passionlabel = new JLabel();

	private Image passionimg = new ImageIcon("끼.jpg").getImage().getScaledInstance(187, 130,
			java.awt.Image.SCALE_SMOOTH);
	private ImageIcon passionicon = new ImageIcon(passionimg);

	// ▽▽▽▽▽▽▽▽▽▽프로필 바뀜▽▽▽▽▽▽▽▽▽▽▽▽
	// 로그아웃 중일때
	private JLabel lbID = new JLabel();
	private JLabel lbPW = new JLabel();



	private Image idimage = new ImageIcon("ID (3).jpg").getImage().getScaledInstance(98, 30,
			java.awt.Image.SCALE_SMOOTH);
	private ImageIcon iconid = new ImageIcon(idimage);

	private Image pwimage = new ImageIcon("PW (2).jpg").getImage().getScaledInstance(98, 30,
			java.awt.Image.SCALE_SMOOTH);
	private ImageIcon iconpw = new ImageIcon(pwimage);
	private JTextField inputID = new JTextField();

	private JPasswordField inputPW = new JPasswordField();
	private JButton membership = new JButton("회원가입");
	private JButton login = new JButton("로그인");
	private JPanel panbox1 = new JPanel(new GridLayout(1, 2));
	private JPanel panbox2 = new JPanel(new GridLayout(1, 2));
	private JPanel panbox3 = new JPanel(new GridLayout(1, 2));
	private JPanel panbox = new JPanel(new GridLayout(3, 1));
	// 로그인 중일때
	private JPanel panboxx = new JPanel(new GridLayout(3, 1));
	private JPanel namePan = new JPanel();
	private JPanel logoutPan = new JPanel();
	private JLabel profilename = new JLabel();
	private JLabel profilePhoto = new JLabel();
	private JButton logout = new JButton("로그아웃");
	private String result;
	// 카드레이아웃 여기있음
	private CardLayout card = new CardLayout();
	private BasicShape self = this;
	private JPanel mainPan = new JPanel(card);
	private JPanel profilePan = new JPanel(card);// 로그인전후 바뀔 프로필패널
	// COMPNENT - homePan
	private Image titleimage = new ImageIcon("타이틀3.jpg").getImage().getScaledInstance(1194, 100,
			java.awt.Image.SCALE_SMOOTH);
	private ImageIcon titleicon = new ImageIcon(titleimage);

	private TitledBorder tborder = new TitledBorder("");
	private JPanel homePan = new JPanel(new GridLayout(1, 1));
	private JScrollPane homeSc = new JScrollPane(homePan);

	private String name = getName();
	private String userID  =null;
	private String userPW  =null;
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPW() {
		return userPW;
	}

	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}

	
	private ImageSlide imgSlide = new ImageSlide();
//	private BMI bmi = new BMI();


	// COMPNENT - dailyPan

	private Dailypan dailyPan = new Dailypan(self);
	private JScrollPane dailySc = new JScrollPane(dailyPan);// 스크롤
	

	private JPanel calandarPan = new JPanel();
	private CalandarPan calandarpan = new CalandarPan();
	private JScrollPane calandarSc = new JScrollPane(calandarpan);

	private JPanel mygoalPan = new JPanel();
	private myGoalPan mygoalpan = new myGoalPan();
	private JScrollPane mygoalPanSc = new JScrollPane(mygoalpan);
	
	// COMPNENT - videoPan
	private JPanel videoPan = new JPanel();
	//private VideoPan video = new VideoPan(client,dis,dos);
	private JScrollPane videoSc = new JScrollPane(videoPan);// 스크롤

	// COMPNENT - imgBoardPan
	private JPanel imgPanel = new JPanel();
	private PictureBoardPan pbp = new PictureBoardPan(client,dis,dos,userID,userPW);
	private JScrollPane picSc = new JScrollPane(imgPanel);// 스크롤

	// COMPNENT - planPan
	private JPanel planPan = new JPanel();
	private PlanPan plan = new PlanPan(self);
	private JScrollPane planSc = new JScrollPane(planPan);// 스크롤


	public Socket getClient() {
		return client;
	}

	public void setClient(Socket client) {
		this.client = client;
	}

	public DataOutputStream getDos() {
		return dos;
	}

	public void setDos(DataOutputStream dos) {
		this.dos = dos;
	}

	public DataInputStream getDis() {
		return dis;
	}

	public void setDis(DataInputStream dis) {
		this.dis = dis;
	}

	public void comp() {
		setLayout(null);
		// 투명

		imgSlide.setPreferredSize(new Dimension(400, 700));
//		bmi.setPreferredSize(new Dimension(672, 800));
//		bmi.setBorder(tborder);
		this.homePan.add(imgSlide);
//		this.homePan.add(bmi);

		//목표(plan) 새로운 목표 패널임 (달력들어간거)
		planPan.setBackground(Color.white);
		this.planPan.add(plan);

		// ---------운동
		videoPan.setBackground(Color.white);
//		this.video.setPreferredSize(new Dimension(965, 500));
		
		

	//	this.videoPan.add(video);
		
		
		
		// ---------커뮤니티
		pbp.setBackground(Color.white);
		imgPanel.setBackground(Color.white);
		this.pbp.setPreferredSize(new Dimension(975, 640));
		this.imgPanel.add(pbp);

		this.lbID.setIcon(iconid);
		this.lbPW.setIcon(iconpw);

		// compInit() - panelCard_StimulsPhoto
		category.setBackground(Color.WHITE);
		sidepan.setBackground(Color.WHITE);
		panbox1.setBackground(Color.WHITE);
		panbox2.setBackground(Color.WHITE);
		panbox3.setBackground(Color.WHITE);
		
		

		category.add(homeBt);
		category.add(planBt);
	//	category.add(dailyBt);
		category.add(videoBt);
		category.add(imgBoardBt);
		category.add(calandarBt);
		category.add(GraphBt);
		
		

		panbox1.add(lbID);
		panbox1.add(inputID);
		panbox2.add(lbPW);
		panbox2.add(inputPW);
		panbox3.add(login);
		panbox3.add(membership);
		panbox.add(panbox1);
		panbox.add(panbox2);
		panbox.add(panbox3);
		// CardLayout이 들어있는 profilePan에 넣음.
		
		profilePan.setBackground(Color.white);
		panboxx.setBackground(Color.white);
		namePan.setBackground(Color.white);
		logoutPan.setBackground(Color.white);
		planPan.setBackground(Color.WHITE);
		imgPanel.setBackground(Color.white);
		
		profilename.setText(name + " 님 환영합니다!");
		namePan.add(profilename);
		logoutPan.add(logout);

		panboxx.add(profilename, BorderLayout.CENTER);
		panboxx.add(logoutPan, BorderLayout.CENTER);
		profilePan.add(panbox, "loginBefore");
		profilePan.add(panboxx, "loginAfter");

		// =======================================================
		// title.setFont(font);
		title.setIcon(titleicon);
		titlePan.add(title);
		sidepan.add(profilePan);
		sidepan.add(category);
		sidepan.add(passionlabel);
		titlePan.setBounds(0, 0, 1194, 100);
		add(titlePan);
		sidepan.setBounds(0, 101, 200, 640);
		add(sidepan);
		// CardLayout들어있는 mainPan에 패널들 넣음
		this.passionlabel.setIcon(passionicon);
		mainPan.add(homeSc, "NamedefaultPane");
		mainPan.add(dailySc, "NamedailyPane");
		mainPan.add(calandarSc, "NamecalandarPane");
		mainPan.add(planSc, "NameplanPane");
		mainPan.add(videoSc, "NamevideoPane");
		mainPan.add(imgPanel, "NameimgBoard"); // 카드로 끼워넣는팬에
		mainPan.add(mygoalPanSc, "NamegoalBoard");

		// 이름을 부여함 .
		// 부여된 이름을 가지고 이벤트 처리부분에서
		// 카드의 이름으로 식별하여 visible함.
		this.setResizable(false);
		this.mainPan.setBounds(200, 99, 1000, 650);
		add(mainPan);

	}
	
	public void receiveDataAfterLogin() {
				
		// 로그인이후시점에서 서버로부터 받는 데이터를 처리합니다.
		// 1.목표 - 캘린더 달성한데이터 Num 값으로 받고 받은데이터는 합계sum 을 만들어서 누적시킨뒤
		// 한달동안 모아진 sum 값을 가지고 보상함.
		
		// 4.동영상url리스트+메타데이터(String)
		// 5.(로그인이후시점에서 서버컴퓨터에서 보내는데이터)업로드한사진 + 코멘트데이터 ( String)
		// 1.콤보리스트데이터 받기.
		try {
			receivedComboListData = dis.readUTF();
			System.out.println("수신한receivedComboListData 값 :" + receivedComboListData);
			// 로그인후 받은 콤보리스트 데이터가 null값이아니면 쪼개서 리스트를 분리한다.
			//수신한데이터가 null이아니면 쪼개서 분리한다.
			//수신한데이터가 null이면 비어있는데이터라고 출력한다.
			if(receivedComboListData !=null) {
				tmpComboListData = receivedComboListData.split(",");
				receiveaction = new String[] { "1.밥먹을때 젓가락만 이용하기 ", "2.운동30분 하기", "3.일어나서 스트레칭 하기",
						"4.집에갈때 계단이용하기 ", "5.스쿼트  30개씩 3세트", "6.플랭크 1분 3세트", "7.저녁안먹기", "8.샤워하며 마사지하기",
						"9.자기전 하늘자전거 5분", "10.일어나서 물한잔 원샷" };

				tmpcombo1 = Integer.parseInt(tmpComboListData[0]);
				tmpComboString1 = receiveaction[tmpcombo1];

				tmpcombo2 = Integer.parseInt(tmpComboListData[1]);
				tmpComboString2 = receiveaction[tmpcombo2];

				tmpcombo3 = Integer.parseInt(tmpComboListData[2]);
				tmpComboString3 = receiveaction[tmpcombo3];

				tmpComboStringList = tmpComboString1 + tmpComboString2 + tmpComboString3;
			}
			else if(receivedComboListData.equals("비어있는ComboList데이터")){
				
				tmpComboStringList ="비어있는ComboList데이터";
			}
		} catch (Exception e1) {
			System.out.println("콤보리스트에서 받는곳에서 에러발생지점.");
			e1.printStackTrace();
		}

		// 2.물컵데이터받기.
		// try {
		// String receicedWaterCupData = dis.readUTF();
		// System.out.println("물컵데이터받기 :" + receicedWaterCupData);
		// } catch (Exception e2) {
		// System.out.println("물컵리스트 받는곳에서 에러발생");
		// e2.printStackTrace();
		// }
		
	}
	
	//서버로 소켓접속
	public void clientConnect() {

		try {
			client = new Socket("127.0.0.1", 40000);
			dos = new DataOutputStream(client.getOutputStream());
			dis = new DataInputStream(client.getInputStream());
			System.out.println("초기연결성공");
		} catch (Exception e1) {
			System.out.println("초기연결실패");
		}

	}// end
	
	//서버 접속후 데이터 수신.
	public void receiveDataBeforeLogin() {
		//Article , URL 데이터 수신
		
		
	}

	public String getResult() {
		// 로그인 버튼
		userID = inputID.getText();
		userPW = inputPW.getText();
		try {
			dos.writeUTF("로그인");//로그인 시그널
			dos.writeUTF(userID);
			dos.writeUTF(userPW);
			System.out.println("데이터보내기 성공");
		} catch (Exception e1) {
			System.out.println("데이터 보내기 실패");
		}
		
		try {
			result = dis.readUTF();
			if (result.equals("로그인성공")) {
				JOptionPane.showMessageDialog(null, "로그인 성공");
				System.out.println("로그인 성공");
				
			} else if (result.equals("로그인실패")) {
				JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다.");
				System.out.println("로그인 실패");
			}

		} catch (Exception e2) {
			e2.printStackTrace();
		}

		return result;
	}// end

	public String getName() {

		try {
			name = dis.readUTF();
		} catch (Exception e1) {
			// System.out.println("홈-프로필 이름데이터받기 실패");
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void eventInit() {
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 소켓생성후 커넥트 , 로그인성공실패 에따른 ui출력
				
				getResult();

				// 서버에 계정 보냄.△△△△△△△
				if (result.equals("로그인성공")) {

					profilename.setText(getName() + " 님 환영합니다!");
					card.show(self.profilePan, "loginAfter");
			//		receiveDataAfterLogin();
					

				} else if (result.equals("로그인실패")) {

					card.show(self.profilePan, "loginBefore");
				}
				// 프로필창 로그인여부에 따라 다름.△△△△△△△

			}
			
		});
		// 로그아웃 버튼
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					
//					dos.close();
//					dis.close();
//					client.close();
//					dos = null;
//					dis = null;
//					client = null;
					self.userID = null;
					self.userPW = null;
					
					System.out.println("성공적으로 소켓종료");
					inputID.setText("");
					inputPW.setText("");
				
				card.show(self.profilePan, "loginBefore");
			}
		});
		// 회원가입 버튼
		membership.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 회원가입창 띄우기
				new SignUp(self).setVisible(true);

			}
		});

		homeBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(self.mainPan, "NamedefaultPane");
			}
		});
		planBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				card.show(self.mainPan, "NameplanPane");

			}

		});
		
		calandarBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				card.show(self.mainPan, "NamecalandarPane");

			}

		});


		// 목표 버튼
//		dailyBt.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				// if() 하루시간이 지난이후시점.
//				System.out.println("나의목표 :" + tmpComboStringList);
//				// else 하루시간이 지나지않은시점. 안나옴.
//				// JOptionPane.showMessageDialog( , );
//
//				// 프로필창 로그인여부에 따라 다름.△△△△△△△
//				card.show(self.mainPan, "NamedailyPane");
//			}
//		});
		// 운동 버튼
		videoBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(self.mainPan, "NamevideoPane");

			}
		});
		// 커뮤니티 버튼
		imgBoardBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PictureBoardPan(client,dis,dos,userID,userPW);
				card.show(self.mainPan, "NameimgBoard");
			}
		});
		
		GraphBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientConnect();
				card.show(self.mainPan, "NamegoalBoard");
			}
		});
		
		// 제목 리스너
		title.addMouseListener(new MouseAdapter() {
			

			// 마우스버튼이 누른뒤 뗄때의 이벤트처리
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				card.show(self.mainPan, "NamedefaultPane");
			}
		});

	}

	public BasicShape() {
		clientConnect();
		setTitle("기본shape테스트");
		// setSize(700, 500);
		setSize(1200, 750);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		cp.setBackground(Color.WHITE);
		comp();
		eventInit();
//		clientConnect();
		receiveDataBeforeLogin();
		setVisible(true);
		
		
		//프로그램의 공통파일 만들기! 
		String path = "C:/Users/Administrator/4weeksWorkout";
		// 파일 객체 생성
		File file = new File(path);
		// !표를 붙여주어 파일이 존재하지 않는 경우의 조건을 걸어줌
		if (!file.exists()) {
			// 디렉토리 생성 메서드
			file.mkdirs();
			System.out.println("created directory successfully!");
		}
		
		
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
// <a target="_blank" href="http://msource.tistory.com/5"
// class="tx-link">http://msource.tistory.com/5</a>;;
// 카드레이아웃 쉬운 설명
// <a target="_blank" href="http://www.w3ii.com/ko/swing/swing_cardlayout.html"
// class="tx-link">http://www.w3ii.com/ko/swing/swing_cardlayout.html</a>;;
// 카드레이아웃 클래스