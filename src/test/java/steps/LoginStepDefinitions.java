package steps;
import io.cucumber.java.en.*;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.ProductsPage;
import base.BaseTest;

public class LoginStepDefinitions {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;

    @Given("I am on the SauceDemo login page")
    public void i_am_on_the_sauce_demo_login_page() {
        driver = BaseTest.getDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @When("I enter username {string} and password {string}")
    public void i_enter_username_and_password(String username, String password) {
        if (loginPage == null) {
            i_am_on_the_sauce_demo_login_page();
        }
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("I click the login button")
    public void i_click_the_login_button() {
        if (loginPage == null) {
            i_am_on_the_sauce_demo_login_page();
        }
        productsPage = loginPage.clickLoginButton();
    }

    @Then("I should be redirected to the products page")
    public void i_should_be_redirected_to_the_products_page() {
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
                "Expected to be on inventory page but was: " + driver.getCurrentUrl());
    }

    @Then("I should see the products inventory")
    public void i_should_see_the_products_inventory() {
        if (productsPage == null) {
            productsPage = new ProductsPage(driver);
        }
        Assert.assertTrue(productsPage.isProductsPageDisplayed(),
                "Products page is not displayed");
    }

    @Then("I should see an error message {string}")
    public void i_should_see_an_error_message(String expectedMessage) {
        if (loginPage == null) {
            i_am_on_the_sauce_demo_login_page();
        }
        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error message is not displayed");
        String actualMessage = loginPage.getErrorMessage();
        Assert.assertEquals(actualMessage, expectedMessage,
                "Error message mismatch. Expected: " + expectedMessage +
                        ", Actual: " + actualMessage);
    }

    @Given("I am logged in as {string} with password {string}")
    public void i_am_logged_in_as_with_password(String username, String password) {
        driver = BaseTest.getDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        productsPage = loginPage.login(username, password);
    }

    @Given("I login with username {string} and password {string}")
    public void i_login_with_username_and_password(String username, String password) {
        i_am_logged_in_as_with_password(username, password);
    }
}