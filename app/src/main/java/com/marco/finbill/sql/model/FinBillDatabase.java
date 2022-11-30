package com.marco.finbill.sql.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.account.AccountDao;
import com.marco.finbill.sql.account.AccountHasCurrenciesDao;
import com.marco.finbill.sql.category.Category;
import com.marco.finbill.sql.category.CategoryDao;
import com.marco.finbill.sql.category.CategoryWithCurrencyDao;
import com.marco.finbill.sql.currency.Currency;
import com.marco.finbill.sql.currency.CurrencyDao;
import com.marco.finbill.sql.exchange.Exchange;
import com.marco.finbill.sql.exchange.ExchangeDao;
import com.marco.finbill.sql.transaction.all.Transaction;
import com.marco.finbill.sql.transaction.all.TransactionDao;
import com.marco.finbill.sql.transaction.expense.Expense;
import com.marco.finbill.sql.transaction.expense.ExpenseDao;
import com.marco.finbill.sql.transaction.expense.ExpenseIsTransactionWithRelationshipsDao;
import com.marco.finbill.sql.transaction.income.Income;
import com.marco.finbill.sql.transaction.income.IncomeDao;
import com.marco.finbill.sql.transaction.income.IncomeIsTransactionWithRelationshipsDao;
import com.marco.finbill.sql.transaction.transfer.Transfer;
import com.marco.finbill.sql.transaction.transfer.TransferDao;
import com.marco.finbill.sql.transaction.transfer.TransferIsTransactionWithRelationshipsDao;
import com.marco.finbill.sql.type_converters.BitmapTypeConverter;
import com.marco.finbill.sql.type_converters.DateTypeConverter;
import com.marco.finbill.sql.type_converters.LocationTypeConverter;
import com.marco.finbill.sql.type_converters.TimeTypeConverter;
import com.marco.finbill.sql.type_converters.enums.AccountTypeTypeConverter;
import com.marco.finbill.sql.type_converters.enums.CategoryTypeTypeConverter;
import com.marco.finbill.sql.type_converters.enums.PriorityTypeTypeConverter;
import com.marco.finbill.sql.type_converters.enums.TransactionFrequencyTypeConverter;
import com.marco.finbill.sql.type_converters.enums.TransactionNotifyFrequencyTypeConverter;
import com.marco.finbill.sql.type_converters.enums.TransactionRecurrencyTypeConverter;
import com.marco.finbill.sql.type_converters.enums.TransactionTypeTypeConverter;

@Database(entities = {Account.class, Category.class, Currency.class, Exchange.class, Expense.class, Income.class, Transfer.class, Transaction.class}, version = 1)
@TypeConverters({AccountTypeTypeConverter.class, CategoryTypeTypeConverter.class, PriorityTypeTypeConverter.class, TransactionFrequencyTypeConverter.class, TransactionNotifyFrequencyTypeConverter.class, TransactionRecurrencyTypeConverter.class, TransactionTypeTypeConverter.class, DateTypeConverter.class, TimeTypeConverter.class, LocationTypeConverter.class, BitmapTypeConverter.class})
public abstract class FinBillDatabase extends RoomDatabase {
    private static FinBillDatabase instance;
    public abstract AccountDao accountDao();
    public abstract AccountHasCurrenciesDao accountHasCurrenciesDao();
    public abstract CategoryDao categoryDao();
    public abstract CategoryWithCurrencyDao categoryWithCurrencyDao();
    public abstract CurrencyDao currencyCodeDao();
    public abstract ExchangeDao exchangeDao();
    public abstract ExpenseIsTransactionWithRelationshipsDao expenseIsTransactionWithRelationshipsDao();
    public abstract IncomeIsTransactionWithRelationshipsDao incomeIsTransactionWithRelationshipsDao();
    public abstract TransferIsTransactionWithRelationshipsDao transferIsTransactionWithRelationshipsDao();
    public abstract TransactionDao transactionDao();
    public abstract ExpenseDao expenseDao();
    public abstract IncomeDao incomeDao();
    public abstract TransferDao transferDao();

    public static synchronized FinBillDatabase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), FinBillDatabase.class, "finbill_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
