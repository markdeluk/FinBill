package com.marco.finbill.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.marco.finbill.R;
import com.marco.finbill.ui.main.dashboard.AddTransactionDialog;
import com.marco.finbill.ui.main.dashboard.DashboardAdapter;

public class MainFragmentDashboard extends Fragment {

    private final int TRANSACTIONS = 0;
    public final int EXPENSES = 1;
    public final int INCOMES = 2;
    public final int TRANSFERS = 3;

    private DashboardAdapter dashboardAdapter;
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
        fab.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.add_icon, requireActivity().getTheme()));

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dashboardAdapter = new DashboardAdapter(this);
        viewPager2 = view.findViewById(R.id.viewPager2);
        viewPager2.setAdapter(dashboardAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
                FloatingActionButton fab = requireActivity().findViewById(R.id.fab);
                switch (tab.getPosition()) {
                    case TRANSACTIONS:
                        fab.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AddTransactionDialog.display(requireActivity().getSupportFragmentManager());
                            }
                        });
                        fab.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View view) {
                                Snackbar.make(view, "Transactions", Snackbar.LENGTH_LONG).show();
                                return false;
                            }
                        });
                        break;
                    case EXPENSES:
                        fab.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View view) {
                                Snackbar.make(view, "Expenses", Snackbar.LENGTH_LONG).show();
                                return false;
                            }
                        });
                        break;
                    case INCOMES:
                        fab.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View view) {
                                Snackbar.make(view, "Incomes", Snackbar.LENGTH_LONG).show();
                                return false;
                            }
                        });
                        break;
                    case TRANSFERS:
                        fab.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View view) {
                                Snackbar.make(view, "Transfers", Snackbar.LENGTH_LONG).show();
                                return false;
                            }
                        });
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
            switch (position) {
                case TRANSACTIONS:
                    tab.setText(R.string.transactions);
                    break;
                case EXPENSES:
                    tab.setText(R.string.expenses);
                    break;
                case INCOMES:
                    tab.setText(R.string.incomes);
                    break;
                case TRANSFERS:
                    tab.setText(R.string.transfers);
                    break;
            }
        }).attach();

    }
}