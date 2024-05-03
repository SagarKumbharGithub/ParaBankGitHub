package com.qa.reusableComponents;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.qa.reports.AllureListener;
import com.qa.reports.ExtentListener;
import com.qa.reports.ExtentLogger;
import com.qa.testComponents.BaseClass;

public class CommonMethods {
	WebDriver driver;
	public static int MaxElementWait = 5;

	public CommonMethods() {
		// TODO Auto-generated constructor stub
		this.driver = BaseClass.getDriver();
	}

	/*
	 * Common method to select radio button
	 */
	public void selectRadiobutton(List<WebElement> element, String value) {
		for (WebElement ref : element) {
			if (ref.getText().equalsIgnoreCase(value)) {
				ref.click();

				break;
			}

		}

	}

	/*
	 * Common method to select CheckBoxes
	 */

	public void selectCheckBoxes(List<WebElement> element, String check) {
		String[] checksArray = check.split(",");
		for (String str : checksArray) {
			for (WebElement ele : element) {
				if (ele.getText().equalsIgnoreCase(str)) {
					ele.click();
					break;
				}
			}

		}

	}

	/*
	 * Customized senKeys method-> send text by using sendKeys method
	 */
	public static void sendKeys_custom(WebElement element, String fieldName, String valueToBeSent) {
		try {
			element.sendKeys(valueToBeSent);
			ExtentLogger.pass(valueToBeSent + " value entered in " + fieldName + " field ");
			AllureListener.saveTextLog(valueToBeSent + " value entered in " + fieldName + " field ");

		} catch (Exception e) {
			e.printStackTrace();
			ExtentLogger.fail("Not able to enter value " + valueToBeSent + " in " + fieldName);
			AllureListener.saveTextLog("Not able to enter value " + valueToBeSent + " in " + fieldName);
			Assert.fail("Not able to enter value " + valueToBeSent + " in " + fieldName);
		}
	}

	/**
	 * 
	 * @param element
	 * @param fieldName
	 */
	public static void click_custom(WebElement element, String fieldName) {
		try {
			element.click();
			ExtentLogger.pass("Cliked on " + fieldName);
			AllureListener.saveTextLog("Cliked on " + fieldName);
		} catch (Exception e) {
			e.printStackTrace();
			ExtentLogger.fail("Not able to Click on " + fieldName);
			AllureListener.saveTextLog("Not able to Click on " + fieldName);
			Assert.fail("Not able to Click on " + fieldName);
		}
	}

	// clear data from field
	public static void clear_custom(WebElement element, String fieldName) {
		element.clear();
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			AllureListener.saveTextLog("Not able to clear " + fieldName);
		}
	}

	// custom mouseHover
	public void moveToElement_custom(WebElement element, String fieldName) {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", element);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// check if element is Present
	public static boolean isElementPresent_custom(WebElement element) {
		try {
			element.isDisplayed();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	// Select dropdown value value by visibleText
	public static void selectDropDownByVisibleText_custom(WebElement element, String fieldName, String ddVisibleText) {
		try {
			Select s = new Select(element);
			s.selectByVisibleText(ddVisibleText);
			ExtentLogger.pass(ddVisibleText + " option is selected from " + fieldName + " dropdown");
			AllureListener.saveTextLog(ddVisibleText + " option is selected from " + fieldName + " dropdown");
		} catch (Exception e) {
			e.printStackTrace();
			AllureListener.saveTextLog("Not able to select text " + ddVisibleText + " from " + fieldName);
			Assert.fail("Not able to select text " + ddVisibleText + " from " + fieldName);
		}

	}

	// Select dropdown value value by value
	public static void  selectDropDownByValue_custom(WebElement element, String fieldName, String ddValue) throws Throwable {
		try {
			Select s = new Select(element);
			s.selectByValue(ddValue);
		} catch (Exception e) {
			e.printStackTrace();
			AllureListener.saveTextLog("Not able to select value " + ddValue + " from " + fieldName);
			Assert.fail("Not able to select value " + ddValue + " from " + fieldName);
		}

	}

	// Select dropdown value value by index
	public static void selectDropDownByIndex_custom(WebElement element, String fieldName, int ddIndex)
			throws Throwable {
		try {
			Select s = new Select(element);
			s.selectByIndex(ddIndex);
		} catch (Exception e) {
			e.printStackTrace();
			AllureListener.saveTextLog("Not able to select index " + ddIndex + " from " + fieldName);
			Assert.fail("Not able to select value " + ddIndex + " from " + fieldName);
		}

	}

	/*
	 * Get the Text from Selected DropDown
	 * 
	 * @ parameter drpValue webElement
	 */
	public static String getTextFromSelectedDropDown(WebElement drpValue) {
		String selectedoption = "";
		try {
			Select select = new Select(drpValue);
			// get selected option with getFirstSelectedOption() with its text
			WebElement o = select.getFirstSelectedOption();
			selectedoption = o.getAttribute("value");
		} catch (Exception e) {
			System.out.println(e);
			AllureListener.saveTextLog("Not able to get Text from dropdown");
			Assert.fail("Not able to get Text from dropdown");
		}
		return selectedoption;
	}

	/*
	 * Get the Text from Selected Dropdown
	 * 
	 * @ parameter drpValue webElement
	 */
	public static String selectValueWithGettext(WebElement drpValue) {
		Select select = new Select(drpValue);
		// get selected option with getFirstSelectedOption() with its text
		WebElement o = select.getFirstSelectedOption();
		String selectedoption = o.getText();
		return selectedoption;
	}

	/**
	 * Validate specified option value present in dropDown
	 * 
	 * @param dropDownEle locate dropDown element
	 * @param text        enter dropdown option value for validation
	 * 
	 * @return
	 */
	public static boolean validateSpecifiedTextPresentInDropdown(WebElement dropDownEle, String fieldName,
			String ddTextValue) {
		boolean isOptionAvailable = false;
		try {
			Select dropdown = new Select(dropDownEle);
			// Get all options in the dropdown
			List<WebElement> options = dropdown.getOptions();

			for (WebElement option : options) {
				System.out.println(option.getText());
				if (option.getText().equalsIgnoreCase(ddTextValue)) {
					selectDropDownByVisibleText_custom(dropDownEle, fieldName, ddTextValue);
					isOptionAvailable = true;
					ExtentLogger.pass(ddTextValue + " :option is present in " + fieldName + " dropdown");
					AllureListener.saveTextLog(ddTextValue + " :option is present in " + fieldName + " dropdown");
					break;
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			ExtentLogger.pass(ddTextValue + " :option is not present in" + fieldName + " dropdown");
			AllureListener.saveTextLog(ddTextValue + " :option is not present in" + fieldName + " dropdown");
			Assert.fail(ddTextValue + " :option is not present in" + fieldName + " dropdown");
		}

		return isOptionAvailable;

	}

	/**
	 * Display hidden Element by setAttribute of style display:true;
	 * 
	 * @param driver  as Webdriver
	 * @param element as WebElement
	 */
	public static void displayHiddenElementByStyleDisplay(WebDriver driver, WebElement element) {
		String js = "arguments[0].setAttribute('style','display:true;')";

		((JavascriptExecutor) driver).executeScript(js, element);
	}

	// Get text from webelement
	public String getText_custom(WebElement element, String fieldName) {
		String text = "";

		text = element.getText();

		return text;
	}

	/**
	 * To wait for the specific element on the page if element is visible or Enabled
	 * 
	 * @param driver  -
	 * @param element - webelement to wait for to appear
	 * @param maxWait - how long to wait for
	 * @return boolean - return true if element is present else return false
	 */
	public static boolean waitForElement(WebDriver driver, String fieldName, WebElement element, int maxWait) {
		boolean statusOfElementToBeReturned = false;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maxWait));
		try {
			WebElement waitElement = wait.until(ExpectedConditions.visibilityOf(element));
			if (waitElement.isDisplayed() && waitElement.isEnabled()) {
				statusOfElementToBeReturned = true;
			}
		} catch (Exception ex) {
			statusOfElementToBeReturned = false;
			ExtentLogger.fail(fieldName + " not found");
			AllureListener.saveTextLog(fieldName + " not found");
		}
		return statusOfElementToBeReturned;
	}

	/**
	 * To wait for the specific element on the page as per configured Max wait
	 * 
	 * @param driver  -
	 * @param element - webelement to wait for to appear
	 * @param maxWait - how long to wait for
	 * @return boolean - return true if element is present else return false
	 */
	public static void waitForElement(WebDriver driver, String fieldName, WebElement element) {
		waitForElement(driver, fieldName, element, MaxElementWait);
	}

	public static void waitForElementClickable(WebDriver driver, String fieldName, WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			// TODO: handle exception
			ExtentLogger.fail(fieldName + " not found");
			AllureListener.saveTextLog(fieldName + " not found");
		}

	}

	// CHECK - MANDATORY
	public static boolean chkMandatory(String dValue) throws Exception {
		if (dValue.isEmpty()) {
			return false;
			// Log.message("Clicked on Go Back arrow");
		} else {
			return true;
		}
	}

	/**
	 * waitForPageLoad waits for the page load with custom page load wait time un
	 * 
	 * @param driver
	 * @param element as element which is displayed on page
	 * 
	 */
	public static void waitForPageLoad(final WebDriver driver, WebElement element) {
		FluentWait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(MaxElementWait))
				.pollingEvery(Duration.ofMillis(1000)).ignoring(StaleElementReferenceException.class)
				.withMessage("Page Load Timed Out");
		try {

			wait.until(ExpectedConditions.visibilityOf(element));

			String title = driver.getTitle().toLowerCase();
			String url = driver.getCurrentUrl().toLowerCase();

			if ("the page cannot be found".equalsIgnoreCase(title) || title.contains("is not available")
					|| url.contains("/error/") || url.toLowerCase().contains("/errorpage/")
					|| driver.getPageSource().contains("No webpage was found for the web address")) {
				Assert.fail("Page is Not loaded. [Title: " + title + ", URL:" + url + "]");
			}
		} catch (TimeoutException e) {
			driver.navigate().refresh();
			wait.until(ExpectedConditions.visibilityOf(element));
		}

	}

	// CHECK - DATE FORMAT
	public static boolean isValidFormat(String format, String value, Locale locale) {

		System.out.println("In isValidFormat Method. Format is -   " + format);
		System.out.println("In isValidFormat Method. Value is -   " + value);

		LocalDateTime ldt = null;
		DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(format, locale);
		System.out.println("11111111111");
		try {
			ldt = LocalDateTime.parse(value, fomatter);
			String result = ldt.format(fomatter);
			System.out.println("22222222222");
			return result.equals(value);
		} catch (DateTimeParseException e) {
			try {
				LocalDate ld = LocalDate.parse(value, fomatter);
				System.out.println("3333333333333");
				String result = ld.format(fomatter);
				return result.equals(value);
			} catch (DateTimeParseException exp) {
				try {
					LocalTime lt = LocalTime.parse(value, fomatter);
					System.out.println("444444444444");
					String result = lt.format(fomatter);
					return result.equals(value);
				} catch (DateTimeParseException e2) {
					// Debugging purposes
					e2.printStackTrace();
				}
			}
		}

		return false;
	}

	/**
	 * Verify Valid Format
	 * 
	 * @param format as String
	 * @param value  as String
	 * @return date
	 * @throws java.text.ParseException
	 */
	public static boolean isValidFormat1(String format, String value) throws java.text.ParseException {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		date = sdf.parse(value);
		if (!value.equals(sdf.format(date))) {
			date = null;
		}
		return date != null;
	}

	/**
	 * verify Alert Text
	 * 
	 * @param text as STring to be verify
	 * @return flag as true if text matches else return false
	 */
	public static boolean verifyAlertText(WebDriver driver, String expText) {
		boolean flag = false;

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(MaxElementWait));
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			// Click on alert Pop up Ok button
			String textAlert = alert.getText().trim();
			acceptAlert(driver);
			AllureListener.saveTextLog("Actual   : " + textAlert);
			AllureListener.saveTextLog("Expected : " + expText);
			if (textAlert.equalsIgnoreCase(expText.trim())) {
				flag = true;

			} else {
				flag = false;

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return flag;
	}

	/**
	 * Displayed Alert will be closed
	 * 
	 */
	public static void acceptAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.accept();

	}

	/**
	 * Select all text from textbox using keys
	 * 
	 * @param text
	 */
	public static void selectAllAndCopyTextUsingKey(WebElement txtbox) {
		txtbox.sendKeys(Keys.CONTROL, "A");
		txtbox.sendKeys(Keys.CONTROL, "C");
	}

	/**
	 * Select all text from textbox using keys
	 * 
	 * @param text
	 */
	public static void selectAllAndDeleteTextUsingKey(WebElement txtbox) {
		txtbox.sendKeys(Keys.CONTROL, "A");
		txtbox.sendKeys(Keys.DELETE);
	}

	/*
	 * Move to Element Mouse hover on Field
	 */
	public void Mousehover(WebElement element, String fieldName) throws InterruptedException {
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(element).perform();
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println(e);
			AllureListener.saveTextLog("Not able to move on field" + fieldName);
			Assert.fail("Not able to move on field " + fieldName);
		}
	}

	/**
	 * Get Next Date
	 * 
	 * @param dateFormat
	 * @return nextDate as String
	 */
	public static String getTodaysDate(String dateFormat) {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		String todaysDate = formatter.format(date);
		System.out.println("Todays Date : " + todaysDate);
		return todaysDate;

	}

	/**
	 * Get Next Date
	 * 
	 * @param dateFormat
	 * @return nextDate as String
	 */
	public static String getNextDate(String dateFormat) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		Date date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		String nextDate = formatter.format(date);
		return nextDate;

	}

	/**
	 * Get Previous Date
	 * 
	 * @param dateFormat
	 * @return tradingDate as String
	 */
	public static String getPreviousDate(String dateFormat) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		Date date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		String tradingDate = formatter.format(date);
		return tradingDate;

	}

	/**
	 * Get Date After Add Number Of Year
	 * 
	 * @param dateFormat as DD/MMM/YYYY
	 * @return Date After Add number of year as String
	 */
	public static String getDateAfterAddNoOfYear(String dateFormat, int noOfYear) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, noOfYear);
		Date date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		String dateWithYear = formatter.format(date);
		System.out.println("dateWithYear==>" + dateWithYear);
		return dateWithYear;

	}
}
