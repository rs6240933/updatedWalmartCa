package WalmartCaTest;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import WalmartCa.PageObjects.OnboardStep1Object;
import WalmartCa.PageObjects.OnboardStep2Object;
import WalmartCa.PageObjects.OnboardStep3Object;
import WalmartCa.PageObjects.launcherObject;
import WalmartCa.webutility.Baseclass;
import WalmartCa.webutility.Listeners;

public class OnboardTest extends Baseclass {
	OnboardStep1Object obj1;
	OnboardStep2Object obj2;
	OnboardStep3Object obj3;
	
	@Test(priority = 1)
	public void VerifyStep1Link() throws InterruptedException, IOException {
		launcherObject launchh = new launcherObject();
		this.obj1 = launchh.launchApplication();
		String ExpectedURL = "https://apps.cedcommerce.com/marketplace-integration/walmartcanada/onboard/index?sHopiFy=1";
		String actualURL = obj1.getUrl();
		Assert.assertTrue(actualURL.contains(ExpectedURL),
				"Wron URl is opened Expected : " + ExpectedURL + " Actual URL " + actualURL);
	}

	@Test(priority = 2, enabled = false)
	public void VerifyClickHereLink() {

		String ExpectedURL = "https://seller.walmart.com/signup?onboardingmart=1&Agency=cedcommerce&locale=en-CA";
		String actualURL = obj1.openClickHere();
		Assert.assertTrue(actualURL.contains(ExpectedURL),
				"Wron URl is opened Expected : " + ExpectedURL + " Actual URL " + actualURL);
	}

	@Test(priority = 3, enabled = false)

	public void verifyContactUS() {
		String ExpectedURL = "https://walmart-support.cedcommerce.com/support/tickets/new";
		String actualURL = obj1.openContactUs();
		Assert.assertTrue(actualURL.contains(ExpectedURL),
				"Wron URl is opened Expected : " + ExpectedURL + " Actual URL " + actualURL);
	}

	@Test(priority = 4, enabled = false)

	public void uncheckCheckBox() throws InterruptedException {
		String attribute = obj1.nextBtndis();
		if (!(attribute.contains("disabled"))) {
			Assert.assertTrue(false, "element enabled");
		}
	}

	@Test(priority = 5, enabled = false)

	public void checkCheckBox() throws InterruptedException {
		String attribute = obj1.nextBtnen();
		if (attribute.contains("disabled")) {
			Assert.assertTrue(false, "element disabled");
		}
	}

	@Test(priority = 6, enabled = false)

	public void readMoreClickable() {
		String ExpectedURL = "https://apps.cedcommerce.com/marketplace-integration/policy/walmartcanada.pdf";
		String actualURL = obj1.readmore();
		Assert.assertTrue(actualURL.contains(ExpectedURL),
				"Wron URl is opened Expected : " + ExpectedURL + " Actual URL " + actualURL);
	}

	@Test(priority = 7, enabled = false)
	public void SellerCenterClickable() {
		String ExpectedURL = "https://seller.walmart.ca/";
		String actualURL = obj1.sellerCenter();
		Assert.assertTrue(actualURL.contains(ExpectedURL),
				"Wron URl is opened Expected : " + ExpectedURL + " Actual URL " + actualURL);
	}

	@Test(priority = 8, enabled = false)
	public void IfConsumeridIsgiven() throws InterruptedException {
		String validate = obj1.fillCredientials("sdewd", " ");
		if ((validate.contains("Secret Key cannot be blank"))) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false, "Validation message is not showing");

		}
	}

	@Test(priority = 9, enabled = false)
	public void IfSecretKeyIsgiven() throws InterruptedException {
		String validate = obj1.fillCredientials(" ", "sdcwed");
		if ((validate.contains("Consumer Id cannot be blank."))) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false, "Validation message is not showing");

		}
	}

	@Test(priority = 10, enabled = false)
	public void BothInvalid() throws InterruptedException {
		String validate = obj1.fillCredientials("sdwed", "sdwedew");
		if ((validate.contains("API credentials are invalid. Please enter valid api credentials"))) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false, "Validation message is not showing");

		}
	}

	@Test(priority = 11)
	public void BothValid() throws InterruptedException {
		this.obj2 = obj1.EnterStep2("CedCommerce007", "CedCommerce008");
	}

	@Test(priority = 12, enabled = false)

	public void ImportOptionVisible() {
		ArrayList<WebElement> op = obj2.openDropdown();
		Assert.assertTrue(op.get(1).getText().contains("All products import"), "Wrong getting :" + op.get(1).getText());
		Assert.assertTrue(op.get(2).getText().contains("Published products import"),
				"Wrong getting :" + op.get(2).getText());
		Assert.assertTrue(op.get(3).getText().contains("Apply filter for"), "Wrong getting :" + op.get(3).getText());

	}

	@Test(priority = 13, enabled = false)
	public void ClickOnApplyFilter() throws InterruptedException { // verify the fields which isgetting visible after
																	// selecting ApplyFilter
		ArrayList<WebElement> option1 = obj2.clickApplyFilter();
		Assert.assertTrue(option1.get(0).getText().contains("Product type"),
				"Wrong getting :" + option1.get(0).getText());
		Assert.assertTrue(option1.get(1).getText().contains("Product vendor"),
				"Wrong getting :" + option1.get(1).getText());
		Assert.assertTrue(option1.get(2).getText().contains("Collections"),
				"Wrong getting :" + option1.get(1).getText());
	}

	@Test(priority = 14, enabled = false)
	public void selectProductType() throws InterruptedException {
		ArrayList<WebElement> AllOptions = obj2.Import(0);
		if (AllOptions.size() == 0) {
			Assert.assertTrue(false, "ProductType options are not showing");
		}
	}

	@Test(priority = 15, enabled = false)
	public void selectProductVendor() throws InterruptedException {
		ArrayList<WebElement> AllOptions = obj2.Import(1);
		if (AllOptions.size() == 0) {
			Assert.assertTrue(false, "ProductVendor options are not showing");
		}
	}

	@Test(priority = 16, enabled = false)
	public void selectCollection() throws InterruptedException {
		ArrayList<WebElement> AllOptions = obj2.Import(2);
		if (AllOptions.size() == 0) {
			Assert.assertTrue(false, "Collection options are not showing");
		}

	}

	@Test(priority = 17, enabled = false)
	public void SelectPublishProduct() {
		String text = obj2.ClickPublishProductImport(2);
		if (!(text.contains("Published product import confirmation"))) {
			Assert.assertTrue(false, "Modal is not Opened");
		}

	}

	@Test(priority = 18, enabled = false)
	public void SelectAllProduct() {
		String text = obj2.ClickPublishProductImport(1);
		if (!(text.contains("All products import confirmation"))) {
			Assert.assertTrue(false, "Modal is not Opened");
		}
	}

	@Test(priority = 19, enabled = false)
	public void SelectAnyCollectionandClickOnImport() throws InterruptedException {
		String text = obj2.ImportBycollection(2);
		if (!(text.contains("Filtered product import confirmation"))) {
			Assert.assertTrue(false, "Modal is not Opened");
		}
	}

	@Test(priority = 20, enabled = false)
	public void SelectAnyvendorandClickOnImport() throws InterruptedException {
		String text = obj2.ImportByVendor(1);
		if (!(text.contains("Filtered product import confirmation"))) {
			Assert.assertTrue(false, "Modal is not Opened");
		}
	}

	@Test(priority = 21, enabled = false)
	public void SelectAnytypeandClickOnImport() throws InterruptedException {
		String text = obj2.ImportBytype(0);
		if (!(text.contains("Filtered product import confirmation"))) {
			Assert.assertTrue(false, "Modal is not Opened");
		}
	}

	@Test(priority = 22, enabled = false)
	public void InstantImportViaCollection() throws InterruptedException {
		String arr1 = obj2.InstantImport(2);
		if (arr1.contains("imported successfully")) {
			Assert.assertTrue(true, "Test case passed");
		} else {
			Assert.assertTrue(false, "Test case failed");
		}
	}

	@Test(priority = 23)
	public void InstantImportViaVendor() throws InterruptedException {
		String arr1 = obj2.InstantImport(1);
		if (arr1.contains("imported successfully")) {
			Assert.assertTrue(true, "Test case passed");
		} else {
			Assert.assertTrue(false, "Test case failed");
		}
	}

	@Test(priority = 24, enabled = false)
	public void InstantImportViatype() throws InterruptedException {
		String arr1 = obj2.InstantImport(0);
		if (arr1.contains("imported successfully")) {
			Assert.assertTrue(true, "Test case passed");
		} else {
			Assert.assertTrue(false, "Test case failed");
		}
	}
	
	@Test(priority = 25, enabled = false)
	public void InstantPublishImport() {
		String n1 = obj2.AllandPublishproductImport(2);
		if (n1.contains("imported successfully")) {
			Assert.assertTrue(true, "Test case passed");
		} else {
			Assert.assertTrue(false, "Test case failed");
		}
	}
	@Test(priority = 25, enabled = false)
	public void InstantAllImport() {
		String n1 = obj2.AllandPublishproductImport(1);
		if (n1.contains("imported successfully")) {
			Assert.assertTrue(true, "Test case passed");
		} else {
			Assert.assertTrue(false, "Test case failed");
		}
	}
	@Test(priority = 26, enabled = false)
	public void VerifyNextButtonByCollection() {
		this.obj3 = obj2.ClickOnNextButtonForAllFilters(2);
		String text = obj3.verifyurl();
		if (text.contains("sHopiFy=3")) {
			Assert.assertTrue(true, "Test case passed");
		} else {
			Assert.assertTrue(false, "Test case failed");
		}
	}
	@Test(priority = 27, enabled = false)
	public void VerifyNextButtonByVendor() {
		this.obj3 = obj2.ClickOnNextButtonForAllFilters(1);
		String text = obj3.verifyurl();
		if (text.contains("sHopiFy=3")) {
			Assert.assertTrue(true, "Test case passed");
		} else {
			Assert.assertTrue(false, "Test case failed");
		}
	}
	@Test(priority = 28, enabled = false)
	public void VerifyNextButtonBytype() {
		this.obj3 = obj2.ClickOnNextButtonForAllFilters(0);
		String text = obj3.verifyurl();
		if (text.contains("sHopiFy=3")) {
			Assert.assertTrue(true, "Test case passed");
		} else {
			Assert.assertTrue(false, "Test case failed");
		}
	}
	
	@Test(priority = 29, enabled = false)
	public void VerifyNextButtonPublish() {
		this.obj3 = obj2.clickOnNextButtonByAllandPublish(2);
		String text = obj3.verifyurl();
		if (text.contains("sHopiFy=3")) {
			Assert.assertTrue(true, "Test case passed");
		} else {
			Assert.assertTrue(false, "Test case failed");
		}
	}
	@Test(priority = 30, enabled = false)
	public void VerifyNextButtonAllProduct() {
		this.obj3 = obj2.clickOnNextButtonByAllandPublish(1);
		String text = obj3.verifyurl();
		if (text.contains("sHopiFy=3")) {
			Assert.assertTrue(true, "Test case passed");
		} else {
			Assert.assertTrue(false, "Test case failed");
		}
	}
	
	@Test(priority = 31, enabled = false)
	public void BackendImportviaCollection() {
		String actual  = obj2.BackendImportstart(2);
		if(!(actual.contains("Activities ongoing"))) {
			Assert.assertTrue(false, "PopOver is not opened. Actual text is :"+ actual);
		}
	}
	@Test(priority = 32, enabled = false)
	public void BackendImportviaVendor() {
		String actual  = obj2.BackendImportstart(1);
		if(!(actual.contains("Activities ongoing"))) {
			Assert.assertTrue(false, "PopOver is not opened. Actual text is :"+ actual);
		}
	}
	@Test(priority = 33, enabled = false)
	public void BackendImportviaType() {
		String actual  = obj2.BackendImportstart(0);
		if(!(actual.contains("Activities ongoing"))) {
			Assert.assertTrue(false, "PopOver is not opened. Actual text is :"+ actual);
		}
	}
	@Test(priority = 34, enabled = false)
	public void PublishBackendImport() throws InterruptedException {
		String actual  = obj2.backendPublishandAllproductImport(2);
		if(!(actual.contains("Activities ongoing"))) {
			Assert.assertTrue(false, "PopOver is not opened. Actual text is :"+ actual);
		}
	}
	@Test(priority = 35, enabled = false)
	public void AllBackendImport() throws InterruptedException {
		String actual  = obj2.backendPublishandAllproductImport(1);
		if(!(actual.contains("Activities ongoing"))) {
			Assert.assertTrue(false, "PopOver is not opened. Actual text is :"+ actual);
		}
	}
	
}
