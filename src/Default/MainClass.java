package Default;

public class MainClass {

	public static void main(String[] args) {
		
		//Objects
		Game solitaire = new Game();
		
		//Create the deck
		solitaire.createDeck();
		
		//Play the game
		//solitaire.playSolitaire();
		
		
		//**TESTING**//
		
		
		solitaire.generateImagineField();
		solitaire.printImagineField();
		solitaire.generateField();
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		solitaire.printField();
		
		solitaire.createMenu();
		
	}//endMain

}//endClass
