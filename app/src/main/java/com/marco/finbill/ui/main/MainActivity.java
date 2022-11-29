package com.marco.finbill.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.marco.finbill.R;
import com.marco.finbill.enums.DownloadStatus;
import com.marco.finbill.enums.HandleExchange;
import com.marco.finbill.sql.model.FinBillViewModel;
import com.marco.finbill.ui.welcome.WelcomeActivity;

import java.text.ParsePosition;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private FinBillViewModel viewModel;
    private SharedPreferences sharedPreferences;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
    private Date today;
    public static final String SHAREDPREFS = "sharedPrefs";

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Data settings

        startWelcomeActivity();
        viewModel = new ViewModelProvider(this).get(FinBillViewModel.class);
        updateExchanges();

        // UI settings

        Toolbar toolbar = findViewById(R.id.toolbarMainActivity);
        setSupportActionBar(toolbar);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);
        navController = Objects.requireNonNull(navHostFragment).getNavController();

        appBarConfiguration = new AppBarConfiguration.Builder(R.id.mainFragmentDashboard).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.add_icon, getTheme()));

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

    public void startWelcomeActivity() {
        sharedPreferences = getSharedPreferences(SHAREDPREFS, MODE_PRIVATE);
        boolean firstStart = sharedPreferences.getBoolean("firstStart", true);
        if (firstStart) {
            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }
    }

    private void updateExchanges() {
        String lastUpdate = sharedPreferences.getString("lastUpdate", null);
        HandleExchange operation;
        today = new Date(System.currentTimeMillis());
        if (lastUpdate == null) {
            operation = HandleExchange.INSERT;
        }
        else {
            // Since it must not be exposed to the user, the date format is not relevant.
            Date lastUpdateDate = simpleDateFormat.parse(lastUpdate, new ParsePosition(0));
            long diff = today.getTime() - lastUpdateDate.getTime();
            int days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            if (days >= 1) {
                operation = HandleExchange.UPDATE;
            }
            else {
                operation = HandleExchange.ABORT;
            }
        }
        if (operation != HandleExchange.ABORT) {
            viewModel.downloadCurrencies(operation).observe(this, currencyStatus -> {
                if (currencyStatus != null) {
                    if (currencyStatus == DownloadStatus.SUCCESS) {
                        downloadExchanges(operation);

                    }
                }
            });
        }
    }

    private void downloadExchanges(HandleExchange operation) {
        viewModel.downloadExchanges(operation).observe(MainActivity.this, exchangeStatus -> {
            if (exchangeStatus != null) {
                if (exchangeStatus == DownloadStatus.STARTED) {
                    Toast.makeText(getApplicationContext(), R.string.updating_exchange_rates, Toast.LENGTH_SHORT).show();
                }
                else if (exchangeStatus == DownloadStatus.SUCCESS) {
                    String date = simpleDateFormat.format(today);
                    sharedPreferences.edit().putString("lastUpdate", date).apply();
                    Toast.makeText(getApplicationContext(), R.string.updated_exchange_rates, Toast.LENGTH_SHORT).show();
                }
                else if (exchangeStatus == DownloadStatus.FAILURE) {
                    Toast.makeText(getApplicationContext(), R.string.error_updating_exchange_rates, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}