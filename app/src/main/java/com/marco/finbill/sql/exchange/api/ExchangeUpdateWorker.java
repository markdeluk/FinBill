package com.marco.finbill.sql.exchange.api;

import android.content.Context;
import android.icu.util.Currency;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.ForegroundInfo;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.marco.finbill.sql.exchange.api.currency_api.SearchForCurrency;
import com.marco.finbill.sql.exchange.api.exchange_api.SearchForExchange;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExchangeUpdateWorker extends Worker {

    private static final int CURRENCY_LENGTH = 3;

    public ExchangeUpdateWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        boolean flag = true;

        SearchForCurrency searchForCurrency = null;
        try {
            searchForCurrency = new SearchForCurrency();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (searchForCurrency != null) {
            List<String> currencies = searchForCurrency.getCurrencies();
            Set<Currency> currencySet = new HashSet<>();
            Currency now;

            // Filtering currencies according to the content of the Java library: this is because I need the translated currency name, which is provided by the Java library.
            // Hence, if a currency is not contained in the Java library, it will not be considered in the worker operations and it will not be added to the database.
            for (String currency : currencies) {
                if (currency.length() == CURRENCY_LENGTH) {
                    now = Currency.getInstance(currency);
                    if (now != null) {
                        currencySet.add(now);
                    }
                }
            }

            if (!currencySet.isEmpty()) {
                for (Currency fromCurrency : currencySet) {
                    SearchForExchange searchForExchange = null;
                    try {
                        searchForExchange = new SearchForExchange(fromCurrency);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (searchForExchange != null) {
                        Map<String, Double> rates = searchForExchange.getRates();
                        if (rates != null) {
                            Set<Map.Entry<String, Double>> toCurrencySet = rates.entrySet();
                            if (!toCurrencySet.isEmpty()) {
                                for (Map.Entry<String, Double> entry : toCurrencySet) {
                                    String key = entry.getKey();
                                    Currency toCurrency;
                                    if (key.length() == CURRENCY_LENGTH) {
                                        toCurrency = Currency.getInstance(key);
                                        if (toCurrency != null) {
                                            Data data = new Data.Builder()
                                                    .putString("fromCurrency", fromCurrency.getCurrencyCode())
                                                    .putString("toCurrency", toCurrency.getCurrencyCode())
                                                    .putDouble("rate", entry.getValue())
                                                    .build();
                                            setProgressAsync(data);
                                            //Log.e("ExchangeUpdateWorker", "doWork: " + data.toString());
                                        }
                                    }
                                }
                            }
                        }
                        else {
                            flag = false;
                        }
                    }
                    else {
                        flag = false;
                    }
                }
            }
            else {
                flag = false;
            }
        }
        else {
            flag = false;
        }

        if (flag) {
            return Result.success();
        }
        else {
            return Result.failure();
        }
    }
}
