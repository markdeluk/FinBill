package com.marco.finbill.sql.transaction.transfer;

import androidx.room.Embedded;

import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.transaction.Transaction;

public class TransferIsTransactionWithRelationships {
    private int transferId;
    @Embedded
    private Transaction transaction;
    @Embedded(prefix = "from_")
    private Account fromTransfer;
    @Embedded(prefix = "to_")
    private Account toTransfer;

    public TransferIsTransactionWithRelationships(int transferId, Transaction transaction, Account fromTransfer, Account toTransfer) {
        this.transferId = transferId;
        this.transaction = transaction;
        this.fromTransfer = fromTransfer;
        this.toTransfer = toTransfer;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
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

    public boolean equals(TransferIsTransactionWithRelationships transferIsTransactionWithRelationships) {
        return this.transferId == transferIsTransactionWithRelationships.transferId && this.transaction.equals(transferIsTransactionWithRelationships.transaction) && this.fromTransfer.equals(transferIsTransactionWithRelationships.fromTransfer) && this.toTransfer.equals(transferIsTransactionWithRelationships.toTransfer);
    }
}
