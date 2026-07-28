package com.azhar.tests; // Tells the computer this file is inside the 'tests' folder

import com.azhar.base.BaseTest; // Brings in our browser engine
import com.azhar.pages.InventoryPage; // Brings in the inventory page blueprint we just made
import com.azhar.pages.LoginPage; // Brings in the login page blueprint
import org.testng.Assert; // Brings in our checking tool
import org.testng.annotations.Test; // Brings in the TestNG sticky note for "This is a test case"

// We use 'extends BaseTest' so this file automatically opens and closes the Chrome browser
public class InventoryTest extends BaseTest {

    @Test // Tells TestNG to run this exact block of code as an official test
    public void testAddProductToCart() {

        // 1. THE PRE-CONDITION: We must log in first! You cannot add products if you aren't logged in.
        LoginPage loginPage = new LoginPage(driver); // Connect to the login page blueprint
        loginPage.enterUsername("standard_user"); // Type the username
        loginPage.enterPassword("secret_sauce"); // Type the password
        loginPage.clickLogin(); // Click the login button

        // 2. CONNECT TO INVENTORY: Now that we are logged in, we connect to the Inventory blueprint
        InventoryPage inventoryPage = new InventoryPage(driver);

        // 3. VERIFICATION: Let's verify we actually made it to the right page by checking the title at the top
        String currentTitle = inventoryPage.getPageTitleText(); // Asks the page to read its title text

        // Assert.assertEquals checks if two things match perfectly.
        // We expect the title to be exactly "Products". If it is not, the test fails and prints our error message.
        Assert.assertEquals(currentTitle, "Products", "The page title does not match! We might not be logged in.");

        // 4. THE ACTION: Finally, we click the button to add the backpack to the cart
        inventoryPage.addBackpackToCart();

        // Note: The browser moves so fast you might barely see the click happen before it closes!
    } // Ends the test case method
} // Ends the InventoryTest class
