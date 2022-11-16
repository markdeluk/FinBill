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
    private int transactionType;
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

    public Transaction(String name, String description, int transactionType, Currency currency, float amount, Date date, Time time, int frequency, String infoOnce, String infoLasting, String infoRecurrent, int notify, int notifyFrequency, String notes, Bitmap image, Location location, int priority) {
        this.name = name;
        this.transactionType = transactionType;
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

    public int getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }

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
                this.infoOnce.equals(transaction.infoOnce) &&
                this.infoLasting.equals(transaction.infoLasting) &&
                this.infoRecurrent.equals(transaction.infoRecurrent) &&
                this.notify == transaction.notify &&
                this.notifyFrequency == transaction.notifyFrequency &&
                this.notes.equals(transaction.notes) &&
                this.image.equals(transaction.image) &&
                this.location.equals(transaction.location) &&
                this.priority == transaction.priority;
    }

}
