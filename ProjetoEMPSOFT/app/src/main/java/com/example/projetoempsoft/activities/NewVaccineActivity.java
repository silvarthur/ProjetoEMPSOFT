package com.example.projetoempsoft.activities;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetoempsoft.R;
import com.example.projetoempsoft.models.Vaccine;

import java.text.DateFormat;
import java.util.Calendar;

public class NewVaccineActivity extends AppCompatActivity {

    Calendar calendar = Calendar.getInstance();
    DateFormat formatDate = DateFormat.getDateTimeInstance();

    EditText vaccineType;
    EditText veterinarian;
    TextView date;
    Button chooseDateButton;
    TextView returnDate;
    Button chooseReturnDateButton;
    Button confirmbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_vaccine);

        vaccineType = (EditText) findViewById(R.id.vaccineType);
        veterinarian = (EditText) findViewById(R.id.veterinarian);
        date = (TextView) findViewById(R.id.date);
        chooseDateButton = (Button) findViewById(R.id.chooseDate);
        returnDate = (TextView) findViewById(R.id.returnDate);
        chooseReturnDateButton = (Button) findViewById(R.id.chooseReturnDate);
        confirmbutton = (Button) findViewById(R.id.confirmVaccine);

        chooseDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(NewVaccineActivity.this, listenerOne, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        chooseReturnDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(NewVaccineActivity.this, listenerTwo, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        confirmbutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Vaccine vaccine = new Vaccine(vaccineType.getText().toString(), veterinarian.getText().toString(), date.getText().toString(), returnDate.getText().toString());

               Toast.makeText(getApplication(), "Adicionando nova vacina...", Toast.LENGTH_SHORT).show();
               finish();
           }
        });

    }

    DatePickerDialog.OnDateSetListener listenerOne = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfTheYear, int dayOfTheMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfTheYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfTheMonth);
            date.setText(dayOfTheMonth + "/" + (monthOfTheYear + 1) + "/" + year);
        }
    };

    DatePickerDialog.OnDateSetListener listenerTwo = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfTheYear, int dayOfTheMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfTheYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfTheMonth);
            returnDate.setText(dayOfTheMonth + "/" + (monthOfTheYear + 1) + "/" + year);
        }
    };
}