package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportManager implements ITestListener {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // Initialize the ExtentReports instance
    public static ExtentReports getReporter() {
        if (extent == null) {
            // Create an instance of ExtentSparkReporter with a proper file path
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/target/extent-report.html");

            // Configure the Spark Reporter
            sparkReporter.config().setDocumentTitle("Extent Report");
            sparkReporter.config().setReportName("Automation Test Report");
            sparkReporter.config().setTheme(Theme.STANDARD);

            // Create an instance of ExtentReports
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Set system information
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("Tester", "Your Name");

            // Set up additional configurations if needed
        }
        return extent;
    }

    // Get the current test instance
    public static ExtentTest getTest() {
        return test.get();
    }

    // Set the current test instance
    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    // Flush the report to the output file
    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = getReporter().createTest(result.getMethod().getMethodName());
        setTest(extentTest);
        getTest().info("Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        getTest().pass("Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        getTest().fail(result.getMethod().getMethodName() + " failed with exception: " + result.getThrowable().getMessage());
        if (result.getThrowable() != null) {
            getTest().fail("Exception: " + result.getThrowable().toString());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        getTest().skip(result.getMethod().getMethodName() + " skipped due to: " + result.getThrowable().getMessage());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Handle this scenario if necessary. For example:
        getTest().warning(result.getMethod().getMethodName() + " failed but is within the success percentage.");
    }

    @Override
    public void onStart(ITestContext context) {
        // Called before any test starts
        // You can add additional setup if needed here
    }

    @Override
    public void onFinish(ITestContext context) {
        flush(); // Ensure that all tests are flushed to the report at the end of the test suite
    }

    // Add a method to capture screenshots in reports
    public static void addScreenshot(String screenshotPath) {
        try {
            getTest().info("Screenshot: ", com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            getTest().fail("Failed to add screenshot with exception: " + e.getMessage());
        }
    }

    // Add a method to add custom logs to the report
    public static void log(String message) {
        getTest().info(message);
    }

    // Add a method to add custom test steps to the report
    public static void logStep(String step, boolean isSuccess) {
        if (isSuccess) {
            getTest().pass(step);
        } else {
            getTest().fail(step);
        }
    }
}