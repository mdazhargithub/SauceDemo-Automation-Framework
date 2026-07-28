package com.azhar.tests;

import com.azhar.base.BaseTest;
import com.azhar.pages.CartPage;
import com.azhar.pages.InventoryPage;
import com.azhar.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test
    public void testProductIsAddedToCart() {

        // 1. Log in (Our gateway)
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        // 2. Add the product to the cart from the Inventory room
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addBackpackToCart();

        // 3. Now, we use our new CartPage blueprint to open the cart
        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();

        // 4. Read the name of the item sitting inside the cart
        String actualItemName = cartPage.getAddedProductName();

        // 5. THE VERIFICATION: Prove the item is exactly what we expect
        Assert.assertEquals(actualItemName, "Sauce Labs Backpack", "Error: The wrong item is in the cart!");
    }
}