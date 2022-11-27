package com.marco.finbill.ui.welcome;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;
import com.marco.finbill.R;
import com.marco.finbill.sql.currency_code.CurrencyCode;
import com.marco.finbill.sql.model.FinBillViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WelcomeFragment2 extends Fragment {

    private EditText name;
    private Spinner appNeedSpinner;
    private Spinner welcomeCurrencyEdit;
    private FinBillViewModel viewModel;

    public WelcomeFragment2() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(FinBillViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_welcome2, container, false);

        name = rootView.findViewById(R.id.welcomeNameEdit);

        appNeedSpinner = rootView.findViewById(R.id.welcomeAppNeedEdit);
        List<String> appNeedList = new ArrayList<>();
        appNeedList.add(0, getResources().getString(R.string.choose_option));
        appNeedList.addAll(Arrays.asList(getResources().getStringArray(R.array.app_need_option)));
        ArrayAdapter<String> appNeedAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, appNeedList);
        appNeedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        appNeedSpinner.setAdapter(appNeedAdapter);

        appNeedSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        welcomeCurrencyEdit = rootView.findViewById(R.id.welcomeCurrencyEdit);
        List<String> currencyStringList = new ArrayList<>();
        ArrayAdapter<String> currencyStringAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, currencyStringList);
        currencyStringAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        welcomeCurrencyEdit.setAdapter(currencyStringAdapter);
        viewModel.getAllCurrencyCodes().observe(getViewLifecycleOwner(), currencyCodes -> {
            currencyStringList.clear();
            currencyStringList.add(0, getResources().getString(R.string.choose_currency));
            for (CurrencyCode currencyCode : currencyCodes) {
                currencyStringList.add(currencyCode.getCurrencyString());
            }
            currencyStringAdapter.notifyDataSetChanged();
        });

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Button next = rootView.findViewById(R.id.nextButton);
        next.setOnClickListener(v -> {
            String nameString = name.getText().toString();
            int appNeed = appNeedSpinner.getSelectedItemPosition();
            String currencyString = welcomeCurrencyEdit.getSelectedItem().toString();
            if (!nameString.isEmpty() && appNeed != 0 && !currencyString.equals(getResources().getString(R.string.choose_currency))) {
                editor.putBoolean("firstStart", false);
                editor.putString("name", nameString);
                editor.putInt("app_need", appNeed);
                editor.putString("currency", currencyString);
                editor.apply();
                Navigation.findNavController(rootView).navigate(R.id.action_welcomeFragment2_to_welcomeFragment3);
            }
            else {

                Snackbar.make(rootView, R.string.incomplete_fields, Snackbar.LENGTH_LONG).show();
            }

        });

        return rootView;
    }


}