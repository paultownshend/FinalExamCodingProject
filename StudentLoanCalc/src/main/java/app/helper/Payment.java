package app.helper;

import org.apache.poi.ss.formula.functions.*;

public class Payment {
	
	private double paymentAmt;
	
	private int paymentNbr;
	
	private double principal;
	
	private double interest;
	
	public Payment(int pmtNbr, double iPrincipal, double iInterest) {
		this.paymentNbr = pmtNbr;
		this.principal = iPrincipal;
		this.interest = iInterest;
		this.paymentAmt = Loan.round(this.principal + this.interest, 2);
	}

	public double getPaymentAmt() {
		return paymentAmt;
	}

	public int getPaymentNbr() {
		return paymentNbr;
	}

	public double getPrincipal() {
		return principal;
	}

	public double getInterest() {
		return interest;
	}
}
