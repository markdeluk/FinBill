package com.marco.finbill.ui.main.dashboard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

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
                fragment = new TransactionFragment();
                break;
            case EXPENSES:
                fragment = new ExpenseFragment();
                break;
            case INCOMES:
                fragment = new IncomeFragment();
                break;
            case TRANSFERS:
                fragment = new TransferFragment();
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
