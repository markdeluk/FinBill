package com.marco.finbill.sql.transaction.transfer;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.account.Account;

public class TransferWithRelationships {

    @Embedded
    public Transfer transfer;

    @Relation(
            parentColumn = "fromTransfer",
            entityColumn = "accountId"
    )
    public Account fromTransfer;

    @Relation(
            parentColumn = "toTransfer",
            entityColumn = "accountId"
    )
    public Account toTransfer;
}
