package net.spark.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SetProperties {
    public static void setWebDriver(){
        System.setProperty("webdriver.chrome.driver", System.getenv("webdriver.chrome.driver"));
    }
}
