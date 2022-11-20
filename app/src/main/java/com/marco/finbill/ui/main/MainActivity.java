package com.marco.finbill.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Update;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Currency;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.marco.finbill.R;
import com.marco.finbill.sql.exchange.UpdateExchangeWorker;
import com.marco.finbill.sql.exchange.exchange_api.SearchForExchange;
import com.marco.finbill.sql.exchange.Exchange;
import com.marco.finbill.sql.exchange.exchange_latest_update.ExchangeLatestUpdate;
import com.marco.finbill.sql.model.FinBillViewModel;
import com.marco.finbill.ui.welcome.WelcomeActivity;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    private FinBillViewModel viewModel;

    private Map<String, Object> exchanges;

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

        Constraints constraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(UpdateExchangeWorker.class, 1, TimeUnit.DAYS).setConstraints(constraints).build();
        WorkManager workManager = WorkManager.getInstance(this);
        workManager.enqueue(periodicWorkRequest);
        workManager.getWorkInfoByIdLiveData(periodicWorkRequest.getId()).observe(this, workInfo -> {
            if (workInfo != null && workInfo.getState().isFinished()) {
                exchanges = workInfo.getOutputData().getKeyValueMap();
                updateExchanges();
            }
        });

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

    private void updateExchanges() {
        ExchangeLatestUpdate exchangeLatestUpdate = viewModel.getExchangeLatestUpdate();
        Date today = new Date(System.currentTimeMillis());
        Set<Currency> currencySet = Currency.getAvailableCurrencies();
        if (exchangeLatestUpdate == null) {
            exchangeLatestUpdate = new ExchangeLatestUpdate(today);
            viewModel.insertExchangeLatestUpdate(exchangeLatestUpdate);
            modifyExchange(Action.INSERT);
        }
        else {
            if (exchangeLatestUpdate.getExchangeLatestUpdate().before(today)) {
                exchangeLatestUpdate.setExchangeLatestUpdate(today);
                viewModel.updateExchangeLatestUpdate(exchangeLatestUpdate);
                modifyExchange(Action.UPDATE);
            }
        }
    }

    private void modifyExchange(Action action) {
        for (Map.Entry<String, Object> entry : exchanges.entrySet()) {
            String[] keys = entry.getKey().split("_");
            Currency from = Currency.getInstance(keys[0].toUpperCase());
            Currency to = Currency.getInstance(keys[1].toUpperCase());
            Double rate = (Double) entry.getValue();
            Exchange exchange = new Exchange(from, to, rate);
            if (action == Action.INSERT) {
                viewModel.insertExchange(exchange);
            }
            else {
                viewModel.updateExchange(exchange);
            }
        }
    }

}