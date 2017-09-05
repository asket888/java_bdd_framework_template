package steps.setup;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public class BaseTest extends BaseDriver {

    @BeforeClass
    public static void initSelenium(){
        getDriver();
    }

    @AfterClass
    public static void closeSelenium(){
        closeDriver();
    }
}
