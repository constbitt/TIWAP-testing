package pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.TestInfo;
import utils.TestExceptionHandler;

import static org.junit.jupiter.api.Assertions.*;

public abstract class Page {
    protected static final String baseUrl = "https://localhost:5000";

    public Page() {
        RestAssured.useRelaxedHTTPSValidation();
    }

    public Element extractH6Element(Response response) {
        TestExceptionHandler.handleHttpError(response, null);
        
        Document doc = Jsoup.parse(response.getBody().asString());
        Element h6 = doc.selectFirst("h6.text-center.mt-3");
        assertNotNull(h6, "<h6 class=\"text-center mt-3\"> not found in response");
        return h6;
    }

    public void assertH6Contains(TestInfo testInfo, Element h6) {
        String extractedText = h6.text();
        System.out.println(testInfo.getDisplayName() + " result: " + extractedText);
        assertFalse(extractedText.isEmpty(), "Extracted <h6> is empty");
        assertNotEquals("[]", extractedText, "Extracted <h6> is '[]'");
    }

    public String extractTextBetweenHiAndHowAreYou(Response response) {
        TestExceptionHandler.handleHttpError(response, null);
        
        String startPattern = "Hi, ";
        String endPattern = ". How are you???";
        
        return TestExceptionHandler.handleTextExtraction(response, null, startPattern, endPattern);
    }

    public void assertExtractedTextBetweenHiAndHowAreYou(TestInfo testInfo, String extractedText) {
        System.out.println(testInfo.getDisplayName() + " result: " + extractedText);
        assertFalse(extractedText.isEmpty(), "Extracted text between 'Hi, ' and '. How are you???' is empty");
        assertNotEquals("[]", extractedText, "Extracted text is '[]'");
    }
} 