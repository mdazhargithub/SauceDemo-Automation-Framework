//package com.azhar.tests; // Tells the computer this file is inside the 'tests' folder
//
//import com.azhar.base.BaseTest; // Brings in our browser engine (the setup and teardown steps)
//import com.azhar.pages.LoginPage; // Brings in the login page blueprint we just made
//import org.testng.Assert; // Brings in TestNG's checking tool (to decide if a test passes or fails)
//import org.testng.annotations.Test; // Brings in the TestNG sticky note for "This is a test case"
//
//// The phrase "extends BaseTest" is very important.
//// It means this file automatically inherits the browser and the @BeforeMethod/@AfterMethod from our BaseTest!
//public class LoginTest extends BaseTest {
//
//    @Test // TestNG sticky note: "Hey computer, run this specific block of code as an official test"
//    public void testValidLogin() { // We name our method to describe exactly what we are testing
//
//        // 1. Connect to the Page Blueprint
//        // We create a new 'loginPage' object and hand it our active browser ('driver')
//        LoginPage loginPage = new LoginPage(driver);
//
//        // 2. Perform the Actions (Using the exact methods we wrote in LoginPage.java)
//        loginPage.enterUsername("standard_user"); // Types the correct username into the box
//        loginPage.enterPassword("secret_sauce"); // Types the correct password into the box
//        loginPage.clickLogin(); // Clicks the login button
//
//        // 3. The Assertion (The Verification Step)
//        // After logging in, the website URL should change to include the word "inventory.html".
//        // We ask the driver what the current URL is, and check if it contains that word.
//        boolean isLoginSuccessful = driver.getCurrentUrl().contains("inventory.html");
//
//        // Assert.assertTrue means "I expect the value in the parenthesis to be TRUE."
//        // If it is true, the test PASSES. If it is false, the test FAILS and prints our error message.
//        Assert.assertTrue(isLoginSuccessful, "The login failed! We did not reach the inventory page.");
//
//    } // Ends our test case method
//
//} // Ends the LoginTest class

                              // for data driven test use below code
package com.azhar.tests;

import com.azhar.base.BaseTest;
import com.azhar.pages.LoginPage;
import com.azhar.utils.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    // 1. THE DATA PROVIDER: This is our "spreadsheet" of test data
//    @DataProvider(name = "loginCredentials")
//    public Object[][] getLoginData() {
//        return new Object[][] {
//                // Row 1: A valid user (Should pass)
//                {"standard_user", "secret"},       // secret_sauce
//
//                // Row 2: A locked-out user (We can test negative scenarios too!)
//                {"locked_out_user", "secret_sauce"}
//        };
//    }

    // THE UPGRADE: We replaced the hard-coded array with our ExcelReader method
    @DataProvider(name = "loginCredentials")
    public Object[][] getLoginData() {
        // We tell it exactly where the file is, and which sheet to read from
        return ExcelReader.getTestData("TestData.xlsx", "Sheet1");
    }




    // 2. THE TEST: We link the test to the Data Provider using its name
    @Test(dataProvider = "loginCredentials")
    public void testLoginWithMultipleUsers(String username, String password) {

        LoginPage loginPage = new LoginPage(driver);

        // Notice we are no longer hard-coding "standard_user".
        // We pass the variables from the Data Provider directly into the methods!
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        // A simple check: If the URL changes to the inventory page, we successfully logged in
        String currentUrl = driver.getCurrentUrl();

        if (username.equals("standard_user")) {
            Assert.assertTrue(currentUrl.contains("inventory.html"), "Error: Standard user failed to log in!");
        } else if (username.equals("locked_out_user")) {
            Assert.assertFalse(currentUrl.contains("inventory.html"), "Error: Locked out user bypassed security!");
        }
    }
}


