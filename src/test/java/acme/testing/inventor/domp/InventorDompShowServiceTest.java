package acme.testing.inventor.domp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorDompShowServiceTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/domp/domp-show.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positiveTest(final int recordIndex, final String code, final String subject, final String summary,final String helping,
		final String creationMoment,final String startDate,final String endDate, final String furtherInfo) {
		super.signIn("inventor1", "inventor1");
	
		super.clickOnMenu("Inventor", "List my domps");
		
		super.sortListing(3, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("subject",subject);
		super.checkInputBoxHasValue("summary",summary);
		super.checkInputBoxHasValue("helping", helping);
		super.checkInputBoxHasValue("creationMoment",creationMoment);
		super.checkInputBoxHasValue("startDate",startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("furtherInfo", furtherInfo);
		
		super.signOut();
	}
	

}
