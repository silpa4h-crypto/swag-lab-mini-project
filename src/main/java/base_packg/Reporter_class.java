

	package base_packg;

	import java.io.IOException;
	import org.openqa.selenium.io.FileHandler;

	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import java.io.File;
	import org.testng.ITestResult;
	import org.testng.annotations.AfterMethod;

	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.Status;
	import com.aventstack.extentreports.reporter.ExtentSparkReporter;
	import com.aventstack.extentreports.reporter.configuration.Theme;

	public class Reporter_class {
		    
		    public static WebDriver driver;
		    public static ExtentSparkReporter reporter;
		    public static ExtentReports extent;
		    public static ExtentTest test;

		    public static ExtentReports sam() {
		        reporter = new ExtentSparkReporter(".\\Reports\\swaglabreport.html");
		        
		        reporter.config().setDocumentTitle("Automation report");
		        reporter.config().setReportName("Functional test");
		        reporter.config().setTheme(Theme.DARK);

		        extent = new ExtentReports();
		        extent.attachReporter(reporter);

		        extent.setSystemInfo("hostname", "localhost");
		        extent.setSystemInfo("os", "windows11");
		        extent.setSystemInfo("tester name", "Silpa");
		        extent.setSystemInfo("browsername", "chrome");

		        return extent;
		    }

		    @AfterMethod
		    public void browserclose(ITestResult result) throws IOException {
		        if (result.getStatus() == ITestResult.FAILURE) {
		            test.log(Status.FAIL, "test case failed is " + result.getName());
		            test.log(Status.FAIL, "test case failed is " + result.getThrowable());
		        }
		        else if (result.getStatus() == ITestResult.SKIP) {
		            test.log(Status.SKIP, "test case skipped is " + result.getName());
		        }
		    }

		    public static String screenshotmethodString(WebDriver driver, String screenshotname) throws IOException {
		        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		        
		        File folder = new File("./screenshot");
		        if (!folder.exists()) {
		            folder.mkdirs();
		        }
		        
		        String destination = "./screenshot/" + screenshotname + ".png";
		        FileHandler.copy(src, new File(destination));
		        
		        return destination;
		    }

			
		    }


