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
import java.util.Locale;

public class CurrencyAdapter extends ArrayAdapter<Currency> {

    Context context;
    int resource;
    List<Currency> currencies;

    public CurrencyAdapter(@NonNull Context context, int resource, @NonNull List<Currency> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.currencies = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Currency currency = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        }
        TextView currencySymbol = convertView.findViewById(R.id.currencySymbol);
        TextView currencyName = convertView.findViewById(R.id.currencyName);
        TextView currencyCode = convertView.findViewById(R.id.currencyCode);
        currencySymbol.setText(currency.getSymbol());
        currencyName.setText(currency.getDisplayName());
        currencyCode.setText(currency.getCurrencyCode());
        return convertView;
    }
}