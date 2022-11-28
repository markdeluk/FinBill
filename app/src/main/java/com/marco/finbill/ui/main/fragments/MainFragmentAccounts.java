package com.marco.finbill.ui.main.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.marco.finbill.R;
import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.model.FinBillViewModel;
import com.marco.finbill.ui.main.adapters.AccountAdapter;
import com.marco.finbill.ui.main.adapters.ExpenseAdapter;
import com.marco.finbill.ui.main.dialogs.AddAccountDialog;
import com.marco.finbill.ui.main.dialogs.AddTransactionDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainFragmentAccounts extends Fragment {

    private FinBillViewModel viewModel;

    public MainFragmentAccounts() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(FinBillViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_accounts, container, false);
        FloatingActionButton fab = requireActivity().findViewById(R.id.fab);
        fab.setOnClickListener(v -> AddAccountDialog.display(getChildFragmentManager()));
        fab.setOnLongClickListener(view -> {
            Snackbar.make(view, R.string.add_account, Snackbar.LENGTH_LONG).show();
            return true;
        });
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        List<Account> accountsList = new ArrayList<>();
        AccountAdapter accountAdapter = new AccountAdapter(accountsList);
        RecyclerView recyclerView = view.findViewById(R.id.accounts_recycler_view);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(accountAdapter);
        viewModel.getAllAccounts().observe(getViewLifecycleOwner(), accounts -> {
            accountsList.clear();
            accountsList.addAll(accounts);
            accountAdapter.notifyDataSetChanged();
        });
    }
}