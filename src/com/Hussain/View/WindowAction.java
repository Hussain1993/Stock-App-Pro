package com.Hussain.View;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
/**
 * This is the class for the action listener of 
 * the window that contains the table data. This method
 * will remove the current JFrame from an arraylist
 * @author Hussain
 *
 */
public class WindowAction extends WindowAdapter {
	private JFrame window;
	
	/**
	 * This is the constructor for the class 
	 * WindowAction
	 * @param window This is the current JFrame
	 */
	public WindowAction(JFrame window){
		this.window = window;
	}//end constructor for the class WindowAction
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.WindowAdapter#windowClosed(java.awt.event.WindowEvent)
	 */
	public void windowClosed(WindowEvent event){
		/*
		 * When a user closes a table window
		 * this method will be called to remove the
		 * current JFrame from the array list
		 */
		WindowUpdater.removeWindow(window);
	}//end method windowClosed

}//end class WindowChecker
