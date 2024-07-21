package com.tutorialsNinja.qa.testcases;

import com.tutorialsNinja.qa.baseclass.Base;
import experiments.Demo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class LoginPageTest extends Base {
    public WebDriver driver;
    @BeforeMethod
    public void setUp(){
        driver = intializeBrowser(prop.getProperty("browser"));
    }

    @Test(priority = 1, dataProvider = "validCredentials")
    public void verifyLoginWithValidCredentials(String email, String password){
        driver.findElement(By.xpath("//span[text() = 'My Account']")).click();
        driver.findElement(By.xpath("//a[text() = 'Login']")).click();
        driver.findElement(By.id("input-email")).sendKeys(email);
        driver.findElement(By.id("input-password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());

    }
    @DataProvider(name="validCredentials")
    public Object[][] supplyData(){
        Object[][] data = Demo.getTextDataFromExcel("Login");
        return data;
    }
    @Test(priority = 2)
    public void verifyLoginWithInvalidCredentials(){
        driver.findElement(By.xpath("//span[text() = 'My Account']")).click();
        driver.findElement(By.xpath("//a[text() = 'Login']")).click();
        driver.findElement(By.id("input-email")).sendKeys("chitti9142@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        driver.quit();
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }


   /* public void generateTimestamp(){
        Date date = new Date();
        System.out.println(date.toString().replace(" ","_").replace(":","_"));
    } */
}
