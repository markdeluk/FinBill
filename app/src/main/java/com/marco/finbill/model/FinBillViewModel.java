package com.marco.finbill.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.marco.finbill.enums.CategoryType;
import com.marco.finbill.model.auxiliary_entity_fields.AccountFields;
import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.account.AccountHasCurrencies;
import com.marco.finbill.sql.category.Category;
import com.marco.finbill.sql.category.CategoryWithCurrency;
import com.marco.finbill.sql.currency.Currency;
import com.marco.finbill.sql.exchange.Exchange;
import com.marco.finbill.api.ServiceGenerator;
import com.marco.finbill.api.currency_api.CurrencyApi;
import com.marco.finbill.api.currency_api.CurrencyResponse;
import com.marco.finbill.api.exchange_api.ExchangeApi;
import com.marco.finbill.api.exchange_api.ExchangeResponse;
import com.marco.finbill.model.auxiliary_entity_fields.CategoryFields;
import com.marco.finbill.model.auxiliary_entity_fields.TransactionFields;
import com.marco.finbill.sql.transaction.all.Transaction;
import com.marco.finbill.sql.transaction.expense.Expense;
import com.marco.finbill.sql.transaction.expense.ExpenseIsTransactionWithRelationships;
import com.marco.finbill.sql.transaction.income.Income;
import com.marco.finbill.sql.transaction.income.IncomeIsTransactionWithRelationships;
import com.marco.finbill.sql.transaction.transfer.Transfer;
import com.marco.finbill.sql.transaction.transfer.TransferIsTransactionWithRelationships;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinBillViewModel extends AndroidViewModel {

    private final FinBillRepository repository;
    private final MutableLiveData<TransactionFields> transactionFieldsLiveData = new MutableLiveData<>();
    private final MutableLiveData<AccountFields> accountFieldsLiveData = new MutableLiveData<>();
    private final MutableLiveData<CategoryFields> categoryFieldsLiveData = new MutableLiveData<>();
    private final MutableLiveData<Exchange> downloadedExchangeLiveData = new MutableLiveData<>();

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

    public LiveData<Long> insertTransaction(Transaction transaction) {
        return repository.insertTransaction(transaction);
    }

    public void updateTransaction(Transaction transaction) {
        repository.updateTransaction(transaction);
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

    // Auxiliary methods for adding a transaction

    public LiveData<TransactionFields> pullTransactionFieldsLiveData() {
        return transactionFieldsLiveData;
    }

    private TransactionFields getTransactionFields() {
        if (transactionFieldsLiveData.getValue() == null) {
            transactionFieldsLiveData.setValue(new TransactionFields());
        }
        return transactionFieldsLiveData.getValue();
    }

    public void pushTransactionFieldId(Integer id) {
        getTransactionFields().setId(id);
        transactionFieldsLiveData.setValue(getTransactionFields());
    }

    public void pushTransactionFieldCurrencyId(Integer id) {
        getTransactionFields().setCurrencyId(id);
        transactionFieldsLiveData.setValue(getTransactionFields());
    }

    public void pushTransactionFieldFrom(Object from) {
        getTransactionFields().setFrom(from);
        transactionFieldsLiveData.setValue(getTransactionFields());
    }

    public void pushTransactionFieldTo(Object to) {
        getTransactionFields().setTo(to);
        transactionFieldsLiveData.setValue(getTransactionFields());
    }

    public void popTransactionFields() {
        getTransactionFields().clear();
        transactionFieldsLiveData.setValue(getTransactionFields());
    }

    // end section

    public void insertExchange(Exchange exchange) {
        repository.insertExchange(exchange);
    }

    public void updateExchange(Exchange exchange) {
        repository.updateExchange(exchange);
    }

    public void deleteAllExchanges() {
        repository.deleteAllExchanges();
    }

    public void deleteAllCurrencies() {
        repository.deleteAllCurrencies();
    }

    public LiveData<List<Currency>> getAllCurrencies() {
        return repository.getAllCurrencies();
    }

    public void insertAccount(Account account) {
        repository.insertAccount(account);
    }

    public void insertCategory(Category category) {
        repository.insertCategory(category);
    }

    public LiveData<Currency> getCurrencyByString(String currencyString) {
        return repository.getCurrencyByString(currencyString);
    }

    public void insertCurrencies(List<Currency> currencies) {
        repository.insertCurrencies(currencies);
    }

    public void downloadExchange(Currency fromCurrency, Currency toCurrency) {
        ExchangeApi exchangeApi = ServiceGenerator.getExchangeApi();
        Call<ExchangeResponse> exchangeCall = exchangeApi.getExchange(fromCurrency.getCurrencyString().toLowerCase(), toCurrency.getCurrencyString().toLowerCase());
        exchangeCall.enqueue(new Callback<ExchangeResponse>() {
            @Override
            public void onResponse(@NonNull Call<ExchangeResponse> call, @NonNull Response<ExchangeResponse> response) {
                if (response.isSuccessful()) {
                    ExchangeResponse exchangeResponse = response.body();
                    if (exchangeResponse != null) {
                        downloadedExchangeLiveData.setValue(new Exchange(fromCurrency.getCurrencyId(), toCurrency.getCurrencyId(), exchangeResponse.getRate()));
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ExchangeResponse> call, @NonNull Throwable t) {
            }
        });
    }

    // Auxiliary methods for adding an account

    public LiveData<AccountFields> pullAccountFieldsLiveData() {
        return accountFieldsLiveData;
    }

    private AccountFields getAccountFields() {
        if (accountFieldsLiveData.getValue() == null) {
            accountFieldsLiveData.setValue(new AccountFields());
        }
        return accountFieldsLiveData.getValue();
    }

    public void pushAccountFieldBalance(Integer balanceCurrencyId) {
        getAccountFields().setBalanceCurrencyId(balanceCurrencyId);
        accountFieldsLiveData.setValue(getAccountFields());
    }

    public void pushAccountFieldPlatfond(Integer platfondCurrencyId) {
        getAccountFields().setPlatfondCurrencyId(platfondCurrencyId);
        accountFieldsLiveData.setValue(getAccountFields());
    }

    public void pushAccountFieldProceed(Boolean proceed) {
        getAccountFields().setProceed(proceed);
        accountFieldsLiveData.setValue(getAccountFields());
    }

    public void popAccountFields() {
        getAccountFields().clear();
        accountFieldsLiveData.setValue(getAccountFields());
    }

    // end section

    public LiveData<List<AccountHasCurrencies>> getAllAccountsHaveCurrencies() {
        return repository.getAllAccountsHaveCurrencies();
    }

    public LiveData<List<CategoryWithCurrency>> getAllCategoriesWithCurrencyByType(CategoryType type) {
        return repository.getAllCategoriesWithCurrencyByType(type);
    }

    public LiveData<List<CategoryWithCurrency>> getAllCategoriesWithCurrency() {
        return repository.getAllCategoriesWithCurrency();
    }

    // Auxiliary methods for adding a category

    public LiveData<CategoryFields> pullCategoryFieldsLiveData() {
        return categoryFieldsLiveData;
    }

    private CategoryFields getCategoryFields() {
        if (categoryFieldsLiveData.getValue() == null) {
            categoryFieldsLiveData.setValue(new CategoryFields());
        }
        return categoryFieldsLiveData.getValue();
    }

    public void pushCategoryFieldBalance(Integer balanceCurrencyId) {
        getCategoryFields().setBalanceCurrencyId(balanceCurrencyId);
        categoryFieldsLiveData.setValue(getCategoryFields());
    }

    public void pushCategoryFieldProceed(Boolean proceed) {
        getCategoryFields().setProceed(proceed);
        categoryFieldsLiveData.setValue(getCategoryFields());
    }

    public void popCategoryFields() {
        getCategoryFields().clear();
        categoryFieldsLiveData.setValue(getCategoryFields());
    }

    // end section

    // Auxiloary methods for adding an exchange

    public LiveData<Exchange> getDownloadedExchange() {
        return downloadedExchangeLiveData;
    }

    public LiveData<Integer> getNumberOfExchanges() {
        return repository.getNumberOfExchanges();
    }

    public LiveData<List<ExpenseIsTransactionWithRelationships>> getAllExpenseIsTransactionWithRelationships() {
        return repository.getAllExpenseIsTransactionWithRelationships();
    }

    public LiveData<List<IncomeIsTransactionWithRelationships>> getAllIncomeIsTransactionWithRelationships() {
        return repository.getAllIncomeIsTransactionWithRelationships();
    }

    public LiveData<List<TransferIsTransactionWithRelationships>> getAllTransferIsTransactionWithRelationships() {
        return repository.getAllTransferIsTransactionWithRelationships();
    }
}
