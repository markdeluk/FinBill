package com.marco.finbill.ui.main.fragments.categories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.marco.finbill.R;
import com.marco.finbill.enums.CategoryType;
import com.marco.finbill.sql.model.FinBillViewModel;
import com.marco.finbill.ui.main.adapters.lists.categories.CategoryAdapter;

public class CategoriesIncomeFragment extends Fragment {

    private FinBillViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(FinBillViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categories_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CategoryAdapter categoryAdapter = new CategoryAdapter();
        RecyclerView recyclerView = view.findViewById(R.id.categoriesRecyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(categoryAdapter);
        viewModel.getAllCategoriesWithCurrencyByType(CategoryType.INCOME).observe(getViewLifecycleOwner(), categoryAdapter::updateCategoryList);
    }
}
