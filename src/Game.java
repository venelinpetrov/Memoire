import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.util.Collections;

public class Game extends Applet {
	/**
	 * Declare a default screen size for the applet
	 */
	private int AppletWidth=1300;
	
	private int AppletHeight=450;
	/**
	 * Create an object of type Menu
	 */
	Menu m=new Menu();
	/**
	 * Un array that keeps the card's images
	 */
	Image cardImage[] = new Image[52];
	/**
	 * Create an object of type Deck
	 */
	Deck d = new Deck();
	/**
	 * Create an object of type Grid 
	 */
	Grid grid = new Grid();
	/**
	 * Declare an audio clip
	 */
	AudioClip ding;
	/**
	 * This variable is used to verify if there are more cards left in the game  
	 */
	static int end = 0;
	/**
	 * These variables are used to verify the state of a card
	 */
	boolean cardIsfaceDown[] = new boolean[52];

	boolean cardIsClickable[] = new boolean[52];

	boolean MouseIsOnCard[] = new boolean[52];
	/**
	 * These variables keep the mouse coordinates
	 */
	int mouseX, mouseY;
	/**
	 * Move counter
	 */
	int moves=0;
	/**
	 * Default card back layout
	 */
	String cardsLayout=new String("Java");
	/*INIT method
	=====================================================================================================================================*/
	public void init() {
		/*Set window settings
		=====================================================================================================================================*/
		setSize(AppletWidth, AppletHeight);
		
		setBackground(Color.blue);
		/*Menu
		=====================================================================================================================================*/		
		add(m.menu);
		
			m.menu.add(m.newGame);
			
				m.newGame.addActionListener(new ResetVariables());
		
			m.menu.add(m.background); 
		  
		  		m.background.add(m.blue);
		  		m.blue.addActionListener (new Blue());
		  		
		  		m.background.add(m.green);
		  		m.green.addActionListener(new Green());
		  
		  		m.background.add(m.pink);
		  		m.pink.addActionListener (new Pink());
		  			
		/*Sound
		=====================================================================================================================================*/
		ding = getAudioClip(getCodeBase(), "ding.au");
		System.out.println(getCodeBase());
		
		/*Initialize game variables
		=====================================================================================================================================*/
		for (int z = 0; z < d.cards.size(); z++) {

			cardImage[z] = getImage(getCodeBase(), "images/"+cardsLayout+".png");

			cardIsfaceDown[z] = true;

			MouseIsOnCard[z] = false;

			cardIsClickable[z] = true;

		}
		/*Card shuffler
		=====================================================================================================================================*/
		//Collections.shuffle(d.cards);
		
		/*Mouse Motion listener
		=====================================================================================================================================*/		
		addMouseListener(new MouseHandler());
	}//end of init method
	
	/*Paint the cards
	=====================================================================================================================================*/	
	public void paint(Graphics g) {

		String str=new String("Bravo! You won in "+moves/2+" moves");
		
		for (int z = 0; z < d.cards.size(); z++) {

			g.drawImage(cardImage[z], grid.getCardX(z), grid.getCardY(z), 72,96, this);
			
			if (end==52){

				printString(g,str,1300,0,0);
				
			}
			
		}
	}
	/*Mouse motion capture 
	=====================================================================================================================================*/	
	/**
	 * This custom MouseHandler class extends the MouseAdapter class
	 */
	class MouseHandler extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			/*End game condition
			=====================================================================================================================================*/
			if (end == 52) {
				for (int z = 0; z < d.cards.size(); z++) {

					cardImage[z] = getImage(getCodeBase(), "");

					cardIsClickable[z] = false;

				}
			}
			/*Game rules
			=====================================================================================================================================*/
			int c = 0;
			/**
			 * This array keeps the indexes of the two fliped cards
			 * It is used later on to verify if the game conditions are satisfied for these two cards
			 */
			int index[] = new int[2];

			for (int v = 0; v < cardIsfaceDown.length; v++) {

				if (cardIsfaceDown[v] == false) {

					index[c] = v;

					c++;
				}
			}
			
			/**
			 * If two card are opened
			 */
			if (c == 2) {
				/**
				 * Verifies if the cards are the same color and if their sum is 14
				 * Cards with the same color have sum of the suit values 3 or are the same suit (equal suit values)
				 */
				if ((d.cards.get(index[0]).getCardValue()+ d.cards.get(index[1]).getCardValue() == 14)&& 
						((d.cards.get(index[0]).getCardSuit() == d.cards.get(index[1]).getCardSuit()) || 
								(d.cards.get(index[0]).getCardSuit() + d.cards.get(index[1]).getCardSuit()) == 3)) {

					ding.play();

					cardIsfaceDown[index[0]] = true;
					
					cardImage[index[0]] = getImage(getCodeBase(),"");
					
					cardIsClickable[index[0]] = false;

					cardIsfaceDown[index[1]] = true;

					cardImage[index[1]] = getImage(getCodeBase(),"");

					cardIsClickable[index[1]] = false;

					end += 2;

					repaint();
				}

				else {

					cardImage[index[0]] = getImage(getCodeBase(),
							"images/"+cardsLayout+".png");

					cardIsfaceDown[index[0]] = true;

					cardImage[index[1]] = getImage(getCodeBase(),
							"images/"+cardsLayout+".png");

					cardIsfaceDown[index[1]] = true;

					repaint();
				}
			}
			/*Card interaction
			=====================================================================================================================================*/	
			/**
			 * The variable e is from type MouseEvent and it is declared as a parameter in method mousePressed
			 * The methods getX() and getY() are implemented in class MouseEvent
			 * In the mouseX and mouseY variables are stored the current X and Y mouse coordinates 
			 */
			mouseX = e.getX(); mouseY = e.getY();
		
			for (int z = 0; z < d.cards.size(); z++) {
				/**
				 * Checks if the mouse is on a card and if this card is clickable
				 * (not clickable card is a destroyed card)
				 */
				if (checkIn(z, mouseX, mouseY) && cardIsClickable[z]) {
					/**
					 * getClickCount() method is implemented in MouseEvent class and returns the number of clicks 
					 * on a given area	
					 */
					if (e.getClickCount() == 1) {
						
						if (cardIsfaceDown[z]) {

							cardImage[z] = getImage(getCodeBase(), "images/"+ d.cards.get(z).getCardSuit() + ""
									+ d.cards.get(z).getCardValue() + ".png");
							
							moves++;
						}

						else {

							cardImage[z] = getImage(getCodeBase(),"images/"+cardsLayout+".png");
						}

						cardIsfaceDown[z] = !cardIsfaceDown[z];

						repaint();
					}
				}
			}
		}
	}
	/**
	 * this method returns true if the mouse is positioned on one of the fixed card places (the grid)
	 * else-it returns false
	 */
	boolean checkIn(int z, int mouseX, int mouseY) {

		if ((mouseX > grid.getCardX(z))&& (mouseX < grid.getCardX(z) + Card.WIDTH)&& 
			(mouseY > grid.getCardY(z))&& (mouseY < grid.getCardY(z) + Card.HEIGHT)) {

			return true;
		}

		else {

			return false;
		}
	}
	/*Print string method
	=====================================================================================================================================*/	
	/**
	 * This method is currently used to print the "win" message
	 */
	public void printString(Graphics g, String str, int width, int XPos, int YPos){
		
		/**
		 * Declare a variable of type Dimension 
		 * getSize() method is implemented in class Dimension
		 */
		Dimension appletSize = this.getSize();
		/**
		 * width and height variables are declared in class Dimension
		 */
		int appletWidth = appletSize.width;
		
		int appletHeight = appletSize.height;

		Font font = new Font("Helvetica", Font.PLAIN, 40);
		/**
		 * setFont(), setColor(), getFontMetrics(), getStringBounds(), getWidth(), getHeight() and drawString() 
		 * methods are defined in abstract class Graphics 
		 */
		g.setFont(font);

		g.setColor(Color.red);

		int stringLen = (int) g.getFontMetrics().getStringBounds(str, g).getWidth();  

		int stringHeight = (int) g.getFontMetrics().getStringBounds(str, g).getHeight();

		int startX = appletWidth/2 - stringLen/2;

		int startY = appletHeight/2 - stringHeight/2;

		g.drawString(str, startX + XPos, startY + YPos);
	}
	/*Menu interaction
	=====================================================================================================================================*/
	/**
	 * Trigger the menu
	 * the method setBackground() is implemented in abstract class Component
	 */
	public void processMouseEvent(MouseEvent e) {

		if (e.isPopupTrigger()) {
			/**
			 * getComponent() returns the originator of the event
			 */
			m.menu.show(e.getComponent(), e.getX(), e.getY());
		}

		super.processMouseEvent(e);
	}
	
	class Green implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			setBackground(Color.green);
		}
	}

	class Blue implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			setBackground(Color.blue);
		}
	}

	class Pink implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			setBackground(Color.pink);
		}
	}
	
	/*Reset game variables
	=====================================================================================================================================*/	
	/**
	 * This class is used by the "New game" oprion of the game menu
	 */
	class ResetVariables implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			for (int z = 0; z < d.cards.size(); z++) {

				cardImage[z] = getImage(getCodeBase(), "images/"+cardsLayout+".png");

				cardIsfaceDown[z] = true;

				MouseIsOnCard[z] = false;

				cardIsClickable[z] = true;
			}
			
			Collections.shuffle(d.cards);
			//init();
			repaint();
		}
	}
}