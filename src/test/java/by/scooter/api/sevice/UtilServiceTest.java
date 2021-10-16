package by.scooter.api.sevice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilServiceTest {

    @Test
    void isDateBetweenInclusive() {
    }

    @Test
    void roundUpTo3Decimal() {
        float f = 111.1109F;
        assertEquals(111.111F, UtilService.roundUpToXDecimal(f, 3));
    }

    @Test
    void convertArray() {
    }

    @Test
    void convertList() {
    }

    @Test
    void convertSet() {
    }
}