package by.scooter.api.sevice;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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
    void getRoundedDays() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime time = LocalDateTime.now().plusDays(1).plusHours(22).plusMinutes(10);
        assertEquals(2, UtilService.getRoundedDays(now, time));
    }

    @Test
    void getRoundedHours() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime time = LocalDateTime.now().plusDays(1).plusHours(22).plusMinutes(10);
        assertEquals(47, UtilService.getRoundedHours(now, time));
    }
}