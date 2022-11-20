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
import android.icu.util.Currency;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.marco.finbill.R;
import com.marco.finbill.sql.exchange.exchange_api.SearchForExchange;
import com.marco.finbill.sql.exchange.Exchange;
import com.marco.finbill.sql.exchange.exchange_latest_update.ExchangeLatestUpdate;
import com.marco.finbill.sql.model.FinBillViewModel;
import com.marco.finbill.ui.welcome.WelcomeActivity;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    private FinBillViewModel viewModel;

    private enum Action {
        INSERT, UPDATE
    }

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

        viewModel = new ViewModelProvider(this).get(FinBillViewModel.class);

        // UI settings

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);
        navController = Objects.requireNonNull(navHostFragment).getNavController();

        appBarConfiguration = new AppBarConfiguration.Builder(R.id.mainFragmentDashboard).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        updateExchanges();
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

    private void updateExchanges() {
        ExchangeLatestUpdate exchangeLatestUpdate = viewModel.getExchangeLatestUpdate();
        Date today = new Date(System.);
        Set<Currency> currencySet = Currency.getAvailableCurrencies();
        if (exchangeLatestUpdate == null) {
            exchangeLatestUpdate = new ExchangeLatestUpdate(today);
            viewModel.insertExchangeLatestUpdate(exchangeLatestUpdate);
            modifyExchange(currencySet, Action.INSERT);
        }
        else {
            if (exchangeLatestUpdate.getExchangeLatestUpdate().before(today)) {
                exchangeLatestUpdate.setExchangeLatestUpdate(today);
                viewModel.updateExchangeLatestUpdate(exchangeLatestUpdate);
                modifyExchange(currencySet, Action.UPDATE);
            }
        }
    }

    private void modifyExchange(Set<Currency> currencySet, Action action) {
        for (Currency fromCurrency : currencySet) {
            SearchForExchange searchForExchange = new SearchForExchange(fromCurrency);
            List<Exchange> exchangeList = searchForExchange.getExchangesFromCurrencyToCurrencies();
            if (exchangeList != null) {
                for (Exchange exchange: exchangeList) {
                    if (action == Action.INSERT) {
                        viewModel.insertExchange(exchange);
                    }
                    else {
                        viewModel.updateExchange(exchange);
                    }
                }
            }
        }
    }

}