package com.example.projetoempsoft.activities;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetoempsoft.R;
import com.example.projetoempsoft.helper.DatabaseHelper;
import com.example.projetoempsoft.helper.PetsDatabaseTable;
import com.example.projetoempsoft.helper.TipoVacinaDatabaseTable;
import com.example.projetoempsoft.helper.VacinaDatabaseTable;
import com.example.projetoempsoft.helper.VeterinarioDatabaseTable;
import com.example.projetoempsoft.models.Vacina;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewVaccineActivity extends AppCompatActivity {

    Calendar calendar = Calendar.getInstance();
    DateFormat formatDate = DateFormat.getDateTimeInstance();

    EditText vaccineType;
    EditText veterinarian;
    EditText date;
    EditText returnDate;
    Button confirmButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_vaccine);

        vaccineType = (EditText) findViewById(R.id.vaccineType);
        veterinarian = (EditText) findViewById(R.id.veterinarian);
        date = (EditText) findViewById(R.id.date);
        returnDate = (EditText) findViewById(R.id.returnDate);
        confirmButton = (Button) findViewById(R.id.confirmVaccine);
        cancelButton = (Button) findViewById(R.id.cancelVaccine);

        date.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int inputType = date.getInputType();
                date.setInputType(InputType.TYPE_NULL);
                date.onTouchEvent(motionEvent);
                date.setInputType(inputType);
                return true;
            }
        });

        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus) {
                    DatePickerDialog dialog = new DatePickerDialog(NewVaccineActivity.this, listenerOne, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                    dialog.show();
                }
            }
        });

        returnDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int inputType = returnDate.getInputType();
                returnDate.setInputType(InputType.TYPE_NULL);
                returnDate.onTouchEvent(motionEvent);
                returnDate.setInputType(inputType);
                return true;
            }
        });

        returnDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus) {
                    DatePickerDialog dialog = new DatePickerDialog(NewVaccineActivity.this, listenerTwo, calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                    dialog.show();
                }
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper mDbHelper = new DatabaseHelper(getApplicationContext());
                SQLiteDatabase dbr = mDbHelper.getReadableDatabase();
                SQLiteDatabase dbw = mDbHelper.getReadableDatabase();

                DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
                Vacina vacina = null;
                try {
                    // TODO nesse ponto precisa haver um Pet em memoria para passar para o construtor de Vacina
                    vacina = new Vacina(
                            0,
                            PetsDatabaseTable.getPorId(dbr, 0),
                            vaccineType.getText().toString(),
                            formater.parse(date.getText().toString()),
                            formater.parse(returnDate.getText().toString()),
                            VeterinarioDatabaseTable.getPorID(dbr, 0));
                    VacinaDatabaseTable.saveVacina(dbw, vacina, 0);

                    Toast.makeText(getApplication(), "Adicionando nova vacina...", Toast.LENGTH_SHORT).show();
                } catch (ParseException pe) {
                    Toast.makeText(getApplication(), "Falha ao adicionar nova vacina...", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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