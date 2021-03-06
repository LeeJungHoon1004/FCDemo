package free;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class CellRenderer extends JLabel implements ListCellRenderer {

	public CellRenderer() {

		this.setOpaque(true);
		this.setPreferredSize(new Dimension(580, 100));
		this.setIconTextGap(5);

	}

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus)
	{

		FileList fl = (FileList) value;

		String path = "C:/Users/Administrator/4weeks";
		// 파일 객체 생성
		File file = new File(path);
		// !표를 붙여주어 파일이 존재하지 않는 경우의 조건을 걸어줌
		if (!file.exists()) {
			// 디렉토리 생성 메서드
			file.mkdirs();
			System.out.println("created directory successfully!");
		}
		
		
		ImageIcon icon = new ImageIcon(path + "/" + fl.getFileName());
		Image originImg = icon.getImage();
		Image changedImg= originImg.getScaledInstance(200, 80, Image.SCALE_SMOOTH );
		ImageIcon image = new ImageIcon(changedImg);
		//리스트에 있는 사진 크기 조정.△
		
		this.setIcon(image);
		this.setText(fl.getTitle());

		if (isSelected) {
			this.setBackground(Color.GRAY);
			this.setForeground(Color.WHITE);
		} else {
			this.setBackground(Color.WHITE);
			this.setForeground(Color.BLACK);
		}
		return this;
	}
}
