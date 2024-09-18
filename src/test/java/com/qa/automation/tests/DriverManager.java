package com.qa.automation.tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Reporter;

import com.qa.automation.utils.PropFileHandler;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
    private static WebDriver driver;
    private static String browser;
    private static String server;
    private static String sheetName;

    public static WebDriver getWebDriver() {
        if (driver == null) {
            initialize();
        }
        return driver;
    }

    private static void initialize() {
        Reporter.log("************************************** Session Started **************************************", true);

        // Read properties
        browser = System.getProperty("browser", PropFileHandler.readProperty("browser"));
        server = System.getProperty("server", PropFileHandler.readProperty("seleniumserver"));
        sheetName = System.getProperty("sheetName", PropFileHandler.readProperty("sheet"));

        Reporter.log("Server: " + server.toUpperCase(), true);
        Reporter.log("Browser: " + browser.toUpperCase(), true);

        if ("local".equalsIgnoreCase(server)) {
            initializeLocalDriver();
        } else if (server.contains("remote")) {
            driver = setRemoteDriver();
        } else {
            throw new IllegalArgumentException("Server type not supported: " + server);
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(
                Integer.parseInt(PropFileHandler.readProperty("implicitTimeOut")), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(
                Integer.parseInt(PropFileHandler.readProperty("pageLoadTimeOut")), TimeUnit.SECONDS);
    }

    private static void initializeLocalDriver() {
        if (driver != null) {
            Reporter.log("Driver instance already initialized, skipping reinitialization.", true);
            return;
        }

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized", "--disable-notifications", "--ignore-ssl-errors=yes",
                        "--ignore-certificate-errors", "--incognito");
                chromeOptions.setExperimentalOption("prefs", new HashMap<String, Object>() {{
                    put("download.default_directory", Paths.get("downloadedData").toAbsolutePath().toString());
                }});
                driver = new ChromeDriver(chromeOptions);
                Reporter.log("ChromeDriver initialized with options: " + chromeOptions.toString(), true);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                Reporter.log("FirefoxDriver initialized!", true);
                break;

            case "safari":
                driver = new SafariDriver();
                Reporter.log("SafariDriver initialized!", true);
                break;

            case "ie":
            case "internet explorer":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                Reporter.log("InternetExplorerDriver initialized!", true);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                Reporter.log("EdgeDriver initialized!", true);
                break;

            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
    }

    private static WebDriver setRemoteDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito", "--enable-javascript", "--start-fullscreen");
                options.setAcceptInsecureCerts(true);
                options.setPageLoadStrategy(PageLoadStrategy.EAGER);
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                break;

            // Add other browser configurations if needed
            default:
                throw new IllegalArgumentException("Remote browser not supported: " + browser);
        }

        String seleniumHubAddress = System.getProperty("vm.IP", PropFileHandler.readProperty("seleniumserverhost"));
        try {
            URL hubUrl = new URL(seleniumHubAddress);
            Reporter.log("Target Hub Address: " + hubUrl, true);
            return new RemoteWebDriver(hubUrl, capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Selenium Hub URL: " + seleniumHubAddress, e);
        }
    }

    public static void quitSession() {
        if (driver != null) {
            driver.quit();
            Reporter.log("************************************** Session closed **************************************", true);
        }
    }

    public static void closeWindow() {
        if (driver != null) {
            driver.close();
            Reporter.log("************************************** Window closed **************************************", true);
        }
    }
}
