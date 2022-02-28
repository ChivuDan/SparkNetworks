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
import static org.openqa.selenium.Keys.ENTER;


public class GoogleListing {
    //We should add @Test annotation that JUnit will run below method
    @Test
    public void getCatImages() {
        SetProperties.setWebDriver();
        //Step 1- Driver Instantiation: Instantiate driver object as ChromeDriver
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //Step 2- Navigation: Open a website
        driver.navigate().to("https://www.google.com/");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("L2AGLb"))));
        driver.findElement(By.id("L2AGLb")).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@name='q']"))));
        driver.findElement((By.xpath("//*[@name='q']"))).sendKeys("funny cat memes", ENTER);
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
    //Start to write our test method.
    public void verifyGoogleSearchResultPosition(){
        //Step 0- Verify system properties
        SetProperties.setWebDriver();
        //Step 1- Driver Instantiation: Instantiate driver object as ChromeDriver
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //Step 2- Navigation: Open google
        driver.navigate().to("https://www.google.com/");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("L2AGLb"))));
        driver.findElement(By.id("L2AGLb")).click();
        wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@name='q']"))));
        driver.findElement(By.xpath("//*[@name='q']")).sendKeys("Spark Networks" + Keys.ENTER);
            /*Step 3- Assertion: Check the position of the element in the DOM, if it doesn't return true,
           either the element changed name, or it is in another position*/
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("// * [@id='rso']/ * [1]//*[contains(text(), 'Spark Networks SE')]"))));
        assertEquals("Spark Networks SE | A global leading dating company", driver.findElement(By.xpath("// * [@id='rso']/ * [1]//*[contains(text(), 'Spark Networks SE')]")).getText());
        //Step 4- Close Driver
        driver.close();
        //Step 5- Quit Driver
        driver.quit();

    }


}
