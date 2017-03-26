package com.example.projetoempsoft.activities;

import android.content.Context;
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
import com.example.projetoempsoft.models.Endereco;
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

        List<PetShop> listOfPetShops = new ArrayList<>();

        Endereco endereco1 = new Endereco(1, "Rua 10", "Campina Grande", "PB", "Nenhum", "159", "11111-111");
        Endereco endereco2 = new Endereco(1, "Rua 11", "Campina Grande", "PB", "Nenhum", "160", "90189-456");
        Endereco endereco3 = new Endereco(1, "Rua 12", "Campina Grande", "PB", "Nenhum", "161", "12900-888");

        PetShop petShop1 = new PetShop();
        petShop1.setNome("Casa do Animal");
        petShop1.setEndereco(endereco1);

        PetShop petShop2 = new PetShop();
        petShop2.setNome("Mundo Animal");
        petShop2.setEndereco(endereco2);

        PetShop petShop3 = new PetShop();
        petShop3.setNome("CG Animal Shop");
        petShop3.setEndereco(endereco3);

        listOfPetShops.add(petShop1);
        listOfPetShops.add(petShop2);
        listOfPetShops.add(petShop3);

        return listOfPetShops;

    }

    public List<Veterinario> getListVeterinarians() {

        List<Veterinario> listOfVeterinarians = new ArrayList<>();

        Endereco endereco1 = new Endereco(2, "Rua 16", "Campina Grande", "PB", "Nenhum", "222", "12345-678");
        Veterinario veterinario1 = new Veterinario(2, "Clínica do Animal", endereco1);

        Endereco endereco2 = new Endereco(3, "Rua 17", "Campina Grande", "PB", "Nenhum", "222", "99999-678");
        Veterinario veterinario2 = new Veterinario(2, "Clínica do Animal", endereco2);

        //Endereco endereco3 = new Endereco(4, "Rua 18", "Campina Grande", "PB", "Nenhum", "222", "10101-890");
        //Veterinario veterinario3 = new Veterinario(2, "Clínica do Animal", endereco3);

        listOfVeterinarians.add(veterinario1);
        //listOfVeterinarians.add(veterinario2);
        //listOfVeterinarians.add(veterinario3);

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
