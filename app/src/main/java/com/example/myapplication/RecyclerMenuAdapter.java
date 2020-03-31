package com.example.myapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class RecyclerMenuAdapter extends RecyclerView.Adapter<RecyclerMenuAdapter.MyMenuHolder> {

    private Context mContext ;
    private List<Item> mData ;


    public RecyclerMenuAdapter(Context mContext, List<Item> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyMenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.view_card,parent,false);
        return new MyMenuHolder(view);
    }
    @Override
    public void onBindViewHolder(MyMenuHolder holder, final int position) {

        holder.viewtitle.setText("Title:"+mData.get(position).getTitle());
        holder.viewprice.setText("Price:"+mData.get(position).getPrice());
        holder.viewquantity.setText("Quantity:"+mData.get(position).getQuantity());
        holder.viewexpiry.setText("Expiry Date:"+mData.get(position).getExpiry());
        holder.view_thumbnail.setImageBitmap(mData.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyMenuHolder extends RecyclerView.ViewHolder {

        TextView viewtitle;
        TextView viewprice;
        TextView viewexpiry;
        TextView viewquantity;
        ImageView view_thumbnail;
        CardView viewcard;

        public MyMenuHolder(View itemView) {
            super(itemView);
            viewexpiry=(TextView)itemView.findViewById(R.id.viewexpirydate);
            viewtitle = (TextView) itemView.findViewById(R.id.viewtitle) ;
            viewprice = (TextView) itemView.findViewById(R.id.viewprice) ;
            viewquantity = (TextView) itemView.findViewById(R.id.viewquantity) ;
            view_thumbnail = (ImageView) itemView.findViewById(R.id.viewitemImage);
            viewcard = (CardView) itemView.findViewById(R.id.viewcard);
        }
    }


}
