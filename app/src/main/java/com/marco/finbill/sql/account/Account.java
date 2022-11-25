package com.marco.finbill.sql.account;

import android.graphics.Bitmap;
import android.icu.util.Currency;
import android.media.Image;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "account_table", indices = {@Index(value = {"accountName"}, unique = true)})
public class Account {
    @PrimaryKey(autoGenerate = true)
    private int accountId;
    private String accountName;
    private String accountDescription;
    private int accountType;
    private float accountBalance;
    private float accountPlatfond;
    private Date accountAdded;
    private Bitmap accountImage;
    private int accountPriority;

    private enum Type {
        CASH, BANK, CREDIT_CARD, DEBIT_CARD, DEPOSIT, ONLINE_ACCOUNT, OTHER
    }

    public Account(String accountName, String accountDescription, int accountType, float accountBalance, float accountPlatfond, Date accountAdded, Bitmap accountImage, int accountPriority) {
        this.accountName = accountName;
        this.accountDescription = accountDescription;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
        this.accountPlatfond = accountPlatfond;
        this.accountAdded = accountAdded;
        this.accountImage = accountImage;
        this.accountPriority = accountPriority;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountDescription() {
        return accountDescription;
    }

    public void setAccountDescription(String accountDescription) {
        this.accountDescription = accountDescription;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public float getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(float accountBalance) {
        this.accountBalance = accountBalance;
    }

    public float getAccountPlatfond() {
        return accountPlatfond;
    }

    public void setAccountPlatfond(float accountPlatfond) {
        this.accountPlatfond = accountPlatfond;
    }

    public Date getAccountAdded() {
        return accountAdded;
    }

    public void setAccountAdded(Date accountAdded) {
        this.accountAdded = accountAdded;
    }

    public Bitmap getAccountImage() {
        return accountImage;
    }

    public void setAccountImage(Bitmap accountImage) {
        this.accountImage = accountImage;
    }

    public int getAccountPriority() {
        return accountPriority;
    }

    public void setAccountPriority(int accountPriority) {
        this.accountPriority = accountPriority;
    }

    public boolean equals(Account account) {
        return this.accountId == account.getAccountId() &&
                this.accountName.equals(account.getAccountName()) &&
                this.accountDescription.equals(account.getAccountDescription()) &&
                this.accountType == account.getAccountType() &&
                this.accountBalance == account.getAccountBalance() &&
                this.accountPlatfond == account.getAccountPlatfond() &&
                this.accountAdded.equals(account.getAccountAdded()) &&
                this.accountImage.equals(account.getAccountImage()) &&
                this.accountPriority == account.getAccountPriority();
    }
}
