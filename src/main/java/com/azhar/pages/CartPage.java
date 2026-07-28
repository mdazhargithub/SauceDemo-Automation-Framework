//package com.azhar.pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//
//public class CartPage {
//
//    private final WebDriver driver;
//
//    // 1. LOCATORS: Finding the cart icon and the product name inside the cart
//    private final By cartIcon = By.className("shopping_cart_link"); // The cart icon at the top right
//    private final By productNameInCart = By.className("inventory_item_name"); // The text of the product name
//
//    // 2. CONSTRUCTOR: Connects the page to our active browser
//    public CartPage(WebDriver driver) {
//        this.driver = driver;
//    }
//
//    // 3. ACTIONS: What we want to do on this page
//
//    public void openCart() {
//        // Clicks the shopping cart icon at the top right of the screen
//        driver.findElement(cartIcon).click();
//    }
//
//    public String getAddedProductName() {
//        // Reads the name of the product sitting inside the cart and hands it back to us
//        return driver.findElement(productNameInCart).getText();
//    }
//
//    // NEW ACTION: Clicking the Checkout button
//    public void clickCheckout() {
//        driver.findElement(checkoutButton).click();
//    }
//}


package com.azhar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    private final WebDriver driver;

    // 1. LOCATORS: We must define ALL addresses here at the top!
    private final By cartIcon = By.className("shopping_cart_link");
    private final By productNameInCart = By.className("inventory_item_name");

    // --> THIS IS THE LINE THAT WAS MISSING <--
    private final By checkoutButton = By.id("checkout");

    // 2. CONSTRUCTOR
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // 3. ACTIONS
    public void openCart() {
        driver.findElement(cartIcon).click();
    }

    public String getAddedProductName() {
        return driver.findElement(productNameInCart).getText();
    }

    public void clickCheckout() {
        // Now Java knows exactly what checkoutButton means!
        driver.findElement(checkoutButton).click();
    }
}