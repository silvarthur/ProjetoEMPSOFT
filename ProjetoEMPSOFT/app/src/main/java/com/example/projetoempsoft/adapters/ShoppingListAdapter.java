package com.example.projetoempsoft.adapters;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projetoempsoft.R;
import com.example.projetoempsoft.activities.FoodDetailsFragment;
import com.example.projetoempsoft.models.Item;

import java.util.List;

/**
 * Created by nathan on 3/26/17.
 */

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

    List<Item> listOfItems;
    FragmentActivity parentActivity;
    Context ct;
    public ShoppingListAdapter(List<Item> listOfItems){
        this.listOfItems = listOfItems;
    }

    @Override
    public ShoppingListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        parentActivity = (FragmentActivity)parent.getContext();
        ct = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =  inflater.inflate(R.layout.shopping_list_item, parent, false);
        ShoppingListAdapter.ViewHolder holder = new ShoppingListAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ShoppingListAdapter.ViewHolder holder, int position) {
        Item current = listOfItems.get(position);
        holder.itemPrice.setText("R$ " + current.getPreco());
        holder.itemTitle.setText(current.getTitulo());

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FoodDetailsFragment();
                FragmentManager fragmentManager = parentActivity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_main, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView itemPrice;
        private TextView itemTitle;
        private CardView cv;

        public ViewHolder(final View itemView) {
            super(itemView);
            itemPrice = (TextView) itemView.findViewById(R.id.card_itemPrice);
            itemTitle = (TextView) itemView.findViewById(R.id.card_itemTitle);
            cv = (CardView) itemView.findViewById(R.id.shoppingListCard);
        }

    }
}
