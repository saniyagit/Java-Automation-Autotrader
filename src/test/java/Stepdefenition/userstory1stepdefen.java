package Stepdefenition;

import org.openqa.selenium.WebDriver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pagesPackage.LoanCalculatorPage;
import utilityPackage.BrowserFactory;

public class userstory1stepdefen {
	
	 WebDriver driver;
	
	@Given("^user is on Autotrader caalculator page$")
	public void user_is_on_Autotrader_caalculator_page() throws Throwable {
		
		BrowserFactory.startBrowser("chrome");
	    throw new PendingException();
	}

	@When("^Title of page is Auto Loan Calculator$")
	public void title_of_page_is_Auto_Loan_Calculator() throws Throwable {
		LoanCalculatorPage loan_cal = new LoanCalculatorPage();		
		loan_cal.validateCalHomePage();
	    throw new PendingException();
	}

	@Then("^validate the error message when no price is given as input$")
	public void validate_the_error_message_when_no_price_is_given_as_input() throws Throwable {
		
		LoanCalculatorPage loan_cal = new LoanCalculatorPage();	
		int testCaseID = 1;
		loan_cal.validateNoPrice(testCaseID);
	    throw new PendingException();
	}

	@Then("^Enter the values in calculator and validate output$")
	public void enter_the_values_in_calculator_and_validate_output() throws Throwable {
		LoanCalculatorPage loan_cal = new LoanCalculatorPage();	
		int testCaseID = 3;
		loan_cal.ValidateDetails(loan_cal.GetEMI(testCaseID));
	    throw new PendingException();
	}



}
