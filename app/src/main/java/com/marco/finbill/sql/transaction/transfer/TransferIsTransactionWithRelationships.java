package com.marco.finbill.sql.transaction.transfer;

import androidx.room.Embedded;

import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.transaction.all.TransactionHasCurrency;

public class TransferIsTransactionWithRelationships {
    private int transferId;
    @Embedded
    private TransactionHasCurrency transactionHasCurrency;
    @Embedded(prefix = "from_")
    private Account fromTransfer;
    @Embedded(prefix = "to_")
    private Account toTransfer;

    public TransferIsTransactionWithRelationships(int transferId, TransactionHasCurrency transactionHasCurrency, Account fromTransfer, Account toTransfer) {
        this.transferId = transferId;
        this.transactionHasCurrency = transactionHasCurrency;
        this.fromTransfer = fromTransfer;
        this.toTransfer = toTransfer;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
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

    public boolean equals(TransferIsTransactionWithRelationships transferIsTransactionWithRelationships) {
        return this.transferId == transferIsTransactionWithRelationships.transferId && this.transactionHasCurrency.equals(transferIsTransactionWithRelationships.transactionHasCurrency) && this.fromTransfer.equals(transferIsTransactionWithRelationships.fromTransfer) && this.toTransfer.equals(transferIsTransactionWithRelationships.toTransfer);
    }
}
