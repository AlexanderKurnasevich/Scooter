package by.scooter.service;

import by.scooter.api.dao.RentPointPricingDAO;
import by.scooter.api.dao.ScooterModelPricingDAO;
import by.scooter.api.dao.SpecialModelPricingDAO;
import by.scooter.api.sevice.PricingService;
import by.scooter.entity.pricing.RentPointPricing;
import by.scooter.entity.pricing.ScooterModelPricing;
import by.scooter.entity.pricing.SpecialModelPricing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PricingServiceImpl implements PricingService {
    private final RentPointPricingDAO pointPricingDAO;
    private final ScooterModelPricingDAO modelPricingDAO;
    private final SpecialModelPricingDAO specialModelPricingDAO;

    @Override
    public RentPointPricing addRentPointPricing(RentPointPricing pricing) {
        return pointPricingDAO.save(pricing);
    }

    @Override
    public void addSpecialPricing(Long rentPointId, SpecialModelPricing... pricingSet) {
        RentPointPricing pricing = pointPricingDAO.getByRentPointId(rentPointId);
        Set<SpecialModelPricing> specialPricingSet = pricing.getStrategiesOverride();
        specialPricingSet.addAll(Arrays.asList(pricingSet));
        pricing.setStrategiesOverride(specialPricingSet);
        pointPricingDAO.update(pricing);
    }

    @Override
    public RentPointPricing getRentPointPricingByPointId(Long rentPointId) {
        return pointPricingDAO.getByRentPointId(rentPointId);
    }

    @Override
    public void removeRentPointPricing(Long id) {
        pointPricingDAO.delete(id);
    }

    @Override
    public void updateRentPointPricing(RentPointPricing update) {
        RentPointPricing updated = pointPricingDAO.getByRentPointId(update.getRentPoint().getId());
        updateNotNullFields(update, updated);
    }

    @Override
    public void updateRentPointPricing(Long updatedId, RentPointPricing update) {
        RentPointPricing updated = pointPricingDAO.getById(updatedId);
        updateNotNullFields(update, updated);
    }

    private void updateNotNullFields(RentPointPricing update, RentPointPricing updated) {
        Optional.of(update.getCommonFactor()).ifPresent(updated::setCommonFactor);
        Optional.of(update.getMinuteFactor()).ifPresent(updated::setMinuteFactor);
        Optional.of(update.getHourFactor()).ifPresent(updated::setHourFactor);
        Optional.of(update.getDayFactor()).ifPresent(updated::setDayFactor);
        Optional.of(update.getWeekFactor()).ifPresent(updated::setWeekFactor);
        Optional.of(update.getMonthFactor()).ifPresent(updated::setMonthFactor);
        Optional.of(update.getStrategiesOverride()).ifPresent(updated::setStrategiesOverride);
        pointPricingDAO.update(updated);
    }

    @Override
    public ScooterModelPricing addScooterModelPricing(ScooterModelPricing pricing) {
        return modelPricingDAO.save(pricing);
    }

    @Override
    public void removeScooterModelPricing(Long id) {
        modelPricingDAO.delete(id);
    }

    @Override
    public void updateScooterModelPricing(Long updatedId, ScooterModelPricing update) {
        ScooterModelPricing updated = modelPricingDAO.getById(updatedId);
        Optional.of(update.getMinutePrice()).ifPresent(updated::setMinutePrice);
        Optional.of(update.getHourPrice()).ifPresent(updated::setHourPrice);
        Optional.of(update.getDayPrice()).ifPresent(updated::setDayPrice);
        Optional.of(update.getWeekPrice()).ifPresent(updated::setWeekPrice);
        Optional.of(update.getMonthPrice()).ifPresent(updated::setMonthPrice);
    }
}
