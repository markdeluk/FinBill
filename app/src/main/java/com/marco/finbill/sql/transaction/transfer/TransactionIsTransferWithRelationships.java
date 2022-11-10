package com.marco.finbill.sql.transaction.transfer;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.transaction.Transaction;

public class TransactionIsTransferWithRelationships {

    @Embedded
    public Transaction transaction;

    @Relation(
            parentColumn = "transactionId",
            entityColumn = "transferId"
    )
    public TransferWithRelationships transferWithRelationships;
}
