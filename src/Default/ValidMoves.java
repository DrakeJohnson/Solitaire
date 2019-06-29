package Default;

public class ValidMoves {

	//Variables
	
	//Objects
	String[] cardOrder;
	
	//Constructor
	//When creating a class objects, set the list
	public ValidMoves() {
		
		cardOrder = new String[13];
		
		//Setting Cards
		cardOrder[0] = "A";
		cardOrder[1] = "2";
		cardOrder[2] = "3";
		cardOrder[3] = "4";
		cardOrder[4] = "5";
		cardOrder[5] = "6";
		cardOrder[6] = "7";
		cardOrder[7] = "8";
		cardOrder[8] = "9";
		cardOrder[9] = "1";
		cardOrder[10] = "J";
		cardOrder[11] = "Q";
		cardOrder[12] = "K";
		
	}//endConstructor
	
	
	// Functions //
		
	
	//Function to check if the card chosen to score is valid
	public boolean checkScoring(String[][] scoreField, String card) {
		
		//Variables
		boolean valid = false;
		int position = -1;
		
		//Compare the card to the order list to find its position
		for (int x = 0; x < 13; x ++) {
			
			if (cardOrder[x].charAt(0) == card.charAt(0)) {
				
				position = x;
				System.out.println(x);
				
			}//endIf
			
		}//endFor
		
		/*
		 * Check if the position is an ace, then return true
		 * Else check if there are previous cards so that the position can be added, then return true
		 * Else return false
		 */
		
		if (position == 0) {
			
			valid = true;
			
		} else if (position == -1) {
			
			return valid;
			
		} else if (checkScoreField(scoreField, card, position)){
			
			valid = true;
			
		}//endIf
		
		return valid;
		
	}//endMethod
	
	//Utility function to check if there are previous cards in the 2D array
	public boolean checkScoreField(String[][] scoreField, String card, int pos) {
		
		//Variables
		boolean valid = false;
		int column = 0;
		int counter = 0;
		
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
		
		if (card.charAt(1) == 'S') {
			
			column = 0;
			
		} else if (card.charAt(1) == 'C') {
			
			column = 2;
			
		} else if (card.charAt(1) == 'D') {
			
			column = 4;
			
		} else if (card.charAt(1) == 'H') {
			
			column = 6;
			
		}//endIf
		
		System.out.println(column);
		
		//Then checks what the current score in that suit is at by evaluating that specific column
		//until a "  " is reached then report the current index
		while (!scoreField[counter][column].equals("  ")) {
			
			counter ++;
			
		}//endWhile
		
		System.out.println(counter + "  " + pos);
		
		//Then counter will equal the last index before a "  " happens
		//If that index is the position then it is a valid placement
		if (counter == pos) {
			
			valid = true;
			
		}//endIf
		
		return valid;
		
	}//endMethod
	
}//endClass
