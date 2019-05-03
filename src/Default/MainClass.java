package Default;

public class MainClass {

	public static void main(String[] args) {
		
		//Objects
		Game solitaire = new Game();
		
		//Create the deck
		solitaire.createDeck();
		
		//Play the game
		//solitaire.playSolitaire();
		
		solitaire.generateActualField();
		
		
		//**TESTING**//
		
		//System.out.println(solitaire.imagineField);
		
		
	}//endMain

}//endClass
