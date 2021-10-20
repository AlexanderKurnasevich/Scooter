package by.scooter.api.dao;

import by.scooter.entity.pricing.Discount;

public interface DiscountDAO extends DAO<Discount> {
    Discount getByPromoCode(String promoCode);
}
