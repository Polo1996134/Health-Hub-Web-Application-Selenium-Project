package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.aventstack.extentreports.ExtentTest;

public class ProductDetailsPage extends BasePage {
    
    @FindBy(css = ".product-title")
    private WebElement productTitle;
    
    @FindBy(css = ".add-to-cart")
    private WebElement addToCartButton;
    
    @FindBy(css = ".product-price")
    private WebElement productPrice;

    public ProductDetailsPage(WebDriver driver, ExtentTest test) {
        super(driver, test);
    }

    public String getProductTitle() {
        return productTitle.getText();
    }

    public void addProductToCart() {
        log("Adding product to cart from details page");
        addToCartButton.click();
    }

    public double getProductPrice() {
        return Double.parseDouble(productPrice.getText().replace("$", ""));
    }
}