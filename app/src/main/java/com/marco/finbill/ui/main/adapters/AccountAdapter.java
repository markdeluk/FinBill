package com.marco.finbill.ui.main.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.marco.finbill.R;
import com.marco.finbill.sql.account.Account;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

public class AccountAdapter extends ListAdapter<Account, AccountAdapter.ViewHolder> {

    private final List<Account> accounts;

    public AccountAdapter(List<Account> accounts) {
        super(DIFF_CALLBACK);
        this.accounts = accounts;
    }


    private static final DiffUtil.ItemCallback<Account> DIFF_CALLBACK = new DiffUtil.ItemCallback<Account>() {
        @Override
        public boolean areItemsTheSame(@NonNull Account oldItem, @NonNull Account newItem) {
            return oldItem.getAccountId() == newItem.getAccountId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Account oldItem, @NonNull Account newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public AccountAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.account_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountAdapter.ViewHolder holder, int position) {
        Account account = getAccountAt(position);
        Bitmap image = account.getAccountImage();
        if (image == null) {
            holder.picture.setImageResource(R.drawable.picture_icon);
        } else {
            holder.picture.setImageBitmap(image);
        }
        holder.date.setText(account.getAccountAdded().toString());
        holder.title.setText(account.getAccountName());
        holder.type.setText(holder.accountTypes[account.getAccountType().ordinal() - 1]);
        double balance = account.getAccountBalance();
        holder.sign.setText(balance < 0 ? R.string.minus : R.string.plus);
        holder.amount.setText(String.valueOf(balance < 0 ? -balance : balance));
        holder.currency.setText(account.getAccountBalanceCurrency());
    }

    private Account getAccountAt(int position) {
        return getItem(position);
    }

    public int getItemCount() {
        return accounts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView picture;
        private final TextView date;
        private final TextView title;
        private final TextView type;
        private final TextView sign;
        private final TextView amount;
        private final TextView currency;
        private final String[] accountTypes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            picture = itemView.findViewById(R.id.picture);
            date = itemView.findViewById(R.id.date_text);
            title = itemView.findViewById(R.id.title_text);
            type = itemView.findViewById(R.id.type_text);
            sign = itemView.findViewById(R.id.sign);
            amount = itemView.findViewById(R.id.amount);
            currency = itemView.findViewById(R.id.currencyTransactionSymbol);
            accountTypes = itemView.getResources().getStringArray(R.array.account_types_option);
        }
    }
}