package com.marco.finbill.ui.main;

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
import com.marco.finbill.sql.model.FinBillViewModel;
import com.marco.finbill.sql.transaction.expense.ExpenseDao;
import com.marco.finbill.ui.main.adapters.ExpenseAdapter;
import com.marco.finbill.ui.main.adapters.IncomeAdapter;
import com.marco.finbill.ui.main.adapters.TransferAdapter;

public class MainFragmentDashboard extends Fragment {

    private FinBillViewModel viewModel;
    private ExpenseAdapter expenseAdapter;
    private IncomeAdapter incomeAdapter;
    private TransferAdapter transferAdapter;

    public MainFragmentDashboard() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        expenseAdapter = new ExpenseAdapter();
        incomeAdapter = new IncomeAdapter();
        transferAdapter = new TransferAdapter();
        viewModel = new ViewModelProvider(requireActivity()).get(FinBillViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(FinBillViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ConcatAdapter concatAdapter = new ConcatAdapter(expenseAdapter, incomeAdapter, transferAdapter);
        recyclerView.setAdapter(concatAdapter);

    }
}