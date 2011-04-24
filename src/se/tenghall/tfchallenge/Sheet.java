package se.tenghall.tfchallenge;

import java.util.HashMap;

public class Sheet {

	private HashMap<String, String> sheetMatix = new HashMap<String, String>();

	public void put(String theCell, String value) {
		sheetMatix.put(theCell, value);
	}

	public String get(String theCell) {
		return ValueParser.pars(getStoredValue(theCell));
	}

	public String getLiteral(String theCell) {
		return ValueParser.convertNullToEmptyString(getStoredValue(theCell));
	}

	private String getStoredValue(String theCell) {
		return sheetMatix.get(theCell);
	}
}
