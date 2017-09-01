package teamP_0901;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.URI;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;

public class VideoPan extends JPanel {

		
	private TitledBorder yoga = new TitledBorder("");
	private TitledBorder stretching = new TitledBorder("");
	private TitledBorder miley = new TitledBorder("");
	private TitledBorder dance = new TitledBorder("");
	private TitledBorder smi = new TitledBorder("");
//===========================================================	
	private ImageIcon imgE1 = new ImageIcon("KakaoTalk_20170820_180152201.jpg");
	private JLabel encourage = new JLabel(imgE1);
	private JButton renew = new JButton("갱신");
//===========================================================
	private JPanel healthPan = new JPanel();
	private JPanel yogaPan = new JPanel();
	private JPanel stretchPan = new JPanel();
	private JPanel mileyPan = new JPanel();
	private JPanel dancePan = new JPanel();
	private JPanel smiPan = new JPanel();
	
	private JScrollPane healthSc = new JScrollPane(healthPan);
	private JScrollPane yogaSc = new JScrollPane(yogaPan);
	private JScrollPane stretchSc = new JScrollPane(stretchPan);
	private JScrollPane mileySc = new JScrollPane(mileyPan);
	private JScrollPane danceSc = new JScrollPane(dancePan);
	private JScrollPane smiSc = new JScrollPane(smiPan);
//===========================================================	
	private Socket client;
	private DataInputStream dis;
	private DataOutputStream dos;
	private ObjectInputStream ois;
	private FileOutputStream fos;
	private BufferedOutputStream bos;
//===========================================================	
	private ImageIcon ic1;
	private ImageIcon ic2 ;
	private ImageIcon ic3 ;
	private ImageIcon ic4 ;
	private ImageIcon ic5 ;
	private ImageIcon ic6 ;
	private ImageIcon ic7 ;
	private ImageIcon ic8 ;
	private ImageIcon ic9 ;
	private ImageIcon ic10 ;
	private ImageIcon ic11 ;
	private ImageIcon ic12 ;
	private ImageIcon ic13 ;
	private ImageIcon ic14;
	private ImageIcon ic15;
	private ImageIcon ic16;
	private ImageIcon ic17;
	private ImageIcon ic18;
	private ImageIcon ic19 ;
	private ImageIcon ic20;
	private ImageIcon ic21 ;
	private ImageIcon ic22;
	private ImageIcon ic23;
	private ImageIcon ic24;
	private ImageIcon ic25 ;
	
	private JButton b1 ;
	private JButton b2 ;
	private JButton b3;
	private JButton b4;
	private JButton b5 ;
	private JButton b6;
	private JButton b7;
	private JButton b8 ;
	private JButton b9 ;
	private JButton b10;
	private JButton b11 ;
	private JButton b12;
	private JButton b13 ;
	private JButton b14;
	private JButton b15 ;
	private JButton b16 ;
	private JButton b17;
	private JButton b18 ;
	private JButton b19 ;
	private JButton b20 ;
	private JButton b21;
	private JButton b22 ;
	private JButton b23 ;
	private JButton b24 ;
	private JButton b25 ;
	
	
	private String[] urls = null;
	private String[] fileNames = null;
	int[] fileSize = null;
	byte[] filecontents = null;	
	private String path = "C:/Users/Administrator/4weeksWorkout/";
	private String[] imgpath;
	
	
	
	public void insertImage() {
		
		unmarsharlling();
		
		ic1 = new ImageIcon(imgpath[0]);
		ic2 = new ImageIcon(imgpath[1]);
		ic3 = new ImageIcon(imgpath[2]);
		ic4 = new ImageIcon(imgpath[3]);
		ic5 = new ImageIcon(imgpath[4]);
		ic6 = new ImageIcon(imgpath[5]);
		ic7 = new ImageIcon(imgpath[6]);
		ic8 = new ImageIcon(imgpath[7]);
		ic9 = new ImageIcon(imgpath[8]);
		ic10 = new ImageIcon(imgpath[9]);
		ic11 = new ImageIcon(imgpath[10]);
		ic12 = new ImageIcon(imgpath[11]);
		ic13 = new ImageIcon(imgpath[12]);
		ic14 = new ImageIcon(imgpath[13]);
		ic15 = new ImageIcon(imgpath[14]);
		ic16 = new ImageIcon(imgpath[15]);
		ic17 = new ImageIcon(imgpath[16]);
		ic18 = new ImageIcon(imgpath[17]);
		ic19 = new ImageIcon(imgpath[18]);
		ic20 = new ImageIcon(imgpath[19]);
		ic21 = new ImageIcon(imgpath[20]);
		ic22 = new ImageIcon(imgpath[21]);
		ic23 = new ImageIcon(imgpath[22]);
		ic24 = new ImageIcon(imgpath[23]);
		ic25 = new ImageIcon(imgpath[24]);
		
		b1 = new JButton(ic1);
		b2 = new JButton(ic2);
		b3 = new JButton(ic3);
		b4 = new JButton(ic4);
		b5 = new JButton(ic5);
		b6 = new JButton(ic6);
		b7 = new JButton(ic7);
		b8 = new JButton(ic8);
		b9 = new JButton(ic9);
		b10 = new JButton(ic10);
		b11 = new JButton(ic11);
		b12 = new JButton(ic12);
		b13 = new JButton(ic13);
		b14 = new JButton(ic14);
		b15 = new JButton(ic15);
		b16 = new JButton(ic16);
		b17 = new JButton(ic17);
		b18 = new JButton(ic18);
		b19 = new JButton(ic19);
		b20 = new JButton(ic20);
		b21 = new JButton(ic21);
		b22 = new JButton(ic22);
		b23 = new JButton(ic23);
		b24 = new JButton(ic24);
		b25 = new JButton(ic25);
		
	}
	
	
	public void unmarsharlling() {
		try {
			
			dos.writeUTF("비디오패널 갱신");
			
			ois = new ObjectInputStream(client.getInputStream());
			
			ArrayList < VideoFileList> vflList = new ArrayList < VideoFileList> ();
			vflList =(ArrayList)ois.readObject();
			
			for(int i = 0 ; i< vflList.size() ; i++) {
			
			urls[i] = vflList.get(i).getUrl();
			fileNames[i] = vflList.get(i).getFilename();
			fileSize[i] = vflList.get(i).getFileSize();
			filecontents =vflList.get(i).getFilecontents();
			imgpath[i] = path+fileNames[i];
			
			System.out.println(urls[i]);
			System.out.println(fileNames[i]);
			System.out.println(fileSize[i]);
			System.out.println(filecontents);
			System.out.println(imgpath[i]);
			
			}
			//배열에 데이터 넣기완료
			System.out.println("배열에 데이터 넣기 완료");
			
			File f = new File("");
			fos = new FileOutputStream(f);
			bos = new BufferedOutputStream(fos);
			dos = new DataOutputStream(bos);
			dos.write(filecontents);
			dos.flush();
			
			System.out.println("비디오패널 언마셜링 성공");
			
			dos.close();
		}catch(Exception e) {
			System.out.println("비디오패널 언마셜링 실패");
		}
	}
	
	
	
	public void compInit() {

		setLayout(new GridLayout(8, 1));

		healthPan.setBackground(Color.white);
		yogaPan.setBackground(Color.white);
		stretchPan.setBackground(Color.white);
		mileyPan.setBackground(Color.white);
		dancePan.setBackground(Color.white);
		smiPan.setBackground(Color.white);
		
		b1.setPreferredSize(new Dimension(200, 150));
		b2.setPreferredSize(new Dimension(200, 150));
		b3 .setPreferredSize(new Dimension(200, 150));
		b4.setPreferredSize(new Dimension(200, 150));
		b5.setPreferredSize(new Dimension(200, 150));
		b6.setPreferredSize(new Dimension(200, 150));
		b7.setPreferredSize(new Dimension(200, 150));
		b8.setPreferredSize(new Dimension(200, 150));
		b9.setPreferredSize(new Dimension(200, 150));
		b10.setPreferredSize(new Dimension(200, 150));
		b11.setPreferredSize(new Dimension(200, 150));
		b12.setPreferredSize(new Dimension(200, 150));
		b13.setPreferredSize(new Dimension(200, 150));
		b14.setPreferredSize(new Dimension(200, 150));
		b15.setPreferredSize(new Dimension(200, 150));
		b16.setPreferredSize(new Dimension(200, 150));
		b17.setPreferredSize(new Dimension(200, 150));
		b18.setPreferredSize(new Dimension(200, 150));
		b19.setPreferredSize(new Dimension(200, 150));
		b20.setPreferredSize(new Dimension(200, 150));
		b21.setPreferredSize(new Dimension(200, 150));
		b22.setPreferredSize(new Dimension(200, 150));
		b23.setPreferredSize(new Dimension(200, 150));
		b24.setPreferredSize(new Dimension(200, 150));
		b25.setPreferredSize(new Dimension(200, 150));
		
		
		renew.setPreferredSize(new Dimension(100,70));
		

		add(encourage);
		
		yogaPan.setBackground(Color.white);
		yogaPan.setBorder(yoga);
		yogaPan.add(b1);
		yogaPan.add(b2);
		yogaPan.add(b3);
		yogaPan.add(b4);
		yogaPan.add(b5);
		add(yogaSc);

		stretchPan.setBackground(Color.white);
		stretchPan.setBorder(stretching);
		stretchPan.add(b6);
		stretchPan.add(b7);
		stretchPan.add(b8);
		stretchPan.add(b9);
		stretchPan.add(b10);
		add(stretchSc);

		mileyPan.setBackground(Color.white);
		mileyPan.setBorder(miley);
		mileyPan.add(b11);
		mileyPan.add(b12);
		mileyPan.add(b13);
		mileyPan.add(b14);
		mileyPan.add(b15);
		add(mileySc);

		dancePan.setBackground(Color.white);
		dancePan.setBorder(dance);
		dancePan.add(b16);
		dancePan.add(b17);
		dancePan.add(b18);
		dancePan.add(b19);
		dancePan.add(b20);
		add(danceSc);
		
		smiPan.setBackground(Color.white);
		smiPan.setBorder(smi);
		smiPan.add(b21);
		smiPan.add(b22);
		smiPan.add(b23);
		smiPan.add(b24);
		smiPan.add(b25);
		add(smiSc);
		
		add(renew);
	}
	
	public void eventInitYoga() {
		
		renew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertImage();
				//△언마셜링 메소드도 포함되어있음
				//△언마셜링에 서버시그널 포함.
			}
		});
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						Desktop d = Desktop.getDesktop();
						d.browse(new URI(urls[0]));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			}
		});b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[1]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}
	});
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						Desktop d = Desktop.getDesktop();
						d.browse(new URI(urls[2]));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			}
		});
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						Desktop d = Desktop.getDesktop();
						d.browse(new URI(urls[3]));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			}
		});
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						Desktop d = Desktop.getDesktop();
						d.browse(new URI(urls[4]));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			}
		});
	}//end
	
	public void eventInitStretching() {
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[5]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[6]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		b8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[7]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		b9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[8]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		b10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[9]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public void eventInitMiley() {
		b11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[10]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		b12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[11]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		b13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[12]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		b14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[13]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		b15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[14]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
	}//end

	public void eventInitDance() {
		b16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[15]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		b17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[16]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		b18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[17]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		b19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[18]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		b20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[19]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public void eventInitSmi() {

		b21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						try {
							Desktop d = Desktop.getDesktop();
							d.browse(new URI(urls[20]));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
			}
		});
		b22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
						try {
							Desktop d = Desktop.getDesktop();
							d.browse(new URI(urls[21]));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
			}
		});
		b23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
						try {
							Desktop d = Desktop.getDesktop();
							d.browse(new URI(urls[22]));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
			}
		});
		b24.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
						try {
							Desktop d = Desktop.getDesktop();
							d.browse(new URI(urls[23]));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
			}
		});
		b25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						try {
							Desktop d = Desktop.getDesktop();
							d.browse(new URI(urls[24]));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
			}
		});
	}
	
	public VideoPan(Socket client,DataInputStream dis,DataOutputStream dos) {
		
		this.client = client;
		this.dis = dis;
		this.dos = dos;
		
		insertImage();
		//△언마셜링 메소드도 포함되어있음
		//△언마셜링에 서버시그널 포함.
		
		
		this.setBackground(Color.white);
		
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
		
		compInit();
		
		eventInitYoga();
		eventInitStretching();
		eventInitMiley();
		eventInitDance();
		eventInitSmi();
		
	}

}
