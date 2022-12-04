package com.marco.finbill.ui.main.adapters.lists.categories;

import android.util.Log;

import androidx.recyclerview.widget.DiffUtil;

import com.marco.finbill.sql.category.Category;
import com.marco.finbill.sql.category.CategoryWithCurrency;

import java.util.List;

public class CategoryDiffCallback extends DiffUtil.Callback {

    private final List<CategoryWithCurrency> oldCategoryList;
    private final List<CategoryWithCurrency> newCategoryList;

    public CategoryDiffCallback(List<CategoryWithCurrency> oldCategoryList, List<CategoryWithCurrency> newCategoryList) {
        this.oldCategoryList = oldCategoryList;
        this.newCategoryList = newCategoryList;
    }

    @Override
    public int getOldListSize() {
        return oldCategoryList.size();
    }

    @Override
    public int getNewListSize() {
        return newCategoryList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldCategoryList.get(oldItemPosition).getCategory().getCategoryId() == newCategoryList.get(newItemPosition).getCategory().getCategoryId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldCategoryList.get(oldItemPosition).equals(newCategoryList.get(newItemPosition));
    }
}
