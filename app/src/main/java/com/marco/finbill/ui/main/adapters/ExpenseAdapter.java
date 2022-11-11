package com.marco.finbill.ui.main.adapters;

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
import com.marco.finbill.sql.transaction.expense.ExpenseIsTransaction;
import com.marco.finbill.sql.transaction.expense.ExpenseIsTransactionWithRelationships;
import com.marco.finbill.sql.transaction.expense.ExpenseIsTransactionWithRelationshipsDao;

import java.util.List;

public class ExpenseAdapter extends ListAdapter<ExpenseIsTransactionWithRelationships, ExpenseAdapter.ViewHolder> {

    public ExpenseAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<ExpenseIsTransactionWithRelationships> DIFF_CALLBACK = new DiffUtil.ItemCallback<ExpenseIsTransactionWithRelationships>() {

        @Override
        public boolean areItemsTheSame(ExpenseIsTransactionWithRelationships oldItem, ExpenseIsTransactionWithRelationships newItem) {
            return oldItem.expenseId == newItem.expenseId;
        }

        @Override
        public boolean areContentsTheSame(ExpenseIsTransactionWithRelationships oldItem, ExpenseIsTransactionWithRelationships newItem) {
            // below line is to check the course name, description and course duration.
            return oldItem.transaction.equals(newItem.transaction) && oldItem.fromExpense.equals(newItem.fromExpense) && oldItem.toExpense.equals(newItem.toExpense);
        }
    };

    @NonNull
    @Override
    public ExpenseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.transaction_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseAdapter.ViewHolder holder, int position) {
        ExpenseIsTransactionWithRelationships expenseIsTransactionWithRelationships = getExpenseAt(position);
        // picture
        if (expenseIsTransactionWithRelationships.transaction.getImage() == null) {
            holder.picture.setImageResource(R.drawable.picture_icon);
        } else {
            holder.picture.setImageBitmap(expenseIsTransactionWithRelationships.transaction.getImage());
        }
        holder.title.setText(expenseIsTransactionWithRelationships.transaction.getName());
        holder.from.setText(expenseIsTransactionWithRelationships.fromExpense.getAccountName());
        holder.to.setText(expenseIsTransactionWithRelationships.toExpense.getCategoryName());
        holder.date.setText(expenseIsTransactionWithRelationships.transaction.getDate().toString());
        holder.time.setText(expenseIsTransactionWithRelationships.transaction.getTime().toString());
        holder.sign.setText(R.string.minus);
        holder.amount.setText(String.valueOf(expenseIsTransactionWithRelationships.transaction.getAmount()));
        holder.currency.setText(expenseIsTransactionWithRelationships.transaction.getCurrency().getCurrencyCode());
    }

    public ExpenseIsTransactionWithRelationships getExpenseAt(int position) {
        return getItem(position);
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
