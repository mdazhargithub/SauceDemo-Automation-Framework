package com.azhar.pages; // Tells the computer this file lives inside the 'pages' folder

import org.openqa.selenium.By; // Brings in the tool to find elements (like buttons and text boxes) on the web page
import org.openqa.selenium.WebDriver; // Brings in the browser controller

public class LoginPage { // Creates a blueprint for the Login Page

    // Added 'final' here because the driver for this specific page instance won't change once set
    private final WebDriver driver;

    // 1. LOCATORS: Added 'final' because these HTML addresses never change
    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");

    // 2. CONSTRUCTOR: This is a special setup method that runs the moment we call this page in our test
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // 3. ACTIONS: These are the things a human user would actually DO on the page
    public void enterUsername(String username) {
        driver.findElement(usernameInput).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }
}