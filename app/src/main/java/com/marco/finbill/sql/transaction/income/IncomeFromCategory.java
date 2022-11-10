package com.marco.finbill.sql.transaction.income;

import androidx.lifecycle.LiveData;
import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.category.Category;

import java.util.List;

public class IncomeFromCategory {

    @Embedded
    Category category;

    @Relation(
            parentColumn = "categoryId",
            entityColumn = "fromIncome"
    )
    LiveData<List<Income>> incomes;

}
