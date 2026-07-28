////package com.azhar.base; // Tells the computer this file lives inside the 'com.azhar.base' folder
////
////import org.openqa.selenium.WebDriver; // Brings in the Selenium WebDriver tools to control browsers
////import org.openqa.selenium.chrome.ChromeDriver; // Brings in the specific tool to control the Google Chrome browser
////import org.testng.annotations.AfterMethod; // Brings in the TestNG rule to run code AFTER a test finishes
////import org.testng.annotations.BeforeMethod; // Brings in the TestNG rule to run code BEFORE a test starts
////import java.time.Duration; // Brings in Java's time tool, which we will use to make the browser wait
////
////public class BaseTest { // Creates our main blueprint called BaseTest
////
////    protected WebDriver driver; // Creates a variable called 'driver' that represents our browser. 'protected' means our test files can easily use it.
////
////    @BeforeMethod // TestNG tag: This tells the program "Run the method below me BEFORE every single test case"
////    public void setUp() { // We name this method setUp, as it sets up our environment
////
////        driver = new ChromeDriver(); // This line actually launches a brand new, empty Google Chrome window
////
////        driver.manage().window().maximize(); // This takes that Chrome window and makes it full screen
////
////        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // This is a safety net. It tells Selenium: "If you can't find a button or text box immediately, keep looking for up to 10 seconds before failing the test."
////
////        driver.get("https://www.saucedemo.com/"); // This types the SauceDemo URL into the browser and hits Enter
////
////    } // Ends the setUp method
////
////    @AfterMethod // TestNG tag: This tells the program "Run the method below me AFTER every single test case finishes"
////    public void tearDown() { // We name this method tearDown, as it cleans up our environment
////
////        if (driver != null) { // A quick check to make sure the browser actually exists and didn't already crash
////            driver.quit(); // This completely closes the browser window and safely shuts down the ChromeDriver process in the background
////        } // Ends the if statement
////
////    } // Ends the tearDown method
////
////} // Ends the BaseTest class
//
//
//
////package com.azhar.base;
////
////        import org.openqa.selenium.WebDriver;
////        import org.openqa.selenium.chrome.ChromeDriver;
////        import org.openqa.selenium.chrome.ChromeOptions; // Brings in the tool to make rules for Chrome
////        import org.testng.annotations.AfterMethod;
////        import org.testng.annotations.BeforeMethod;
////        import java.time.Duration;
////        import java.util.HashMap; // Brings in a tool to store our preferences
////        import java.util.Map;
////
////public class BaseTest {
////
////    protected WebDriver driver;
////
////    @BeforeMethod
////    public void setUp() {
////
////        // 1. Create a new list of rules for Chrome
////        ChromeOptions options = new ChromeOptions();
////
////        // 2. Tell Chrome to completely disable its password manager and breach alerts
////        Map<String, Object> prefs = new HashMap<>();
////        prefs.put("credentials_enable_service", false);
////        prefs.put("profile.password_manager_enabled", false);
////        options.setExperimentalOption("prefs", prefs);
////
////        // 3. Launch Chrome, but this time hand it our strict rules!
////        driver = new ChromeDriver(options);
////
////        // 4. Maximize the window
////        driver.manage().window().maximize();
////
////        // 5. Tell the browser to wait up to 10 seconds for elements to appear
////        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
////
////        // 6. Open the website
////        driver.get("https://www.saucedemo.com/");
////    }
////
////    @AfterMethod
////    public void tearDown() {
////        if (driver != null) {
////            driver.quit();
////        }
////    }
////}
//
//
//package com.azhar.base;
//
//        import org.openqa.selenium.WebDriver;
//        import org.openqa.selenium.chrome.ChromeDriver;
//        import org.openqa.selenium.chrome.ChromeOptions;
//        import org.testng.annotations.AfterMethod;
//        import org.testng.annotations.BeforeMethod;
//        import java.time.Duration;
//
//public class BaseTest {
//
//    protected WebDriver driver;
//
//    @BeforeMethod
//    public void setUp() {
//
//        ChromeOptions options = new ChromeOptions();
//
//        // 1. THE ULTIMATE FIX: Tell Chrome to open in Incognito mode
//        options.addArguments("--incognito");
//
//        // 2. Just in case, block any other system notifications
//        options.addArguments("--disable-notifications");
//
//        // 3. Launch Chrome with our new arguments
//        driver = new ChromeDriver(options);
//
//        // 4. Maximize the window
//        driver.manage().window().maximize();
//
//        // 5. Smart wait for 10 seconds
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//
//        // 6. Open the website
//        driver.get("https://www.saucedemo.com/");
//    }
//
//    @AfterMethod
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//}

//package com.azhar.base;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.edge.EdgeOptions;
//import org.openqa.selenium.io.FileHandler;
//import org.testng.ITestResult;
//import org.testng.annotations.*; // Brings in @Parameters and @Optional
//
//import java.io.File;
//import java.io.IOException;
//import java.lang.reflect.Method;
//import java.time.Duration;
//
//public class BaseTest {
//
//    protected WebDriver driver;
//    protected static ExtentReports extent;
//    protected static ExtentTest test;
//
//    @BeforeSuite
//    public void setupReport() {
//        ExtentSparkReporter spark = new ExtentSparkReporter("reports/AutomationReport.html");
//        spark.config().setDocumentTitle("SauceDemo QA Dashboard");
//        spark.config().setReportName("End-to-End Test Results");
//
//        extent = new ExtentReports();
//        extent.attachReporter(spark);
//        extent.setSystemInfo("QA Engineer", "Azhar");
//    }
//
//    // 1. THE UPGRADE: We tell TestNG to listen for a "browser" parameter from the XML file
//    @Parameters("browser")
//    @BeforeMethod
//    public void setUp(@Optional("chrome") String browser, Method method) {
//
//        // Add the browser name to the report so we know which one ran!
//        test = extent.createTest(method.getName() + " (" + browser.toUpperCase() + ")");
//
//        // 2. THE LOGIC: Choose the driver based on the XML parameter
//        if (browser.equalsIgnoreCase("chrome")) {
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--incognito", "--disable-notifications");
//            driver = new ChromeDriver(options);
//
//        } else if (browser.equalsIgnoreCase("edge")) {
//            EdgeOptions options = new EdgeOptions();
//            options.addArguments("--inprivate", "--disable-notifications"); // Edge calls it InPrivate
//            driver = new EdgeDriver(options);
//
//        } else {
//            throw new IllegalArgumentException("Invalid browser name provided: " + browser);
//        }
//
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.get("https://www.saucedemo.com/");
//    }
//
//    @AfterMethod
//    public void tearDown(ITestResult result) {
//        if (result.getStatus() == ITestResult.SUCCESS) {
//            test.log(Status.PASS, "Test Passed Successfully!");
//        }
//        else if (result.getStatus() == ITestResult.FAILURE) {
//            test.log(Status.FAIL, "Test Failed: " + result.getThrowable().getMessage());
//            try {
//                TakesScreenshot camera = (TakesScreenshot) driver;
//                File rawScreenshot = camera.getScreenshotAs(OutputType.FILE);
//
//                File screenshotDir = new File("screenshots");
//                if (!screenshotDir.exists()) {
//                    screenshotDir.mkdir();
//                }
//
//                String screenshotPath = "screenshots/" + result.getName() + ".png";
//                File destination = new File(screenshotPath);
//                FileHandler.copy(rawScreenshot, destination);
//                test.addScreenCaptureFromPath("../" + screenshotPath);
//
//            } catch (IOException e) {
//                System.out.println("Error saving screenshot: " + e.getMessage());
//            }
//        }
//
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//
//    @AfterSuite
//    public void flushReport() {
//        extent.flush();
//    }
//}

                                  // Parallel execution

package com.azhar.base;

        import com.aventstack.extentreports.ExtentReports;
        import com.aventstack.extentreports.ExtentTest;
        import com.aventstack.extentreports.Status;
        import com.aventstack.extentreports.reporter.ExtentSparkReporter;
        import org.openqa.selenium.OutputType;
        import org.openqa.selenium.TakesScreenshot;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.chrome.ChromeOptions;
        import org.openqa.selenium.edge.EdgeDriver;
        import org.openqa.selenium.edge.EdgeOptions;
        import org.openqa.selenium.io.FileHandler;
        import org.testng.ITestResult;
        import org.testng.annotations.*;

        import java.io.File;
        import java.io.IOException;
        import java.lang.reflect.Method;
        import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extent;

    // 1. THE THREAD-SAFE UPGRADE: We wrap ExtentTest in a ThreadLocal
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/AutomationReport.html");
        spark.config().setDocumentTitle("SauceDemo QA Dashboard");
        spark.config().setReportName("End-to-End Test Results");

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("QA Engineer", "Azhar");
    }

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser, Method method) {

        // 2. Use .set() to assign a unique report page to the current thread
        ExtentTest extentTest = extent.createTest(method.getName() + " (" + browser.toUpperCase() + ")");
        test.set(extentTest);

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito", "--disable-notifications");
            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--inprivate", "--disable-notifications");
            driver = new EdgeDriver(options);

        } else {
            throw new IllegalArgumentException("Invalid browser name provided: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        // 3. Use .get() to retrieve the specific report page for this thread
        if (result.getStatus() == ITestResult.SUCCESS) {
            test.get().log(Status.PASS, "Test Passed Successfully!");
        }
        else if (result.getStatus() == ITestResult.FAILURE) {
            test.get().log(Status.FAIL, "Test Failed: " + result.getThrowable().getMessage());
            try {
                TakesScreenshot camera = (TakesScreenshot) driver;
                File rawScreenshot = camera.getScreenshotAs(OutputType.FILE);

                File screenshotDir = new File("screenshots");
                if (!screenshotDir.exists()) {
                    screenshotDir.mkdir();
                }

                String screenshotPath = "screenshots/" + result.getName() + ".png";
                File destination = new File(screenshotPath);
                FileHandler.copy(rawScreenshot, destination);

                test.get().addScreenCaptureFromPath("../" + screenshotPath);

            } catch (IOException e) {
                System.out.println("Error saving screenshot: " + e.getMessage());
            }
        }

        // 4. Clean up the ThreadLocal to prevent memory leaks
        test.remove();

        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
}