package com.marco.finbill.ui.main.fragments.dashboard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marco.finbill.R;
import com.marco.finbill.model.FinBillViewModel;
import com.marco.finbill.ui.main.adapters.lists.incomes.IncomeAdapter;

public class DashboardIncomeFragment extends Fragment {

    private FinBillViewModel viewModel;

    public DashboardIncomeFragment() {
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

        FinBillViewModel viewModel = new ViewModelProvider(requireActivity()).get(FinBillViewModel.class);
        IncomeAdapter incomeAdapter = new IncomeAdapter();
        RecyclerView recyclerView = view.findViewById(R.id.dashboardRecyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(incomeAdapter);
        viewModel.getAllIncomeIsTransactionWithRelationships().observe(getViewLifecycleOwner(), incomeAdapter::updateIncomeList);
    }

}