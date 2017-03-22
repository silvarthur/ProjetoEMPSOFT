package com.example.projetoempsoft.helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.projetoempsoft.models.TipoVacina;

/**
 * Created by lucasfnf on 21/03/17.
 */

public final class TipoVacinaDatabaseTable implements BaseColumns {
    public static final String TABLE_NAME = "tipo_vacina";
    public static final String COLUMN_NAME_NOME = "nome";
    public static final String COLUMN_NAME_DESCRICAO = "descricao";

    private TipoVacinaDatabaseTable() { }

    public static void createTable(SQLiteDatabase db) {
        String SQL_CREATE_TIPO_VACINA =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        _ID + " INTEGER NOT NULL," +
                        COLUMN_NAME_NOME + " VARCHAR2(50) NOT NULL," +
                        COLUMN_NAME_DESCRICAO + " VARCHAR2(400)," +
                        "CONSTRAINT PK_Vacina PRIMARY KEY (" + _ID + "))";
        db.execSQL(SQL_CREATE_TIPO_VACINA);

        // INSERINDO DADOS DUMMY
        String[] nomes = {"Doença 1","Doença 2","Doença 3","Doença 4"};
        for (int i = 0; i < 4; i++) {
            ContentValues values = new ContentValues();
            values.put(_ID, i);
            values.put(COLUMN_NAME_NOME, nomes[i]);

            db.insert(TABLE_NAME, null, values);
        }
    }

    public static TipoVacina getPorID(SQLiteDatabase db, Integer id) {
        String[] projection = {
                _ID,
                COLUMN_NAME_NOME,
                COLUMN_NAME_DESCRICAO
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
        TipoVacina tipoVacina = null;
        if (cursor.getCount() != 0) {
            cursor.moveToNext();
            tipoVacina = converteResultado(db, cursor);
        }
        cursor.close();
        return tipoVacina;
    }

    private static TipoVacina converteResultado(SQLiteDatabase db, Cursor cursor) {
        Integer id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
        String nome = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_NOME));
        String descricao = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_DESCRICAO));
        return new TipoVacina(id, nome, descricao);
    }
}
