package com.marco.finbill.ui.welcome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.marco.finbill.R;

import java.util.Objects;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = Objects.requireNonNull(navHostFragment).getNavController();

    }

    @Override
    public void onBackPressed() {
        Snackbar.make(findViewById(R.id.fragmentContainerView), R.string.cant_skip, Snackbar.LENGTH_SHORT).show();
    }
}