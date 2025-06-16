package pages;

import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.response.Response;

public class LoginPage extends Page {
    public LoginPage() {
        super();
    }

    public Response login(String endpoint, String username, String password, CookieFilter session) {
        return RestAssured.given()
                .filter(session)
                .formParam("username", username)
                .formParam("password", password)
                .post(baseUrl + endpoint);
    }

    public CookieFilter loginAsAdmin() {
        CookieFilter cookieFilter = new CookieFilter();
        login("/login", "admin", "admin", cookieFilter);
        return cookieFilter;
    }
}
