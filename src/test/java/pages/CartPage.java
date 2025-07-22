package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    @FindBy(css = ".cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(css = ".cart_button") // Estos son los botones de "Remove" en el carrito
    private List<WebElement> removeButtons;


    public CartPage(WebDriver driver){
        super(driver);
    }

    public boolean isProductInCart(String productName) {

        List<WebElement> currentCartItems = driver.findElements(By.cssSelector(".cart_item"));
        if (currentCartItems.isEmpty()) {
            return false; // No hay ítems en el carrito
        }

        // Espera a que los elementos del carrito estén visibles para asegurar que la lista esté cargada
        wait.until(ExpectedConditions.visibilityOfAllElements(currentCartItems));

        for (WebElement item : currentCartItems) {
            try {
                WebElement itemNameElement = item.findElement(By.cssSelector(".inventory_item_name"));
                if (itemNameElement.getText().trim().equals(productName)) {
                    return true;
                }
            } catch (org.openqa.selenium.NoSuchElementException e) {
                System.err.println("Advertencia: Un elemento de carrito no contiene el nombre del producto esperado.");
            }
        }
        return false;
    }


    public List<String> getProductsInCartNames() {
        List<String> productNames = new ArrayList<>();
        WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            localWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart_item")));
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("No se encontraron elementos en el carrito dentro del tiempo esperado. Retornando lista vacía.");
            return productNames;
        }


        List<WebElement> currentCartItems = driver.findElements(By.cssSelector(".cart_item"));

        for (WebElement item : currentCartItems) {
            try {
                WebElement nameElement = localWait.until(ExpectedConditions.visibilityOf(item.findElement(By.cssSelector(".inventory_item_name"))));
                productNames.add(nameElement.getText().trim());
            } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.TimeoutException e) {
                System.err.println("Advertencia: No se pudo obtener el nombre de un producto en el carrito. Error: " + e.getMessage());
            }
        }
        return productNames;
    }
}