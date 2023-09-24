package WalmartCa.webutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import WalmartCa.PageObjects.launcherObject;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Baseclass {
	public WebDriver driverr;
	public String broswername;
	public String runOn;
	public String store;
	public String pass;
	public String storeName;

	public WebDriver InitializeBrowser() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//property.properties");
		prop.load(fis);
		broswername = prop.getProperty("browser");
		runOn = prop.getProperty("RunOnNewStore");
		store = prop.getProperty("store");
		pass = prop.getProperty("password");
		storeName = prop.getProperty("storeName");

		if (broswername.equalsIgnoreCase("chrome")) {
//			WebDriverManager.chromedriver().setup();
			this.driverr = new ChromeDriver();
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driverr.manage().window().maximize();

		}
		if (broswername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			this.driverr = new FirefoxDriver();
			driverr.manage().window().maximize();

		}
		Listeners.test.log(Status.INFO, "Browser launched");
		return driverr;
	}

	public static ExtentReports configReport() {
		// ExtentReports and ExtentSparkReporter
		String path = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("This is basic report");
		reporter.config().setReportName("Testone");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Rishabh");
		return extent;
	}
}
