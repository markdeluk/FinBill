package com.marco.finbill.ui.main.fragments.categories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.marco.finbill.R;
import com.marco.finbill.ui.main.dialogs.AddCategoryDialog;

public class CategoriesExpenseFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_categories, container, false);
        FloatingActionButton fab = requireActivity().findViewById(R.id.fab);
        fab.setOnClickListener(v -> AddCategoryDialog.display(getChildFragmentManager()));
        fab.setOnLongClickListener(view -> {
            Snackbar.make(view, R.string.add_category, Snackbar.LENGTH_LONG).show();
            return true;
        });

        return rootView;
    }
}
