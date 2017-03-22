package com.example.projetoempsoft.helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.provider.ContactsContract;

import com.example.projetoempsoft.models.Endereco;

/**
 * Created by lucasfnf on 21/03/17.
 */

public final class EnderecoDatabaseTable implements BaseColumns {

    public static final String TABLE_NAME = "endereco";
    public static final String COLUMN_NAME_RUA = "rua";
    public static final String COLUMN_NAME_CIDADE = "cidade";
    public static final String COLUMN_NAME_ESTADO = "estado";
    public static final String COLUMN_NAME_CEP = "cep";
    public static final String COLUMN_NAME_NUMERO = "numero";
    public static final String COLUMN_NAME_COMPLEMENTO = "complemento";

    private EnderecoDatabaseTable() { }

    public static void createTable(SQLiteDatabase db) {
        String SQL_CREATE_ENDERECO =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        _ID + " INTEGER NOT NULL," +
                        COLUMN_NAME_RUA + " VARCHAR2(100) NOT NULL," +
                        COLUMN_NAME_CIDADE + " VARCHAR2(50) NOT NULL," +
                        COLUMN_NAME_ESTADO + " CHAR(2) NOT NULL," +
                        COLUMN_NAME_CEP + " CHAR(9) NOT NULL," +
                        COLUMN_NAME_NUMERO + " CHAR(8) NOT NULL," +
                        COLUMN_NAME_COMPLEMENTO + " VARCHAR2(200)," +
                        "CONSTRAINT PK_Endereco PRIMARY KEY (" + _ID + "))";
                ;
        db.execSQL(SQL_CREATE_ENDERECO);

        // INSERINDO DADOS DUMMY
        ContentValues values = new ContentValues();
        values.put(_ID, 0);
        values.put(COLUMN_NAME_RUA, "Rua 1");
        values.put(COLUMN_NAME_CIDADE, "Campina Grande");
        values.put(COLUMN_NAME_ESTADO, "PB");
        values.put(COLUMN_NAME_CEP, "55555-555");
        values.put(COLUMN_NAME_NUMERO, "1");

        db.insert(TABLE_NAME, null, values);
    }

    public static Endereco getPorID(SQLiteDatabase db, Integer id) {
        String[] projection = {
                _ID,
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
        Endereco endereco = null;
        if (cursor.getCount() != 0) {
            cursor.moveToNext();
            endereco = converteResultado(db, cursor);
        }
        cursor.close();
        return endereco;
    }

    private static Endereco converteResultado(SQLiteDatabase db, Cursor cursor) {
        Integer id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
        String rua = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_RUA));
        String cidade = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_CIDADE));
        String estado = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_ESTADO));
        String cep = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_CEP));
        String numero = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_NUMERO));
        String complemento = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_COMPLEMENTO));
        return new Endereco(id, rua, cidade, estado, complemento, numero, cep);
    }
}
