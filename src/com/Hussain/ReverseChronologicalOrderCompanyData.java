package com.Hussain;

import java.util.Calendar;
/**
 * This is the class where the Yahoo! servers will be
 * queried and the resulting array will be reversed to
 * present the data in Reverse Chronological Order. This
 * class extends the class <code>ChronologicalCompanyData</code> this class adds one
 * extra method to reverse the array to represent the data as
 * reverse chronological order
 * @author Hussain
 */
public class ReverseChronologicalOrderCompanyData extends ChronologicalCompanyData {

	/**
	 * This is the constructor for the class ReverseChronologicalOrderCompanyData
	 * @param companyTicker This is the company ticker that the user has typed into the
	 * text field on the top level window.
	 * @param beginCalendar This is the begin date the user has chosen on the top
	 * level window.
	 * @param endCalendar This is the end date the user has chosen on the top 
	 * level window.
	 * @param interval This is the interval that the user has chosen, from the 
	 * choices: "Monthly", "Weekly" and "Daily".
	 * @throws Exception This method will throw an exception
	 * if there is no data.
	 */
	public ReverseChronologicalOrderCompanyData(String companyTicker,Calendar beginCalendar, Calendar endCalendar, String interval)throws Exception {
		super(companyTicker, beginCalendar, endCalendar, interval);//Call the constructor of the super class
		reverseArray();
	}//end constructor for the class ReverseChronologicalOrderCompanyData
	
	/**
	 * This is a method to reverse the
	 * company data array to represent the array data in reverse chronological order
	 */
	private void reverseArray(){
		String [][] companyReverse = new String[this.companyData.length][this.columnHeadings.length];
		int count1 = 0;
		for(int row = this.companyData.length - 1; row >= 0; row --)
		{
			for(int col = 0; col <= this.columnHeadings.length - 1; col++)
			{
				companyReverse[count1][col] = this.companyData[row][col];
			}
			count1++;
		}
		/*
		 * Make the company data array into the new
		 * reverse array
		 */
		this.companyData = companyReverse;
	}//end method reverseArray
}//end class ReverseChronologicalOrderCompanyData
