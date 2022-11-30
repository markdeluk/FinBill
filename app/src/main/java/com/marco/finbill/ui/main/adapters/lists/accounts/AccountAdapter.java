package com.marco.finbill.ui.main.adapters.lists.accounts;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.marco.finbill.R;
import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.account.AccountHasCurrencies;
import com.marco.finbill.sql.model.FinBillViewModel;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {

    private final List<AccountHasCurrencies> accounts;

    public AccountAdapter() {
        this.accounts = new ArrayList<>();
    }

    @NonNull
    @Override
    public AccountAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.account_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AccountHasCurrencies accountHasCurrencies = accounts.get(position);
        Bitmap image = accountHasCurrencies.getAccount().getAccountImage();
        if (image == null) {
            holder.picture.setImageResource(R.drawable.picture_icon);
        } else {
            holder.picture.setImageBitmap(image);
        }
        holder.title.setText(accountHasCurrencies.getAccount().getAccountName());
        holder.type.setText(holder.accountTypes[accountHasCurrencies.getAccount().getAccountType().ordinal() - 1]);
        double balance = accountHasCurrencies.getAccount().getAccountBalance();
        holder.sign.setText(balance < 0 ? R.string.minus : R.string.plus);
        holder.amount.setText(new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.getDefault())).format(balance < 0 ? -balance : balance));
        holder.currency.setText(accountHasCurrencies.getBalanceCurrency().getCurrencyString());
    }

    public int getItemCount() {
        return accounts.size();
    }

    public void updateAccountList(List<AccountHasCurrencies> accounts) {
        AccountDiffCallback diffCallback = new AccountDiffCallback(this.accounts, accounts);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.accounts.clear();
        this.accounts.addAll(accounts);
        diffResult.dispatchUpdatesTo(this);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView picture;
        private final TextView title;
        private final TextView type;
        private final TextView sign;
        private final TextView amount;
        private final TextView currency;
        private final String[] accountTypes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            picture = itemView.findViewById(R.id.picture);
            title = itemView.findViewById(R.id.title_text);
            type = itemView.findViewById(R.id.type_text);
            sign = itemView.findViewById(R.id.sign);
            amount = itemView.findViewById(R.id.amount);
            currency = itemView.findViewById(R.id.currencySymbol);
            accountTypes = itemView.getResources().getStringArray(R.array.account_types_option);
        }
    }
}