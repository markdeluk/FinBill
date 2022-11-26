package com.marco.finbill.ui.main;

import android.content.Context;
import android.content.res.Resources;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.marco.finbill.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AddItem {

    private List<String> priorityList;
    private Resources resources;
    private ArrayAdapter<String> priorityAdapter;

    public AddItem() {
    }

    public void populatePriorityEdit(Context context, Spinner priorityEdit, int defaultItemResource, int arrayResource) {
        priorityList = new ArrayList<>();
        resources = context.getResources();
        priorityList.add(resources.getString(defaultItemResource));
        priorityList.addAll(Arrays.asList(context.getResources().getStringArray(arrayResource)));
        priorityAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, priorityList);
        priorityEdit.setAdapter(priorityAdapter);
    }

    public List<String> getPriorityList() {
        return priorityList;
    }

    public Resources getResources() {
        return resources;
    }

    public ArrayAdapter<String> getPriorityAdapter() {
        return priorityAdapter;
    }
}
