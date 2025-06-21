package vulnerabilities;

import io.restassured.filter.cookie.CookieFilter;
import io.restassured.response.Response;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.TestInfo;
import pages.LoginPage;
import pages.SubmitPage;
import utils.TestExceptionHandler;

public abstract class TestBase {
    LoginPage loginPage = new LoginPage();
    SubmitPage submitPage = new SubmitPage();
    CookieFilter session = loginPage.loginAsAdmin();

    protected abstract String getEndpoint();

    protected void testLogin(TestInfo testInfo, String username, String password) {
        Response response = sendLoginRequest(username, password);
        TestExceptionHandler.handleAuthenticationError(response, testInfo);
        
        Element h6 = loginPage.extractH6Element(response);
        loginPage.assertH6Contains(testInfo, h6);
    }

    protected void testSubmit(TestInfo testInfo, String input) {
        Response response = submitRequest(input);
        Element h6 = submitPage.extractH6Element(response);
        submitPage.assertH6Contains(testInfo, h6);
    }

    protected void testSubmitWithExtractedText(TestInfo testInfo, String input) {
        Response response = submitRequest(input);
        String extractedText = submitPage.extractTextBetweenHiAndHowAreYou(response);
        submitPage.assertExtractedTextBetweenHiAndHowAreYou(testInfo, extractedText);
    }

    protected Response sendLoginRequest(String username, String password) {
        Response response = loginPage.login(getEndpoint(), username, password, session);
        TestExceptionHandler.handleHttpError(response, null);
        return response;
    }

    protected Response submitRequest(String input) {
        Response response = submitPage.submitRequest(getEndpoint(), input, session);
        TestExceptionHandler.handleHttpError(response, null);
        return response;
    }
}
