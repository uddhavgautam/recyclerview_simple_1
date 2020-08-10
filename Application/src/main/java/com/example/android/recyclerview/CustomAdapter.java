/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
 */

package com.example.android.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.common.logger.Log;

/*
. View is the base class for widgets, which are used to create interactive UI components
(buttons, text fields, etc.). The ViewGroup subclass is the base class for layouts, which are
invisible containers that hold other Views (or other ViewGroups) and define their layout properties.
 */


/**
 * Provide views to RecyclerView with data from mDataSet.
 */
//RecyclerView's Adapter is generic
//Adapter got like PC (program counter) for the view inside ViewHolder
//we can get the position of element using Adapter
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private String[] mDataSet;

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    // onCreateViewHolder is by the system
    // gets called after it is left with no views. reuse is already done and we need more views

    //* Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
    //* an item.
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        Log.d(TAG, "On create view holder!");

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    public CustomAdapter(String[] dataSet) {
        mDataSet = dataSet;
    }

    //always gets called for each new item
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.getTextView().setText(mDataSet[position]);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */

    //inner class usually needs to reference outer class.
    // if inner class is non-static, then outer (enclosing) class can't be garbage collected
    // because non-static inner class holds outer class reference implicitly
    //until inner class is garbage collected.

    //the only pitfalls of static inner class, it it can't reference instance members of outer
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        //construct all inside constructor
        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(v1 /* v1 is new View.OnClickListener() */ ->
                    Log.d(TAG, "Element " + this.getAdapterPosition() /* if there is outer
                    Recycler view then it returns position; otherwise it return no_position */
                            + " clicked."));

//            v.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
//                }
//            });
            textView = (TextView) v.findViewById(R.id.textView);
        }

        //getter for textView
        public TextView getTextView() {
            return textView;
        }
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}
