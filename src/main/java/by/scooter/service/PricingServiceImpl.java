package by.scooter.service;

import by.scooter.api.dao.DiscountDAO;
import by.scooter.api.dao.ScooterModelPricingDAO;
import by.scooter.api.sevice.PricingService;
import by.scooter.api.sevice.UtilService;
import by.scooter.dto.event.OrderCreateDTO;
import by.scooter.dto.pricing.ScooterModelPricingDTO;
import by.scooter.entity.pricing.ScooterModelPricing;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PricingServiceImpl implements PricingService {

    private final ScooterModelPricingDAO modelPricingDAO;
    private final DiscountDAO discountDAO;
    private final ModelMapper mapper;
    private final UtilService utilService;

    @Autowired
    public PricingServiceImpl(ScooterModelPricingDAO modelPricingDAO, DiscountDAO discountDAO, ModelMapper mapper, UtilService utilService) {
        this.modelPricingDAO = modelPricingDAO;
        this.discountDAO = discountDAO;
        this.mapper = mapper;
        this.utilService = utilService;
    }

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
    @Transactional
    public void removeScooterModelPricing(Long id) {
        modelPricingDAO.delete(id);
    }

    @Override
    @Transactional
    public void updateScooterModelPricing(Long updatedId, ScooterModelPricingDTO update) {
        update.setId(updatedId);
        modelPricingDAO.update(mapper.map(update, ScooterModelPricing.class));
    }

    @Override
    public Float calculatePrice(OrderCreateDTO order) {
        LocalDateTime eventStart = order.getEventStart();
        LocalDateTime eventEnd = order.getEventEnd();
        ScooterModelPricing pricing = modelPricingDAO.getByModelId(order.getScooterModelId());
        long fullDays = UtilService.getFullDays(eventStart, eventEnd);

        float res = fullDays * pricing.getDayPrice();
        eventStart = eventStart.plusDays(fullDays);

        long fullHours = UtilService.getFullHours(eventStart, eventEnd);
        res += fullHours * pricing.getHourPrice();
        eventStart = eventStart.plusHours(fullHours);

        long minutes = ChronoUnit.MINUTES.between(eventStart, eventEnd);
        res += minutes * pricing.getMinutePrice();

        if (order.getPromoCode() != null) {
            var discount = discountDAO.getByPromoCode(order.getPromoCode());
            if (discount.getExpireDate().isAfter(LocalDate.now())) {
                res *= discount.getDiscountFactor();
            }
        }

        return (float) UtilService.roundUpToXDecimal(res, 3);
    }
}
