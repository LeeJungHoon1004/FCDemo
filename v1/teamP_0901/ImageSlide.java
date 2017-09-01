package teamP_0901;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//javafx example jar file  ..
public class ImageSlide extends JPanel {
	JLabel labelpicture = new JLabel();
	JLabel labelpicture2 = new JLabel();
	JLabel labelpicture3 = new JLabel();
	Timer tm;
	int x = 0;
	// Images Path In Array
	String[] list = { "img8.jpg", // 0
			"img18.jpg", // 1
			"img14.jpg"// 2
	};
	
	String[] list2 = {"img13.jpg","img17.jpg","img20.jpg"};
	String[] list3 = {"img19.jpg","img1.jpg","img4.jpg"};
	// 생성자
	public ImageSlide() {
		this.setBackground(Color.WHITE);
		/*
		 * Frame에 Background 색 입히는법. ContentPane Frame에는 5계층으로 팬이 붙어있는형태로 만들어져있다. 그중에서
		 * 
		 * 가장 외곽부터에는 glass층 , 컨텐트팬+메뉴바 , 레이어드 팬 , 루트팬 , 프레임 이 있다. 배경색을 입힐때는 컨텐트팬을 꺼내서
		 * 
		 * .setBackground 에 컬러를 입힌다.
		 * 
		 */
		labelpicture.setBounds(20,25, 500, 850);
		labelpicture2.setBounds(530, 25, 420, 500);
		labelpicture3.setBounds(530, 530, 420, 345);
		// Call The Function SetImageSize
		SetImageSize(2);
		// set a timer
		// Timer 클래스 이용- 쓰레드 입니다.
		//
		
		tm = new Timer(1500, new ActionListener() { // delay값
			@Override
			public void actionPerformed(ActionEvent e) {
				SetImageSize(x);
				x += 1;
				if (x >= list.length)
					x = 0;
				if (x >= list2.length)
					x = 0;
				if (x >= list3.length)
					x = 0;
			}
			
		});
		add(labelpicture);
		add(labelpicture2);
		add(labelpicture3);
		tm.start();
		setLayout(null);
		// getContentPane().setBackground(Color.decode("#bdb67b"));
		// this.setBackground(Color.decode("#bdb67b"));
	}

	// create a function to resize the image
	public void SetImageSize(int i) {
		// 이미지 아이콘에 경로에 잡혀있는 이미지들을 넣어서 등록한다.
		// img라는 객체에 아이콘에 경로에 잡혀있는 이미지들을 get하여 넣고
		// newimg 로 재탄생 시킨다 . ( String 파일네임을 인자값으로넣음)
		ImageIcon icon = new ImageIcon(list[i]);
		ImageIcon icon2 = new ImageIcon(list2[i]);
		ImageIcon icon3 = new ImageIcon(list3[i]);
		// System.out.println(icon.getIconWidth()); // 이미지의 실제 픽셀 가로길이
		// System.out.println(icon.getIconHeight()); // 이미지의 실제 픽셀 세로길이
		Image img = icon.getImage();
		Image img2 = icon2.getImage();
		Image img3 = icon3.getImage();
		// 위에서 레이블의 크기를 700,300으로 정한대로 아이콘의 이미지크기를 이에맞게 등록함.
		Image newImg = img.getScaledInstance(labelpicture.getWidth(), labelpicture.getHeight(), Image.SCALE_SMOOTH);
		Image newImg2 = img2.getScaledInstance(labelpicture2.getWidth(), labelpicture2.getHeight(), Image.SCALE_SMOOTH);
		Image newImg3 = img3.getScaledInstance(labelpicture3.getWidth(), labelpicture3.getHeight(), Image.SCALE_SMOOTH);
		// System.out.println(labelpicture.getWidth());
		// System.out.println(labelpicture.getHeight());
		// 이미지 아이콘에 수정된 이미지를 넣는다. ( 이미지를 인자값으로넣음)
		ImageIcon newImc = new ImageIcon(newImg);
		ImageIcon newImc2 = new ImageIcon(newImg2);
		ImageIcon newImc3 = new ImageIcon(newImg3);
		// 레이블에 아이콘을 등록한다.
		labelpicture.setIcon(newImc);
		labelpicture2.setIcon(newImc2);
		labelpicture3.setIcon(newImc3);
	}
}