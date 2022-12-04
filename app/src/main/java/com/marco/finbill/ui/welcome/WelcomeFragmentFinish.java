package com.marco.finbill.ui.welcome;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.marco.finbill.R;

public class WelcomeFragmentFinish extends Fragment {

    Button button;

    public WelcomeFragmentFinish() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome_end, container, false);
        button = view.findViewById(R.id.button4);
        button.setOnClickListener(v -> ((WelcomeActivity) requireActivity()).finishWelcomeActivity());
        return view;
    }
}