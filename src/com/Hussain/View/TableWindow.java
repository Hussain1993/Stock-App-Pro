package com.Hussain.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import com.Hussain.AbstractCompanyData;
/**
 * This is the class where the window 
 * to display the data to the user will be created
 * @author Hussain
 *
 */
public class TableWindow extends JFrame {
	private AbstractCompanyData company;
	private JPanel panelCenter;
	private JTable dataTable;
	
	private JPanel panelSouth;
	private JLabel statusLabel;
	
	private boolean chronological;
	
	/**
	 * This is the constructor for the class TableWindow
	 * @param windowTitle This is the window title
	 * @param company This is the company data
	 * @param chronological This is a boolean variable which
	 * determines whether the data is in chronological or
	 * reverse chronological order
	 */
	public TableWindow(String windowTitle, AbstractCompanyData company, boolean chronological){
		super(windowTitle);
		this.company = company;
		this.chronological = chronological;
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(640,480));
		initWidgets();
		setLayout();
		addWidgets();
		pack();
	}//end constructor for the class TableWindow
	
	/**
	 * This is a method to initialize the
	 * variables
	 */
	private void initWidgets(){
		panelCenter = new JPanel();
		panelSouth = new JPanel();
		dataTable = new JTable(new CompanyDataModel(company));
		dataTable.setGridColor(Color.BLACK);
		TableColumn col = dataTable.getColumnModel().getColumn(6);
		col.setCellRenderer(new CellRenderer(this.chronological));//Set the cell renderer for column 6, it is only this column because this is the column that needs to be coloured
		statusLabel = new JLabel("Average Adj.Close: "+company.getAverage()+" | Maximum Drawdown: "+company.getMaxDrawDown());
		addWindowListener(new WindowAction(this));
	}//end method initWidgets
	
	/**
	 * This is method to set the
	 * layout of the window
	 */
	private void setLayout(){
		this.setLayout(new BorderLayout());
		
		panelCenter.setLayout(new GridLayout(1, 1));
		
		panelSouth.setLayout(new FlowLayout(FlowLayout.LEFT));
	}//end method setLayout
	
	/**
	 * This is a method to add the
	 * widgets to the window
	 */
	private void addWidgets(){
		this.add(panelCenter,BorderLayout.CENTER);
		this.add(panelSouth,BorderLayout.SOUTH);
		
		panelCenter.add(new JScrollPane(dataTable));
		panelSouth.add(statusLabel);
	}//end method addWidgets
}//end class TableWindow
