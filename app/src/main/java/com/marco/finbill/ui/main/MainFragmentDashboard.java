package com.marco.finbill.ui.main;

import android.os.Bundle;

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
import com.marco.finbill.ui.main.adapters.ExpenseAdapter;

public class MainFragmentDashboard extends Fragment {

    private FinBillViewModel viewModel;

    public MainFragmentDashboard() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel =


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_dashboard, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ConcatAdapter concatAdapter = new ConcatAdapter();
        ExpenseAdapter expenseAdapter = new ExpenseAdapter();




        TransactionAdapter transactionAdapter = new TransactionAdapter();
        recyclerView.setAdapter(transactionAdapter);


        /*FloatingActionButton fab = requireActivity().findViewById(R.id.fab);
        fab.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.dashboard_icon, requireActivity().getTheme()));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Dashboard", Snackbar.LENGTH_LONG).show();
            }
        });*/

        return rootView;
    }
}