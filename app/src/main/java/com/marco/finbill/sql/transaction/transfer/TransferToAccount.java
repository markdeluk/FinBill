package com.marco.finbill.sql.transaction.transfer;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.account.Account;

public class TransferToAccount {

    @Embedded Transfer transfer;

    @Relation(
            parentColumn = "accountId",
            entityColumn = "toTransfer"
    )
    Account account;

}
