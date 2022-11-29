package com.marco.finbill.ui.main.adapters.tabs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.marco.finbill.enums.CategoryType;
import com.marco.finbill.ui.main.fragments.categories.CategoriesAllFragment;
import com.marco.finbill.ui.main.fragments.categories.CategoriesExpenseFragment;
import com.marco.finbill.ui.main.fragments.categories.CategoriesIncomeFragment;

public class CategoriesAdapter extends FragmentStateAdapter {
    @Override
    public int getItemCount() {
        return CategoryType.values().length;
    }

    public CategoriesAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        CategoryType categoryPosition = CategoryType.values()[position];
        switch (categoryPosition) {
            case DEFAULT:
                fragment = new CategoriesAllFragment();
                break;
            case EXPENSE:
                fragment = new CategoriesExpenseFragment();
                break;
            case INCOME:
                fragment = new CategoriesIncomeFragment();
                break;
        }
        Bundle args = new Bundle();
        if (fragment != null) {
            fragment.setArguments(args);
        }
        return fragment;
    }
}
