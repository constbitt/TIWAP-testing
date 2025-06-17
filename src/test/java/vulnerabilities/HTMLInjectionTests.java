package vulnerabilities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class HTMLInjectionTests extends TestBase {
    @Override
    protected String getEndpoint() {
        return "/html-injection";
    }

    @Test
    void testNormalScript(TestInfo testInfo) {
        testSubmitWithExtractedText(testInfo, "Ksenia");
    }

    @Test
    void testHTMLScript(TestInfo testInfo) {
        testSubmitWithExtractedText(testInfo, "<h1 Ksenia </h1>");
    }

    @Test
    void testFunnyScript(TestInfo testInfo) {
        testSubmitWithExtractedText(testInfo, "<h1 style=\"color:hotpink;\">\uD83C\uDF08 Welcome to the Hack Zone! \uD83C\uDF08</h1><marquee behavior=\"alternate\" scrollamount=\"10\" style=\"font-size:20px; color:red;\">\uD83D\uDEA8 You have been mildly surprised! \uD83D\uDEA8</marquee><img src=\"https://media.giphy.com/media/JIX9t2j0ZTN9S/giphy.gif\" width=\"300\" alt=\"Cute kittens!\" /><p style=\"font-family:Comic Sans MS;\">This is not a bug... it's a feature \uD83D\uDC31</p><button onclick=\"alert('You clicked the kitten trap \uD83D\uDE3C')\">Click me if you love cats</button>\n");
    }
}
