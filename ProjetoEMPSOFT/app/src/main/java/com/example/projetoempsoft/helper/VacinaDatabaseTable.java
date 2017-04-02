package com.example.projetoempsoft.helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.projetoempsoft.models.TipoVacina;
import com.example.projetoempsoft.models.Vacina;
import com.example.projetoempsoft.models.Veterinario;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lucasfnf on 21/03/17.
 */

public final class VacinaDatabaseTable implements BaseColumns {
    static final String TABLE_NAME = "vacina";
    static final String COLUMN_NAME_PET = "pet";
    static final String COLUMN_NAME_TIPO_VACINA = "tipo_vacina";
    static final String COLUMN_NAME_DATA = "data";
    static final String COLUMN_NAME_DATA_RETORNO = "data_retorno";
    static final String COLUMN_NAME_VETERINARIO = "veterinario";

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private VacinaDatabaseTable() { }

    private static Date stringToDate(String str) throws ParseException {
        DateFormat formater = new SimpleDateFormat(DATE_FORMAT);
        return formater.parse(str);
    }

    private static String dateToString(Date date) {
        DateFormat formater = new SimpleDateFormat(DATE_FORMAT);
        return formater.format(date);
    }

    static void createTable(SQLiteDatabase db) {
        String SQL_CREATE_VACINA =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER NOT NULL," +
                        COLUMN_NAME_PET + " INTEGER NOT NULL," +
                        COLUMN_NAME_TIPO_VACINA + " INTEGER NOT NULL," +
                        COLUMN_NAME_DATA + " DATETIME NOT NULL," +
                        COLUMN_NAME_DATA_RETORNO + " DATETIME," +
                        COLUMN_NAME_VETERINARIO + " INTEGER NOT NULL," +
                        "CONSTRAINT PK_Tipo_Vacina PRIMARY KEY ("+_ID+")," +
                        "CONSTRAINT FK_Vacina_Tipo_Vacina FOREIGN KEY ("+COLUMN_NAME_PET+") REFERENCES "+PetsDatabaseTable.TABLE_NAME+"("+PetsDatabaseTable._ID+")" +
                        "CONSTRAINT FK_Vacina_Tipo_Vacina FOREIGN KEY ("+COLUMN_NAME_TIPO_VACINA+") REFERENCES "+TipoVacinaDatabaseTable.TABLE_NAME+"("+TipoVacinaDatabaseTable._ID+")" +
                        "CONSTRAINT FK_Vacina_Veterinario FOREIGN KEY ("+COLUMN_NAME_VETERINARIO+") REFERENCES "+VeterinarioDatabaseTable.TABLE_NAME+"("+VeterinarioDatabaseTable._ID+"))";
        db.execSQL(SQL_CREATE_VACINA);

        // INSERINDO DADOS DUMMY
        ContentValues values;
        values = new ContentValues();
        values.put(_ID, 0);
        values.put(COLUMN_NAME_PET, 0);
        values.put(COLUMN_NAME_TIPO_VACINA, 0);
        values.put(COLUMN_NAME_DATA, "2017-01-01 00:00:00");
        values.put(COLUMN_NAME_DATA_RETORNO, "2017-02-02 00:00:00");
        values.put(COLUMN_NAME_VETERINARIO, 0);
        db.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(_ID, 1);
        values.put(COLUMN_NAME_PET, 0);
        values.put(COLUMN_NAME_TIPO_VACINA, 1);
        values.put(COLUMN_NAME_DATA, "2017-01-12 00:00:00");
        values.put(COLUMN_NAME_DATA_RETORNO, "2017-02-12 00:00:00");
        values.put(COLUMN_NAME_VETERINARIO, 0);
        db.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(_ID, 2);
        values.put(COLUMN_NAME_PET, 0);
        values.put(COLUMN_NAME_TIPO_VACINA, 2);
        values.put(COLUMN_NAME_DATA, "2017-01-12 00:00:00");
        values.put(COLUMN_NAME_DATA_RETORNO, "2017-03-12 00:00:00");
        values.put(COLUMN_NAME_VETERINARIO, 0);
        db.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(_ID, 3);
        values.put(COLUMN_NAME_PET, 0);
        values.put(COLUMN_NAME_TIPO_VACINA, 3);
        values.put(COLUMN_NAME_DATA, "2017-04-13 00:00:00");
        values.put(COLUMN_NAME_DATA_RETORNO, "2017-06-15 00:00:00");
        values.put(COLUMN_NAME_VETERINARIO, 0);
        db.insert(TABLE_NAME, null, values);
    }

    public static Vacina getPorId(SQLiteDatabase db, Integer id) {
        String[] projection = {
                _ID,
                COLUMN_NAME_TIPO_VACINA,
                COLUMN_NAME_DATA,
                COLUMN_NAME_DATA_RETORNO,
                COLUMN_NAME_VETERINARIO
        };

        String selection = _ID + " = ?";
        String[] selectionArgs = { id.toString() };

        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        Vacina vacina = null;
        if (cursor.getCount() != 0)
            cursor.moveToNext();
            vacina = converteResultado(db, cursor);
        cursor.close();
        return vacina;
    }

    public static List<Vacina> getTodasVacinas(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME,null);
        Log.d("READ", "Foram lidas "+Integer.toString(cursor.getCount())+" linhas da tabela "+TABLE_NAME);

        List<Vacina> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            Log.d("READ", "Foi lido uma linha do banco de dados");
            items.add(converteResultado(db, cursor));
        }
        cursor.close();
        return items;
    }

    public static List<Vacina> getVacinasPet(SQLiteDatabase db, Integer id) {
        String[] projection = {
                _ID,
                COLUMN_NAME_TIPO_VACINA,
                COLUMN_NAME_DATA,
                COLUMN_NAME_DATA_RETORNO,
                COLUMN_NAME_VETERINARIO
        };

        String selection = COLUMN_NAME_PET + " = ?";
        String[] selectionArgs = { id.toString() };

        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        List<Vacina> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            Log.d("READ", "Foi lido uma linha do banco de dados");
            items.add(converteResultado(db, cursor));
        }
        cursor.close();
        return items;
    }

    public static void saveVacina(SQLiteDatabase db, Vacina vacina, Integer petID) {
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME,null);
        Integer max = 0;
        while (cursor.moveToNext()) {
            Integer i = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
            if (i > max) max = i;
        }
        cursor.close();
        ContentValues values = new ContentValues();
        values.put(_ID, max + 1);
        values.put(COLUMN_NAME_TIPO_VACINA, vacina.getTipoVacina().getId());
        values.put(COLUMN_NAME_PET, petID);
        values.put(COLUMN_NAME_DATA, dateToString(vacina.getData()));
        values.put(COLUMN_NAME_DATA_RETORNO, dateToString(vacina.getDataRetorno()));
        values.put(COLUMN_NAME_VETERINARIO, vacina.getVeterinario().getId());
        db.insert(TABLE_NAME, null, values);
    }

    private static Vacina converteResultado(SQLiteDatabase db, Cursor cursor) {
        Integer id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
        TipoVacina tipoVacina = TipoVacinaDatabaseTable.getPorID(db,
                cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_TIPO_VACINA)));
        Date data;
        Date data_retorno;
        try {
            data = stringToDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_DATA)));
        } catch (ParseException pe) {
            data = new Date();
        }
        try {
            data_retorno = stringToDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_DATA_RETORNO)));
        } catch (ParseException pe) {
            data_retorno = new Date();
        }
        Integer veterinarioID = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_VETERINARIO));
        Veterinario veterinario = VeterinarioDatabaseTable.getPorID(db, veterinarioID);
        if (veterinario == null) {
            Log.d("NULL", "Veterinario retornou com valor nulo (ID "+veterinarioID.toString()+")");
        }
        return new Vacina(id, null, tipoVacina, data, data_retorno, veterinario);
    }
}
