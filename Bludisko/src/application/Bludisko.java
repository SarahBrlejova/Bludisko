package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bludisko extends ImageView {
	Image stena;

	public Bludisko(double x, double y, int velkost) {
		super();
		setLayoutX(x);setLayoutY(y);
		stena=new Image("wall.bmp", velkost, velkost, false, false);
		setImage(stena);
	}
}
