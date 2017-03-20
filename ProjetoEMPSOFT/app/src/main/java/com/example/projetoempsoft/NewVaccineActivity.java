package com.example.projetoempsoft;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

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