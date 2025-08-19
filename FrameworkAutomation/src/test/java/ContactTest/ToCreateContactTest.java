package ContactTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ElementRepository.ContactInfoPage;
import ElementRepository.ContactsPage;
import ElementRepository.CreateContactPage;
import ElementRepository.HomePage;
import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;

@Listeners(genericUtility.ListenersImplementation.class)
public class ToCreateContactTest extends BaseClass {
	@Test(groups="Smoke")

	public void toCreateContact_001() throws EncryptedDocumentException, IOException {
		HomePage hp = new HomePage(driver);
		hp.getContactTextfield().click();
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreatecontactlookup().click();
		ExcelFileUtility eutil = new ExcelFileUtility();
		String LASTNAME = eutil.toReadDataFromExcelFile("Contactcs", 1, 2);
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.getLastname().sendKeys(LASTNAME);
		ccp.getSave().click();
		//Fail
		Assert.fail();
		ContactInfoPage cip = new ContactInfoPage(driver);
		String lastname = cip.getHeaderelement().getText();
		Assert.assertTrue(lastname.contains(LASTNAME));
	}

}
