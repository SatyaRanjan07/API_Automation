package api.utilities;


import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    @Override
    public void onStart(ITestContext testContext) {
        // Generate timestamped report name
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";

        // Specify report location
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + repName);

        // Report Configuration
        sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); // Title of the report
        sparkReporter.config().setReportName("Pet Store Users API");            // Name of the report
        sparkReporter.config().setTheme(Theme.DARK);                            // Dark Theme

        // Initialize ExtentReports
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Add environment details
        extent.setSystemInfo("Application", "Pet Store Users API");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User", "Satya Acharya");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.pass("Test Passed ✅");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.fail("Test Failed ❌: " + result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.skip("Test Skipped ⚠️");
    }

    @Override
    public void onFinish(ITestContext testContext) {
        extent.flush();
        System.out.println("✅ Extent Report generated: " + System.getProperty("user.dir") + "/reports/" + repName);
    }
}
