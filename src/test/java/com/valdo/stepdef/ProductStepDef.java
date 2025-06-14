package com.valdo.stepdef;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ProductStepDef {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() {
        // Disable password-manager popups
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);

        ChromeOptions opts = new ChromeOptions().setExperimentalOption("prefs", prefs);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(opts);
        driver.manage().window().maximize();
        // implicit wait for element availability
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // timeout for async scripts
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void teardown() {
        if (driver != null) {
            // pause before closing to see final state
            ((JavascriptExecutor) driver).executeAsyncScript(
                    "window.setTimeout(arguments[arguments.length-1], 1500);"
            );
            driver.quit();
        }
    }

    // Positive Product test steps
    @Given("the user is on the Products page")
    public void userIsOnProductsPage() {
        driver.get("https://www.saucedemo.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")))
                .sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        wait.until(ExpectedConditions.urlContains("/inventory.html"));
    }

    @When("the user clicks the Add to cart button for a product")
    public void userClicksAddToCart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
    }

    @Then("the product should appear in the cart")
    public void productShouldAppearInCart() {
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart_item")));
    }

    // Negative Login test steps
    @Given("the user is on the Login page")
    public void userIsOnLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("the user clicks the Login button without fill the form")
    public void userClicksLogin() {
        driver.findElement(By.id("login-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']")));
    }

    @Then("the website give error message {string}")
    public void userAbleToSeeErrorMessage(String errorMessage) {
        String pageSource = driver.getPageSource();
        assertNotNull("Page source should never be null", pageSource);
        assertTrue(
                "Expected error message '" + errorMessage + "' not found in page source",
                pageSource.contains(errorMessage));
    }
}
