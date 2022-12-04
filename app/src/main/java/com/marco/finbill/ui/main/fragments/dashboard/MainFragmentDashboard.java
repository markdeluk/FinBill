package com.marco.finbill.ui.main.fragments.dashboard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.marco.finbill.R;
import com.marco.finbill.enums.TransactionType;
import com.marco.finbill.ui.main.dialogs.AddTransactionDialog;
import com.marco.finbill.ui.main.adapters.tabs.DashboardAdapter;

public class MainFragmentDashboard extends Fragment {

    private ViewPager2 viewPager2;

    public MainFragmentDashboard() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_dashboard, container, false);

        FloatingActionButton fab = requireActivity().findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DashboardAdapter dashboardAdapter = new DashboardAdapter(this);
        viewPager2 = view.findViewById(R.id.viewPager2);
        viewPager2.setAdapter(dashboardAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
                FloatingActionButton fab = requireActivity().findViewById(R.id.fab);

                View.OnLongClickListener transactionLongClickListener = view1 -> {
                    Snackbar.make(view1, R.string.add_transaction, Snackbar.LENGTH_LONG).show();
                    return false;
                };
                View.OnLongClickListener expensesLongClickListener = view2 -> {
                    Snackbar.make(view2, R.string.add_expense, Snackbar.LENGTH_LONG).show();
                    return false;
                };
                View.OnLongClickListener incomesLongClickListener = view3 -> {
                    Snackbar.make(view3, R.string.add_income, Snackbar.LENGTH_LONG).show();
                    return false;
                };
                View.OnLongClickListener transfersLongClickListener = view4 -> {
                    Snackbar.make(view4, R.string.add_transfer, Snackbar.LENGTH_LONG).show();
                    return false;
                };

                View.OnClickListener transactionOnClickListener = view5 -> AddTransactionDialog.display(getChildFragmentManager());
                View.OnClickListener expensesOnClickListener = view6 -> AddTransactionDialog.display(getChildFragmentManager(), TransactionType.EXPENSE);
                View.OnClickListener incomesOnClickListener = view7 -> AddTransactionDialog.display(getChildFragmentManager(), TransactionType.INCOME);
                View.OnClickListener transfersOnClickListener = view8 -> AddTransactionDialog.display(getChildFragmentManager(), TransactionType.TRANSFER);

                TransactionType tabPosition = TransactionType.values()[tab.getPosition()];
                switch (tabPosition) {
                    case DEFAULT:
                        fab.setOnClickListener(transactionOnClickListener);
                        fab.setOnLongClickListener(transactionLongClickListener);
                        break;
                    case EXPENSE:
                        fab.setOnClickListener(expensesOnClickListener);
                        fab.setOnLongClickListener(expensesLongClickListener);
                        break;
                    case INCOME:
                        fab.setOnClickListener(incomesOnClickListener);
                        fab.setOnLongClickListener(incomesLongClickListener);
                        break;
                    case TRANSFER:
                        fab.setOnClickListener(transfersOnClickListener);
                        fab.setOnLongClickListener(transfersLongClickListener);
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
            TransactionType tabPosition = TransactionType.values()[position];
            switch (tabPosition) {
                case DEFAULT:
                    tab.setText(R.string.transactions);
                    break;
                case EXPENSE:
                    tab.setText(R.string.expenses);
                    break;
                case INCOME:
                    tab.setText(R.string.incomes);
                    break;
                case TRANSFER:
                    tab.setText(R.string.transfers);
                    break;
            }
        }).attach();

    }
}