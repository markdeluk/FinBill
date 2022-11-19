package com.marco.finbill.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.marco.finbill.R;
import com.marco.finbill.sql.exchange.Exchange;
import com.marco.finbill.sql.exchange.currencyapi.ExchangeApi;
import com.marco.finbill.sql.exchange.currencyapi.ExchangeResponse;
import com.marco.finbill.sql.exchange.currencyapi.ServiceGenerator;
import com.marco.finbill.sql.model.FinBillViewModel;
import com.marco.finbill.sql.transaction.expense.Expense;
import com.marco.finbill.ui.main.adapters.ExpenseAdapter;
import com.marco.finbill.ui.main.adapters.IncomeAdapter;
import com.marco.finbill.ui.main.adapters.TransferAdapter;
import com.marco.finbill.ui.welcome.WelcomeActivity;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        boolean firstStart = sharedPreferences.getBoolean("firstStart", true);
        if (firstStart) {
            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }

        // Data settings

        FinBillViewModel viewModel = new ViewModelProvider(this).get(FinBillViewModel.class);

        // UI settings

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);
        navController = Objects.requireNonNull(navHostFragment).getNavController();

        appBarConfiguration = new AppBarConfiguration.Builder(R.id.mainFragmentDashboard).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    public List<Exchange> searchForExchange(String from) {
        ExchangeApi exchangeApi = ServiceGenerator.getExchangeApi();
        Call<ExchangeResponse> call = exchangeApi.getExchange(from);
        call.enqueue(new Callback<ExchangeResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<ExchangeResponse> call, Response<ExchangeResponse> response) {
                if (response.isSuccessful()) {
                    response.body().getExchange();
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<ExchangeResponse> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
    }

}