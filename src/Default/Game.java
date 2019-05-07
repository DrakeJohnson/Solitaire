package Default;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Game {

	//Variables
	
	//Objects
	Scanner read;
	ArrayList<String> Deck;
	ArrayList<String> removedFromDeck;
	ArrayList<String> discardPile;
	Random rand;
	String[][] field;
	String[][] imagineField;
	
	//Constructor
	public Game() {
		
		read = new Scanner(System.in);
		Deck = new ArrayList<String>();
		removedFromDeck = new ArrayList<String>();
		discardPile = new ArrayList<String>();
		rand = new Random();
		field = new String[13][14];
		imagineField = new String[13][14];
		
	}//endConstructor
	
	
	// Functions //
	
	
	//This creates the deck
	public void createDeck() {
		
		//Creates the cards 2 to 10
		for (int x = 2; x <= 10; x ++) {
			
			//For each suit
			for (int y = 0; y < 4; y ++) {
				
				//Numbers
				if (y == 0)
					Deck.add(x + "Spades");
				
				if (y == 1)
					Deck.add(x + "Diamonds");
				
				if (y == 2)
					Deck.add(x + "Hearts");
				
				if (y == 3)
					Deck.add(x + "Clubs");
					
			}//endFor
			
		}//endFor
		
		Deck.add("JackSpades");
		Deck.add("JackDiamonds");
		Deck.add("JackHearts");
		Deck.add("JackClubs");
		
		Deck.add("QueenSpades");
		Deck.add("QueenDiamonds");
		Deck.add("QueenHearts");
		Deck.add("QueenClubs");
		
		Deck.add("KingSpades");
		Deck.add("KingDiamonds");
		Deck.add("KingHearts");
		Deck.add("KingClubs");
		
		Deck.add("AceSpades");
		Deck.add("AceDiamonds");
		Deck.add("AceHearts");
		Deck.add("AceClubs");
		
	}//endMethod
	
	//This shuffles the current deck
	public void shuffleDeck() {
		
		/*
		 * -------------------------------------
		 * Stores a random card into a String
		 * Chooses a random location and replace that position with the stored card
		 * Replace the original card with the card that is now the stored card
		 * Repeat 100 times
		 * -------------------------------------
		*/
		
		//Variables
		String temp = " ";
		String newTemp = " ";
		
		//Loop
		for (int x = 0; x < 100; x ++) {
			
			//Get the first card position and set to the temporary variable
			int origPos = rand.nextInt(52);
			temp = Deck.get(origPos);
			
			//Get a new card position and set the new temporary variables
			int newPos = rand.nextInt(52);
			newTemp = Deck.get(newPos);
			
			//Swap the 2 cards
			Deck.set(newPos, temp);
			Deck.set(origPos, newTemp);
			
		}//endFor
		
	}//endMethod

	//**Solitaire Functions**//
	
	//This plays the game solitaire
	public void playSolitaire() {
		
		/*
		 * ----------------------
		 * Check to see if the user has won, otherwise loop through the turn
		 * Provide a few options to complete a turn with a way back to the menu
		 * 		Make sure each decision has a checking loop
		 * 		1) Move a card to the top (if correct add to the score)
		 * 		2) Draw the next 3 cards
		 * 		3) Move a set/single card on the field
		 * 		4) New Game
		 * If the user gets the final score, the user wins
		 * ----------------------
		 */
		
		//Variables
		boolean winGame = false;
		boolean loseGame = false;
		
		//Loop until the game is over
		while (winGame == false && loseGame == false) {
			
			/*
			 * This section will start the game
			 * 		Shuffle the deck twice
			 * 		Generate the imaginary field of strings
			 * 		Generate and print the player's field of ** and strings
			 */
			
			//Shuffle
			shuffleDeck();
			shuffleDeck();
			
			//Generate "imaginary" field
			generateActualField();
			
			
			
			
			
			
			
			
			
			
		}//endWhile
		
	}//endMethod
	
	//Utility function to create the imaginary field
	public void generateActualField() {
		
		//Variables
		String nextDraw = " ";
		
		//Loop through the columns in the specific row
		for (int x = 0; x < 14; x ++) {
			
			placeCards(0, x, nextDraw);
			
		}//endIf
		
		//Loop through the second row
		for (int x = 0; x < 13; x ++) {
			
			placeCards(1, x, nextDraw);
			
		}//endFor
		
		//Loop through the third row
		for (int x = 0; x < 12; x ++) {
			
			placeCards(2, x, nextDraw);
			
		}//endFor
		
		//Loop through the 4th row
		for (int x = 0; x < 11; x ++) {
			
			placeCards(3, x, nextDraw);
			
		}//endFor
		
		//Loop through the 5th row
		for (int x = 0; x < 10; x ++) {
			
			placeCards(4, x, nextDraw);
			
		}//endFor
		
		for (int p = 0; p < 13; p ++) {
			
			for (int q = 0; q < 14; q ++) {
				
				System.out.print(imagineField[p][q]);
				
			}//endFor
			
			System.out.println("");
			
		}//endFor
		
	}//endMethod
	
	//Utility function for generateActual Field to get the next card, remove it, then set it to the given position
	public void placeCards(int currRow, int currCol, String next) {
		
		//Get the card
		next = Deck.get(0);
		
		//Move to removed
		removedFromDeck.add(Deck.remove(0));
		
		//Set that card to the position
		imagineField[currRow][currCol] = next;
		
		
	}//endMethod
	
}//endClass
