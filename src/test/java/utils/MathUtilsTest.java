package utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathUtilsTest {

    @Test
    void div() {
        Assertions.assertEquals(MathUtils.div(10, 2), 5.0);
        Assertions.assertEquals(MathUtils.div(7.5, 3), 2.5);
    }
}