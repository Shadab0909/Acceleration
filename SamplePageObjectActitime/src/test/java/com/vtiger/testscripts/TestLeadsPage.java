package com.vtiger.testscripts;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vtiger.webpages.VtigerHomePage;
import com.vtiger.webpages.VtigerLeadsPage;
import com.vtiger.webpages.VtigerLoginPage;

import utilities.SeleniumUtility;

public class TestLeadsPage extends SeleniumUtility {
	WebDriver driver;
	VtigerLoginPage getVtigerLoginPage = null;
	VtigerHomePage getVtigerHomePage = null;
	VtigerLeadsPage getVtigerLeadsPage = null;

	@BeforeClass
	public void prerequisite() {
		driver = setUp("chrome", "https://demo.vtiger.com/vtigercrm/index.php");
		getVtigerLoginPage = new VtigerLoginPage(driver);
		getVtigerHomePage = new VtigerHomePage(driver);
		getVtigerLeadsPage = new VtigerLeadsPage(driver);
		getVtigerLoginPage.loginInVtiger(properties.getProperty("username"), properties.getProperty("password"));
		getVtigerHomePage.navigateToLeadsPage();
	}

	@Test(priority = 0)
	public void testLeadCreation() {

		getVtigerLeadsPage.createNewLead(properties.getProperty("leadFirstName"),
				properties.getProperty("leadLastName"), properties.getProperty("leadContactNumber"));
		Assert.assertTrue(getVtigerLeadsPage.checkLeadCreation(), "Leads not created");
	}

	@Test(priority = 0, dependsOnMethods = "testLeadCreation")
	public void testEditCreatedLead() {
		getVtigerLeadsPage.editCreatedLead(properties.getProperty("leadCompanyName"));
		Assert.assertTrue(getVtigerLeadsPage.checkEditedLead(), "Lead not edited");
	}

	@Test(priority = 0, dependsOnMethods = "testEditCreatedLead")
	public void testDeleteCreatedLead() {
		getVtigerLeadsPage.deleteCreatedLead();
	}

	@AfterClass
	public void closeBrowser() {
		cleanUp();
	}
}
