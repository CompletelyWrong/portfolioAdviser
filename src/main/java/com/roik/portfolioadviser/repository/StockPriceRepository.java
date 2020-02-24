package com.roik.portfolioadviser.repository;

import com.roik.portfolioadviser.entity.StockEntity;
import com.roik.portfolioadviser.entity.StockPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPriceEntity, Long> {
    List<StockPriceEntity> findAllByStockEntity(StockEntity stockEntity);
}
