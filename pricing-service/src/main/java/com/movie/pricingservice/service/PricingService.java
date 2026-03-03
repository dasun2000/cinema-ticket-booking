package com.movie.pricingservice.service;

import com.movie.pricingservice.entity.Pricing;
import com.movie.pricingservice.repository.PricingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import java.util.List;
import java.util.Optional;

@Service
public class PricingService {

    @Autowired
    private PricingRepository pricingRepository;

    @PostConstruct
    public void init() {
        if (pricingRepository.count() == 0) {
            Pricing odc = new Pricing();
            odc.setName("ODC");
            odc.setBasePrice(500.0);
            odc.setInternetFee(100.0);
            pricingRepository.save(odc);

            Pricing vip = new Pricing();
            vip.setName("BOX");
            vip.setBasePrice(1200.0);
            vip.setInternetFee(150.0);
            pricingRepository.save(vip);
        }
    }

    public List<Pricing> getAllPricing() {
        return pricingRepository.findAll();
    }

    public Optional<Pricing> getPricingById(Long id) {
        return pricingRepository.findById(id);
    }

    public Optional<Pricing> getPricingByName(String name) {
        return pricingRepository.findByName(name);
    }

    public Pricing createPricing(Pricing pricing) {
        return pricingRepository.save(pricing);
    }

    public Pricing updatePricing(Long id, Pricing updatedPricing) {
        return pricingRepository.findById(id).map(p -> {
            p.setName(updatedPricing.getName());
            p.setBasePrice(updatedPricing.getBasePrice());
            p.setInternetFee(updatedPricing.getInternetFee());
            return pricingRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("Pricing not found: " + id));
    }

    public void deletePricing(Long id) {
        pricingRepository.deleteById(id);
    }
}
