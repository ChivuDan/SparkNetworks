package net.spark.www;

public class SetProperties {
    public static void webDriverSet (){
        System.setProperty("webdriver.chrome.driver", System.getenv("webdriver.chrome.driver"));

    }
}
