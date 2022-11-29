package com.marco.finbill.ui.welcome;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.marco.finbill.R;

public class WelcomeFragmentStart extends Fragment {

    Button button;

    public WelcomeFragmentStart() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_welcome_start, container, false);
        button = rootView.findViewById(R.id.startButton);
        button.setOnClickListener(v -> Navigation.findNavController(rootView).navigate(R.id.action_welcomeFragment1_to_welcomeFragment2));

        return rootView;
    }

}