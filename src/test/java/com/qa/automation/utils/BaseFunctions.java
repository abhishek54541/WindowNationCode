package com.qa.automation.utils;
 
import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
 
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
 
import com.qa.automation.tests.DriverManager;
 
public class BaseFunctions {
	protected WebDriverWait wait;
	static String parentWindow;
	public static String domain;
	protected static WebDriver driver;
	public static YamlReader reader;
 
	protected BaseFunctions() {
		driver = DriverManager.getWebDriver();
		reader = new YamlReader();
	}
	
	public void waitForElementAndSendKeys(WebElement element, String text, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));  // Wait for element to be visible
        element.clear();  // Clear any existing text (optional)
        element.sendKeys(text);  // Send the provided text
    }
	
	public void sendKeysAndEnter(WebElement element, String text, int timeoutInSeconds) {
	    // Initialize WebDriverWait
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	    // Wait until the element is visible
	    wait.until(ExpectedConditions.visibilityOf(element));
	    // Clear any existing text (optional)
	    element.clear();
	    // Send the provided text and simulate pressing the "Enter" key
	    element.sendKeys(text, Keys.ENTER);
	}
	
	
	public void sendKeysAndTab(WebElement element, String text, int timeoutInSeconds) {
	    // Initialize WebDriverWait
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	    // Wait until the element is visible
	    wait.until(ExpectedConditions.visibilityOf(element));
	    // Clear any existing text (optional)
	    element.clear();
	    // Send the provided text and simulate pressing the "Enter" key
	    element.sendKeys(text, Keys.TAB);
	}
	
	
	public void waitForElementAndClick(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));  // Wait for element to be visible
        element.click();  // Perform click
    }
 
	protected boolean element_visibility(By elem, String replacementText) {
		boolean flag = false;
		WebElement elementToken;
		try {
			elementToken = element(elem, replacementText);
			Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(5))
					.pollingEvery(Duration.ofSeconds(1));
//			Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(5, TimeUnit.SECONDS).pollingEvery(1,
//					TimeUnit.SECONDS);
			wait1.until(ExpectedConditions.visibilityOf((WebElement) elementToken));
			if (((WebElement) elementToken).getSize() != null)
				flag = true;
		} catch (TimeoutException excp) {
		} catch (NoSuchElementException n) {
		} catch (WebDriverException w) {
		}
		return flag;
	}
 
	protected void waitForPageToLoadCompletely() {
		Timeouts time = driver.manage().timeouts();
		time.pageLoadTimeout(60, TimeUnit.SECONDS);
	}
 
	protected WebElement waitForElementToBeVisible(WebElement element) {
		FluentWait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(60))
				.pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
		return (WebElement) wait.until(ExpectedConditions.visibilityOf(element));
	}
 
	protected WebElement waitForElementToBeVisible(By ele) {
		WebElement element = element(ele);
		Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(5))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(TimeoutException.class);
//		Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
//				.pollingEvery(3, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
//				.ignoring(.class);
		return (WebElement) wait1.until(ExpectedConditions.visibilityOf(element));
	}
 
	protected WebElement waitForElementToBeVisible(By ele, String text) {
		WebElement element = element(ele, text);
		Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(3)).ignoring(TimeoutException.class);
//		Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(20, TimeUnit.SECONDS)
//				.pollingEvery(3, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
//				.ignoring(TimeoutException.class);
		return (WebElement) wait1.until(ExpectedConditions.visibilityOf(element));
	}
 
	public void launchApplication(String url) {
		driver.get(url);
		hardWait(4);
		logMessage("**[INFO]: Product URL :: " + url);
	}
 
	public void closeApplication() {
		DriverManager.quitSession();
//		DriverManager.closeWindow();
	}
 
	protected void waitTOSync() {
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	protected void hardWait(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	protected void waitFor_N_Seconds(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	public void launchProduct(String Product, String url) {
		driver.get(url);
		logMessage("**[Info]: " + Product + " URL :: " + url);
	}
 
	public static void logMessage(String msg) {
		Reporter.log(msg, true);
	}
 
	// protected void waitForPageToLoadCompletely()
	// {
	// Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver)
	// .withTimeout(30, TimeUnit.SECONDS)
	// .pollingEvery(5, TimeUnit.SECONDS)
	// .ignoring(NoSuchElementException.class);
	// wait1.until(webDriver -> ((JavascriptExecutor)
	// webDriver).executeScript("return
	// document.readyState").equals("complete"));
	// }
	protected void waitForUrlToContain(String str) {
		Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
//		@SuppressWarnings("deprecation")
//		Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(20, TimeUnit.SECONDS)
//				.pollingEvery(3, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		wait1.until(ExpectedConditions.urlContains(str));
	}
 
	protected void selectDropdownOptionText(WebElement elem, String text) {
		Select sel = new Select(elem);
		sel.selectByVisibleText(text);
	}
 
	protected void selectDropdownOptionText(By e, String text) {
		WebElement elem = element(e);
		Select sel = new Select(elem);
		sel.selectByVisibleText(text);
	}
 
	protected WebElement element(WebElement e) {
		return e;
	}
 
	protected String getCurrentTimestamp() {
		// Format formatter = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
		Format formatter = new SimpleDateFormat("ddMMyyHHmmss");
		String timestamp = formatter.format(new Date());
		return timestamp;
	}
 
	protected void SendKeysUsingJavaScript(WebElement e, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].value='" + value + "';", e);
	}
 
	protected void clickUsingJavaScript(By elem) {
		WebElement e = element(elem);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
	}
 
	protected void clickUsingJavaScript(WebElement e) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
	}
 
	protected void executeJavascript(String script) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(script);
	}
 
	protected Object executeJavascriptWithReturn(String script) {
		return ((JavascriptExecutor) driver).executeScript(script);
	}
 
	protected Object executeJavascriptWithReturn(String script, WebElement e) {
		return ((JavascriptExecutor) driver).executeScript(script, e);
	}
 
	protected String getCurrentURL() {
		return driver.getCurrentUrl();
	}
 
	protected boolean element_visibility(By elem) {
		boolean flag = false;
		WebElement elementToken;
 
		try {
			elementToken = element(elem);
			Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(5))
					.pollingEvery(Duration.ofSeconds(1));
//			Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(5, TimeUnit.SECONDS).pollingEvery(1,
//					TimeUnit.SECONDS);
//			Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(5, TimeUnit.SECONDS).pollingEvery(1,
//					TimeUnit.SECONDS);
			wait1.until(ExpectedConditions.visibilityOf((WebElement) elementToken));
			if (((WebElement) elementToken).getSize() != null)
				flag = true;
		} catch (TimeoutException excp) {
		} catch (NoSuchElementException n) {
		} catch (WebDriverException w) {
		}
		return flag;
	}
 
	protected boolean element_visibility_ByText(By elem, String text) {
		boolean flag = false;
		WebElement elementToken;
 
		try {
			elementToken = element(elem, text);
			Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(5))
					.pollingEvery(Duration.ofSeconds(1));
//			Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(5, TimeUnit.SECONDS).pollingEvery(1,
//					TimeUnit.SECONDS);
			wait1.until(ExpectedConditions.visibilityOf((WebElement) elementToken));
			if (((WebElement) elementToken).getSize() != null)
				flag = true;
		} catch (TimeoutException excp) {
		} catch (NoSuchElementException n) {
		} catch (WebDriverException w) {
		}
		return flag;
	}
 
	protected boolean element_visibility(By elem, int timeout) {
		boolean flag = false;
		WebElement elementToken;
 
		try {
			elementToken = element(elem);
			Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
					.pollingEvery(Duration.ofSeconds(1));
//			Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(timeout, TimeUnit.SECONDS)
//					.pollingEvery(1, TimeUnit.SECONDS);
			wait1.until(ExpectedConditions.visibilityOf((WebElement) elementToken));
			if (((WebElement) elementToken).getSize() != null)
				flag = true;
		} catch (TimeoutException excp) {
		} catch (NoSuchElementException n) {
		} catch (WebDriverException w) {
		}
		return flag;
	}
 
	protected void waitForElementToDisappear(By element) {
		WebElement e = element(element);
		Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
		wait1.until(ExpectedConditions.invisibilityOf(e));
	}
	
	protected void waitForElementToDisappear(By element, String text) {
		WebElement e = element(element, text);
		Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
		wait1.until(ExpectedConditions.invisibilityOf(e));
	}

	protected WebElement waitForElementToBeClickable(By elem) {
		WebElement e = element(elem);
		Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
		wait1.until(ExpectedConditions.elementToBeClickable(e));
		return e;
	}
 
	protected WebElement waitForElementToBeClickable_ByText(By elem, String text) {
		WebElement e = element(elem, text);
		Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
		wait1.until(ExpectedConditions.elementToBeClickable(e));
		return e;
	}
 
	protected void clickUsingActions(WebElement e) {
		Actions action = new Actions(driver);
		action.moveToElement(e).click().build().perform();
	}
 
	protected void MousehoverUsingActions(By element) {
		WebElement e = element(element);
		Actions action = new Actions(driver);
		action.moveToElement(e).perform();
	}
 
	protected List<WebElement> elements(By elementToken) {
		return driver.findElements(getLocator(elementToken, ""));
	}
 
	protected List<WebElement> elements(By elementToken, String replacement) {
		return driver.findElements(getLocator(elementToken, replacement));
	}
 
	protected WebElement element(By elementToken) {
		return driver.findElement(getLocator(elementToken, ""));
	}
 
	protected WebElement element(By elementToken, String replacement) {
		return driver.findElement(getLocator(elementToken, replacement));
	}
 
	protected By getLocator(By elementToken, String replacement) {
		if (!replacement.isEmpty()) {
			String loc = elementToken.toString().replaceAll("\'", "\\\"");
			String type = loc.split(":", 2)[0].split(",")[0].split("\\.")[1];
			String variable = loc.split(":", 2)[1].replaceAll("\\$\\{.+?\\}", replacement);
			return getBy(type, variable);
		} else {
			return elementToken;
		}
	}
 
	private By getBy(String locatorType, String locatorValue) {
		switch (Locators.valueOf(locatorType)) {
		case id:
			return By.id(locatorValue);
		case xpath:
			return By.xpath(locatorValue);
		case css:
		case cssSelector:
			return By.cssSelector(locatorValue);
		case name:
			return By.name(locatorValue);
		case classname:
			return By.className(locatorValue);
		case linktext:
			return By.linkText(locatorValue);
		default:
			return By.id(locatorValue);
		}
	}
 
	protected void pressEnterKeyOnElement(By elem) {
		WebElement e = element(elem);
		Actions ac = new Actions(driver);
		Point point = e.getLocation();
		Dimension s = e.getSize();
		int x = point.getX();
		int y = point.getY();
		int Y = s.getHeight();
		int X = s.getWidth();
		int XX = x / 2 + x;
		int YY = y + Y / 2;
		System.out.println("-----------------------" + XX);
		System.out.println("========================" + YY);
		ac.moveByOffset(x + X / 2, y + Y / 2).click().build().perform();
		ac.sendKeys(Keys.ARROW_DOWN);
		ac.sendKeys(Keys.RETURN);
 
		// ac.moveToElement(e).sendKeys(Keys.ENTER).build().perform();
	}
 
	protected void clickElementUsingCoordinates(WebElement e) {
		Actions ac = new Actions(driver);
		Point point = e.getLocation();
		int x = point.getX();
		int y = point.getY();
		System.out.println("Coordinates: X:" + x + "Y:" + y);
		ac.moveByOffset(x, y).sendKeys(Keys.ENTER).build().perform();
	}
 
	public String executeScript(String value) {
		return (String) ((JavascriptExecutor) driver).executeScript(value);
	}
 
	public void cssJavaScriptUsingAction(String csslocation, String Action, String setValue) {
		waitTOSync();
 
		String js = "document.querySelector(\"" + csslocation + "\")" + Action + "= '" + setValue + "';";
		System.out.println(js);
		((JavascriptExecutor) driver).executeScript(js);
	}
 
	public void deleteCookies() {
		driver.manage().deleteAllCookies();
		logMessage("All cookies have been deleted");
	}
 
	public void clickBrowserBackBtn() {
		waitTOSync();
		driver.navigate().back();
		logMessage("Clicked on browser back button");
	}
 
	public void switchToNewTab() {
		hardWait(5);
		parentWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
 
		for (String winHandle : handles) {
			if (!parentWindow.equalsIgnoreCase(winHandle)) {
				driver.switchTo().window(winHandle);
				break;
			}
		}
		waitTOSync();
		waitTOSync();
		logMessage("[INFO]: Switched to new window having URL: " + getCurrentURL());
	}
 
	public void switchToNewtabSafari() {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
		waitTOSync();
		logMessage("[INFO]: Switched to new window having URL: " + getCurrentURL());
	}
 
	public void closeCurrentWindow() {
		driver.close();
		logMessage("Closed current window");
		driver.switchTo().window(parentWindow);
		logMessage("Switched back to original window");
	}
 
	public void switchToParentTab() {
		driver.switchTo().window(parentWindow);
		logMessage("Switched back to original window");
	}
 
	public void scrollDown(By elem) {
		WebElement element = element(elem);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
 
	protected void scrollDown(WebElement e) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
	}
 
	protected void scrollDown() {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,10000);");
	}
 
	protected void scrollDown_ToElement(By elem, String text) {
		try {
			WebElement element = element(elem, text);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			logMessage("[INFO]: Element not found");
		}
	}
 
	public static boolean compareDate(Date date1, Date date2, String operator) {
		boolean result = false;
		if (operator.equals("="))
			result = date1.equals(date2);
		else if (operator.equals("<"))
			result = date1.before(date2);
		else if (operator.equals(">"))
			result = date1.after(date2);
		else if (operator.equals(">="))
			result = date1.after(date2) || date1.equals(date2);
		else if (operator.equals("<="))
			result = date1.before(date2) || date1.equals(date2);
 
		return result;
	}
 
	public static long betweenDates(Date firstDate, Date secondDate) throws IOException {
		return ChronoUnit.DAYS.between(firstDate.toInstant(), secondDate.toInstant());
	}
 
	public void refreshPage() {
		driver.navigate().refresh();
		logMessage("Page is refreshed");
	}
 
	protected void hoverOnElement(WebElement e) {
		Actions ac = new Actions(driver);
		ac.moveToElement(e).build().perform();
	}
	// public void clickXpathUsingJS(String locator)
	// {
	// String js = "var targetElement2 = document.evaluate(\"" + locator
	// + "\",document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null
	// ).singleNodeValue;"
	// + "var evt=new Event('mouseover');" + "var evt1=new Event('click');"
	// + "targetElement.dispatchEvent(evt);" + "targetElement2.click();";
	// ((JavascriptExecutor) driver).executeScript(js);
	// }
 
	public void clickXpathUsingJS(String locator) {
		String js = "var xPathRes = document.evaluate(\"" + locator
				+ "\",document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null );"
				+ "xPathRes.singleNodeValue.click();";
		((JavascriptExecutor) driver).executeScript(js);
	}
 
	public static String getSelectorAsString(By by) {
		String str = by.toString();
		return str.substring(str.indexOf(" "), str.length());
	}
 
	public boolean checkIfActualArrayContainsExpectedArray(String[] actual, String[] expected) {
		int i = 0, j = 0;
		for (i = 0; i < actual.length; i++) {
			for (j = 0; j < expected.length; j++) {
				if (actual[i] == expected[j])
					break;
			}
			if (j == 4)
				return false;
		}
 
		return true;
	}
 
	public String removeAllSpecialCharacters(String str) {
 
		String resultStr = "";
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) > 64 && str.charAt(i) <= 122) {
				resultStr = resultStr + str.charAt(i);
			}
		}
		return resultStr;
 
	}
 
	public void openLinkInNewTab(String url) throws AWTException {
 
		((JavascriptExecutor) driver).executeScript("window.open('" + url + "','_blank');");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
	}
 
	// make this dynamic
	public void switchToTab2() {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(2));
	}
 
	public void switchToTab3() {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(3));
	}
 
	public void switchToTab0() {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
	}
 
	public void switchToFrame(WebElement frame) {
		driver.switchTo().frame(frame);
	}
 
	public String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
 
	}
 
	public String getRandomNumber() {
		Random rand = new Random();
		int num1 = (rand.nextInt(7) + 1) * 100 + (rand.nextInt(8) * 10) + rand.nextInt(8);
		int num2 = rand.nextInt(743);
		int num3 = rand.nextInt(10000);
 
		DecimalFormat df3 = new DecimalFormat("000"); // 3 zeros
		DecimalFormat df4 = new DecimalFormat("0000"); // 4 zeros
 
		String phoneNumber = df3.format(num1) + df3.format(num2) + df4.format(num3);
 
		return phoneNumber;
	}
 
	public String getRandomChar() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
 
	}
 
	public String getRandomPrefix() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 3) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
 
	}
 
	public boolean isFileDownloaded(String fileName) {
		boolean flag = false;
		File dir = new File(Paths.get("downloadedData").toAbsolutePath().toString());
		File[] dir_contents = dir.listFiles();
 
		for (int i = 0; i < dir_contents.length; i++) {
			hardWait(2);
			if (dir_contents[i].getName().contains(fileName))
				return flag = true;
		}
 
		return flag;
	}
 
	public boolean isReportDownloaded(String fileName) {
		boolean flag = false;
		File dir = new File(Paths.get("downloadedHRDeskReport").toAbsolutePath().toString());
		File[] dir_contents = dir.listFiles();
 
		for (int i = 0; i < dir_contents.length; i++) {
			hardWait(2);
			if (dir_contents[i].getName().contains(fileName))
				return flag = true;
		}
 
		return flag;
	}
 
	public void cleanDirectory() {
		File dir = new File(Paths.get("downloadedData").toAbsolutePath().toString());
		try {
			FileUtils.cleanDirectory(dir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
	public void cleanReportDirectory() {
		File dir = new File(Paths.get("downloadedHRDeskReport").toAbsolutePath().toString());
		try {
			FileUtils.cleanDirectory(dir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
	public static String getUserDirFilePath(String pathOfFileToBeUploaded) {
		String workingDir = System.getProperty("user.dir");
		String filepath = workingDir + pathOfFileToBeUploaded;
		return filepath;
	}
 
	public void doubleClickOnButton(WebElement e) {
		Actions act = new Actions(driver);
		act.moveToElement(e).doubleClick().perform();
	}
	
	// Get text of element 
	public String getElementText(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.getText();
        } catch (NoSuchElementException e) {
            System.err.println("Element with locator " + locator + " not found.");
            e.printStackTrace();
            return "";
        }
    }
	
	// Scroll until found
	public void scrollUntilElementFound(By elem) {
	    boolean elementFound = false;
	    int maxScrollAttempts = 5; // You can adjust this number based on how many attempts you want
	    int attempt = 0;

	    while (!elementFound && attempt < maxScrollAttempts) {
	        try {
	            // Try to find the element
	            WebElement element = driver.findElement(elem);
	            if (element.isDisplayed()) {
	                // Scroll the element into view if found
	                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	                elementFound = true;
	                System.out.println("Element found and scrolled into view.");
	            }
	        } catch (NoSuchElementException e) {
	            // Scroll down slightly if element is not found and attempt again
	            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
	            System.out.println("Scrolling down to search for element...");
	        }
	        attempt++;
	    }

	    if (!elementFound) {
	        System.out.println("Element not found after " + maxScrollAttempts + " scroll attempts.");
	    }
	}
	
	// Scroll and click on located element
	public void scrollAndClick(By locator) {
	    WebElement element = driver.findElement(locator);
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);  // Scroll the element into view
	    element.click();
	}


}