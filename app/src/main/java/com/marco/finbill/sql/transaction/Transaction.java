package com.marco.finbill.sql.transaction;

import android.graphics.Bitmap;
import android.icu.util.Currency;
import android.location.Location;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.marco.finbill.enums.PriorityType;
import com.marco.finbill.enums.TransactionFrequency;
import com.marco.finbill.enums.TransactionNotifyFrequency;
import com.marco.finbill.enums.TransactionRecurrency;
import com.marco.finbill.enums.TransactionType;

import java.sql.Date;
import java.sql.Time;

@Entity(tableName = "transaction_table")
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    private int transactionId;
    private String name;
    private String description;
    private TransactionType transactionType;
    private Currency currency;
    private double amount;
    private Date date;
    private Time time;
    private TransactionFrequency frequency;
    private int infoLasting;
    private TransactionRecurrency infoRecurrent;
    private boolean notify;
    private TransactionNotifyFrequency notifyFrequency;
    private String notes;
    private Bitmap image;
    private Location location;
    private PriorityType priority;

    public Transaction(String name, String description, TransactionType transactionType, Currency currency, double amount, Date date, Time time, TransactionFrequency frequency, int infoLasting, TransactionRecurrency infoRecurrent, boolean notify, TransactionNotifyFrequency notifyFrequency, String notes, Bitmap image, Location location, PriorityType priority) {
        this.name = name;
        this.description = description;
        this.transactionType = transactionType;
        this.currency = currency;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.frequency = frequency;
        this.infoLasting = infoLasting;
        this.infoRecurrent = infoRecurrent;
        this.notify = notify;
        this.notifyFrequency = notifyFrequency;
        this.notes = notes;
        this.image = image;
        this.location = location;
        this.priority = priority;
    }

    public Transaction() {
        this.name = null;
        this.description = null;
        this.transactionType = null;
        this.currency = null;
        this.amount = 0;
        this.date = null;
        this.time = null;
        this.frequency = null;
        this.infoLasting = 0;
        this.infoRecurrent = null;
        this.notify = false;
        this.notifyFrequency = null;
        this.notes = null;
        this.image = null;
        this.location = null;
        this.priority = null;
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

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
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

    public TransactionFrequency getFrequency() {
        return frequency;
    }

    public void setFrequency(TransactionFrequency frequency) {
        this.frequency = frequency;
    }

    public int getInfoLasting() {
        return infoLasting;
    }

    public void setInfoLasting(int infoLasting) {
        this.infoLasting = infoLasting;
    }

    public TransactionRecurrency getInfoRecurrent() {
        return infoRecurrent;
    }

    public void setInfoRecurrent(TransactionRecurrency infoRecurrent) {
        this.infoRecurrent = infoRecurrent;
    }

    public boolean getNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }

    public TransactionNotifyFrequency getNotifyFrequency() {
        return notifyFrequency;
    }

    public void setNotifyFrequency(TransactionNotifyFrequency notifyFrequency) {
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

    public PriorityType getPriority() {
        return priority;
    }

    public void setPriority(PriorityType priority) {
        this.priority = priority;
    }

    public boolean equals(Transaction transaction) {
        return this.transactionId == transaction.transactionId &&
                this.name.equals(transaction.name) &&
                this.description.equals(transaction.description) &&
                this.transactionType == transaction.transactionType &&
                this.currency.equals(transaction.currency) &&
                this.amount == transaction.amount &&
                this.date.equals(transaction.date) &&
                this.time.equals(transaction.time) &&
                this.frequency == transaction.frequency &&
                this.infoLasting == transaction.infoLasting &&
                this.infoRecurrent.equals(transaction.infoRecurrent) &&
                this.notify == transaction.notify &&
                this.notifyFrequency == transaction.notifyFrequency &&
                this.notes.equals(transaction.notes) &&
                this.image.equals(transaction.image) &&
                this.location.equals(transaction.location) &&
                this.priority == transaction.priority;
    }

    public boolean isValid() {
        // Mandatory fields must be filled in
        return this.name != null &&
                this.transactionType != TransactionType.DEFAULT &&
                this.currency != null &&
                this.amount != 0 &&
                this.date != null &&
                (this.frequency != TransactionFrequency.DEFAULT &&
                        (this.frequency != TransactionFrequency.LASTING || this.infoLasting != 0) &&
                        (this.frequency != TransactionFrequency.RECURRENT || this.infoRecurrent != TransactionRecurrency.DEFAULT)) &&
                (!this.notify || this.notifyFrequency != TransactionNotifyFrequency.DEFAULT);
    }
}
