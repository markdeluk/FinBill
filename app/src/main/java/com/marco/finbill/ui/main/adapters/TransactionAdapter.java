package com.marco.finbill.ui.main.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marco.finbill.R;
import com.marco.finbill.sql.category.Category;
import com.marco.finbill.sql.transaction.Transaction;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private final ArrayList<Transaction> transactions;

    public TransactionAdapter(ArrayList<Transaction> transactions, ArrayList<Category> categories) {
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.transaction_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction transaction = transactions.get(position);
        if (transaction.getImage() != null) {
            holder.picture.setImageBitmap(transaction.getImage());
        }
        else {
            holder.picture.setImageResource(R.drawable.picture_icon);
        }
        holder.titleIcon.setImageResource(R.drawable.title_icon);
        holder.title.setText(transaction.getName());
        holder.categoryIcon.setImageResource(R.drawable.categories_icon); // SISTEMALO
        holder.category.setText(transaction.getCategory());

    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView picture;
        private final ImageView titleIcon;
        private final ImageView categoryIcon;
        private final ImageView dateIcon;
        private final ImageView timeIcon;
        private final ImageView accountIcon;
        private final TextView title;
        private final TextView category;
        private final TextView date;
        private final TextView time;
        private final TextView account;
        private final TextView sign;
        private final TextView amount;
        private final TextView currency;

        public ViewHolder(View itemView) {
            super(itemView);
            picture = itemView.findViewById(R.id.picture);
            titleIcon = itemView.findViewById(R.id.title_icon);
            categoryIcon = itemView.findViewById(R.id.category_icon);
            dateIcon = itemView.findViewById(R.id.date_icon);
            timeIcon = itemView.findViewById(R.id.time_icon);
            accountIcon = itemView.findViewById(R.id.account_icon);
            title = itemView.findViewById(R.id.title);
            category = itemView.findViewById(R.id.category);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            account = itemView.findViewById(R.id.account);
            sign = itemView.findViewById(R.id.sign);
            amount = itemView.findViewById(R.id.amount);
            currency = itemView.findViewById(R.id.currency);
        }
    }
}
