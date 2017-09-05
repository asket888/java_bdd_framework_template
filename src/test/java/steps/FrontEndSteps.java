package steps;


import cucumber.api.java.en.And;
import org.junit.Assert;
import pages.FrontEndPage;
import utils.ElementManagementUtil;

import java.util.List;

import static pages.FrontEndPage.getDefaultPageElements;

public class FrontEndSteps {

    private FrontEndPage frontEndPage = new FrontEndPage();
    private ElementManagementUtil elementManagementUtil = new ElementManagementUtil();

    @And("^Checks all following fields display on Page3 page$")
    public void checkAllElementsDisplayOnUpdateCertificatePage(List<String> expectedFields) {

        List<String> actualFields = elementManagementUtil
                .getAllElementsTextAsStringList(getDefaultPageElements());

        Assert.assertEquals("Unexpected element or missed element", expectedFields, actualFields);
    }
}
