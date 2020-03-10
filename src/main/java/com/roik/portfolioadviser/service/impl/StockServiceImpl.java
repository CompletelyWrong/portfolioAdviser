package com.roik.portfolioadviser.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roik.portfolioadviser.entity.StockEntity;
import com.roik.portfolioadviser.entity.StockPriceEntity;
import com.roik.portfolioadviser.repository.StockRepository;
import com.roik.portfolioadviser.service.StockService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static com.roik.portfolioadviser.utillity.RequestUtility.sendGetRequest;

@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class StockServiceImpl implements StockService {
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("apiConfig");
    private final StockRepository stockRepository;
    private final ObjectMapper mapper;

    @Override
    public void getListOfStocks() {
        List<String> tickers = new ArrayList<>();
        try {
            Document urlContent = Jsoup.connect(resourceBundle.getString("parseSPIndexUrl")).get();
            Elements elementsWithTickers = urlContent.select(resourceBundle.getString("parseSPIndexKey"));
            elementsWithTickers.forEach(element -> tickers.add(element.text()));
        } catch (IOException e) {
            log.warn("Error while parsing list of S&P 500 companies", e.getCause());
        }

        tickers.forEach(ticker -> stockRepository.save(StockEntity.builder()
                .ticker(ticker)
                .build()));
    }

    @Override
    public void getPriceForStockByDate(LocalDate date) {
//        getListOfStocks();
        stockRepository.findAll().forEach(x -> {
            try {
                String requestContent = sendGetRequest(resourceBundle.getString("parseStockInfoUrl") + x.getTicker());
                StockEntity parsedStock = mapper.readValue(requestContent, StockEntity.class);
                List<StockPriceEntity> stockPriceEntities = mapper.readValue(mapper.readTree(requestContent).get("values").toString(), new TypeReference<>() {
                });

                stockPriceEntities.forEach(System.out::println);
                System.out.println(parsedStock);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
