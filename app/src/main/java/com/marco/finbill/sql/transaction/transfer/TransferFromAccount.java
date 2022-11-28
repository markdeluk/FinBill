package com.marco.finbill.sql.transaction.transfer;

import androidx.room.Embedded;

import com.marco.finbill.sql.account.Account;

public class TransferFromAccount {
    private int transferId;
    @Embedded
    private Account fromTransfer;

    public TransferFromAccount(int transferId, Account fromTransfer) {
        this.transferId = transferId;
        this.fromTransfer = fromTransfer;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public Account getFromTransfer() {
        return fromTransfer;
    }

    public void setFromTransfer(Account fromTransfer) {
        this.fromTransfer = fromTransfer;
    }
}
