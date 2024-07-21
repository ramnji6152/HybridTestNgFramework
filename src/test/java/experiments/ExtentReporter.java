package experiments;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentReporter {

    public static ExtentReports generateExtentReports(){
        ExtentReports extentReports = new ExtentReports();
        File file = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReporter\\ExtentReporter.html");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);

        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("Automation Testing");
        sparkReporter.config().setDocumentTitle("Ramanji");
        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

        extentReports.attachReporter(sparkReporter);

        return extentReports;
    }
}
