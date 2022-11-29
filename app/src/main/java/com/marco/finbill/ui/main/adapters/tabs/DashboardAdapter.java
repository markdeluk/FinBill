package com.marco.finbill.ui.main.adapters.tabs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.marco.finbill.enums.TransactionType;
import com.marco.finbill.ui.main.fragments.dashboard.DashboardExpenseFragment;
import com.marco.finbill.ui.main.fragments.dashboard.DashboardIncomeFragment;
import com.marco.finbill.ui.main.fragments.dashboard.DashboardTransactionFragment;
import com.marco.finbill.ui.main.fragments.dashboard.DashboardTransferFragment;

public class DashboardAdapter extends FragmentStateAdapter {

    @Override
    public int getItemCount() {
        return TransactionType.values().length;
    }

    public DashboardAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        TransactionType transactionPosition = TransactionType.values()[position];
        switch (transactionPosition) {
            case DEFAULT: // all transactions
                fragment = new DashboardTransactionFragment();
                break;
            case EXPENSE:
                fragment = new DashboardExpenseFragment();
                break;
            case INCOME:
                fragment = new DashboardIncomeFragment();
                break;
            case TRANSFER:
                fragment = new DashboardTransferFragment();
                break;
        }
        Bundle args = new Bundle();
        if (fragment != null) {
            fragment.setArguments(args);
        }
        return fragment;
    }
}
