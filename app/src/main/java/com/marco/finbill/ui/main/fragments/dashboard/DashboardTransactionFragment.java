package com.marco.finbill.ui.main.fragments.dashboard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marco.finbill.R;
import com.marco.finbill.model.FinBillViewModel;
import com.marco.finbill.ui.main.adapters.lists.expenses.ExpenseAdapter;
import com.marco.finbill.ui.main.adapters.lists.incomes.IncomeAdapter;
import com.marco.finbill.ui.main.adapters.lists.transfers.TransferAdapter;


public class DashboardTransactionFragment extends Fragment {

    private FinBillViewModel viewModel;

    public DashboardTransactionFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(FinBillViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ExpenseAdapter expenseAdapter = new ExpenseAdapter();
        IncomeAdapter incomeAdapter = new IncomeAdapter();
        TransferAdapter transferAdapter = new TransferAdapter();
        ConcatAdapter concatAdapter = new ConcatAdapter(expenseAdapter, incomeAdapter, transferAdapter);
        RecyclerView recyclerView = view.findViewById(R.id.dashboardRecyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(concatAdapter);
        viewModel.getAllExpenseIsTransactionWithRelationships().observe(getViewLifecycleOwner(), expenseAdapter::updateExpenseList);
        viewModel.getAllIncomeIsTransactionWithRelationships().observe(getViewLifecycleOwner(), incomeAdapter::updateIncomeList);
        viewModel.getAllTransferIsTransactionWithRelationships().observe(getViewLifecycleOwner(), transferAdapter::updateTransferList);
    }

}