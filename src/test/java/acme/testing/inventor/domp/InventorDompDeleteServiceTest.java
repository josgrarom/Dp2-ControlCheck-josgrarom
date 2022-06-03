package acme.testing.inventor.domp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorDompDeleteServiceTest extends TestHarness {
	
	@ParameterizedTest
	@Order(10)
	@CsvFileSource(resources = "/inventor/domp/domp-delete.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positiveTest(final int recordIndex,final String pattern, final String subject, final String summary,final String helping
		,final String startDate,final String endDate, final String futherInfo){
		
		
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
		super.fillInputBoxIn("futherInfo", futherInfo);
		
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Inventor", "List my domps");
		
		super.sortListing(1, "asc");
		super.clickOnListingRecord(0);
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();

		
		super.signOut();
	
		
		
	}

}
