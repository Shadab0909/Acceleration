package com.vtiger.testscripts;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vtiger.webpages.VtigerHomePage;
import com.vtiger.webpages.VtigerLoginPage;
import com.vtiger.webpages.VtigerOrganizationPage;

import utilities.SeleniumUtility;

public class TestOrganizationPage extends SeleniumUtility {
	WebDriver driver;
	VtigerLoginPage getVtigerLogin = null;
	VtigerHomePage getVtigerHome = null;
	VtigerOrganizationPage getVtigerOrganization = null;

	@BeforeClass
	public void prerequisite() {
		driver = setUp("chrome", "https://demo.vtiger.com/vtigercrm/index.php");
		getVtigerLogin = new VtigerLoginPage(driver);
		getVtigerHome = new VtigerHomePage(driver);
		getVtigerOrganization = new VtigerOrganizationPage(driver);
		getVtigerLogin.loginInVtiger(properties.getProperty("username"), properties.getProperty("password"));
		getVtigerHome.navigateToOrganizationsPage();
	}

	@Test(priority = 3)
	public void testOrganizationCreation() {
		getVtigerOrganization.createNewOrganization(properties.getProperty("orgName"),
				properties.getProperty("orgPhone"));
		Assert.assertTrue(getVtigerOrganization.checkOrganizationCreation(), "Organization not created");
	}

	@Test(priority = 3, dependsOnMethods = "testOrganizationCreation")
	public void testOrganizationEdit() {
		getVtigerOrganization.editCreatedOrganization(properties.getProperty("orgCity"));
		Assert.assertTrue(getVtigerOrganization.checkEditedOrganization(), "Oranization not edited");
	}

	@Test(priority = 3, dependsOnMethods = "testOrganizationEdit")
	public void testOrganizationDelete() {
		getVtigerOrganization.deleteEditedOrganization();
	}

	@AfterClass
	public void closeBrowser() {
		cleanUp();
	}
}
