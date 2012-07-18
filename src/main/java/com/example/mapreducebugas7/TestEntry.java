package com.example.mapreducebugas7;

import java.io.Serializable;

public class TestEntry implements Serializable {

	private static final long serialVersionUID = 1L;
	
	int amount;

	public TestEntry(int amount) {
		super();
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

}
