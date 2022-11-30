package com.marco.finbill.ui.main.adapters.spinners.dashboard;

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
import com.marco.finbill.sql.transaction.income.IncomeIsTransactionWithRelationships;

public class IncomeAdapter extends ListAdapter<IncomeIsTransactionWithRelationships, IncomeAdapter.ViewHolder> {

    public IncomeAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<IncomeIsTransactionWithRelationships> DIFF_CALLBACK = new DiffUtil.ItemCallback<IncomeIsTransactionWithRelationships>() {

        @Override
        public boolean areItemsTheSame(IncomeIsTransactionWithRelationships oldItem, IncomeIsTransactionWithRelationships newItem) {
            return oldItem.getIncomeId() == newItem.getIncomeId();
        }

        @Override
        public boolean areContentsTheSame(IncomeIsTransactionWithRelationships oldItem, IncomeIsTransactionWithRelationships newItem) {
            return oldItem.equals(newItem);
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
        if (incomeIsTransactionWithRelationships.getTransactionHasCurrency().getTransaction().getTransactionImage() == null) {
            holder.picture.setImageResource(R.drawable.picture_icon);
        } else {
            holder.picture.setImageBitmap(incomeIsTransactionWithRelationships.getTransactionHasCurrency().getTransaction().getTransactionImage());
        }
        holder.title.setText(incomeIsTransactionWithRelationships.getTransactionHasCurrency().getTransaction().getTransactionName());
        holder.from.setText(incomeIsTransactionWithRelationships.getFromIncome().getCategoryName());
        holder.to.setText(incomeIsTransactionWithRelationships.getToIncome().getAccountName());
        holder.date.setText(incomeIsTransactionWithRelationships.getTransactionHasCurrency().getTransaction().getTransactionDate().toString());
        holder.time.setText(incomeIsTransactionWithRelationships.getTransactionHasCurrency().getTransaction().getTransactionTime().toString());
        holder.sign.setText(R.string.minus);
        holder.amount.setText(String.valueOf(incomeIsTransactionWithRelationships.getTransactionHasCurrency().getTransaction().getTransactionAmount()));
        holder.currency.setText(incomeIsTransactionWithRelationships.getTransactionHasCurrency().getCurrency().getCurrencyString());
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
            currency = itemView.findViewById(R.id.currencySymbol);
        }
    }

}