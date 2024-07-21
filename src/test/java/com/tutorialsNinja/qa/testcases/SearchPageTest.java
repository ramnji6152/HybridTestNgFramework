package com.tutorialsNinja.qa.testcases;

import com.tutorialsNinja.qa.baseclass.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchPageTest extends Base {

    public WebDriver driver;
    @BeforeMethod
    public void setUp(){
        driver = intializeBrowser("chrome");
    }
    @Test(priority = 1)
    public void searchWithValidProduct(){
        driver.findElement(By.xpath("//input[@name='search']")).sendKeys("hp");
        driver.findElement(By.xpath("//span[@class='input-group-btn']")).click();
        String actualSearchElement = driver.findElement(By.xpath("//a[text()='HP LP3065']")).getText();
        Assert.assertEquals(actualSearchElement,"HP LP3065","element is not displayed");


    }
    @Test(priority = 2)
    public void searchWithInValidProduct(){
        driver.findElement(By.xpath("//input[@name='search']")).sendKeys("2");
        driver.findElement(By.xpath("//span[@class='input-group-btn']")).click();
        String warningMessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();

        Assert.assertEquals(warningMessage,"There is no product that matches the search criteria.","message is wrong");


    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
