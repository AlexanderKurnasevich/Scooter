package by.scooter.api.sevice;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface UtilService {

    static boolean isDateBetweenInclusive(LocalDate exploreDate, LocalDate dateFrom, LocalDate dateTo) {
        return exploreDate.compareTo(dateFrom) * dateTo.compareTo(exploreDate) >= 0;
    }

    <T, E> List<T> convertArray(Object[] array, Class<T> type);
    <T, E> List<T> convertList(List<E> list, Class<T> type);
    <T, E> Set<T> convertSet(Set<E> set, Class<T> type);

}

