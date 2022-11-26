package com.marco.finbill.ui.main.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.marco.finbill.R;
import com.marco.finbill.ui.main.dialogs.AddAccountDialog;
import com.marco.finbill.ui.main.dialogs.AddTransactionDialog;

import java.util.Objects;

public class MainFragmentAccounts extends Fragment {

    public MainFragmentAccounts() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}