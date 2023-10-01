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

public class OnboardStep3Object extends utilities {
	WebDriver driver;
	@FindBy(id = "select2-root_category-container")
	WebElement categoryDropdown;
	By categoryOptions = By.xpath("//ul[@id='select2-root_category-results']/li");
	@FindBy(id = "next_new")
	WebElement finishbtn;
	By popup = By.xpath("//div[@id='noty_layout__bottomCenter']/div/div");)
	
	

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
		WebElementClick(finishbtn, "Clicked on finish button");
	}

	public String PopUPText() {
		WaittillvisibilityOfElementLocated(popup);
		String text = driver.findElement(popup).getText();
		Listeners.test.log(Status.INFO, "Validation text - "+ text);
		return text;
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
		String[] arr = {txt, url};
		return arr;
	}
	
	public void VerifyvalidationforAttributes() {
		 refresh();
		 ArrayList<WebElement> option =  openDropdown();
		 for(int i = 0; i < option.size(); i++) {
			 if(option.get(i).getText().contains("Tires")) {
				 option.get(i).click();
				 break;
			 }
		 }
	}
}
