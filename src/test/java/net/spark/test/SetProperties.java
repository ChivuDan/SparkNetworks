package net.spark.test;

public class SetProperties {
    public static void setWebDriver(){
        System.setProperty("webdriver.chrome.driver", System.getenv("webdriver.chrome.driver"));

    }
}
