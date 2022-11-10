package com.marco.finbill.sql.transaction.income;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.category.Category;

public class IncomeFromCategory {

    @Embedded
    Income income;

    @Relation(
            parentColumn = "categoryId",
            entityColumn = "fromIncome"
    )
    Category category;

}
