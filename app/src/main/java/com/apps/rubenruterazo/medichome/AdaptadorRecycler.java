package com.apps.rubenruterazo.medichome;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorRecycler extends RecyclerView.Adapter<AdaptadorRecycler.ViewHolder> {
    private ArrayList<CardsAnalisis> mcaList;
    private OnItemClickListener mListener;
    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mText1;
        public TextView mText2;


        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView=itemView.findViewById(R.id.imagenC);
            mText1=itemView.findViewById(R.id.line1);
            mText2=itemView.findViewById(R.id.line2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position=getAdapterPosition();
                        if (position!= RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position=getAdapterPosition();
                        if (position!= RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    public AdaptadorRecycler(ArrayList<CardsAnalisis> caList){
        mcaList=caList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_analisis,parent,false);
        ViewHolder evh=new ViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardsAnalisis ca=mcaList.get(position);
        holder.mImageView.setImageResource(ca.getImageResource());
        holder.mText1.setText(ca.getText1());
        holder.mText2.setText(ca.getText2());
    }

    @Override
    public int getItemCount() {
        return mcaList.size();
    }
}
