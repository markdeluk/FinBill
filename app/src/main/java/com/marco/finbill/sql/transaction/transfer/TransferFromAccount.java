package com.marco.finbill.sql.transaction.transfer;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.account.Account;

public class TransferFromAccount {

    @Embedded Transfer transfer;

    @Relation(
            parentColumn = "accountId",
            entityColumn = "fromTransfer"
    )
    Account fromAccount;
}
