/* File: ConverterValidityChecks.java
 * 
 * This class contains all functions that check the 
 * validity of user input. It contains one public function
 * that serves as a jumping-off point for all validity checks,
 * directing them to appropriate private methods.
 *
 */

public class ConverterValidityChecks implements ConverterConstants {
	
	/* PRIMARY Function: isValidInput(int, String)
	 * This function checks to see if the input is, in fact, valid for 
	 * the purpose for which it will be used. It is designed to be 
	 * multifunctional, taking in a variety of input data and checking
	 * its validity based on the requirements of the option selected
	 * when the function was called.
	 */
	public static boolean isValidInput(int optionNumber, String input){
		//Verify that the input is a double.
		if(optionNumber == CHECK_FOR_DOUBLE){
			if(isDouble(input)) return true;
		}

		//Check if the user selected one of the three options provided.
		if(optionNumber == VALID_CHOICE){
			if(isValidChoice(input)) return true;
		}

		//Check if the answer is yes or no:
		if(optionNumber == YES_OR_NO){
			if(input.equals("YES") || !input.equals("NO")) return true;
		}

		//Check if the input from the user is valid prior to performing calculations:
		if(optionNumber == CHECK_UNITS) {
			if(verifyUnits(input)) return true;
		}
		
		if(optionNumber == CHECK_ORE){
			if(isValidOre(input)) return true;
		}

		//Check to see if it's actually a valid Roman numeral
		if(optionNumber == VALID_NUMERAL){
			if(verifyRomanNumeral(input)) return true;
		}

		return false;
	}


	/* Function: isDouble(String)
	 * This function iterates over a given string to verify that all numbers 
	 * provided are, in fact, able to be converted to a double form.
	 */
	private static boolean isDouble(String input){
		//There can be one decimal point in this value, so we need to keep track of it.
		int decimalCounter = 0;

		//For each character in the input:
		for(int i = 0; i < input.length(); i++){
			char current = input.charAt(i);

			if(current == DECIMAL) decimalCounter ++;
			if(decimalCounter > 1) return false;

			//If character is any punctuation/symbol:
			if(isPunctuationOrSymbol(current) && current != DECIMAL) return false;

			//If the character is a letter or whitespace:
			if(Character.isLetter(current) || 
			   Character.isWhitespace(current)) return false;
		}
		return true;
	}


	/* Function: isPunctuationOrSymbol(char)
	 * This boolean function checks to see if a character is any of the various 
	 * punctuation marks or symbols used in the English language. It misses a few,  
	 * namely the ' single quotation mark and the \ backslash, because they aren't 
	 * valid characters.
	 */
	private static boolean isPunctuationOrSymbol(char ch){
		return ch == ',' || ch == '/' || ch == '<' ||
		ch == '>' || ch == '?' || ch == ':' ||
		ch == ';' || ch == '"' || ch == '|' ||
		ch == '[' || ch == '{' || ch == ']' ||
		ch == '}' || ch == '+' || ch == '=' ||
		ch == '-' || ch == '_' || ch == ')' ||
		ch == '(' || ch == '*' || ch == '&' ||
		ch == '^' || ch == '%' || ch == '$' ||
		ch == '#' || ch == '@' || ch == '!' ||
		ch == '~' || ch == '`' || ch == '.';
	}
	
	/* Function: isValidChoice(String)
	 * This function checks to see if the user entered one of the three choices
	 * available to them for input. If not, it returns false.
	 */
	private static boolean isValidChoice(String input){
		//Entered more than one character.
		if(input.length() > 1) return false;

		//If they entered one of the three character options
		if(input.charAt(0) == ORE_TO_CREDITS ||
		   input.charAt(0) == UNITS_TO_NUMBER ||
		   input.charAt(0) == FREE_FOR_ALL) return true;
		
		//If we haven't returned already, there's a problem.
		return false;
	}


	/* Function: verifyUnits(String)
	 * This boolean function checks to make sure that the units the user entered
	 * are actually valid intergalactic units. If they're not, the function
	 * returns as false. It does so by assembling words, checking to see if 
	 * they're valid units, and then blanking out the variable used to
	 * assemble the word.
	 */
	private static boolean verifyUnits(String input){
		String wordTracker = "";
		input = input.toUpperCase();
		
		//Iterate over entire string.
		for(int i = 0; i < input.length(); i++){
			char current = input.charAt(i);

			//If we hit anything other than a letter or space:
			if(isPunctuationOrSymbol(current)) return false;
			
			//If we hit whitespace, we have a word.
			if(Character.isWhitespace(current)){
				
				//If it's not one of the units:
				if(!wordTracker.equals(GLOB_UNIT) && 
				   !wordTracker.equals(PROK_UNIT) && 
				   !wordTracker.equals(PISH_UNIT) && 
				   !wordTracker.equals(TEGJ_UNIT)) return false;
				
				//If it is, blank out the word tracker and keep going.
				wordTracker = "";
			}
			
			//If we've hit the end of the string:
			else if(i == input.length() - 1){
				wordTracker += current;
				
				//If it's not one of the units:
				if(!wordTracker.equals(GLOB_UNIT) && 
				   !wordTracker.equals(PROK_UNIT) && 
				   !wordTracker.equals(PISH_UNIT) && 
				   !wordTracker.equals(TEGJ_UNIT)) return false;
			}
			
			//Add character to the word tracker.
			else{
				wordTracker += current;
			}
		}
		
		//If we make it through without returning false, it's valid input.
		return true;
	}
	
	/* Function: isValidOre(String)
	 * This function takes in user input and returns whether it is one of the three
	 * valid ore options.
	 */
private static boolean isValidOre(String input){
	return input.equalsIgnoreCase(SILVER) || input.equalsIgnoreCase(GOLD) || 
		   input.equalsIgnoreCase(IRON);
}

	/* Function: verifyRomanNumeral(String)
	 * This function takes in a Roman numeral and processes it, according to the
	 * rules that govern Roman numerals, to see if it's actually a valid Roman
	 * numeral.
	 */
	private static boolean verifyRomanNumeral(String input){
		for(int i = 0; i < input.length(); i++){
			char current = input.charAt(i);
			
			//Check validity based on what current character is.
			if(current == GLOB_NUM){
				if(!checkThreeNumeral(i, GLOB_NUM, input)) return false;
			}else if(current == PROK_NUM){
				if(!checkOneNumeral(i, PROK_NUM, input)) return false;
			}else if(current == PISH_NUM){
				if(!checkThreeNumeral(i, PISH_NUM, input)) return false;
			}
			else{
				if(!checkOneNumeral(i, TEGJ_NUM, input)) return false;
			}
		}
		return true;
	}


	/* Function: checkThreeNumeral(char,string)
	 * This function takes in numerals that are allowed to appear in patterns where
	 * they can be three in a row, hence the name: checkThreeNumeral. This function
	 * performs checks that are specific to each Three Numeral  (I and X) and then
	 * moves on to the general check function, which checks for problems common to
	 * all Three Numerals.
	 */
	private static boolean checkThreeNumeral(int index, char numeral, String input){
		/*Checks for I, or GLOB_NUM*/
		if(numeral == GLOB_NUM){
			
			//If the I is the last character, we're golden.
			if(index == input.length() - 1) return true;

			//Perform general check.
			if(!verifyThreeNumeral(GLOB_NUM, index, TEGJ_NUM, input)) return false;
		}

		/*Checks for X, or PISH_NUM*/
		if(numeral == PISH_NUM){
			
			//Perform general check.
			if(!verifyThreeNumeral(PISH_NUM, index, ' ', input)) return false;
		}
		return true;
	}


	/* Function: checkNumeralI(int, String)
	 * This function checks for the various problems that can arise with
	 * the numeral I in a Roman numeral sequence. For example, more than
	 * four in a row, an I before an L.
	 */
	private static boolean verifyThreeNumeral(char currentUnit, int index, char problem, 
											  String input){
		//If an character is followed by a character it can't be followed by:
		if(index < input.length() - 1 && input.charAt(index + 1) == problem) return false;

		//Check for more-than-3-in-a-row and 2-then-subtraction attempt.
		int numeralCounter = 0;
		for(int i = index; i < input.length() - index; i ++){
			char current = input.charAt(i);
			if(current == currentUnit) numeralCounter ++;

			//If we have more than 4 in a row.
			if(numeralCounter > 3) return false;

			//If we're looking at an I that isn't the last character:
			if(current == currentUnit && i != (input.length() - index)){

				//If we have 2 or more I, followed by a non-I numeral:
				if(numeralCounter >= 2 && input.charAt(i + 1) != currentUnit) return false;
			}
			//If we hit a non-I, reset the counter.
			if(current != currentUnit) numeralCounter = 0;	
		}
		return true;
	}


	/* Function: checkOneNumeral(char, String)
	 * This function deals with numerals that aren't allowed to repeat at all. They 
	 * can be before basically anything, but they can't be repeated.
	 */
	private static boolean checkOneNumeral(int index, char numeral, String input){
		//Only check if it's not the last numeral, since they're always fine then.
		if(index != input.length() - 1){
			
			//Checks for V, to see if it's followed by either X or L.
			if(numeral == PROK_NUM){
				char problem = PISH_NUM;
				if(isFollowedByLarger(index, numeral, problem, input)) return false;
				
				problem = TEGJ_NUM;
				if(isFollowedByLarger(index, numeral, problem, input)) return false;
			}
			
			//If it's not repeated:
			if(input.charAt(index + 1) != numeral) return true;
		}
		return false;
	}

	/* Function: isFollowedByLarger(int, char, char, String)
	 * This function checks to see if a numeral is followed by a larger
	 * Roman numeral. 
	 */
	private static boolean isFollowedByLarger(int index, char numeral, char larger, 
			                                  String input){
		return input.charAt(index + 1) == larger;
	}
}