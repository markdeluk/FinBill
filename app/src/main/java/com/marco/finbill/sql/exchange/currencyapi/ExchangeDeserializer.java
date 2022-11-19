package com.marco.finbill.sql.exchange.currencyapi;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.sql.Date;
import java.util.Map;
import java.util.Set;

public class ExchangeDeserializer implements JsonDeserializer<ExchangeResponse> {
    @Override
    public ExchangeResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ExchangeResponse exchangeResponse = new ExchangeResponse();
        JsonObject jsonObject = json.getAsJsonObject();
        exchangeResponse.date = Date.valueOf(jsonObject.get("date").getAsString());
        Set<String> keys = jsonObject.keySet();
        keys.
        JsonObject rates = jsonObject.get().getAsJsonObject();
        for (Map.Entry<String, JsonElement> entry : rates.entrySet()) {
            Map<String, Double> rate = new HashMap<>();
            rate.put(entry.getKey(), entry.getValue().getAsDouble());
            exchangeResponse.add(entry.getKey(), rate);
        }
        return exchangeResponse;
    }
}
}
