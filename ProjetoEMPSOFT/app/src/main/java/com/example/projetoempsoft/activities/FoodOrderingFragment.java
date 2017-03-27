package com.example.projetoempsoft.activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.projetoempsoft.R;
import com.example.projetoempsoft.adapters.ShoppingListAdapter;
import com.example.projetoempsoft.models.Item;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FoodOrderingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FoodOrderingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodOrderingFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private RecyclerView itemsListView;
    private ShoppingListAdapter adapter;

    public FoodOrderingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoodOrderingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodOrderingFragment newInstance(String param1, String param2) {
        FoodOrderingFragment fragment = new FoodOrderingFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_food_ordering, container, false);
        itemsListView = (RecyclerView) myView.findViewById(R.id.shoppingList);

        Item item1 = new Item("Whiskas 300g", "Raçao para gatos", 15.00d);
        Item item2 = new Item("Pedigree", "Raçao para caes e gatos", 15.00d);
        Item item3 = new Item("Whiskas 800g", "Raçao para gatos", 20.00d);

        List<Item> list = new ArrayList<Item>();
        list.add(item1);
        list.add(item2);
        list.add(item3);

        adapter = new ShoppingListAdapter(list);
        itemsListView.setAdapter(adapter);
        itemsListView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return myView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
