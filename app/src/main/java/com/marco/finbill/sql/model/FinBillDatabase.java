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
import com.marco.finbill.sql.type_converters.BitmapTypeConverter;
import com.marco.finbill.sql.type_converters.CurrencyTypeConverter;
import com.marco.finbill.sql.type_converters.DateTypeConverter;
import com.marco.finbill.sql.type_converters.LocationTypeConverter;
import com.marco.finbill.sql.type_converters.TimeTypeConverter;

@Database(entities = {Account.class, Category.class, Exchange.class, Transaction.class}, version = 1)
@TypeConverters({CurrencyTypeConverter.class, DateTypeConverter.class, TimeTypeConverter.class, LocationTypeConverter.class, BitmapTypeConverter.class})
public abstract class FinBillDatabase extends RoomDatabase {
    private static FinBillDatabase instance;
    public abstract AccountDao accountDao();
    public abstract CategoryDao categoryDao();
    public abstract ExchangeDao exchangeDao();
    public abstract TransactionDao transactionDao();

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
