package com.example.projetoempsoft.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projetoempsoft.R;
import com.example.projetoempsoft.models.Veterinario;

import java.util.List;

/**
 * Created by silvarthur on 3/25/17.
 */

public class VeterinarianListAdapter extends RecyclerView.Adapter<VeterinarianListAdapter.ViewHolder> {

    private List<Veterinario> data;

    private LayoutInflater layoutInflater;

    public VeterinarianListAdapter(Context context, List<Veterinario> data) {

        this.data = data;
        layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.item_veterinarian_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Veterinario currentCompany = data.get(position);

        holder.veterinarianName.setText(currentCompany.getNome());

        String stringEndereco = currentCompany.getRua() + ", " + currentCompany.getNumero() + ", "
                + currentCompany.getCidade() + " - " + currentCompany.getEstado();
        holder.veterinarianAddress.setText(stringEndereco);

    }

    @Override
    public int getItemCount() {

        if(data == null) {
            return 0;
        } else {
            return this.data.size();
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView veterinarianName;
        TextView veterinarianAddress;

        public ViewHolder(View itemView) {
            super(itemView);

            veterinarianName = (TextView) itemView.findViewById(R.id.card_veterinarian_name);
            veterinarianAddress = (TextView) itemView.findViewById(R.id.card_veterinarian_address);

        }

    }
}