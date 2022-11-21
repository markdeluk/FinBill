package com.marco.finbill;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.work.ListenableWorker;
import androidx.work.testing.TestWorkerBuilder;

import com.marco.finbill.sql.exchange.exchange_api.ExchangeUpdateAllCurrenciesWorker;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@RunWith(AndroidJUnit4.class)
public class ExchangeUpdateAllCurrenciesWorkerTest {
    private Context context;
    private Executor executor;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
        executor = Executors.newSingleThreadExecutor();
    }

    @Test
    public void testExchangeUpdateWorker() {
        ExchangeUpdateAllCurrenciesWorker worker = TestWorkerBuilder.from(context, ExchangeUpdateAllCurrenciesWorker.class, executor).build();
        ListenableWorker.Result result = worker.doWork();
        assertEquals(ListenableWorker.Result.success(), result);
    }
}
