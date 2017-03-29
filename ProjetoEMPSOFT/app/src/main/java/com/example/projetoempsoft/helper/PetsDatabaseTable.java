package com.example.projetoempsoft.helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.projetoempsoft.models.Pets;
import com.example.projetoempsoft.models.Vacina;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucasfnf on 27/03/17.
 */

public final class PetsDatabaseTable implements BaseColumns {
    static final String TABLE_NAME = "pets";
    static final String COLUMN_NAME_NOME = "nome";
    static final String COLUMN_NAME_DONO = "dono";

    private PetsDatabaseTable() {}

    static void createTable(SQLiteDatabase db) {
        String SQL_CREATE_PETS =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER NOT NULL," +
                        COLUMN_NAME_NOME + " VARCHAR2(100) NOT NULL," +
                        COLUMN_NAME_DONO + " INTEGER NOT NULL," +
                        "CONSTRAINT PK_Pet PRIMARY KEY (" + _ID + ")," +
                        "CONSTRAINT FK_Pet_User FOREIGN KEY (" + COLUMN_NAME_DONO + ") REFERENCES " + UserDatabaseTable.TABLE_NAME + "(" + UserDatabaseTable._ID +"))";
        db.execSQL(SQL_CREATE_PETS);

        // INSERINDO DADOS DUMMY
        ContentValues values;
        values = new ContentValues();
        values.put(_ID, 0);
        values.put(COLUMN_NAME_NOME, "Bola");
        values.put(COLUMN_NAME_DONO, 0);
        db.insert(TABLE_NAME, null, values);
    }

    public static Pets getPorId(SQLiteDatabase db, Integer id) {
        String[] projection = {
                _ID,
                COLUMN_NAME_NOME
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
        Pets pet = null;
        if (cursor.getCount() != 0) {
            cursor.moveToNext();
            pet = converteResultado(db, cursor);
        }
        cursor.close();
        return pet;
    }

    public static List<Pets> getTodosPets(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME,null);
        Log.d("READ", "Foram lidas "+Integer.toString(cursor.getCount())+" linhas da tabela "+TABLE_NAME);

        List<Pets> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            Log.d("READ", "Foi lido uma linha do banco de dados");
            items.add(converteResultado(db, cursor));
        }
        cursor.close();
        return items;
    }

    public static List<Pets> getPetsDono(SQLiteDatabase db, Integer id) {
        String[] projection = {
                _ID,
                COLUMN_NAME_NOME
        };

        String selection = COLUMN_NAME_DONO + " = ?";
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

        List<Pets> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            Log.d("READ", "Foi lido uma linha do banco de dados");
            items.add(converteResultado(db, cursor));
        }
        cursor.close();
        return items;
    }

    private static Pets converteResultado(SQLiteDatabase db, Cursor cursor) {
        Integer id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
        String nome = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_NOME));
        return new Pets(id, nome, null);
    }
}
