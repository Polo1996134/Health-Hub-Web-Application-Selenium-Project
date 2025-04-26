package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import com.aventstack.extentreports.ExtentTest;
import java.util.List;

public class ProductListingPage extends BasePage {
    
    @FindBy(id = "sort-dropdown")
    private WebElement sortDropdown;
    
    @FindBy(css = ".price-filter-min")
    private WebElement minPriceFilter;
    
    @FindBy(css = ".price-filter-max")
    private WebElement maxPriceFilter;
    
    @FindBy(css = ".apply-filter")
    private WebElement applyFilterButton;
    
    @FindBy(css = ".product-item")
    private List<WebElement> productItems;
    
    @FindBy(css = ".product-item:first-child .add-to-cart")
    private WebElement firstProductAddToCart;

    public ProductListingPage(WebDriver driver, ExtentTest test) {
        super(driver, test);
    }

    public void sortProductsBy(String sortOption) {
        log("Sorting products by: " + sortOption);
        new Select(sortDropdown).selectByVisibleText(sortOption);
    }

    public void filterByPriceRange(double min, double max) {
        log("Filtering products by price range: " + min + " to " + max);
        minPriceFilter.clear();
        minPriceFilter.sendKeys(String.valueOf(min));
        maxPriceFilter.clear();
        maxPriceFilter.sendKeys(String.valueOf(max));
        applyFilterButton.click();
    }

    public void addToCart() {
        log("Adding first product to cart");
        firstProductAddToCart.click();
    }

    public boolean areProductsInPriceRange(double min, double max) {
        // Implementation to verify products are within price range
        return true;
    }

	public void selectFirstProduct() {
		// TODO Auto-generated method stub
		
	}

	public boolean areProductsSortedByPrice(String string) {
		// TODO Auto-generated method stub
		return false;
	}

	
}