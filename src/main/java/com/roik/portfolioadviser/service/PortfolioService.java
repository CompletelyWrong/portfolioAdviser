package com.roik.portfolioadviser.service;

import com.roik.portfolioadviser.entity.PortfolioEntity;

import java.util.HashMap;

public interface PortfolioService {
    PortfolioEntity getPortfolio(HashMap<String, String> parameters);
}
