package teamP_0901;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PlanPan extends JPanel {

	private BasicShape parent ;
	private PlanPan self = this;
	


	
	private Socket client;
	private DataOutputStream dos;
	private DataInputStream dis;

	


	private TitledBorder tborder = new TitledBorder("");
	private Font font = new Font("나무", Font.ITALIC, 20);

	private JPanel panelNorth = new JPanel();
	private JPanel panelSouth = new JPanel();

	private JPanel paneldivide = new JPanel(new GridLayout(1,2));

	private JPanel comboPanel = new JPanel(new BorderLayout()); //큰 부분 콤보패널(보더로 위에는 라벨, 아래는버튼)
	private JPanel combolistPanel = new JPanel(); //중간부분 콤보리스트패널
	private JPanel waterPanel = new JPanel(new BorderLayout()); //큰 부분 워터패널(보더로 위에는 라벨, 아래는버튼)
	private JPanel waterlistPanel = new JPanel(); //중간 물컵 아이콘 들어가는 패널

	private JPanel cupPan = new JPanel(new GridLayout(1, 5));
	private JPanel cupPan2 = new JPanel(new GridLayout(1, 5));

	private BMI bmi = new BMI();



	//멤버
	private JLabel combolabel = new JLabel(" 1.  오늘의 목표를 선택하세요!");
	private JButton selectBt = new JButton("선택완료 >_<");
	private JLabel waterchecklabel = new JLabel(" 2.  물마신 횟수를 체크해보세요! ");
	private JButton buttonUpload = new JButton("체크완료 >_<"); // 체크완료 버튼

	private JLabel goallabel = new JLabel("");
	private JLabel emptylabel = new JLabel("                                           ");
	private JLabel emptylabel2 = new JLabel("                                           ");

	private JLabel waterinformlabel = new JLabel("");



	//컵버튼 생성
	private JButton cupb1 = new JButton();
	private JButton cupb2 = new JButton();
	private JButton cupb3 = new JButton();

	private JButton cupb4 = new JButton();
	private JButton cupb5 = new JButton();
	private JButton cupb6 = new JButton();
	private JButton cupb7 = new JButton();
	private JButton cupb8 = new JButton();
	private JButton cupb9 = new JButton();
	private JButton cupb10 = new JButton();


	//컵 이미지와 아이콘 생성
	private Image goalimg = new ImageIcon("목표.jpg").getImage().getScaledInstance(330, 180,
			java.awt.Image.SCALE_SMOOTH); // 
	private Image waterinformimg = new ImageIcon("waterinform.jpg").getImage().getScaledInstance(300, 300,
			java.awt.Image.SCALE_SMOOTH); 



	private Image cupimage = new ImageIcon("empty2.png").getImage().getScaledInstance(50, 80,
			java.awt.Image.SCALE_SMOOTH); // 빈컵이미지
	private Image fullcupimage = new ImageIcon("full2.png").getImage().getScaledInstance(50, 80,
			java.awt.Image.SCALE_SMOOTH); // 물들어있는컵



	private ImageIcon icongoal = new ImageIcon(goalimg);
	private ImageIcon iconwaterinform = new ImageIcon(waterinformimg);
	private ImageIcon icon1 = new ImageIcon(cupimage);

	private ImageIcon icon2 = new ImageIcon(cupimage);
	private ImageIcon icon3 = new ImageIcon(cupimage);
	private ImageIcon icon4 = new ImageIcon(cupimage);
	private ImageIcon icon5 = new ImageIcon(cupimage);
	private ImageIcon icon6 = new ImageIcon(cupimage);
	private ImageIcon icon7 = new ImageIcon(cupimage);
	private ImageIcon icon8 = new ImageIcon(cupimage);
	private ImageIcon icon9 = new ImageIcon(cupimage);
	private ImageIcon icon10 = new ImageIcon(cupimage);

	private boolean changecup1 = false;
	private boolean changecup2 = false;
	private boolean changecup3 = false;
	private boolean changecup4 = false;
	private boolean changecup5 = false;
	private boolean changecup6 = false;
	private boolean changecup7 = false;
	private boolean changecup8 = false;
	private boolean changecup9 = false;
	private boolean changecup10 = false;

	private JComboBox combolist1;
	private JComboBox combolist2;
	private JComboBox combolist3;

	public void compInit() {

		Object[] action = { "1.밥먹을때 젓가락만 이용하기 ", "2.운동30분 하기", "3.일어나서 스트레칭 하기", 
				"4.집에갈때 계단이용하기 ", "5.스쿼트  30개씩 3세트",
				"6.플랭크 1분 3세트", "7.저녁안먹기", "8.샤워하며 마사지하기", 
				"9.자기전 하늘자전거 5분", "10.일어나서 물한잔 원샷" };


		combolist1 = new JComboBox();
		for (int i = 0; i < action.length; i++) {

			combolist1.addItem(action[i]);

		}
		combolist1.setPreferredSize(new Dimension(320, 50));
		combolist1.setEditable(false);

		Object[] action2 = { "1.밥먹을때 젓가락만 이용하기 ", "2.운동30분 하기", "3.일어나서 스트레칭 하기",
				"4.집에갈때 계단이용하기 ", "5.스쿼트  30개씩 3세트",
				"6.플랭크 1분 3세트", "7.저녁안먹기", "8.샤워하며 마사지하기",
				"9.자기전 하늘자전거 5분", "10.일어나서 물한잔 원샷" };
		// JComboBox combolist2 = new JComboBox(action);
		combolist2 = new JComboBox();
		for (int i = 0; i < action.length; i++) {
			combolist2.addItem(action2[i]);
		}
		combolist2.setPreferredSize(new Dimension(320, 50));
		combolist2.setEditable(false);

		Object[] action3 = { "1.밥먹을때 젓가락만 이용하기 ", "2.운동30분 하기", "3.일어나서 스트레칭 하기", 
				"4.집에갈때 계단이용하기 ", "5.스쿼트  30개씩 3세트",
				"6.플랭크 1분 3세트", "7.저녁안먹기", "8.샤워하며 마사지하기", 
				"9.자기전 하늘자전거 5분", "10.일어나서 물한잔 원샷" };
		// JComboBox combolist3 = new JComboBox(action);
		combolist3 = new JComboBox();
		for (int i = 0; i < action.length; i++) {
			combolist3.addItem(action3[i]);
		}
		combolist3.setPreferredSize(new Dimension(320, 50));
		combolist3.setEditable(false);


		this.add(panelNorth);
		panelNorth.add(paneldivide);
		this.add(panelSouth);
		panelSouth.add(bmi);

		paneldivide.add(comboPanel);
		paneldivide.add(waterPanel);



		comboPanel.add(combolistPanel);
		waterPanel.add(waterlistPanel);



		comboPanel.add(combolabel,BorderLayout.NORTH);
		comboPanel.add(combolistPanel, BorderLayout.CENTER);
		comboPanel.add(selectBt,BorderLayout.SOUTH);


		combolistPanel.add(emptylabel);
		combolistPanel.add(goallabel);
		goallabel.setIcon(icongoal);
		combolistPanel.add(combolist1);
		combolistPanel.add(combolist2);
		combolistPanel.add(combolist3);






		combolabel.setFont(font);
		waterchecklabel.setFont(font);


		comboPanel.setBorder(tborder);
		waterPanel.setBorder(tborder);

		bmi.setPreferredSize(new Dimension(872, 650));
		bmi.setBorder(tborder);


		panelNorth.setBackground(Color.white);
		panelSouth.setBackground(Color.white);
		paneldivide.setBackground(Color.white);
		comboPanel.setBackground(Color.white);
		waterPanel.setBackground(Color.white);
		combolistPanel.setBackground(Color.white);
		waterlistPanel.setBackground(Color.white);


		waterPanel.add(waterchecklabel,BorderLayout.NORTH);
		waterPanel.add(waterlistPanel,BorderLayout.CENTER);
		waterPanel.add(buttonUpload,BorderLayout.SOUTH);


		waterlistPanel.add(emptylabel2);
		waterinformlabel.setIcon(iconwaterinform);
		waterlistPanel.add(waterinformlabel);
		waterlistPanel.add(cupPan);
		waterlistPanel.add(cupPan2);

		cupPan.setPreferredSize(new Dimension(250, 80));
		// cupPan.add(water_inform);

		cupPan.add(cupb1);

		cupPan.add(cupb2);
		cupPan.add(cupb3);
		cupPan.add(cupb4);
		cupPan.add(cupb5);

		cupPan2.setPreferredSize(new Dimension(250, 80));

		cupPan2.add(cupb6);
		cupPan2.add(cupb7);
		cupPan2.add(cupb8);
		cupPan2.add(cupb9);
		cupPan2.add(cupb10);

		//컵부분 아이콘넣기
		this.cupb1.setIcon(icon1);
		// this.cupb1.setPreferredSize(new Dimension(43, 72));
		this.cupb2.setIcon(icon2);
		// this.cupb2.setPreferredSize(new Dimension(43, 72));
		this.cupb3.setIcon(icon3);
		//this.cupb3.setPreferredSize(new Dimension(43, 72));
		this.cupb4.setIcon(icon4);
		//this.cupb4.setPreferredSize(new Dimension(43, 72));
		this.cupb5.setIcon(icon5);
		//this.cupb5.setPreferredSize(new Dimension(43, 72));
		this.cupb6.setIcon(icon6);
		//this.cupb6.setPreferredSize(new Dimension(43, 72));
		this.cupb7.setIcon(icon7);
		//this.cupb7.setPreferredSize(new Dimension(43, 72));
		this.cupb8.setIcon(icon8);
		//this.cupb8.setPreferredSize(new Dimension(43, 72));
		this.cupb9.setIcon(icon9);
		//this.cupb9.setPreferredSize(new Dimension(43, 72));
		this.cupb10.setIcon(icon10);
		//this.cupb10.setPreferredSize(new Dimension(43, 72));

		paneldivide.setPreferredSize(new Dimension(872,600));
		bmi.setPreferredSize(new Dimension(872, 650));


	}


	public void eventInit() {
		//컵버튼 인식

		cupb1.addMouseListener(new MouseAdapter() {


			// 마우스를 뗏을때의 이벤트 처리입니다.
			@Override
			public void mouseReleased(MouseEvent e) {
				changecup1 = !changecup1;
				if (changecup1 == true) {
					self.icon1.setImage(fullcupimage);
				} else {
					self.icon1.setImage(cupimage);

				}
				self.cupb1.setIcon(icon1);
			}

		});

		cupb2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			// 마우스를 뗏을때의 이벤트 처리입니다.
			@Override
			public void mouseReleased(MouseEvent e) {
				changecup2 = !changecup2;
				if (changecup2 == true) {
					self.icon2.setImage(fullcupimage);
				} else {
					self.icon2.setImage(cupimage);

				}
				self.cupb2.setIcon(icon2);
			}

		});
		cupb3.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				changecup3 = !changecup3;
				if (changecup3 == true) {
					self.icon3.setImage(fullcupimage);
				} else {
					self.icon3.setImage(cupimage);

				}
				self.cupb3.setIcon(icon3);
			}

		});
		cupb4.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				changecup4 = !changecup4;
				if (changecup4 == true) {
					self.icon4.setImage(fullcupimage);
				} else {
					self.icon4.setImage(cupimage);

				}
				self.cupb4.setIcon(icon4);
			}

		});

		cupb5.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				changecup5 = !changecup5;
				if (changecup5 == true) {
					self.icon5.setImage(fullcupimage);
				} else {
					self.icon5.setImage(cupimage);

				}
				self.cupb5.setIcon(icon5);
			}

		});
		cupb6.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				changecup6 = !changecup6;
				if (changecup6 == true) {
					self.icon6.setImage(fullcupimage);
				} else {
					self.icon6.setImage(cupimage);

				}
				self.cupb6.setIcon(icon6);
			}

		});
		cupb7.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				changecup7 = !changecup7;
				if (changecup7 == true) {
					self.icon7.setImage(fullcupimage);
				} else {
					self.icon7.setImage(cupimage);

				}
				self.cupb7.setIcon(icon7);
			}

		});
		cupb8.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				changecup8 = !changecup8;
				if (changecup8 == true) {
					self.icon8.setImage(fullcupimage);
				} else {
					self.icon8.setImage(cupimage);

				}
				self.cupb8.setIcon(icon8);
			}

		});
		cupb9.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				changecup9 = !changecup9;
				if (changecup9 == true) {
					self.icon9.setImage(fullcupimage);
				} else {
					self.icon9.setImage(cupimage);

				}
				self.cupb9.setIcon(icon9);
			}

		});
		cupb10.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				changecup10 = !changecup10;
				if (changecup10 == true) {
					self.icon10.setImage(fullcupimage);
				} else {
					self.icon10.setImage(cupimage);

				}
				self.cupb10.setIcon(icon10);
			}

		});

		selectBt.addActionListener(new ActionListener() {
			//선택완료 버튼 이벤트처리입니다.
			@Override
			public void actionPerformed(ActionEvent e) {

				// 2가지 케이스로 나눈다 . 선택완료 버튼을 누를떄

				// 1.로그인이 된 상태에서 버튼을 누르는경우.
				if (parent.getClient() != null) {

					int Question = JOptionPane.showConfirmDialog(null, "저장하시겠습니까 ? ",
							"저장",JOptionPane.OK_OPTION);
					if (Question == 0) {
						JOptionPane.showMessageDialog(null, "저장되었습니다");
					} 


					else if(Question == 1) {
						JOptionPane.showMessageDialog(null, "취소되었습니다.다시선택해주세요");
						return ;
					}


					// 1-1 유효성검사 combolist 에 등록된 컴포넌트3가지에 대한 유효성 검사
					// a != b , a !=c , b !=c 3가지값이 모두 같지않은경우에 대해서만 값을 통과시킨다.
					if (combolist1.getSelectedIndex() != combolist2.getSelectedIndex()
							&& combolist1.getSelectedIndex() != combolist3.getSelectedIndex()
							&& combolist2.getSelectedIndex() != combolist3.getSelectedIndex()) {



						try {

							dis = parent.getDis();
							dos = parent.getDos();
							dos.writeUTF("하루목표전송");

							System.out.println(combolist1.getSelectedIndex());
							System.out.println(combolist2.getSelectedIndex());
							System.out.println(combolist3.getSelectedIndex());
							String list = combolist1.getSelectedIndex() + "," + combolist2.getSelectedIndex() + ","
									+ combolist3.getSelectedIndex();
							System.out.println(list);
							dos.writeUTF(list);

							System.out.println("하루목표전송 성공");

							System.out.println("combolist데이터 발신 성공");




						} catch (Exception e1) {
							System.out.println("combolist데이터 발신 실패.");
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "목표가 중복됩니다. 다시 선택해주세요.");
						return;
					}

				}

				// 2.로그인을 하지않은 경우에서 버튼을 누르는경우
				else if (parent.getClient() == null) {
					JOptionPane.showMessageDialog(null, "로그인먼저해주세요");
					return;
				}


			}

		});


		buttonUpload.addActionListener(new ActionListener() {
			// 체크완료 버튼에 대한 이벤트처리입니다.
			@Override
			public void actionPerformed(ActionEvent arg0) {


				//예 /아니오 처리.
				//	System.out.println(Boolean.valueOf(changecup1).toString());
				if (parent.getClient() != null) {

					int Question = JOptionPane.showConfirmDialog(null, "저장하시겠습니까 ? ",
							"저장",JOptionPane.OK_OPTION);
					if (Question == 0) {
						JOptionPane.showMessageDialog(null, "저장되었습니다");
					} 


					else if(Question == 1) {
						JOptionPane.showMessageDialog(null, "취소되었습니다.다시선택해주세요");
						return ;
					}

					//parent의 주소를 받아서 input,output 스트림 개방
					dis = parent.getDis();
					dos = parent.getDos();

					//물컵체크에 대한 커맨드 발신.
					try {
						dos.writeUTF("물컵체크");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					String changeCup1 =Boolean.valueOf(changecup1).toString();
					String changeCup2 =Boolean.valueOf(changecup2).toString();
					String changeCup3 =Boolean.valueOf(changecup3).toString();
					String changeCup4 =Boolean.valueOf(changecup4).toString();
					String changeCup5 =Boolean.valueOf(changecup5).toString();
					String changeCup6 =Boolean.valueOf(changecup6).toString();
					String changeCup7 =Boolean.valueOf(changecup7).toString();
					String changeCup8 =Boolean.valueOf(changecup8).toString();
					String changeCup9 =Boolean.valueOf(changecup9).toString();
					String changeCup10 =Boolean.valueOf(changecup10).toString();


					String changeCupList = changeCup1 + "," +changeCup2+ ","+changeCup3+ ","
							+changeCup4+ ","+ changeCup5+ ","+ changeCup6+ 
							","+changeCup7+ ","+changeCup8+ ","+ changeCup9+ ","+changeCup10 ;
					System.out.println("클라이언트에서 서버로 버튼클릭시 보내는changeCupList " + changeCupList);

					try {
						dos.writeUTF(changeCupList);
					} catch (IOException e) {

						e.printStackTrace();
					}



				}

			}
		});
	}


	//생성자
	public PlanPan(BasicShape self) {
		 
		this.setBackground(Color.WHITE);
		this.setLayout(new GridLayout(2,1));
		this.setSize(900, 900);

		this.compInit();
		this.eventInit();
		this.setVisible(true);

	}




}
