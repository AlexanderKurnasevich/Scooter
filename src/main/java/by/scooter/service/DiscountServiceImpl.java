package by.scooter.service;

import by.scooter.api.dao.DiscountDAO;
import by.scooter.api.sevice.DiscountService;
import by.scooter.api.sevice.UtilService;
import by.scooter.entity.dto.pricing.DiscountDTO;
import by.scooter.entity.pricing.Discount;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final DiscountDAO discountDAO;
    private final ModelMapper mapper;
    private final UtilService utilService;

    @Override
    public DiscountDTO getById(Long id) {
        return mapper.map(discountDAO.getById(id), DiscountDTO.class);
    }

    @Override
    public List<DiscountDTO> getAll(Integer page, Integer size) {
        return utilService.convertList(discountDAO.getAll(page, size), DiscountDTO.class);
    }

    @Override
    @Transactional
    public DiscountDTO add(DiscountDTO discount) {
        return mapper.map(discountDAO.save(mapper.map(discount, Discount.class)), DiscountDTO.class);
    }

    @Override
    public void remove(Long id) {
        discountDAO.delete(id);
    }

    @Override
    @Transactional
    public void update(Long updatedId, DiscountDTO update) {
        Discount updated = discountDAO.getById(updatedId);
        Optional.ofNullable(update.getDiscountFactor()).ifPresent(updated::setDiscountFactor);
        Optional.ofNullable(update.getExpireDate()).ifPresent(updated::setExpireDate);
        Optional.ofNullable(update.getPromoCode()).ifPresent(updated::setPromoCode);
        discountDAO.update(updated);
    }
}
