package com.marco.finbill.ui.main.fragments.categories;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.marco.finbill.R;
import com.marco.finbill.model.FinBillViewModel;
import com.marco.finbill.sql.category.CategoryWithCurrency;
import com.marco.finbill.ui.main.adapters.lists.categories.CategoryAdapter;

import java.util.List;

public class CategoriesAllFragment extends Fragment {

    private FinBillViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(FinBillViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        viewModel.getAllCategoriesWithCurrency().observe(getViewLifecycleOwner(), categoryAdapter::updateCategoryList);
    }
}
