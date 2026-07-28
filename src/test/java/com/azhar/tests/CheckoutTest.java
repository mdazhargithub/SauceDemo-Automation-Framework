package com.azhar.tests;

import com.azhar.base.BaseTest;
import com.azhar.pages.CartPage;
import com.azhar.pages.CheckoutPage;
import com.azhar.pages.InventoryPage;
import com.azhar.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test
    public void testCompleteCheckoutProcess() {

        // 1. Gateway: Log in
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        // 2. Inventory: Add the product to the cart
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addBackpackToCart();

        // 3. Cart: Open the cart and click the checkout button
        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();
        cartPage.clickCheckout();

        // 4. Checkout: Fill in the form and complete the order
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterPersonalInformation("John", "Doe", "12345");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        // 5. THE VERIFICATION: Prove the order was completely successful
        String actualMessage = checkoutPage.getSuccessMessage();
        Assert.assertEquals(actualMessage, "Thank you for your order!", "Error: The checkout did not complete successfully!");
    }
}
