package com.marco.finbill.sql.exchange.exchange_api;

import android.content.Context;
import android.icu.util.Currency;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.marco.finbill.sql.exchange.Exchange;
import com.marco.finbill.sql.exchange.ExchangeDao;
import com.marco.finbill.sql.model.FinBillDatabase;
import com.marco.finbill.sql.model.FinBillViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExchangeUpdateWorker extends Worker {

    public ExchangeUpdateWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        FinBillDatabase database = FinBillDatabase.getInstance(getApplicationContext());
        ExchangeDao exchangeDao = database.exchangeDao();
        SearchForExchange searchForExchange = null;
        Map<String, Double> rates = null;
        Set<Map.Entry<String, Double>> ratesSet;
        Set<Currency> currencySet = Currency.getAvailableCurrencies();
        boolean flag = false;
        for (Currency fromCurrency: currencySet) {
            searchForExchange = new SearchForExchange(fromCurrency);
            while (!searchForExchange.isFinished()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            rates = searchForExchange.getRates();
            if (rates != null) {
                ratesSet = rates.entrySet();
                for (Map.Entry<String, Double> entry: ratesSet) {
                    Currency toCurrency = Currency.getInstance(entry.getKey().toUpperCase());
                    Exchange exchange = new Exchange(fromCurrency, toCurrency, entry.getValue());
                    exchangeDao.insertExchange(exchange);
                }
                flag = true;
            }
        }
        if (flag) {
            return Result.success();
        }
        else {
            return Result.failure();
        }
    }
}
