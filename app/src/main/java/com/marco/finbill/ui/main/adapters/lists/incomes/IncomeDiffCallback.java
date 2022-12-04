package com.marco.finbill.ui.main.adapters.lists.incomes;

import androidx.recyclerview.widget.DiffUtil;

import com.marco.finbill.sql.transaction.income.IncomeIsTransactionWithRelationships;

import java.util.List;

public class IncomeDiffCallback extends DiffUtil.Callback {

    private final List<IncomeIsTransactionWithRelationships> oldIncomeList;
    private final List<IncomeIsTransactionWithRelationships> newIncomeList;

    public IncomeDiffCallback(List<IncomeIsTransactionWithRelationships> oldIncomeList, List<IncomeIsTransactionWithRelationships> newIncomeList) {
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
        return oldIncomeList.get(oldItemPosition).getIncomeId() == newIncomeList.get(newItemPosition).getIncomeId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldIncomeList.get(oldItemPosition).equals(newIncomeList.get(newItemPosition));
    }
}
