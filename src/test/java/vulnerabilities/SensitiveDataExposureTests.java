package vulnerabilities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SensitiveDataExposureTests extends TestBase {

    @Override
    protected String getEndpoint() {
        return "/sensitive-data-exposure";
    }

    @Test
    void testPasswordVariants(TestInfo testInfo) {
        String[] guesses = {
                "admin123", "administrator", "admin@123",
                "56fUi5MggETn9cuC1d",
                "56fUi5MggETn9cuC1d!".toLowerCase(),
                "Sakshi2024", "Sakshi2025", "Yash@123"
        };

        for (String guess : guesses) {
            testLogin(testInfo, "adM1n1sTrat0R", guess);
        }
    }

    @Test
    void testBase64DecodedPassword(TestInfo testInfo) {
        String decoded = new String(Base64.getDecoder().decode("56fUi5MggETn9cuC1d"));
        testLogin(testInfo, "adM1n1sTrat0R", decoded);
    }

    @Test
    void testBase62DecodedPassword(TestInfo testInfo) {
        String decoded = base62Decode("56fUi5MggETn9cuC1d");
        testLogin(testInfo, "adM1n1sTrat0R", decoded);
    }

    @Test
    void testBase36DecodedPassword(TestInfo testInfo) {
        String decoded = base36Decode("56fUi5MggETn9cuC1d");
        testLogin(testInfo, "adM1n1sTrat0R", decoded);
    }

    @Test
    void testBase91DecodedPassword(TestInfo testInfo) {
        String decoded = base91Decode("56fUi5MggETn9cuC1d");
        testLogin(testInfo, "adM1n1sTrat0R", decoded);
    }

    @Test
    void testBase58DecodedPassword(TestInfo testInfo) {
        String decoded = base58Decode("56fUi5MggETn9cuC1d");
        System.out.println(decoded);
        testLogin(testInfo, "adM1n1sTrat0R", decoded);
    }


    private String base62Decode(String input) {
        String base62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        BigInteger value = BigInteger.ZERO;
        for (char c : input.toCharArray()) {
            value = value.multiply(BigInteger.valueOf(62));
            value = value.add(BigInteger.valueOf(base62.indexOf(c)));
        }
        return new String(value.toByteArray(), StandardCharsets.UTF_8);
    }

    private String base36Decode(String input) {
        return new BigInteger(input, 36).toString(10);
    }

    private String base91Decode(String input) {
        StringBuilder result = new StringBuilder();
        int b = 0, n = 0, v = -1;
        for (char c : input.toCharArray()) {
            int p = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!#$%&()*+,./:;<=>?@[]^_`{|}~\"".indexOf(c);
            if (p == -1) continue;
            if (v < 0) {
                v = p;
            } else {
                v += p * 91;
                b |= v << n;
                n += (v & 8191) > 88 ? 13 : 14;
                do {
                    result.append((char) (b & 255));
                    b >>= 8;
                    n -= 8;
                } while (n > 7);
                v = -1;
            }
        }
        if (v + 1 > 0) {
            result.append((char) ((b | v << n) & 255));
        }
        return result.toString();
    }

    private String base58Decode(String input) {
        String base58 = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
        BigInteger value = BigInteger.ZERO;
        for (char c : input.toCharArray()) {
            value = value.multiply(BigInteger.valueOf(58));
            value = value.add(BigInteger.valueOf(base58.indexOf(c)));
        }
        return new String(value.toByteArray(), StandardCharsets.UTF_8);
    }
}

