package com.marco.finbill.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.marco.finbill.exchange_api.ServiceGenerator;
import com.marco.finbill.exchange_api.ExchangeApi;
import com.marco.finbill.exchange_api.ExchangeResponse;
import com.marco.finbill.enums.CategoryType;
import com.marco.finbill.model.auxiliary_entity_fields.AccountFields;
import com.marco.finbill.model.auxiliary_entity_fields.CategoryFields;
import com.marco.finbill.model.auxiliary_entity_fields.TransactionFields;
import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.account.AccountDao;
import com.marco.finbill.sql.account.AccountWithCurrencies;
import com.marco.finbill.sql.account.AccountWithCurrenciesDao;
import com.marco.finbill.sql.category.Category;
import com.marco.finbill.sql.category.CategoryDao;
import com.marco.finbill.sql.category.CategoryWithCurrency;
import com.marco.finbill.sql.category.CategoryWithCurrencyDao;
import com.marco.finbill.sql.currency.Currency;
import com.marco.finbill.sql.currency.CurrencyDao;
import com.marco.finbill.sql.exchange.Exchange;
import com.marco.finbill.sql.exchange.ExchangeDao;
import com.marco.finbill.sql.transaction.all.Transaction;
import com.marco.finbill.sql.transaction.all.TransactionDao;
import com.marco.finbill.sql.transaction.expense.Expense;
import com.marco.finbill.sql.transaction.expense.ExpenseDao;
import com.marco.finbill.sql.transaction.expense.ExpenseRelationshipsDao;
import com.marco.finbill.sql.transaction.expense.ExpenseRelationships;
import com.marco.finbill.sql.transaction.income.Income;
import com.marco.finbill.sql.transaction.income.IncomeDao;
import com.marco.finbill.sql.transaction.income.IncomeRelationships;
import com.marco.finbill.sql.transaction.income.IncomeRelationshipsDao;
import com.marco.finbill.sql.transaction.transfer.Transfer;
import com.marco.finbill.sql.transaction.transfer.TransferDao;
import com.marco.finbill.sql.transaction.transfer.TransferRelationships;
import com.marco.finbill.sql.transaction.transfer.TransferRelationshipsDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinBillRepository {

    private static FinBillRepository instance;

    private ExpenseRelationshipsDao expenseRelationshipsDao;
    private IncomeRelationshipsDao incomeRelationshipsDao;
    private TransferRelationshipsDao transferRelationshipsDao;
    private TransactionDao transactionDao;
    private ExpenseDao expenseDao;
    private IncomeDao incomeDao;
    private TransferDao transferDao;
    private AccountDao accountDao;
    private AccountWithCurrenciesDao accountWithCurrenciesDao;
    private CategoryDao categoryDao;
    private CategoryWithCurrencyDao categoryWithCurrencyDao;
    private CurrencyDao currencyDao;
    private ExchangeDao exchangeDao;

    private ExecutorService executorService;

    private final MutableLiveData<TransactionFields> transactionFieldsLiveData = new MutableLiveData<>();
    private final MutableLiveData<AccountFields> accountFieldsLiveData = new MutableLiveData<>();
    private final MutableLiveData<CategoryFields> categoryFieldsLiveData = new MutableLiveData<>();
    private final MutableLiveData<Exchange> downloadedExchangeLiveData = new MutableLiveData<>();

    private FinBillRepository(Application application){
        FinBillDatabase database = FinBillDatabase.getInstance(application);

        accountDao = database.accountDao();
        accountWithCurrenciesDao = database.accountHasCurrenciesDao();
        categoryDao = database.categoryDao();
        categoryWithCurrencyDao = database.categoryWithCurrencyDao();
        currencyDao = database.currencyCodeDao();
        exchangeDao = database.exchangeDao();
        expenseRelationshipsDao = database.expenseIsTransactionWithRelationshipsDao();
        incomeRelationshipsDao = database.incomeIsTransactionWithRelationshipsDao();
        transferRelationshipsDao = database.transferIsTransactionWithRelationshipsDao();
        transactionDao = database.transactionDao();
        expenseDao = database.expenseDao();
        incomeDao = database.incomeDao();
        transferDao = database.transferDao();

        executorService = Executors.newFixedThreadPool(4);
    }

    public static FinBillRepository getInstance(Application application){
        if (instance == null) instance = new FinBillRepository(application);
        return instance;
    }

    public LiveData<List<ExpenseRelationships>> getAllExpenses() {
        return expenseRelationshipsDao.getAllExpenseIsTransactionWithRelationships();
    }

    public LiveData<List<IncomeRelationships>> getAllIncomes() {
        return incomeRelationshipsDao.getAllIncomeIsTransactionWithRelationships();
    }

    public LiveData<List<TransferRelationships>> getAllTransfers() {
        return transferRelationshipsDao.getAllTransferIsTransactionWithRelationships();
    }

    public LiveData<List<Account>> getAllAccounts() {
        return accountDao.getAllAccounts();
    }

    public LiveData<List<Category>> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    public LiveData<List<Category>> getAllCategoriesByType(CategoryType type) {
        return categoryDao.getAllCategoriesByType(type);
    }

    public LiveData<Long> insertTransaction(Transaction transaction) {
        MutableLiveData<Long> data = new MutableLiveData<>();
        executorService.execute(() -> data.postValue(transactionDao.insertTransaction(transaction)));
        return data;
    }

    public void updateTransaction(Transaction transaction) {
        executorService.execute(() -> transactionDao.updateTransaction(transaction));
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

    public LiveData<Account> getAccountByName(String name) {
        return accountDao.getAccountByName(name);
    }

    public LiveData<Category> getCategoryByName(String name) {
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

    public LiveData<List<Currency>> getAllCurrencies() {
        return currencyDao.getAllCurrencies();
    }

    public void deleteAllCurrencies() {
        executorService.execute(() -> currencyDao.deleteAllCurrencies());
    }

    public void insertAccount(Account account) {
        executorService.execute(() -> accountDao.insertAccount(account));
    }

    public void insertCategory(Category category) {
        executorService.execute(() -> categoryDao.insertCategory(category));
    }

    public LiveData<Currency> getCurrencyByString(String currencyString) {
        return currencyDao.getCurrencyByString(currencyString);
    }

    public void insertCurrencies(List<Currency> currencies) {
        executorService.execute(() -> currencyDao.insertCurrencies(currencies));
    }

    public LiveData<List<AccountWithCurrencies>> getAllAccountsHaveCurrencies() {
        return accountWithCurrenciesDao.getAllAccountsHaveCurrencies();
    }

    public LiveData<List<CategoryWithCurrency>> getAllCategoriesWithCurrencyByType(CategoryType type) {
        return categoryWithCurrencyDao.getAllCategoriesWithCurrencyByType(type);
    }

    public LiveData<List<CategoryWithCurrency>> getAllCategoriesWithCurrency() {
        return categoryWithCurrencyDao.getAllCategoriesWithCurrency();
    }

    public LiveData<Integer> getNumberOfExchanges() {
        return exchangeDao.getNumberOfExchanges();
    }

    public LiveData<List<ExpenseRelationships>> getAllExpenseIsTransactionWithRelationships() {
        return expenseRelationshipsDao.getAllExpenseIsTransactionWithRelationships();
    }

    public LiveData<List<IncomeRelationships>> getAllIncomeIsTransactionWithRelationships() {
        return incomeRelationshipsDao.getAllIncomeIsTransactionWithRelationships();
    }

    public LiveData<List<TransferRelationships>> getAllTransferIsTransactionWithRelationships() {
        return transferRelationshipsDao.getAllTransferIsTransactionWithRelationships();
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

    // Auxiloary method for adding an exchange

    public LiveData<Exchange> getDownloadedExchange() {
        return downloadedExchangeLiveData;
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

}
