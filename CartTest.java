package tests;

import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductListingPage;
import com.aventstack.extentreports.Status;

import base.BaseTest;

import org.testng.Assert;

public class CartTest extends BaseTest {
    
    @Test(priority = 1, description = "Test adding product to cart")
    public void testAddToCart() {
        test = extent.createTest("Add to Cart Test", "Test adding a product to cart");
        ProductListingPage plp = new ProductListingPage(driver, test);
        CartPage cartPage = new CartPage(driver, test);
        
        plp.selectFirstProduct();
        plp.addToCart();
        Assert.assertTrue(cartPage.isItemInCart());
        test.log(Status.PASS, "Product added to cart successfully");
    }

    @Test(priority = 2, description = "Test removing product from cart")
    public void testRemoveFromCart() {
        test = extent.createTest("Remove from Cart Test", "Test removing a product from cart");
        CartPage cartPage = new CartPage(driver, test);
        
        cartPage.removeFirstItem();
        Assert.assertTrue(cartPage.isCartEmpty());
        test.log(Status.PASS, "Product removed from cart successfully");
    }
}