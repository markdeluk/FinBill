package com.marco.finbill.sql.account;

import android.graphics.Bitmap;
import android.icu.util.Currency;
import android.media.Image;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "account_table")
public class Account {
    @PrimaryKey(autoGenerate = true)
    private int accountId;
    private String name;
    private String description;
    private int type;
    private Currency currency;
    private float balance;
    private float limit;
    private Date added;
    private Bitmap image;
    private int priority;

    private enum Type {
        CASH, BANK, CREDIT_CARD, DEBIT_CARD, DEPOSIT, ONLINE_ACCOUNT, CRYPTO, OTHER
    }

    public Account(String name, String description, int type, Currency currency, float balance, float limit, Date added, Bitmap image, int priority) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.currency = currency;
        this.balance = balance;
        this.limit = limit;
        this.added = added;
        this.image = image;
        this.priority = priority;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getLimit() {
        return limit;
    }

    public void setLimit(float limit) {
        this.limit = limit;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
