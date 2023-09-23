package WalmartCa.PageObjects;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import WalmartCa.webutility.Listeners;
import WalmartCa.webutility.utilities;

public class OnboardStep1Object extends utilities{
	WebDriver driver;
	By clickHere = By.xpath("//strong/a[@target='_blank']");
	By contactUsLink = By.cssSelector("strong a[class='c-pointer']");
	By nextBtn = By.xpath("//div[@class='Polaris-Page-Header__Pagination']/nav/button");
	By readmore =By.xpath("(//span[@class='Polaris-Button__Text'])[1]");
	By sellercenter = By.xpath("//a[@href='https://seller.walmart.ca/']");
	By TnC = By.xpath("//span[@class='Polaris-Checkbox']");
	@FindBy(id = "consumer_id")
	WebElement client_id;
	@FindBy(id = "secret_key")
	WebElement secret_idd;
	By validateSecretkey = By.xpath("(//div[@class='help-block'])[2]"); //locator for secret key span message
	By validateClientkey = By.xpath("(//div[@class='help-block'])[1]");  //locator for client key span message
	@FindBy (xpath="(//div[@id='Banner3Content'])[2]/p")
	WebElement TopvalidateMsg;
	@FindBy (xpath="//div[@class='nav-steps-wrap d-flex']/h2")
	WebElement productImport;
	By pImport =By.xpath("//div[@class='nav-steps-wrap d-flex']/h2");
	@FindBy (xpath="//div[@class='Polaris-Page-Header__Pagination']/nav/button")
	WebElement nextbutton;
	
	OnboardStep1Object(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public String getUrl() {
		String pageURL = getpageUrl();
		return pageURL;
	}
	
	public String openClickHere() {
		ElementClick(clickHere, "Clicked on click here link");
		return tabswitch();
	}
	public String openContactUs() {
		ElementClick(contactUsLink, "Clicked on Contact US link");
		
		return tabswitch();
	}
	
	public String tabswitch() {
		ArrayList<String> n = TabsCounter();
		driver.switchTo().window(n.get(3));
		String pageURL = getpageUrl();
		driver.close();
		driver.switchTo().window(n.get(2));
		return pageURL;
	}
	
	public String nextBtndis() throws InterruptedException {
		refresh();
		
		String str = nextbutton.getAttribute("class");
		Listeners.test.log(Status.INFO, str);
		return str;
	}
	public String nextBtnen() throws InterruptedException {
		refresh();
		ElementClick(TnC, "Clicked on terms and condition checkbox");
		String str = nextbutton.getAttribute("class");
		Listeners.test.log(Status.INFO, str);
		return str;
	}
	public String readmore() {
		ElementClick(readmore, "Clicked on read more link");
		return tabswitch();
	}
	public String sellerCenter() {
		ElementClick(sellercenter, "Clicked seller center link");
		return tabswitch();
	}
	String validateSecretkeyText = "Secret Key cannot be blank";
	String validateClientkeyText = "Consumer Id cannot be blank.";
	
	public String fillCredientials(String x, String y) throws InterruptedException {
		refresh();
		if(y == " ") {
			EnterText(client_id, x);
			ElementClick(TnC, "Clicked on terms and condition checkbox");
			WaitTillTextPresent(validateSecretkey, validateSecretkeyText);
			return driver.findElement(validateSecretkey).getText();
		}else if(x ==" ") {
			EnterText(secret_idd, y);
			ElementClick(TnC, "Clicked on terms and condition checkbox");
			WaitTillTextPresent(validateClientkey, validateClientkeyText);
			return driver.findElement(validateClientkey).getText();
		}else {
			EnterBothCred(x, y);
			return TopvalidateMsg.getText();
		}
	}
	public void EnterBothCred(String x, String y){
		EnterText(client_id, x);
		EnterText(secret_idd, y);
		ElementClick(TnC, "Clicked on terms and condition checkbox");
		ElementClick(nextBtn, "Clicked on Next button");
	}
	
	public OnboardStep2Object EnterStep2(String x, String y) throws InterruptedException {
		refresh();
		EnterBothCred(x,y);
		WaitTillTextPresent(pImport, "Product Import");
		Listeners.test.log(Status.INFO, "Step2 Opened");
		OnboardStep2Object ob2 = new OnboardStep2Object(driver);
		return ob2;
	}
	
}
