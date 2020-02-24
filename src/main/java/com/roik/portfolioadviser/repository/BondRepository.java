package com.roik.portfolioadviser.repository;

import com.roik.portfolioadviser.entity.BondEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BondRepository extends JpaRepository<BondEntity, Long> {
}
