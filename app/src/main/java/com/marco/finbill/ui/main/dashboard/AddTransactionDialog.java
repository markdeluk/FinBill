package com.marco.finbill.ui.main.dashboard;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.marco.finbill.R;
import com.marco.finbill.enums.TransactionType;
import com.marco.finbill.ui.main.adapters.SpinnerAdapterWithInitialText;

public class AddTransactionDialog extends DialogFragment {

    public static final String TAG = "AddTransactionDialog";

    private TransactionType transactionType = TransactionType.ERROR;
    private Toolbar toolbar;

    public AddTransactionDialog() {
    }

    public AddTransactionDialog(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public static AddTransactionDialog display(FragmentManager fragmentManager) {
        AddTransactionDialog addTransactionDialog = new AddTransactionDialog();
        addTransactionDialog.show(fragmentManager, TAG);
        return addTransactionDialog;
    }

    public static AddTransactionDialog display(FragmentManager fragmentManager, TransactionType transactionType) {
        AddTransactionDialog addTransactionDialog = new AddTransactionDialog(transactionType);
        addTransactionDialog.show(fragmentManager, TAG);
        return addTransactionDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_add_transaction, container, false);
        toolbar = rootView.findViewById(R.id.toolbar3);

        Spinner spinner = rootView.findViewById(R.id.transactionEdit);
        SpinnerAdapterWithInitialText<CharSequence> adapter = (SpinnerAdapterWithInitialText<CharSequence>) SpinnerAdapterWithInitialText.createFromResource(getContext(), R.array.transaction_types_option, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        if (transactionType != TransactionType.ERROR) {
            spinner.setSelection(transactionType.ordinal());
        }
        else {
            spinner.setSelection(TransactionType.ERROR.ordinal());
        }

        Spinner fromSpinner = rootView.findViewById(R.id.fromEdit);
        Spinner toSpinner = rootView.findViewById(R.id.toEdit);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == TransactionType.EXPENSE.ordinal()) {
                    // from = Account
                    // to = Category

                } else if (position == TransactionType.INCOME.ordinal()) {
                    //toolbar.setTitle("Add Income");
                } else {
                    //toolbar.setTitle("Add Transfer");
                }

                getResources().getString(R.string.choose_option);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });




        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setNavigationOnClickListener(v -> dismiss());
        toolbar.setTitle(R.string.add_transaction);
        toolbar.inflateMenu(R.menu.menu_add_transaction);
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_save) {
                dismiss();
                return true;
            }
            return false;
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }
}