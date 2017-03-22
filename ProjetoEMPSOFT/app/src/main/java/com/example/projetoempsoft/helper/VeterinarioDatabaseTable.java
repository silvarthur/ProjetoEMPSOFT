package com.example.projetoempsoft.helper;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.database.Cursor;

import com.example.projetoempsoft.models.Endereco;
import com.example.projetoempsoft.models.Veterinario;

/**
 * Created by lucasfnf on 21/03/17.
 */

public final class VeterinarioDatabaseTable implements BaseColumns {
    public static final String TABLE_NAME = "veterinario";
    public static final String COLUMN_NAME_NOME = "nome";
    public static final String COLUMN_NAME_ENDERECO = "endereco";

    private VeterinarioDatabaseTable() { }

    public static void createTable(SQLiteDatabase db) {
        String SQL_CREATE_ENDERECO =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        _ID + " INTEGER NOT NULL," +
                        COLUMN_NAME_NOME + " VARCHAR2(100) NOT NULL," +
                        COLUMN_NAME_ENDERECO + " INTEGER NOT NULL," +
                        "CONSTRAINT PK_Endereco PRIMARY KEY (" + _ID + ")" +
                        "CONSTRAINT FK_Veterinario_Endereco FOREIGN KEY ("+COLUMN_NAME_ENDERECO+") REFERENCES "+EnderecoDatabaseTable.TABLE_NAME+"("+EnderecoDatabaseTable._ID+"))";
        ;
        db.execSQL(SQL_CREATE_ENDERECO);

        // INSERINDO DADOS DUMMY
        String[] nomes = {"Dr. Goku","Dr. Urameshi", "Dr. Monkey D. Ruffy", "Dr. Gohan"};
        for (int i = 0; i < 4; i++) {
            ContentValues values = new ContentValues();
            values.put(_ID, i);
            values.put(COLUMN_NAME_NOME, nomes[i]);
            values.put(COLUMN_NAME_ENDERECO, 0);

            db.insert(TABLE_NAME, null, values);
        }
    }

    public static Veterinario getPorID(SQLiteDatabase db, Integer id) {
        String[] projection = {
                _ID,
                COLUMN_NAME_NOME,
                COLUMN_NAME_ENDERECO
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

    private static Veterinario converteResultado(SQLiteDatabase db, Cursor cursor) {
        Integer id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
        String nome = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_NOME));
        Endereco endereco = EnderecoDatabaseTable.getPorID(db,
                cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_ENDERECO)));
        return new Veterinario(id, nome, endereco);
    }
}
