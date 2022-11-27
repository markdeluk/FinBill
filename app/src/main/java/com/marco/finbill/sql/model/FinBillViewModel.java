package com.marco.finbill.sql.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.marco.finbill.enums.CategoryType;
import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.category.Category;
import com.marco.finbill.sql.currency_code.CurrencyCode;
import com.marco.finbill.sql.exchange.Exchange;
import com.marco.finbill.sql.transaction.Transaction;
import com.marco.finbill.sql.transaction.expense.Expense;
import com.marco.finbill.sql.transaction.expense.ExpenseIsTransactionWithRelationships;
import com.marco.finbill.sql.transaction.income.Income;
import com.marco.finbill.sql.transaction.income.IncomeIsTransactionWithRelationships;
import com.marco.finbill.sql.transaction.transfer.Transfer;
import com.marco.finbill.sql.transaction.transfer.TransferIsTransactionWithRelationships;

import java.util.List;

public class FinBillViewModel extends AndroidViewModel {

    private final FinBillRepository repository;
    private MutableLiveData<TransactionViewModelFields> transactionViewModelFieldsLiveData;

    public FinBillViewModel(Application application) {
        super(application);
        repository = FinBillRepository.getInstance(application);
    }

    public LiveData<List<ExpenseIsTransactionWithRelationships>> getAllExpenses() {
        return repository.getAllExpenses();
    }

    public LiveData<List<IncomeIsTransactionWithRelationships>> getAllIncomes() {
        return repository.getAllIncomes();
    }

    public LiveData<List<TransferIsTransactionWithRelationships>> getAllTransfers() {
        return repository.getAllTransfers();
    }

    public LiveData<List<Account>> getAllAccounts() {
        return repository.getAllAccounts();
    }

    public LiveData<List<Category>> getAllCategories() {
        return repository.getAllCategories();
    }

    public LiveData<List<Category>> getAllCategoriesByType(CategoryType type) {
        return repository.getAllCategoriesByType(type);
    }

    public void insertTransaction(Transaction transaction) {
        repository.insertTransaction(transaction);
    }

    public void insertExpense(Expense expense) {
        repository.insertExpense(expense);
    }

    public void insertIncome(Income income) {
        repository.insertIncome(income);
    }

    public void insertTransfer(Transfer transfer) {
        repository.insertTransfer(transfer);
    }

    public LiveData<Account> getAccountByName(String name) {
        return repository.getAccountByName(name);
    }

    public LiveData<Category> getCategoryByName(String name) {
        return repository.getCategoryByName(name);
    }

    public MutableLiveData<TransactionViewModelFields> getTransactionViewModelFields() {
        if (transactionViewModelFieldsLiveData == null) {
            transactionViewModelFieldsLiveData = new MutableLiveData<>();
        }
        return transactionViewModelFieldsLiveData;
    }

    private TransactionViewModelFields getTransactionViewModelFieldsContent() {
        MutableLiveData<TransactionViewModelFields> transactionViewModelFields = getTransactionViewModelFields();
        if (transactionViewModelFields.getValue() == null) {
            transactionViewModelFields.setValue(new TransactionViewModelFields());
        }
        return getTransactionViewModelFields().getValue();
    }

    public void setTransactionViewModelFieldFrom(Object from) {
        TransactionViewModelFields transactionViewModelFields = getTransactionViewModelFieldsContent();
        transactionViewModelFields.setFrom(from);
        getTransactionViewModelFields().setValue(transactionViewModelFields);
    }

    public void setTransactionViewModelFieldTo(Object to) {
        TransactionViewModelFields transactionViewModelFields = getTransactionViewModelFieldsContent();
        transactionViewModelFields.setTo(to);
        getTransactionViewModelFields().setValue(transactionViewModelFields);
    }

    public void clearTransactionViewModelFields() {
        TransactionViewModelFields transactionViewModelFields = getTransactionViewModelFieldsContent();
        transactionViewModelFields.clear();
        getTransactionViewModelFields().setValue(transactionViewModelFields);
    }

    public void insertExchange(Exchange exchange) {
        repository.insertExchange(exchange);
    }

    public void updateExchange(Exchange exchange) {
        repository.updateExchange(exchange);
    }

    public void deleteAllExchanges() {
        repository.deleteAllExchanges();
    }

    public void insertCurrencyCode(CurrencyCode currencyCode) {
        repository.insertCurrencyCode(currencyCode);
    }

    public void deleteAllCurrencyCodes() {
        repository.deleteAllCurrencyCodes();
    }

    public LiveData<List<CurrencyCode>> getAllCurrencyCodes() {
        return repository.getAllCurrencyCodes();
    }

    public void insertAccount(Account account) {
        repository.insertAccount(account);
    }

    public void insertCategory(Category category) {
        repository.insertCategory(category);
    }

}
