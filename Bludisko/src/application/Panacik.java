package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Panacik extends ImageView {
	private Image[] zoznamObrazkov;
	double polohaX, polohaY;
	private int actImage = 0;
	int smer=0;
	
	public Panacik(String nazov, int pocet, double polohaX, double polohaY, double w, double h) {
		super();
		this.polohaX = polohaX; this.polohaY = polohaY;
		setLayoutX(polohaX);setLayoutY(polohaY);
		   zoznamObrazkov = new Image[pocet];
		   for(int i = 0; i < pocet; i++) {
			   zoznamObrazkov[i] = new Image(nazov+i+".png", w, h, false, false);
		   }
		   setImage(zoznamObrazkov[0]); 
	}
	
	 public void hore(int velkost) {
	  	  setLayoutY(getLayoutY() - velkost);
	  	  smer=1;
	  	  vykresli();
	  	}
	  	        
	  	public void dole(int velkost) {
	  	  setLayoutY(getLayoutY() + velkost);
	  	  smer=0;
	  	    vykresli();
	  	}
	  	    
	  	public void dolava(int velkost) {
	  		smer=3;
	  	  setLayoutX(getLayoutX() - velkost);
	  	    vykresli();
	  	}

	  	public void doprava(int velkost) {
	  		smer=2;
	  	  setLayoutX(getLayoutX() + velkost);
	  	   vykresli();
	  	}
	  	private void vykresli() {
	  	    nextImage();
	  	    setImage(zoznamObrazkov[actImage]);
	  	}
	  	
	  	 private void nextImage(){
	  		if (smer == 0) actImage = (actImage + 1) % 3; //
	  		if (smer == 1) actImage = 3+(actImage + 1) % 3;
	  	    if (smer == 2) actImage = 6+(actImage + 1) % 3;
	  	    if (smer == 3) actImage = 9+(actImage + 1) % 3;
		 	  
		}
}
