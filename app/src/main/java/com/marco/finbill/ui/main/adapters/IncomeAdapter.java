package com.marco.finbill.ui.main.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marco.finbill.R;
import com.marco.finbill.sql.transaction.income.TransactionIsIncomeWithRelationships;

import java.util.ArrayList;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.ViewHolder> {

    private final ArrayList<TransactionIsIncomeWithRelationships> incomes;

    public IncomeAdapter(ArrayList<TransactionIsIncomeWithRelationships> incomes) {
        this.incomes = incomes;
    }

    @NonNull
    @Override
    public IncomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.transaction_list_item, parent, false);
        return new IncomeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeAdapter.ViewHolder holder, int position) {
        TransactionIsIncomeWithRelationships transactionIsIncomeWithRelationships = incomes.get(position);
        // picture
        if (transactionIsIncomeWithRelationships.transaction.getImage() == null) {
            holder.picture.setImageResource(R.drawable.picture_icon);
        } else {
            holder.picture.setImageBitmap(transactionIsIncomeWithRelationships.transaction.getImage());
        }
        holder.title.setText(transactionIsIncomeWithRelationships.transaction.getName());
        holder.from.setText(transactionIsIncomeWithRelationships.incomeWithRelationships.fromIncome.getName());
        holder.to.setText(transactionIsIncomeWithRelationships.incomeWithRelationships.toIncome.getName());
        holder.date.setText(transactionIsIncomeWithRelationships.transaction.getDate().toString());
        holder.time.setText(transactionIsIncomeWithRelationships.transaction.getTime().toString());
        holder.sign.setText(R.string.minus);
        holder.amount.setText(String.valueOf(transactionIsIncomeWithRelationships.transaction.getAmount()));
        holder.currency.setText(transactionIsIncomeWithRelationships.transaction.getCurrency().getCurrencyCode());
    }

    @Override
    public int getItemCount() {
        return incomes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView picture;
        private final TextView title;
        private final TextView from;
        private final TextView to;
        private final TextView date;
        private final TextView time;
        private final TextView sign;
        private final TextView amount;
        private final TextView currency;

        public ViewHolder(View itemView) {
            super(itemView);
            picture = itemView.findViewById(R.id.picture);
            title = itemView.findViewById(R.id.title);
            from = itemView.findViewById(R.id.from);
            to = itemView.findViewById(R.id.to);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            sign = itemView.findViewById(R.id.sign);
            amount = itemView.findViewById(R.id.amount);
            currency = itemView.findViewById(R.id.currency);
        }
    }

}