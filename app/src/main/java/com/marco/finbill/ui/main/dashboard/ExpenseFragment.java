package com.marco.finbill.ui.main.dashboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.marco.finbill.R;
import com.marco.finbill.sql.model.FinBillViewModel;
import com.marco.finbill.ui.addtransaction.AddTransactionActivity;
import com.marco.finbill.ui.main.adapters.ExpenseAdapter;

public class ExpenseFragment extends Fragment {

    public ExpenseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_expense, container, false);



        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FinBillViewModel viewModel = new ViewModelProvider(requireActivity()).get(FinBillViewModel.class);
        ExpenseAdapter expenseAdapter = new ExpenseAdapter();
        RecyclerView recyclerView = view.findViewById(R.id.expense_recycler_view);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(expenseAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();

        FloatingActionButton fab = requireActivity().findViewById(R.id.fab);
        fab.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.add_icon, requireActivity().getTheme()));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddTransactionActivity.class);
                intent.putExtra("type", "expense");
                startActivity(intent);
            }
        });
        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Snackbar.make(view, R.string.add_expense, Snackbar.LENGTH_LONG).show();
                return true;
            }
        });
    }
}