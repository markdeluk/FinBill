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
import com.marco.finbill.sql.transaction.expense.Expense;
import com.marco.finbill.sql.transaction.expense.ExpenseDao;
import com.marco.finbill.sql.transaction.expense.TransactionIsExpenseWithRelationships;
import com.marco.finbill.sql.transaction.income.IncomeDao;
import com.marco.finbill.sql.transaction.income.TransactionIsIncomeWithRelationships;
import com.marco.finbill.sql.transaction.transfer.TransactionIsTransferWithRelationships;
import com.marco.finbill.sql.transaction.transfer.TransferDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FinBillRepository {

    private static FinBillRepository instance;

    private ExpenseDao expenseDao;
    private IncomeDao incomeDao;
    private TransferDao transferDao;

    private FinBillRepository(Application application){
        FinBillDatabase database = FinBillDatabase.getInstance(application);

        AccountDao accountDao = database.accountDao();
        CategoryDao categoryDao = database.categoryDao();
        ExchangeDao exchangeDao = database.exchangeDao();
        expenseDao = database.expenseDao();
        incomeDao = database.incomeDao();
        transferDao = database.transferDao();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Handler mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());
    }

    public static FinBillRepository getInstance(Application application){
        if (instance == null) instance = new FinBillRepository(application);
        return instance;
    }

    public LiveData<List<TransactionIsExpenseWithRelationships>> getAllExpenses() {
        return expenseDao.getAllExpenses();
    }

    public LiveData<List<TransactionIsIncomeWithRelationships>> getAllIncomes() {
        return incomeDao.getAllIncomes();
    }

    public LiveData<List<TransactionIsTransferWithRelationships>> getAllTransfers() {
        return transferDao.getAllTransfers();
    }
}
