package Default;

import java.util.ArrayList;

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
	public boolean checkScoring(ArrayList<String> scoreField, String card) {
		
		//Variables
		boolean valid = false;
		
		//Compare the card to the order list to find its position
		
		
		
		return valid;
		
	}//endMethod
	
	
	
	
}//endClass
