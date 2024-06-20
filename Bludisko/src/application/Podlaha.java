package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Podlaha extends ImageView {
	Image floor;

	public Podlaha(double x, double y, int velkost) {
		super();
		setLayoutX(x);setLayoutY(y);
		floor=new Image("floor.bmp", velkost, velkost, false, false);
		setImage(floor);
	}
}
