package app.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;

import org.apache.poi.ss.formula.functions.*;

public class Loan {

	private double loanAmt;
	private double nbrOfYears;
	private double interestRate;
	private double futureValue;
	private boolean interestCalc;
	private double extraPayment;

	private LinkedList<Payment> loanPayments = new LinkedList<Payment>();
	
	public Loan(double loanAmt, double interestRate, double term, double extraPayment) {
		super();
		this.loanAmt = loanAmt;
		this.interestRate = interestRate/12;
		this.nbrOfYears = term;
		this.extraPayment = extraPayment;
		this.interestCalc = false;
	}
	
	public void payments() {
		if (this.extraPayment == 0) {
			int i = 1;
			double pmt = Math.abs(FinanceLib.pmt(this.interestRate, this.nbrOfYears * 12, this.loanAmt, this.futureValue, this.interestCalc));
			double ipmt;
			double ppmt;
			double balance = this.loanAmt;
			while (balance > 0) {
				//System.out.print(balance + "   ");
				ipmt = Loan.round(this.interestRate * balance, 2);
				ppmt = pmt - ipmt;
				balance -= ppmt;
				Payment p = new Payment(i++, round(ppmt, 2), round(ipmt, 2));
				//System.out.println(p.getPrincipal() + " , " + p.getInterest() + " , " + p.getPaymentAmt());
				loanPayments.addLast(p);
			}
		}
		else {
			double balance = this.loanAmt;
			int i = 1;
			double pmt;
			double ppmt;
			double ipmt;
			double tpmt;
			while (balance > 0) {
				//System.out.print(balance + "   ");
				pmt = Math.abs(FinanceLib.pmt(this.interestRate, this.nbrOfYears * 12, this.loanAmt, this.futureValue, this.interestCalc));
				tpmt = pmt + this.extraPayment;
				ipmt = Loan.round(this.interestRate * balance, 2);
				ppmt = tpmt - ipmt;
				balance -= ppmt;
				Payment p = new Payment(i, round(ppmt, 2), round(ipmt, 2));
				//System.out.println(p.getPrincipal() + " , " + p.getInterest() + " , " + p.getPaymentAmt());
				loanPayments.addLast(p);
				i++;
			}
		}
	}
	
	public double calculateTotalInterest() {
		double sumInterest = 0;
		for (Payment p : this.loanPayments) {
			sumInterest += p.getInterest();
		}
		return Loan.round(sumInterest, 2);
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

	public double getLoanAmt() {
		return loanAmt;
	}

	public double getNbrOfYears() {
		return nbrOfYears;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public double getFutureValue() {
		return futureValue;
	}

	public boolean isInterestCalc() {
		return interestCalc;
	}

	public double getExtraPayment() {
		return extraPayment;
	}

	public LinkedList<Payment> getLoanPayments() {
		return loanPayments;
	}
}
