import java.awt.MenuItem;
import java.awt.PopupMenu;
/**
 * PopUp menu class
 */
public class Menu {
	/**
	 * First level menu options
	 */
	PopupMenu menu;
	
	PopupMenu background;

	PopupMenu cards;
	/**
	 * Second level menu optinons
	 */
	MenuItem green;

	MenuItem pink;

	MenuItem blue;
	
	MenuItem newGame;
	/**
	 * This constructor creates the menu items and assigns a name to each of them
	 */
	Menu() {
		
		menu       = new PopupMenu("Menu");
		
		background = new PopupMenu("Background");

		green      = new MenuItem("Green");

		pink       = new MenuItem("Pink");
		
		blue       = new MenuItem("Blue");		

		cards      = new PopupMenu("Cards Layout");
		
		newGame    = new MenuItem("New Game");
	}

}