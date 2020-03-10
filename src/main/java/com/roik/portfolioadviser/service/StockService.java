package com.roik.portfolioadviser.service;

import java.time.LocalDate;

public interface StockService {
    void getListOfStocks();

    void getPriceForStockByDate(LocalDate date);
}
