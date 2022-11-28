package com.marco.finbill.sql.transaction.transfer;

public class TransferIsTransaction {
    private int transferId;
    private int transactionId;

    public TransferIsTransaction(int transferId, int transactionId) {
        this.transferId = transferId;
        this.transactionId = transactionId;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
}
