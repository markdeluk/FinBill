package com.marco.finbill.ui.main.adapters.lists.categories;

import androidx.recyclerview.widget.DiffUtil;

import com.marco.finbill.sql.category.Category;

import java.util.List;

public class CategoryDiffCallback extends DiffUtil.Callback {

    private final List<Category> oldCategoryList;
    private final List<Category> newCategoryList;

    public CategoryDiffCallback(List<Category> oldCategoryList, List<Category> newCategoryList) {
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
        return oldCategoryList.get(oldItemPosition).getCategoryId() == newCategoryList.get(newItemPosition).getCategoryId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldCategoryList.get(oldItemPosition).equals(newCategoryList.get(newItemPosition));
    }
}
