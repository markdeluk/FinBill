package com.marco.finbill.ui.main.fragments.categories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.marco.finbill.R;
import com.marco.finbill.enums.CategoryType;
import com.marco.finbill.ui.main.adapters.tabs.CategoriesAdapter;
import com.marco.finbill.ui.main.dialogs.AddCategoryDialog;

public class MainFragmentCategories extends Fragment {

    private ViewPager2 viewPager2;

    public MainFragmentCategories() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(this);
        viewPager2 = view.findViewById(R.id.viewPager2);
        viewPager2.setAdapter(categoriesAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
                FloatingActionButton fab = requireActivity().findViewById(R.id.fab);
                View.OnLongClickListener allCategoriesLongClickListener = view1 -> {
                    Snackbar.make(view1, R.string.add_category, Snackbar.LENGTH_LONG).show();
                    return false;
                };
                View.OnLongClickListener expenseCategoriesLongClickListener = view2 -> {
                    Snackbar.make(view2, R.string.add_category_expense, Snackbar.LENGTH_LONG).show();
                    return false;
                };
                View.OnLongClickListener incomeCategoriesLongClickListener = view3 -> {
                    Snackbar.make(view3, R.string.add_category_income, Snackbar.LENGTH_LONG).show();
                    return false;
                };

                View.OnClickListener allCategoriesOnClickListener = view4 -> AddCategoryDialog.display(getChildFragmentManager());
                View.OnClickListener expenseCategoriesOnClickListener = view5 -> AddCategoryDialog.display(getChildFragmentManager(), CategoryType.EXPENSE);
                View.OnClickListener incomeCategoriesOnClickListener = view6 -> AddCategoryDialog.display(getChildFragmentManager(), CategoryType.INCOME);

                CategoryType tabPosition = CategoryType.values()[tab.getPosition()];
                switch (tabPosition) {
                    case DEFAULT:
                        fab.setOnClickListener(allCategoriesOnClickListener);
                        fab.setOnLongClickListener(allCategoriesLongClickListener);
                        break;
                    case EXPENSE:
                        fab.setOnClickListener(expenseCategoriesOnClickListener);
                        fab.setOnLongClickListener(expenseCategoriesLongClickListener);
                        break;
                    case INCOME:
                        fab.setOnClickListener(incomeCategoriesOnClickListener);
                        fab.setOnLongClickListener(incomeCategoriesLongClickListener);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }

        });

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            CategoryType tabPosition = CategoryType.values()[position];
            switch (tabPosition) {
                case DEFAULT:
                    tab.setText(R.string.all_categories);
                    break;
                case EXPENSE:
                    tab.setText(R.string.expense_categories);
                    break;
                case INCOME:
                    tab.setText(R.string.income_categories);
                    break;
            }
        }).attach();

    }
}