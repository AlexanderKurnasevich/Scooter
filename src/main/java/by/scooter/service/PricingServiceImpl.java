package by.scooter.service;

import by.scooter.api.dao.RentPointPricingDAO;
import by.scooter.api.dao.ScooterModelPricingDAO;
import by.scooter.api.dao.SpecialModelPricingDAO;
import by.scooter.api.sevice.PricingService;
import by.scooter.api.sevice.UtilService;
import by.scooter.entity.dto.pricing.RentPointPricingDTO;
import by.scooter.entity.dto.pricing.ScooterModelPricingDTO;
import by.scooter.entity.dto.pricing.SpecialModelPricingDTO;
import by.scooter.entity.pricing.RentPointPricing;
import by.scooter.entity.pricing.ScooterModelPricing;
import by.scooter.entity.pricing.SpecialModelPricing;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PricingServiceImpl implements PricingService {
    private final RentPointPricingDAO pointPricingDAO;
    private final ScooterModelPricingDAO modelPricingDAO;
    private final SpecialModelPricingDAO specialModelPricingDAO;
    private final ModelMapper mapper;
    private final UtilService utilService;

    @Override
    public RentPointPricingDTO addRentPointPricing(RentPointPricingDTO pricing) {
        return mapper.map(pointPricingDAO.save(mapper.map(pricing, RentPointPricing.class)), RentPointPricingDTO.class);
    }

    @Override
    public RentPointPricingDTO getRentPointPricingByPointId(Long rentPointId) {
        return mapper.map(pointPricingDAO.getByRentPointId(rentPointId), RentPointPricingDTO.class);
    }

    @Override
    public void removeRentPointPricing(Long id) {
        pointPricingDAO.delete(id);
    }

    @Override
    public void updateRentPointPricing(RentPointPricingDTO update) {
        RentPointPricing updated = pointPricingDAO.getByRentPointId(update.getRentPointId());
        updateNotNullFields(update, updated);
    }

    @Override
    public void updateRentPointPricing(Long updatedId, RentPointPricingDTO update) {
        RentPointPricing updated = pointPricingDAO.getById(updatedId);
        updateNotNullFields(update, updated);
    }

    private void updateNotNullFields(RentPointPricingDTO update, RentPointPricing updated) {
        Optional.of(update.getCommonFactor()).ifPresent(updated::setCommonFactor);
        Optional.of(update.getMinuteFactor()).ifPresent(updated::setMinuteFactor);
        Optional.of(update.getHourFactor()).ifPresent(updated::setHourFactor);
        Optional.of(update.getDayFactor()).ifPresent(updated::setDayFactor);
        Optional.of(update.getWeekFactor()).ifPresent(updated::setWeekFactor);
        Optional.of(update.getMonthFactor()).ifPresent(updated::setMonthFactor);
        pointPricingDAO.update(updated);
    }

    @Override
    public void addSpecialPricing(Long rentPointId, SpecialModelPricingDTO... pricingArray) {
        RentPointPricing pricing = pointPricingDAO.getByRentPointId(rentPointId);
        Set<SpecialModelPricing> specialPricingSet = pricing.getStrategiesOverride();
        specialPricingSet.addAll(utilService.convertArray(pricingArray, SpecialModelPricing.class));
        pricing.setStrategiesOverride(specialPricingSet);
        pointPricingDAO.update(pricing);
    }

    @Override
    public ScooterModelPricingDTO addScooterModelPricing(ScooterModelPricingDTO pricing) {
        return mapper.map(modelPricingDAO.save(mapper.map(pricing, ScooterModelPricing.class)),
                ScooterModelPricingDTO.class);
    }

    @Override
    public void removeScooterModelPricing(Long id) {
        modelPricingDAO.delete(id);
    }

    @Override
    public void updateScooterModelPricing(Long updatedId, ScooterModelPricingDTO update) {
        ScooterModelPricing updated = modelPricingDAO.getById(updatedId);
        Optional.of(update.getMinutePrice()).ifPresent(updated::setMinutePrice);
        Optional.of(update.getHourPrice()).ifPresent(updated::setHourPrice);
        Optional.of(update.getDayPrice()).ifPresent(updated::setDayPrice);
        Optional.of(update.getWeekPrice()).ifPresent(updated::setWeekPrice);
        Optional.of(update.getMonthPrice()).ifPresent(updated::setMonthPrice);
    }
}
