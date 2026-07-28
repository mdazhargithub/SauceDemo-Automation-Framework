package com.azhar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    private final WebDriver driver;

    // 1. LOCATORS: We need the text boxes for the form, and the continue/finish buttons
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By zipCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");

    private final By finishButton = By.id("finish");
    private final By successMessage = By.className("complete-header");

    // 2. CONSTRUCTOR
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // 3. ACTIONS

    // This method takes all three pieces of information at once to save us time
    public void enterPersonalInformation(String firstName, String lastName, String zipCode) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(zipCodeInput).sendKeys(zipCode);
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    public void clickFinish() {
        driver.findElement(finishButton).click();
    }

    public String getSuccessMessage() {
        // Reads the final "Thank you for your order!" text
        return driver.findElement(successMessage).getText();
    }
}
