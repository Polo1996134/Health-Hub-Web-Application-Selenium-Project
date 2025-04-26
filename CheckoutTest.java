package tests;

import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import com.aventstack.extentreports.Status;

import base.BaseTest;

import org.testng.Assert;

public class CheckoutTest extends BaseTest {
    
    @Test(priority = 1, description = "Test checkout process")
    public void testCheckoutProcess() {
        test = extent.createTest("Checkout Test", "Test complete checkout flow");
        CartPage cartPage = new CartPage(driver, test);
        CheckoutPage checkoutPage = new CheckoutPage(driver, test);
        
        cartPage.proceedToCheckout();
        checkoutPage.enterShippingAddress("123 Health St, Medical City");
        checkoutPage.selectPaymentMethod("Credit Card");
        checkoutPage.placeOrder();
        Assert.assertTrue(checkoutPage.isOrderConfirmationDisplayed());
        test.log(Status.PASS, "Checkout completed successfully");
    }
}