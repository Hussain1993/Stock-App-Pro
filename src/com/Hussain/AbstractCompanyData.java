package com.Hussain;

import java.math.BigDecimal;
import java.util.Calendar;
/**
 * This is the abstract class for querying 
 * the Yahoo! servers, where there are concrete methods
 * to return the data that was returned by the servers
 * but there is also the abstract method <code>getData</code>> 
 * which will have a concrete implementation in a
 * class that will extend it.
 * @author Hussain
 *
 */
public abstract class AbstractCompanyData {
	protected String data;
	protected String [] firstSplit;//This is the array that will hold the data after the first split ("\n")
	protected String [] secondSplit;//This is the array that will hold the data after the second split (",")
	protected String [][] companyData;//This is the company data represented in a 2D array to be used in a JTable
	protected String [] columnHeadings;//This is the column headings for the table
	
	protected int count = 0;
	protected String temp = "";
	protected String companyTicker;
	protected Calendar beginCal;
	protected Calendar endCal;
	protected String inteval;
	
	protected String maximumDrawdown;
	
	/**
	 * This method will return the column heading that were
	 * returned by the Yahoo! servers
	 * @return Column heading for the table
	 */
	public String[] getColumnHeadings(){
		return this.columnHeadings;
	}//end method getColumnHeading
	
	/**
	 * This will return the company data
	 * that was returned by the servers
	 * @return The company data represented as
	 * a 2D array to be used in a JTable
	 */
	public String [][] getCompanyData(){
		return this.companyData;
	}//end method getCompanyData
	
	/**
	 * This is a method to work out the
	 * average of the Adjusted Close price
	 * @return The average of the Adjusted Close price
	 * as a String
	 */
	public String getAverage(){
		double total = 0;
		double average = 0;
		for(int row = 0; row < companyData.length; row++)
		{
			total =  total + Double.parseDouble(companyData[row][6]);
		}
		average = total / companyData.length;
		return roundNumber(average);//Round the average
	}//end method getAverage
	
	/**
	 * This method will calculate the
	 * maximum drawdown for a company, when the
	 * data is in chronological order
	 * because the class <code>ReverseChronologicalOrderCompanyData</code> extends
	 * this class and calls the constructor of the super class
	 * this method will be called to calculate the right maximum drawdown
	 */
	protected void calculateMaximumDrawdown(){
		double maximumDrawdown = 0;
		double peak = -99999;
		double drawDown = 0;
		int columnAdjClose = 6;
		for(int row = 0; row < companyData.length; row++)
		{
			double currentAdjClose = Double.parseDouble(companyData[row][columnAdjClose]);
			if(currentAdjClose > peak)
			{
				peak = currentAdjClose;
			}
			else
			{
				drawDown = peak - currentAdjClose;
				if(drawDown > maximumDrawdown)
				{
					maximumDrawdown = drawDown;
				}
			}
		}
		this.maximumDrawdown = roundNumber(maximumDrawdown);
	}//end method calculateMaximumDrawdown
	
	/**
	 * This a method that will round
	 * any double value to two decimal places
	 * @param numberToRound This is the number to round
	 * @return The rounded number to two decimal places
	 * and as a String.
	 */
	public String roundNumber(double numberToRound){
		BigDecimal number = new BigDecimal(numberToRound);
		return number.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}//end method roundNumber
	
	/**
	 * This will return the maximum
	 * drawdown for the company
	 * @return The maximum drawdown for the
	 * company as a String
	 */
	public String getMaxDrawDown(){
		return this.maximumDrawdown;
	}//end method getMaxDrawDown
	
	/**
	 * This is the method where the Yahoo! servers will
	 * be queried and will be parsed so that there are
	 * two arrays One with the column headings and the other
	 * will be a 2D array which will hold the company data
	 * @throws Exception This method will throw an exception
	 * if the data returned by the servers is a null, there may
	 * be several reasons why the data returned by the
	 * servers is null one is that there is not Internet
	 * connection and the other is that the company does not
	 * exists or on the other hand the other reason why the 
	 * data returned by the server is a null is because the user
	 * has not specified a large enough span to collect data 
	 */
	abstract void getData() throws Exception;
}//end class AbstractCompanyData
