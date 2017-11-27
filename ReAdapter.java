package com.example.adity.whatsup;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReAdapter extends RecyclerView.Adapter<ReAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    public List<Item> data= Collections.emptyList();
    private Context mContext;
    public ReAdapter(Context context, List<Item> data){
        inflater=LayoutInflater.from(context);
        this.data=data;
        this.mContext=context;
    }

    @Override
    public ReAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.custom_row, parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Item current=data.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconid);
        holder.descripts.setText(current.descript);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setFilter(List<Item> newlists){
        data = new ArrayList<>();
        data.addAll(newlists);
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView title;
        public ImageView icon;
        public TextView descripts;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title= (TextView) itemView.findViewById(R.id.listText);
            icon= (ImageView) itemView.findViewById(R.id.listIcon);
            descripts= (TextView) itemView.findViewById(R.id.des);
        }

        @Override
        public void onClick(View v) {
            if(mContext instanceof MainActivity ){
                ((MainActivity)mContext).chatscreen();
            }
        }
    }


}
