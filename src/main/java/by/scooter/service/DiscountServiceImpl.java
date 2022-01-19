package by.scooter.service;

import by.scooter.api.dao.DiscountDAO;
import by.scooter.api.sevice.DiscountService;
import by.scooter.api.sevice.UtilService;
import by.scooter.dto.pricing.DiscountDTO;
import by.scooter.entity.pricing.Discount;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountDAO discountDAO;
    private final ModelMapper mapper;
    private final UtilService utilService;

    @Autowired
    public DiscountServiceImpl(DiscountDAO discountDAO, ModelMapper mapper, UtilService utilService) {
        this.discountDAO = discountDAO;
        this.mapper = mapper;
        this.utilService = utilService;
    }

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
    @Transactional
    public void remove(Long id) {
        discountDAO.delete(id);
    }

    @Override
    @Transactional
    public void update(Long updatedId, DiscountDTO update) {
        update.setId(updatedId);
        discountDAO.update(mapper.map(update, Discount.class));
    }
}
