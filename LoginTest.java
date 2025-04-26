package tests;

import org.testng.annotations.Test;
import pages.LoginPage;
import com.aventstack.extentreports.Status;

import base.BaseTest;

import org.testng.Assert;

public class LoginTest extends BaseTest {
    
    @Test(priority = 1, description = "Valid login test")
    public void testValidLogin() {
        test = extent.createTest("Valid Login Test", "Test login with valid credentials");
        LoginPage loginPage = new LoginPage(driver, test);
        
        loginPage.login("user@example.com", "correctPassword123");
        Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard"));
        test.log(Status.PASS, "Login successful with valid credentials");
    }

    @Test(priority = 2, description = "Invalid login test")
    public void testInvalidLogin() {
        test = extent.createTest("Invalid Login Test", "Test login with invalid credentials");
        LoginPage loginPage = new LoginPage(driver, test);
        
        loginPage.login("invalid@example.com", "wrongPassword");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        test.log(Status.PASS, "Error message displayed for invalid login");
    }
}