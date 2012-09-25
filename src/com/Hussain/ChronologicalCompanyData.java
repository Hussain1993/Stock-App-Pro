package com.Hussain;

import java.util.Calendar;
/**
 * This is the class where the Yahoo! servers will
 * be queried and the company data will be parsed so that 
 * it will be ordered in chronological order
 * @author Hussain
 *
 */
public class ChronologicalCompanyData extends AbstractCompanyData {
	/**
	 * This is the constructor for the class ChronologicalCompanyData
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
	public ChronologicalCompanyData(String companyTicker, Calendar beginCalendar, Calendar endCalendar, String interval) throws Exception{
		this.companyTicker = companyTicker;
		this.beginCal = beginCalendar;
		this.endCal = endCalendar;
		this.inteval = interval;
		this.getData();
	}//end constructor for the class ChronologicalCompanyData
            
	/*
	 * (non-Javadoc)
	 * @see com.Hussain.AbstractCompanyData#getData()
	 */
	@Override
	void getData() throws Exception {
		/*
		 * Get the first letter from the interval
		 * the user has chosen and turn it
		 * into a lower case letter to be used in the query
		 */
		String intervalFirstLetter = this.inteval.substring(0, 1).toLowerCase();
		this.data = URLReader.readURL("http://ichart.yahoo.com/table.csv?s="+companyTicker+"&a="+this.beginCal.get(Calendar.MONTH)+"&b="+beginCal.get(Calendar.DAY_OF_MONTH)+"&c="+beginCal.get(Calendar.YEAR)+"&d="+this.endCal.get(Calendar.MONTH)+"&e="+endCal.get(Calendar.DAY_OF_MONTH)+"&f="+this.endCal.get(Calendar.YEAR)+"&g="+intervalFirstLetter);
		if(data != null)
		{
			/*
			 * First split the data with the regular expression "\n"
			 */
			this.firstSplit =  data.split("\n");
			/*
			 * The first item in the array is the column headings
			 * and this will be split using the comma regular
			 * expression and placed into the array columnHeadings
			 */
			this.columnHeadings = firstSplit[0].split(",");
			/*
			 * This for loop starts at the index 1 because as
			 * as explained before the first element in the
			 * array is the column headings and this not part of the
			 * company data.
			 */
			for(int i = 1; i < this.firstSplit.length; i++)
			{
				this.temp = firstSplit[i] + "," + temp;
			}
			this.secondSplit = temp.split(",");
			this.companyData =  new String[firstSplit.length -1][columnHeadings.length];
			/*
			 * The following nested for loop creates a 2D array
			 * of the data that was returned by the Yahoo! servers
			 */
			for(int row = 0; row < firstSplit.length -1; row++)
			{
				for(int col = 0; col < columnHeadings.length; col++)
				{
					companyData[row][col] = secondSplit[count];
					count++;
				}
			}
			calculateMaximumDrawdown();
		}
		else
		{
			/*
			 * Throw an error if the data returned is a
			 * null
			 */
			throw new Exception("There was an error");
		}
	}//end method getData
}//end class ChronologicalCompanyData
