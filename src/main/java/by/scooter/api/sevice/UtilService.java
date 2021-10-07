package by.scooter.api.sevice;

import java.time.LocalDate;
import java.util.List;

public interface UtilService {

    static boolean isDateBetweenInclusive(LocalDate exploreDate, LocalDate dateFrom, LocalDate dateTo) {
        return exploreDate.compareTo(dateFrom) * dateTo.compareTo(exploreDate) >= 0;
    }

    <T, E> List<T> convertList(List<E> list, Class<T> type);
}

