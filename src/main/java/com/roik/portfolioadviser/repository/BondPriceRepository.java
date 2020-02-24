package com.roik.portfolioadviser.repository;

import com.roik.portfolioadviser.entity.BondEntity;
import com.roik.portfolioadviser.entity.BondPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BondPriceRepository extends JpaRepository<BondPriceEntity, Long> {

    List<BondPriceEntity> findAllByBondEntity(BondEntity bondEntity);
}
