package com.example.dellpc.skychat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private ArrayList<DataObject> mDataset;


    public static class MyViewHolder extends RecyclerView.ViewHolder
            {
       public TextView label;
     public    TextView value;

        public MyViewHolder(View view) {
            super(view);
            label = (TextView) view.findViewById(R.id.textView);
            value = (TextView) view.findViewById(R.id.textView2);

        }



    }
    public MyRecyclerViewAdapter(ArrayList<DataObject> myDataset) {
        mDataset = myDataset;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View itemview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_profile, parent, false);

        MyViewHolder MyViewHolder = new MyViewHolder(itemview);
        return MyViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.label.setText(mDataset.get(position).getmText1());
        holder.value.setText(mDataset.get(position).getmText2());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}