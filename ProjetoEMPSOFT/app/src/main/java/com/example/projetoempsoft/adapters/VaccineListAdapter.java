package com.example.projetoempsoft.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projetoempsoft.R;
import com.example.projetoempsoft.models.Vacina;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by silvarthur on 3/19/17.
 */

public class VaccineListAdapter extends RecyclerView.Adapter<VaccineListAdapter.ViewHolder> {

    //private LayoutInflater inflater;

    List<Vacina> data;

    public VaccineListAdapter(List<Vacina> data) {
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
        Vacina current = data.get(position);

        DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        holder.vaccineTypeText.setText(current.getTipoVacina().getNome());
        holder.veterinarianText.setText(current.getVeterinario().getNome());
        holder.date.setText(formater.format(current.getData()));
        holder.returnDateText.setText(formater.format(current.getDataRetorno()));
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
