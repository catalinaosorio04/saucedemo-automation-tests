package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions; // Necesitas esta importación

import java.util.List;

public class ProductsPage extends BasePage {
    @FindBy(css = ".inventory_item")
    private List<WebElement> products;

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(css = ".title")
    private WebElement productsPageTitle;


    public ProductsPage(WebDriver driver){
        super(driver);
    }

    public boolean isProductsPageDisplayed() {
        try {

            wait.until(ExpectedConditions.visibilityOf(productsPageTitle));
            return productsPageTitle.isDisplayed() && productsPageTitle.getText().equals("Products"); // Ajusta "Products" si el texto del título es diferente
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public void addProductToCart(String productName) {
        WebElement addButton = driver.findElement(
                By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button")
        );
        addButton.click();
    }

    public CartPage goToCart() {
        cartIcon.click();
        return new CartPage(driver);
    }

    public int getCartItemCount() {
        wait.until(ExpectedConditions.visibilityOf(cartBadge));
        return Integer.parseInt(cartBadge.getText());
    }
}