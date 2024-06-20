package application;

import javafx.animation.AnimationTimer;

public class MyTimer extends AnimationTimer{
	  private Game myGame;
	   private long lastNanoTime;
	   
	  public MyTimer(Game mg) {
		     myGame = mg;
		   }
	   @Override
	    public void handle(long now) {
	        myGame.update(now - lastNanoTime);
	        lastNanoTime = now;
	    try {
	         Thread.sleep(50);
	        } catch (Exception e)  {}
	    }

	    
	    public void start() {
	       lastNanoTime =  System.nanoTime(); 
	       super.start();       
	    }   
	

}
