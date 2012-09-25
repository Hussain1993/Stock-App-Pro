package com.Hussain;
import java.util.Calendar;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 * This is class with static methods that will
 * be used to check the validation of the
 * data the user has entered, such validation will be
 * checking that the input is not empty and also seeing if
 * the dates entered by the user are valid dates.
 * @author Hussain
 *
 */
public class Validation {
	/*
	 * This is the complication of a regular
	 * expression into a Pattern Object. This
	 * regular expression excepts strings that follow these rules:
	 * The string can only contain upper case letters and numbers
	 * and also the full stop character no other characters will be
	 * accepted.
	 */
	private static final Pattern pattern = Pattern.compile("^[A-Z0-9\\.]+$");
	
	/**
	 * This is a method to check if the input string
	 * is empty
	 * @param stringToCheck This is the string to check, it is the user input
	 * @throws Exception Throws an exception if the input is empty
	 */
	private static void isStringEmpty(String stringToCheck) throws Exception{
		if(stringToCheck.isEmpty())
		{
			throw new Exception("The input is empty");
		}
	}//end method isStringEmpty
	
	/**
	 * This is a method to check if the input text
	 * is greater than some threshold.
	 * @param stringToCheck This is the user input
	 * @param lengthCheck This is the upper bound of the
	 * length of the string
	 * @throws Exception This method will throw an exception 
	 * if the length of the string is greater than <code>lengthCheck</code>
	 */
	private static void checkLengthOfString(String stringToCheck, int lengthCheck) throws Exception{
		if(stringToCheck.length() > lengthCheck)
		{
			throw new Exception("The input: \""+stringToCheck+"\" is larger than "+ lengthCheck);
		}
	}//end method checkLengthOfStirng
	
	/**
	 * This is method to check that the input 
	 * text follows the format of the regular
	 * expression
	 * @param stringToCheck This is the user input
	 * @throws Exception This method will throw an exception
	 * if the string entered by the user does not match
	 * the regular expression
	 */
	private static  void checkFormat(String stringToCheck)throws Exception{
		Matcher matcher = pattern.matcher(stringToCheck);
		if(matcher.find() == false)
		{
			throw new Exception("The input can only be upper case letters numbers and full stops");
		}
	}//end method checkFormat
	
	/**
	 * This method checks if the end date the user has chosen
	 * is before the begin date the user has chosen as this is
	 * clearly a invalid date to have as the end date for the
	 * stock symbol
	 * @param beginCalendar This is the begin date the user has entered
	 * @param endCalendar This is the end date the user has entered
	 * @throws Exception This method will throw an exception
	 * if the end date is before the begin date
	 */
	public static void checkDateChosen(Calendar beginCalendar, Calendar endCalendar)throws Exception{
		if(endCalendar.before(beginCalendar))
		{
			throw new Exception("The end date is before the begin date");
		}
	}//end method checkDateChosen
	
	/**
	 * This is method that will call various
	 * other methods to check that the input the user
	 * has entered is valid.
	 * @param stringToCheck This is the user input
	 * @param lengthCheck This is the maximum length that the
	 * user input string can be.
	 * @throws Exception This method will throw an exception if any
	 * of the validation checks fail.
	 */
	public static void validateInput(String stringToCheck, int lengthCheck)throws Exception{
		isStringEmpty(stringToCheck);
		checkLengthOfString(stringToCheck, lengthCheck);
		checkFormat(stringToCheck);
	}//end method validateInput
}//end class Validation
