package Default;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Game {

	//Variables
	int rowPos;
	int colPos;
	int desRP;
	int desCP;
	
	//Objects
	Scanner read;
	ArrayList<String> Deck;
	//Don't remember the purpose of this one...
	ArrayList<String> removedFromDeck;
	ArrayList<String> discardPile;
	Random rand;
	String[][] field;
	String[][] imagineField;
	String[][] scoreField;
	String[] hand;
	ValidMoves checker;
	
	//Constructor
	public Game() {
		
		rowPos = 0;
		colPos = 0;
		
		read = new Scanner(System.in);
		Deck = new ArrayList<String>();
		removedFromDeck = new ArrayList<String>();
		discardPile = new ArrayList<String>();
		rand = new Random();
		field = new String[13][14];
		imagineField = new String[13][14];
		scoreField = new String[13][7];
		hand = new String[3];
		checker = new ValidMoves();
		
	}//endConstructor
	
	
	// Functions //
	
	
	//This creates the deck
	public void createDeck() {
		
		//Creates the cards 2 to 10
		for (int x = 1; x < 10; x ++) {
			
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
		Deck.add("AC");
		
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
		
		//Generate the score field
		generateScoreField();
		
		//Generate & print player's field
		generateField();
		printField();
		
		//Create a hand
		setupHand();
		
		//Loop until the game is over
		while (winGame == false) {
			
			/*
			 * This section will create the menu
			 * 		Print the menu
			 */
			
			//Create menu
			createMenu();
			
			/*
			 * This section will collect the user input for the turn and act accordingly
			 * (It will also make sure the move is valid)
			 * 		Call function if they want to move a set/card
			 * 		Call function if they want to draw
			 * 		Call function if they want to score
			 * 		Call function if they want to start a new game
			 */
			
			//Start the turn
			getTurn();
			
			//Print the hand
			printHand();
			
			//Print the scoreField
			printScore();
			
			//Print the field again
			printField();
			
		}//endWhile
		
	}//endMethod
	
	//**************************************************************************\\
	
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
				
		//Loop through the 2nd row
		for (int x = 0; x < 12; x ++) {
					
			//Only place cards on the even places and set the evens to a space
			if (x % 2 == 0) {
					
				field[1][x] = "**";
					
			} else {
						
				field[1][x] = "  ";
						
			}//endIf
					
		}//endFor
				
		//Loop through the 3rd row
		for (int x = 0; x < 10; x ++) {
							
			//Only place cards on the even places and set the evens to a space
			if (x % 2 == 0) {
							
				field[2][x] = "**";
							
			} else {
								
				field[2][x] = "  ";
								
			}//endIf
							
		}//endFor
						
		//Loop through the 4th row
		for (int x = 0; x < 8; x ++) {
							
			//Only place cards on the even places and set the evens to a space
			if (x % 2 == 0) {
							
				field[3][x] = "**";
							
			} else {
								
				field[3][x] = "  ";
								
			}//endIf
							
		}//endFor
						
		//Loop through the 5th row
		for (int x = 0; x < 6; x ++) {
							
			//Only place cards on the even places and set the evens to a space
			if (x % 2 == 0) {
							
				field[4][x] = "**";
						
			} else {
								
				field[4][x] = "  ";
								
			}//endIf
							
		}//endFor
				
		//Loop through the 6th row
		for (int x = 0; x < 4; x ++) {
							
			//Only place cards on the even places and set the evens to a space
			if (x % 2 == 0) {
							
				field[5][x] = "**";
							
			} else {
								
				field[5][x] = "  ";
								
			}//endIf
						
		}//endFor
				
		//Loop through the 7th row
		for (int x = 0; x < 2; x ++) {
							
			//Only place cards on the even places and set the evens to a space
			if (x % 2 == 0) {
							
				field[6][x] = "**";
							
			} else {
								
				field[6][x] = "  ";
								
			}//endIf
							
		}//endFor
		
		//This will set the last card in each column to the card in the imaginary field
		field[0][12] = imagineField[0][12];
		field[1][10] = imagineField[1][10];
		field[2][8] = imagineField[2][8];
		field[3][6] = imagineField[3][6];
		field[4][4] = imagineField[4][4];
		field[5][2] = imagineField[5][2];
		field[6][0] = imagineField[6][0];
		
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
	
	//**************************************************************************\\
	
	//Function to setup the scoreField
	public void generateScoreField() {
		
		//Loop to set all spaces to "  "
		for (int x = 0; x < 13; x ++) {
			
			for (int y = 0; y < 7; y ++) {
				
				scoreField[x][y] = "  ";
				
			}//endFor
			
		}//endFor
		
	}//endMethod

	//Utility function to print the scoring field
	public void printScore() {
		
		//Loop through each section
		for (int x = 0; x < 13; x ++) {
			
			for (int y = 0; y < 7; y ++) {
				
				System.out.print(scoreField[x][y]);
				
			}//endFor
			
			System.out.println("");
			
		}//endFor
		
	}//endIf
	
	//**************************************************************************\\
	
	//Function to create the first menu
	public void createMenu() {
		
		//Prompt
		System.out.println("----Options----");
		System.out.println("1) Move a card to the top to score");
		System.out.println("2) Draw the next 3 cards");
		System.out.println("3) Move a set/single card on the field");
		System.out.println("4) New Game");
		
	}//endMethod
	
	//**************************************************************************\\
	
	//Function to setup the hand
	public void setupHand() {
		
		hand[0] = "  ";
		hand[1] = "  ";
		hand[2] = "  ";
		
	}//endIf
	
	//Utility function to print the hand
	public void printHand() {
		
		for (int x = 0; x < 3; x ++) {
			
			System.out.print(hand[x] + " ");
			
		}//endFor
		
		System.out.println("");
		
	}//endMethod

	//**************************************************************************\\
	
	//Function to collect answers and call the separate functions
	public void getTurn() {
		
		//Variables
		int ans = 0;
		
		//Loop until they enter a valid answer
		do {
			
			//Prompt
			System.out.println("");
			System.out.print("Enter a number to do a move: ");
			ans = read.nextInt();
			
			//Switch to call different moves based on what they chose
			switch (ans) {
			
			case 1:
				
				/*
				 * ---Scoring a Card Section---
				 * Get the card which they want to score with
				 * Call the moveScore to see if it's a valid move
				 * 		If so it will move it
				 * 		Else it will print "Doesn't work"
				 * Should then return to the menu
				 */
				
				//Variables
				String card = "  ";
				
				//Get the card they want to score
				card = cardFromPlayer();
				
				//Call the moving method
				moveScore(card, rowPos, colPos);
					
				break;
				
			case 2:
				
				/*
				 * ---Drawing 3 Cards section---
				 * Check the size of the draw deck
				 * 		If more or equal to 3, set the hand array to the next 3 cards
				 * 		If less than 3, add all the discarded cards back to the draw deck, 
				 * 			then set the hand array to the next 3 cards
				 * Should return to the menu
				 */
				
				if (checkDraw(Deck)) {
					
					nextThreeCards();
					
				} else {
					
					//Get the size of the discardPile to remove them into the Deck
					int tempSize = discardPile.size();
					
					for (int x = 0; x < tempSize; x ++) {
						
						Deck.add(discardPile.remove(0));
						
					}//endFor
					
					nextThreeCards();
					
				}//endIf

				break;
				
			case 3:
				
				//Call move set/single card
				/*
				 * ---Moving a single card or a set of cards---
				 * Check if the user wants to use a hand card or field card
				 * 		If hand card...
				 * 			chose 1-3
				 * 			chose the location of a card it should go under
				 * 			if it works, place it below. If not say "Doesn't work"
				 * 		If a field card...
				 * 			chose the location of a card or the top of a set you want to move
				 * 			chose the location of a card it should go under
				 * 			if it works, place it below. If not say "Doesn't work"
				 */
		
				moveCard();
				
				break;
				
			case 4:
			
				//Call the newGame function
				newGame();
				
				break;
				
			default:
	
				//DOESNT EXIST
				break;
			
			}//endSwitch
		
		} while (ans > 5 && ans < 0);
		
	}//endMethod
	
	//**************************************************************************\\
	
	//PRIMARY Utility function to move a card to the score position
	public void moveScore(String card, int rowPos, int colPos) {
			
		//Variables
		int handOrNot = 0;
		
		//Check to see if the card was from the hand or not
		if (rowPos == -1 && colPos == -1) {
			
			handOrNot = 2;
			
		} else {
			
			handOrNot = 1;
			
		}//endIf
		
		//Makes sure the move is valid
		if (checker.checkScoring(scoreField, card)) {
				
			//Variables
			int numPos = 0;
			int suitPos = 0;
			int tempSize = -1;
			int position = -1;
				
			/*
			 * If the card is from the field
			 * 		Set that card in both arrays to a "  "
			 * 		Set the provided card to the position in the scoreField
			 * 		Flip the next card in line with a **
			 * Else
			 * 		Set the card to the position in the scoreField
			 */
			
			if (handOrNot == 1) {
				
				imagineField[rowPos][colPos] = "  ";
				field[rowPos][colPos] = "  ";
				
				numPos = scoreNumPos(card);
				suitPos = scoreSuitPos(card);
				
				scoreField[numPos][suitPos] = card;
			
				//Only if the card is not in the top row
				if (rowPos != 0) {
	
					field[rowPos - 1][colPos] = imagineField[rowPos - 1][colPos];
				
				}//endIf
				
			} else {
				
				//Find the cardPicked in the hand and remove it
				for (int x = 0; x < 3; x ++) {
					
					if (hand[x].equals(card)) {
						
						hand[x] = "  ";
						
					}//endIf
					
				}//endFor
				
				numPos = scoreNumPos(card);
				suitPos = scoreSuitPos(card);
				
				scoreField[numPos][suitPos] = card;
				
				//Remove it from the discardPile
				tempSize = discardPile.size();
				
				for (int x = 0; x < tempSize; x ++) {
					
					if (discardPile.get(x) == card) {
						
						position = x;
						
					}//endIf
					
				}//endFor
				
				discardPile.remove(position);
				
			}//endIf
			
		} else {
			
			System.out.println("Doesn't Work");
			
		}//endIf
			
	}//endMethod

	//Utility function to get the card the player wants to choose
	public String cardFromPlayer() {
		
		//Variables
		String card = "  ";
		int choice = -1;
		
		//Check if they want to move the card from the hand or not
		do {
			
			System.out.print("Enter a 1 if you are scoring from the hand, or 0 from the field: ");
			choice = read.nextInt();
		
		} while (choice < 0 || choice > 1);
		
		if (choice == 1) {
			
			//Player enters which card they want from the hand, then return it 
			//(set the colPos and rowPos to -1 to indicate that it was from the hand)
			rowPos = -1;
			colPos = -1;
			return cardFromHand();
			
		} else {
			
			//Prompt, loop until valid
			do {
			
				System.out.print("Enter the card row: ");
				rowPos = read.nextInt() - 1;
		
			} while (rowPos > 14 && rowPos < 0);
		
			//Second prompt, loop until valid
			do {
			
				System.out.print("Enter the card column (the columns go: CARD | SPACE | CARD etc, so enter an odd column): ");
				colPos = read.nextInt() - 1;
			
			} while (colPos > 15 && colPos < 0 && colPos % 2 != 0);
		
			//Set the card to that position the user entered
			card = field[rowPos][colPos];
		
			return card;
		
		}//endIf
			
	}//endMethod
	
	//Utility function that returns the position a card value is according to the list in ValidMoves
	public int scoreNumPos(String card) {
		
		//Variables
		int position = 0;
		
		//Compare the card to the order list to find its position
		for (int x = 0; x < 13; x ++) {
			
			if (card.charAt(0) == checker.cardOrder[x].charAt(0)) {
				
				position = x;
				
			}//endIf
			
		}//endFor
		
		return position;
		
	}//endMethod
	
	//Utility function that returns the position a card suit is (see checkScoreField in ValidMoves class)
	public int scoreSuitPos(String card) {
		
		//Variables
		int column = 0;
		
		//Checks which suit the card is and sets the array column slot
		/*
		 * 0 is spades
		 * 1 is a space
		 * 2 is clubs
		 * 3 is a space
		 * 4 is diamonds
		 * 5 is a space
		 * 6 is hearts
		 * 7 is a space
		 */
		
		if (card.substring(1).equals("S")) {
			
			column = 0;
			
		} else if (card.substring(1).equals("C")) {
			
			column = 2;
			
		} else if (card.substring(1).equals("D")) {
			
			column = 4;
			
		} else if (card.substring(1).equals("H")) {
			
			column = 6;
			
		}//endIf
		
		return column;
		
	}//endMethod
	
	//***************************************************************************\\
	
	//PRIMARY Utility function to transfer the next 3 cards to the hand (and to the discarded pile)
	public void nextThreeCards() {
			
		//Set the next three cards to the spaces in hand, then move that card to the discarded
		for (int x = 0; x < 3; x ++) {
				
			hand[x] = Deck.get(0);
			discardPile.add(Deck.remove(0));
				
		}//endFor
	
	}//endMethod
		
	//Utility function that returns true if the draw deck has 3 or more cards
	public boolean checkDraw(ArrayList<String> Deck) {
		
		//Variables
		boolean threeOrMore = true;
		
			//Check the deck size to see if it's less than 3, if so change threeOrMore
			if (Deck.size() < 3) {
				
				threeOrMore = false;
				
			}//endIf
			
		return threeOrMore;
		
	}//endMethod
	
	//****************************************************************************\\
	
	//PRIMARY utility function that moves the card or set to the next location
	public void moveCard() {
		
		//Variables
		int move = 0;
		int secondMove = 0;
		String cardPicked = " ";
		String cardDest = " ";
		int tempSize = -1;
		int position = -1;
		
		/*
		 * If the move is valid:
		 * 		If the input is 0:
		 * 			get the card they want
		 * 			remove cardPicked from hand and put into a temporary variable
		 * 			set the part of the field to the temporary variable
		 * 		If the input is 1:
		 * 			loop through cards starting with cardPicked and going down, set the part of the field to that card
		 * 			set the old cards to "  "
		 * 		Else, print ERROR
		 * Else, print Doesn't Work
		 */
		
		//Get their move
		move = moveInput();
		
		//If they chose hand, get the card from the hand and where they want to place the card
		if (move == 1) {
			
			cardPicked = cardFromHand();
			cardDest = cardDestination();
			
			//Check if the destination is valid
			if (cardDest.equals("ERROR")) {
				
				System.out.println("Destination Error");
				
			} else if (checkMoveValid(cardPicked, cardDest)) {
				
				moveFromHand(cardPicked, cardDest);
				
				//Remove the card from the discardPile
				tempSize = discardPile.size();
				
				for (int x = 0; x < tempSize; x ++) {
					
					if (discardPile.get(x) == cardPicked) {
						
						position = x;
						
					}//endIf
					
				}//endFor
				
				discardPile.remove(position);
				
			} else {
				
				System.out.println("Doesn't Work");
				
			}//endIf
			
		} else {
			
			//TODO If they chose the field, check to see if it's a card or set, then move the card/set if valid
			secondMove = moveCardSetInput();
			
			
			
			System.out.println("IN PROGRESS");
			
		}//endIf
		
	}//endMethod
	
	//Utility function that gets what moving action the user wants to take
	public int moveInput() {
		
		//Variables
		int choice = -1;
		
		//Prompt if the hand has objects
		do {
				
			if (hand[0] != "  " || hand[1] != "  " || hand[2] != "  ") {
		
				do {
					
					System.out.print("Enter a 1 to use a card from the hand, or a 0 to use a card from the field: ");
					choice = read.nextInt();
			
				} while (choice > 1 || choice < 0);
					
			} else {
		
				System.out.println("No hand available");
				choice = 0;
				
			}//endIf
		
		} while (choice > 1 || choice < 0);
			
		return choice;
		
	}//endMethod
	
	//Utility function that gets whether the player wants to move a set or a single card
	public int moveCardSetInput() {
		
		//Variables
		int choice = -1;
		
		//Prompt if they want to use a card or a set
		do {
			
			System.out.print("Enter a 1 to use a single card, or enter a 0 to use a set: ");
			choice = read.nextInt();
		
		} while (choice > 1 || choice < 0);
			
		return choice;
		
	}//endMethod
	
	//Utility function that checks to make sure that the movement is valid
	public boolean checkMoveValid(String cardPicked, String cardDest) {
		
		//Variables
		boolean works = true;
		int rowPos = -1;
		int colPos = -1;
		
		/*
		 * If the cardDest is "  " and the cardPicked is a King, then return works
		 * Else
		 * 		Find what suit & position the card is
		 * 		Compare to the destination
		 * 		If it works, return works, else return false
		 */
		
		if (cardDest.equals("  ") && cardPicked.charAt(0) == 'K') {
			
			return works;
			
		} else if (!cardDest.equals("  ")){
	
			//Variables for the suit and number value of both cards
			int pickNum = scoreNumPos(cardPicked);
			int pickSuit = scoreSuitPos(cardPicked);
			int destNum = scoreNumPos(cardDest);
			int destSuit = scoreSuitPos(cardDest);
			
			//Position of the cardDest
			for (int x = 0; x < 13; x ++) {
				
				for (int y = 0; y < 14; y ++) {
					
					if (imagineField[x][y].equals(cardDest)) {
						
						rowPos = x;
						colPos = y;
						
					}//endIf
					
				}//endFor
				
			}//endFor
		
			if (destNum - pickNum == 1 && ((destSuit < 3 && pickSuit > 3) || (destSuit > 3 && pickSuit < 3)) && imagineField[rowPos + 1][colPos].equals("  ")) {
			
				return works;
			
			} else {
			
				return false;
			
			}//endIf
		
		} else {
			
			return false;
			
		}//endIf
			
	}//endMethod
	
	//Utility function that retrieves the card from the hand the user wants to use

	//Utility function that gets the card the user wants from the hand
	public String cardFromHand() {
		
		//Variables
		String card = "  ";
		int handPos = -1;
		
		//Prompt
		do {
		
			System.out.print("Enter which card you want by entering their placement (1 2 3): ");
			handPos = read.nextInt() - 1;
		
		} while(handPos < 0 || handPos > 2);
			
		//Get the card
		card = hand[handPos];
		
		return card;
		
	}//endMethod
	
	//Utility function that returns the card the user wants to place the previously chosen card under

	//Utility function that gets the card the user wants to put the desired cards under
	public String cardDestination() {
		
		//Variables
		String card = " ";
		
		//Prompt
		System.out.print("Here you will enter the card you want to place the previously chosen card under. Enter a row: ");
		desRP = read.nextInt() - 1;
		
		System.out.print("Enter a column: ");
		desCP = read.nextInt() - 1;
		
		//If the destination is an actual location (top of the field and "  " or an actual card) then return, else print incorrect location
		if ((!imagineField[desRP][desCP].equals("**") && !imagineField[desRP][desCP].equals("  ")) || (imagineField[desRP][desCP].equals("  ") && rowPos == 0)) {
		
			card = imagineField[desRP][desCP];
			
			return card;
			
		} else {
			
			return "ERROR";
			
		}//endIf
		
	}//endMethod
	
	//Utility function to actually move the card

	//Utility function that actually moves the card from the hand to the new position
	public void moveFromHand(String cardPicked, String cardDest) {
		
		//Find the cardPicked in the hand and remove it
		for (int x = 0; x < 3; x ++) {
			
			if (hand[x].equals(cardPicked)) {
				
				hand[x] = "  ";
				
			}//endIf
			
		}//endFor
		
		//In the space below the cardDest's position, replace the "  " with the chosenCard
		field[desRP + 1][desCP] = cardPicked;
		imagineField[desRP + 1][desCP] = cardPicked;
		
	}//endMethod
	
	//****************************************************************************\\
	
	//PRIMARY Utility function that starts a new game
	
	//**************************************************************************\\
	
	//PRIMARY utility function that starts a new game
	public void newGame() {
		
		/*
		 * Print a bunch of spaces
		 * Clear every list and create a new deck & shuffle & lists
		 */
		
		for (int x = 0; x < 4; x ++) {
			
			System.out.println("");
			
			if (x == 2) {
				
				System.out.println("--------------------------------------------------");
				
			}//endIf
			
		}//endFor
		
		clearLists();
		newLists();
		
	}//endMethod
	
	//Utility function that clears all the lists and arrays

	//Utility function that clears all the lists
	public void clearLists() {
		
		while (Deck.size() != 0) {
			
			Deck.remove(0);
			
		}//endWhile
		
		while (removedFromDeck.size() != 0) {
			
			removedFromDeck.remove(0);
			
		}//endWhile
		
		while (discardPile.size() != 0) {
			
			discardPile.remove(0);
			
		}//endWhile
		
		for (int x = 0; x < 13; x ++) {
			
			for (int y = 0; y < 14; y ++) {
				
				imagineField[x][y] = "  ";
				field[x][y] = "  ";
				
			}//endFor
			
		}//endFor
		
		for (int x = 0; x < 13; x ++) {
			
			for (int y = 0; y < 7; y ++) {
				
				scoreField[x][y] =  "  ";
				
			}//endFor
			
		}//endFor
		
		hand[0] = "  ";
		hand[1] = "  ";
		hand[2] = "  ";
		
	}//endMethod
	
	//Utility function that creates the new lists and arrays

	//Utility function to make a new deck & make new lists
	public void newLists() {
		
		createDeck();
		
		shuffleDeck();
		shuffleDeck();
		
		generateImagineField();
		generateField();
		generateScoreField();
		setupHand();
		
	}//endMethod
	
}//endClass
