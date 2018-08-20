package utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringUtilsTest {

    @Test
    void removeAccents() {
        Assertions.assertEquals(StringUtils.removeAccents("Jóel"), "Joel");
    }

    @Test
    void sanitize() {
        Assertions.assertEquals(StringUtils.sanitize("Jóel"), "joel");
    }
}