package free;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class CellRenderer extends JLabel implements ListCellRenderer {

	public CellRenderer() {

		this.setOpaque(true);
		this.setPreferredSize(new Dimension(580, 100));
		this.setIconTextGap(5);

	}

	public Component getListCellRendererComponent(JList list, Object value,

			int index, boolean isSelected, boolean cellHasFocus)

	{

		SetRenderer emo = (SetRenderer) value;

		this.setIcon(emo.getImage());

		this.setText(emo.getTitle());

		if (isSelected)

		{
			this.setBackground(Color.GRAY);
			this.setForeground(Color.WHITE);
		} else {
			this.setBackground(Color.WHITE);
			this.setForeground(Color.BLACK);
		}
		return this;
	}


}
