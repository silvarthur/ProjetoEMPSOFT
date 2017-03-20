package com.example.projetoempsoft.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projetoempsoft.R;
import com.example.projetoempsoft.models.Vaccine;

import java.util.List;

/**
 * Created by silvarthur on 3/19/17.
 */

public class VaccineListAdapter extends RecyclerView.Adapter<VaccineListAdapter.ViewHolder> {

    //private LayoutInflater inflater;

    List<Vaccine> data;

    public VaccineListAdapter(List<Vaccine> data) {
        //inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =  inflater.inflate(R.layout.item_vaccine_list, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Vaccine current = data.get(position);

        holder.vaccineTypeText.setText(current.getVaccineType());
        holder.veterinarianText.setText(current.getVeterinarian());
        holder.date.setText(current.getDate());
        holder.returnDateText.setText(current.getReturnDate());

    }

    @Override
    public int getItemCount() {
        if(this.data == null) {
            return 0;
        } else {
            return this.data.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView vaccineTypeText;
        TextView veterinarianText;
        TextView date;
        TextView returnDateText;

        public ViewHolder(View itemView) {
            super(itemView);

            vaccineTypeText = (TextView) itemView.findViewById(R.id.cardVaccineType);
            veterinarianText = (TextView) itemView.findViewById(R.id.cardVetName);
            date = (TextView) itemView.findViewById(R.id.cardDate);
            returnDateText = (TextView) itemView.findViewById(R.id.cardReturnDate);
        }
    }
}
