package com.marco.finbill.sql.transaction.all;

import android.graphics.Bitmap;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.marco.finbill.enums.PriorityType;
import com.marco.finbill.enums.TransactionFrequency;
import com.marco.finbill.enums.TransactionNotifyFrequency;
import com.marco.finbill.enums.TransactionRecurrency;
import com.marco.finbill.enums.TransactionType;

import java.util.Date;
import java.sql.Time;

@Entity(tableName = "transaction_table")
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    private int transactionId;
    private String transactionName;
    @Nullable
    private String transactionDescription;
    private TransactionType transactionType;
    private Integer transactionCurrencyId;
    private double transactionAmount;
    private Date transactionDate;
    private Time transactionTime;
    private TransactionFrequency transactionFrequency;
    private int transactionInfoLasting;
    private TransactionRecurrency transactionInfoRecurrent;
    private boolean transactionNotify;
    private TransactionNotifyFrequency transactionNotifyFrequency;
    @Nullable
    private String transactionNotes;
    @Nullable
    private Bitmap transactionImage;
    private PriorityType transactionPriority;

    public Transaction(String transactionName, @Nullable String transactionDescription, TransactionType transactionType, Integer transactionCurrencyId, double transactionAmount, Date transactionDate, Time transactionTime, TransactionFrequency transactionFrequency, int transactionInfoLasting, TransactionRecurrency transactionInfoRecurrent, boolean transactionNotify, TransactionNotifyFrequency transactionNotifyFrequency, @Nullable String transactionNotes, @Nullable Bitmap transactionImage, PriorityType transactionPriority) {
        this.transactionName = transactionName;
        this.transactionDescription = transactionDescription;
        this.transactionType = transactionType;
        this.transactionCurrencyId = transactionCurrencyId;
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
        this.transactionPriority = transactionPriority;
    }

    @Ignore
    public Transaction() {
        this.transactionName = null;
        this.transactionDescription = null;
        this.transactionType = TransactionType.DEFAULT;
        this.transactionCurrencyId = null;
        this.transactionAmount = 0;
        this.transactionDate = new Date(System.currentTimeMillis());
        this.transactionTime = new Time(System.currentTimeMillis());
        this.transactionFrequency = TransactionFrequency.ONCE;
        this.transactionInfoLasting = 0;
        this.transactionInfoRecurrent = null;
        this.transactionNotify = false;
        this.transactionNotifyFrequency = null;
        this.transactionNotes = null;
        this.transactionImage = null;
        this.transactionPriority = PriorityType.LOW;
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

    @Nullable
    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(@Nullable String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Integer getTransactionCurrencyId() {
        return transactionCurrencyId;
    }

    public void setTransactionCurrencyId(Integer transactionCurrencyId) {
        this.transactionCurrencyId = transactionCurrencyId;
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

    @Nullable
    public String getTransactionNotes() {
        return transactionNotes;
    }

    public void setTransactionNotes(@Nullable String transactionNotes) {
        this.transactionNotes = transactionNotes;
    }

    @Nullable
    public Bitmap getTransactionImage() {
        return transactionImage;
    }

    public void setTransactionImage(@Nullable Bitmap transactionImage) {
        this.transactionImage = transactionImage;
    }

    public PriorityType getTransactionPriority() {
        return transactionPriority;
    }

    public void setTransactionPriority(PriorityType transactionPriority) {
        this.transactionPriority = transactionPriority;
    }

    public boolean equals(Transaction transaction) {
        return this.transactionId == transaction.getTransactionId() &&
                this.transactionName.equals(transaction.getTransactionName()) &&
                (this.transactionDescription == null && transaction.getTransactionDescription() == null || this.transactionDescription.equals(transaction.getTransactionDescription())) &&
                this.transactionType == transaction.getTransactionType() &&
                this.transactionCurrencyId.equals(transaction.getTransactionCurrencyId()) &&
                this.transactionAmount == transaction.getTransactionAmount() &&
                this.transactionDate.equals(transaction.getTransactionDate()) &&
                (this.transactionTime == null && transaction.getTransactionTime() == null || this.transactionTime.equals(transaction.getTransactionTime())) &&
                this.transactionFrequency == transaction.getTransactionFrequency() &&
                this.transactionInfoLasting == transaction.getTransactionInfoLasting() &&
                this.transactionInfoRecurrent.equals(transaction.getTransactionInfoRecurrent()) &&
                this.transactionNotify == transaction.getTransactionNotify() &&
                this.transactionNotifyFrequency == transaction.getTransactionNotifyFrequency() &&
                (this.transactionNotes == null && transaction.getTransactionNotes() == null || this.transactionNotes.equals(transaction.getTransactionNotes())) &&
                (this.transactionImage == null && transaction.getTransactionImage() == null || this.transactionImage.equals(transaction.getTransactionImage())) &&
                this.transactionPriority == transaction.getTransactionPriority();
    }

    public boolean isValid() {
        // Mandatory fields must be filled in
        return this.transactionName != null &&
                this.transactionType != TransactionType.DEFAULT &&
                this.transactionCurrencyId != null &&
                this.transactionAmount != 0 &&
                (
                        (this.transactionFrequency != TransactionFrequency.LASTING || this.transactionInfoLasting != 0) &&
                        (this.transactionFrequency != TransactionFrequency.RECURRENT || this.transactionInfoRecurrent != TransactionRecurrency.DEFAULT)
                ) &&
                (!this.transactionNotify || this.transactionNotifyFrequency != TransactionNotifyFrequency.DEFAULT);
    }
}
