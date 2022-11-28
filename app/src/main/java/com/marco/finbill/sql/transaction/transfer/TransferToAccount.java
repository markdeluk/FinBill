package com.marco.finbill.sql.transaction.transfer;

import androidx.room.Embedded;

import com.marco.finbill.sql.account.Account;

public class TransferToAccount {
    private int transferId;
    @Embedded
    private Account toTransfer;

    public TransferToAccount(int transferId, Account toTransfer) {
        this.transferId = transferId;
        this.toTransfer = toTransfer;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public Account getToTransfer() {
        return toTransfer;
    }

    public void setToTransfer(Account toTransfer) {
        this.toTransfer = toTransfer;
    }
}
