package com.marco.finbill.sql.model;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.marco.finbill.R;
import com.marco.finbill.enums.CategoryType;
import com.marco.finbill.enums.DownloadStatus;
import com.marco.finbill.enums.HandleExchange;
import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.category.Category;
import com.marco.finbill.sql.currency_code.CurrencyCode;
import com.marco.finbill.sql.exchange.Exchange;
import com.marco.finbill.sql.exchange.api.ServiceGenerator;
import com.marco.finbill.sql.exchange.api.currency_api.CurrencyApi;
import com.marco.finbill.sql.exchange.api.currency_api.CurrencyResponse;
import com.marco.finbill.sql.exchange.api.exchange_api.ExchangeApi;
import com.marco.finbill.sql.exchange.api.exchange_api.ExchangeResponse;
import com.marco.finbill.sql.transaction.Transaction;
import com.marco.finbill.sql.transaction.expense.Expense;
import com.marco.finbill.sql.transaction.expense.ExpenseIsTransactionWithRelationships;
import com.marco.finbill.sql.transaction.income.Income;
import com.marco.finbill.sql.transaction.income.IncomeIsTransactionWithRelationships;
import com.marco.finbill.sql.transaction.transfer.Transfer;
import com.marco.finbill.sql.transaction.transfer.TransferIsTransactionWithRelationships;
import com.marco.finbill.ui.main.MainActivity;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Currency;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinBillViewModel extends AndroidViewModel {

    private final FinBillRepository repository;
    private MutableLiveData<TransactionViewModelFields> transactionViewModelFieldsLiveData;
    private MutableLiveData<DownloadStatus> currencySucceedLiveData;
    private MutableLiveData<DownloadStatus> exchangeSucceedLiveData;
    private final static int CURRENCY_LENGTH = 3;
    private List<String> currencyStrings;
    private final ArrayList<String> fromCurrencyList = new ArrayList<>();
    private final Set<Currency> availableCurrencies = Currency.getAvailableCurrencies();

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
        return transactionViewModelFields.getValue();
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

    private MutableLiveData<DownloadStatus> getCurrencySucceedLiveData() {
        if (currencySucceedLiveData == null) {
            currencySucceedLiveData = new MutableLiveData<>();
        }
        return currencySucceedLiveData;
    }

    public LiveData<DownloadStatus> downloadCurrencies(HandleExchange operation) {
        MutableLiveData<DownloadStatus> currencySucceed = getCurrencySucceedLiveData();
        CurrencyApi currencyApi = ServiceGenerator.getCurrencyApi();
        Call<CurrencyResponse> currencyCall = currencyApi.getCurrencies();
        currencyCall.enqueue(new Callback<CurrencyResponse>() {
            @Override
            public void onResponse(@NonNull Call<CurrencyResponse> call, @NonNull Response<CurrencyResponse> response) {
                if (response.isSuccessful()) {
                    CurrencyResponse currencyResponse = response.body();
                    if (currencyResponse != null) {
                        currencyStrings = currencyResponse.getCurrencies();
                        deleteAllCurrencyCodes();
                        fromCurrencyList.clear();
                        for (String currencyString : currencyStrings) {
                            if (currencyString.length() == CURRENCY_LENGTH) {
                                if (availableCurrencies.contains(Currency.getInstance(currencyString))) {
                                    insertCurrencyCode(new CurrencyCode(currencyString));
                                    fromCurrencyList.add(currencyString);
                                }
                            }
                        }
                        if (fromCurrencyList.isEmpty()) {
                            currencySucceed.setValue(DownloadStatus.FAILURE);
                        }
                        else {
                            currencySucceed.setValue(DownloadStatus.SUCCESS);
                        }
                    }
                    else {
                        currencySucceed.setValue(DownloadStatus.FAILURE);
                    }
                }
                else {
                    currencySucceed.setValue(DownloadStatus.FAILURE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<CurrencyResponse> call, @NonNull Throwable t) {
                currencySucceed.setValue(DownloadStatus.FAILURE);
            }
        });
        return currencySucceed;
    }

    private MutableLiveData<DownloadStatus> getExchangeSucceedLiveData() {
        if (exchangeSucceedLiveData == null) {
            exchangeSucceedLiveData = new MutableLiveData<>();
        }
        return exchangeSucceedLiveData;
    }

    public MutableLiveData<DownloadStatus> downloadExchanges(HandleExchange operation) {
        MutableLiveData<DownloadStatus> exchangeSucceed = getExchangeSucceedLiveData();
        int n = fromCurrencyList.size();
        exchangeSucceed.setValue(DownloadStatus.STARTED);
        for (int i = 0; i < n; i++) {
            String fromCurrencyString = fromCurrencyList.get(i);
            ExchangeApi exchangeApi = ServiceGenerator.getExchangeApi();
            Call<ExchangeResponse> exchangeCall = exchangeApi.getExchange(fromCurrencyString.toLowerCase());
            int index = i; // needed for accessing into inner class
            exchangeCall.enqueue(new Callback<ExchangeResponse>() {
                @Override
                public void onResponse(@NonNull Call<ExchangeResponse> call, @NonNull Response<ExchangeResponse> response) {
                    if (response.isSuccessful()) {
                        ExchangeResponse exchangeResponse = response.body();
                        if (exchangeResponse != null) {
                            LinkedHashMap<String, Double> ratesMap = exchangeResponse.getRates();
                            if (ratesMap != null) {
                                if (index == n - 1) { // last iteration
                                    exchangeSucceed.setValue(DownloadStatus.SUCCESS);
                                }
                                else {
                                    exchangeSucceed.setValue(DownloadStatus.PROCESSING);
                                }
                                for (Map.Entry<String, Double> entry : ratesMap.entrySet()) {
                                    String toCurrencyString = entry.getKey();
                                    Double rate = entry.getValue();
                                    if (toCurrencyString.length() == CURRENCY_LENGTH) {
                                        if (availableCurrencies.contains(Currency.getInstance(toCurrencyString)) && !fromCurrencyString.equals(toCurrencyString)) {
                                            Exchange exchange = new Exchange(fromCurrencyString, toCurrencyString, rate);
                                            if (operation == HandleExchange.UPDATE) {
                                                updateExchange(exchange);
                                            } // operation == HandleExchange.INSERT
                                            else {
                                                insertExchange(exchange);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else {
                            exchangeSucceed.setValue(DownloadStatus.FAILURE);
                        }
                    }
                    else {
                        exchangeSucceed.setValue(DownloadStatus.FAILURE);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ExchangeResponse> call, @NonNull Throwable t) {
                    exchangeSucceed.setValue(DownloadStatus.FAILURE);
                }
            });
        }
        return exchangeSucceed;
    }
}
