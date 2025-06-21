package com.valdo.stepdef;

import com.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends Driver {

    @Before
    public void beforeSetup() {
        getDriver();
    }

    @After
    public void afterSetup() {
        driver.close();
    }

}
