package com.example.adity.whatsup;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    ArrayList<Item> arrayList = new ArrayList<>();
    private Context mContext;
    CardAdapter (ArrayList<Item> arrayList,Context context){

        this.arrayList=arrayList;
        this.mContext=context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemDetail;

        public ViewHolder(View itemView) {
            super(itemView);
            itemImage = (ImageView) itemView.findViewById(R.id.img1);
            itemTitle = (TextView) itemView.findViewById(R.id.item_Title);
            itemDetail = (TextView) itemView.findViewById(R.id.item_details);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Snackbar.make(v, "Click detected " + position, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    if(mContext instanceof MainActivity ){
                        ((MainActivity)mContext).chatscreen();
                    }
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemTitle.setText(arrayList.get(position).getTitle());
        holder.itemDetail.setText(arrayList.get(position).getDescript());
        holder.itemImage.setImageResource(arrayList.get(position).getIconid());
    }
    public void setFilter(List<Item> newlists){
        arrayList = new ArrayList<>();
        arrayList.addAll(newlists);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}

