package com.marco.finbill.sql.account;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.marco.finbill.enums.AccountType;
import com.marco.finbill.enums.PriorityType;

import java.sql.Date;

@Entity(tableName = "account_table", indices = {@Index(value = {"accountName"}, unique = true)})
public class Account {
    @PrimaryKey(autoGenerate = true)
    private int accountId;
    private String accountName;
    private String accountDescription;
    private AccountType accountType;
    private double accountBalance;
    private String accountBalanceCurrency;
    private double accountPlatfond;
    private String accountPlatfondCurrency;
    private Date accountAdded;
    private Date accountCreated;
    private Bitmap accountImage;
    private PriorityType accountPriority;

    public Account() {
        this.accountName = null;
        this.accountDescription = null;
        this.accountType = null;
        this.accountBalance = Integer.MIN_VALUE;
        this.accountBalanceCurrency = null;
        this.accountPlatfond = Integer.MIN_VALUE;
        this.accountPlatfondCurrency = null;
        this.accountAdded = null;
        this.accountCreated = null;
        this.accountImage = null;
        this.accountPriority = null;

    }

    public Account(String accountName, String accountDescription, AccountType accountType, double accountBalance, String accountBalanceCurrency, double accountPlatfond, Date accountAdded, Date accountCreated, Bitmap accountImage, PriorityType accountPriority) {
        this.accountName = accountName;
        this.accountDescription = accountDescription;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
        this.accountBalanceCurrency = accountBalanceCurrency;
        this.accountPlatfond = accountPlatfond;
        this.accountAdded = accountAdded;
        this.accountCreated = accountCreated;
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

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountBalanceCurrency() {
        return accountBalanceCurrency;
    }

    public void setAccountBalanceCurrency(String accountBalanceCurrency) {
        this.accountBalanceCurrency = accountBalanceCurrency;
    }

    public double getAccountPlatfond() {
        return accountPlatfond;
    }

    public void setAccountPlatfond(double accountPlatfond) {
        this.accountPlatfond = accountPlatfond;
    }

    public String getAccountPlatfondCurrency() {
        return accountPlatfondCurrency;
    }

    public void setAccountPlatfondCurrency(String accountPlatfondCurrency) {
        this.accountPlatfondCurrency = accountPlatfondCurrency;
    }

    public Date getAccountAdded() {
        return accountAdded;
    }

    public void setAccountAdded(Date accountAdded) {
        this.accountAdded = accountAdded;
    }

    public Date getAccountCreated() {
        return accountCreated;
    }

    public void setAccountCreated(Date accountCreated) {
        this.accountCreated = accountCreated;
    }

    public Bitmap getAccountImage() {
        return accountImage;
    }

    public void setAccountImage(Bitmap accountImage) {
        this.accountImage = accountImage;
    }

    public PriorityType getAccountPriority() {
        return accountPriority;
    }

    public void setAccountPriority(PriorityType accountPriority) {
        this.accountPriority = accountPriority;
    }

    public boolean equals(Account account) {
        return this.accountId == account.getAccountId() &&
                this.accountName.equals(account.getAccountName()) &&
                this.accountDescription.equals(account.getAccountDescription()) &&
                this.accountType.equals(account.getAccountType()) &&
                this.accountBalance == account.getAccountBalance() &&
                this.accountPlatfond == account.getAccountPlatfond() &&
                this.accountAdded.equals(account.getAccountAdded()) &&
                this.accountImage.equals(account.getAccountImage()) &&
                this.accountPriority == account.getAccountPriority();
    }

    public boolean isValid() {
        return this.accountName != null &&
                this.accountType != null &&
                this.accountBalance != Integer.MIN_VALUE &&
                this.accountBalanceCurrency != null &&
                this.accountPlatfond != Integer.MIN_VALUE &&
                this.accountPlatfondCurrency != null;
    }
}
