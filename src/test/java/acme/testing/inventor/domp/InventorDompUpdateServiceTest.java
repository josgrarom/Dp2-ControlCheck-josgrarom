package acme.testing.inventor.domp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorDompUpdateServiceTest extends TestHarness {
	
	@ParameterizedTest
	@Order(10)
	@CsvFileSource(resources = "/inventor/domp/domp-update.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positiveTest(final int recordIndex, final String pattern,final String subject, final String summary,final String helping
		,final String startDate,final String endDate, final String furtherInfo){
				
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my domps");
		super.sortListing(1, "asc");
		super.clickOnListingRecord(0);	
		super.fillInputBoxIn("pattern", pattern);
		super.fillInputBoxIn("subject", subject);
		super.fillInputBoxIn("summary", summary);
		super.fillInputBoxIn("helping", helping);
		super.fillInputBoxIn("startDate",startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("furtherInfo", furtherInfo);
		
		super.clickOnSubmit("Update");
	
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/domp/domp-update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void negativeTest(final int recordIndex, final String pattern,final String subject, final String summary,final String helping
		,final String startDate,final String endDate, final String furtherInfo) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my domps");
		super.sortListing(1, "asc");
		super.clickOnListingRecord(0);
		super.fillInputBoxIn("pattern", pattern);
		super.fillInputBoxIn("subject", subject);
		super.fillInputBoxIn("summary", summary);
		super.fillInputBoxIn("helping", helping);
		super.fillInputBoxIn("startDate",startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("furtherInfo", furtherInfo);
		
		super.clickOnSubmit("Update");
		super.checkErrorsExist();

		
		super.signOut();
	}


}
