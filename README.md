# TIWAP-testing project documentation
## Project under test
Totally Insecure Web Application Project (TIWAP) - web security testing lab made using Flask for budding security enthusiasts to learn about various web vulnerabilities. 
## Running TIWAP
To execute TIWAP run these commands in Linux:

```bash
git clone https://github.com/tombstoneghost/TIWAP
cd TIWAP
sudo docker-compose up
```

## TIWAP-testing project structure

- `pages/`: Page object models for tests
- `vulnerabilities/`: Test cases for different vulnerabilities
- `utils/`: Error handling

## Running tests

To execute all tests:

```bash
git clone https://github.com/constbitt/TIWAP-testing
cd TIWAP-testing
mvn test
```

## Class descriptions

### pages/Page.java
An abstract base class for page objects used in tests.  
- Stores the base URL for requests.
- Sets up relaxed HTTPS validation for REST Assured.
- Provides utility methods for extracting and asserting HTML elements from HTTP responses.
- Contains methods for extracting specific text patterns from responses.

---

### pages/LoginPage.java
Represents the login page for testing purposes.  
- Inherits from `Page`.
- Provides methods to perform login requests with given credentials.
- Includes a helper method to log in as an admin user and return a session cookie.

---

### pages/SubmitPage.java
Represents a submit page for testing input submission.  
- Inherits from `Page`.
- Provides a method to submit input data to a specified endpoint using a session.

---

### utils/TestExceptionHandler.java
Class for handling exceptions and errors during tests.  
- Provides static methods to handle HTTP errors, authentication errors, and text extraction from responses.

---

### vulnerabilities/TestBase.java  
Abstract base class for all vulnerability test classes.  
- Initializes `LoginPage`, `SubmitPage`, and an admin session.
- Declares an abstract method `getEndpoint()` for subclasses to specify the tested lab endpoint.
- Provides utility methods for common test actions: login, submit, and text extraction.
- Handles HTTP and authentication errors using `TestExceptionHandler`.
---

### vulnerabilities/SqlInjectionTests.java 
Test class for classic SQL injection vulnerabilities.  
- Inherits from `TestBase`.
- Specifies the `/sql-injection` endpoint.
- Contains tests for various SQL injection scenarios in username and password fields, including error-based and time-based injections.
---

### vulnerabilities/BlindSqlInjectionTests.java  
Test class for blind SQL injection vulnerabilities.  
- Inherits from `TestBase`.
- Specifies the `/blind-sql-injection` endpoint.
- Contains tests for guessing password length, specific characters, and the full password using blind SQL injection techniques.

---

### vulnerabilities/NoSqlInjectionTests.java
Test class for NoSQL injection vulnerabilities.  
- Inherits from `TestBase`.
- Specifies the `/no-sql-injection` endpoint.
- Contains tests for different NoSQL injection payloads in username and password fields.

---

### vulnerabilities/CommandInjectionTests.java
Test class for command injection vulnerabilities.  
- Inherits from `TestBase`.
- Specifies the `/cmd-injection` endpoint.
- Contains tests for various command injection payloads (e.g., using `;`, `|`, and common shell commands).

---

### vulnerabilities/HTMLInjectionTests.java
Test class for HTML injection vulnerabilities.  
- Inherits from `TestBase`.
- Specifies the `/html-injection` endpoint.
- Contains tests for injecting HTML and JavaScript code into the application.

---

### vulnerabilities/BusinessLogicFlawTests.java 
Test class for business logic flaw vulnerabilities.  
- Inherits from `TestBase`.
- Specifies the `/business-logic` endpoint.
- Contains tests for weak or common credentials, empty credentials, and other business logic issues.

---

### vulnerabilities/SensitiveDataExposureTests.java 
Test class for sensitive data exposure vulnerabilities.  
- Inherits from `TestBase`.
- Specifies the `/sensitive-data-exposure` endpoint.
- Contains tests for leaked credentials, encoded passwords (Base64, Base62, Base36, Base91, Base58), and common password variants.
- Implements helper methods for decoding various encodings.

---

## Sources
* TIWAP https://github.com/tombstoneghost/TIWAP
* Clean code https://www.freecodecamp.org/news/how-to-write-clean-code
* SQL Injection https://portswigger.net/web-security/sql-injection
* Blind SQL Injection https://owasp.org/www-community/attacks/Blind_SQL_Injection
* No SQL Injection https://portswigger.net/web-security/nosql-injection
* Command Injection https://portswigger.net/web-security/os-command-injection
* Business Logic Flaw https://portswigger.net/web-security/logic-flaws
* Sensitive Data Exposure https://owasp.org/www-project-top-ten/2017/A3_2017-Sensitive_Data_Exposure
* Security Misconfiguration https://owasp.org/Top10/A05_2021-Security_Misconfiguration/
* HTML Injection https://www.invicti.com/learn/html-injection/
* Base encodings https://code.tutsplus.com/base-what-a-practical-introduction-to-base-encoding--net-27590t


## Dependencies
- **JUnit 5 (for testing)**
  - `org.junit.jupiter:junit-jupiter-api:5.10.0`
  - `org.junit.jupiter:junit-jupiter-engine:5.10.0`
- **REST Assured (for API testing)**
  - `io.rest-assured:rest-assured:5.4.0`
- **jsoup (for HTML parsing in tests)**
  - `org.jsoup:jsoup:1.17.2`
- **Maven Surefire Plugin (for running tests)**
  - `org.apache.maven.plugins:maven-surefire-plugin:3.1.2`
