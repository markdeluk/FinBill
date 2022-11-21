package com.marco.finbill.sql.exchange.exchange_api;

import android.content.Context;
import android.icu.util.Currency;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.marco.finbill.sql.exchange.Exchange;
import com.marco.finbill.sql.exchange.ExchangeDao;
import com.marco.finbill.sql.model.FinBillDatabase;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class ExchangeUpdateCurrencyWorker extends Worker {

    private MutableLiveData<Map<String, Double>> rates;

    public ExchangeUpdateCurrencyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        rates = new MutableLiveData<>();
        ExchangeDao exchangeDao = FinBillDatabase.getInstance(getApplicationContext()).exchangeDao();
        Currency fromCurrency = Currency.getInstance(getInputData().getString("fromCurrency"));
        searchForExchange(fromCurrency);
        Map<String, Double> map = rates.getValue();
        if (map != null) {
            for (Map.Entry<String, Double> entry : map.entrySet()) {
                Currency toCurrency = Currency.getInstance(entry.getKey());
                Exchange search = exchangeDao.getExchangeById(fromCurrency, toCurrency);
                Exchange exchange = new Exchange(fromCurrency, toCurrency, entry.getValue());
                if (search == null) {
                    exchangeDao.insertExchange(exchange);
                } else {
                    exchangeDao.updateExchange(exchange);
                }
            }
            return Result.success();
        }
        return Result.failure();
    }

    private void searchForExchange(Currency fromCurrency) {
        ExchangeApi exchangeApi = ServiceGenerator.getExchangeApi();
        Call<ExchangeResponse> call = exchangeApi.getExchange(fromCurrency.getCurrencyCode().toLowerCase());
        call.enqueue(new Callback<ExchangeResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<ExchangeResponse> call, Response<ExchangeResponse> response) {
                if (response.isSuccessful()) {
                    ExchangeResponse exchangeResponse = response.body();
                    if (exchangeResponse != null) {
                        rates.setValue(exchangeResponse.getRates());
                        Log.e("ExchangeUpdateCurrency", "onResponse: " + rates.getValue());
                    }
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<ExchangeResponse> call, Throwable t) {
            }
        });
    }
}
