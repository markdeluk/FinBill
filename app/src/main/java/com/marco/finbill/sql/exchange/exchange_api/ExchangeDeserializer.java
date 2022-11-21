package com.marco.finbill.sql.exchange.exchange_api;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Set;

public class ExchangeDeserializer implements JsonDeserializer<ExchangeResponse> {
    @Override
    public ExchangeResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ExchangeResponse exchangeResponse = new ExchangeResponse();
        JsonObject jsonObject = json.getAsJsonObject();
        Set<String> keys = jsonObject.keySet();
        for (String key : keys) { // executed only twice
            if (!key.equals("date")) {
                JsonObject rates = jsonObject.get(key).getAsJsonObject();
                Set<String> ratesKeys = rates.keySet();
                for (String rateKey : ratesKeys) {
                    exchangeResponse.putRates(rateKey, rates.get(rateKey).getAsDouble());
                }
            }
        }
        return exchangeResponse;
    }
}
