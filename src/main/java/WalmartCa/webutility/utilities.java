package WalmartCa.webutility;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

public class utilities {
	WebDriver driver;
	WebDriverWait wait;

	public utilities(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	public void ElementClick(By element, String message) {
		WaitforElementClickable(element);
		driver.findElement(element).click();
		Listeners.test.log(Status.INFO, message);
	}

	public void WebElementClick(WebElement ele, String msg) {
		waitforClickableBywebElement(ele);
		ele.click();
		Listeners.test.log(Status.INFO, msg);
	}

	public void EnterText(WebElement element, String message) {
		element.sendKeys(message);
		Listeners.test.log(Status.INFO, "Entered Text :" + message);
	}

	public String getpageUrl() {
		Listeners.test.log(Status.INFO, "Opened URL :" + driver.getCurrentUrl());
		return driver.getCurrentUrl();

	}

	public ArrayList<String> TabsCounter() {
		ArrayList<String> n3 = new ArrayList<String>(driver.getWindowHandles());
		return n3;
	}

	public void waitforClickable(By FindElement) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(FindElement));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void WaitForAttribute(WebElement ele) {
		WebDriverWait waiter = new WebDriverWait(driver, Duration.ofSeconds(60));
		try {
			waiter.until(ExpectedConditions.attributeToBe(ele, "display", "none"));
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void waitforClickableBywebElement(WebElement FindElement) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(FindElement));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public WebElement waitForelementpresent(By FindElement) {
		wait.until(ExpectedConditions.presenceOfElementLocated(FindElement));
		return driver.findElement(FindElement);
	}

	public void WaitTillTextPresent(By FindElement, String text) {
		try {
			wait.until(ExpectedConditions.textToBePresentInElementLocated(FindElement, text));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void WaittillvisibilityOfElementLocated(By FindElement) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(FindElement));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void WaitforElementClickable(By FindElement) {
		wait.until(ExpectedConditions.elementToBeClickable(FindElement));
	}

	public void refresh() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(1000);
	}
	public void visibilityofElement(WebElement Element) {
		wait.until(ExpectedConditions.visibilityOf(Element));
		
	}
	
}
