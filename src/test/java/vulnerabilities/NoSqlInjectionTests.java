package vulnerabilities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class NoSqlInjectionTests extends TestBase {

    @Override
    protected String getEndpoint() {
        return "/no-sql-injection";
    }

    @Test
    void testUsernameNeNull(TestInfo testInfo) {
        testLogin(testInfo, "{\"$ne\": null}", "admin");
    }

    @Test
    void testPasswordNeNull(TestInfo testInfo) {
        testLogin(testInfo, "admin", "{\"$ne\": null}");
    }

    @Test
    void testPasswordRegex(TestInfo testInfo) {
        testLogin(testInfo, "admin", "{\"$regex\": \".*\"}");
    }

    @Test
    void testUsernameRegex(TestInfo testInfo) {
        testLogin(testInfo, "{\"$regex\": \".*\"}", "admin");
    }

    @Test
    void testUsernameGtEmpty(TestInfo testInfo) {
        testLogin(testInfo, "{\"$gt\": \"\"}", "admin");
    }

    @Test
    void testPasswordGtEmpty(TestInfo testInfo) {
        testLogin(testInfo, "admin", "{\"$gt\": \"\"}");
    }

    @Test
    void testUsernameNeAdmin(TestInfo testInfo) {
        testLogin(testInfo, "{\"$ne\": \"admin\"}", "admin");
    }

    @Test
    void testPasswordNeAdmin(TestInfo testInfo) {
        testLogin(testInfo, "admin", "{\"$ne\": \"admin\"}");
    }

    @Test
    void testAny(TestInfo testInfo) {
        testLogin(testInfo, "{\"$regex\": \".*\"}", "{\"$regex\": \".*\"}");
    }
}
