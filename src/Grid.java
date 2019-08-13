/**
 * This class is used to define a "grid" which is used by the drawImage method in class Game
 * to draw the card in it's proper place on the screen. 
 * Each point z from the plane is defined by two coordinates x and y
 */
public class Grid {
	/**
	 * These arrays keep the x and y coordinates for each point
	 */
	private int x[]=new int[52];
	
	private int y[]=new int[52];
	/**
	 * The offset variable defines a default card displacement (space between the cards)
	 */
	int offset=5;
	/**
	 * This constructor gives x and y values for each point z from the plane
	 * x0=...,x1=...,x2=...,x51=...
	 * y0=...,y1=...,y2=...,y51=...
	 */
	Grid(){
		
		int z=0;
		
		for(int j=0; j<=3; j++){
			
			for(int i=1; i<=13; i++){
				
				x[z]=10+i*(Card.WIDTH+offset);
				y[z]=30+j*(Card.HEIGHT+offset);
				z++;
			}
		}
	}
	/**
	 * These methods return the x and y coordinates generated for thid grid
	 */
	int getCardX(int z){
		
		return x[z];
	}
	
	int getCardY(int z){
		
		return y[z];
	}
}