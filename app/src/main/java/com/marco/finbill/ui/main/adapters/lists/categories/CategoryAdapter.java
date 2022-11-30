package com.marco.finbill.ui.main.adapters.lists.categories;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.marco.finbill.R;
import com.marco.finbill.sql.category.Category;
import com.marco.finbill.sql.category.CategoryWithCurrency;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private final ArrayList<CategoryWithCurrency> categories;

    public CategoryAdapter() {
        this.categories = new ArrayList<>();
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.category_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        CategoryWithCurrency categoryWithCurrency = categories.get(position);
        Bitmap image = categoryWithCurrency.getCategory().getCategoryImage();
        if (image == null) {
            holder.picture.setImageResource(R.drawable.picture_icon);
        } else {
            holder.picture.setImageBitmap(image);
        }
        holder.title.setText(categoryWithCurrency.getCategory().getCategoryName());
        double balance = categoryWithCurrency.getCategory().getCategoryBalance();
        holder.sign.setText(balance < 0 ? R.string.minus : R.string.plus);
        holder.amount.setText(new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.getDefault())).format(balance < 0 ? -balance : balance));
        holder.currency.setText(categoryWithCurrency.getCurrency().getCurrencyString());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void updateCategoryList(List<CategoryWithCurrency> categories) {
        CategoryDiffCallback diffCallback = new CategoryDiffCallback(this.categories, categories);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.categories.clear();
        this.categories.addAll(categories);
        diffResult.dispatchUpdatesTo(this);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView picture;
        private final TextView title;
        private final TextView sign;
        private final TextView amount;
        private final TextView currency;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            picture = itemView.findViewById(R.id.picture);
            title = itemView.findViewById(R.id.title);
            sign = itemView.findViewById(R.id.sign);
            amount = itemView.findViewById(R.id.amount);
            currency = itemView.findViewById(R.id.currency);
        }
    }
}