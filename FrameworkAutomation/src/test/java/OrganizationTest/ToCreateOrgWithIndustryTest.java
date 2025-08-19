package OrganizationTest;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import ElementRepository.CreateOrgPage;
import ElementRepository.HomePage;
import ElementRepository.OrganizationInfoPage;
import ElementRepository.OrganizationPage;
import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.WebDriverUtility;

public class ToCreateOrgWithIndustryTest extends BaseClass{
@Test(groups="Regression")
	public void ToCreateOrgWithIndustry_001() throws EncryptedDocumentException, IOException {
		HomePage hp = new HomePage(driver);
		hp.getOrganizationTextfield().click();
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateorganizationlookup().click();
		ExcelFileUtility eutil = new ExcelFileUtility();
		String ORGANIZATIONNAME = eutil.toReadDataFromExcelFile("Organization", 1, 2);
		JavaUtility jutil = new JavaUtility();
		CreateOrgPage cop = new CreateOrgPage(driver);
		cop.getOrganizationname().sendKeys(ORGANIZATIONNAME + jutil.toGenerateRandomNumber());
		WebDriverUtility weutil = new WebDriverUtility();
		WebElement industryDropDown = cop.getIndustry();
		weutil.toHandleDropdown(industryDropDown, "Chemicals");
		cop.getSave().click();
		//Fail
		//Assert.fail();
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String organizationname = oip.getHeaderElement().getText();
		Assert.assertTrue(organizationname.contains(ORGANIZATIONNAME));

	}

}
