package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import utils.ConfigReader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseTest {
    protected static WebDriver driver;
    protected static ExtentReports extent;
    protected static ExtentTest test;
    protected static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @BeforeSuite
    public void setUpSuite() {
        try {
            // Verify required config properties
            String baseUrl = ConfigReader.getProperty("baseUrl");
            if (baseUrl == null || baseUrl.isEmpty()) {
                throw new RuntimeException("baseUrl is required in config.properties");
            }

            // Initialize ExtentReports
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(
                    System.getProperty("user.dir") + "/test-output/HealthHubReport_" + timestamp + ".html");
            
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            
            // Configure report
            sparkReporter.config().setDocumentTitle("Health Hub Automation Report");
            sparkReporter.config().setReportName("Test Execution Report");
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
            
            // System information
            extent.setSystemInfo("Application", "Health Hub");
            extent.setSystemInfo("Environment", ConfigReader.getProperty("env", "staging"));
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            
        } catch (Exception e) {
            System.err.println("Test suite initialization failed: " + e.getMessage());
            throw e;
        }
    }

    @BeforeMethod
    @Parameters("browser")
    public void setUp(Method method, @Optional("chrome") String browser) {
        try {
            // Initialize test reporting first
            test = extent.createTest(
                method.getName(), 
                method.getAnnotation(Test.class).description()
            );
            extentTest.set(test);
            
            // Get config values with safe defaults
            String baseUrl = ConfigReader.getProperty("baseUrl");
            boolean headless = ConfigReader.getBooleanProperty("headless", false);
            int implicitWait = ConfigReader.getIntProperty("implicitWait", 10);
            
            // Initialize WebDriver with options
            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (headless) chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--start-maximized");
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
                    driver = new ChromeDriver(chromeOptions);
                    break;
                    
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (headless) firefoxOptions.addArguments("--headless");
                    System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                    
                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if (headless) edgeOptions.addArguments("--headless=new");
                    System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");
                    driver = new EdgeDriver(edgeOptions);
                    break;
                    
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
            
            // Configure driver
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
            
            // Log browser info
            test.log(Status.INFO, "Browser: " + browser.toUpperCase() + 
                  (headless ? " (headless)" : ""));
            
            // Navigate to base URL
            driver.get(baseUrl);
            test.log(Status.INFO, "Navigated to: " + baseUrl);
            
        } catch (Exception e) {
            if (test != null) {
                test.log(Status.FAIL, "Test initialization failed: " + e.getMessage());
            } else {
                System.err.println("Test initialization failed before ExtentTest creation: " + e.getMessage());
            }
            throw new RuntimeException("Test setup failed", e);
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                extentTest.get().log(
                    Status.FAIL, 
                    MarkupHelper.createLabel(result.getName() + " - FAILED", ExtentColor.RED)
                );
                extentTest.get().fail(result.getThrowable());
                
                // Capture screenshot
                String screenshotPath = captureScreenshot(result.getName());
                extentTest.get().addScreenCaptureFromPath(screenshotPath);
                
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                extentTest.get().log(
                    Status.PASS, 
                    MarkupHelper.createLabel(result.getName() + " - PASSED", ExtentColor.GREEN)
                );
            } else {
                extentTest.get().log(
                    Status.SKIP, 
                    MarkupHelper.createLabel(result.getName() + " - SKIPPED", ExtentColor.ORANGE)
                );
            }
        } catch (Exception e) {
            System.err.println("Error during test teardown: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @AfterSuite
    public void tearDownSuite() {
        if (extent != null) {
            extent.flush();
        }
    }

    private String captureScreenshot(String screenshotName) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotDir = System.getProperty("user.dir") + "/Screenshots/";
        
        // Create directory if it doesn't exist
        new File(screenshotDir).mkdirs();
        
        String screenshotPath = screenshotDir + screenshotName + "_" + timestamp + ".png";
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File(screenshotPath));
        
        if (test != null) {
            test.log(Status.INFO, "Screenshot saved at: " + screenshotPath);
        }
        return screenshotPath;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }
}