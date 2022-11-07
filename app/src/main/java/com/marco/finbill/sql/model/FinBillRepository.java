package com.marco.finbill.sql.model;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;
import androidx.lifecycle.LiveData;

import com.marco.finbill.sql.account.AccountDao;
import com.marco.finbill.sql.category.CategoryDao;
import com.marco.finbill.sql.exchange.ExchangeDao;
import com.marco.finbill.sql.transaction.Transaction;
import com.marco.finbill.sql.transaction.TransactionDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FinBillRepository {

    private final TransactionDao transactionDao;

    private static FinBillRepository instance;

    private FinBillRepository(Application application){
        FinBillDatabase database = FinBillDatabase.getInstance(application);

        AccountDao accountDao = database.accountDao();
        CategoryDao categoryDao = database.categoryDao();
        ExchangeDao exchangeDao = database.exchangeDao();
        transactionDao = database.transactionDao();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Handler mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());
    }

    public static FinBillRepository getInstance(Application application){
        if (instance == null) instance = new FinBillRepository(application);
        return instance;
    }

    public LiveData<List<Transaction>> getAllTransactions() {
        return transactionDao.getAllTransactions();
    }
}
