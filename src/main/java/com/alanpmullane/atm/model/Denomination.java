package com.alanpmullane.atm.model;

public enum Denomination {
	Five(5, "€5"), Ten(10, "€10"), Twenty(20, "€20"), Fifty(50, "€50");
	
	private Integer value;
	private String display;
	
	Denomination(Integer value, String display) {
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
	
	public static Denomination getLargest() {
		return Denomination.values()[Denomination.values().length - 1];
	}
	
	public static Denomination getSmallest() {
		return Denomination.values()[0];
	}
	
	public static Denomination getNext(Denomination current) {
		Denomination next = null;
		for (int i = Denomination.values().length - 1; i > 0; i--) {
			if (Denomination.values()[i].getValue().equals(current.getValue())) {
				next = Denomination.values()[i-1];
			}
		}
		return next;
	}
}
