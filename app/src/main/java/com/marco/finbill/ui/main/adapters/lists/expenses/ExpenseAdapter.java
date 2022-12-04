package com.marco.finbill.ui.main.adapters.lists.expenses;

import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.marco.finbill.R;
import com.marco.finbill.sql.category.CategoryWithCurrency;
import com.marco.finbill.sql.transaction.expense.ExpenseIsTransactionWithRelationships;
import com.marco.finbill.ui.main.adapters.lists.categories.CategoryDiffCallback;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {

    private final ArrayList<ExpenseIsTransactionWithRelationships> expenses;

    public ExpenseAdapter() {
        this.expenses = new ArrayList<>();
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
        ExpenseIsTransactionWithRelationships expenseIsTransactionWithRelationships = expenses.get(position);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        if (expenseIsTransactionWithRelationships.getTransactionHasCurrency().getTransaction().getTransactionImage() == null) {
            holder.picture.setImageResource(R.drawable.picture_icon);
        } else {
            holder.picture.setImageBitmap(expenseIsTransactionWithRelationships.getTransactionHasCurrency().getTransaction().getTransactionImage());
        }
        holder.title.setText(expenseIsTransactionWithRelationships.getTransactionHasCurrency().getTransaction().getTransactionName());
        holder.from.setText(expenseIsTransactionWithRelationships.getFromExpense().getAccountName());
        holder.to.setText(expenseIsTransactionWithRelationships.getToExpense().getCategoryName());
        holder.date.setText(simpleDateFormat.format(expenseIsTransactionWithRelationships.getTransactionHasCurrency().getTransaction().getTransactionDate()));
        simpleDateFormat.applyPattern("HH:mm");
        holder.time.setText(simpleDateFormat.format(expenseIsTransactionWithRelationships.getTransactionHasCurrency().getTransaction().getTransactionTime()));
        holder.sign.setText(R.string.minus);
        holder.amount.setText(new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.getDefault())).format(expenseIsTransactionWithRelationships.getTransactionHasCurrency().getTransaction().getTransactionAmount()));
        holder.currency.setText(expenseIsTransactionWithRelationships.getTransactionHasCurrency().getCurrency().getCurrencyString());
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public void updateExpenseList(List<ExpenseIsTransactionWithRelationships> expenses) {
        ExpenseDiffCallback diffCallback = new ExpenseDiffCallback(this.expenses, expenses);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.expenses.clear();
        this.expenses.addAll(expenses);
        diffResult.dispatchUpdatesTo(this);
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
            currency = itemView.findViewById(R.id.currencySymbol);
        }
    }
}
