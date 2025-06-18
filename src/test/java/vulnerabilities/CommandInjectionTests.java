package vulnerabilities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class CommandInjectionTests extends TestBase {

    @Override
    protected String getEndpoint() {
        return "/cmd-injection";
    }

    @Test
    void testSemicolonWithLs(TestInfo testInfo) {
        testSubmit(testInfo, "; ls");
    }

    @Test
    void testPipeWithWhoami(TestInfo testInfo) {
        testSubmit(testInfo, "; whoami");
    }

    @Test
    void testSemicolonEcho(TestInfo testInfo) {
        testSubmit(testInfo, "; echo injected");
    }

    @Test
    void testPipeWithId(TestInfo testInfo) {
        testSubmit(testInfo, "; id");
    }

    @Test
    void testSemicolonCatEtcPasswd(TestInfo testInfo) {
        testSubmit(testInfo, "; cat /etc/passwd");
    }
}
