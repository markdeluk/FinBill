package com.marco.finbill.ui.welcome;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.marco.finbill.R;

import java.util.Objects;

public class WelcomeFragment2 extends Fragment {

    EditText name;

    Spinner spinner;
    SpinnerAdapter spinnerAdapter;

    Button next;

    public WelcomeFragment2() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_welcome2, container, false);

        name = rootView.findViewById(R.id.editTextTextPersonName);

        spinner = rootView.findViewById(R.id.spinner);
        spinnerAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.app_need_option, android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        next = rootView.findViewById(R.id.button2);
        next.setOnClickListener(v -> {
            editor.putBoolean("firstStart", false);
            editor.putString("name", name.getText().toString());
            editor.putInt("app_need", (int) spinner.getSelectedItemId());
            editor.apply();

            Navigation.findNavController(rootView).navigate(R.id.action_welcomeFragment2_to_welcomeFragment3);
        });

        return rootView;
    }


}