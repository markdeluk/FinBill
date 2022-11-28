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
import com.marco.finbill.sql.transaction.transfer.TransferIsTransactionWithRelationships;

public class TransferAdapter extends ListAdapter<TransferIsTransactionWithRelationships, TransferAdapter.ViewHolder> {

    public TransferAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<TransferIsTransactionWithRelationships> DIFF_CALLBACK = new DiffUtil.ItemCallback<TransferIsTransactionWithRelationships>() {

        @Override
        public boolean areItemsTheSame(TransferIsTransactionWithRelationships oldItem, TransferIsTransactionWithRelationships newItem) {
            return oldItem.getTransferId() == newItem.getTransferId();
        }

        @Override
        public boolean areContentsTheSame(TransferIsTransactionWithRelationships oldItem, TransferIsTransactionWithRelationships newItem) {
            // below line is to check the course name, description and course duration.
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public TransferAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.transaction_list_item, parent, false);
        return new TransferAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransferAdapter.ViewHolder holder, int position) {
        TransferIsTransactionWithRelationships transferIsTransactionWithRelationships = getTransferAt(position);
        // picture
        if (transferIsTransactionWithRelationships.getTransaction().getTransactionImage() == null) {
            holder.picture.setImageResource(R.drawable.picture_icon);
        } else {
            holder.picture.setImageBitmap(transferIsTransactionWithRelationships.getTransaction().getTransactionImage());
        }
        holder.title.setText(transferIsTransactionWithRelationships.getTransaction().getTransactionName());
        holder.from.setText(transferIsTransactionWithRelationships.getFromTransfer().getAccountName());
        holder.to.setText(transferIsTransactionWithRelationships.getToTransfer().getAccountName());
        holder.date.setText(transferIsTransactionWithRelationships.getTransaction().getTransactionDate().toString());
        holder.time.setText(transferIsTransactionWithRelationships.getTransaction().getTransactionTime().toString());
        holder.sign.setText(R.string.minus);
        holder.amount.setText(String.valueOf(transferIsTransactionWithRelationships.getTransaction().getTransactionAmount()));
        holder.currency.setText(transferIsTransactionWithRelationships.getTransaction().getTransactionCurrencyString());
    }

    public TransferIsTransactionWithRelationships getTransferAt(int position) {
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
            currency = itemView.findViewById(R.id.currencyTransactionSymbol);
        }
    }

}