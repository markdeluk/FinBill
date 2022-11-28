package com.marco.finbill.ui.welcome;

import static com.marco.finbill.ui.main.MainActivity.SHAREDPREFS;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.marco.finbill.R;

import java.util.Objects;

public class WelcomeFragment3 extends Fragment {

    Button button;

    public WelcomeFragment3() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome3, container, false);
        button = view.findViewById(R.id.button4);
        button.setOnClickListener(v -> requireActivity().finish());
        return view;
    }
}