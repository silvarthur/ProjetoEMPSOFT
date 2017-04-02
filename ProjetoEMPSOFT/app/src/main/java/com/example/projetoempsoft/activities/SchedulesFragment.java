package com.example.projetoempsoft.activities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.projetoempsoft.R;
import com.example.projetoempsoft.adapters.ScheduleAdapter;
import com.example.projetoempsoft.helper.AgendamentoDatabaseTable;
import com.example.projetoempsoft.helper.DatabaseHelper;
import com.example.projetoempsoft.models.Agendamento;
import com.example.projetoempsoft.models.StatusAgendamento;
import com.example.projetoempsoft.models.TipoAgendamento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SchedulesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SchedulesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SchedulesFragment extends Fragment {

    private ScheduleAdapter adapter;
    private RecyclerView recScheduleList;
    private OnFragmentInteractionListener mListener;

    public SchedulesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SchedulesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SchedulesFragment newInstance(String param1, String param2) {
        SchedulesFragment fragment = new SchedulesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_schedules, container, false);

        FloatingActionButton addScheduleBtn = (FloatingActionButton) myView.findViewById(R.id.newScheduleBtn);

        addScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new NewScheduleFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_main, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        recScheduleList = (RecyclerView) myView.findViewById(R.id.scheduleListView);

        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // TODO nesse ponto é necessario ter um User em memoria para passar o ID para o metodo
        // isso seria para depois do MVP
        List<Agendamento> AgList = AgendamentoDatabaseTable.getAgendamentosPorUsuario(db, 0);

        adapter = new ScheduleAdapter(AgList);

        recScheduleList.setAdapter(adapter);
        recScheduleList.setLayoutManager(new LinearLayoutManager(this.getContext()));

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
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
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
        adapter.notifyDataSetChanged();
    }
}
