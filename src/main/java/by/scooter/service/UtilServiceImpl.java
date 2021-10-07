package by.scooter.service;

import by.scooter.api.sevice.UtilService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UtilServiceImpl implements UtilService {
    private final ModelMapper mapper;

    @Override
    public <T, E> List<T> convertList(List<E> list, Class<T> type) {
        return list.stream().map(obj -> mapper.map(obj, type)).collect(Collectors.toList());
    }
}
