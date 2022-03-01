package net.spark.test;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.openqa.selenium.Keys.ENTER;


public class GoogleListing {

    //Step 0- Driver Instantiation: Instantiate driver object as ChromeDriver and WebDriverWait
    private void searchGoogle(String query, WebDriver driver, WebDriverWait wait){
        //Step 1- Verify system properties
        SetProperties.setWebDriver();
        //Step 2- Navigation: Open google
        driver.navigate().to("https://www.google.com/");
        //Step 3- Click the search bar
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("L2AGLb"))));
        driver.findElement(By.id("L2AGLb")).click();
        //Step 4- send the query
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@name='q']"))));
        driver.findElement(By.xpath("//*[@name='q']")).sendKeys(query + Keys.ENTER);
    }

    @Test
    public void getCatImages() {
        //Step 0- Verify system properties
        System.setProperty("webdriver.chrome.driver", System.getenv("webdriver.chrome.driver"));
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        searchGoogle("funny cat memes",driver,wait);
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(source, new File("./SparkScreenshots/Screen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Screenshot is captured");
        driver.close();
        driver.quit();
    }

    @Test
    public void verifyGoogleSearchResultPosition(){
        //Step 0- Verify system properties
        SetProperties.setWebDriver();
        //Step 1- Driver Instantiation: Instantiate driver object as ChromeDriver
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //Step 2- Navigation: Open google
        searchGoogle("Spark Networks",driver,wait);
            /*Step 3- Assertion: Check the position of the element in the DOM, if it doesn't return true,
           either the element changed name, or it is in another position*/
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id='rso']/*[1]//*[contains(text(), 'Spark Networks SE')]"))));
       // assertNotNull(driver.findElement(By.xpath("//*[@id='rso']/*[1]//*[contains(text(), 'Spark Networks SE')]")));
       assertEquals("Spark Networks SE | A global leading dating company", driver.findElement(By.xpath("//*[@id='rso']/*[1]//*[contains(text(), 'Spark Networks SE')]")).getText());        //Step 4- Close Driver
        driver.close();
        //Step 5- Quit Driver
        driver.quit();

    }


}
