package com.marco.finbill.sql.transaction.transfer;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.transaction.Transaction;

public class TransactionIsTransfer {

    @Embedded
    Transaction transaction;

    @Relation(
            parentColumn = "transactionId",
            entityColumn = "transferId"
    )
    Transfer transfer;

}
