package app.controller;

import app.StudentCalc;
import app.helper.Loan;
import app.helper.Payment;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;

public class LoanCalcViewController implements Initializable   {

	private StudentCalc SC = null;
	
	@FXML
	private TextField LoanAmount;

	@FXML
	private TextField additionalPayment;
	
	@FXML
	private Label totalInterest;
	
	@FXML 
	private TextField interestRate;
	
	@FXML
	private TextField termLength;
	
	@FXML
	private Label lblTotalPayemnts;
	
	@FXML
	private DatePicker PaymentStartDate;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void setMainApp(StudentCalc sc) {
		this.SC = sc;
	}
	
	/**
	 * btnCalcLoan - Fire this event when the button clicks
	 * 
	 * @version 1.0
	 * @param event
	 */
	@FXML
	private void btnCalcLoan(ActionEvent event) {

		//System.out.println("Amount: " + LoanAmount.getText());
		double dLoanAmount = Double.parseDouble(LoanAmount.getText());
		//System.out.println("Amount: " + dLoanAmount);
		double dInterestRate = Double.parseDouble(interestRate.getText());
		double dTermLength = Double.parseDouble(termLength.getText());
		double dExtraPayment = Double.parseDouble(additionalPayment.getText());
		Loan loan = new Loan(dLoanAmount, dInterestRate, dTermLength, dExtraPayment);
		loan.payments();
		LinkedList<Payment> payments = loan.getLoanPayments();
		
		lblTotalPayemnts.setText(Integer.toString(payments.getLast().getPaymentNbr()));
		
		totalInterest.setText(Double.toString(loan.calculateTotalInterest()));
		
		LocalDate localDate = PaymentStartDate.getValue();
	 
		System.out.println(localDate);
	}
}
