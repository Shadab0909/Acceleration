package com.vtiger.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.SeleniumUtility;

public class VtigerOrganizationPage extends SeleniumUtility {
	WebDriver driver;

	public VtigerOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "Accounts_listView_basicAction_LBL_ADD_RECORD")
	private WebElement addOrganization;

	@FindBy(id = "Accounts_editView_fieldName_accountname")
	private WebElement organizationName;

	@FindBy(id = "Accounts_editView_fieldName_phone")
	private WebElement organizationPhone;

	@FindBy(xpath = "//td[@class='fieldValue']/div[@id='s2id_autogen7']")
	private WebElement assignedToOptionMenu;

	@FindBy(xpath = "//div[text()='Marketing Group']")
	private WebElement selectAssignedToAsMarketing;

	@FindBy(xpath = "//button[text()='Save']")
	private WebElement saveButtonForOrganization;

	@FindBy(xpath = "//h4[contains(text(),'Organizations')]")
	private WebElement organizationHeaderLink;

	@FindBy(xpath = "//span[contains(text(),'1234567890')]")
	private WebElement newlyCreatedOrganizationNumber;

	@FindBy(xpath = "//tr[td[span[span[contains(text(),'1234567890')]]]]/td[1]//span[@class='more dropdown action']")
	private WebElement oraganizationOptions;

	@FindBy(xpath = "//body/div[@id='page']/ul[3]/li[2]/a[1]")
	private WebElement organizationEditOption;

	@FindBy(id = "Accounts_editView_fieldName_bill_city")
	private WebElement organizationCityName;

	@FindBy(xpath = "//span[contains(text(),'Pune')]")
	private WebElement editedOrganizationCityName;
	
	@FindBy(xpath = "//body/div[@id='page']/ul[3]/li[3]/a[1]")
	private WebElement organizationDeleteOption;
	
	@FindBy(xpath="//button[text()='Yes']")
	private WebElement organizationDeleteYesBtn;

	public void createNewOrganization(String orgName, String orgPhone) {
		clickOnElement(addOrganization);
		typeInput(organizationName, orgName);
		typeInput(organizationPhone, orgPhone);
		clickOnElement(assignedToOptionMenu);
		clickOnElement(selectAssignedToAsMarketing);
		clickOnElement(saveButtonForOrganization);
		clickOnElement(organizationHeaderLink);
	}

	public boolean checkOrganizationCreation() {
		return isElementExist(newlyCreatedOrganizationNumber);
	}

	public void editCreatedOrganization(String orgCity) {
		clickOnElement(oraganizationOptions);
		clickOnElement(organizationEditOption);
		typeInput(organizationCityName, orgCity);
		clickOnElement(saveButtonForOrganization);
		clickOnElement(organizationHeaderLink);
	}

	public boolean checkEditedOrganization() {
		return isElementExist(editedOrganizationCityName);
	}
	
	public void deleteEditedOrganization() {
		clickOnElement(oraganizationOptions);
		clickOnElement(organizationDeleteOption);
		clickOnElement(organizationDeleteYesBtn);
	}
}
