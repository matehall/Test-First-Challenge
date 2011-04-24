package se.tenghall.tfchallenge;

public class ValueParser {
	private static final String END_PARENTHES = ")";
	private static final String STAR_PARENTHES = "(";
	private static final String BLANK = " ";
	private static final String EMPTY_STRING = "";
	private static final int SECOND_CHAR = 1;

	public static String pars(String value) {
		value = convertNullToEmptyString(value);
		value = trimValueFromStartAndEndBlanks(value);
		if (isFormula(value))
			value = parsFormula(value);
		return value;
	}

	public static String trimValueFromStartAndEndBlanks(String value) {
		if (valueNotBlank(value) && startsAndEndsWithBlanks(value))
			value = value.trim();
		return value;
	}

	public static boolean startsAndEndsWithBlanks(String value) {
		return value.startsWith(BLANK) && value.endsWith(BLANK);
	}

	public static boolean valueNotBlank(String value) {
		return value != BLANK;
	}

	public static boolean isFormula(String value) {
		return value.startsWith("=");
	}

	public static String parsFormula(String value) {
		value = removeEqualSign(value);
		value = removeStartAndEndParentes(value);
		return value;
	}

	public static String removeStartAndEndParentes(String value) {
		if (stringStartAndEndsWith(value, STAR_PARENTHES, END_PARENTHES)) {
			value = trimStartAndEndChar(value);
			value = removeStartAndEndParentes(value);
		}
		return value;
	}

	public static String trimStartAndEndChar(String value) {
		return value.substring(SECOND_CHAR, secondToLastChar(value));
	}

	public static int secondToLastChar(String value) {
		return value.length() - 1;
	}

	public static boolean stringStartAndEndsWith(String value,
			String starParenthes,
			String endParenthes) {
		return value.startsWith(starParenthes) && value.endsWith(endParenthes);
	}

	public static String removeEqualSign(String value) {
		return value.replaceFirst("=", "");
	}

	public static String convertNullToEmptyString(String ans) {
		return ans != null ? ans : EMPTY_STRING;
	}
}
