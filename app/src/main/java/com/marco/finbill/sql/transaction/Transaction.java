package com.marco.finbill.sql.transaction;

import android.graphics.Bitmap;
import android.icu.util.Currency;
import android.location.Location;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.sql.Time;

@Entity(tableName = "transaction_table")
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    private int transactionId;
    private String name;
    private String description;
    private int type;/*
    private int fromExpense;
    private int fromIncome;
    private int fromTransfer;
    private int toExpense;
    private int toIncome;
    private int toTransfer;*/
    private Currency currency;
    private float amount;
    private Date date;
    private Time time;
    private int frequency;
    private String infoOnce;
    private String infoLasting;
    private String infoRecurrent;
    private int notify;
    private int notifyFrequency;
    private String notes;
    private Bitmap image;
    private Location location;
    private int priority;

    public enum Type {
        EXPENSE, INCOME, TRANSFER
    }

    public enum Frequency {
        ONCE, LASTING, RECURRENT
    }

    public enum Notify {
        NO, YES
    }

    public enum NotifyFrequency {
        NONE, DAILY, WEEKLY, MONTHLY, YEARLY
    }

    public Transaction(String name, String description, int type, int fromExpense, int fromIncome, int fromTransfer, int toExpense, int toIncome, int toTransfer, Currency currency, float amount, Date date, Time time, int frequency, String infoOnce, String infoLasting, String infoRecurrent, int notify, int notifyFrequency, String notes, Bitmap image, Location location, int priority) {
        this.name = name;
        this.description = description;
        this.type = type;/*
        this.fromExpense = fromExpense;
        this.fromIncome = fromIncome;
        this.fromTransfer = fromTransfer;
        this.toExpense = toExpense;
        this.toIncome = toIncome;
        this.toTransfer = toTransfer;*/
        this.currency = currency;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.frequency = frequency;
        this.infoOnce = infoOnce;
        this.infoLasting = infoLasting;
        this.infoRecurrent = infoRecurrent;
        this.notify = notify;
        this.notifyFrequency = notifyFrequency;
        this.notes = notes;
        this.image = image;
        this.location = location;
        this.priority = priority;
    }

    // Visibility methods

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
/*
    public int getFromExpense() {
        return fromExpense;
    }

    public void setFromExpense(int fromExpense) {
        this.fromExpense = fromExpense;
    }

    public int getFromIncome() {
        return fromIncome;
    }

    public void setFromIncome(int fromIncome) {
        this.fromIncome = fromIncome;
    }

    public int getFromTransfer() {
        return fromTransfer;
    }

    public void setFromTransfer(int fromTransfer) {
        this.fromTransfer = fromTransfer;
    }

    public int getToExpense() {
        return toExpense;
    }

    public void setToExpense(int toExpense) {
        this.toExpense = toExpense;
    }

    public int getToIncome() {
        return toIncome;
    }

    public void setToIncome(int toIncome) {
        this.toIncome = toIncome;
    }

    public int getToTransfer() {
        return toTransfer;
    }

    public void setToTransfer(int toTransfer) {
        this.toTransfer = toTransfer;
    }
*/
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getInfoOnce() {
        return infoOnce;
    }

    public void setInfoOnce(String infoOnce) {
        this.infoOnce = infoOnce;
    }

    public String getInfoLasting() {
        return infoLasting;
    }

    public void setInfoLasting(String infoLasting) {
        this.infoLasting = infoLasting;
    }

    public String getInfoRecurrent() {
        return infoRecurrent;
    }

    public void setInfoRecurrent(String infoRecurrent) {
        this.infoRecurrent = infoRecurrent;
    }

    public int getNotify() {
        return notify;
    }

    public void setNotify(int notify) {
        this.notify = notify;
    }

    public int getNotifyFrequency() {
        return notifyFrequency;
    }

    public void setNotifyFrequency(int notifyFrequency) {
        this.notifyFrequency = notifyFrequency;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

}
