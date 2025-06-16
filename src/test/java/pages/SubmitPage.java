package pages;

import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.response.Response;

public class SubmitPage extends Page {
    public SubmitPage() {
        super();
    }

    public Response submitRequest(String endpoint, String input, CookieFilter session) {
        return RestAssured.given()
                .filter(session)
                .formParam("input", input)
                .post(baseUrl + endpoint);
    }
}
