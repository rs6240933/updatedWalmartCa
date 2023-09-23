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
import WalmartCa.PageObjects.launcherObject;
import WalmartCa.webutility.Baseclass;
import WalmartCa.webutility.Listeners;

public class OnboardTest extends Baseclass {
	OnboardStep1Object obj1;
	OnboardStep2Object obj2;

	@Test(priority = 1)
	public void VerifyStep1Link() throws InterruptedException, IOException {
		launcherObject launchh = new launcherObject();
		this.obj1 = launchh.launchApplication();
		String ExpectedURL = "https://apps.cedcommerce.com/marketplace-integration/walmartcanada/onboard/index?sHopiFy=1";
		String actualURL = obj1.getUrl();
		Assert.assertTrue(actualURL.contains(ExpectedURL),
				"Wron URl is opened Expected : " + ExpectedURL + " Actual URL " + actualURL);
	}

	@Test(priority = 2)
	public void VerifyClickHereLink() {

		String ExpectedURL = "https://seller.walmart.com/signup?onboardingmart=1&Agency=cedcommerce&locale=en-CA";
		String actualURL = obj1.openClickHere();
		Assert.assertTrue(actualURL.contains(ExpectedURL),
				"Wron URl is opened Expected : " + ExpectedURL + " Actual URL " + actualURL);
	}

	@Test(priority = 3)

	public void verifyContactUS() {
		String ExpectedURL = "https://walmart-support.cedcommerce.com/support/tickets/new";
		String actualURL = obj1.openContactUs();
		Assert.assertTrue(actualURL.contains(ExpectedURL),
				"Wron URl is opened Expected : " + ExpectedURL + " Actual URL " + actualURL);
	}

	@Test(priority = 4)

	public void uncheckCheckBox() throws InterruptedException {
		String attribute = obj1.nextBtndis();
		if (!(attribute.contains("disabled"))) {
			Assert.assertTrue(false, "element enabled");
		}
	}

	@Test(priority = 5)

	public void checkCheckBox() throws InterruptedException {
		String attribute = obj1.nextBtnen();
		if (attribute.contains("disabled")) {
			Assert.assertTrue(false, "element disabled");
		}
	}

	@Test(priority = 6)

	public void readMoreClickable() {
		String ExpectedURL = "https://apps.cedcommerce.com/marketplace-integration/policy/walmartcanada.pdf";
		String actualURL = obj1.readmore();
		Assert.assertTrue(actualURL.contains(ExpectedURL),
				"Wron URl is opened Expected : " + ExpectedURL + " Actual URL " + actualURL);
	}

	@Test(priority = 7)
	public void SellerCenterClickable() {
		String ExpectedURL = "https://seller.walmart.ca/";
		String actualURL = obj1.sellerCenter();
		Assert.assertTrue(actualURL.contains(ExpectedURL),
				"Wron URl is opened Expected : " + ExpectedURL + " Actual URL " + actualURL);
	}

	@Test(priority = 8)
	public void IfConsumeridIsgiven() throws InterruptedException {
		String validate = obj1.fillCredientials("sdewd", " ");
		if ((validate.contains("Secret Key cannot be blank"))) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false, "Validation message is not showing");

		}
	}

	@Test(priority = 9)
	public void IfSecretKeyIsgiven() throws InterruptedException {
		String validate = obj1.fillCredientials(" ", "sdcwed");
		if ((validate.contains("Consumer Id cannot be blank."))) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false, "Validation message is not showing");

		}
	}

	@Test(priority = 10)
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

	@Test(priority = 12)

	public void ImportOptionVisible() {
		ArrayList<WebElement> op = obj2.openDropdown();
		Assert.assertTrue(op.get(1).getText().contains("All products import"), "Wrong getting :" + op.get(1).getText());
		Assert.assertTrue(op.get(2).getText().contains("Published products import"),
				"Wrong getting :" + op.get(2).getText());
		Assert.assertTrue(op.get(3).getText().contains("Apply filter for"), "Wrong getting :" + op.get(3).getText());

	}

	@Test(priority = 13)
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

	@Test(priority = 14)
	public void selectProductType() throws InterruptedException {
		ArrayList<WebElement> AllOptions = obj2.selectProductType();
		if(AllOptions.size() == 0) {
			Assert.assertTrue(false, "ProductType options are not showing");
		}
	}
	@Test(priority = 15)
	public void selectProductVendor() throws InterruptedException {
		ArrayList<WebElement> AllOptions = obj2.selectProductVendor();
		if(AllOptions.size() == 0) {
			Assert.assertTrue(false, "ProductVendor options are not showing");
		}
	}
	@Test(priority = 16)
	public void selectCollection() throws InterruptedException {
		ArrayList<WebElement> AllOptions = obj2.selectCollection();
		if(AllOptions.size() == 0) {
			Assert.assertTrue(false, "Collection options are not showing");
		}
	}

}
