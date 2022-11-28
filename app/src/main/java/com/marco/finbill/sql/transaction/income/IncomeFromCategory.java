package com.marco.finbill.sql.transaction.income;

import androidx.room.Embedded;

import com.marco.finbill.sql.category.Category;

public class IncomeFromCategory {
    private int incomeId;
    @Embedded
    private Category category;

    public IncomeFromCategory(int incomeId, Category category) {
        this.incomeId = incomeId;
        this.category = category;
    }

    public int getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(int incomeId) {
        this.incomeId = incomeId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
