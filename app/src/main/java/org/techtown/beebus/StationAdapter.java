package org.techtown.beebus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.ViewHolder>{

    ArrayList<Station> items = new ArrayList<Station>();

    public void setItems(ArrayList<Station> items){this.items = items;}

    public void clearItems(){ items.clear(); }
    public void addItem(Station station){ items.add(station); }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.station_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Station item =items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView bus_id;
        TextView bus_nm;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bus_id = itemView.findViewById(R.id.tv_bstopnm);
            bus_nm = itemView.findViewById(R.id.tv_carno);
        }

        public void setItem(Station station){
            bus_id.setText(station.carno);
            bus_nm.setText(station.bstopnm);
        }
    }
}
