package WalmartCa.PageObjects;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import WalmartCa.webutility.Listeners;
import WalmartCa.webutility.utilities;

public class OnboardStep3Object extends utilities {
	WebDriver driver;
	@FindBy(id = "select2-root_category-container")
	WebElement categoryDropdown;
	By categoryOptions = By.xpath("//ul[@id='select2-root_category-results']/li");
	@FindBy(id = "next_new")
	WebElement finishbtn;
	By popup = By.xpath("//div[@id='noty_layout__bottomCenter']/div/div");
	@FindBy (id="skipAndContinue")
	WebElement skipDiv;
	@FindBy (xpath = "//div[@id='skipAndContinue']/button")
	WebElement skipBtn;
	@FindBy (xpath = "//label[@for='Checkbox1']/span/span")
	WebElement skipCheckBox;
	@FindBy (xpath = "//div[@class='row']/div/h3")
	WebElement NoProfile;
	@FindBy (id = "popup-confirm")
	WebElement popUp;
	By Welcome = By.xpath("//div[@class='Polaris-Header-Title']/h1");
	@FindBy (xpath = "(//li[@class='Polaris-Navigation__ListItem'])[2]/a")
	WebElement profilelink;
	@FindBy (id = "LoadingMSG")
	WebElement loader;
	@FindBy (id = "TextField1")
	WebElement taxcode;
	By variantAttr = By.xpath("(//tbody)[2]/tr");
	By grid = By.xpath("//tbody/tr");
	@FindBy (xpath = "//button[@class='Polaris-Button Polaris-Button--iconOnly']")
	WebElement actions;
	@FindBy (xpath = "(//ul[@class='Polaris-Navigation__Section'])[2]/li[2]/a")
	WebElement edit;
	By heading = By.xpath("//div[@class='Polaris-Header-Title']/h1");
	
	public OnboardStep3Object(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		changeURL();
	}

	private void changeURL() {
		String current = getpageUrl();
		String[] array = current.split("walmartcanada");
		System.out.print(array[0]);
		String newUrl = array[0] + "walmartcanada/onboard/index?sHopiFy=3";
		driver.navigate().to(newUrl);
	}

	public String verifyurl() {
		return getpageUrl();
	}

	public ArrayList<WebElement> openDropdown() {
		WebElementClick(categoryDropdown, "Walmart Category Dropdown Opened");
		ArrayList<WebElement> options = new ArrayList<WebElement>(driver.findElements(categoryOptions));
		return options;
	}

	public boolean VerifyDropdown() {
		Listeners.test.log(Status.INFO, "check whether the dropdown has category options or not");
		ArrayList<WebElement> option = openDropdown();
		if (option.size() > 1) {
			return true;
		} else {
			return false;
		}
	}

	public void clickFinish() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElementClick(finishbtn, "Clicked on finish button");
	}

	public String PopUPText() {
		WaittillvisibilityOfElementLocated(popup);
		String text = " ";
		try {
			 text = driver.findElement(popup).getText();
			Listeners.test.log(Status.INFO, "Validation text - " + text);
			return text;
		}catch(Exception e){
			return text;
		}
		
	}

	public String[] verifyFinishBtn() {
		try {
			refresh();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Listeners.test.log(Status.INFO, "Check by clicking on Finish button Validation message is showing or not");
		clickFinish();
		String url = getpageUrl();
		String txt = PopUPText();
		String[] arr = { txt, url };
		return arr;
	}
	
	public void urlCheck() {
		String n = getpageUrl();
		if(!(n.contains("sHopiFy=3"))) {
			changeURL();
		}
	}

	public ArrayList<String> VerifyvalidationforAttributes(String[] category) {
		urlCheck();
		Listeners.test.log(Status.INFO, "Check whether the popup is showing or not if required attribute is empty");
		ArrayList<String> arr1 = new ArrayList<String>();
		for (int x = 0; x < category.length; x++) {
			try {
				refresh();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<WebElement> option = openDropdown();
			for (int i = 0; i < option.size(); i++) {
				if (option.get(i).getText().contains(category[x])) {
					Listeners.test.log(Status.INFO, "clicked on - "+option.get(i).getText());
					option.get(i).click();
					
					break;
				}
			}
			WaitForAttribute(loader);
			clickFinish();
			String text = PopUPText();
			if (!(text.contains("is required"))) {
				arr1.add(category[x]);
				Listeners.test.log(Status.FAIL, "Not showing Validation message for +"+category[x]);
			}
		}
		return arr1;
	}
	
	public String SkipandContinueBtn() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElementClick(skipCheckBox, "Clicked on skip check box");
		WaitForAttribute2(skipDiv);
		return skipBtn.getText();
	}
	
	public String VerifySkipandContinueBtn() {
		urlCheck();
		try {
			refresh();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WaitForAttribute(loader);
		Listeners.test.log(Status.INFO, "verify if skip checkbox is checked then Skip and continue button is visible or not");
		return SkipandContinueBtn();
	}
	
	public String VerifySkipandContinueurl() {
		urlCheck();
		try {
			refresh();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WaitForAttribute(loader);
		Listeners.test.log(Status.INFO, "verify by clicking on Skip and continue button it is redirecting to the dashboard and no default profile is creating");
		SkipandContinueBtn();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		WebElementClick(skipBtn, "Clicked oN Skip & Continue");
		WebElementClick(popUp, "Comfirmation popup is displayed");
		Listeners.test.log(Status.INFO, "Clicked Ok");
		GoToProfile();
		if(NoProfile.isDisplayed()) {
			Listeners.test.log(Status.PASS, "No Profile is present");
			return "No Profile is present";
		}else {
			return "Profile is present";
		}
	}
	
	public void GoToProfile() {
		WaittillvisibilityOfElementLocated(Welcome); 
		WebElementClick(profilelink, "Clicked oN Profile Link");
	}
	
	public void createDefaultProfile() {
		urlCheck();
		Listeners.test.log(Status.INFO, "Check by creating default profile then verify whether all the attribute value is showing correctly at profile section");
		ArrayList<WebElement> option = openDropdown();
		for (int i = 0; i < option.size(); i++) {
			if (option.get(i).getText().contains("Beauty, Personal Care, & Hygiene")) {
				Listeners.test.log(Status.INFO, "clicked on - "+option.get(i).getText());
				option.get(i).click();
				break;
			}
		}
		WaitForAttribute(loader);
		EnterText(taxcode, "1234567");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		String[] walmart_arr = new String[4];
		String[] shopify_arr = new String[4];
		ArrayList<WebElement> attributes = new ArrayList<WebElement>(driver.findElements(variantAttr));
		for(int i=0; i < attributes.size(); i++) {
			if(i > 3) {
				break;
			}
			
			String walmart = driver.findElement(By.xpath("(((//tbody)[2]/tr)["+(i+1)+"]/td)[1]/p")).getText();
			String shopify = driver.findElement(By.xpath("(//select)["+(i+2)+"]/option[1]")).getText();
			driver.findElement(By.xpath("(//select)["+(i+2)+"]/option[1]")).click();		
			Listeners.test.log(Status.INFO, "selected "+shopify+"  for Walmart attribute "+walmart);
			 walmart_arr[i] = walmart;
			 shopify_arr[i] = shopify;
		}
		for(int a=0; a < walmart_arr.length; a++) {
			System.out.println(walmart_arr[a]+" = "+shopify_arr[a]);
		}
		jse.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		WebElementClick(finishbtn, "Clicked oN Finish Button");
		GoToProfile();
		try {
			ArrayList<WebElement> profiles = new ArrayList<WebElement>(driver.findElements(grid));
			WebElementClick(actions, "Clicked oN actions button");
			WebElementClick(edit, "Clicked oN edit button");
			WaittillvisibilityOfElementLocated(heading);
			String[] walmart_arr1 = new String[4];
			String[] shopify_arr1 = new String[4];
			for(int i=0; i < attributes.size(); i++) {
				if(i > 3) {
					break;
				}
				
				String walmart_app = driver.findElement(By.xpath("(((//tbody)[2]/tr)["+(i+1)+"]/td)[1]/p")).getText();
				String shopify_app = driver.findElement(By.xpath("(//select)["+(i+2)+"]/option")).getText();	
				Listeners.test.log(Status.INFO, "selected under app "+shopify_app+"  for Walmart attribute "+walmart_app);
				 walmart_arr1[i] = walmart_app;
				 shopify_arr1[i] = shopify_app;
			}
		}catch(Exception e) {
			Listeners.test.log(Status.FAIL, "Default Profile is not showing");
			e.printStackTrace();
		}
		
		
	}
}
