package com.marco.finbill.sql.transaction;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.marco.finbill.sql.currency.Currency;

@Entity(tableName = "Transaction")
public class Transaction {
    /* Name, Type, FromExpense, FromIncome, FromTransfer, ToExpense, ToIncome,
    ToTransfer, Currency, Amount, Date, Time, Frequency, InfoOnce, InfoLasting,
    InfoRecurrent, Notify, NotifyFrequency, Notes, Picture, Location, Priority
    */
    @PrimaryKey(autoGenerate = true)
    private int transactionId;
    private String name;
    private int type;
    private int fromExpense;
    private int fromIncome;
    private int fromTransfer;
    private int toExpense;
    private int toIncome;
    private int toTransfer;
    private Currency currency;
    private double amount;
    private String date;
    private String time;
    private int frequency;
    private String infoOnce;
    private String infoLasting;
    private String infoRecurrent;
    private int notify;
    private int notifyFrequency;
    private String notes;
    private String picture;
    private String location;
    private int priority;
}
