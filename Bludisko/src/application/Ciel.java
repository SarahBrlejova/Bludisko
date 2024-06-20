package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ciel extends ImageView {
	Image ciel;

	public Ciel(double x, double y, int velkost) {
		super();
		setLayoutX(x);setLayoutY(y);
		ciel=new Image("goal.png", velkost, velkost, false, false);
		setImage(ciel);
	}
}
