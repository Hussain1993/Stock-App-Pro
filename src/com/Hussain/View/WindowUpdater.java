package com.Hussain.View;

import java.util.ArrayList;

import javax.swing.JFrame;

/**
 * This is class which will maintains
 * the table windows the user 
 * has created, this class will remove a
 * window from the array list if the user closes the window
 * @author Hussain
 *
 */
public class WindowUpdater {
	private static ArrayList<JFrame> windows = new ArrayList<JFrame>();
	
	/**
	 * This method adds a JFrame to the
	 * ArrayList
	 * @param window This is the window to be added
	 * to the array list
	 */
	public static void addWindow(JFrame window){
		windows.add(window);
	}//end method addWindow 
	
	/**
	 * This method checks if a window with the
	 * same title is open
	 * @param windowTitle This is the title of the window
	 * @return true if the JFrame title is already in the
	 * array list otherwise return false if the window
	 * is not found
	 */
	public static boolean windowChecker(String windowTitle){
		for(int index = 0; index < windows.size(); index++)
		{
			if(windows.get(index).getTitle().equals(windowTitle))
			{
				windows.get(index).toFront();
				return true;
			}
		}
		return false;
	}//end method windowChecker
	
	/**
	 * This method removes a window from the
	 * array list
	 * @param window This is the window to be
	 * removed
	 */
	public static void removeWindow(JFrame window){
		windows.remove(window);
	}//end method removeWindow
}//end class WindowChecker
