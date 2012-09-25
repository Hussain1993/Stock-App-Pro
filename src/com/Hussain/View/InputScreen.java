package com.Hussain.View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.Hussain.ChronologicalCompanyData;
import com.Hussain.ReverseChronologicalOrderCompanyData;
import com.Hussain.Validation;
/**
 * This is the class for the top level window for the user interface 
 * where the user will have the chance to enter the stock symbol
 * that the user will want to query, also choosing begin and end dates
 * for query and also choosing the interval that they would like.
 * 
 * @author Hussain
 */
public class InputScreen extends JFrame implements ActionListener{
	private final String [] months = {"January", "February","March","April","May","June","July","August","September","October","November","December"};
	private final String [] choices = {"Monthly","Weekly","Daily"};
	private final ArrayList<Integer> yearsList = new ArrayList<Integer>();
	
    private Calendar currentCalendar = Calendar.getInstance();//This is the calendar with "today's" current date
    private Calendar beginCalendar = Calendar.getInstance();
    private Calendar endCalendar = Calendar.getInstance();
    
    private JPanel panelNorth;
    private JTextField stockTextField;
    
    private JPanel panelCenter;
    private JComboBox dayBegin;
    private JComboBox monthBegin;
    private JComboBox yearBegin;
    
    private JComboBox dayEnd;
    private JComboBox monthEnd;
    private JComboBox yearEnd;
    
    private JComboBox intervalComboBox;
    
    private JCheckBox chronologicalChooser;
    
    private JPanel panelSouth;
    private JButton lookUpButton;
    
    /**
     * This is the constructor for the class
     * InputScreen
     */
    public InputScreen(){
    	super("Stock App Pro");
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	this.setYears();
    	this.initWidgets();
    	this.setLayout();
    	this.addWidgets();
    	pack();
    }//end constructor for the class InputScreen
    
    /**
     * This is the method where all the
     * widgets will be initialised
     */
    private void initWidgets(){
    	this.panelNorth = new JPanel();
    	this.stockTextField  = new JTextField();
    	
    	this.panelCenter = new JPanel();
    	this.dayBegin = new JComboBox();
    	this.monthBegin = new JComboBox(months);
    	this.yearBegin = new JComboBox(yearsList.toArray());
    	
    	this.dayEnd = new JComboBox();
    	this.monthEnd = new JComboBox(months);
    	this.yearEnd = new JComboBox(yearsList.toArray());
    	
    	this.intervalComboBox = new JComboBox(choices);
    	
    	this.panelSouth = new JPanel();
    	this.lookUpButton = new JButton("Look Up");
    	
    	chronologicalChooser = new JCheckBox("Chronological");
    	
    	monthBegin.setSelectedIndex(0);
    	yearBegin.setSelectedItem(2000);
    	setDays(dayBegin, monthBegin);
    	dayBegin.setSelectedIndex(0);
    	
    	monthEnd.setSelectedIndex(this.getMonth());
    	yearEnd.setSelectedItem(this.getYear());
    	setDays(dayEnd, monthEnd);
    	dayEnd.setSelectedItem(this.getDay());
    	
    	intervalComboBox.setSelectedIndex(0);
    	
    	/*
    	 * These are the anonymous inner classes that will
    	 * be used for setting the number of days in the
    	 * combo box, in this program there will only
    	 * be 28 days in the month of February
    	 */
    	monthBegin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Integer selectedDay = Integer.parseInt(dayBegin.getSelectedItem().toString());
				setDays(dayBegin, monthBegin);
				if(monthBegin.getSelectedIndex() == 1 && selectedDay >= 30)
				{
					dayBegin.setSelectedItem(1);
				}
				else
				{
					dayBegin.setSelectedItem(selectedDay);
				}
			}
		});
    	
    	monthEnd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Integer selectedDay = Integer.parseInt(dayEnd.getSelectedItem().toString());
				setDays(dayEnd, monthEnd);
				if(monthEnd.getSelectedIndex() == 1 && selectedDay >= 30)
				{
					dayEnd.setSelectedItem(1);
				}
				else
				{
					dayEnd.setSelectedItem(selectedDay);
				}
			}
		});
    	/*
    	 * Add "this" class as an action listener
    	 * to the "Look Up" button
    	 */
    	lookUpButton.addActionListener(this);
    }//end method initWidgets
    
    /**
     * This method sets the layout for the various
     * panels that are placed on the top level component
     */
    private void setLayout(){
    	this.setLayout(new BorderLayout());
    	
    	this.panelNorth.setLayout(new GridLayout(1,1));
    	
    	this.panelCenter.setLayout(new GridLayout(0,4));
    	
    	this.panelSouth.setLayout(new FlowLayout());
    }//end method setLayout
    
    /**
     * This method adds all the widgets to
     * the top level window
     */
    private void addWidgets(){
    	this.add(panelNorth,BorderLayout.NORTH);
    	this.add(panelCenter, BorderLayout.CENTER);
    	this.add(panelSouth,BorderLayout.SOUTH);
    	
    	this.panelNorth.add(new JLabel(" Stock Symbol:"));
    	this.panelNorth.add(stockTextField);
    	
    	this.panelCenter.add(new JLabel(" Begin:"));
    	this.panelCenter.add(dayBegin);
    	this.panelCenter.add(monthBegin);
    	this.panelCenter.add(yearBegin);
    	this.panelCenter.add(new JLabel(" End:"));
    	this.panelCenter.add(dayEnd);
    	this.panelCenter.add(monthEnd);
    	this.panelCenter.add(yearEnd);
    	this.panelCenter.add(new JLabel(" Interval:"));
    	this.panelCenter.add(intervalComboBox);
    	this.panelCenter.add(chronologicalChooser);

    	this.panelSouth.add(lookUpButton);
    }//end method addWidgets
    
    /**
     * This method checks which month has been
     * selected by the user and sets a number
     * of days for that particular month. In this method
     * January is represented by 0 and February by the 1 and so on.
     * @param days This is the days combo box where the number
     * of days will change depending on which month is chosen
     * @param month This is the month the user has chosen
     */
    private void setDays(JComboBox days, JComboBox month){
    	int selectedMonth = month.getSelectedIndex();
    	days.removeAllItems();//Remove all the items from the days Combo Box
    	/*
    	 * These are the months where there are 31 days
    	 */
    	if(selectedMonth == 0 || selectedMonth == 2 || selectedMonth ==  4 || selectedMonth == 6 || selectedMonth == 7 || selectedMonth == 9 || selectedMonth == 11)
    	{
    		setDays(days, 31);
    	}
    	/*
    	 * These are the months where there are 30 days
    	 */
    	else if(selectedMonth == 3 || selectedMonth == 5 || selectedMonth == 8 || selectedMonth == 10)
    	{
    		setDays(days, 30);
    	}
    	/*
    	 * This is for the month of February where there
    	 * will only be 28 days
    	 */
    	else
    	{
    		setDays(days, 28);
    	}
    }//end method setDays
    
    /**
     * This is the method where the number of days will
     * actually be set for the days Combo box. 
     * @param days This is the days combo box where the number of
     * days will change
     * @param numberOfDays These are the number of
     * days that should be added to the combo box
     */
    private void setDays(JComboBox days, int numberOfDays){
    	for(int day = 1; day <= numberOfDays; day++)
    	{
    		days.addItem(day);
    	}
    }//end method setDays
    
    /**
     * Return the current date
     * @return The current date as an integer
     */
    private int getDay(){
    	return currentCalendar.get(Calendar.DAY_OF_MONTH);
    }//end method
    
    /**
     * Return the current month
     * @return The current month as an integer
     */
    private int getMonth(){
    	return currentCalendar.get(Calendar.MONTH);
    }//end method getMonth
    
    /**
     * Return the current year
     * @return The current year as an integer
     */
    private int getYear(){
    	return currentCalendar.get(Calendar.YEAR);
    }//end method getYear

    /**
     * This method sets the years for the
     * years combo box, where the years is in the
     * range from 1970 to the current year (Currently 2012) inclusive
     */
    private void setYears(){
    	for(int year = 1970; year <= getYear(); year++)
    	{
    		this.yearsList.add(year);
    	}
    }//end method setYears
    
    /*
     * (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
	@Override
	public void actionPerformed(ActionEvent event) {
		String stockSymbol = stockTextField.getText();
		try{
			/*
			 * Validate the user input of the text
			 */
			Validation.validateInput(stockSymbol, 8);
			
			int dayStart = Integer.parseInt(dayBegin.getSelectedItem().toString());
			int monthStart = monthBegin.getSelectedIndex();
			int yearStart = Integer.parseInt(yearBegin.getSelectedItem().toString());
			
			int dayFinish = Integer.parseInt(dayEnd.getSelectedItem().toString());
			int monthFinish = monthEnd.getSelectedIndex();
			int yearFinish = Integer.parseInt(yearEnd.getSelectedItem().toString());
			
			String interval = intervalComboBox.getSelectedItem().toString();
			
			beginCalendar.set(yearStart, monthStart, dayStart);//Set the begin calendar to the date the user has chosen 
			endCalendar.set(yearFinish, monthFinish, dayFinish);//Set the end calendar to the date the user has chosen
			
			boolean isSelected = chronologicalChooser.isSelected();
			
			Validation.checkDateChosen(beginCalendar, endCalendar);//Validate the date the user has chosen
			String windowTitle = stockSymbol+": "+ new SimpleDateFormat("yyyy-MM-dd").format(beginCalendar.getTime()) + " to " + new SimpleDateFormat("yyyy-MM-dd").format(endCalendar.getTime()) + " ("+ interval;
			if(isSelected == true)
			{
				windowTitle = windowTitle + ", Chronological Order)";
				if(WindowUpdater.windowChecker(windowTitle) == false)
				{
					TableWindow window = new TableWindow(windowTitle, new ChronologicalCompanyData(stockSymbol, beginCalendar, endCalendar, interval), isSelected);
					WindowUpdater.addWindow(window);
					window.setVisible(true);
				}
			}
			else
			{
				windowTitle = windowTitle + ", Reverse Chronological Order)";
				if(WindowUpdater.windowChecker(windowTitle) == false)
				{
					TableWindow window = new TableWindow(windowTitle, new ReverseChronologicalOrderCompanyData(stockSymbol, beginCalendar, endCalendar, interval), isSelected);
					WindowUpdater.addWindow(window);
					window.setVisible(true);
				}
			}
		}
		catch(Exception exception){
			JOptionPane.showMessageDialog(this, exception.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		finally{
			/*
			 * Clear the input field at the end
			 */
			stockTextField.setText("");
		}
	}//end method actionPerformed
}//end class InputScreen
