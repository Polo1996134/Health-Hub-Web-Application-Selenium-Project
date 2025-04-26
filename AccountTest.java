package tests;

import org.testng.annotations.Test;
import pages.AccountPage;
import pages.LoginPage;
import com.aventstack.extentreports.Status;

import base.BaseTest;

import org.testng.Assert;

public class AccountTest extends BaseTest {
    
    @Test(priority = 1, description = "Test order history access")
    public void testOrderHistory() {
        test = extent.createTest("Order History Test", "Test viewing order history");
        LoginPage loginPage = new LoginPage(driver, test);
        AccountPage accountPage = new AccountPage(driver, test);
        
        loginPage.login("user@example.com", "correctPassword123");
        accountPage.viewOrderHistory();
        Assert.assertTrue(accountPage.areOrdersListed());
        test.log(Status.PASS, "Order history accessed successfully");
    }
}