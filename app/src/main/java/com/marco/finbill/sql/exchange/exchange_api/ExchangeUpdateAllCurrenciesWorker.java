package com.marco.finbill.sql.exchange.exchange_api;

import android.content.Context;
import android.icu.util.Currency;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.marco.finbill.sql.exchange.Exchange;
import com.marco.finbill.sql.exchange.ExchangeDao;
import com.marco.finbill.sql.model.FinBillDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class ExchangeUpdateAllCurrenciesWorker extends Worker {

    public ExchangeUpdateAllCurrenciesWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Set<Currency> currencySet = Currency.getAvailableCurrencies();

        List<OneTimeWorkRequest> workRequests = new ArrayList<>();
        for (Currency fromCurrency: currencySet) {
            Data inputData = new Data.Builder().putString("fromCurrency", fromCurrency.getCurrencyCode()).build();
            OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(ExchangeUpdateCurrencyWorker.class).setInputData(inputData).build();
            workRequests.add(workRequest);
        }
        WorkManager workManager = WorkManager.getInstance(getApplicationContext());
        workManager.beginWith(workRequests).enqueue();
    }



}
