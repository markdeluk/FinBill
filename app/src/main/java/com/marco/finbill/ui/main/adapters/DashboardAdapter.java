package com.marco.finbill.ui.main.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.marco.finbill.ui.main.fragments.dashboard.DashboardExpenseFragment;
import com.marco.finbill.ui.main.fragments.dashboard.DashboardIncomeFragment;
import com.marco.finbill.ui.main.fragments.dashboard.DashboardTransactionFragment;
import com.marco.finbill.ui.main.fragments.dashboard.DashboardTransferFragment;

public class DashboardAdapter extends FragmentStateAdapter {

    public final int TRANSACTIONS = 0;
    public final int EXPENSES = 1;
    public final int INCOMES = 2;
    public final int TRANSFERS = 3;

    public final int NUM_PAGES = 4;

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }

    public DashboardAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case TRANSACTIONS:
                fragment = new DashboardTransactionFragment();
                break;
            case EXPENSES:
                fragment = new DashboardExpenseFragment();
                break;
            case INCOMES:
                fragment = new DashboardIncomeFragment();
                break;
            case TRANSFERS:
                fragment = new DashboardTransferFragment();
                break;
            default:
                break;
        }
        Bundle args = new Bundle();
        assert fragment != null;
        fragment.setArguments(args);
        return fragment;
    }
}
