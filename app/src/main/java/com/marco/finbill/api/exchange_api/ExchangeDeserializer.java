package com.marco.finbill.api.exchange_api;

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
                exchangeResponse.setRate(jsonObject.get(key).getAsDouble());
            }
        }
        return exchangeResponse;
    }
}
