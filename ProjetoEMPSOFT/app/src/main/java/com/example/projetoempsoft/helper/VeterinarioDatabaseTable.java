package com.example.projetoempsoft.helper;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.database.Cursor;
import android.util.Log;

import com.example.projetoempsoft.models.Veterinario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucasfnf on 21/03/17.
 */

public final class VeterinarioDatabaseTable implements BaseColumns {
    static final String TABLE_NAME = "veterinario";
    static final String COLUMN_NAME_NOME = "nome";
    static final String COLUMN_NAME_TELEFONE = "telefone";
    static final String COLUMN_NAME_RUA = "rua";
    static final String COLUMN_NAME_CIDADE = "cidade";
    static final String COLUMN_NAME_ESTADO = "estado";
    static final String COLUMN_NAME_CEP = "cep";
    static final String COLUMN_NAME_NUMERO = "numero";
    static final String COLUMN_NAME_COMPLEMENTO = "complemento";

    private VeterinarioDatabaseTable() { }

    static void createTable(SQLiteDatabase db) {
        String SQL_CREATE_VETERINARIO =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        _ID + " INTEGER NOT NULL," +
                        COLUMN_NAME_NOME + " VARCHAR2(100) NOT NULL," +
                        COLUMN_NAME_TELEFONE + " CHAR(14) NOT NULL," +
                        COLUMN_NAME_RUA + " VARCHAR2(100) NOT NULL," +
                        COLUMN_NAME_CIDADE + " VARCHAR2(50) NOT NULL," +
                        COLUMN_NAME_ESTADO + " CHAR(2) NOT NULL," +
                        COLUMN_NAME_CEP + " CHAR(9) NOT NULL," +
                        COLUMN_NAME_NUMERO + " CHAR(16) NOT NULL," +
                        COLUMN_NAME_COMPLEMENTO + " VARCHAR2(200)," +
                        "CONSTRAINT PK_Veterinario PRIMARY KEY (" + _ID + "))";
        db.execSQL(SQL_CREATE_VETERINARIO);

        // INSERINDO DADOS DUMMY
        String[] nomes = {"Dr. Monkey D. Ruffy","Dr. Urameshi", "Dr. Monkey D. Ruffy", "Dr. Gohan"};
        for (int i = 0; i < 1; i++) {
            ContentValues values = new ContentValues();
            values.put(_ID, i);
            values.put(COLUMN_NAME_NOME, nomes[i]);
            values.put(COLUMN_NAME_TELEFONE, "(83)3333-3333");
            values.put(COLUMN_NAME_RUA, "Aprigio Nepomuceno 123");
            values.put(COLUMN_NAME_CIDADE, "Campina Grande");
            values.put(COLUMN_NAME_ESTADO, "PB");
            values.put(COLUMN_NAME_CEP, "55555-555");
            values.put(COLUMN_NAME_NUMERO, "1");

            db.insert(TABLE_NAME, null, values);
        }
    }

    public static Veterinario getPorID(SQLiteDatabase db, Integer id) {
        String[] projection = {
                _ID,
                COLUMN_NAME_NOME,
                COLUMN_NAME_TELEFONE,
                COLUMN_NAME_RUA,
                COLUMN_NAME_CIDADE,
                COLUMN_NAME_ESTADO,
                COLUMN_NAME_CEP,
                COLUMN_NAME_NUMERO,
                COLUMN_NAME_COMPLEMENTO
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
        Veterinario veterinario = null;
        if (cursor.getCount() != 0) {
            cursor.moveToNext();
            veterinario = converteResultado(db, cursor);
        }
        cursor.close();
        return veterinario;
    }

    public static List<Veterinario> getTodosVeterinario(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME,null);
        Log.d("READ", "Foram lidas "+Integer.toString(cursor.getCount())+" linhas da tabela "+TABLE_NAME);

        List<Veterinario> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            Log.d("READ", "Foi lido uma linha do banco de dados");
            items.add(converteResultado(db, cursor));
        }
        cursor.close();
        return items;
    }

    private static Veterinario converteResultado(SQLiteDatabase db, Cursor cursor) {
        Integer id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
        String nome = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_NOME));
        String telefone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_TELEFONE));
        String rua = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_RUA));
        String cidade = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_CIDADE));
        String estado = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_ESTADO));
        String cep = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_CEP));
        String numero = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_NUMERO));
        String complemento = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_COMPLEMENTO));
        return new Veterinario(id, nome, telefone, rua, cidade, estado, complemento, numero, cep);
    }
}
