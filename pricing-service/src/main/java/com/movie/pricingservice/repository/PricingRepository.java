package com.movie.pricingservice.repository;

import com.movie.pricingservice.entity.Pricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PricingRepository extends JpaRepository<Pricing, Long> {
    Optional<Pricing> findByName(String name);
}
