package steps;

import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;
import org.testng.Assert;
import pages.ProductsPage;
import pages.CartPage;
import base.BaseTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartStepDefinitions extends BaseTest {

    private List<String> expectedAddedProducts;
    private ProductsPage productsPage;
    private CartPage cartPage;

    @Given("I am on the products page")
    public void i_am_on_the_products_page() {
        Assert.assertTrue(driver().getCurrentUrl().contains("inventory"));
    }

    @When("I add {string} to the cart")
    public void i_add_to_the_cart(String productName) {
        productsPage = new ProductsPage(driver());
        productsPage.addProductToCart(productName);
    }

    @When("I add the following products to cart:")
    public void i_add_the_following_products_to_cart(DataTable dataTable) {
        productsPage = new ProductsPage(driver());
        expectedAddedProducts = new ArrayList<>(dataTable.asList(String.class));
        for (String product : expectedAddedProducts) {
            productsPage.addProductToCart(product);
        }
    }

    @Then("the cart badge should show {string}")
    public void the_cart_badge_should_show(String expectedCount) {
        Assert.assertEquals(String.valueOf(productsPage.getCartItemCount()), expectedCount);
    }

    @When("I go to the cart page")
    public void i_go_to_the_cart_page() {

        cartPage = productsPage.goToCart();
    }

    @Then("I should see {string} in the cart")
    public void i_should_see_in_the_cart(String productName) {
        Assert.assertTrue(cartPage.isProductInCart(productName));
    }

    @Then("I should see all added products in the cart")
    public void i_should_see_all_added_products_in_the_cart() {
        cartPage = new CartPage(driver());

        Assert.assertNotNull(expectedAddedProducts, "Error: La lista de productos esperados está nula. ¿Se ejecutó el paso 'When' correctamente antes de este 'Then'?");
        Assert.assertFalse(expectedAddedProducts.isEmpty(), "Error: La lista de productos esperados está vacía, no hay nada que verificar.");

        List<String> actualProductsInCart = cartPage.getProductsInCartNames();
        List<String> mutableActualProductsInCart = new ArrayList<>(actualProductsInCart);

        Assert.assertEquals(mutableActualProductsInCart.size(), expectedAddedProducts.size(),
                "El número de productos en el carrito (" + mutableActualProductsInCart.size() + ") NO coincide con los productos añadidos esperados (" + expectedAddedProducts.size() + ").");

        Collections.sort(expectedAddedProducts);
        Collections.sort(mutableActualProductsInCart);

        Assert.assertEquals(mutableActualProductsInCart, expectedAddedProducts,
                "Los nombres de los productos listados en el carrito NO coinciden con los productos añadidos esperados.");

        System.out.println("Verificación exitosa: Todos los productos añadidos están presentes y correctos en el carrito.");
    }
}