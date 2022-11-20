package com.marco.finbill.sql.exchange.exchange_api;

import android.icu.util.Currency;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.sql.Date;
import java.util.Set;
import java.util.function.Predicate;

public class ExchangeDeserializer implements JsonDeserializer<ExchangeResponse> {
    @Override
    public ExchangeResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ExchangeResponse exchangeResponse = new ExchangeResponse();
        JsonObject jsonObject = json.getAsJsonObject();
        Set<String> keys = jsonObject.keySet();
        for (String key : keys) {
            if (!key.equals("date")) {
                exchangeResponse.fromCurrency = Currency.getInstance(key.toUpperCase());
                JsonObject rates = jsonObject.get(key).getAsJsonObject();
                Set<String> ratesKeys = rates.keySet();
                for (String rateKey : ratesKeys) {
                    if (Currency.getAvailableCurrencies().stream().map(Currency::getCurrencyCode).anyMatch(Predicate.isEqual(rateKey.toUpperCase()))) {
                        exchangeResponse.rates.put(Currency.getInstance(rateKey.toUpperCase()), rates.get(rateKey).getAsDouble());
                    }
                }
            }
        }
        return exchangeResponse;
    }
}
