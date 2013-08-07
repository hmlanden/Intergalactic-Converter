/* File: Converter.java
 * 
 * This class has only one public function which is supported
 * by a variety of helper functions.
 * 
 * The primary function runs a conversion process, depending
 * on what type of conversion the user wanted to do.
 * 
 * The helper functions handle actual conversion between
 * intergalactic Roman numerals, Roman numerals, and normal numbers.
 */

import java.io.*;

public class Converter implements ConverterConstants{
	
	/* PRIMARY Function: performRequestedConversion(char, double, double, double)
	 * This function takes in what conversion the user wants to do, then sends
	 * the data the appropriate ways to do that conversion. Finally, it prints out the
	 * result for the user.*/
	public static void performRequestedConversion(char option, double ironValue,
			double silverValue, double goldValue){
		units = "";
		ore = "";
		double result = 0;

		//For conversion from units to numbers.
		if(option == UNITS_TO_NUMBER){
			result = convertUnitsToNumbers();
		}
		
		//For conversion from an amount of ore to value in Credits.
		if(option == ORE_TO_CREDITS){
			double numbers = convertUnitsToNumbers();
			getOre();
			result = calculateResult(numbers, ironValue, silverValue, goldValue);
		}
		
		//Print out result of conversion to console.
		reportResult(result, option);
	}


	/* HELPER FUNCTIONS.
	 * This section contains helper functions that help run the
	 * converter. They handle various conversions and calculations,
	 * as well as get user input and report results.
	 */
	
	
	/* Function: convertUnitsToNumbers()
	 * This function takes in intergalactic units and converts them
	 * to standard numerical form. Returns a double (the result of
	 * the conversion).
	 */
	private static double convertUnitsToNumbers(){
		getUnits();
		double result = convertToNumber();
		return result;
	}


	/* Function: getOre()
	 * This function obtains input from the user to find out which
	 * type of ore we're working with in conversions. It loops until
	 * the user enters valid input.
	 */
	private static void getOre(){
		String input = null;
		
		//Loops until obtains valid input.
		while(true){
			try{
				System.out.print("Enter what ore we're working with: ");
				
				InputStreamReader inputStream = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(inputStream);
				input = br.readLine();
			}catch(IOException e){
				System.out.print("You have entered invalid input. Sorry!");
			}
			
			//If is valid ore:
			if(ConverterValidityChecks.isValidInput(CHECK_ORE, input)){
				ore = input;
				break;
			}
			//If is invalid input:
			System.out.print("Sorry, that's not valid input! Try again. \n");
		}
	}


	/* Function: getUnits()
	 * This function takes in input from the user with regards to what sequence of
	 * intergalactic units they want to convert to normal numbers. It loops until
	 * the user inputs valid data.
	 */
	private static void getUnits(){
		String input = null;
		
		//Loops until user enters valid units.
		while(true){
			try{
				System.out.print("Enter the units you want to translate below. \n");
				System.out.print("How much is: ");
				
				InputStreamReader inputStream = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(inputStream);
				input = br.readLine();
			}catch(IOException e){
				System.out.print("You have entered invalid input. Sorry!");
			}
			
			//If input is entirely valid units:
			if(ConverterValidityChecks.isValidInput(CHECK_UNITS, input)){
				units = input;
				break;
			}
			
			//If input has any invalid units/input:
			System.out.print("Sorry, but that's not valid input. Let's try again, shall we? \n");
		}
	}


	/* Function: convertToNumber()
	 * This function obtains a normal Roman numeral version of the user's input,
	 * then checks its validity as a Roman numeral by iterating through and checking
	 * to make sure that the characters are in an order that is possible.
	 * 
	 * There are three options for what to do:
	 * --#Option 1: If the number is followed by a larger numeral, we need to subtract.
	 * --#Option 2: If the number has a smaller numeral before it, we need to do nothing, since
	 * 		 we are handling those calculations with Option #1.
	 * --#Option 3: We just add the number.
	 */
	private static double convertToNumber(){
		//Convert units to Roman numerals.
		String romanNumeral = "";
		romanNumeral = convertToRomanNumeral();
		
		double result = 0;
		
		//Iterate over every numeral in the sequence.
		for(int i = 0; i < romanNumeral.length(); i++){
			char current = romanNumeral.charAt(i);

			/* All options that involve checking the next/previous character include
			 * a guard to insure that we don't go outOfBounds. */
			
			//Option #1 for IV:
			if(i != romanNumeral.length() - 1 && current == GLOB_NUM && 
					romanNumeral.charAt(i + 1) == PROK_NUM){
				result += (NUMERAL_V - NUMERAL_I);
			}
			//Option #1 for IX:
			else if(i != romanNumeral.length() - 1 && current == GLOB_NUM && 
					romanNumeral.charAt(i + 1) == PISH_NUM){
				result += (NUMERAL_X - NUMERAL_V);
			}
			//Option #1 for XL:
			else if(i != romanNumeral.length() - 1 && current == PISH_NUM && 
					romanNumeral.charAt(i + 1) == TEGJ_NUM){
				result += (NUMERAL_L - NUMERAL_X);
			}
			//Option #3 for I:
			else if(current == GLOB_NUM) result += NUMERAL_I;

			//Option #2 & 3 for V: Only add it if it doesn't have a small character prior:
			else if(current == PROK_NUM){
				if(i != 0 && romanNumeral.charAt(i - 1) != GLOB_NUM) result += NUMERAL_V;
				else{
					result += NUMERAL_V;
				}
			}
			//Option #2 & 3 for X: Only add it if it doesn't have a small character prior:
			else if(current == PISH_NUM){
				if(i != 0 && romanNumeral.charAt(i - 1) != GLOB_NUM) result += NUMERAL_X;
				else{
					result += NUMERAL_X;
				}
			}
			//Option #2 & 3 for L: Only add it if it doesn't have a small character prior:
			else if(current == TEGJ_NUM){
				if(i != 0 && romanNumeral.charAt(i - 1) != PISH_NUM) result += NUMERAL_L;
				else{
					result += NUMERAL_L;
				}
			}
		}
		return result;
	}

	/* Function: createRomanNumeral
	 * This function parses the string of input, find the Roman numeral
	 * equivalent of that intergalactic unit, and creates the Roman
	 * numeral version of the input units from that.
	 */
	private static String convertToRomanNumeral(){
		String numeral = "";
		String currentUnit = "";

		//Iterates over string of input units.
		for(int i = 0; i < units.length(); i++){
			char current = units.charAt(i);

			//If we hit whitespace, we've found a complete word.
			if(Character.isWhitespace(current)){
				numeral = determineUnit(currentUnit.toUpperCase(), numeral);
				currentUnit = "";
			}
			
			//If we hit the end of the string.
			else if(i == units.length() - 1){
				currentUnit += current;
				numeral = determineUnit(currentUnit.toUpperCase(), numeral);
			}
			
			//Otherwise, add current character to the unit we're reconstructing
			else{
				currentUnit += current;
			}
		}
		return numeral;
	}


	/* Function: determineUnit(String)
	 * This function takes in the current unit we've found in the user's
	 * input and the Roman numeral we've assembled thus far. It determines 
	 * what Roman numeral value to append, appends it to a copy of the
	 * current numeral, and returns that new Roman numeral.
	 */
	private static String determineUnit(String currentUnit, String numeral){
		String result = numeral;
		
		if(currentUnit.equals(GLOB_UNIT)) result += GLOB_NUM;
		if(currentUnit.equals(PROK_UNIT)) result += PROK_NUM;
		if(currentUnit.equals(PISH_UNIT)) result += PISH_NUM;
		if(currentUnit.equals(TEGJ_UNIT)) result += TEGJ_NUM;
		
		return result;
	}

	/* Function: reportResult(double)
	 * This function prints out the result to the user, depending on
	 * what conversion they were doing. Selects appropriate printing
	 * format for response based on what conversion it was.
	 */
	private static void reportResult(double result, char option){
		System.out.print("Conversion complete! \n");

		//Conversion was units to number:
		if(option == UNITS_TO_NUMBER){
			System.out.print(units + " is " + result + ".\n");
		}
		
		//Conversion was ore to credits:
		else if(option == ORE_TO_CREDITS){
			System.out.print(units + " " + ore.toLowerCase() + " is " + result + ".\n");
		}
		
		//The user took the third option.
		else{
			System.out.print("Sorry, I have no idea what you're talking about. \n" +
			"I'm only a computer--not a god, you know.\n");
		}
	}


	/* Function: calculateResult(double,double,double)
	 * This functions returns the result of multiplying the appropriate
	 * ore value (as obtained from the instance variable value of ore)
	 * by the number of units of it.
	 */
	private static double calculateResult(double units, double ironValue, 
			double silverValue, double goldValue){
		double oreValue = 0;

		//Select appropriate ore value.
		if(ore.equalsIgnoreCase(IRON)) oreValue = ironValue;
		if(ore.equalsIgnoreCase(SILVER)) oreValue = silverValue;
		if(ore.equalsIgnoreCase(GOLD)) oreValue = goldValue;

		//Multiply ore value by number of units and return result.
		return units * oreValue;
	}



	/* INSTANCE VARIABLES:
	 * Because units and ore information are used in multiple functions, it
	 * seemed easier to just make them instance variables.
	 */
	private static String units;
	private static String ore;
}