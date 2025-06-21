package com.valdo.stepdef;

import com.Driver;
import com.valdo.page.LoginPage;
import com.valdo.page.ProductPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;


public class ProductStepDef extends Driver {
    LoginPage loginPage;
    ProductPage productpage;

    // Positive Product test steps
    @Given("the user is on the Products page")
    public void userIsOnProductsPage(){
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        new Actions(driver).pause(Duration.ofSeconds(2)).perform();
        loginPage.clickLoginButton();

        productpage = new ProductPage(driver);
        productpage.validateOnHomePage();
    }

    @When("the user clicks the Add to cart button for a product")
    public void userClicksAddToCart(){
        new Actions(driver).pause(Duration.ofSeconds(2)).perform();
        productpage.clickToCart();
        new Actions(driver).pause(Duration.ofSeconds(2)).perform();
    }

    @Then("the product should appear in the cart")
    public void productShouldAppearInCart(){
        productpage.cartList();
        new Actions(driver).pause(Duration.ofSeconds(2)).perform();
    }
}
