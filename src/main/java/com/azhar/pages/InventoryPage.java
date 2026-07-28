package com.azhar.pages; // Tells the computer this file is inside the 'pages' folder

import org.openqa.selenium.By; // Brings in the tool to find elements on the page
import org.openqa.selenium.WebDriver; // Brings in the browser controller

public class InventoryPage { // Creates our blueprint for the Inventory (Products) Page

    // A variable to hold the browser so this page can use it
    private final WebDriver driver;

    // 1. LOCATORS: Finding the exact address of elements on the Inventory page
    // We are finding the main title at the top of the page that says "Products"
    private final By pageTitle = By.cssSelector("span.title");

    // We are finding the specific "Add to cart" button for the Backpack item
    private final By addToCartBackpack = By.id("add-to-cart-sauce-labs-backpack");

    // 2. CONSTRUCTOR: The setup method that connects this page to our active browser
    public InventoryPage(WebDriver driver) {
        this.driver = driver; // Connects our active browser session to this specific page
    }

    // 3. ACTIONS: What a human would do on this page
    public String getPageTitleText() { // A method to read the text of the page title
        // Tells the browser: Find the title element, read the text on it, and hand that text back to us
        return driver.findElement(pageTitle).getText();
    }

    public void addBackpackToCart() { // A method to click the add to cart button
        // Tells the browser: Find the Backpack add-to-cart button, and click it
        driver.findElement(addToCartBackpack).click();
    }
}
