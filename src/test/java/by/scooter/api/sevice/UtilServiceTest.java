package by.scooter.api.sevice;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class UtilServiceTest {

    @Test
    void roundUpTo3Decimal() {
        float f = 111.1109F;
        assertEquals(111.111F, UtilService.roundUpToXDecimal(f, 3));
        float f2 = 990.0999755859375F;
        assertEquals(990.1F, UtilService.roundUpToXDecimal(f2, 3), 0.001);
    }

    @Test
    void getFullDays(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime time = LocalDateTime.now().plusDays(1).plusHours(22).plusMinutes(10);
        assertEquals(1, UtilService.getFullDays(now, time));
    }

    @Test
    void getRoundedDays() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime time = LocalDateTime.now().plusDays(1);
        assertEquals(1, UtilService.getRoundedDays(now, time));
        time = time.plusHours(1);
        assertEquals(2, UtilService.getRoundedDays(now, time));
        time = time.plusHours(23).plusMinutes(1);
        assertEquals(3, UtilService.getRoundedDays(now, time));
    }

    @Test
    void getFullHours(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime time = LocalDateTime.now().plusHours(22).plusMinutes(10);
        assertEquals(22, UtilService.getFullHours(now, time));
    }

    @Test
    void getRoundedHours() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime time = LocalDateTime.now().plusDays(1).plusHours(22);
        assertEquals(46, UtilService.getRoundedHours(now, time));
        time = time.plusMinutes(10);
        assertEquals(47, UtilService.getRoundedHours(now, time));
    }
}