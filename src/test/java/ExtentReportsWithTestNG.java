import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class ExtentReportsWithTestNG {
    ExtentReports extent;
    ExtentSparkReporter extentSparkReporter;
    WebDriver driver;

    @BeforeSuite
    public void setUp(){
        extent = new ExtentReports();
        extentSparkReporter = new ExtentSparkReporter("extentReports.html");
        extent.attachReporter(extentSparkReporter);
    }

    @BeforeTest
    public void setUpTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void test1(){
        ExtentTest test = extent.createTest("GoogleSearchTest1", "This is a simple test to validate a Google Search Functionality");
        driver.get("https://best.aliexpress.com/");
        test.log(Status.INFO, "Starting Test");
        test.pass("Navigated to the web-site");
        test.info("This step shows usage of info(details)");
        test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
        test.addScreenCaptureFromPath("screenshot.png");
    }

    @Test
    public void test2(){
        ExtentTest test = extent.createTest("GoogleSearchTest2", "This is a simple test to validate a Google Search Functionality");
        test.log(Status.INFO, "Starting Test");
        test.info("This step shows usage of info(details)");
        test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
        test.addScreenCaptureFromPath("screenshot.png");
    }

    @AfterTest
    public void tearDownTest(){
        driver.quit();
        System.out.println("Test is completed");
    }

    @AfterSuite
    public void tearDown(){
        extent.flush();
    }
}
