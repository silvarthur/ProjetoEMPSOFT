package com.example.projetoempsoft.helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.projetoempsoft.models.Pets;
import com.example.projetoempsoft.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucasfnf on 27/03/17.
 */

public final class UserDatabaseTable implements BaseColumns {
    static final String TABLE_NAME = "user";
    static final String COLUMN_NAME_NOME = "nome";
    static final String COLUMN_NAME_EMAIL = "email";
    static final String COLUMN_NAME_TELEFONE = "telefone";
    static final String COLUMN_NAME_RUA = "rua";
    static final String COLUMN_NAME_CIDADE = "cidade";
    static final String COLUMN_NAME_ESTADO = "estado";
    static final String COLUMN_NAME_CEP = "cep";
    static final String COLUMN_NAME_NUMERO = "numero";
    static final String COLUMN_NAME_COMPLEMENTO = "complemento";

    private UserDatabaseTable() {}

    static void createTable(SQLiteDatabase db) {
        String SQL_CREATE_USER =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER NOT NULL," +
                        COLUMN_NAME_NOME + " VARCHAR2(100) NOT NULL," +
                        COLUMN_NAME_TELEFONE + " CHAR(14) NOT NULL," +
                        COLUMN_NAME_RUA + " VARCHAR2(100) NOT NULL," +
                        COLUMN_NAME_CIDADE + " VARCHAR2(50) NOT NULL," +
                        COLUMN_NAME_ESTADO + " CHAR(2) NOT NULL," +
                        COLUMN_NAME_CEP + " CHAR(9) NOT NULL," +
                        COLUMN_NAME_NUMERO + " CHAR(16) NOT NULL," +
                        COLUMN_NAME_COMPLEMENTO + " VARCHAR2(200)," +
                        "CONSTRAINT PK_User PRIMARY KEY (" + _ID + "))";
        db.execSQL(SQL_CREATE_USER);

        // INSERINDO DADOS DUMMY
        ContentValues values;
        values = new ContentValues();
        values.put(_ID, 0);
        values.put(COLUMN_NAME_NOME, "User");
        values.put(COLUMN_NAME_TELEFONE, "(83)3333-3333");
        values.put(COLUMN_NAME_RUA, "Rua 1");
        values.put(COLUMN_NAME_CIDADE, "Campina Grande");
        values.put(COLUMN_NAME_ESTADO, "PB");
        values.put(COLUMN_NAME_CEP, "55555-555");
        values.put(COLUMN_NAME_NUMERO, "1");
        db.insert(TABLE_NAME, null, values);
    }

    public static User getPorId(SQLiteDatabase db, Integer id) {
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
        User user = null;
        if (cursor.getCount() != 0) {
            cursor.moveToNext();
            user = converteResultado(db, cursor);
        }
        cursor.close();
        return user;
    }

    public static List<User> getTodosUser(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME,null);
        Log.d("READ", "Foram lidas "+Integer.toString(cursor.getCount())+" linhas da tabela "+TABLE_NAME);

        List<User> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            Log.d("READ", "Foi lido uma linha do banco de dados");
            items.add(converteResultado(db, cursor));
        }
        cursor.close();
        return items;
    }

    private static User converteResultado(SQLiteDatabase db, Cursor cursor) {
        Integer id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
        String nome = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_NOME));
        String email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_EMAIL));
        String telefone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_TELEFONE));
        String rua = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_RUA));
        String cidade = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_CIDADE));
        String estado = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_ESTADO));
        String cep = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_CEP));
        String numero = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_NUMERO));
        String complemento = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_COMPLEMENTO));
        return new User(id, nome, email, telefone, rua, cidade, estado, complemento, numero, cep);
    }
}
