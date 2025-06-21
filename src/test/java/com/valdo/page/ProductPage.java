package com.valdo.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // locator for the little cart badge in the header
    private By cartBadge       = By.cssSelector(".shopping_cart_badge");

    By productTitle = By.xpath("//*[ @id=\"item_4_title_link\"]//div");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void validateOnHomePage() {
        WebElement productElement = driver.findElement(productTitle);
        assertTrue(productElement.isDisplayed());
        assertEquals("Sauce Labs Backpack", productElement.getText());
    }

    public void clickToCart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge));
    }

    public void cartList(){
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
    }

}
