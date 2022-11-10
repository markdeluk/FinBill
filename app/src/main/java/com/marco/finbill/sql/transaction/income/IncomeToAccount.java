package com.marco.finbill.sql.transaction.income;

import androidx.lifecycle.LiveData;
import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.account.Account;

import java.util.List;

public class IncomeToAccount {

    @Embedded
    Account account;

    @Relation(
            parentColumn = "accountId",
            entityColumn = "toIncome"
    )
    LiveData<List<Income>> incomes;

}