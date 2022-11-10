package com.marco.finbill.sql.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

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
import com.marco.finbill.sql.transaction.expense.ExpenseWithRelationships;
import com.marco.finbill.sql.transaction.expense.TransactionIsExpenseWithRelationships;
import com.marco.finbill.sql.transaction.income.Income;
import com.marco.finbill.sql.transaction.income.IncomeDao;
import com.marco.finbill.sql.transaction.transfer.Transfer;
import com.marco.finbill.sql.transaction.transfer.TransferDao;
import com.marco.finbill.sql.type_converters.BitmapTypeConverter;
import com.marco.finbill.sql.type_converters.CurrencyTypeConverter;
import com.marco.finbill.sql.type_converters.DateTypeConverter;
import com.marco.finbill.sql.type_converters.LocationTypeConverter;
import com.marco.finbill.sql.type_converters.TimeTypeConverter;

@Database(entities = {Account.class, Category.class, Exchange.class, Expense.class, Income.class, Transfer.class}, version = 1)
@TypeConverters({CurrencyTypeConverter.class, DateTypeConverter.class, TimeTypeConverter.class, LocationTypeConverter.class, BitmapTypeConverter.class})
public abstract class FinBillDatabase extends RoomDatabase {
    private static FinBillDatabase instance;
    public abstract AccountDao accountDao();
    public abstract CategoryDao categoryDao();
    public abstract ExchangeDao exchangeDao();
    public abstract ExpenseDao expenseDao();
    public abstract IncomeDao incomeDao();
    public abstract TransferDao transferDao();

    public static synchronized FinBillDatabase getInstance(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            FinBillDatabase.class, "finbill_database")
                    /*.addTypeConverter(new CurrencyTypeConverter())
                    .addTypeConverter(new DateTypeConverter())
                    .addTypeConverter(new TimeTypeConverter())
                    .addTypeConverter(new LocationTypeConverter())
                    .addTypeConverter(new BitmapTypeConverter())*/
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
