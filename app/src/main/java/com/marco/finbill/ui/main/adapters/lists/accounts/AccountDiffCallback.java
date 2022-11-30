package com.marco.finbill.ui.main.adapters.lists.accounts;

import androidx.recyclerview.widget.DiffUtil;

import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.account.AccountHasCurrencies;

import java.util.List;

public class AccountDiffCallback extends DiffUtil.Callback {

    private final List<AccountHasCurrencies> oldAccountList;
    private final List<AccountHasCurrencies> newAccountList;

    public AccountDiffCallback(List<AccountHasCurrencies> oldAccountList, List<AccountHasCurrencies> newAccountList) {
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
        return oldAccountList.get(oldItemPosition).getAccount().getAccountId() == newAccountList.get(newItemPosition).getAccount().getAccountId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldAccountList.get(oldItemPosition).equals(newAccountList.get(newItemPosition));
    }
}
