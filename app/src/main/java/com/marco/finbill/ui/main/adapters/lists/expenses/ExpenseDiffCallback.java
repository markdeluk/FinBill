package com.marco.finbill.ui.main.adapters.lists.expenses;

import androidx.recyclerview.widget.DiffUtil;

import com.marco.finbill.sql.transaction.expense.ExpenseIsTransactionWithRelationships;

import java.util.List;

public class ExpenseDiffCallback extends DiffUtil.Callback {

    private final List<ExpenseIsTransactionWithRelationships> oldExpenseList;
    private final List<ExpenseIsTransactionWithRelationships> newExpenseList;

    public ExpenseDiffCallback(List<ExpenseIsTransactionWithRelationships> oldExpenseList, List<ExpenseIsTransactionWithRelationships> newExpenseList) {
        this.oldExpenseList = oldExpenseList;
        this.newExpenseList = newExpenseList;
    }

    @Override
    public int getOldListSize() {
        return oldExpenseList.size();
    }

    @Override
    public int getNewListSize() {
        return newExpenseList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldExpenseList.get(oldItemPosition).getExpense().getExpenseId() == newExpenseList.get(newItemPosition).getExpense().getExpenseId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldExpenseList.get(oldItemPosition).equals(newExpenseList.get(newItemPosition));
    }
}
