package WalmartCa.PageObjects;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import WalmartCa.webutility.Baseclass;
import WalmartCa.webutility.Listeners;

//import utils.Listeners;

public class launcherObject {
	WebDriver driver;
	Baseclass base;
	WebDriverWait wait;
	public OnboardStep1Object launchApplication() throws InterruptedException, IOException {
		this.base = new Baseclass();
		this.driver = base.InitializeBrowser();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		if (base.runOn.equalsIgnoreCase("true")) {
			Listeners.test.log(Status.INFO, "Store launched");
			runonNewStore(base.store, base.pass, base.storeName);
		} else {
			Listeners.test.log(Status.INFO, "Store launched");
			runonOldStore(base.store, base.pass, base.storeName);
		}
		OnboardStep1Object obj = new OnboardStep1Object(driver);
		return obj;
	}
	
	public void runonNewStore(String store, String pass, String storeName) throws InterruptedException {

		driver.get("https://www.shopify.com/in/partners");
		driver.findElement(By.xpath("(//a[@href='https://partners.shopify.com/organizations'])[1]")).click();
		driver.findElement(By.id("account_email")).sendKeys(store);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("account_password")));
		driver.findElement(By.id("account_password")).sendKeys(pass);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		// wait.until(ExpectedConditions.elementToBeClickable(By.id("(//span[@class='Polaris-Navigation__Text'])[2]")));
		driver.findElement(By.xpath("(//span[@class='Polaris-Navigation__Text'])[2]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PolarisTextField1")));
		driver.findElement(By.id("PolarisTextField1")).sendKeys(storeName);
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[class='dQQabkSmBTfgBjKaboio']")));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[class='dQQabkSmBTfgBjKaboio']")).click();
		Thread.sleep(8000);
		ArrayList<String> n1 = new ArrayList<String>(driver.getWindowHandles());
		System.out.print(n1.size());

		driver.switchTo().window(n1.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@class='jb2yQ'])[2]")));
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.xpath("(//button[@class='jb2yQ'])[2]")).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//div[@class='Polaris-HorizontalStack_dv6q6'])[15]")));
		driver.findElement(By.xpath("(//div[@class='Polaris-HorizontalStack_dv6q6'])[15]")).click();
		driver.findElement(By.xpath("(//div[@class='Polaris-Box_375yx Polaris-Box--printHidden_15ag0'])[1]/a")).click();
		Thread.sleep(3000);
		ArrayList<String> n2 = new ArrayList<String>(driver.getWindowHandles());
		System.out.print(n2.size());
		driver.switchTo().window(n2.get(2));
		driver.findElement(By.xpath("(//div[@class='tw-w-full tw-relative'])[1]/input"))
				.sendKeys("CedCommerce Walmart Canada");
		driver.navigate().to(
				"https://apps.shopify.com/walmart-canada-marketplace-integration?search_id=3b7a9fc9-0568-4018-90a9-394c92f040fb&surface_detail=walmart&surface_inter_position=1&surface_intra_position=8&surface_type=search");
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@class='button_to']/button")));
		driver.findElement(By.xpath("//form[@class='button_to']/button")).click();
		wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='Polaris-Button__Text_yj3uv'])[2]")));
		driver.findElement(By.xpath("(//span[@class='Polaris-Button__Text_yj3uv'])[2]")).click();

	}
	
	public void runonOldStore(String store, String pass, String storeName) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://www.shopify.com/in/partners");
		driver.findElement(By.xpath("(//a[@href='https://partners.shopify.com/organizations'])[1]")).click();
		driver.findElement(By.id("account_email")).sendKeys(store);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("account_password")));
		driver.findElement(By.id("account_password")).sendKeys(pass);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		// wait.until(ExpectedConditions.elementToBeClickable(By.id("(//span[@class='Polaris-Navigation__Text'])[2]")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[@class='Polaris-Navigation__Text'])[2]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PolarisTextField1")));
		driver.findElement(By.id("PolarisTextField1")).sendKeys(storeName);
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[class='dQQabkSmBTfgBjKaboio']")));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[class='dQQabkSmBTfgBjKaboio']")).click();
		Thread.sleep(5000);
		ArrayList<String> n1 = new ArrayList<String>(driver.getWindowHandles());
		System.out.print(n1.size());

		driver.switchTo().window(n1.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@class='jb2yQ'])[2]")));
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//button[@class='jb2yQ'])[2]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='FSndY'])[1]/div")));
		driver.findElement(By.xpath("(//div[@class='FSndY'])[1]/div")).click();
		ArrayList<String> n2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(n2.get(2));
		Thread.sleep(4000);
		String current_url = driver.getCurrentUrl();
		
		String[] array = current_url.split("walmartcanada");
		System.out.print(array[0]);
		String newUrl = array[0]+"walmartcanada/onboard/index?sHopiFy=1";
//		Listeners.test.log(Status.INFO, "Browser Opened "+newUrl);
		driver.navigate().to(newUrl);
	}


}
