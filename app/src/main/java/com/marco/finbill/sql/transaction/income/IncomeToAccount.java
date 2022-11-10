package com.marco.finbill.sql.transaction.income;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.account.Account;

public class IncomeToAccount {

    @Embedded
    Income income;

    @Relation(
            parentColumn = "accountId",
            entityColumn = "toIncome"
    )
    Account account;

}