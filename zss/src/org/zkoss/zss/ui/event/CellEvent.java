/* CellEvent.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Dec 19, 2007 2:18:10 PM     2007, Created by Dennis.Chen
}}IS_NOTE

Copyright (C) 2007 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.zss.ui.event;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
//import org.zkoss.zss.model.Sheet;
import org.apache.poi.ss.usermodel.Sheet;
/**
 * A class from handle event which about cell
 * @author Dennis.Chen
 */
public class CellEvent extends Event{
	
	private Sheet _sheet;
	private int _row;
	private int _col;

	public CellEvent(String name, Component target,Sheet sheet, int row ,int col,Object data) {
		super(name, target, data);
		_sheet = sheet;
		this._row = row;
		this._col = col;
	}
	
	public CellEvent(String name, Component target,Sheet sheet, int row ,int col) {
		this(name,target,sheet,row,col,null);
	}
	
	/**
	 * get Sheet
	 * @return sheet the related sheet 
	 */
	public Sheet getSheet(){
		return _sheet;
	}
	
	/**
	 * get row index
	 * @return row index
	 */
	public int getRow(){
		return _row;
	}
	
	/**
	 * get column index
	 * @return column index
	 */
	public int getColumn(){
		return _col;
	}

}
