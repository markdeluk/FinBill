package com.marco.finbill.sql.model;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;
import androidx.lifecycle.LiveData;

import com.marco.finbill.sql.account.AccountDao;
import com.marco.finbill.sql.category.CategoryDao;
import com.marco.finbill.sql.exchange.ExchangeDao;
import com.marco.finbill.sql.transaction.expense.ExpenseDao;
import com.marco.finbill.sql.transaction.expense.ExpenseIsTransactionWithRelationships;
import com.marco.finbill.sql.transaction.expense.ExpenseIsTransactionWithRelationshipsDao;
import com.marco.finbill.sql.transaction.income.IncomeDao;
import com.marco.finbill.sql.transaction.income.IncomeIsTransactionWithRelationships;
import com.marco.finbill.sql.transaction.income.IncomeIsTransactionWithRelationshipsDao;
import com.marco.finbill.sql.transaction.transfer.TransferDao;
import com.marco.finbill.sql.transaction.transfer.TransferIsTransactionWithRelationships;
import com.marco.finbill.sql.transaction.transfer.TransferIsTransactionWithRelationshipsDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FinBillRepository {

    private static FinBillRepository instance;

    private ExpenseIsTransactionWithRelationshipsDao expenseIsTransactionWithRelationshipsDao;
    private IncomeIsTransactionWithRelationshipsDao incomeIsTransactionWithRelationshipsDao;
    private TransferIsTransactionWithRelationshipsDao transferIsTransactionWithRelationshipsDao;

    private FinBillRepository(Application application){
        FinBillDatabase database = FinBillDatabase.getInstance(application);

        AccountDao accountDao = database.accountDao();
        CategoryDao categoryDao = database.categoryDao();
        ExchangeDao exchangeDao = database.exchangeDao();
        expenseIsTransactionWithRelationshipsDao = database.expenseIsTransactionWithRelationshipsDao();
        incomeIsTransactionWithRelationshipsDao = database.incomeIsTransactionWithRelationshipsDao();
        transferIsTransactionWithRelationshipsDao = database.transferIsTransactionWithRelationshipsDao();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Handler mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());
    }

    public static FinBillRepository getInstance(Application application){
        if (instance == null) instance = new FinBillRepository(application);
        return instance;
    }

    public LiveData<List<ExpenseIsTransactionWithRelationships>> getAllExpenses() {
        return expenseIsTransactionWithRelationshipsDao.getAllExpenseIsTransactionWithRelationships();
    }

    public LiveData<List<IncomeIsTransactionWithRelationships>> getAllIncomes() {
        return incomeIsTransactionWithRelationshipsDao.getAllIncomeIsTransactionWithRelationships();
    }

    public LiveData<List<TransferIsTransactionWithRelationships>> getAllTransfers() {
        return transferIsTransactionWithRelationshipsDao.getAllTransferIsTransactionWithRelationships();
    }
}
