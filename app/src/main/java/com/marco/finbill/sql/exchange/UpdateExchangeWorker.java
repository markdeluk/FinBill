package com.marco.finbill.sql.exchange;

import android.content.Context;
import android.icu.util.Currency;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.marco.finbill.sql.exchange.exchange_api.SearchForExchange;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UpdateExchangeWorker extends Worker {

    public UpdateExchangeWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        SearchForExchange searchForExchange = null;
        Map<String, Object> exchangesMap = new HashMap<>();
        Set<Currency> currencySet = Currency.getAvailableCurrencies();
        for (Currency fromCurrency : currencySet) {
            searchForExchange = new SearchForExchange(fromCurrency);
            Map<String, Double> exchangeMap = searchForExchange.getExchangesFromCurrencyToCurrencies();
            for (Map.Entry<String, Double> entry : exchangeMap.entrySet()) {
                exchangesMap.put(fromCurrency.getCurrencyCode() + "_" + entry.getKey(), entry.getValue());
            }
        }
        Data outputData = new Data.Builder().putAll(exchangesMap).build();
        return Result.success(outputData);
    }

}
