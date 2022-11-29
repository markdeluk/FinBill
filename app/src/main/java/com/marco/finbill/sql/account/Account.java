package com.marco.finbill.sql.account;

import android.graphics.Bitmap;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.marco.finbill.enums.AccountType;
import com.marco.finbill.enums.PriorityType;

import java.util.Date;

@Entity(tableName = "account_table", indices = {@Index(value = {"accountName"}, unique = true)})
public class Account {
    @PrimaryKey(autoGenerate = true)
    private int accountId;
    private String accountName;
    @Nullable
    private String accountDescription;
    private AccountType accountType;
    private Double accountBalance;
    private String accountBalanceCurrency;
    @Nullable
    private Double accountPlatfond;
    @Nullable
    private String accountPlatfondCurrency;
    private Date accountAdded;
    @Nullable
    private Date accountCreated;
    @Nullable
    private Bitmap accountImage;
    private PriorityType accountPriority;

    public Account() {
        this.accountName = null;
        this.accountDescription = null;
        this.accountType = null;
        this.accountBalance = (double) 0;
        this.accountBalanceCurrency = null;
        this.accountPlatfond = null;
        this.accountPlatfondCurrency = null;
        this.accountAdded = null;
        this.accountCreated = null;
        this.accountImage = null;
        this.accountPriority = PriorityType.LOW;
    }

    public Account(String accountName, @Nullable String accountDescription, AccountType accountType, @Nullable Double accountBalance, String accountBalanceCurrency, @Nullable Double accountPlatfond, Date accountAdded, @Nullable Date accountCreated, @Nullable Bitmap accountImage, PriorityType accountPriority) {
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

    @Nullable
    public String getAccountDescription() {
        return accountDescription;
    }

    public void setAccountDescription(@Nullable String accountDescription) {
        this.accountDescription = accountDescription;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Double getAccountBalance() {
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

    @Nullable
    public Double getAccountPlatfond() {
        return accountPlatfond;
    }

    public void setAccountPlatfond(double accountPlatfond) {
        this.accountPlatfond = accountPlatfond;
    }

    @Nullable
    public String getAccountPlatfondCurrency() {
        return accountPlatfondCurrency;
    }

    public void setAccountPlatfondCurrency(@Nullable String accountPlatfondCurrency) {
        this.accountPlatfondCurrency = accountPlatfondCurrency;
    }

    public Date getAccountAdded() {
        return accountAdded;
    }

    public void setAccountAdded(Date accountAdded) {
        this.accountAdded = accountAdded;
    }

    @Nullable
    public Date getAccountCreated() {
        return accountCreated;
    }

    public void setAccountCreated(@Nullable Date accountCreated) {
        this.accountCreated = accountCreated;
    }

    @Nullable
    public Bitmap getAccountImage() {
        return accountImage;
    }

    public void setAccountImage(@Nullable Bitmap accountImage) {
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
                (this.accountDescription == null && account.getAccountDescription() == null || this.accountDescription.equals(account.getAccountDescription())) &&
                this.accountType.equals(account.getAccountType()) &&
                (this.accountBalance == null && account.getAccountBalance() == null || this.accountBalance.equals(account.getAccountBalance())) &&
                (this.accountPlatfond == null && account.getAccountPlatfond() == null || this.accountPlatfond.equals(account.getAccountPlatfond())) &&
                this.accountAdded.equals(account.getAccountAdded()) &&
                (this.accountImage == null && account.getAccountImage() == null || this.accountImage.equals(account.getAccountImage())) &&
                (this.accountPriority == null && account.getAccountPriority() == null || this.accountPriority.equals(account.getAccountPriority()));
    }

    public boolean isValid() {
        return this.accountName != null &&
                this.accountType != null &&
                this.accountBalanceCurrency != null &&
                this.accountPlatfondCurrency != null;
    }
}
