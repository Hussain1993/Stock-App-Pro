package com.Hussain.View;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * This is the class where the cell renderer for the 
 * JTabel will be defined, where the colour of the cells
 * will change depending on their value and the previous
 * value in the table.
 * @author Hussain
 *
 */
public class CellRenderer extends DefaultTableCellRenderer {
	private boolean chronological;
	
	/**
	 * This is the constructor for the class
	 * <code>CellRenderer</code> 
	 * @param chronological This boolean variable indicates whether
	 * the company data is in chronological order or not, so when this
	 * variable is set to true will mean that the order of the data will be
	 * in chronological order and on the other hand if the value is set to
	 * false then the order of the data will be reverse chronological. This 
	 * attribute of the class is needed because the method to work out the
	 * colour of the cell changes depending in which the order of the data is in. 
	 */
	public CellRenderer(boolean chronological){
		this.chronological = chronological;
	}//end constructor for the class CellRenderer
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		/*
		 * This is how the cell will be rendered
		 * when the data is in chronological order.
		 */
		if(this.chronological == true)
		{
			if(row == 0)//The first row is set to black in chronological order
			{
				cell.setForeground(Color.BLACK);
			}
			else
			{
				/*
				 * Get the previous and current value of the 
				 * rows
				 */
				Double previous = Double.parseDouble(table.getValueAt(row - 1, column).toString());
				Double current = Double.parseDouble(table.getValueAt(row, column).toString());
				if(current < previous)
				{
					cell.setForeground(Color.RED);
				}
				else if(current > previous)
				{
					cell.setForeground(Color.GREEN);
				}
				else
				{
					cell.setForeground(Color.BLACK);
				}
			}
		}
		else
		{
			if(row == table.getRowCount() - 1)//In reverse chronological order the last row is set the default colour of black
			{
				cell.setForeground(Color.BLACK);
			}
			else
			{
				/*
				 * For reverse chronological order get the
				 * current and next value of the row
				 */
				Double current = Double.parseDouble(table.getValueAt(row, column).toString());
				Double next = Double.parseDouble(table.getValueAt(row + 1, column).toString());
				if(current < next)
				{
					cell.setForeground(Color.RED);
				}
				else if(current > next)
				{
					cell.setForeground(Color.GREEN);
				}
				else
				{
					cell.setForeground(Color.BLACK);
				}
			}
		}
		return cell;
	}//end method getTableCellRenderer
}//end class TableCellRenderer
