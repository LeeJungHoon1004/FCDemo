package teamP_0901;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

public class ImagePreview extends JComponent implements PropertyChangeListener {

	final static int WIDTH = 150;
	final static int HEIGHT = 100;

	private ImageIcon icon;

	public ImagePreview(JFileChooser fc) {

		fc.addPropertyChangeListener(this);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (icon != null) {
			Graphics2D g2d = (Graphics2D) g;
			Rectangle bounds = new Rectangle(0, 0, icon.getIconWidth(), icon.getIconHeight());
			g.setColor(Color.white);
			g2d.fill(bounds);

			icon.paintIcon(this, g, 0, 0);
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		String propName = e.getPropertyName();

		if (JFileChooser.DIRECTORY_CHANGED_PROPERTY.equals(propName)) {
			icon = null;
			repaint();
			return;
		}

		if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(propName)) {
			File file = (File) e.getNewValue();

			if (file == null) {
				icon = null;
				repaint();
				return;
			}

			icon = new ImageIcon(file.getPath());

			if (icon.getIconWidth() == -1) {
				icon.getImage().flush();
				icon = new ImageIcon(file.getPath());
			}

			if (icon.getIconWidth() > WIDTH)
				icon = new ImageIcon(icon.getImage().getScaledInstance(WIDTH, -1, Image.SCALE_DEFAULT));

			repaint();
		}
	}
}