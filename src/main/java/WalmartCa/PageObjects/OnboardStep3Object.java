package WalmartCa.PageObjects;

import org.openqa.selenium.WebDriver;

import WalmartCa.webutility.utilities;

public class OnboardStep3Object extends utilities{
	WebDriver driver;
	public OnboardStep3Object(WebDriver driver) {
		super(driver);
		this.driver = driver;
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

}
