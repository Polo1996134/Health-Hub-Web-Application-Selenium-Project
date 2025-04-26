package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.aventstack.extentreports.ExtentTest;

public class HomePage extends BasePage {
    
    @FindBy(id = "search-bar")
    private WebElement searchBar;
    
    @FindBy(css = ".search-button")
    private WebElement searchButton;
    
    @FindBy(linkText = "Medicines")
    private WebElement medicinesCategory;
    
    @FindBy(linkText = "Healthcare Products")
    private WebElement healthcareProductsCategory;

    public HomePage(WebDriver driver, ExtentTest test) {
        super(driver, test);
    }

    public void searchForProduct(String productName) {
        log("Searching for product: " + productName);
        searchBar.clear();
        searchBar.sendKeys(productName);
        searchButton.click();
    }

    public void navigateToMedicinesCategory() {
        log("Navigating to Medicines category");
        medicinesCategory.click();
    }

    public void navigateToHealthcareProducts() {
        log("Navigating to Healthcare Products");
        healthcareProductsCategory.click();
    }
}