package net.spark.test;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.openqa.selenium.Keys.ENTER;


public class GoogleListing {

    @Test
    public void getCatImages() {
        //Step 0- Verify system properties
        WebDriver driver = initWebDriver();
        WebDriverWait wait = initWebDriverWait(driver, 30);
        searchGoogle("funny cat memes", driver, wait);
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(source, new File("./SparkScreenshots/Screen.png"));
        } catch (IOException e) {
            e.printStackTrace();
            fail("Screenshot could not be taken");
        }
        System.out.println("Screenshot is captured");
        closeChromeDriver(driver);
    }

    @Test
    public void verifyGoogleSearchResultPosition() {
        //Step 0- Verify system properties
        String query = "Spark Networks";
        //Step 1- Driver Instantiation: Instantiate driver object as ChromeDriver
        WebDriver driver = initWebDriver();
        WebDriverWait wait = initWebDriverWait(driver, 30);
        //Step 2- Navigation: Open google
        searchGoogle(query, driver, wait);
        /*Step 3- Assertion: Check the position of the element in the DOM, if it doesn't return true, either the element changed name, or it is in another position*/
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@id='rso']/*[1]//*[contains(text(), 'Spark Networks SE')]"))));
        assertEquals(query, driver.findElement(By.xpath("//*[@id='rso']/*[1]//*[contains(text(), 'Spark Networks SE')]")).getText());
        //Step 4- Close Driver
        closeChromeDriver(driver);
    }

    //Step 0- Driver Instantiation: Instantiate driver object as ChromeDriver and WebDriverWait
    private void searchGoogle(String query, WebDriver driver, WebDriverWait wait) {
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

    private void closeChromeDriver(WebDriver driver) {
        // Close and quit Driver
        driver.close();
        driver.quit();
    }

    private WebDriver initWebDriver() {
        SetProperties.setWebDriver();
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    private WebDriverWait initWebDriverWait(WebDriver driver, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        return wait;
    }
}
