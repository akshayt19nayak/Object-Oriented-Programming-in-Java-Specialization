package akshay;

import processing.core.*;

public class sunBeach extends PApplet{
	
	private PImage backgroundImg;
	
	public void setup(){
		size(300,300);
		backgroundImg = loadImage("palmTrees.jpg");	
	}
	
	public void draw(){
		backgroundImg.resize(0, height);
		image(backgroundImg,0,0);
		int[] rgb = sunColorSec(second());
		/*int ratio;
		int diffFromEnd;
		for(int i=0;i<30;i++) {
			diffFromEnd = 30-i;
			ratio = diffFromEnd/30;
			fill(255*ratio, 255*ratio,0);
			ellipse(width/4, height/5, width/5, height/5);
		}*/
		fill(rgb[0], rgb[1], rgb[2]);
		ellipse(width/4, height/5, width/5, height/5);
	}	
	
	public int[] sunColorSec(float seconds) {
		int[] rgb = new int[3];
		
		float diffFrom30 = Math.abs(30 - seconds);
		float ratio = diffFrom30/30;
		
		rgb[0] = (int)(255*ratio);
		rgb[1] = (int)(255*ratio);
		rgb[2] = (int)(0);
		
		return rgb;
	}
	/*
	public static void main (String[] args) {
		//Add main method for running as application
		PApplet.main(new String[] {"--present", "MyPApplet"});
	}*/
}