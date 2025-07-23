package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class CheckoutPage extends BasePage {

    // Elementos del formulario de información personal
    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    // Elementos de la página de resumen
    @FindBy(css = ".cart_item")
    private List<WebElement> cartItems;

    @FindBy(css = ".inventory_item_name")
    private List<WebElement> itemNames;

    @FindBy(css = ".inventory_item_price")
    private List<WebElement> itemPrices;

    @FindBy(css = ".summary_subtotal_label")
    private WebElement subtotalLabel;

    @FindBy(css = ".summary_tax_label")
    private WebElement taxLabel;

    @FindBy(css = ".summary_total_label")
    private WebElement totalLabel;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(css = ".btn_secondary")
    private WebElement cancelButtonOverview;

    // Elementos de la página de confirmación
    @FindBy(css = ".complete-header")
    private WebElement completeHeader;

    @FindBy(css = ".complete-text")
    private WebElement completeText;

    @FindBy(id = "back-to-products")
    private WebElement backToProductsButton;

    @FindBy(css = ".pony_express")
    private WebElement ponyExpressImage;

    // Elementos de error
    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    @FindBy(css = ".error-button")
    private WebElement errorCloseButton;

    // Constructor
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }


    public void fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        waitForElement(firstNameField);
        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        lastNameField.clear();
        lastNameField.sendKeys(lastName);

        postalCodeField.clear();
        postalCodeField.sendKeys(postalCode);
    }

    public void clickContinue() {
        clickElement(continueButton);
    }

    public void fillAndContinue(String firstName, String lastName, String postalCode) {
        fillCheckoutInformation(firstName, lastName, postalCode);
        clickContinue();
    }

    public void clickCancelInformation() {
        clickElement(cancelButton);
    }

    // Métodos para validación de campos requeridos
    public boolean isFirstNameFieldDisplayed() {
        return firstNameField.isDisplayed();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        if (isErrorMessageDisplayed()) {
            return errorMessage.getText();
        }
        return "";
    }

    public void closeErrorMessage() {
        if (isErrorMessageDisplayed()) {
            clickElement(errorCloseButton);
        }
    }

    // Métodos para la página de resumen (Step 2)
    public int getCartItemsCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(cartItems));
        return cartItems.size();
    }

    public List<String> getItemNames() {
        wait.until(ExpectedConditions.visibilityOfAllElements(itemNames));
        return itemNames.stream()
                .map(WebElement::getText)
                .toList();
    }

    public List<String> getItemPrices() {
        wait.until(ExpectedConditions.visibilityOfAllElements(itemPrices));
        return itemPrices.stream()
                .map(WebElement::getText)
                .toList();
    }

    public String getSubtotal() {
        waitForElement(subtotalLabel);
        return subtotalLabel.getText();
    }

    public String getTax() {
        waitForElement(taxLabel);
        return taxLabel.getText();
    }

    public String getTotal() {
        waitForElement(totalLabel);
        return totalLabel.getText();
    }

    public double getSubtotalAmount() {
        String subtotalText = getSubtotal();
        // Extraer el número del texto "Item total: $29.99"
        return Double.parseDouble(subtotalText.replaceAll("[^0-9.]", ""));
    }

    public double getTaxAmount() {
        String taxText = getTax();
        // Extraer el número del texto "Tax: $2.40"
        return Double.parseDouble(taxText.replaceAll("[^0-9.]", ""));
    }

    public double getTotalAmount() {
        String totalText = getTotal();
        // Extraer el número del texto "Total: $32.39"
        return Double.parseDouble(totalText.replaceAll("[^0-9.]", ""));
    }

    public void clickFinish() {
        clickElement(finishButton);
    }

    public void clickCancelOverview() {
        clickElement(cancelButtonOverview);
    }

    // Métodos para la página de confirmación (Step 3)
    public boolean isOrderComplete() {
        try {
            waitForElement(completeHeader);
            return completeHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCompleteHeader() {
        if (isOrderComplete()) {
            return completeHeader.getText();
        }
        return "";
    }

    public String getCompleteText() {
        waitForElement(completeText);
        return completeText.getText();
    }

    public boolean isPonyExpressImageDisplayed() {
        try {
            return ponyExpressImage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public ProductsPage clickBackToProducts() {
        clickElement(backToProductsButton);
        return new ProductsPage(driver);
    }

    // Método completo para finalizar la compra
    public void finishPurchase() {
        clickFinish();
    }

    // Método para completar todo el proceso de checkout
    public boolean completeCheckoutProcess(String firstName, String lastName, String postalCode) {
        try {
            // Step 1: Llenar información personal
            fillAndContinue(firstName, lastName, postalCode);

            // Step 2: Revisar y finalizar
            clickFinish();

            // Step 3: Verificar que se completó
            return isOrderComplete();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentStepTitle() {
        try {
            WebElement titleElement = driver.findElement(org.openqa.selenium.By.cssSelector(".title"));
            return titleElement.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isOnCheckoutInformationPage() {
        return getCurrentStepTitle().equals("Checkout: Your Information");
    }

    public boolean isOnCheckoutOverviewPage() {
        return getCurrentStepTitle().equals("Checkout: Overview");
    }

    public boolean isOnCheckoutCompletePage() {
        return getCurrentStepTitle().equals("Checkout: Complete!");
    }
}
