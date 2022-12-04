package com.marco.finbill.ui.main.adapters.lists.incomes;

import androidx.recyclerview.widget.DiffUtil;

import com.marco.finbill.sql.transaction.income.IncomeRelationships;

import java.util.List;

public class IncomeDiffCallback extends DiffUtil.Callback {

    private final List<IncomeRelationships> oldIncomeList;
    private final List<IncomeRelationships> newIncomeList;

    public IncomeDiffCallback(List<IncomeRelationships> oldIncomeList, List<IncomeRelationships> newIncomeList) {
        this.oldIncomeList = oldIncomeList;
        this.newIncomeList = newIncomeList;
    }

    @Override
    public int getOldListSize() {
        return oldIncomeList.size();
    }

    @Override
    public int getNewListSize() {
        return newIncomeList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldIncomeList.get(oldItemPosition).getIncome().getIncomeId() == newIncomeList.get(newItemPosition).getIncome().getIncomeId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldIncomeList.get(oldItemPosition).equals(newIncomeList.get(newItemPosition));
    }
}
