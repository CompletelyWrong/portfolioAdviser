package com.roik.portfolioadviser.controller;

import com.roik.portfolioadviser.entity.PortfolioEntity;
import com.roik.portfolioadviser.service.PortfolioService;
import com.roik.portfolioadviser.service.StockService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class ApiController {
    private final PortfolioService portfolioService;
    private final StockService stockService;

    @RequestMapping(value = "api/V1/portfolio/{type}", method = GET)
    @ResponseBody
    PortfolioEntity getPortfolio(@PathVariable() String type,
                                 @RequestParam(required = false) String currency,
                                 @RequestParam(required = false) String country,
                                 @RequestParam(required = false) String startDate) {
        HashMap<String, String> parameters = new HashMap<>();

        putParameterToMap("type", type, parameters);
        putParameterToMap("country", country, parameters);
        putParameterToMap("currency", currency, parameters);
        putParameterToMap("startDate", startDate, parameters);
        stockService.getPriceForStockByDate(null);
        return portfolioService.getPortfolio(parameters);
    }

    private void putParameterToMap(String key, String parameter, HashMap<String, String> parameters) {
        if (Objects.nonNull(parameter)) {
            parameters.put(key, parameter);
        }
    }
}
