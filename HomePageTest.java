package tests;

import org.testng.annotations.Test;
import pages.HomePage;
import com.aventstack.extentreports.Status;

import base.BaseTest;

import org.testng.Assert;

public class HomePageTest extends BaseTest {
    
    @Test(priority = 1, description = "Verify search functionality")
    public void testSearchProduct() {
        test = extent.createTest("Search Product Test", "Test product search on homepage");
        HomePage homePage = new HomePage(driver, test);
        
        homePage.searchForProduct("Paracetamol");
        Assert.assertTrue(driver.getTitle().contains("Search Results"));
        test.log(Status.PASS, "Product search successful");
    }

    @Test(priority = 2, description = "Verify category navigation")
    public void testNavigateToMedicines() {
        test = extent.createTest("Category Navigation Test", "Test navigation to Medicines category");
        HomePage homePage = new HomePage(driver, test);
        
        homePage.navigateToMedicinesCategory();
        Assert.assertTrue(driver.getCurrentUrl().contains("/medicines"));
        test.log(Status.PASS, "Navigation to Medicines category successful");
    }
}