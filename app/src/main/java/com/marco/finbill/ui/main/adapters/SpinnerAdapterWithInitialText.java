package com.marco.finbill.ui.main.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.marco.finbill.R;

/**
 * A SpinnerAdapter which does not show the value of the initial selection initially,
 * but an initialText.
 * To use the spinner with initial selection instead call notifyDataSetChanged().
 */
public class SpinnerAdapterWithInitialText<T> extends ArrayAdapter<T> {

    private final Context context;
    private final int resource;

    private boolean initialTextWasShown = false;

    private final String initialText;

    /**
     * Constructor
     *
     * @param context The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects The objects to represent in the ListView.
     */
    public SpinnerAdapterWithInitialText(@NonNull Context context, int resource, @NonNull T[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.initialText = context.getString(R.string.choose_option);
    }

    /**
     * Returns whether the user has selected a spinner item, or if still the initial text is shown.
     * @param spinner The spinner the SpinnerAdapterWithInitialText is assigned to.
     * @return true if the user has selected a spinner item, false if not.
     */
    public boolean selectionMade(Spinner spinner) {
        return !((TextView)spinner.getSelectedView()).getText().toString().equals(initialText);
    }

    /**
     * Returns a TextView with the initialText the first time getView is called.
     * So the Spinner has an initialText which does not represent the selected item.
     * To use the spinner with initial selection instead call notifyDataSetChanged(),
     * after assigning the SpinnerAdapterWithInitialText.
     */
    @Override
    public View getView(int position, View recycle, ViewGroup container) {
        if(initialTextWasShown) {
            return super.getView(position, recycle, container);
        } else {
            initialTextWasShown = true;
            LayoutInflater inflater = LayoutInflater.from(context);
            final View view = inflater.inflate(resource, container, false);

            ((TextView) view).setText(initialText);

            return view;
        }
    }
}