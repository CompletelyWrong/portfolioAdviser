package com.roik.portfolioadviser.controller;

import com.roik.portfolioadviser.entity.PortfolioEntity;
import com.roik.portfolioadviser.service.PortfolioService;
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

    @RequestMapping(value = "api/V1/portfolio/{type}", method = GET)
    @ResponseBody
    PortfolioEntity getPortfolio(@PathVariable() String type,
                                 @RequestParam(required = false) String currency,
                                 @RequestParam(required = false) String country,
                                 @RequestParam(required = false) String startDate) {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("type", type);

        if (Objects.nonNull(country)) {
            parameters.put("country", country);
        }

        if (Objects.nonNull(currency)) {
            parameters.put("currency", currency);
        }

        if (Objects.nonNull(startDate)) {
            parameters.put("startDate", startDate);
        }

        return portfolioService.getPortfolio(parameters);
    }

}
