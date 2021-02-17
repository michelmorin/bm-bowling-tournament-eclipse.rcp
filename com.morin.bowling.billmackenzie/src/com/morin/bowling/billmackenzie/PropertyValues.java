package com.morin.bowling.billmackenzie;

public class PropertyValues {
	
	private String key;
	private String adultValue;
	private String youthValue;
	
	public PropertyValues(String key, String adultValue, String youthValue) {
		this.key = key;
		this.adultValue = adultValue;
		this.youthValue = youthValue;
	}

	public String getAdultValue() {
		return adultValue;
	}

	public String getKey() {
		return key;
	}

	public String getYouthValue() {
		return youthValue;
	}
}
