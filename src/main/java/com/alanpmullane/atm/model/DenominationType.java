package com.alanpmullane.atm.model;

public enum DenominationType {
	Fifty(50, "50s"), Twenty(20, "20s"), Ten(10, "10s"), Five(5, "5s");
	
	private Integer value;
	private String display;
	
	DenominationType(Integer value, String display) {
		this.value = value;
		this.display = display;
	}
	
	public Integer getValue() {
		return value;
	}
	
	public void setValue(Integer value) {
		this.value = value;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}
}
