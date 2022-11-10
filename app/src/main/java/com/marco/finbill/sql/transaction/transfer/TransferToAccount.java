package com.marco.finbill.sql.transaction.transfer;

import androidx.lifecycle.LiveData;
import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.account.Account;

import java.util.List;

public class TransferToAccount {

    @Embedded
    Account account;

    @Relation(
            parentColumn = "accountId",
            entityColumn = "toTransfer"
    )
    LiveData<List<Transfer>> transfers;

}
