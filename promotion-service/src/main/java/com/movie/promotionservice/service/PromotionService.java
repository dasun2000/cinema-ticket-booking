package com.movie.promotionservice.service;

import com.movie.promotionservice.entity.Promotion;
import com.movie.promotionservice.repository.PromotionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @PostConstruct
    public void init() {
        if (promotionRepository.count() == 0) {
            Promotion p1 = new Promotion();
            p1.setCode("WELCOME10");
            p1.setDiscountPercentage(10.0);
            p1.setValidUntil(LocalDate.now().plusMonths(6));
            p1.setActive(true);
            promotionRepository.save(p1);

            Promotion p2 = new Promotion();
            p2.setCode("CINEMA20");
            p2.setDiscountPercentage(20.0);
            p2.setValidUntil(LocalDate.now().plusMonths(1));
            p2.setActive(true);
            promotionRepository.save(p2);
        }
    }

    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    public Optional<Promotion> getPromotionById(Long id) {
        return promotionRepository.findById(id);
    }

    public Optional<Promotion> getPromotionByCode(String code) {
        return promotionRepository.findByCode(code.toUpperCase());
    }

    public Promotion createPromotion(Promotion promotion) {
        promotion.setCode(promotion.getCode().toUpperCase());
        return promotionRepository.save(promotion);
    }

    public Promotion updatePromotion(Long id, Promotion updatedPromotion) {
        return promotionRepository.findById(id).map(promotion -> {
            promotion.setCode(updatedPromotion.getCode().toUpperCase());
            promotion.setDiscountPercentage(updatedPromotion.getDiscountPercentage());
            promotion.setValidUntil(updatedPromotion.getValidUntil());
            promotion.setActive(updatedPromotion.isActive());
            return promotionRepository.save(promotion);
        }).orElseThrow(() -> new RuntimeException("Promotion not found with id " + id));
    }

    public void deletePromotion(Long id) {
        promotionRepository.deleteById(id);
    }
}
