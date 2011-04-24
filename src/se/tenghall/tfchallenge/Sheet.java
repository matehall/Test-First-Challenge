package se.tenghall.tfchallenge;

import java.util.HashMap;

public class Sheet {

	private static final int SECOND_CHAR = 1;
	private static final String END_PARENTHES = ")";
	private static final String STAR_PARENTHES = "(";
	private static final String EMPTY_STRING = "";
	private static final String BLANK = " ";

	private HashMap<String, String> sheetValues = new HashMap<String, String>();

	public void put(String theCell, String value) {
		sheetValues.put(theCell, value);
	}

	public String getCellContents(String theCell) {
		String value = getLiteral(theCell);
		value = trimValueFromStartAndEndBlanks(value);
		value = parsIfFormula(value);
		return value;
	}

	public String getLiteral(String theCell) {
		return getStoredValue(theCell);
	}

	public String getStoredValue(String theCell) {
		String value = sheetValues.get(theCell);
		return convertNullToEmptyString(value);
	}

	public String convertNullToEmptyString(String ans) {
		return ans != null ? ans : EMPTY_STRING;
	}

	public String parsIfFormula(String value) {
		if (isFormula(value)) {
			value = removeEqualSign(value);
			value = removeStartAndEndParentes(value);
		}
		return value;
	}

	public String removeStartAndEndParentes(String value) {
		if (stringStartAndEndsWith(value, STAR_PARENTHES, END_PARENTHES)) {
			value = trimStartAndEndChar(value);
			value = removeStartAndEndParentes(value);
		}
		return value;
	}

	public String trimStartAndEndChar(String value) {
		return value.substring(SECOND_CHAR, secondToLastChar(value));
	}

	public int secondToLastChar(String value) {
		return value.length() - 1;
	}

	public boolean stringStartAndEndsWith(String value, String starParenthes,
			String endParenthes) {
		return value.startsWith(starParenthes) && value.endsWith(endParenthes);
	}

	public String removeEqualSign(String value) {
		return value.replaceFirst("=", "");
	}

	public boolean isFormula(String value) {
		return value.startsWith("=");
	}


	public String trimValueFromStartAndEndBlanks(String value) {
		if (valueNotBlank(value) && startsAndEndsWithBlanks(value)) {
			value = value.trim();
		}
		return value;
	}

	public boolean startsAndEndsWithBlanks(String value) {
		return value.startsWith(BLANK) && value.endsWith(BLANK);
	}

	public boolean valueNotBlank(String value) {
		return value != BLANK;
	}
}
