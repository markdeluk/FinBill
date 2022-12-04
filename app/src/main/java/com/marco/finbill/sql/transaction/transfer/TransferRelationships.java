package com.marco.finbill.sql.transaction.transfer;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.transaction.all.Transaction;
import com.marco.finbill.sql.transaction.all.TransactionHasCurrency;
public class TransferRelationships {

    @Embedded
    private Transfer transfer;

    @Relation(
            entity = Transaction.class,
            parentColumn = "transferId",
            entityColumn = "transactionId"
    )
    private TransactionHasCurrency transactionHasCurrency;

    @Relation(
            parentColumn = "fromTransfer",
            entityColumn = "accountId"
    )
    private Account fromTransfer;

    @Relation(
            parentColumn = "toTransfer",
            entityColumn = "accountId"
    )
    private Account toTransfer;

    public TransferRelationships(Transfer transfer, TransactionHasCurrency transactionHasCurrency, Account fromTransfer, Account toTransfer) {
        this.transfer = transfer;
        this.transactionHasCurrency = transactionHasCurrency;
        this.fromTransfer = fromTransfer;
        this.toTransfer = toTransfer;
    }

    public Transfer getTransfer() {
        return transfer;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }

    public TransactionHasCurrency getTransactionHasCurrency() {
        return transactionHasCurrency;
    }

    public void setTransactionHasCurrency(TransactionHasCurrency transactionHasCurrency) {
        this.transactionHasCurrency = transactionHasCurrency;
    }

    public Account getFromTransfer() {
        return fromTransfer;
    }

    public void setFromTransfer(Account fromTransfer) {
        this.fromTransfer = fromTransfer;
    }

    public Account getToTransfer() {
        return toTransfer;
    }

    public void setToTransfer(Account toTransfer) {
        this.toTransfer = toTransfer;
    }

    public boolean equals(TransferRelationships transferRelationships) {
        return this.transfer.equals(transferRelationships.transfer) &&
                this.transactionHasCurrency.equals(transferRelationships.transactionHasCurrency) &&
                this.fromTransfer.equals(transferRelationships.fromTransfer) &&
                this.toTransfer.equals(transferRelationships.toTransfer);
    }
}