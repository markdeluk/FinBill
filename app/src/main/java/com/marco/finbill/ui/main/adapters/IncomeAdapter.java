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
import com.marco.finbill.sql.transaction.expense.ExpenseIsTransactionWithRelationships;
import com.marco.finbill.sql.transaction.income.Income;
import com.marco.finbill.sql.transaction.income.IncomeIsTransactionWithRelationships;
import com.marco.finbill.sql.transaction.income.IncomeIsTransactionWithRelationshipsDao;

import java.util.List;

public class IncomeAdapter extends ListAdapter<IncomeIsTransactionWithRelationships, IncomeAdapter.ViewHolder> {

    public IncomeAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<IncomeIsTransactionWithRelationships> DIFF_CALLBACK = new DiffUtil.ItemCallback<IncomeIsTransactionWithRelationships>() {

        @Override
        public boolean areItemsTheSame(IncomeIsTransactionWithRelationships oldItem, IncomeIsTransactionWithRelationships newItem) {
            return oldItem.incomeId == newItem.incomeId;
        }

        @Override
        public boolean areContentsTheSame(IncomeIsTransactionWithRelationships oldItem, IncomeIsTransactionWithRelationships newItem) {
            // below line is to check the course name, description and course duration.
            return oldItem.transaction.equals(newItem.transaction) && oldItem.fromIncome.equals(newItem.fromIncome) && oldItem.toIncome.equals(newItem.toIncome);
        }
    };

    @NonNull
    @Override
    public IncomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.transaction_list_item, parent, false);
        return new IncomeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeAdapter.ViewHolder holder, int position) {
        IncomeIsTransactionWithRelationships incomeIsTransactionWithRelationships = getIncomeAt(position);
        // picture
        if (incomeIsTransactionWithRelationships.transaction.getImage() == null) {
            holder.picture.setImageResource(R.drawable.picture_icon);
        } else {
            holder.picture.setImageBitmap(incomeIsTransactionWithRelationships.transaction.getImage());
        }
        holder.title.setText(incomeIsTransactionWithRelationships.transaction.getName());
        holder.from.setText(incomeIsTransactionWithRelationships.fromIncome.getCategoryName());
        holder.to.setText(incomeIsTransactionWithRelationships.toIncome.getAccountName());
        holder.date.setText(incomeIsTransactionWithRelationships.transaction.getDate().toString());
        holder.time.setText(incomeIsTransactionWithRelationships.transaction.getTime().toString());
        holder.sign.setText(R.string.minus);
        holder.amount.setText(String.valueOf(incomeIsTransactionWithRelationships.transaction.getAmount()));
        holder.currency.setText(incomeIsTransactionWithRelationships.transaction.getCurrency().getCurrencyCode());
    }

    public IncomeIsTransactionWithRelationships getIncomeAt(int position) {
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