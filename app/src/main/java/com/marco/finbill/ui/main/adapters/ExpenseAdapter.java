package com.marco.finbill.ui.main.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marco.finbill.R;
import com.marco.finbill.sql.transaction.expense.TransactionIsExpenseWithRelationships;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {

    private final ArrayList<TransactionIsExpenseWithRelationships> expenses;

    public ExpenseAdapter(ArrayList<TransactionIsExpenseWithRelationships> expenses) {
        this.expenses = expenses;
    }

    @NonNull
    @Override
    public ExpenseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.transaction_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseAdapter.ViewHolder holder, int position) {
        TransactionIsExpenseWithRelationships transactionIsExpenseWithRelationships = expenses.get(position);
        // picture
        if (transactionIsExpenseWithRelationships.transaction.getImage() == null) {
            holder.picture.setImageResource(R.drawable.picture_icon);
        } else {
            holder.picture.setImageBitmap(transactionIsExpenseWithRelationships.transaction.getImage());
        }
        holder.title.setText(transactionIsExpenseWithRelationships.transaction.getName());
        holder.from.setText(transactionIsExpenseWithRelationships.expenseWithRelationships.fromExpense.getName());
        holder.to.setText(transactionIsExpenseWithRelationships.expenseWithRelationships.toExpense.getName());
        holder.date.setText(transactionIsExpenseWithRelationships.transaction.getDate().toString());
        holder.time.setText(transactionIsExpenseWithRelationships.transaction.getTime().toString());
        holder.sign.setText(R.string.minus);
        holder.amount.setText(String.valueOf(transactionIsExpenseWithRelationships.transaction.getAmount()));
        holder.currency.setText(transactionIsExpenseWithRelationships.transaction.getCurrency().getCurrencyCode());
    }

    @Override
    public int getItemCount() {
        return expenses.size();
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
