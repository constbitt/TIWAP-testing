package vulnerabilities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class SqlInjectionTests extends TestBase {

    @Override
    protected String getLoginPath() {
        return "/sql-injection";
    }

    @Test
    void testNormalLogin(TestInfo testInfo) {
        testLogin(testInfo, "admin", "admin");
    }

    @Test
    void testNoPasswordLogin(TestInfo testInfo) {
        testLogin(testInfo, "johndoe", "");
    }

    @Test
    void testInjectionUsernameField(TestInfo testInfo) {
        testLogin(testInfo, "admin' --", "irrelevant");
    }

    @Test
    void testOrAlwaysTrueInjection(TestInfo testInfo) {
        testLogin(testInfo, "admin' OR '1'='1", "irrelevant");
    }

    @Test
    void testCommentInjection(TestInfo testInfo) {
        testLogin(testInfo, "' OR 1=1 --", "irrelevant");
    }

    @Test
    void testInjectionInPasswordField(TestInfo testInfo) {
        testLogin(testInfo, "admin", "' OR '1'='1");
    }

    @Test
    void testSubqueryInjection(TestInfo testInfo) {
        testLogin(testInfo, "' OR (SELECT COUNT(*) FROM users) > 0 --", "irrelevant");
    }

    @Test
    void testSqlError(TestInfo testInfo) {
        testLogin(testInfo, "admin' Tralalero Tralala", "");
    }

    @Test
    void testSqlSleepError(TestInfo testInfo) {
        testLogin(testInfo, "admin' OR SLEEP(5) --", "");
    }
}
