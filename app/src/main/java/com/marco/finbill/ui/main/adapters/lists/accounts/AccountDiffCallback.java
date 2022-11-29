package com.marco.finbill.ui.main.adapters.lists.accounts;

import androidx.recyclerview.widget.DiffUtil;

import com.marco.finbill.sql.account.Account;

import java.util.List;

public class AccountDiffCallback extends DiffUtil.Callback {

    private final List<Account> oldAccountList;
    private final List<Account> newAccountList;

    public AccountDiffCallback(List<Account> oldAccountList, List<Account> newAccountList) {
        this.oldAccountList = oldAccountList;
        this.newAccountList = newAccountList;
    }

    @Override
    public int getOldListSize() {
        return oldAccountList.size();
    }

    @Override
    public int getNewListSize() {
        return newAccountList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldAccountList.get(oldItemPosition).getAccountId() == newAccountList.get(newItemPosition).getAccountId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldAccountList.get(oldItemPosition).equals(newAccountList.get(newItemPosition));
    }
}
