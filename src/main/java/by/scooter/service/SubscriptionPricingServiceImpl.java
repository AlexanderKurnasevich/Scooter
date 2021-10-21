package by.scooter.service;

import by.scooter.api.dao.SubscriptionPricingDAO;
import by.scooter.api.sevice.SubscriptionPricingService;
import by.scooter.api.sevice.UtilService;
import by.scooter.entity.dto.pricing.SubscriptionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionPricingServiceImpl implements SubscriptionPricingService {
    private final SubscriptionPricingDAO pricingDAO;

    @Override
    public Float calculatePrice(SubscriptionDTO dto) {
        return (float) UtilService
                .roundUpToXDecimal(pricingDAO.getByUnit(dto.getUnit()).getPrice() * dto.getQuantity(), 3);
    }
}
