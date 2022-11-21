package com.marco.finbill.sql.model;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;
import androidx.lifecycle.LiveData;

import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.account.AccountDao;
import com.marco.finbill.sql.category.Category;
import com.marco.finbill.sql.category.CategoryDao;
import com.marco.finbill.sql.exchange.Exchange;
import com.marco.finbill.sql.exchange.ExchangeDao;
import com.marco.finbill.sql.transaction.Transaction;
import com.marco.finbill.sql.transaction.TransactionDao;
import com.marco.finbill.sql.transaction.expense.Expense;
import com.marco.finbill.sql.transaction.expense.ExpenseDao;
import com.marco.finbill.sql.transaction.expense.ExpenseIsTransactionWithRelationships;
import com.marco.finbill.sql.transaction.expense.ExpenseIsTransactionWithRelationshipsDao;
import com.marco.finbill.sql.transaction.income.Income;
import com.marco.finbill.sql.transaction.income.IncomeDao;
import com.marco.finbill.sql.transaction.income.IncomeIsTransactionWithRelationships;
import com.marco.finbill.sql.transaction.income.IncomeIsTransactionWithRelationshipsDao;
import com.marco.finbill.sql.transaction.transfer.Transfer;
import com.marco.finbill.sql.transaction.transfer.TransferDao;
import com.marco.finbill.sql.transaction.transfer.TransferIsTransactionWithRelationships;
import com.marco.finbill.sql.transaction.transfer.TransferIsTransactionWithRelationshipsDao;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FinBillRepository {

    private static FinBillRepository instance;

    private ExpenseIsTransactionWithRelationshipsDao expenseIsTransactionWithRelationshipsDao;
    private IncomeIsTransactionWithRelationshipsDao incomeIsTransactionWithRelationshipsDao;
    private TransferIsTransactionWithRelationshipsDao transferIsTransactionWithRelationshipsDao;
    private TransactionDao transactionDao;
    private ExpenseDao expenseDao;
    private IncomeDao incomeDao;
    private TransferDao transferDao;
    private AccountDao accountDao;
    private CategoryDao categoryDao;
    private ExchangeDao exchangeDao;

    private ExecutorService executorService;
    private Handler mainThreadHandler;

    private FinBillRepository(Application application){
        FinBillDatabase database = FinBillDatabase.getInstance(application);

        accountDao = database.accountDao();
        categoryDao = database.categoryDao();
        exchangeDao = database.exchangeDao();
        expenseIsTransactionWithRelationshipsDao = database.expenseIsTransactionWithRelationshipsDao();
        incomeIsTransactionWithRelationshipsDao = database.incomeIsTransactionWithRelationshipsDao();
        transferIsTransactionWithRelationshipsDao = database.transferIsTransactionWithRelationshipsDao();
        transactionDao = database.transactionDao();
        expenseDao = database.expenseDao();
        incomeDao = database.incomeDao();
        transferDao = database.transferDao();

        executorService = Executors.newFixedThreadPool(2);
        mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());
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

    public LiveData<List<Account>> getAllAccounts() {
        return accountDao.getAllAccounts();
    }

    public LiveData<List<Category>> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    public LiveData<List<Category>> getAllCategoriesByType(int type) {
        return categoryDao.getAllCategoriesByType(type);
    }

    public void insertTransaction(Transaction transaction) {
        executorService.execute(() -> transactionDao.insertTransaction(transaction));
    }

    public void insertExpense(Expense expense) {
        executorService.execute(() -> expenseDao.insertExpense(expense));
    }

    public void insertIncome(Income income) {
        executorService.execute(() -> incomeDao.insertIncome(income));
    }

    public void insertTransfer(Transfer transfer) {
        executorService.execute(() -> transferDao.insertTransfer(transfer));
    }

    public Account getAccountByName(String name) {
        return accountDao.getAccountByName(name);
    }

    public Category getCategoryByName(String name) {
        return categoryDao.getCategoryName(name);
    }

    public void insertExchange(Exchange exchange) {
        executorService.execute(() -> exchangeDao.insertExchange(exchange));
    }

    public void updateExchange(Exchange exchange) {
        executorService.execute(() -> exchangeDao.updateExchange(exchange));
    }

    public void deleteAllExchanges() {
        executorService.execute(() -> exchangeDao.deleteAllExchanges());
    }

}
