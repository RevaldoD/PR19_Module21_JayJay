package com.valdo.stepdef;

import com.Driver;
import com.valdo.page.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LoginStepDef extends Driver {

    LoginPage loginPage;
    // Negative Login test steps
    @Given("the user is on the Login page")
    public void userIsOnLoginPage() {

        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
    }

    @When("the user clicks the Login button without fill the form")
    public void userClicksLogin() {
        loginPage = new LoginPage(driver);
        loginPage.clickLoginButton();
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
