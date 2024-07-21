package com.tutorialsNinja.qa.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import experiments.ExtentReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class Listeners implements ITestListener {
    ExtentReports extentReports;
    ExtentTest extentTest;

    @Override
    public void onStart(ITestContext context) {
        extentReports = ExtentReporter.generateExtentReports();
    }
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getName();
        extentTest = extentReports.createTest(testName);
        extentTest.log(Status.INFO, testName+"test cases Started Execution");


    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getName();
        extentTest = extentReports.createTest(testName);
        extentTest.log(Status.PASS, testName+"testcase Passed Successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        WebDriver driver = null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e){
            e.printStackTrace();
        }
        File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String destinationPath = System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
        try {
            FileHandler.copy(screenShot, new File(destinationPath));
        } catch (IOException e){
            e.printStackTrace();
        }
        extentTest.addScreenCaptureFromPath(destinationPath);
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.FAIL, testName+"Test case Failed");




    }



    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}
