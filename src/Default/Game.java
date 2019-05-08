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
	String[][] scoreField;
	
	//Constructor
	public Game() {
		
		read = new Scanner(System.in);
		Deck = new ArrayList<String>();
		removedFromDeck = new ArrayList<String>();
		discardPile = new ArrayList<String>();
		rand = new Random();
		field = new String[13][14];
		imagineField = new String[13][14];
		scoreField = new String[13][7];
		
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
					Deck.add(x + "S");
				
				if (y == 1)
					Deck.add(x + "D");
				
				if (y == 2)
					Deck.add(x + "H");
				
				if (y == 3)
					Deck.add(x + "C");
					
			}//endFor
			
		}//endFor
		
		Deck.add("JS");
		Deck.add("JD");
		Deck.add("JH");
		Deck.add("JC");
		
		Deck.add("QS");
		Deck.add("QD");
		Deck.add("QH");
		Deck.add("QC");
		
		Deck.add("KS");
		Deck.add("KD");
		Deck.add("KH");
		Deck.add("KC");
		
		Deck.add("AS");
		Deck.add("AD");
		Deck.add("AH");
		Deck.add("AH");
		
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
			generateImagineField();
			
			
			
			
			
			
			
			
		}//endWhile
		
	}//endMethod
	
	//Function to create the imaginary field
	public void generateImagineField() {
		
		//Variables
		String nextDraw = " ";
		
		//First set the entire array to double spaces
		for (int x = 0; x < 13; x ++) {
			
			for (int y = 0; y < 14; y ++) {
				
				imagineField[x][y] = "  ";
				
			}//endFor
			
		}//endFor
		
		
		//Loop through the columns in the 1st row
		for (int x = 0; x < 14; x ++) {
			
			//Only place cards on the even places and set the evens to a space
			if (x % 2 == 0) {
			
				placeCards(0, x, nextDraw);
			
			} else {
				
				imagineField[0][x] = "  ";
				
			}//endIf
				
		}//endFor
		
		//Loop through the 2nd row
		for (int x = 0; x < 12; x ++) {
			
			//Only place cards on the even places and set the evens to a space
			if (x % 2 == 0) {
			
				placeCards(1, x, nextDraw);
			
			} else {
				
				imagineField[1][x] = "  ";
				
			}//endIf
			
		}//endFor
		
		//Loop through the 3rd row
		for (int x = 0; x < 10; x ++) {
					
			//Only place cards on the even places and set the evens to a space
			if (x % 2 == 0) {
					
				placeCards(2, x, nextDraw);
					
			} else {
						
				imagineField[2][x] = "  ";
						
			}//endIf
					
		}//endFor
				
		//Loop through the 4th row
		for (int x = 0; x < 8; x ++) {
					
			//Only place cards on the even places and set the evens to a space
			if (x % 2 == 0) {
					
				placeCards(3, x, nextDraw);
					
			} else {
						
				imagineField[3][x] = "  ";
						
			}//endIf
					
		}//endFor
				
		//Loop through the 5th row
		for (int x = 0; x < 6; x ++) {
					
			//Only place cards on the even places and set the evens to a space
			if (x % 2 == 0) {
					
				placeCards(4, x, nextDraw);
				
			} else {
						
				imagineField[4][x] = "  ";
						
			}//endIf
					
		}//endFor
		
		//Loop through the 6th row
		for (int x = 0; x < 4; x ++) {
					
			//Only place cards on the even places and set the evens to a space
			if (x % 2 == 0) {
					
				placeCards(5, x, nextDraw);
					
			} else {
						
				imagineField[5][x] = "  ";
						
			}//endIf
				
		}//endFor
		
		//Loop through the 7th row
		for (int x = 0; x < 2; x ++) {
					
			//Only place cards on the even places and set the evens to a space
			if (x % 2 == 0) {
					
				placeCards(6, x, nextDraw);
					
			} else {
						
				imagineField[6][x] = "  ";
						
			}//endIf
					
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

	//Utility function to print the imaginary field
	public void printImagineField() {
		
		//Loop through each section and print out that space
		for (int x = 0; x < 13; x ++) {
			
			for (int y = 0; y < 14; y ++) {
				
				System.out.print(imagineField[x][y]);
				
			}//endFor
			
			System.out.println("");
			
		}//endFor
		
	}//endMethod
	
	//**************************************************************************\\
	
	//Function to create the player's field
	public void generateField() {
		
		//First set the entire array to double spaces
		for (int x = 0; x < 13; x ++) {
					
			for (int y = 0; y < 14; y ++) {
						
				field[x][y] = "  ";
						
			}//endFor
					
		}//endFor
				
		//Loop through the columns in the 1st row
		for (int x = 0; x < 14; x ++) {
					
			//Only place cards on the even places and set the evens to a space
			if (x % 2 == 0) {
					
				field[0][x] = "**";
					
			} else {
						
				field[0][x] = "  ";
						
			}//endIf
						
		}//endFor
		
		//*****************************************\\
		//TODO
		//Pulled from generate Imaginary Field so format like it above
				
				//Loop through the 2nd row
				for (int x = 0; x < 12; x ++) {
					
					//Only place cards on the even places and set the evens to a space
					if (x % 2 == 0) {
					
						//placeCards(1, x, nextDraw);
					
					} else {
						
						imagineField[1][x] = "  ";
						
					}//endIf
					
				}//endFor
				
				//Loop through the 3rd row
				for (int x = 0; x < 10; x ++) {
							
					//Only place cards on the even places and set the evens to a space
					if (x % 2 == 0) {
							
						//placeCards(2, x, nextDraw);
							
					} else {
								
						imagineField[2][x] = "  ";
								
					}//endIf
							
				}//endFor
						
				//Loop through the 4th row
				for (int x = 0; x < 8; x ++) {
							
					//Only place cards on the even places and set the evens to a space
					if (x % 2 == 0) {
							
						//placeCards(3, x, nextDraw);
							
					} else {
								
						imagineField[3][x] = "  ";
								
					}//endIf
							
				}//endFor
						
				//Loop through the 5th row
				for (int x = 0; x < 6; x ++) {
							
					//Only place cards on the even places and set the evens to a space
					if (x % 2 == 0) {
							
						//placeCards(4, x, nextDraw);
						
					} else {
								
						imagineField[4][x] = "  ";
								
					}//endIf
							
				}//endFor
				
				//Loop through the 6th row
				for (int x = 0; x < 4; x ++) {
							
					//Only place cards on the even places and set the evens to a space
					if (x % 2 == 0) {
							
						//placeCards(5, x, nextDraw);
							
					} else {
								
						imagineField[5][x] = "  ";
								
					}//endIf
						
				}//endFor
				
				//Loop through the 7th row
				for (int x = 0; x < 2; x ++) {
							
					//Only place cards on the even places and set the evens to a space
					if (x % 2 == 0) {
							
						//placeCards(6, x, nextDraw);
							
					} else {
								
						imagineField[6][x] = "  ";
								
					}//endIf
							
				}//endFor
		
	}//endMethod
	
	//Utility function to print the player's field
	public void printField() {
		
		//Loop through each space
		for (int x = 0; x < 13; x ++) {
			
			for (int y = 0; y < 14; y ++) {
				
				System.out.print(field[x][y]);
				
			}//endFor
			
			System.out.println("");
			
		}//endFor
		
	}//endMethod
	
}//endClass
