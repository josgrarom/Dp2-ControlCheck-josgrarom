package acme.testing.inventor.domp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorDompListTest  extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/chimpum-list.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positiveTest(final int recordIndex, final String code, final String subject,final String helping,
		final String startDate,final String endDate) {
		super.signIn("inventor1", "inventor1");
	
		super.clickOnMenu("Inventor", "List my chimpums");
		super.checkListingExists();
		super.sortListing(3, "asc");
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, subject);
		super.checkColumnHasValue(recordIndex, 2, helping);
		super.checkColumnHasValue(recordIndex, 3, startDate);
		super.checkColumnHasValue(recordIndex, 4, endDate);
	}

}
