package se.tenghall.tfchallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestCells {

	private static final String A_CELL = "A21";
	private Sheet sheet;

	@Before
	public void setup() {
		sheet = new Sheet();
	}

	@Test
	public void shouldBeEmptyByDefault() throws Exception {
		assertEquals("", sheet.get("A1"));
		assertEquals("", sheet.get("ZX347"));
	}

	@Test
	public void shouldStorText() throws Exception {
		String aTestString1 = "A String";
		String aTestString2 = "A Second String";

		sheet.put(A_CELL, aTestString1);
		assertEquals(aTestString1, sheet.get(A_CELL));

		sheet.put(A_CELL, aTestString2);
		assertEquals(aTestString2, sheet.get(A_CELL));

		sheet.put(A_CELL, "");
		assertEquals("", sheet.get(A_CELL));

	}

	@Test
	public void shouldHandleManyCells() throws Exception {
		String cellOne = "A1";
		String cellTwo = "X27";
		String cellThree = "ZX901";
		String cellContents_1 = "First";
		String cellContents_2 = "Second";
		String cellContents_3 = "Third";
		String cellContents_4 = "Fourth";

		sheet.put(cellOne, cellContents_1);
		sheet.put(cellTwo, cellContents_2);
		sheet.put(cellThree, cellContents_3);

		assertEquals(cellOne, cellContents_1, sheet.get(cellOne));
		assertEquals(cellTwo, cellContents_2, sheet.get(cellTwo));
		assertEquals(cellThree, cellContents_3,
				sheet.get(cellThree));

		sheet.put(cellOne, cellContents_4);
		assertEquals(cellOne + " contains new value", cellContents_4,
				sheet.get(cellOne));
		assertEquals(cellTwo + " contains same value", cellContents_2,
				sheet.get(cellTwo));
		assertEquals(cellThree + " contains same value", cellContents_3,
				sheet.get(cellThree));

	}

	@Test
	public void shouldBeAbleToHandNumericalValues() throws Exception {
		sheet.put(A_CELL, "X99");
		assertEquals("Obvious string", "X99", sheet.get(A_CELL));

		sheet.put(A_CELL, "14");
		assertEquals("Obvious number", "14", sheet.get(A_CELL));

		sheet.put(A_CELL, " ");
		assertEquals("Just a blank", " ", sheet.get(A_CELL));

		sheet.put(A_CELL, " 99 X");
		assertEquals("Whole string must be numeric", " 99 X",
				sheet.get(A_CELL));

		sheet.put(A_CELL, " 1234 ");
		assertEquals("Ignore blanks", "1234", sheet.get(A_CELL));
	}

	@Test
	public void shouldProvideLiteralValuesForEditing() throws Exception {
		sheet.put(A_CELL, "Some string");
		assertEquals("Some string", sheet.getLiteral(A_CELL));

		sheet.put(A_CELL, " 1234 ");
		assertEquals(" 1234 ", sheet.getLiteral(A_CELL));

	}

	@Test
	public void shouHandleFormulaSpec() throws Exception {
		sheet.put(A_CELL, "=7"); // Foreshadowing formulas:)
		assertEquals("=7", sheet.getLiteral(A_CELL));

		sheet.put("B1", " =7"); // note leading space
		assertEquals("Not a formula", " =7", sheet.get("B1"));
		assertEquals("Unchanged", " =7", sheet.getLiteral("B1"));
	}

	@Test
	public void shouldHandleConstantFunctions() throws Exception {
		sheet.put(A_CELL, "=7");
		assertEquals("Formula", "=7", sheet.getLiteral(A_CELL));
		assertEquals("Value", "7", sheet.get(A_CELL));

	}

	@Test
	public void testParentheses() throws Exception {
		sheet.put(A_CELL, "=(7)");
		assertEquals("Parends", "7", sheet.get(A_CELL));
	}

	@Test
	public void testDeepParentheses() throws Exception {
		sheet.put(A_CELL, "=((((10))))");
		assertEquals("Parends", "10", sheet.get(A_CELL));
	}
}
