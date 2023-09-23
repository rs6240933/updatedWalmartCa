package WalmartCaTest;

import java.io.IOException;

import org.testng.annotations.Test;

import WalmartCa.PageObjects.launcherObject;
import WalmartCa.webutility.Baseclass;

public class OnboardTest extends Baseclass{
	@Test(priority = 1)
	public void VerifyStep1Link() throws InterruptedException, IOException {
		launcherObject launchh = new launcherObject();
		launchh.launchApplication();
	}

}
