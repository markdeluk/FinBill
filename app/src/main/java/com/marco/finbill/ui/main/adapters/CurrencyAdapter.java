package com.marco.finbill.ui.main.adapters;

import android.content.Context;
import android.icu.util.Currency;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.marco.finbill.R;

import java.util.List;

public class CurrencyAdapter extends ArrayAdapter<Currency> {

    Context context;
    List<Currency> currencies;

    public CurrencyAdapter(@NonNull Context context, @NonNull List<Currency> objects) {
        super(context, R.layout.currency_list_item, objects);
        this.context = context;
        this.currencies = objects;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.currency_list_item, parent, false);
        TextView currencySymbol = row.findViewById(R.id.currencySymbol);
        TextView currencyCode = row.findViewById(R.id.currencyCode);
        TextView currencyName = row.findViewById(R.id.currencyName);
        Currency currency = currencies.get(position);
        currencySymbol.setText(currency.getSymbol());
        currencyCode.setText(currency.getCurrencyCode());
        currencyName.setText(currency.getDisplayName());
        return row;
    }
}