package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;



public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private ArrayList<Item> mData ;
    private ArrayList<Item> next ;


    public RecyclerViewAdapter(Context mContext, ArrayList<Item> mData) {
        this.mContext = mContext;
        this.mData = mData;
        next=new ArrayList<Item>(mData);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

//          holder.title.setText(mData.get(position).getTitle());
            holder.img.setImageBitmap(mData.get(position).getImage());
            holder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mData.get(position).setPrice(holder.priceIn.getText().toString());
                    mData.get(position).setQuantity(holder.quantityIn.getText().toString());
                    mData.get(position).setTitle(holder.titleIn.getText().toString());
                    mData.get(position).setExpiry(holder.expriryIn.getText().toString());
                    mData.remove(holder.getAdapterPosition());
                    notifyDataSetChanged();
                    if(mData.isEmpty()){
                        Intent intent = new Intent(mContext, ItemActivity.class);
                        ItemActivity.Items=next;
                        mContext.startActivity(intent);
                    }
                    holder.priceIn.setText("");
                    holder.quantityIn.setText("");
                    holder.titleIn.setText("");
                    holder.expriryIn.setText("");
                }
            });
            holder.cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    next.remove(holder.getAdapterPosition());
                    mData.remove(holder.getAdapterPosition());
                    notifyItemRemoved(holder.getAdapterPosition());
                    notifyItemRangeChanged(holder.getAdapterPosition(), mData.size());
                    notifyDataSetChanged();
                    if(mData.isEmpty()){
                        Intent intent = new Intent(mContext, ItemActivity.class);
                        ItemActivity.Items=next;
                        mContext.startActivity(intent);
                    }
                }
            });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView price;
        TextView quantity;
        TextView expriry;
        EditText titleIn;
        EditText priceIn;
        EditText expriryIn;
        EditText quantityIn;
        ImageView img;
        Button add;
        Button cancel;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);
            expriry=(TextView) itemView.findViewById(R.id.expirydate);
            expriryIn=(EditText) itemView.findViewById(R.id.expiryIn);
            add=(Button)itemView.findViewById(R.id.add);
            cancel=(Button)itemView.findViewById(R.id.cancel);
            title = (TextView) itemView.findViewById(R.id.title) ;
            price = (TextView) itemView.findViewById(R.id.price) ;
            quantity = (TextView) itemView.findViewById(R.id.quantity) ;
            titleIn = (EditText) itemView.findViewById(R.id.titleIn) ;
            priceIn = (EditText) itemView.findViewById(R.id.priceIn) ;
            quantityIn = (EditText) itemView.findViewById(R.id.quantityIn) ;
            img = (ImageView) itemView.findViewById(R.id.itemImage);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);

        }
    }


}