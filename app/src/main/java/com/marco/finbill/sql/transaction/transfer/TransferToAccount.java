package com.marco.finbill.sql.transaction.transfer;

import androidx.room.Embedded;

import com.marco.finbill.sql.account.Account;

public class TransferToAccount {
    public int transferId;
    @Embedded
    public Account toTransfer;
}
