package vulnerabilities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class HTMLInjectionTests extends TestBase {
    @Override
    protected String getLoginPath() {
        return "/html-injection";
    }

    @Test
    void testHackedScript(TestInfo testInfo) {
        testSubmitWithExtractedText(testInfo, "<h1>Hacked</h1>");
    }
}
