package com.Hussain.View;

import javax.swing.table.AbstractTableModel;

import com.Hussain.AbstractCompanyData;
/**
 * This is the class where the table model
 * for the table that will be used to display the data
 * to the user will be defined. 
 * @author Hussain
 *
 */
public class CompanyDataModel extends AbstractTableModel {
	private String[] colummnHeading;
	private String[][] companyData;
	
	/**
	 * This is the constructor for the class CompanyDataModel
	 * @param company This is a AbstarctCompanyData object so
	 * the column and headings of the table can be populated with
	 * data
	 */
	public CompanyDataModel(AbstractCompanyData company){
		colummnHeading = company.getColumnHeadings();
		companyData = company.getCompanyData();
	}//end constructor for the class CompanyDataModel
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int col){
		return colummnHeading[col];
	}//end method getColumnName
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return colummnHeading.length;
	}//end method getColumnCount

	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return companyData.length;
	}//end method getRowCount

	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int row, int col) {
		return companyData[row][col];
	}//end method getValueAt

}//end class CompanyDataModel
