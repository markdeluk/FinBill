package com.marco.finbill.ui.main.adapters.lists.incomes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.marco.finbill.R;
import com.marco.finbill.sql.transaction.income.IncomeIsTransactionWithRelationships;

import java.util.ArrayList;
import java.util.List;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.ViewHolder> {

    private final ArrayList<IncomeIsTransactionWithRelationships> incomes;

    public IncomeAdapter() {
        this.incomes = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.transaction_list_item, parent, false);
        return new IncomeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IncomeIsTransactionWithRelationships incomeIsTransactionWithRelationships = incomes.get(position);
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

    @Override
    public int getItemCount() {
        return incomes.size();
    }

    public void updateIncomeList(List<IncomeIsTransactionWithRelationships> incomes) {
        IncomeDiffCallback diffCallback = new IncomeDiffCallback(this.incomes, incomes);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.incomes.clear();
        this.incomes.addAll(incomes);
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
