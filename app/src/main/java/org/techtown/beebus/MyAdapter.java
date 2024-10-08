package org.techtown.beebus;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Item> mList;
    private LayoutInflater mInflate;
    private Context mContext;
  
    public MyAdapter(Context context, ArrayList<Item> itmes) {
        this.mList = itmes;
        this.mInflate = LayoutInflater.from(context);

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = mInflate.inflate(R.layout.item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //binding
        holder.bstopnm.setText(mList.get(position).bstopnm);
        holder.carno.setText(mList.get(position).carno);
        holder.image.setImageResource(mList.get(position).image);

        //Click event
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //ViewHolder
    public  class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView bstopnm;
        public TextView carno;
        public ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);

            bstopnm = itemView.findViewById(R.id.tv_bstopnm);
            carno = itemView.findViewById(R.id.tv_carno);
            image = itemView.findViewById(R.id.imageView2);

            itemView.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    int position2 = getAdapterPosition();
                    Item item =mList.get(position2);
                    Intent intent = new Intent(mContext.getApplicationContext(),search.class);
                    intent.putExtra("bstid",item.getBstdid());
                    intent.putExtra("busnum",item.getBusnum());
                    intent.putExtra("bstopnm",item.getBstopnm());
                    mContext.startActivity(intent);
                }
            });
        }
    }


}
