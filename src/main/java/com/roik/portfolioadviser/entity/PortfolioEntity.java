package com.roik.portfolioadviser.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Setter(AccessLevel.NONE)
@Data
@Builder(toBuilder = true)
public class PortfolioEntity {
    private final PortfolioType type;
    private final BondEntity bondToSell;
    private final BondEntity bondToBuy;
    private final StockEntity stockToBuy;
    private final StockEntity stockToSell;
}
