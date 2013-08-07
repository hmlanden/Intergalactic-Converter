/* File: ConverterMain.java
 *
 * This program serves as a currency converter to be used with "intergalactic"
 * currency to convert it into an understandable form of "credits" that
 * the average earthling can understand. Because we're dealing with the value of ores
 * and the value of any currency of any kind can vary, the program first asks for the 
 * current values of iron, silver, and gold ores.
 *
 * This class, Main, focuses on getting initial data from the user and communicating
 * instructions.
 *
 * ADDITIONAL CLASSES/INTERFACE:
 * This program has two classes and one interface to support its function. 
 * --Class #1: Converter--This class contains functions to convert between normal
 *   numbers, Roman numerals, and "intergalactic" Roman numerals, and then calculate the
 *   value of any amount of ore in credits.
 * --Class #2: ConverterValidityChecks--This class contains all of the validity checks
 * 	 that are performed on user input throughout the program's interactions with the user.
 * --Interface: ConverterConstants--This class contains all constants used throughout
 *   all classes.
 */

import java.io.*;


public class ConverterMain implements ConverterConstants {

	/* MAIN FUNCTION:
	 * This function runs the converter program. It obtains values for the
	 * three pertinent ores and then allows the user to convert units/numbers
	 * until he wants to quit.
	 */
	public static void main(String[] args) {		
		/*Introduce the program to the user.*/
		welcomeToUser();
		
		/*Obtain the current values of the three ores.*/
		double ironValue = getOreValue(IRON);
		double silverValue = getOreValue(SILVER);
		double goldValue = getOreValue(GOLD);

		/* This next part is where conversion happens and a result is 
		 * returned. The user is in control here. He chooses and inputs
		 * what to convert in what way. Returns a result and loops if 
		 * user wants to.
		 */
		while(true){
			//Get user's choice.
			char option = requestNextConversion();
			
			//Perform conversion and print result.
			Converter.performRequestedConversion(option, ironValue, silverValue, goldValue);
			
			//If the user wants to continue:
			if(!wantsToContinue()) break;
			System.out.print("Okay, let's go again! \n\n\n\n");
		}
		//If the user wants to quit:
		System.out.print("Thanks for playing! Bye!");
	}



	/*
	 *  Helper Functions
	 */

	
	
	/* Function: welcomeToUser()
	 * This function prints out a greeting to the user, explaining what the program is
	 * and what the first step in using it (obtaining ore values) is going to be.
	 */
	private static void welcomeToUser(){
		System.out.print("Welcome to the Intergalactic Converter! \n" +
				"My goal here is to help you figure out what all these intergalactic \n" +
				"units and whatnot actually mean, for you, in numbers you can understand. \n \n" +
				"Before we get started, I'll need to know what the current ore values are. \n" +
				"Let's go! \n");
	}

	
	/* Function: getOreValue(String)
	 * This function is used to ask for the value of all three ores (iron, silver, gold) by
	 * taking in a parameter of which ore it is currently asking about. It loops until the 
	 * user enters a valid number (a double) as input.
	 */
	private static double getOreValue(String oreName){
		String inputValue = null;
		double oreValue = 0;
		
		//Loops while we haven't obtained valid input.
		while(true){
			try{
				System.out.print("Enter the current value, in Credits, of " + oreName + " : ");
				InputStreamReader inputStream = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(inputStream);
				inputValue = br.readLine();
			}catch(IOException e){
				System.out.print("You have entered invalid input. Sorry!");
			}
			//If the input is valid:
			if(ConverterValidityChecks.isValidInput(CHECK_FOR_DOUBLE, inputValue)){
				oreValue = Double.parseDouble(inputValue);
				System.out.print("Thank you very muchly indeed! \n");
				break;
			}
			//If the input is invalid:
			System.out.print("Sorry, that wasn't valid input! Let's try again. \n");
		}
		return oreValue;
	}

	
	/* Function: requestNextConversion()
	 * This function asks the user what kind of conversion he would like to perform. There
	 * are three options: (A) units->numbers, (B) ore->credits, and (C) freeform.
	 */
	private static char requestNextConversion(){
		//Explain options to the user.
		giveUserOptions();
		
		String input = null;
		char option;
		
		//Loops until we have valid input.
		while(true){
			try{
				System.out.print("So, which conversion option do you want? ");
				InputStreamReader inputStream = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(inputStream);
				input = br.readLine().toUpperCase();
			}catch(IOException e){
				System.out.print("You have entered invalid input. Sorry!");
			}
			
			//If the user has selected one of the three options:
			if(ConverterValidityChecks.isValidInput(VALID_CHOICE, input)){
				option = input.charAt(0);
				System.out.print("Thanks! \n");
				break;
			}
			//If the user entered invalid input:
			System.out.print("Sorry, that wasn't one of the options I gave you. \n");
		}
		return option;
	}
	
	
	/* Function: giveUserOptions()
	 * This function prints out instructions for the user, explaining their options
	 * for conversion.
	 */
	private static void giveUserOptions(){
		System.out.print("So, I have three options that I can give you. \n");
		System.out.print("In the first two, I have some prompts to help you enter \n" +
						 "the data you want to be converted. If you want something \n" +
						 "more freeform, I would choose the last option, which lets \n" +
						 "you enter whatever you want to. \n \n");
		System.out.print("Your options are: \n" +
						 "A) Convert intergalactic units to normal numbers. \n" +
						 "B) Convert the value of an amount of an ore to Credits. \n" +
						 "C) Enter whatever you want and see what happens. \n");
	}
	
	
	/* Function: wantsToContinue()
	 * This function obtains user input on whether or not the user wishes to perform 
	 * another conversion. If the user wishes to quit, function returns false. Loops
	 * until user enters valid input.
	 */
	private static boolean wantsToContinue(){
		String choice = "";
		
		//Loops until user enters 'yes' or 'no'
		while(true){
			try{
				System.out.print("So do you wanna keep going? \n");
				System.out.print("Enter 'Yes' or 'No': ");
				InputStreamReader inputStream = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(inputStream);
				choice = br.readLine().toUpperCase();
			}catch(IOException e){
				System.out.print("You have entered invalid input. Sorry!");
			}
			
			//If the user has entered valid input:
			if(ConverterValidityChecks.isValidInput(YES_OR_NO, choice)){
				if(choice.equals("NO")) return false;
				return true;
			}
			//If the user entered invalid input:
			System.out.print("Sorry, that wasn't one of the options I gave you. \n");
		}
	}	
}