package utils;

import io.restassured.response.Response;
import org.junit.jupiter.api.TestInfo;

public class TestExceptionHandler {

    public static String handleTextExtraction(Response response, TestInfo testInfo, String startPattern, String endPattern) {
        String bodyText = response.getBody().asString();
        if (bodyText == null || bodyText.isEmpty()) {
            throw new RuntimeException("Response body is empty");
        }

        int startIndex = bodyText.indexOf(startPattern);
        int endIndex = bodyText.indexOf(endPattern);

        if (startIndex == -1) {
            throw new RuntimeException("Start pattern not found: " + startPattern);
        }

        if (endIndex == -1) {
            throw new RuntimeException("End pattern not found: " + endPattern);
        }

        if (startIndex >= endIndex) {
            throw new RuntimeException("Start pattern appears after end pattern");
        }

        return bodyText.substring(startIndex + startPattern.length(), endIndex);
    }

    public static void handleHttpError(Response response, TestInfo testInfo) {
        int statusCode = response.getStatusCode();
        if (statusCode >= 400) {
            String errorMessage = String.format("HTTP Error %d: %s", statusCode, response.getStatusLine());
            
            if (statusCode >= 500) {
                throw new RuntimeException("Server error: " + errorMessage);
            } else if (statusCode == 404) {
                throw new RuntimeException("Endpoint not found: " + errorMessage);
            } else if (statusCode == 403) {
                throw new RuntimeException("Access forbidden: " + errorMessage);
            }
        }
    }

    public static void handleAuthenticationError(Response response, TestInfo testInfo) {
        String bodyText = response.getBody().asString();
        if (bodyText.contains("Invalid credentials") || bodyText.contains("Login failed")) {
        }
    }
} 