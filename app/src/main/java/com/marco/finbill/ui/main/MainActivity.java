package com.marco.finbill.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Currency;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.marco.finbill.R;
import com.marco.finbill.sql.exchange.Exchange;
import com.marco.finbill.sql.exchange.api.ExchangeUpdateWorker;
import com.marco.finbill.sql.model.FinBillViewModel;
import com.marco.finbill.ui.welcome.WelcomeActivity;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    private FinBillViewModel viewModel;
    private SharedPreferences sharedPreferences;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Data settings

        startWelcomeActivity();
        viewModel = new ViewModelProvider(this).get(FinBillViewModel.class);
        insertExchanges();

        //viewModel.deleteAllExchanges();

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

    private void startWelcomeActivity() {
        sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        boolean firstStart = sharedPreferences.getBoolean("firstStart", true);
        if (firstStart) {
            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }
    }

    private void insertExchanges() {

        // periodic update of exchange rates
        Constraints constraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(ExchangeUpdateWorker.class, 1, TimeUnit.DAYS).setConstraints(constraints).build();
        //OneTimeWorkRequest periodicWorkRequest = new OneTimeWorkRequest.Builder(ExchangeUpdateWorker.class).setConstraints(constraints).build();

        WorkManager workManager = WorkManager.getInstance(this);
        workManager.enqueue(periodicWorkRequest);

        workManager.getWorkInfoByIdLiveData(periodicWorkRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                if (workInfo != null) {
                    if (workInfo.getState().isFinished()) {
                        Toast.makeText(MainActivity.this, R.string.updated_exchange_rates, Toast.LENGTH_SHORT).show();
                    }
                    Data progress = workInfo.getProgress();
                    //Log.e("progress", progress.toString());
                    String fromCurrencyString = progress.getString("fromCurrency");
                    if (fromCurrencyString != null) {
                        //Log.e("fromCurrency", fromCurrencyString);
                        if (fromCurrencyString.equals("STARTED")) {
                            Toast.makeText(MainActivity.this, R.string.updating_exchange_rates, Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("MainActivity", "onChanged: " + progress.toString());
                            String toCurrencyString = progress.getString("toCurrency");
                            Currency fromCurrency = Currency.getInstance(fromCurrencyString);
                            Currency toCurrency = Currency.getInstance(toCurrencyString);
                            double rate = progress.getDouble("rate", 0.0);
                            Exchange exchange = new Exchange(fromCurrency, toCurrency, rate);
                            //Log.e("EXCHANGE", exchange.getExchangeFromCurrency().getCurrencyCode().toString() + "_" + exchange.getExchangeToCurrency().getCurrencyCode().toString() + ": " + exchange.getExchangeRate());
                            //viewModel.insertExchange(exchange);
                        }
                    }
                }
            }
        });

    }

}