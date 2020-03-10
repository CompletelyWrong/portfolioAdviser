package com.roik.portfolioadviser.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.roik.portfolioadviser.entity.StockEntity;

import java.io.IOException;
import java.math.BigDecimal;

public class StockDeserializer extends StdDeserializer<StockEntity> {
    public StockDeserializer(Class<?> vc) {
        super(vc);
    }

    public StockDeserializer() {
        this(null);
    }

    @Override
    public StockEntity deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        JsonNode stockNode = jsonParser.getCodec().readTree(jsonParser);
        return StockEntity.builder()
                .currency(stockNode.get("meta").get("currency").textValue())
                .exchangeName(stockNode.get("meta").get("exchange").textValue())
                .country("USA")
                .percentageOfDividend(BigDecimal.valueOf(10))
                .build();
    }
}
