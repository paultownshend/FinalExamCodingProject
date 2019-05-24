package pkgUT;

import static org.junit.Assert.*;
import org.apache.poi.ss.formula.functions.*;
import org.junit.Test;

import app.helper.Loan;
import app.helper.Payment;

public class TestPMT {

	@Test
	public void test() {
		double PMT;
		double r = 0.07 / 12;
		double n = 20 * 12;
		double p = 150000;
		double f = 0;
		boolean t = false;
		PMT = Math.abs(FinanceLib.pmt(r, n, p, f, t));
		
		double PMTExpected = 1162.95;
		
		assertEquals(PMTExpected, PMT, 0.01);
	}
	
	@Test
	public void test_paymentsList() {
		Loan l = new Loan(15000, .07, 15, 50);
		l.payments();
		for (Payment p : l.getLoanPayments()) {
			//System.out.println(p.getPaymentAmt());
		}
		
	}
	
	@Test
	public void test_round() {
		double a = 17.556789;
		a = Loan.round(a, 2);
		assertTrue(a==17.56);
	}

}

 

