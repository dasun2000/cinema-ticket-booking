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

    // @PostConstruct
    // public void init() {
    //     if (pricingRepository.count() == 0) {
    //         Pricing odc = new Pricing();
    //         odc.setMovieName("ODC");
    //         odc.setBasePrice(500.0);
    //         odc.setInternetFee(100.0);
    //         odc.setCinemaHall("Hall 1");
    //         odc.setShowTime("7:00 PM");
    //         odc.setSeatType("Standard");
    //         odc.setDayType("Weekday");
    //         pricingRepository.save(odc);

    //         Pricing vip = new Pricing();
    //         vip.setMovieName("BOX");
    //         vip.setBasePrice(1200.0);
    //         vip.setInternetFee(150.0);
    //         vip.setCinemaHall("Hall 2");
    //         vip.setShowTime("9:00 PM");
    //         vip.setSeatType("VIP");
    //         vip.setDayType("Weekend");
    //         pricingRepository.save(vip);
    //     }
    // }

    public List<Pricing> getAllPricing() {
        return pricingRepository.findAll();
    }

    public Optional<Pricing> getPricingById(Long id) {
        return pricingRepository.findById(id);
    }

    public Optional<Pricing> getPricingByMovieName(String movieName) {
        return pricingRepository.findByMovieName(movieName);
    }

    public Optional<Pricing> getPricingBySeatType(String seatType) {
        return pricingRepository.findBySeatType(seatType);
    }

    public Optional<Pricing> getPricingByDayType(String dayType) {
        return pricingRepository.findByDayType(dayType);
    }

    public Pricing createPricing(Pricing pricing) {
        // Calculate total price as basePrice + internetFee
        pricing.setTotalPrice(pricing.getBasePrice() + pricing.getInternetFee());
        return pricingRepository.save(pricing);
    }

    public Pricing updatePricing(Long id, Pricing updatedPricing) {
        return pricingRepository.findById(id).map(p -> {
            p.setMovieName(updatedPricing.getMovieName());
            p.setBasePrice(updatedPricing.getBasePrice());
            p.setInternetFee(updatedPricing.getInternetFee());
            p.setCinemaHall(updatedPricing.getCinemaHall());
            p.setShowTime(updatedPricing.getShowTime());
            p.setSeatType(updatedPricing.getSeatType());
            p.setDayType(updatedPricing.getDayType());
            // Calculate total price as basePrice + internetFee
            p.setTotalPrice(p.getBasePrice() + p.getInternetFee());
            return pricingRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("Pricing not found: " + id));
    }

    public void deletePricing(Long id) {
        pricingRepository.deleteById(id);
    }
}
