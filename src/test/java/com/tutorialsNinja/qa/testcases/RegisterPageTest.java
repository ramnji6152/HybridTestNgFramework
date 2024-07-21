package com.tutorialsNinja.qa.testcases;

import com.tutorialsNinja.qa.baseclass.Base;
import experiments.Demo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterPageTest extends Base {
    public WebDriver driver;
    public RegisterPageTest(){
        super();
    }
    @BeforeMethod
    public void setUp(){
        driver = intializeBrowser(prop.getProperty("browser"));
        driver.findElement(By.xpath("//span[text() = 'My Account']")).click();
        driver.findElement(By.xpath(("//a[text()='Register']"))).click();
    }
    @Test(priority = 1)
    public void registerTheDetails(){
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Ram");
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Juturu");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(Demo.utilities());
        driver.findElement(By.xpath("//input[@name='telephone']")).sendKeys("9182582787");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@name='confirm']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@name='agree']")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Continue']")).isDisplayed());

    }
    @Test(priority=2)
    public void registerWithOutFillingDetails(){
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        String actualWarningAlert = driver.findElement(By.xpath("//div[contains(@class,'alert alert-danger alert-dismissible')]")).getText();
        System.out.println(actualWarningAlert);
        Assert.assertTrue(actualWarningAlert.contains("Warning: You must agree to the Privacy Policy!"),"Alert is not displayed");

        String actualFirstNameAlert = driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
        Assert.assertTrue(actualFirstNameAlert.contains("First Name must be between 1 and 32 characters!"),"Alert id not displayed");

        String actualLastNameAlert = driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
        Assert.assertTrue(actualLastNameAlert.contains("Last Name must be between 1 and 32 characters!"),"Alert is not displayed");

        String actualEmailAlert = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
        Assert.assertTrue(actualEmailAlert.contains("E-Mail Address does not appear to be valid!"),"Alert is not displayed");

        String actualMobileAlert = driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
        Assert.assertTrue(actualMobileAlert.contains("Telephone must be between 3 and 32 characters!"),"Alert is not displayed");

        String actualPasswordAlert = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
        Assert.assertTrue(actualPasswordAlert.contains("Password must be between 4 and 20 characters!"),"Alert is not displayed");


    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
