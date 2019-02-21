package com.rajkumarrajan.sampleapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rajkumarrajan.sampleapplication.Pojo.MyPojo;
import com.rajkumarrajan.sampleapplication.R;
import com.rajkumarrajan.sampleapplication.activity.RecyclerActivity;

import java.util.List;

public class RecyclerActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<MyPojo> myPojos;
    public RecyclerActivityAdapter(RecyclerActivity recyclerActivity, List<MyPojo> body){
        this.context = recyclerActivity;
        this.myPojos = body;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView TextViewTitle;
        private ViewHolder(View itemView) {
            super(itemView);
            TextViewTitle = (TextView) itemView.findViewById(R.id.TextViewTitle);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder = null;
        View menuItemLayoutView = LayoutInflater.from(context).inflate(
                R.layout.recycler_card_view, viewGroup, false);
        viewHolder = new ViewHolder(menuItemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.TextViewTitle.setText(myPojos.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return myPojos.size();
    }
}
