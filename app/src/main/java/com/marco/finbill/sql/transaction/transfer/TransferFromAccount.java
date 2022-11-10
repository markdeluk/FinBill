package com.marco.finbill.sql.transaction.transfer;

import androidx.lifecycle.LiveData;
import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.account.Account;

import java.util.List;

public class TransferFromAccount {

    @Embedded
    Account account;

    @Relation(
            parentColumn = "accountId",
            entityColumn = "fromTransfer"
    )
    LiveData<List<Transfer>> transfers;
}
