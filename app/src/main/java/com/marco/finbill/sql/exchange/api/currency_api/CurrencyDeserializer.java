package com.marco.finbill.sql.exchange.api.currency_api;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Set;

public class CurrencyDeserializer implements JsonDeserializer<CurrencyResponse> {
    @Override
    public CurrencyResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        CurrencyResponse currencyResponse = new CurrencyResponse();
        JsonObject jsonObject = json.getAsJsonObject();
        Set<String> keys = jsonObject.keySet();
        for (String key: keys) {
            currencyResponse.putCurrency(key.toUpperCase());
        }
        return currencyResponse;
    }
}
