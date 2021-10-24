package by.scooter.api.sevice;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface UtilService {

    static double roundUpToXDecimal(double n, int x) {
        return Math.round(n * Math.pow(10, x)) / ((float) Math.pow(10, x));
    }

    static long getRoundedDays(LocalDateTime dateFrom, LocalDateTime dateTo){
        long res = ChronoUnit.DAYS.between(dateFrom, dateTo);
        dateFrom = dateFrom.plusDays(res);

        if(ChronoUnit.HOURS.between(dateFrom, dateTo) > 0) {
            return ++res;
        }

        if(ChronoUnit.MINUTES.between(dateFrom, dateTo) > 0) {
            return ++res;
        }

        return res;
    }

    static long getRoundedHours(LocalDateTime dateFrom, LocalDateTime dateTo){
        long res = ChronoUnit.HOURS.between(dateFrom, dateTo);
        dateFrom = dateFrom.plusHours(res);

        if(ChronoUnit.MINUTES.between(dateFrom, dateTo) > 0) {
            return ++res;
        }

        return res;
    }

    <T, E> List<T> convertList(List<E> list, Class<T> type);
}

