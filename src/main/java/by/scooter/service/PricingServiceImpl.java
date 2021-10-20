package by.scooter.service;

import by.scooter.api.dao.DiscountDAO;
import by.scooter.api.dao.ScooterModelPricingDAO;
import by.scooter.api.sevice.PricingService;
import by.scooter.api.sevice.UtilService;
import by.scooter.entity.dto.event.OrderCreateDTO;
import by.scooter.entity.dto.pricing.ScooterModelPricingDTO;
import by.scooter.entity.pricing.ScooterModelPricing;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PricingServiceImpl implements PricingService {

    private final ScooterModelPricingDAO modelPricingDAO;
    private final DiscountDAO discountDAO;
    private final ModelMapper mapper;
    private final UtilService utilService;

    @Override
    public ScooterModelPricingDTO getByModelId(Long modelId) {
        return mapper.map(modelPricingDAO.getByModelId(modelId), ScooterModelPricingDTO.class);
    }

    @Override
    public ScooterModelPricingDTO getById(Long id) {
        return mapper.map(modelPricingDAO.getById(id), ScooterModelPricingDTO.class);
    }

    @Override
    public List<ScooterModelPricingDTO> getAll(Integer page, Integer size) {
        return utilService.convertList(modelPricingDAO.getAll(page, size), ScooterModelPricingDTO.class);
    }

    @Override
    @Transactional
    public ScooterModelPricingDTO addScooterModelPricing(ScooterModelPricingDTO pricing) {
        return mapper.map(modelPricingDAO.save(mapper.map(pricing, ScooterModelPricing.class)),
                ScooterModelPricingDTO.class);
    }

    @Override
    public void removeScooterModelPricing(Long id) {
        modelPricingDAO.delete(id);
    }

    @Override
    @Transactional
    public void updateScooterModelPricing(Long updatedId, ScooterModelPricingDTO update) {
        ScooterModelPricing updated = modelPricingDAO.getById(updatedId);
        Optional.of(update.getMinutePrice()).ifPresent(updated::setMinutePrice);
        Optional.of(update.getHourPrice()).ifPresent(updated::setHourPrice);
        Optional.of(update.getDayPrice()).ifPresent(updated::setDayPrice);
    }

    @Override
    public Float calculatePrice(OrderCreateDTO order, String promoCode) {
        long minutes = ChronoUnit.MINUTES.between(order.getEventStart(), order.getEventEnd());
        float res = minutes * getByModelId(order.getScooterModelId()).getMinutePrice();
        if (promoCode != null) {
            var discount = discountDAO.getByPromoCode(promoCode);
            if (discount.getExpireDate().isAfter(LocalDate.now())) {
                res *= discount.getDiscountFactor();
            }
        }
        return (float) UtilService.roundUpToXDecimal(res, 3);
    }
}
