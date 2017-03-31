package com.example.projetoempsoft.activities;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.projetoempsoft.R;
import com.example.projetoempsoft.helper.AgendamentoDatabaseTable;
import com.example.projetoempsoft.helper.DatabaseHelper;
import com.example.projetoempsoft.models.Agendamento;
import com.example.projetoempsoft.models.StatusAgendamento;
import com.example.projetoempsoft.models.TipoAgendamento;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewScheduleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewScheduleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewScheduleFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    public NewScheduleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewScheduleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewScheduleFragment newInstance(String param1, String param2) {
        NewScheduleFragment fragment = new NewScheduleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_new_schedule, container, false);

        final EditText date = (EditText) myView.findViewById(R.id.scheduleDate);
        final EditText hour = (EditText) myView.findViewById(R.id.scheduleHour);
        final Button submit = (Button) myView.findViewById(R.id.submitSchedule);
        final Checkable banho = (Checkable) myView.findViewById(R.id.banhoCheck);
        final Checkable tosa = (Checkable) myView.findViewById(R.id.tosaCheck);
        final Checkable consulta = (Checkable) myView.findViewById(R.id.consultaCheck);

        date.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int inType = date.getInputType(); // backup the input type
                date.setInputType(InputType.TYPE_NULL); // disable soft input
                date.onTouchEvent(event); // call native handler
                date.setInputType(inType);
                return true;
            }
        });

        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    DatePickerDialog dialog = new DatePickerDialog(getContext());
                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

                    dialog.show();

                    dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            date.setText(dayOfMonth+"/"+month+"/"+year);
                        }
                    });
                }
            }
        });

        hour.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int inType = hour.getInputType(); // backup the input type
                hour.setInputType(InputType.TYPE_NULL); // disable soft input
                hour.onTouchEvent(event); // call native handler
                hour.setInputType(inType);
                return true;
            }
        });

        hour.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Calendar c = Calendar.getInstance();
                    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            hour.setText(String.format("%02d:%02d", hourOfDay, minute));
                        }
                    };

                    TimePickerDialog timePicker = new TimePickerDialog(getContext(), t, c.getTime().getHours(), c.getTime().getMinutes(), true);
                    timePicker.show();
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Consulta enviada para avaliação, espere confirmação!", Toast.LENGTH_SHORT).show();
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date scheduleDateObj = null;

                try{
                    String scheduleDate = date.getText().toString();
                    scheduleDateObj = df.parse(scheduleDate);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Agendamento ag = new Agendamento(getCheckedBoxes(banho, tosa, consulta), scheduleDateObj, hour.getText().toString(), StatusAgendamento.PENDENTE);
                DatabaseHelper dbHelper = new DatabaseHelper(getContext());
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                AgendamentoDatabaseTable.saveAgendamento(db, ag);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }
        });

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
        mListener = null;
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

    public TipoAgendamento getCheckedBoxes(Checkable banho, Checkable tosa, Checkable consulta){
        if(banho.isChecked() && tosa.isChecked() && !consulta.isChecked()){
            return TipoAgendamento.BANHO_E_TOSA;
        }else if(banho.isChecked() && !tosa.isChecked() && !consulta.isChecked()){
            return TipoAgendamento.BANHO;
        }else if(!banho.isChecked() && tosa.isChecked() && !consulta.isChecked()){
            return TipoAgendamento.TOSA;
        }else if(!banho.isChecked() && !tosa.isChecked() && consulta.isChecked()){
            return TipoAgendamento.CONSULTA;
        }else if(banho.isChecked() && tosa.isChecked() && consulta.isChecked()) {
            return TipoAgendamento.BANHO_TOSA_CONSULTA;
        }else{
            return null;
        }
    }
}
