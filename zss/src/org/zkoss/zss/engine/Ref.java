/* Ref.java

	Purpose:
		
	Description:
		
	History:
		Mar 6, 2010 3:06:05 PM, Created by henrichen

Copyright (C) 2010 Potix Corporation. All Rights Reserved.

*/

package org.zkoss.zss.engine;

import java.util.HashSet;
import java.util.Set;

/**
 * Area Reference used for cell dependency tracking(form a graph structure). 
 * @author henrichen
 *
 */
public interface Ref {
	/**
	 * Returns the owner sheet this reference belongs to.
	 * @return the owner sheet this reference belongs to.
	 */
	public RefSheet getOwnerSheet();
	/**
	 * Return the row index of the left top corner.
	 * @return the row index of the left top corner.
	 */
	public int getTopRow();
	/**
	 * Return the column index of the left top corner.
	 * @return the column index of the left top corner.
	 */
	public int getLeftCol();
	/**
	 * Return the row index of the right bottom corner.
	 * @return the row index of the right bottom corner.
	 */
	public int getBottomRow();
	/**
	 * Return the column index of the right bottom corner.
	 * @return the column index of the right bottom corner.
	 */
	public int getRightCol();
	/**
	 * Return dependent {@link Ref}s of this Ref. 
	 * @return dependent {@link Ref}s of this Ref.
	 */
	public Set<Ref> getDependents();
	/**
	 * Return precedent {@link Ref}s of this Ref.
	 * @return precedent {@link Ref}s of this Ref.
	 */
	public Set<Ref> getPrecedents();
	
	/**
	 * Returns whether this reference cover the whole row. 
	 * @return whether this reference cover the whole row.
	 */
	public boolean isWholeRow();
	
	/**
	 * Returns whether this reference cover the whole column.
	 * @return whether this reference cover the whole column.
	 */
	public boolean isWholeColumn();

	/**
	 * Returns the number of columns in this reference.
	 * @return the number of columns in this reference.
	 */
	public int getColumnCount();
	
	/**
	 * Returns the number of rows in this reference.
	 * @return the number of rows in this reference.
	 */
	public int getRowCount();
	
	/**
	 * Remove all precedents of this reference. 
	 */
	public void removeAllPrecedents();
}
