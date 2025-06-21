package vulnerabilities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class BusinessLogicFlawTests extends TestBase {

    @Override
    protected String getEndpoint() {
        return "/business-logic";
    }

    @Test
    void testCommonCredentials1(TestInfo testInfo) {
        testLogin(testInfo, "admin", "admin");
    }

    @Test
    void testCommonCredentials2(TestInfo testInfo) {
        testLogin(testInfo, "admin", "21232f297a57a5a743894a0e4a801fc3");
    }

    @Test
    void testEmptyCredentials(TestInfo testInfo) {
        testLogin(testInfo, "", "");
    }

    @Test
    void testUsernameCatherine(TestInfo testInfo) {
        testLogin(testInfo, "catherine", "");
    }

    @Test
    void testPasswordCatherine(TestInfo testInfo) {
        testLogin(testInfo, "", "catherine");
    }

    @Test
    void testLoginPasswordCatherine(TestInfo testInfo) {
        testLogin(testInfo, "catherine", "catherine");
    }
}

