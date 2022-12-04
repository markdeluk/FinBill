package com.marco.finbill.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.marco.finbill.enums.CategoryType;
import com.marco.finbill.model.auxiliary_entity_fields.AccountFields;
import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.account.AccountWithCurrencies;
import com.marco.finbill.sql.category.Category;
import com.marco.finbill.sql.category.CategoryWithCurrency;
import com.marco.finbill.sql.currency.Currency;
import com.marco.finbill.sql.exchange.Exchange;
import com.marco.finbill.model.auxiliary_entity_fields.CategoryFields;
import com.marco.finbill.model.auxiliary_entity_fields.TransactionFields;
import com.marco.finbill.sql.transaction.all.Transaction;
import com.marco.finbill.sql.transaction.expense.Expense;
import com.marco.finbill.sql.transaction.expense.ExpenseRelationships;
import com.marco.finbill.sql.transaction.income.Income;
import com.marco.finbill.sql.transaction.income.IncomeRelationships;
import com.marco.finbill.sql.transaction.transfer.Transfer;
import com.marco.finbill.sql.transaction.transfer.TransferRelationships;

import java.util.List;

public class FinBillViewModel extends AndroidViewModel {

    private final FinBillRepository repository;

    public FinBillViewModel(Application application) {
        super(application);
        repository = FinBillRepository.getInstance(application);
    }

    public LiveData<List<ExpenseRelationships>> getAllExpenses() {
        return repository.getAllExpenses();
    }

    public LiveData<List<IncomeRelationships>> getAllIncomes() {
        return repository.getAllIncomes();
    }

    public LiveData<List<TransferRelationships>> getAllTransfers() {
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

    public LiveData<List<AccountWithCurrencies>> getAllAccountsHaveCurrencies() {
        return repository.getAllAccountsHaveCurrencies();
    }

    public LiveData<List<CategoryWithCurrency>> getAllCategoriesWithCurrencyByType(CategoryType type) {
        return repository.getAllCategoriesWithCurrencyByType(type);
    }

    public LiveData<List<CategoryWithCurrency>> getAllCategoriesWithCurrency() {
        return repository.getAllCategoriesWithCurrency();
    }

    public LiveData<Integer> getNumberOfExchanges() {
        return repository.getNumberOfExchanges();
    }

    public LiveData<List<ExpenseRelationships>> getAllExpenseIsTransactionWithRelationships() {
        return repository.getAllExpenseIsTransactionWithRelationships();
    }

    public LiveData<List<IncomeRelationships>> getAllIncomeIsTransactionWithRelationships() {
        return repository.getAllIncomeIsTransactionWithRelationships();
    }

    public LiveData<List<TransferRelationships>> getAllTransferIsTransactionWithRelationships() {
        return repository.getAllTransferIsTransactionWithRelationships();
    }

    public void downloadExchange(Currency fromCurrency, Currency toCurrency) {
        repository.downloadExchange(fromCurrency, toCurrency);
    }

    public LiveData<Exchange> getDownloadedExchange() {
        return repository.getDownloadedExchange();
    }

    public LiveData<AccountFields> pullAccountFieldsLiveData() {
        return repository.pullAccountFieldsLiveData();
    }

    public void pushAccountFieldBalance(Integer balanceCurrencyId) {
        repository.pushAccountFieldBalance(balanceCurrencyId);
    }

    public void pushAccountFieldPlatfond(Integer platfondCurrencyId) {
        repository.pushAccountFieldPlatfond(platfondCurrencyId);
    }

    public void pushAccountFieldProceed(Boolean proceed) {
        repository.pushAccountFieldProceed(proceed);
    }

    public void popAccountFields() {
        repository.popAccountFields();
    }

    public LiveData<CategoryFields> pullCategoryFieldsLiveData() {
        return repository.pullCategoryFieldsLiveData();
    }

    public void pushCategoryFieldBalance(Integer balanceCurrencyId) {
        repository.pushCategoryFieldBalance(balanceCurrencyId);
    }

    public void pushCategoryFieldProceed(Boolean proceed) {
        repository.pushCategoryFieldProceed(proceed);
    }

    public void popCategoryFields() {
        repository.popCategoryFields();
    }

    public LiveData<TransactionFields> pullTransactionFieldsLiveData() {
        return repository.pullTransactionFieldsLiveData();
    }

    public void pushTransactionFieldId(Integer id) {
        repository.pushTransactionFieldId(id);
    }

    public void pushTransactionFieldCurrencyId(Integer id) {
        repository.pushTransactionFieldCurrencyId(id);
    }

    public void pushTransactionFieldFrom(Object from) {
        repository.pushTransactionFieldFrom(from);
    }

    public void pushTransactionFieldTo(Object to) {
        repository.pushTransactionFieldTo(to);
    }

    public void popTransactionFields() {
        repository.popTransactionFields();
    }

}
