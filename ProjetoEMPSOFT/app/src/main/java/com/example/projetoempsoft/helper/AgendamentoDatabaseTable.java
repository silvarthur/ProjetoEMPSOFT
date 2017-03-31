package com.example.projetoempsoft.helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.provider.BaseColumns;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.projetoempsoft.models.Agendamento;
import com.example.projetoempsoft.models.StatusAgendamento;
import com.example.projetoempsoft.models.TipoAgendamento;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lucasfnf on 29/03/17.
 */

public final class AgendamentoDatabaseTable implements BaseColumns {
    static final String TABLE_NAME = "agendamento";
    static final String COLUMN_NAME_USER = "user";
    static final String COLUMN_NAME_TIPO_AGENDAMENTO = "tipo";
    static final String COLUMN_NAME_DATA = "data";
    static final String COLUMN_NAME_STATUS_AGENDAMENTO = "status";

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private AgendamentoDatabaseTable() { }

    private static Date stringToDate(String str) throws ParseException {
        DateFormat formater = new SimpleDateFormat(DATE_FORMAT);
        return formater.parse(str);
    }

    private static String dateToString(Date date) {
        DateFormat formater = new SimpleDateFormat(DATE_FORMAT);
        return formater.format(date);
    }

    static void createTable(SQLiteDatabase db) {
        String SQL_CREATE_AGENDAMENTO =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        _ID + " INTEGER NOT NULL," +
                        COLUMN_NAME_USER + " INTEGER NOT NULL," +
                        COLUMN_NAME_TIPO_AGENDAMENTO + " VARCHAR2(20) NOT NULL," +
                        COLUMN_NAME_DATA + " DATETIME NOT NULL," +
                        COLUMN_NAME_STATUS_AGENDAMENTO + " VARCHAR2(20) NOT NULL," +
                        "CONSTRAINT PK_Agendamento PRIMARY KEY(" + _ID + ")," +
                        "CONSTRAINT FK_Agendamento_User FOREIGN KEY("+COLUMN_NAME_USER+") REFERENCES " + UserDatabaseTable.TABLE_NAME + "(" + UserDatabaseTable._ID + "))";
        db.execSQL(SQL_CREATE_AGENDAMENTO);

        ContentValues values;
        values = new ContentValues();
        values.put(_ID, 0);
        values.put(COLUMN_NAME_USER, 0);
        values.put(COLUMN_NAME_TIPO_AGENDAMENTO, TipoAgendamento.BANHO.toString());
        values.put(COLUMN_NAME_DATA, "2017-04-12 15:00:00");
        values.put(COLUMN_NAME_STATUS_AGENDAMENTO, StatusAgendamento.EM_ANDAMENTO.toString());
        db.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(_ID, 1);
        values.put(COLUMN_NAME_USER, 0);
        values.put(COLUMN_NAME_TIPO_AGENDAMENTO, TipoAgendamento.BANHO_E_TOSA.toString());
        values.put(COLUMN_NAME_DATA, "2017-04-12 15:00:00");
        values.put(COLUMN_NAME_STATUS_AGENDAMENTO, StatusAgendamento.AGENDADO.toString());
        db.insert(TABLE_NAME, null, values);
    }

    public static Agendamento getPorId(SQLiteDatabase db, Integer id) {
        String[] projection = {
                _ID,
                COLUMN_NAME_TIPO_AGENDAMENTO,
                COLUMN_NAME_DATA,
                COLUMN_NAME_STATUS_AGENDAMENTO
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
        Agendamento agendamento = null;
        if (cursor.getCount() != 0) {
            cursor.moveToNext();
            agendamento = converteResultado(db, cursor);
        }
        cursor.close();
        return agendamento;
    }

    public static List<Agendamento> getTodosPets(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME,null);
        Log.d("READ", "Foram lidas "+Integer.toString(cursor.getCount())+" linhas da tabela "+TABLE_NAME);

        List<Agendamento> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            Log.d("READ", "Foi lido uma linha do banco de dados");
            items.add(converteResultado(db, cursor));
        }
        cursor.close();
        return items;
    }

    public static List<Agendamento> getAgendamentosPorUsuario(SQLiteDatabase db, Integer id) {
        String[] projection = {
                _ID,
                COLUMN_NAME_TIPO_AGENDAMENTO,
                COLUMN_NAME_DATA,
                COLUMN_NAME_STATUS_AGENDAMENTO
        };

        String selection = COLUMN_NAME_USER + " = ?";
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

        List<Agendamento> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            Log.d("READ", "Foi lido uma linha do banco de dados");
            items.add(converteResultado(db, cursor));
        }
        cursor.close();
        return items;
    }

    private static Agendamento converteResultado(SQLiteDatabase db, Cursor cursor) {
        Integer id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
        TipoAgendamento tipo = TipoAgendamento.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_TIPO_AGENDAMENTO)));
        Date data;
        try {
            System.out.println("------------------------------" + cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_DATA)));
            data = stringToDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_DATA)));
        } catch (ParseException pex) {
            data = new Date();
        }
        StatusAgendamento status = StatusAgendamento.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_STATUS_AGENDAMENTO)));
        return new Agendamento(tipo, data, data.getHours()+":"+data.getMinutes(), status);
    }

    public static void saveAgendamento(SQLiteDatabase db, Agendamento ag){
        ContentValues values;
        values = new ContentValues();
        //values.put(_ID, 1);
        values.put(COLUMN_NAME_USER, 0);
        values.put(COLUMN_NAME_TIPO_AGENDAMENTO, ag.getTipoAgendamento().toString());
        values.put(COLUMN_NAME_DATA, ag.getFormatedData() + " " + ag.getHora() + ":00");
        values.put(COLUMN_NAME_STATUS_AGENDAMENTO, ag.getStatus().toString());
        db.insert(TABLE_NAME, null, values);
    }

}
