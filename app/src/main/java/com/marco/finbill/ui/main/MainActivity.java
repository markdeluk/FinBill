package com.marco.finbill.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.marco.finbill.R;
import com.marco.finbill.enums.DownloadStatus;
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

    private MutableLiveData<Boolean> checkFinishedDownloadCurrencies;
    private MutableLiveData<Boolean> downloadExchanges;
    private MutableLiveData<Boolean> checkFinishedDownloadExchanges;
    private MutableLiveData<Integer> downloadedItemsLiveData;

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
        boolean flag = true;
        today = new Date(System.currentTimeMillis());
        if (lastUpdate != null) {
            // Since it must not be exposed to the user, the date format is not relevant.
            Date lastUpdateDate = simpleDateFormat.parse(lastUpdate, new ParsePosition(0));
            long diff = today.getTime() - lastUpdateDate.getTime();
            int days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            if (days < 1) {
                flag = false;
            }
        }
        if (flag) {
            downloadedItemsLiveData = new MutableLiveData<>();
            checkFinishedDownloadCurrencies = new MutableLiveData<>();
            downloadExchanges = new MutableLiveData<>();
            checkFinishedDownloadExchanges = new MutableLiveData<>();

            checkFinishedDownloadCurrencies.observe(this, aBoolean -> {
                if (aBoolean) checkFinishedDownloadCurrencies();
            });
            downloadExchanges.observe(this, aBoolean -> {
                if (aBoolean) downloadExchanges();
            });
            checkFinishedDownloadExchanges.observe(this, aBoolean -> {
                if (aBoolean) checkFinishedDownloadExchanges();
            });

            downloadCurrencies();
        }
    }

    private void downloadCurrencies() {
        Log.e("MainActivity", "updateExchanges");
        viewModel.downloadCurrencies().observe(this, downloadResult -> {
            if (downloadResult != null) {
                if (downloadResult.getDownloadStatus() == DownloadStatus.STARTED) {
                    Log.e("MainActivity", "updateExchanges: STARTED");
                    Toast.makeText(getApplicationContext(), R.string.updating_currencies, Toast.LENGTH_SHORT).show();
                }
                else if (downloadResult.getDownloadStatus() == DownloadStatus.SUCCESS) {
                    Log.e("MainActivity", "updateExchanges: SUCCESS");
                    downloadedItemsLiveData.setValue(downloadResult.getDownloadedItems());
                    checkFinishedDownloadCurrencies.setValue(true);
                }
                else {
                    Log.e("MainActivity", "updateExchanges: ERROR");
                    Toast.makeText(getApplicationContext(), R.string.error_updating_exchange_rates, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkFinishedDownloadCurrencies() {
        Log.e("MainActivity", "checkFinishedDownloadCurrencies");
        viewModel.getLastCurrencyId().observe(this, numberOfCurrencyCodes -> {
            Integer downloadedItems = downloadedItemsLiveData.getValue();
            if (numberOfCurrencyCodes != null && downloadedItems != null) {
                if (numberOfCurrencyCodes.equals(downloadedItems)) {
                    Log.e("MainActivity", "checkFinishedDownloadCurrencies: SUCCESS");
                    Toast.makeText(getApplicationContext(), R.string.updated_currencies, Toast.LENGTH_SHORT).show();
                    downloadExchanges.setValue(true);
                }
            }
        });
    }

    private void downloadExchanges() {
        Log.e("MainActivity", "downloadExchanges");
        viewModel.downloadExchanges().observe(this, downloadResultExchange -> {
            if (downloadResultExchange != null) {
                if (downloadResultExchange.getDownloadStatus() == DownloadStatus.STARTED) {
                    Log.e("MainActivity", "downloadExchanges: STARTED");
                    Toast.makeText(getApplicationContext(), R.string.updating_exchange_rates, Toast.LENGTH_SHORT).show();
                }
                else if (downloadResultExchange.getDownloadStatus() == DownloadStatus.PROCESSING) {

                }
                else if (downloadResultExchange.getDownloadStatus() == DownloadStatus.SUCCESS) {
                    Log.e("MainActivity", "downloadExchanges: SUCCESS");
                    checkFinishedDownloadExchanges.setValue(true);
                }
                else if (downloadResultExchange.getDownloadStatus() == DownloadStatus.FAILURE) {
                    Log.e("MainActivity", "downloadExchanges: FAILURE");
                    Toast.makeText(getApplicationContext(), R.string.error_updating_exchange_rates, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkFinishedDownloadExchanges() {
        Log.e("MainActivity", "checkFinishedDownloadExchanges");
        viewModel.getLastExchangeId().observe(this, numberOfExchanges -> {
            Integer downloadedItems = downloadedItemsLiveData.getValue();
            if (numberOfExchanges != null && downloadedItems != null) {
                int iterations = downloadedItems * (downloadedItems - 1);
                if (numberOfExchanges.equals(iterations)) {
                    Log.e("MainActivity", "checkFinishedDownloadExchanges: SUCCESS");
                    Toast.makeText(getApplicationContext(), R.string.updated_exchange_rates, Toast.LENGTH_SHORT).show();
                    String date = simpleDateFormat.format(today);
                    sharedPreferences.edit().putString("lastUpdate", date).apply();
                }
            }
        });
    }
}