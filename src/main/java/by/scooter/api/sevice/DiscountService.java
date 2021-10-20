package by.scooter.api.sevice;

import by.scooter.entity.dto.pricing.DiscountDTO;

import java.util.List;

public interface DiscountService {
    DiscountDTO getById(Long id);

    List<DiscountDTO> getAll(Integer page, Integer size);

    DiscountDTO add(DiscountDTO discount);

    void remove(Long id);

    void update(Long updatedId, DiscountDTO update);
}
