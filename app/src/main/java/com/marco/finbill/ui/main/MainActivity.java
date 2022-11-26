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
import com.marco.finbill.sql.currency_code.CurrencyCode;
import com.marco.finbill.sql.exchange.Exchange;
import com.marco.finbill.sql.exchange.api.ServiceGenerator;
import com.marco.finbill.sql.exchange.api.currency_api.CurrencyApi;
import com.marco.finbill.sql.exchange.api.currency_api.CurrencyResponse;
import com.marco.finbill.sql.exchange.api.exchange_api.ExchangeApi;
import com.marco.finbill.sql.exchange.api.exchange_api.ExchangeResponse;
import com.marco.finbill.sql.model.FinBillViewModel;
import com.marco.finbill.ui.welcome.WelcomeActivity;

import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    private FinBillViewModel viewModel;
    private SharedPreferences sharedPreferences;

    private MutableLiveData<List<String>> currencyStringsLiveData;
    private MutableLiveData<RatesClass> ratesLiveData;

    private final Set<Currency> availableCurrencies = Currency.getAvailableCurrencies();

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
    private Date today;

    private enum HandleExchange {
        ABORT, UPDATE, INSERT
    }

    private static class RatesClass {
        private final String fromCurrencyString;
        private final Map<String, Double> ratesMap;
        public RatesClass(String fromCurrencyString, Map<String, Double> ratesMap) {
            this.fromCurrencyString = fromCurrencyString;
            this.ratesMap = ratesMap;
        }
        public String getFromCurrencyString() {
            return fromCurrencyString;
        }
        public Map<String, Double> getRatesMap() {
            return ratesMap;
        }
    }

    private final int CURRENCY_LENGTH = 3;

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

        //viewModel.deleteAllCurrencyCodes();

        //viewModel.deleteAllExchanges();

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
        sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        boolean firstStart = sharedPreferences.getBoolean("firstStart", true);
        if (firstStart) {
            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }
    }

    private void updateExchanges() {
        String lastUpdate = sharedPreferences.getString("lastUpdate", null);
        HandleExchange operation;
        today = new Date();
        if (lastUpdate == null) {
            operation = HandleExchange.INSERT;
        }
        else {
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
        downloadExchanges(operation);
    }

    private void downloadExchanges(HandleExchange operation) {
        if (operation != HandleExchange.ABORT) {
            currencyStringsLiveData = new MutableLiveData<>();
            ratesLiveData = new MutableLiveData<>();
            CurrencyApi currencyApi = ServiceGenerator.getCurrencyApi();
            Call<CurrencyResponse> currencyCall = currencyApi.getCurrencies();
            currencyCall.enqueue(new Callback<CurrencyResponse>() {
                @Override
                public void onResponse(@NonNull Call<CurrencyResponse> call, @NonNull Response<CurrencyResponse> response) {
                    if (response.isSuccessful()) {
                        CurrencyResponse currencyResponse = response.body();
                        if (currencyResponse != null) {
                            currencyStringsLiveData.setValue(currencyResponse.getCurrencies());
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<CurrencyResponse> call, @NonNull Throwable t) {
                }
            });
            currencyStringsLiveData.observe(this, currencyStrings -> {
                if (currencyStrings != null) {
                    List<String> fromCurrencyList = new ArrayList<>();
                    viewModel.deleteAllCurrencyCodes();
                    for (String currencyString : currencyStrings) {
                        if (currencyString.length() == CURRENCY_LENGTH) {
                            if (availableCurrencies.contains(Currency.getInstance(currencyString))) {
                                viewModel.insertCurrencyCode(new CurrencyCode(currencyString));
                                fromCurrencyList.add(currencyString);
                            }
                        }
                    }

                    if (!fromCurrencyList.isEmpty()) {
                        Toast.makeText(this, R.string.updating_exchange_rates, Toast.LENGTH_SHORT).show();
                        for (String fromCurrencyStringItem : fromCurrencyList) {
                            ExchangeApi exchangeApi = ServiceGenerator.getExchangeApi();
                            Call<ExchangeResponse> exchangeCall = exchangeApi.getExchange(fromCurrencyStringItem.toLowerCase());
                            exchangeCall.enqueue(new Callback<ExchangeResponse>() {
                                @Override
                                public void onResponse(@NonNull Call<ExchangeResponse> call, @NonNull Response<ExchangeResponse> response) {
                                    if (response.isSuccessful()) {
                                        ExchangeResponse exchangeResponse = response.body();
                                        if (exchangeResponse != null) {
                                            ratesLiveData.setValue(new RatesClass(fromCurrencyStringItem, exchangeResponse.getRates()));
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(@NonNull Call<ExchangeResponse> call, @NonNull Throwable t) {
                                }
                            });
                        }
                        String date = simpleDateFormat.format(today);
                        sharedPreferences.edit().putString("lastUpdate", date).apply();
                        Toast.makeText(this, R.string.updated_exchange_rates, Toast.LENGTH_SHORT).show();
                    }
                }
            });
            ratesLiveData.observe(this, ratesClass -> {
                if (ratesClass != null) {
                    String fromCurrencyString = ratesClass.getFromCurrencyString();
                    Map<String, Double> ratesMap = ratesClass.getRatesMap();
                    if (ratesMap != null) {
                        for (Map.Entry<String, Double> entry : ratesMap.entrySet()) {
                            String toCurrencyString = entry.getKey();
                            Double rate = entry.getValue();
                            if (toCurrencyString.length() == CURRENCY_LENGTH) {
                                if (availableCurrencies.contains(Currency.getInstance(toCurrencyString))) {
                                    Exchange exchange = new Exchange(fromCurrencyString, toCurrencyString, rate);
                                    if (operation == HandleExchange.UPDATE) {
                                        viewModel.updateExchange(exchange);
                                    } // operation == HandleExchange.INSERT
                                    else {
                                        viewModel.insertExchange(exchange);
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }

}