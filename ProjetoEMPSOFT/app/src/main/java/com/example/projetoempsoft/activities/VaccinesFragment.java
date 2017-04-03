package com.example.projetoempsoft.activities;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetoempsoft.R;
import com.example.projetoempsoft.adapters.VaccineListAdapter;
import com.example.projetoempsoft.helper.DatabaseHelper;
import com.example.projetoempsoft.helper.VacinaDatabaseTable;
import com.example.projetoempsoft.models.Vacina;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VaccinesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VaccinesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VaccinesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;

    public FloatingActionButton addNewVaccineButton;

    private RecyclerView listOfVaccines;
    private RecyclerView.Adapter adapter;

    private OnFragmentInteractionListener mListener;

    DatabaseHelper mDbHelper;
    SQLiteDatabase db;


    public VaccinesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VaccinesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VaccinesFragment newInstance(String param1, String param2) {
        VaccinesFragment fragment = new VaccinesFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vaccines, container, false);

        listOfVaccines = (RecyclerView) view.findViewById(R.id.vaccinesList);

        mDbHelper = new DatabaseHelper(getContext());
        db = mDbHelper.getReadableDatabase();
        // TODO nesse ponto é necessario ter um Pet na memoria para passar o id como parametro
        List<Vacina> data = VacinaDatabaseTable.getVacinasPet(db, 0);

        adapter = new VaccineListAdapter(data);

        listOfVaccines.setAdapter(adapter);
        listOfVaccines.setLayoutManager(new LinearLayoutManager(this.getContext()));

        addNewVaccineButton = (FloatingActionButton) view.findViewById(R.id.add_new_vaccine_button);
        addNewVaccineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NewVaccineActivity.class);
                startActivity(intent);
            }
        });

        return view;
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

    @Override
    public void onResume() {
        super.onResume();

        List<Vacina> data = VacinaDatabaseTable.getVacinasPet(db, 0);

        adapter = new VaccineListAdapter(data);

        listOfVaccines.setAdapter(adapter);
        listOfVaccines.setLayoutManager(new LinearLayoutManager(this.getContext()));

        adapter.notifyDataSetChanged();
    }
}
