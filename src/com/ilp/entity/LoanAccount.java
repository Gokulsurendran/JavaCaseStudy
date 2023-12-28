package com.ilp.entity;

import java.util.ArrayList;

public class LoanAccount extends Product {
	private double chequeDepositRate;
	
	public LoanAccount(String productCode, String productName, ArrayList<Service> serviceList, double chequeDepositRate) {
		super(productCode, productName, serviceList);
		this.chequeDepositRate=chequeDepositRate;
	}

	public double getChequeDepositRate() {
		return chequeDepositRate;
	}

	public void setChequeDepositRate(double chequeDepositRate) {
		this.chequeDepositRate = chequeDepositRate;
	}
	
	
}
