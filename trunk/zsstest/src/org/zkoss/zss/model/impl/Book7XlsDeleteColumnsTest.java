/**
 * 
 */
package org.zkoss.zss.model.impl;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;

import org.apache.poi.hssf.record.formula.Ptg;
import org.apache.poi.hssf.usermodel.HSSFEvaluationWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.FormulaRenderer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.zkoss.util.resource.ClassLocator;
import org.zkoss.zss.model.Book;
import org.zkoss.zss.model.impl.BookHelper;
import org.zkoss.zss.model.impl.ExcelImporter;
import org.zkoss.zss.model.impl.HSSFBookImpl;

/**
 * Insert a row and check if the formula still work
 * @author henrichen
 */
public class Book7XlsDeleteColumnsTest {
	private Workbook _workbook;
	private FormulaEvaluator _evaluator;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		final String filename = "Book7.xls";
		final InputStream is = new ClassLocator().getResourceAsStream(filename);
		_workbook = new ExcelImporter().imports(is, filename);
		assertTrue(_workbook instanceof Book);
		assertTrue(_workbook instanceof HSSFBookImpl);
		assertTrue(_workbook instanceof HSSFWorkbook);
		assertEquals(filename, ((Book)_workbook).getBookName());
		assertEquals("Sheet1", _workbook.getSheetName(0));
		assertEquals("Sheet2", _workbook.getSheetName(1));
		assertEquals("Sheet3", _workbook.getSheetName(2));
		assertEquals(0, _workbook.getSheetIndex("Sheet1"));
		assertEquals(1, _workbook.getSheetIndex("Sheet2"));
		assertEquals(2, _workbook.getSheetIndex("Sheet3"));

		_evaluator = ((Book)_workbook).getFormulaEvaluator();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		_workbook = null;
		_evaluator = null;
	}
	
	@Test
	public void testDeleteColumnD_F() {
		Sheet sheet1 = _workbook.getSheet("Sheet1");
		Row row2 = sheet1.getRow(1);
		Row row3 = sheet1.getRow(2);
		assertEquals(1, row2.getCell(4).getNumericCellValue(), 0.0000000000000001);
		assertEquals(2, row3.getCell(4).getNumericCellValue(), 0.0000000000000001);
		assertEquals(3, row2.getCell(5).getNumericCellValue(), 0.0000000000000001);
		assertEquals(5, row2.getCell(6).getNumericCellValue(), 0.0000000000000001);
		assertEquals(7, row2.getCell(7).getNumericCellValue(), 0.0000000000000001);
		assertEquals(9, row2.getCell(8).getNumericCellValue(), 0.0000000000000001);
		assertEquals(11, row2.getCell(9).getNumericCellValue(), 0.0000000000000001);

		//A1: =SUM(E2:E3)
		Row row1 = sheet1.getRow(0);
		Cell cellA1 = row1.getCell(0);
		CellValue valueA1 = _evaluator.evaluate(cellA1);
		assertEquals(3, valueA1.getNumberValue(), 0.0000000000000001);
		assertEquals(Cell.CELL_TYPE_NUMERIC, valueA1.getCellType());
		testToFormulaString(cellA1, "SUM(E2:E3)");

		//A2: =SUM(E2:J2)
		Cell cellA2 = row2.getCell(0);
		CellValue valueA2 = _evaluator.evaluate(cellA2);
		assertEquals(36, valueA2.getNumberValue(), 0.0000000000000001);
		assertEquals(Cell.CELL_TYPE_NUMERIC, valueA2.getCellType());
		testToFormulaString(cellA2, "SUM(E2:J2)");
		
		//A3: =SUM(H2:J2)
		Cell cellA3 = row3.getCell(0);
		CellValue valueA3 = _evaluator.evaluate(cellA3);
		assertEquals(27, valueA3.getNumberValue(), 0.0000000000000001);
		assertEquals(Cell.CELL_TYPE_NUMERIC, valueA3.getCellType());
		testToFormulaString(cellA3, "SUM(H2:J2)");
	
		//remove column D ~ F
		BookHelper.deleteColumns(sheet1, 3, 3);
		_evaluator.notifySetFormula(cellA1);
		_evaluator.notifySetFormula(cellA2);
		_evaluator.notifySetFormula(cellA3);
		
		//D2: 5, E2:7, E3: empty, F2: 9, G2: 11, H ~ J empty
		assertNull(row3.getCell(4)); //E3 not exist
		assertNull(row2.getCell(7));
		assertNull(row2.getCell(8));
		assertNull(row2.getCell(9));
		
		assertEquals(5, row2.getCell(3).getNumericCellValue(), 0.0000000000000001);
		assertEquals(7, row2.getCell(4).getNumericCellValue(), 0.0000000000000001);
		assertEquals(9, row2.getCell(5).getNumericCellValue(), 0.0000000000000001);
		assertEquals(11, row2.getCell(6).getNumericCellValue(), 0.0000000000000001);
		
		//A3: =SUM(E2:G2)
		valueA3 = _evaluator.evaluate(cellA3);
		assertEquals(Cell.CELL_TYPE_NUMERIC, valueA3.getCellType());
		testToFormulaString(cellA3, "SUM(E2:G2)");
		assertEquals(27, valueA3.getNumberValue(), 0.0000000000000001);
		
		//A2: =SUM(D2:G2)
		valueA2 = _evaluator.evaluate(cellA2);
		assertEquals(32, valueA2.getNumberValue(), 0.0000000000000001);
		assertEquals(Cell.CELL_TYPE_NUMERIC, valueA2.getCellType());
		testToFormulaString(cellA2, "SUM(D2:G2)");
		
		//A1: =SUM(#REF!)
		valueA1 = _evaluator.evaluate(cellA1);
		assertEquals(Cell.CELL_TYPE_ERROR, valueA1.getCellType());
		testToFormulaString(cellA1, "SUM(#REF!)");
	}
	
	@Test
	public void testDeleteRangeD1_F3() {
		Sheet sheet1 = _workbook.getSheet("Sheet1");
		Row row2 = sheet1.getRow(1);
		Row row3 = sheet1.getRow(2);
		assertEquals(1, row2.getCell(4).getNumericCellValue(), 0.0000000000000001);
		assertEquals(2, row3.getCell(4).getNumericCellValue(), 0.0000000000000001);
		assertEquals(3, row2.getCell(5).getNumericCellValue(), 0.0000000000000001);
		assertEquals(5, row2.getCell(6).getNumericCellValue(), 0.0000000000000001);
		assertEquals(7, row2.getCell(7).getNumericCellValue(), 0.0000000000000001);
		assertEquals(9, row2.getCell(8).getNumericCellValue(), 0.0000000000000001);
		assertEquals(11, row2.getCell(9).getNumericCellValue(), 0.0000000000000001);

		//A1: =SUM(E2:E3)
		Row row1 = sheet1.getRow(0);
		Cell cellA1 = row1.getCell(0);
		CellValue valueA1 = _evaluator.evaluate(cellA1);
		assertEquals(3, valueA1.getNumberValue(), 0.0000000000000001);
		assertEquals(Cell.CELL_TYPE_NUMERIC, valueA1.getCellType());
		testToFormulaString(cellA1, "SUM(E2:E3)");

		//A2: =SUM(E2:J2)
		Cell cellA2 = row2.getCell(0);
		CellValue valueA2 = _evaluator.evaluate(cellA2);
		assertEquals(36, valueA2.getNumberValue(), 0.0000000000000001);
		assertEquals(Cell.CELL_TYPE_NUMERIC, valueA2.getCellType());
		testToFormulaString(cellA2, "SUM(E2:J2)");
		
		//A3: =SUM(H2:J2)
		Cell cellA3 = row3.getCell(0);
		CellValue valueA3 = _evaluator.evaluate(cellA3);
		assertEquals(27, valueA3.getNumberValue(), 0.0000000000000001);
		assertEquals(Cell.CELL_TYPE_NUMERIC, valueA3.getCellType());
		testToFormulaString(cellA3, "SUM(H2:J2)");
	
		//remove column D ~ F
		BookHelper.deleteRange(sheet1, 0, 3, 2, 5, true);
		_evaluator.notifySetFormula(cellA1);
		_evaluator.notifySetFormula(cellA2);
		_evaluator.notifySetFormula(cellA3);
		
		//D2: 5, E2:7, E3: empty, F2: 9, G2: 11, H ~ J empty
		assertNull(row3.getCell(4)); //E3 not exist
		assertNull(row2.getCell(7));
		assertNull(row2.getCell(8));
		assertNull(row2.getCell(9));
		
		assertEquals(5, row2.getCell(3).getNumericCellValue(), 0.0000000000000001);
		assertEquals(7, row2.getCell(4).getNumericCellValue(), 0.0000000000000001);
		assertEquals(9, row2.getCell(5).getNumericCellValue(), 0.0000000000000001);
		assertEquals(11, row2.getCell(6).getNumericCellValue(), 0.0000000000000001);
		
		//A3: =SUM(E2:G2)
		valueA3 = _evaluator.evaluate(cellA3);
		assertEquals(Cell.CELL_TYPE_NUMERIC, valueA3.getCellType());
		testToFormulaString(cellA3, "SUM(E2:G2)");
		assertEquals(27, valueA3.getNumberValue(), 0.0000000000000001);
		
		//A2: =SUM(D2:G2)
		valueA2 = _evaluator.evaluate(cellA2);
		assertEquals(32, valueA2.getNumberValue(), 0.0000000000000001);
		assertEquals(Cell.CELL_TYPE_NUMERIC, valueA2.getCellType());
		testToFormulaString(cellA2, "SUM(D2:G2)");
		
		//A1: =SUM(#REF!)
		valueA1 = _evaluator.evaluate(cellA1);
		assertEquals(Cell.CELL_TYPE_ERROR, valueA1.getCellType());
		testToFormulaString(cellA1, "SUM(#REF!)");
	}
	
	private void testToFormulaString(Cell cell, String expect) {
		HSSFEvaluationWorkbook evalbook = HSSFEvaluationWorkbook.create((HSSFWorkbook)_workbook);
		Ptg[] ptgs = BookHelper.getCellPtgs(cell);
		final String formula = FormulaRenderer.toFormulaString(evalbook, ptgs);
		assertEquals(expect, formula);
	}
}
