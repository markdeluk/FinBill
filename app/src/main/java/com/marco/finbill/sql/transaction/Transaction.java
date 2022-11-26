package com.marco.finbill.sql.transaction;

import android.graphics.Bitmap;
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
    private String transactionName;
    private String transactionDescription;
    private TransactionType transactionType;
    private String transactionCurrencyString;
    private double transactionAmount;
    private Date transactionDate;
    private Time transactionTime;
    private TransactionFrequency transactionFrequency;
    private int transactionInfoLasting;
    private TransactionRecurrency transactionInfoRecurrent;
    private boolean transactionNotify;
    private TransactionNotifyFrequency transactionNotifyFrequency;
    private String transactionNotes;
    private Bitmap transactionImage;
    private Location transactionLocation;
    private PriorityType transactionPriority;

    public Transaction(String transactionName, String transactionDescription, TransactionType transactionType, String transactionCurrencyString, double transactionAmount, Date transactionDate, Time transactionTime, TransactionFrequency transactionFrequency, int transactionInfoLasting, TransactionRecurrency transactionInfoRecurrent, boolean transactionNotify, TransactionNotifyFrequency transactionNotifyFrequency, String transactionNotes, Bitmap transactionImage, Location transactionLocation, PriorityType transactionPriority) {
        this.transactionName = transactionName;
        this.transactionDescription = transactionDescription;
        this.transactionType = transactionType;
        this.transactionCurrencyString = transactionCurrencyString;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.transactionTime = transactionTime;
        this.transactionFrequency = transactionFrequency;
        this.transactionInfoLasting = transactionInfoLasting;
        this.transactionInfoRecurrent = transactionInfoRecurrent;
        this.transactionNotify = transactionNotify;
        this.transactionNotifyFrequency = transactionNotifyFrequency;
        this.transactionNotes = transactionNotes;
        this.transactionImage = transactionImage;
        this.transactionLocation = transactionLocation;
        this.transactionPriority = transactionPriority;
    }

    public Transaction() {
        this.transactionName = null;
        this.transactionDescription = null;
        this.transactionType = null;
        this.transactionCurrencyString = null;
        this.transactionAmount = 0;
        this.transactionDate = null;
        this.transactionTime = null;
        this.transactionFrequency = null;
        this.transactionInfoLasting = 0;
        this.transactionInfoRecurrent = null;
        this.transactionNotify = false;
        this.transactionNotifyFrequency = null;
        this.transactionNotes = null;
        this.transactionImage = null;
        this.transactionLocation = null;
        this.transactionPriority = null;
    }

    // Visibility methods

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionCurrencyString() {
        return transactionCurrencyString;
    }

    public void setTransactionCurrencyString(String transactionCurrencyString) {
        this.transactionCurrencyString = transactionCurrencyString;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Time getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Time transactionTime) {
        this.transactionTime = transactionTime;
    }

    public TransactionFrequency getTransactionFrequency() {
        return transactionFrequency;
    }

    public void setTransactionFrequency(TransactionFrequency transactionFrequency) {
        this.transactionFrequency = transactionFrequency;
    }

    public int getTransactionInfoLasting() {
        return transactionInfoLasting;
    }

    public void setTransactionInfoLasting(int transactionInfoLasting) {
        this.transactionInfoLasting = transactionInfoLasting;
    }

    public TransactionRecurrency getTransactionInfoRecurrent() {
        return transactionInfoRecurrent;
    }

    public void setTransactionInfoRecurrent(TransactionRecurrency transactionInfoRecurrent) {
        this.transactionInfoRecurrent = transactionInfoRecurrent;
    }

    public boolean getTransactionNotify() {
        return transactionNotify;
    }

    public void setTransactionNotify(boolean transactionNotify) {
        this.transactionNotify = transactionNotify;
    }

    public TransactionNotifyFrequency getTransactionNotifyFrequency() {
        return transactionNotifyFrequency;
    }

    public void setTransactionNotifyFrequency(TransactionNotifyFrequency transactionNotifyFrequency) {
        this.transactionNotifyFrequency = transactionNotifyFrequency;
    }

    public String getTransactionNotes() {
        return transactionNotes;
    }

    public void setTransactionNotes(String transactionNotes) {
        this.transactionNotes = transactionNotes;
    }

    public Bitmap getTransactionImage() {
        return transactionImage;
    }

    public void setTransactionImage(Bitmap transactionImage) {
        this.transactionImage = transactionImage;
    }

    public Location getTransactionLocation() {
        return transactionLocation;
    }

    public void setTransactionLocation(Location transactionLocation) {
        this.transactionLocation = transactionLocation;
    }

    public PriorityType getTransactionPriority() {
        return transactionPriority;
    }

    public void setTransactionPriority(PriorityType transactionPriority) {
        this.transactionPriority = transactionPriority;
    }

    public boolean equals(Transaction transaction) {
        return this.transactionId == transaction.transactionId &&
                this.transactionName.equals(transaction.transactionName) &&
                this.transactionDescription.equals(transaction.transactionDescription) &&
                this.transactionType == transaction.transactionType &&
                this.transactionCurrencyString.equals(transaction.transactionCurrencyString) &&
                this.transactionAmount == transaction.transactionAmount &&
                this.transactionDate.equals(transaction.transactionDate) &&
                this.transactionTime.equals(transaction.transactionTime) &&
                this.transactionFrequency == transaction.transactionFrequency &&
                this.transactionInfoLasting == transaction.transactionInfoLasting &&
                this.transactionInfoRecurrent.equals(transaction.transactionInfoRecurrent) &&
                this.transactionNotify == transaction.transactionNotify &&
                this.transactionNotifyFrequency == transaction.transactionNotifyFrequency &&
                this.transactionNotes.equals(transaction.transactionNotes) &&
                this.transactionImage.equals(transaction.transactionImage) &&
                this.transactionLocation.equals(transaction.transactionLocation) &&
                this.transactionPriority == transaction.transactionPriority;
    }

    public boolean isValid() {
        // Mandatory fields must be filled in
        return this.transactionName != null &&
                this.transactionType != TransactionType.DEFAULT &&
                this.transactionCurrencyString != null &&
                this.transactionAmount != 0 &&
                this.transactionDate != null &&
                (this.transactionFrequency != TransactionFrequency.DEFAULT &&
                        (this.transactionFrequency != TransactionFrequency.LASTING || this.transactionInfoLasting != 0) &&
                        (this.transactionFrequency != TransactionFrequency.RECURRENT || this.transactionInfoRecurrent != TransactionRecurrency.DEFAULT)) &&
                (!this.transactionNotify || this.transactionNotifyFrequency != TransactionNotifyFrequency.DEFAULT);
    }
}
