package com.marco.finbill.sql.transaction.transfer;

import androidx.room.Embedded;

import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.transaction.Transaction;

public class TransferIsTransactionWithRelationships {
    public int transferId;
    @Embedded
    public Transaction transaction;
    @Embedded(prefix = "from_")
    public Account fromTransfer;
    @Embedded(prefix = "to_")
    public Account toTransfer;
}
