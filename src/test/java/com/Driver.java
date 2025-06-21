package com;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;

public class Driver {

    protected static WebDriver driver;

    protected void getDriver(){
        // Disable password-manager popups
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);

        ChromeOptions opts = new ChromeOptions().setExperimentalOption("prefs", prefs);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(opts);
        driver.manage().window().maximize();
    }


}
