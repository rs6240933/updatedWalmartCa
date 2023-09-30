package WalmartCa.PageObjects;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;

import WalmartCa.webutility.utilities;
import io.netty.handler.timeout.TimeoutException;
import WalmartCa.webutility.Listeners;

public class OnboardStep2Object extends utilities {
	WebDriver driver;
	@FindBy(id = "ced-product-import")
	WebElement dropdown;
	By applyfilter = By.xpath("(//select[@id='ced-product-import']/option)[4]");
	By applyFilteroptions = By.xpath("//span[@class='Polaris-Choice__Label']");
	@FindBy(id = "ced_fetch_by_link")
	WebElement fetch;
	By fetched = By.id("ced_fetch_by_link");
	By fetchoptions = By.xpath("//ul[@id='select2-ced_import_select2_value-results']/li");
	By AlldropdownOptions = By.xpath("//select[@id='ced-product-import']/option");
	By Modal = By.id("modal-header");
	By importButton = By.xpath("(//div[@class='Polaris-FormLayout'])[3]/div/button");
	@FindBy(css = "div[id='modal-header'] h2")
	WebElement ModalHeader;
	@FindBy(id = "popup-confirm-cancel")
	WebElement InstantBtn;
	By loaderText = By.xpath("//span[@class='loader-message']");
	By bannermsg = By.xpath("(//div[@id=\"Banner3Content\"])[2]/p");
	@FindBy(id = "ced_import_next_button")
	WebElement Nextbtn;
	By Step3Text = By.cssSelector("h4[class='Polaris-Heading custom-required']");
	@FindBy(id = "popup-confirm")
	WebElement backendBtn;
	By popover = By.xpath("//div[@id='ongoing-activity']/div/div");
	@FindBy(xpath = "//h2[@class='Polaris-Heading line_clamp_ele_1 ']")
	WebElement popoverText;
	@FindBy(xpath = "//button[@class='Polaris-Button Polaris-Button--plain']/a")
	WebElement seeAll;
	@FindBy(xpath = "(//div[@class='Polaris-ButtonGroup']/div/button/span)[1]")
	WebElement Abort;
	@FindBy(id = "popup-confirm")
	WebElement AbortConfrm;
	
	OnboardStep2Object(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		changeURL();
	}

	private void changeURL() {
		String current_url = driver.getCurrentUrl();

		String[] array = current_url.split("walmartcanada");
		System.out.print(array[0]);
		String newUrl = array[0] + "walmartcanada/onboard/index?sHopiFy=2";
		driver.navigate().to(newUrl);

	}

	public ArrayList<WebElement> openDropdown() {
		dropdown.click();
		ArrayList<WebElement> options = new ArrayList<WebElement>(driver.findElements(AlldropdownOptions));
		for (int i = 0; i < options.size(); i++) {

			Listeners.test.log(Status.INFO, options.get(i).getText());
		}
		return options;
	}

	public ArrayList<WebElement> selectImportType() throws InterruptedException {
		refresh();
		dropdown.click();
		ArrayList<WebElement> options = openDropdown();
		options.get(3).click();
		ArrayList<WebElement> importType = new ArrayList<WebElement>(driver.findElements(applyFilteroptions));
		for (int i = 0; i < importType.size(); i++) {
			Listeners.test.log(Status.INFO, importType.get(i).getText());
		}
		return importType;
	}

	public ArrayList<WebElement> clickApplyFilter() throws InterruptedException {
		ArrayList<WebElement> importTypeOptions = selectImportType();
		for (int i = 0; i < importTypeOptions.size(); i++) {

			Listeners.test.log(Status.INFO, importTypeOptions.get(i).getText());
		}
		return importTypeOptions;
	}

	public ArrayList<WebElement> selectProductType() throws InterruptedException {
		ArrayList<WebElement> applyOptions = clickApplyFilter();
		applyOptions.get(0).click();
		Listeners.test.log(Status.PASS, "Checked Product Type");
		ArrayList<WebElement> AllOption = optionTobeFetch();
		if (AllOption.size() > 0) {
			Listeners.test.log(Status.INFO, "Product Type Fetched");
		}
		return AllOption;
	}

	public ArrayList<WebElement> optionTobeFetch() {
		WaittillvisibilityOfElementLocated(fetched);
		Actions ac = new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");
		waitforClickable(fetched);
		ElementClick(fetched, "clicked on fetched option");
		WaittillvisibilityOfElementLocated(fetchoptions);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<WebElement> AllOptions = new ArrayList<WebElement>(driver.findElements(fetchoptions));
		return AllOptions;

	}

	public ArrayList<WebElement> selectProductVendor() throws InterruptedException {
		ArrayList<WebElement> applyOptions = clickApplyFilter();
		applyOptions.get(1).click();
		Listeners.test.log(Status.PASS, "Checked Product vendor");
		ArrayList<WebElement> AllOption = optionTobeFetch();
		if (AllOption.size() > 0) {
			Listeners.test.log(Status.INFO, "Product Vendor Fetched");
		}
		return AllOption;
	}

	public ArrayList<WebElement> selectCollection() throws InterruptedException {
		ArrayList<WebElement> applyOptions = clickApplyFilter();
		applyOptions.get(2).click();
		Listeners.test.log(Status.PASS, "Checked Collection");
		ArrayList<WebElement> AllOption = optionTobeFetch();
		if (AllOption.size() > 0) {
			Listeners.test.log(Status.INFO, "Collection Fetched");
		}
		return AllOption;
	}

	public String ClickPublishProductImport(int optionToClick) {
		try {
			refresh();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dropdown.click();
		ArrayList<WebElement> options = openDropdown();
		options.get(optionToClick).click();
		Listeners.test.log(Status.INFO, "Clicked On " + options.get(optionToClick).getText());
		return openImportModal();
	}

	public String openImportModal() {
		WaittillvisibilityOfElementLocated(Modal);
		Listeners.test.log(Status.PASS, "Modal Opened");
		return ModalHeader.getText();
	}

	public ArrayList<WebElement> Import(int i) throws InterruptedException {
		refresh();
		ArrayList<WebElement> dropdownOpt = openDropdown();
		dropdownOpt.get(3).click();
		ArrayList<WebElement> fiteropts = new ArrayList<WebElement>(driver.findElements(applyFilteroptions));
		fiteropts.get(i).click();
		Listeners.test.log(Status.INFO, fiteropts.get(i).getText());
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		Thread.sleep(2000);
		ElementClick(fetched, "clicked on fetched option");
		WaittillvisibilityOfElementLocated(fetchoptions);
		ArrayList<WebElement> AllOptionss = new ArrayList<WebElement>(driver.findElements(fetchoptions));
		waitforClickable(fetchoptions);
		if (AllOptionss.size() > 0) {
			for (int z = 0; z < AllOptionss.size(); z++) {
				Listeners.test.log(Status.INFO, "Selected -" + AllOptionss.get(z).getText());
				AllOptionss.get(z).click();
				
			}
			
		}
		return AllOptionss;

	}

	public String ImportBytype(int x) throws InterruptedException {
		ArrayList<WebElement> AllOptions = Import(x);
		driver.findElement(By.xpath("(//h2[@class='Polaris-Heading'])[2]")).click();
		Listeners.test.log(Status.INFO, "Clicked on dropdown");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		waitforClickable(importButton);
		ElementClick(importButton, "Clicked on start import Button");
		return openImportModal();
	}

	public String ImportByVendor(int x) throws InterruptedException {
		ArrayList<WebElement> AllOptions = Import(x);
		driver.findElement(By.xpath("(//h2[@class='Polaris-Heading'])[2]")).click();
		Listeners.test.log(Status.INFO, "Clicked on dropdown");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		waitforClickable(importButton);
		ElementClick(importButton, "Clicked on start import Button");
		return openImportModal();
	}

	public String ImportBycollection(int x) throws InterruptedException {
		ArrayList<WebElement> AllOptions = Import(x);
		driver.findElement(By.xpath("(//h2[@class='Polaris-Heading'])[2]")).click();
		Listeners.test.log(Status.INFO, "Clicked on dropdown");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		waitforClickable(importButton);
		ElementClick(importButton, "Clicked on start import Button");
		return openImportModal();
	}

	public String InstantImport(int i) throws InterruptedException {
		if (i == 2) {
			String txt = ImportBycollection(2);
		}
		if (i == 1) {
			String txt = ImportByVendor(1);
		}
		if (i == 0) {
			String txt = ImportBytype(0);
		}

		return ClickInstantImport();

		// ImportBytype
	}

	public String ClickInstantImport() {
		WebElementClick(InstantBtn, "Clicked on Instant Button");
		WaitTillTextPresent(loaderText, "imported successfully!");
		Listeners.test.log(Status.PASS, "Loader displayed Successfully");
		String loadermsg = driver.findElement(loaderText).getText();
		WaittillvisibilityOfElementLocated(By.xpath("(//div[@id=\"Banner3Content\"])[2]/p"));
		String bannermsg = driver.findElement(By.xpath("(//div[@id=\"Banner3Content\"])[2]/p")).getText();
		Listeners.test.log(Status.INFO, "Loader text " + loadermsg);
		Listeners.test.log(Status.INFO, "banner text " + bannermsg);

		return bannermsg;
	}

	public String AllandPublishproductImport(int option) {
		ClickPublishProductImport(option);
		return ClickInstantImport();
	}

	public OnboardStep3Object ClickOnNextButtonForAllFilters(int i) {
		String url = getpageUrl();
		if (!(url.contains("sHopiFy=2"))) {
			String[] array = url.split("walmartcanada");
			System.out.print(array[0]);
			String newUrl = array[0] + "walmartcanada/onboard/index?sHopiFy=2";
			driver.navigate().to(newUrl);
		}
		try {
			InstantImport(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clickNextbtn();
	}

	public OnboardStep3Object clickNextbtn() {
		OnboardStep3Object obj3 = null;
		WebElementClick(Nextbtn, "Clicked on Next Button");
		Listeners.test.log(Status.INFO, "Clicked on Next Button");
		waitForelementpresent(Step3Text);
		if (driver.findElement(Step3Text).getText().contains("Walmart Canada Category")) {
			obj3 = new OnboardStep3Object(driver);
		}
		return obj3;
	}

	public OnboardStep3Object clickOnNextButtonByAllandPublish(int i) {
		ClickPublishProductImport(i);
		ClickInstantImport();
		return clickNextbtn();
	}

	public String BackendImportstart(int i) {
		String url = getpageUrl();
		String text = null;
		if (!(url.contains("sHopiFy=2"))) {
			String[] array = url.split("walmartcanada");
			System.out.print(array[0]);
			String newUrl = array[0] + "walmartcanada/onboard/index?sHopiFy=2";
			driver.navigate().to(newUrl);
		}
		try {
			text = BackendImport(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return text;
	}

	public String BackendImport(int i) throws InterruptedException {
		if (i == 2) {
			String txt = ImportBycollection(2);
		}
		if (i == 1) {
			String txt = ImportByVendor(1);
		}
		if (i == 0) {
			String txt = ImportBytype(0);
		}

		return ClickBackendImport();

	}

	public String ClickBackendImport() throws InterruptedException {
		WebElementClick(backendBtn, "Clicked on Backend Import Button");
		WaitTillTextPresent(loaderText, "imported successfully!");
		Listeners.test.log(Status.PASS, "Loader displayed Successfully");
		WaittillvisibilityOfElementLocated(popover);
		refresh();
		driver.findElement(By.xpath("//div[@class='Polaris-Card__Section']/button/span")).click();
		visibilityofElement(popoverText);
		String popuptxt = popoverText.getText();
		Listeners.test.log(Status.INFO, "Popover message -"+popuptxt);
		WebElementClick(seeAll, "Clicked on See all button");
		ArrayList<String> n = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(n.get(3));
		WebElementClick(Abort, "Clicked on Abort button");
		WebElementClick(AbortConfrm, "Aborted the process");
		driver.close();
		
		driver.switchTo().window(n.get(2));
		clickNextbtn();
		return popuptxt;
	}
	
	public String backendPublishandAllproductImport(int i) throws InterruptedException {
		String url = getpageUrl();
		if (!(url.contains("sHopiFy=2"))) {
			String[] array = url.split("walmartcanada");
			System.out.print(array[0]);
			String newUrl = array[0] + "walmartcanada/onboard/index?sHopiFy=2";
			driver.navigate().to(newUrl);
		}
		ClickPublishProductImport(i);
		return ClickBackendImport();
	}

}
