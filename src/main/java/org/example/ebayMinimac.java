package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ebayMinimac {

    WebDriver driver = new ChromeDriver();

    @BeforeMethod
    public void Setup() {
        System.setProperty("webdriver.chrome.driver", "/home/baishali/Downloads/chromedriver-linux64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        options.addArguments("--disable-notifications");
        driver.manage().window().maximize();

    }

    @Test
    public void ebayHomePage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.ebay.com/b/Desktops-All-In-One-Computers/171957/bn_1643067");

        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='gh-tb ui-autocomplete-input']")));
        searchBox.sendKeys("macmini");
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='gh-btn']")));
        searchButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[contains(@class, 'srp-results srp-list clearfix')]")));
        List<WebElement> items = driver.findElements(By.xpath("//li[contains(@class, 's-item')]"));
        double minPrice = Double.MAX_VALUE;

        for (int i = 2; i < items.size(); i++) {
            String title = items.get(i).findElement(By.xpath(".//div[@class='s-item__title']")).getText();
            //System.out.println(title);
           String price = items.get(i).findElement(By.xpath(".//span[@class='s-item__price']")).getText();
           System.out.println("Title: " + title + ", Price: " + price);

        }

    }
    @AfterMethod
    public void Close(){
        driver.quit();
    }
}
