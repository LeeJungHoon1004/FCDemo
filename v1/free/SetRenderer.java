package free;

import java.awt.Image;

import javax.swing.ImageIcon;

public class SetRenderer {

	private ImageIcon icon;
	private String title;
	private String imagePath;
	private int index;
	private Image originImg;
	private Image changedImg;
	private ImageIcon image;
	
	public SetRenderer(ImageIcon icon, String title) {
		super();
		this.title = title;
		this.icon = icon;
		originImg = icon.getImage();
		changedImg= originImg.getScaledInstance(200, 80, Image.SCALE_SMOOTH );
		image = new ImageIcon(changedImg);
		
	}
	
	public SetRenderer(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public ImageIcon getIconPath() {
		
		icon = new ImageIcon(imagePath);
		originImg = icon.getImage();
		changedImg= originImg.getScaledInstance(200, 80, Image.SCALE_SMOOTH );
		image = new ImageIcon(changedImg);
		
		return image;
	}
	
	
	public ImageIcon getIcon() {
		return icon;
	}
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Image getOriginImg() {
		return originImg;
	}
	public void setOriginImg(Image originImg) {
		this.originImg = originImg;
	}
	public Image getChangedImg() {
		return changedImg;
	}
	public void setChangedImg(Image changedImg) {
		this.changedImg = changedImg;
	}
	public ImageIcon getImage() {
		return image;
	}
	public void setImage(ImageIcon image) {
		this.image = image;
	}
	
	

}
