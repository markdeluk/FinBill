package com.marco.finbill.ui.main.fragments.accounts;

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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.marco.finbill.R;
import com.marco.finbill.model.FinBillViewModel;
import com.marco.finbill.ui.main.adapters.lists.accounts.AccountAdapter;
import com.marco.finbill.ui.main.dialogs.AddAccountDialog;

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
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(v -> AddAccountDialog.display(getChildFragmentManager()));
        fab.setOnLongClickListener(view -> {
            Snackbar.make(view, R.string.add_account, Snackbar.LENGTH_LONG).show();
            return true;
        });
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AccountAdapter accountAdapter = new AccountAdapter();
        RecyclerView recyclerView = view.findViewById(R.id.accounts_recycler_view);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(accountAdapter);
        viewModel.getAllAccountsHaveCurrencies().observe(getViewLifecycleOwner(), accountAdapter::updateAccountList);
    }
}