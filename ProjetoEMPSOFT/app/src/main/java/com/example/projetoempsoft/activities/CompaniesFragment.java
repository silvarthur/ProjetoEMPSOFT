package com.example.projetoempsoft.activities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetoempsoft.R;
import com.example.projetoempsoft.adapters.PetShopListAdapter;
import com.example.projetoempsoft.adapters.VeterinarianListAdapter;
import com.example.projetoempsoft.helper.DatabaseHelper;
import com.example.projetoempsoft.helper.PetShopDatabaseTable;
import com.example.projetoempsoft.helper.VeterinarioDatabaseTable;
import com.example.projetoempsoft.models.PetShop;
import com.example.projetoempsoft.models.Veterinario;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CompaniesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CompaniesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompaniesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;

    RecyclerView recyclerViewPetShops;
    RecyclerView recyclerViewVeterinarians;

    PetShopListAdapter petShopListAdapter;
    VeterinarianListAdapter veterinarianListAdapter;

    private OnFragmentInteractionListener mListener;

    public CompaniesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CompaniesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CompaniesFragment newInstance(String param1, String param2) {

        CompaniesFragment fragment = new CompaniesFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        //}

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_companies, container, false);

        recyclerViewPetShops = (RecyclerView) view.findViewById(R.id.pet_shops_list);
        petShopListAdapter = new PetShopListAdapter(getContext(), getListPetShops());
        recyclerViewPetShops.setAdapter(petShopListAdapter);
        recyclerViewPetShops.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerViewVeterinarians = (RecyclerView) view.findViewById(R.id.veterinarians_list);
        veterinarianListAdapter = new VeterinarianListAdapter(getContext(), getListVeterinarians());
        recyclerViewVeterinarians.setAdapter(veterinarianListAdapter);
        recyclerViewVeterinarians.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;

    }

    public List<PetShop> getListPetShops() {
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        List<PetShop> listOfPetShops = PetShopDatabaseTable.getTodosPetShops(db);

        return listOfPetShops;
    }

    public List<Veterinario> getListVeterinarians() {
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        List<Veterinario> listOfVeterinarians = VeterinarioDatabaseTable.getTodosVeterinario(db);

        return listOfVeterinarians;
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
