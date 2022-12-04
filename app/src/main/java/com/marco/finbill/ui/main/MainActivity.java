package com.marco.finbill.ui.main;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
import com.marco.finbill.model.FinBillViewModel;
import com.marco.finbill.sql.currency.Currency;
import com.marco.finbill.sql.exchange.Exchange;
import com.marco.finbill.ui.welcome.WelcomeActivity;

import java.net.SecureCacheResponse;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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
    private int iterations;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Data settings

        viewModel = new ViewModelProvider(this).get(FinBillViewModel.class);
        startWelcomeActivity();

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
            downloadCurrencies();
            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
            ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    sharedPreferences.edit().putBoolean("firstStart", false).apply();
                    updateExchanges();
                }
            });
            launcher.launch(intent);
        }
        else {
            updateExchanges();
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
            downloadExchanges();
        }
    }

    private void downloadCurrencies() {
        String[] currencies = getResources().getStringArray(R.array.currencies);
        List<Currency> currencyList = new ArrayList<>();
        for (String currency : currencies) {
            currencyList.add(new Currency(currency));
        }
        int n = currencyList.size();
        iterations = n * (n - 1);
        viewModel.insertCurrencies(currencyList);
    }

    private void downloadExchanges() {
        viewModel.deleteAllExchanges();
        viewModel.getAllCurrencies().observe(this, currencies -> {
            if (currencies != null) {
                for (Currency fromCurrency: currencies) {
                    for (Currency toCurrency: currencies) {
                        if (!fromCurrency.equals(toCurrency)) {
                            viewModel.downloadExchange(fromCurrency, toCurrency);
                        }
                    }
                }
            }
        });
        viewModel.getDownloadedExchange().observe(this, exchange -> {
            if (exchange != null) {
                viewModel.insertExchange(new Exchange(exchange.getExchangeFromCurrencyId(), exchange.getExchangeToCurrencyId(), exchange.getExchangeRate()));
            }
        });
        viewModel.getNumberOfExchanges().observe(this, numberOfExchanges -> {
            if (numberOfExchanges != null) {
                if (numberOfExchanges.equals(iterations)) {
                    Toast.makeText(getApplicationContext(), R.string.updated_exchange_rates, Toast.LENGTH_SHORT).show();
                    String date = simpleDateFormat.format(today);
                    sharedPreferences.edit().putString("lastUpdate", date).apply();
                }
            }
        });
    }
}