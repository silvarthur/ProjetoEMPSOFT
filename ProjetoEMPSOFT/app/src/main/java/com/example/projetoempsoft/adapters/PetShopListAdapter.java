package com.example.projetoempsoft.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projetoempsoft.R;
import com.example.projetoempsoft.models.PetShop;

import java.util.List;

/**
 * Created by silvarthur on 3/25/17.
 */

public class PetShopListAdapter extends RecyclerView.Adapter<PetShopListAdapter.ViewHolder> {

    private List<PetShop> data;

    private LayoutInflater layoutInflater;

    public PetShopListAdapter(Context context, List<PetShop> data) {

        this.data = data;
        layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.item_petshop_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        PetShop currentCompany = data.get(position);

        holder.petShopName.setText(currentCompany.getNome());

        String stringEndereco = currentCompany.getRua() + ", " + currentCompany.getNumero() + ", "
                + currentCompany.getCidade() + " - " + currentCompany.getEstado();
        holder.petShopAddress.setText(stringEndereco);

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

        TextView petShopName;
        TextView petShopAddress;

        public ViewHolder(View itemView) {
            super(itemView);

            petShopName = (TextView) itemView.findViewById(R.id.card_pet_shop_name);
            petShopAddress = (TextView) itemView.findViewById(R.id.card_pet_shop_address);

        }

    }
}