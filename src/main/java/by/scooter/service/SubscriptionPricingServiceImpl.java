package by.scooter.service;

import by.scooter.api.dao.SubscriptionPricingDAO;
import by.scooter.api.sevice.SubscriptionPricingService;
import by.scooter.api.sevice.UtilService;
import by.scooter.dto.pricing.SubscriptionDTO;
import by.scooter.dto.pricing.SubscriptionPricingDTO;
import by.scooter.entity.pricing.SubscriptionPricing;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;

@Service
public class SubscriptionPricingServiceImpl implements SubscriptionPricingService {

    private final SubscriptionPricingDAO pricingDAO;
    private final ModelMapper mapper;
    private final UtilService utilService;

    @Autowired
    public SubscriptionPricingServiceImpl(SubscriptionPricingDAO pricingDAO, ModelMapper mapper,
                                          UtilService utilService) {
        this.pricingDAO = pricingDAO;
        this.mapper = mapper;
        this.utilService = utilService;
    }

    @Override
    public Float calculatePrice(SubscriptionDTO dto) {
        return (float) UtilService
                .roundUpToXDecimal(pricingDAO.getByUnit(dto.getUnit()).getPrice() * dto.getQuantity(), 3);
    }

    @Override
    public SubscriptionPricingDTO getByUnit(String unit) {
        return mapper.map(pricingDAO.getByUnit(ChronoUnit.valueOf(unit.toUpperCase(Locale.ROOT))),
                SubscriptionPricingDTO.class);
    }

    @Override
    public List<SubscriptionPricingDTO> getAll(Integer page, Integer size) {
        return utilService.convertList(pricingDAO.getAll(page, size), SubscriptionPricingDTO.class);
    }

    @Override
    @Transactional
    public SubscriptionPricingDTO addSubscriptionPricing(SubscriptionPricingDTO pricing) {
        return mapper
                .map(pricingDAO.save(mapper.map(pricing, SubscriptionPricing.class)), SubscriptionPricingDTO.class);
    }

    @Override
    @Transactional
    public void updateSubscriptionPricing(Long id, SubscriptionPricingDTO pricing) {
        SubscriptionPricing updated = pricingDAO.getById(id);
        updated.setPrice(pricing.getPrice());
        pricingDAO.update(updated);
    }

    @Override
    @Transactional
    public void removeSubscriptionPricing(Long id) {
        pricingDAO.delete(id);
    }
}
