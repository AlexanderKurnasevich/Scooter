package by.scooter.service;

import by.scooter.api.dao.SubscriptionPricingDAO;
import by.scooter.api.sevice.SubscriptionPricingService;
import by.scooter.api.sevice.UtilService;
import by.scooter.entity.dto.pricing.SubscriptionDTO;
import by.scooter.entity.dto.pricing.SubscriptionPricingDTO;
import by.scooter.entity.pricing.SubscriptionPricing;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class SubscriptionPricingServiceImpl implements SubscriptionPricingService {

    private final SubscriptionPricingDAO pricingDAO;
    private final ModelMapper mapper;
    private final UtilService utilService;

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
    public void removeSubscriptionPricing(Long id) {
        pricingDAO.delete(id);
    }
}
