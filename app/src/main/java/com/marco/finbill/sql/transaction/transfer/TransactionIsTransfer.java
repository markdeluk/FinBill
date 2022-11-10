package com.marco.finbill.sql.transaction.transfer;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.transaction.Transaction;

public class TransactionIsTransfer {

    @Embedded
    public Transaction transaction;

    @Relation(
            parentColumn = "transactionId",
            entityColumn = "transferId"
    )
    public Transfer transfer;

}
