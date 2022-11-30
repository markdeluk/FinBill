package com.marco.finbill.sql.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.marco.finbill.enums.CategoryType;
import com.marco.finbill.enums.DownloadStatus;
import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.account.AccountHasCurrencies;
import com.marco.finbill.sql.category.Category;
import com.marco.finbill.sql.category.CategoryWithCurrency;
import com.marco.finbill.sql.currency.Currency;
import com.marco.finbill.sql.exchange.Exchange;
import com.marco.finbill.sql.exchange.api.ServiceGenerator;
import com.marco.finbill.sql.exchange.api.currency_api.CurrencyApi;
import com.marco.finbill.sql.exchange.api.currency_api.CurrencyResponse;
import com.marco.finbill.sql.exchange.api.exchange_api.ExchangeApi;
import com.marco.finbill.sql.exchange.api.exchange_api.ExchangeResponse;
import com.marco.finbill.sql.model.entity_fields.AccountViewModelFields;
import com.marco.finbill.sql.model.entity_fields.CategoryViewModelFields;
import com.marco.finbill.sql.model.entity_fields.TransactionViewModelFields;
import com.marco.finbill.sql.transaction.all.Transaction;
import com.marco.finbill.sql.transaction.expense.Expense;
import com.marco.finbill.sql.transaction.expense.ExpenseIsTransactionWithRelationships;
import com.marco.finbill.sql.transaction.income.Income;
import com.marco.finbill.sql.transaction.income.IncomeIsTransactionWithRelationships;
import com.marco.finbill.sql.transaction.transfer.Transfer;
import com.marco.finbill.sql.transaction.transfer.TransferIsTransactionWithRelationships;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinBillViewModel extends AndroidViewModel {

    private final FinBillRepository repository;
    private MutableLiveData<TransactionViewModelFields> transactionViewModelFieldsLiveData;
    private MutableLiveData<DownloadResultNumber> downloadedCurrenciesLiveData;
    private MutableLiveData<DownloadResultExchange> downloadExchangesLiveData;
    private MutableLiveData<AccountViewModelFields> accountViewModelFieldsLiveData;
    private MutableLiveData<CategoryViewModelFields> categoryViewModelFieldsLiveData;
    private final static int CURRENCY_LENGTH = 3;
    private List<String> currencyStrings;
    private final ArrayList<String> fromCurrencyList = new ArrayList<>();
    private final Set<java.util.Currency> availableCurrencies = java.util.Currency.getAvailableCurrencies();

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

    public MutableLiveData<TransactionViewModelFields> getTransactionViewModelFieldsLiveData() {
        if (transactionViewModelFieldsLiveData == null) {
            transactionViewModelFieldsLiveData = new MutableLiveData<>();
        }
        return transactionViewModelFieldsLiveData;
    }

    private TransactionViewModelFields getTransactionViewModelFields() {
        if (getTransactionViewModelFieldsLiveData().getValue() == null) {
            setTransactionViewModelFieldsLiveData(new TransactionViewModelFields());
        }
        return getTransactionViewModelFieldsLiveData().getValue();
    }

    private void setTransactionViewModelFieldsLiveData(TransactionViewModelFields transactionViewModelFields) {
        getTransactionViewModelFieldsLiveData().setValue(transactionViewModelFields);
    }

    public void setTransactionViewModelFieldFrom(Object from) {
        getTransactionViewModelFields().setFrom(from);
        getTransactionViewModelFieldsLiveData().setValue(getTransactionViewModelFields());
    }

    public void setTransactionViewModelFieldTo(Object to) {
        getTransactionViewModelFields().setTo(to);
        getTransactionViewModelFieldsLiveData().setValue(getTransactionViewModelFields());
    }

    public void clearTransactionViewModelFields() {
        getTransactionViewModelFields().clear();
        getTransactionViewModelFieldsLiveData().setValue(getTransactionViewModelFields());
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

    public void insertCurrencyCode(Currency currency) {
        repository.insertCurrencyCode(currency);
    }

    public void deleteAllCurrencyCodes() {
        repository.deleteAllCurrencyCodes();
    }

    public LiveData<List<Currency>> getAllCurrencyCodes() {
        return repository.getAllCurrencyCodes();
    }

    public void insertAccount(Account account) {
        repository.insertAccount(account);
    }

    public void insertCategory(Category category) {
        repository.insertCategory(category);
    }

    private DownloadResultNumber getDownloadedCurrencies() {
        if (getDownloadedCurrenciesLiveData().getValue() == null) {
            getDownloadedCurrenciesLiveData().setValue(new DownloadResultNumber());
        }
        return getDownloadedCurrenciesLiveData().getValue();
    }

    private MutableLiveData<DownloadResultNumber> getDownloadedCurrenciesLiveData() {
        if (downloadedCurrenciesLiveData == null) {
            downloadedCurrenciesLiveData = new MutableLiveData<>();
        }
        return downloadedCurrenciesLiveData;
    }

    private void setDownloadedCurrenciesLiveData(DownloadStatus status, Integer items) {
        DownloadResultNumber downloadResultNumber = getDownloadedCurrencies();
        downloadResultNumber.setDownloadStatus(status);
        downloadResultNumber.setDownloadedItems(items);
        getDownloadedCurrenciesLiveData().setValue(downloadResultNumber);
    }

    public LiveData<DownloadResultNumber> downloadCurrencies() {
        CurrencyApi currencyApi = ServiceGenerator.getCurrencyApi();
        Call<CurrencyResponse> currencyCall = currencyApi.getCurrencies();
        setDownloadedCurrenciesLiveData(DownloadStatus.STARTED, null);
        deleteAllCurrencyCodes();
        fromCurrencyList.clear();
        currencyCall.enqueue(new Callback<CurrencyResponse>() {
            @Override
            public void onResponse(@NonNull Call<CurrencyResponse> call, @NonNull Response<CurrencyResponse> response) {
                if (response.isSuccessful()) {
                    CurrencyResponse currencyResponse = response.body();
                    if (currencyResponse != null) {
                        currencyStrings = currencyResponse.getCurrencies();
                        for (String currencyString : currencyStrings) {
                            if (currencyString.length() == CURRENCY_LENGTH) {
                                if (availableCurrencies.contains(java.util.Currency.getInstance(currencyString))) {
                                    insertCurrencyCode(new Currency(currencyString));
                                    fromCurrencyList.add(currencyString);
                                }
                            }
                        }
                        if (fromCurrencyList.isEmpty()) {
                            setDownloadedCurrenciesLiveData(DownloadStatus.FAILURE, null);
                        }
                        else {
                            Log.e("FinBillViewModel", "onResponse: " + fromCurrencyList.size());
                            setDownloadedCurrenciesLiveData(DownloadStatus.SUCCESS, fromCurrencyList.size());
                        }
                    }
                    else {
                        setDownloadedCurrenciesLiveData(DownloadStatus.FAILURE, null);
                    }
                }
                else {
                    setDownloadedCurrenciesLiveData(DownloadStatus.FAILURE, null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<CurrencyResponse> call, @NonNull Throwable t) {
                setDownloadedCurrenciesLiveData(DownloadStatus.FAILURE, null);
            }
        });
        return downloadedCurrenciesLiveData;
    }

    private MutableLiveData<DownloadResultExchange> getDownloadExchangesLiveData() {
        if (downloadExchangesLiveData == null) {
            downloadExchangesLiveData = new MutableLiveData<>();
        }
        return downloadExchangesLiveData;
    }

    private void setDownloadExchangesLiveData(DownloadStatus status) {
        getDownloadExchanges().setDownloadStatus(status);
        getDownloadExchanges().setFromCurrencyString(null);
        getDownloadExchanges().setToCurrencyString(null);
        getDownloadExchanges().setExchangeRate(null);
        getDownloadExchangesLiveData().setValue(getDownloadExchanges());
    }

    private void setDownloadExchangesLiveData(DownloadStatus status, String fromCurrencyString, String toCurrencyString, Double exchangeRate) {
        getDownloadExchanges().setDownloadStatus(status);
        getDownloadExchanges().setFromCurrencyString(fromCurrencyString);
        getDownloadExchanges().setToCurrencyString(toCurrencyString);
        getDownloadExchanges().setExchangeRate(exchangeRate);
        getDownloadExchangesLiveData().setValue(getDownloadExchanges());
    }

    private DownloadResultExchange getDownloadExchanges() {
        if (getDownloadExchangesLiveData().getValue() == null) {
            getDownloadExchangesLiveData().setValue(new DownloadResultExchange());
        }
        return getDownloadExchangesLiveData().getValue();
    }

    public LiveData<DownloadResultExchange> downloadExchanges() {
        int n = fromCurrencyList.size();
        setDownloadExchangesLiveData(DownloadStatus.STARTED);
        for (int i = 0; i < n; i++) {
            String fromCurrencyString = fromCurrencyList.get(i);
            ExchangeApi exchangeApi = ServiceGenerator.getExchangeApi();
            Call<ExchangeResponse> exchangeCall = exchangeApi.getExchange(fromCurrencyString.toLowerCase());
            int index = i; // needed for accessing into inner class
            deleteAllExchanges();
            exchangeCall.enqueue(new Callback<ExchangeResponse>() {
                @Override
                public void onResponse(@NonNull Call<ExchangeResponse> call, @NonNull Response<ExchangeResponse> response) {
                    if (response.isSuccessful()) {
                        ExchangeResponse exchangeResponse = response.body();
                        if (exchangeResponse != null) {
                            Map<String, Double> ratesMap = exchangeResponse.getRates();
                            if (ratesMap != null) {
                                for (Map.Entry<String, Double> entry : ratesMap.entrySet()) {
                                    String toCurrencyString = entry.getKey();
                                    Double rate = entry.getValue();
                                    if (toCurrencyString.length() == CURRENCY_LENGTH) {
                                        if (availableCurrencies.contains(java.util.Currency.getInstance(toCurrencyString)) && !fromCurrencyString.equals(toCurrencyString)) {
                                            setDownloadExchangesLiveData(DownloadStatus.PROCESSING, fromCurrencyString, toCurrencyString, rate);
                                        }
                                    }
                                }
                            }
                        }
                        else {
                            setDownloadExchangesLiveData(DownloadStatus.FAILURE);
                        }
                    }
                    else {
                        setDownloadExchangesLiveData(DownloadStatus.FAILURE);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ExchangeResponse> call, @NonNull Throwable t) {
                    setDownloadExchangesLiveData(DownloadStatus.FAILURE);
                }
            });
        }
        return getDownloadExchangesLiveData();
    }

    public LiveData<Integer> getLastCurrencyId() {
        return repository.getLastCurrencyId();
    }

    public LiveData<Integer> getLastExchangeId() {
        return repository.getLastExchangeId();
    }

    public LiveData<Currency> getCurrencyByString(String currencyString) {
        return repository.getCurrencyByString(currencyString);
    }

    public MutableLiveData<AccountViewModelFields> getAccountViewModelFieldsLiveData() {
        if (accountViewModelFieldsLiveData == null) {
            accountViewModelFieldsLiveData = new MutableLiveData<>();
        }
        return accountViewModelFieldsLiveData;
    }

    private AccountViewModelFields getAccountViewModelFields() {
        if (getAccountViewModelFieldsLiveData().getValue() == null) {
            setAccountViewModelFieldsLiveData(new AccountViewModelFields());
        }
        return getAccountViewModelFieldsLiveData().getValue();
    }

    private void setAccountViewModelFieldsLiveData(AccountViewModelFields accountViewModelFields) {
        getAccountViewModelFieldsLiveData().setValue(accountViewModelFields);
    }

    public void setAccountViewModelFieldBalance(Integer balanceCurrencyId) {
        getAccountViewModelFields().setBalanceCurrencyId(balanceCurrencyId);
        getAccountViewModelFieldsLiveData().setValue(getAccountViewModelFields());
    }

    public void setAccountViewModelFieldPlatfond(Integer platfondCurrencyId) {
        getAccountViewModelFields().setPlatfondCurrencyId(platfondCurrencyId);
        getAccountViewModelFieldsLiveData().setValue(getAccountViewModelFields());
    }

    public void setAccountViewModelFieldProceed(Boolean proceed) {
        getAccountViewModelFields().setProceed(proceed);
        getAccountViewModelFieldsLiveData().setValue(getAccountViewModelFields());
    }

    public void clearAccountViewModelFields() {
        getAccountViewModelFields().clear();
        getAccountViewModelFieldsLiveData().setValue(getAccountViewModelFields());
    }

    public LiveData<List<AccountHasCurrencies>> getAllAccountsHaveCurrencies() {
        return repository.getAllAccountsHaveCurrencies();
    }

    public LiveData<List<CategoryWithCurrency>> getAllCategoriesWithCurrencyByType(CategoryType type) {
        return repository.getAllCategoriesWithCurrencyByType(type);
    }

    public LiveData<List<CategoryWithCurrency>> getAllCategoriesWithCurrency() {
        return repository.getAllCategoriesWithCurrency();
    }

    public MutableLiveData<CategoryViewModelFields> getCategoryViewModelFieldsLiveData() {
        if (categoryViewModelFieldsLiveData == null) {
            categoryViewModelFieldsLiveData = new MutableLiveData<>();
        }
        return categoryViewModelFieldsLiveData;
    }

    private CategoryViewModelFields getCategoryViewModelFields() {
        if (getCategoryViewModelFieldsLiveData().getValue() == null) {
            setCategoryViewModelFieldsLiveData(new CategoryViewModelFields());
        }
        return getCategoryViewModelFieldsLiveData().getValue();
    }

    private void setCategoryViewModelFieldsLiveData(CategoryViewModelFields categoryViewModelFields) {
        getCategoryViewModelFieldsLiveData().setValue(categoryViewModelFields);
    }

    public void setCategoryViewModelFieldBalance(Integer balanceCurrencyId) {
        getCategoryViewModelFields().setBalanceCurrencyId(balanceCurrencyId);
        getCategoryViewModelFieldsLiveData().setValue(getCategoryViewModelFields());
    }

    public void setCategoryViewModelFieldProceed(Boolean proceed) {
        getCategoryViewModelFields().setProceed(proceed);
        getCategoryViewModelFieldsLiveData().setValue(getCategoryViewModelFields());
    }

    public void clearCategoryViewModelFields() {
        getCategoryViewModelFields().clear();
        getCategoryViewModelFieldsLiveData().setValue(getCategoryViewModelFields());
    }

}
