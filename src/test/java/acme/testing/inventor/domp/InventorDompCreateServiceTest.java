package acme.testing.inventor.domp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorDompCreateServiceTest extends TestHarness {
	
	@ParameterizedTest
	@Order(10)
	@CsvFileSource(resources = "/inventor/domp/domp-create.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positiveTest(final int recordIndex, final String pattern,final String subject, final String summary,final String helping
		,final String startDate,final String endDate, final String furtherInfo){
		
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my items");
		super.sortListing(2, "asc");
		super.clickOnListingRecord(0);
		
		super.clickOnButton("Create domp");
		super.fillInputBoxIn("pattern", pattern);
		super.fillInputBoxIn("subject", subject);
		super.fillInputBoxIn("summary", summary);
		super.fillInputBoxIn("helping", helping);
		super.fillInputBoxIn("startDate",startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("furtherInfo", furtherInfo);
		
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Inventor", "List my domps");

		super.sortListing(1, "asc");
		super.checkColumnHasValue(recordIndex, 1, subject);
		super.checkColumnHasValue(recordIndex, 2, helping);
		super.checkColumnHasValue(recordIndex, 3, startDate);
		super.checkColumnHasValue(recordIndex, 4, endDate);

		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/domp/domp-create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void negativeTest(final int recordIndex,final String pattern, final String subject, final String summary,final String helping
		,final String startDate,final String endDate, final String furtherInfo) {
		
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my items");
		super.sortListing(2, "asc");
		super.clickOnListingRecord(0);
		
		super.clickOnButton("Create domp");
		super.fillInputBoxIn("pattern", pattern);
		super.fillInputBoxIn("subject", subject);
		super.fillInputBoxIn("summary", summary);
		super.fillInputBoxIn("helping", helping);
		super.fillInputBoxIn("startDate",startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("murtherInfo", furtherInfo);
		
		super.clickOnSubmit("Create");
		super.checkErrorsExist();

		
		super.signOut();
	}

}
