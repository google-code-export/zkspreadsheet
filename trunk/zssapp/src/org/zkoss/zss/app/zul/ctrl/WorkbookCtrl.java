/* WorkbookCtrl.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Nov 25, 2010 10:39:37 AM , Created by Sam
}}IS_NOTE

Copyright (C) 2009 Potix Corporation. All Rights Reserved.

*/
package org.zkoss.zss.app.zul.ctrl;

import java.io.ByteArrayOutputStream;

import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zss.app.file.SpreadSheetMetaInfo;

/**
 * @author Sam
 *
 */
public interface WorkbookCtrl {
	
	public final static int HEADER_TYPE_ROW = 0;
	public final static int HEADER_TYPE_COLUMN = 1;
	
	public void reGainFocus();
	
	public void renameSelectedSheet(String name);
	
	public void setSelectedSheet(String name);
	
	public void insertSheet();
	
	public void insertImage(Media media);
	
	public void insertFormula(String formula);
	
	//TODO: use clip board interface, return  
	public void cutSelection();
	
	public void copySelection();
	
	public void pasteSelection();
	
	public void clearSelectionContent();
	
	public void clearSelectionStyle();
	
	//TODO: insert row below
	public void insertRowAbove();
	
	public void deleteRow();
	
	//TODO: insert column right
	public void insertColumnLeft();
	
	public void deleteColumn();
	
	public void hide(boolean hide);
	
	public void sort(boolean isSortDescending);
	
	public void shiftCell(int direction);
	
	public void setRowFreeze(int rowfreeze);
	
	public void setColumnFreeze(int columnFreeze);
	
	public void addEventListener(String evtnm, EventListener listener);
	
	public boolean removeEventListener(String evtnm, EventListener listener);
	
	public String getCurrentCellPosition();
	
	public void setDataFormat(String format);
	
	/**
	 * Sets {@link Spreadsheet} book src
	 * @param src
	 */
	public void setBookSrc(String src);
	
	/**
	 * Save book
	 */
	public void save();
	
	/**
	 * Close book
	 */
	public void close();
	
	public String getSrc();
	
	public void setSrcName(String src);
	
	/**
	 * Open a new empty work sheet of current {@link Spreadsheet}
	 */
	public void newBook();
	
	/**
	 * Open a work book of current {@link Spreadsheet}
	 * @param spreadSheetMetaInfo
	 */
	public void openBook(SpreadSheetMetaInfo spreadSheetMetaInfo);
	
	/**
	 * Returns whether {@link Spreadsheet} has book or not
	 * @return
	 */
	public boolean hasBook();
	
	/**
	 * Returns whether {@link Spreadsheet} has file name or not
	 * <p> if {@link Spreadsheet} open new empty work sheet will return false
	 * @return boolean
	 */
	public boolean hasFileExtentionName();

	/**
	 * Exports spreadsheet to excel file
	 * @return
	 */
	public ByteArrayOutputStream exportToExcel();

	/**
	 * Returns {@link Spreadsheet} book name
	 * @return
	 */
	public String getBookName();
	
	/**
	 * Add a {@link #EventListener} to this book
	 * @param listener
	 */
	public void addBookEventListener(EventListener listener);
	
	/**
	 * Remove {@link #EventListener} the specified listener from listening to this book
	 * @param listener
	 */
	public void removeBookEventListener(EventListener listener);

	/**
	 * Sets column width of selected columns
	 * @param width
	 */
	public void setColumnWidthInPx(int width);

	/**
	 * Sets row height of selected rows
	 * @param height
	 */
	public void setRowHeightInPx(int height);
	
	public int getDefaultCharWidth();
}