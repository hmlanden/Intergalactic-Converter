/* File: ConverterConstants.java
 * 
 * This file contains all constants that are used in the Converter
 * program, including the values of Intergalactic Roman numerals and
 * the various options for multipurpose functions.
 */

public interface ConverterConstants {
		
		/* INTERGALACTIC ROMAN NUMERALS
		 * These four constants define the Roman numeral equivalents of the
		 * "intergalactic" Roman numerals.
		 */
		public static final char GLOB_NUM = 'I';
		public static final char PROK_NUM = 'V';
		public static final char PISH_NUM = 'X';
		public static final char TEGJ_NUM = 'L';
		
		/* INTERGALACTIC UNITS
		 * These four constants simply define the names of the different
		 * intergalactic units of measurement.
		 */
		public static final String GLOB_UNIT = "GLOB";
		public static final String PROK_UNIT = "PROK";
		public static final String PISH_UNIT = "PISH";
		public static final String TEGJ_UNIT = "TEGJ";
		
		/* ROMAN NUMERALS
		 * These three constants define the values of Roman numerals.
		 */
		public static final int NUMERAL_I = 1;
		public static final int NUMERAL_V = 5;
		public static final int NUMERAL_X = 10;
		public static final int NUMERAL_L = 50;
		
		/* CONVERSION OPTIONS
		 * These three constants give the three option "numbers" for conversion.
		 */
		public final static char UNITS_TO_NUMBER = 'A';
		public final static char ORE_TO_CREDITS = 'B';
		public final static char FREE_FOR_ALL = 'C';
		
		/* VALIDITY CHECK OPTIONS
		 * These three constants give the options for what checks
		 * can be performed for validity.
		 */
		public final static int CHECK_FOR_DOUBLE = 111;
		public final static int VALID_CHOICE = 222;
		public final static int YES_OR_NO = 333;
		public final static int CHECK_UNITS = 444;
		public final static int CHECK_ORE = 555;
		public final static int VALID_NUMERAL = 666;
		
		
		/* ORES
		 * These three constants are for the names of the three types of
		 * ore that are relevant to the converter.
		 */
		public final static String IRON = "iron";
		public final static String SILVER = "silver";
		public final static String GOLD = "gold";
		
		/* OTHER CONSTANTS
		 * Used to see if there's more than one decimal point in any input.
		 */
		final static char DECIMAL = '.';
}