package tests;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductListingPage;
import com.aventstack.extentreports.Status;

import base.BaseTest;

import org.testng.Assert;

public class ProductSearchTest extends BaseTest {
    
    @Test(priority = 1, description = "Test product filtering by price")
    public void testPriceFilter() {
        test = extent.createTest("Price Filter Test", "Test filtering products by price range");
        HomePage homePage = new HomePage(driver, test);
        ProductListingPage plp = new ProductListingPage(driver, test);
        
        homePage.navigateToHealthcareProducts();
        plp.filterByPriceRange(100, 500);
        Assert.assertTrue(plp.areProductsInPriceRange(100, 500));
        test.log(Status.PASS, "Price filter applied successfully");
    }

    @Test(priority = 2, description = "Test product sorting")
    public void testSortProducts() {
        test = extent.createTest("Product Sorting Test", "Test sorting products by price (Low to High)");
        ProductListingPage plp = new ProductListingPage(driver, test);
        
        plp.sortProductsBy("Price: Low to High");
        Assert.assertTrue(plp.areProductsSortedByPrice("asc"));
        test.log(Status.PASS, "Products sorted by price (Low to High)");
    }
}