package com.roik.portfolioadviser.service.impl;

import com.roik.portfolioadviser.entity.*;
import com.roik.portfolioadviser.repository.BondPriceRepository;
import com.roik.portfolioadviser.repository.BondRepository;
import com.roik.portfolioadviser.repository.StockPriceRepository;
import com.roik.portfolioadviser.repository.StockRepository;
import com.roik.portfolioadviser.service.PortfolioService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class PortfolioServiceImpl implements PortfolioService {
    private final BondRepository bondRepository;
    private final BondPriceRepository bondPriceRepository;
    private final StockRepository stockRepository;
    private final StockPriceRepository stockPriceRepository;

    @Override
    public PortfolioEntity getPortfolio(HashMap<String, String> parameters) {
        PortfolioType type = PortfolioType.valueOf(parameters.get("type").toUpperCase());
        switch (type) {
            case BALANCED:
                return getBalancedPortfolio(parameters);
            case CRAZY:
                return getCrazyPortfolio(parameters);
            case SAFE:
                return getSafePortfolio(parameters);
            default:
                return null;
        }
    }

    private PortfolioEntity getSafePortfolio(HashMap<String, String> parameters) {
        List<BondEntity> entities = bondRepository.findAll();

        filterList(entities, parameters);

        Map<BondEntity, Double> bondToEstimate = new HashMap<>();

        for (BondEntity bondEntity : entities) {
            List<BondPriceEntity> entitiesByBondEntity = bondPriceRepository.findAllByBondEntity(bondEntity);

            if (parameters.containsKey("startDate")) {
                filterByDate(parameters.get("startDate"), entitiesByBondEntity);
            }

            bondToEstimate.put(bondEntity, estimateBond(entitiesByBondEntity));
        }

        BondEntity securityToBy = getSecurityToBuy(bondToEstimate);
        BondEntity securityToSell = getSecurityToSell(bondToEstimate);

        return PortfolioEntity.builder()
                .bondToBuy(securityToBy)
                .bondToSell(securityToSell)
                .build();
    }

    private Double estimateBond(List<BondPriceEntity> priceEntities) {
        return priceEntities.stream()
                .mapToDouble(x -> x.getPrice().doubleValue() * x.getBondEntity().getPercentageOfProfit().doubleValue())
                .average()
                .orElse(0);
    }

    private Double estimateStock(List<StockPriceEntity> priceEntities) {
        return priceEntities.stream()
                .mapToDouble(x -> x.getPrice().doubleValue() * x.getStockEntity().getPercentageOfDividend().doubleValue())
                .average()
                .orElse(0);
    }

    private PortfolioEntity getBalancedPortfolio(HashMap<String, String> parameters) {
        PortfolioEntity crazyPortfolio = getCrazyPortfolio(parameters);
        PortfolioEntity safePortfolio = getSafePortfolio(parameters);

        return crazyPortfolio.toBuilder()
                .bondToBuy(safePortfolio.getBondToBuy())
                .bondToSell(safePortfolio.getBondToSell())
                .build();
    }


    private PortfolioEntity getCrazyPortfolio(HashMap<String, String> parameters) {
        List<StockEntity> entities = stockRepository.findAll();

        filterList(entities, parameters);
        Map<StockEntity, Double> stockToEstimate = new HashMap<>();

        for (StockEntity stockEntity : entities) {

            List<StockPriceEntity> entitiesByStockEntity = stockPriceRepository.findAllByStockEntity(stockEntity);
            if (parameters.containsKey("startDate")) {
                filterByDate(parameters.get("startDate"), entitiesByStockEntity);
            }

            stockToEstimate.put(stockEntity, estimateStock(entitiesByStockEntity));
        }

        StockEntity securityToBuy = getSecurityToBuy(stockToEstimate);
        StockEntity securityToSell = getSecurityToSell(stockToEstimate);

        return PortfolioEntity.builder()
                .stockToBuy(securityToBuy)
                .stockToSell(securityToSell)
                .build();
    }

    private void filterList(List<? extends AbstractSecurityEntity> list, HashMap<String, String> parameters) {
        if (parameters.containsKey("country")) {
            filterByCountry(parameters.get("country"), list);
        }

        if (parameters.containsKey("currency")) {
            filterByCurrency(parameters.get("currency"), list);
        }
    }

    private <T> T getSecurityToBuy(Map<T, Double> map) {
        return map.entrySet().stream()
                .filter(key -> Collections.max(map.values()).equals(key.getValue()))
                .map(Map.Entry::getKey)
                .findAny()
                .orElse(null);
    }

    private <T> T getSecurityToSell(Map<T, Double> map) {
        return map.entrySet().stream()
                .filter(key -> Collections.min(map.values()).equals(key.getValue()))
                .map(Map.Entry::getKey)
                .findAny()
                .orElse(null);
    }

    private void filterByCurrency(String currency, List<? extends AbstractSecurityEntity> list) {
        list.removeIf(x -> !x.getCurrency().equals(currency));
    }

    private void filterByCountry(String country, List<? extends AbstractSecurityEntity> list) {
        list.removeIf(x -> !x.getCountry().equals(country));
    }

    private void filterByDate(String date, List<? extends AbstractPriceEntity> list) {
        LocalDate startDate = LocalDate.parse(date);
        list.removeIf(x -> x.getDate().compareTo(startDate) < 0);
    }
}
