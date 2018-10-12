package pagesPackage;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import utilityPackage.BrowserFactory;
import testPackage.BaseClass;
import utilityPackage.ConfigReader;
import utilityPackage.CustomWait;
import utilityPackage.ExcelDataConfig;
import utilityPackage.ScreenShot;

public class LoanCalculatorPage extends BaseClass{
	//POM constructor
	public LoanCalculatorPage()
	{
		PageFactory.initElements(BrowserFactory.driver, this);
	}
	
	//Calculate page title
	@FindBy(how = How.XPATH, using = "//span[@class='atcui-page-title']")
	static WebElement calculatePageTitle;
	
	//Continue button
	@FindBy(how = How.CLASS_NAME, using = "continuebtnlbl")
	static WebElement continueButton;
	
	//Interest Rate
	@FindBy(how = How.XPATH, using = ".//*[@id='j_id_3s-tabsColumn-j_id_43-j_id_44-autoLoanCalculator-j_id_4a-calculatorFormColumn-j_id_55-loanCalculatorInterestRate']")
	static WebElement interestRate;
		
	//price
	@FindBy(how = How.XPATH, using = ".//*[@id='j_id_3s-tabsColumn-j_id_43-j_id_44-autoLoanCalculator-j_id_4a-calculatorFormColumn-j_id_5k-priceInput']")
	static WebElement price;
		
	//price displayed on Estimated Payment
	@FindBy(how = How.XPATH, using = ".//*[@id='price']")
	static WebElement price_EP;
				
	//price reminder when no price entered
	@FindBy(how = How.XPATH, using = ".//*[@id='price-help-content']/div")
	static WebElement price_reminder;
		
	//calculate button
	@FindBy(how = How.XPATH, using = ".//*[@id='j_id_3s-tabsColumn-j_id_43-j_id_44-autoLoanCalculator-j_id_4a-calculatorFormColumn-j_id_7m-loanCalculatorCalculateActionPanel-j_id_7r-j_id_7r']")
	static WebElement calculateBtn;   
		
	//Estimated Payment
	@FindBy(how = How.XPATH, using = ".//*[@id='top-of-text']/span[1]")
	static WebElement EP;
		
	//EMI
	@FindBy(how = How.XPATH, using = ".//*[@id='top-of-text']/span[2]")
	static WebElement EMI;
		
	//month&rate
	@FindBy(how = How.XPATH, using = ".//*[@id='top-of-text']/span[3]")
	static WebElement monthANDrate;
				
	//Sales Tax Rate
	@FindBy(how = How.XPATH, using = ".//*[@id='salesTaxRate']/span[2]/span")
	static WebElement SalesTaxRate;
				
	//Down Payment
	@FindBy(how = How.XPATH, using = ".//*[@id='downPayment']/span[2]/span")
	static WebElement DownPayment;
				
	//Trade-In Value
	@FindBy(how = How.XPATH, using = ".//*[@id='tradeInValue']/span[2]/span")
	static WebElement TradeInValue;
				
	//Amount Owed on Trade
	@FindBy(how = How.XPATH, using = ".//*[@id='amountOwed']/span[2]/span")
	static WebElement AmountOwedonTrade;
				
	//Total Financed
	@FindBy(how = How.XPATH, using = ".//*[@id='j_id_az']/span[2]/span")
	static WebElement TotalFinanced;
				
	//Total Interest
	@FindBy(how = How.XPATH, using = ".//*[@id='j_id_b7']/span[2]/span")
	static WebElement TotalInterest;

	//Total Loan
	@FindBy(how = How.CSS, using = "#j_id_3s-tabsColumn-j_id_43-j_id_44-autoLoanCalculator-j_id_4a-calculatorFormColumn-j_id_bg > span.output-text > span")
	static WebElement TotalLoan;			
	
	
	
	//Method to check if loan calculator page is displayed
	public void validateCalHomePage() throws HeadlessException, AWTException, IOException, InterruptedException
	{
		String title=null;
		
		//wait until the title is displayed
		Thread.sleep(2000);
		//CustomWait.wait("//span[@class='atcui-page-title']",30,1);
		
		try 
		{
			title="Auto Loan Calculator";	
			
			//click on continueButton
			continueButton.click();
		}
		catch (Exception e)
		{
		//	testLog.log(Status.INFO, e.getMessage());
		}
		
		
		//Verification in Calculate Home Page
		if(title.equals(calculatePageTitle.getText()))
		{
			//testLog.log(Status.PASS, "Loan calculator page is displayed successfully");
		}
		else
		{
			//Capture screenshot of the fail step
			//ScreenShot.captureScreenshot(BrowserFactory.driver, "HomePage_Fail");
			//testLog.log(Status.FAIL, "Loan calculator page is NOT displayed successfully");
		}
		
		//assertEquals(title, calculatePageTitle.getText(),"Loan calculator page is NOT displayed successfully");
		
		
	}
	
	
	//method to input data in calculator
	public int GetEMI(int testCaseID) throws HeadlessException, AWTException, IOException//ID is defined in ./TestData/TestData.xlsx
	{
		try 
		{
			String title=null;
			
			int type;
			//prepare the data
			ExcelDataConfig excel = new ExcelDataConfig(ConfigReader.getExcelPath());
			
			//read "type" from "EMIDATA" sheet
			type=new Integer(excel.getData("EMIDATA", testCaseID, 1)).intValue(); 
			
			//choose month based on xpath
			
			//x_path = ".//*[@id='loanCalculatorMonths']/div/span/table/tbody/tr/td[{0}]/label".format(i)
			//List<WebElement> element = BrowserFactory.driver.findElements(By.xpath(_xpath)); 
			//for (int i=0 ; i<_element.size() ; i++)
			//{
			
			//element.get(i).click();
		    //  }
			
			
			
			
			//choose month via "type"
			BrowserFactory.driver.findElement(By.xpath(".//*[@id='loanCalculatorMonths']/div/span/table/tbody/tr/td[" + type +"]/label")).click();
		
			//send value to Interest Rate
			interestRate.sendKeys(excel.getData("EMIDATA", testCaseID, 3));
			
			//send value to Price
			price.sendKeys(excel.getData("EMIDATA", testCaseID, 4));
			
			//wait until page title is displayed
			//BrowserFactory.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			CustomWait.wait(".//*[@id='loanCalculatorCalculateActionPanel']/span/input",30,1);
			
			//click on calculate button
			calculateBtn.click();
			
			//wait until page title is displayed
			//BrowserFactory.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			CustomWait.wait(".//*[@id='top-of-text']/span[1]",30,1);
					
			//validate Estimated Payment is displayed
			title="Estimated Payment";
			
			assertEquals(title, EP.getText(),"Estimated Payment is not displayed");
			
		}catch(Exception e)
		
		{
			//Capture screenshot of the fail step
			ScreenShot.captureScreenshot(BrowserFactory.driver, "Calculate_Fail");
			//testLog.log(Status.FAIL, e.getMessage());
		}
		
		return testCaseID;
		
	}
	
	
	//method to validate the EMI and the details
	public void ValidateDetails(int testCaseID) throws HeadlessException, AWTException, IOException
	{
		//prepare the data
		ExcelDataConfig excel = new ExcelDataConfig(ConfigReader.getExcelPath());
		
		SoftAssert softAssert = new SoftAssert();
		
		try 
		{
			//verify price
			softAssert.assertEquals(price_EP.getText(), excel.getData("EMIDATA", testCaseID, 5), "The price is wrong");
			//testLog.log(Status.INFO, "The price is " +price_EP.getText());
			
			//verify Tax Rate
			softAssert.assertEquals(SalesTaxRate.getText(), excel.getData("EMIDATA", testCaseID, 6), "The SalesTaxRate is wrong");
			//testLog.log(Status.INFO, "The SalesTaxRate is " +SalesTaxRate.getText());

			//verify Down Payment
			softAssert.assertEquals(DownPayment.getText(), excel.getData("EMIDATA", testCaseID, 7), "The DownPayment is wrong");
			//testLog.log(Status.INFO, "The DownPayment is " +DownPayment.getText());

			//verify Trade-In Value
			softAssert.assertEquals(TradeInValue.getText(), excel.getData("EMIDATA", testCaseID, 8), "The TradeInValue is wrong");
			//testLog.log(Status.INFO, "The Trade In value is " +TradeInValue.getText());

			//verify Amount Owed on Trade
			softAssert.assertEquals(AmountOwedonTrade.getText(), excel.getData("EMIDATA", testCaseID, 9), "The AmountOwedonTrade is wrong");
			//testLog.log(Status.INFO, "The AmountOwedonTrade is " +AmountOwedonTrade.getText());

			//verify EMI
			softAssert.assertEquals(EMI.getText(), excel.getData("EMIDATA", testCaseID, 10), "The Estimated Payment is wrong");
			//testLog.log(Status.INFO, "The EMI is " +EMI.getText());

			//verify month&rate
			softAssert.assertEquals(monthANDrate.getText(), excel.getData("EMIDATA", testCaseID, 11), "The monthsANDrate is wrong");
			//testLog.log(Status.INFO, "The monthANDrate is " +monthANDrate.getText());

			//verify Total Financed
			softAssert.assertEquals(TotalFinanced.getText(), excel.getData("EMIDATA", testCaseID, 12), "The TotalFinanced is wrong");
			//testLog.log(Status.INFO, "The TotalFinanced is " +TotalFinanced.getText());

			//verify Total Interest
			softAssert.assertEquals(TotalInterest.getText(), excel.getData("EMIDATA", testCaseID, 13), "The TotalInterest is wrong");
			//testLog.log(Status.INFO, "The TotalInterest is " +TotalInterest.getText());

			//verify Total Loan
			softAssert.assertEquals(TotalLoan.getText(), excel.getData("EMIDATA", testCaseID, 14), "The TotalLoan is wrong");
			//testLog.log(Status.INFO, "The TotalLoan is " +TotalLoan.getText());

			softAssert.assertAll();
			
			//testLog.log(Status.PASS, "The values entered is same as the value displayed and calculation is verified");
			
			//Capture screenshot of the pass
			ScreenShot.captureScreenshot(BrowserFactory.driver, "UserStory2_Pass");

		}
		catch(Exception e)
		{
			//testLog.log(Status.FAIL, e.getMessage());
			//testLog.log(Status.FAIL, "Input values provided are not equal to the output reult values");
			
			//Capture screenshot of the fail step
			ScreenShot.captureScreenshot(BrowserFactory.driver, "UserStory2_Fail");

		}
		
	}
	
	//Method to validate the error message when no price is given as input
	public void validateNoPrice(int testCaseID) throws HeadlessException, AWTException, IOException
	{
		String NoPriceMsg=null;
		NoPriceMsg="Please fill out the Vehicle Price field.";
		
		try 
		{
				int type;
				//prepare the data
				ExcelDataConfig excel = new ExcelDataConfig(ConfigReader.getExcelPath());
				
				//read "type" from "EMIDATA" sheet
				type=new Integer(excel.getData("EMIDATA", testCaseID, 1)).intValue(); 		
				
				//choose month via "type"
				BrowserFactory.driver.findElement(By.xpath(".//*[@id='loanCalculatorMonths']/div/span/table/tbody/tr/td[" + type +"]/label")).click();
			
				//click on 24 months
				BrowserFactory.driver.findElement(By.xpath(".//*[@id='loanCalculatorMonths']/div/span/table/tbody/tr/td[1]/label")).click();
				
				//send value to interestRate
				interestRate.sendKeys(excel.getData("EMIDATA", testCaseID, 3));
				Thread.sleep(2000);
				//click on calculate button
				calculateBtn.click();
				
				BrowserFactory.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

				//Assert the error message
				//testLog.log(Status.INFO, "The actual price reminder is :"+price_reminder.getText());
				assertEquals(NoPriceMsg, price_reminder.getText(), "price reminder is not displayed");
				//testLog.log(Status.PASS, "The price reminder Error Message is displayed when no price is entered");
				
				//Capture screenshot of the pass
				//ScreenShot.captureScreenshot(BrowserFactory.driver, "UserStory1_Pass");
		}
		catch(Exception e)
		{
		//	testLog.log(Status.FAIL, e.getMessage());
			
			//Capture screenshot of the fail step
			ScreenShot.captureScreenshot(BrowserFactory.driver, "UserStory1_Fail");
		}
	}


}
