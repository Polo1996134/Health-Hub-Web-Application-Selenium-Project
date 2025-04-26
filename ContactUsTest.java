package tests;

import org.testng.annotations.Test;
import pages.ContactUsPage;
import com.aventstack.extentreports.Status;

import base.BaseTest;

import org.testng.Assert;

public class ContactUsTest extends BaseTest {
    
    @Test(priority = 1, description = "Test contact form submission")
    public void testContactForm() {
        test = extent.createTest("Contact Form Test", "Test feedback form submission");
        ContactUsPage contactPage = new ContactUsPage(driver, test);
        
        contactPage.submitFeedback("John Doe", "john@example.com", "Great service!");
        Assert.assertTrue(contactPage.isSuccessMessageDisplayed());
        test.log(Status.PASS, "Feedback submitted successfully");
    }
}