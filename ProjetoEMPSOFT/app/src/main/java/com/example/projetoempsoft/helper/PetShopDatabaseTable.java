package com.example.projetoempsoft.helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.projetoempsoft.models.PetShop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucasfnf on 27/03/17.
 */

public final class PetShopDatabaseTable implements BaseColumns {
    static final String TABLE_NAME = "pet_shop";
    static final String COLUMN_NAME_NOME = "nome";
    static final String COLUMN_NAME_TELEFONE = "telefone";
    static final String COLUMN_NAME_RUA = "rua";
    static final String COLUMN_NAME_CIDADE = "cidade";
    static final String COLUMN_NAME_ESTADO = "estado";
    static final String COLUMN_NAME_COMPLEMENTO = "complemento";
    static final String COLUMN_NAME_NUMERO = "numero";
    static final String COLUMN_NAME_CEP = "cep";

    private PetShopDatabaseTable() { }

    static void createTable(SQLiteDatabase db) {
        String SQL_CREATE_PET_SHOP =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        _ID + " INTEGER NOT NULL," +
                        COLUMN_NAME_NOME + " VARCHAR2(100) NOT NULL," +
                        COLUMN_NAME_TELEFONE + " CHAR(14) NOT NULL," +
                        COLUMN_NAME_RUA + " VARCHAR2(100) NOT NULL," +
                        COLUMN_NAME_CIDADE + " VARCHAR2(50) NOT NULL," +
                        COLUMN_NAME_ESTADO + " CHAR(2) NOT NULL," +
                        COLUMN_NAME_COMPLEMENTO + " VARCHAR2(200)," +
                        COLUMN_NAME_NUMERO + " CHAR(16) NOT NULL," +
                        COLUMN_NAME_CEP + " CHAR(9) NOT NULL," +
                        "CONSTRAINT PK_Pet_Shop PRIMARY KEY (" + _ID + "))";
        db.execSQL(SQL_CREATE_PET_SHOP);

        // INSERINDO DADOS DUMMY
        String[] nomes = {"Petshop 1","Petshop 2","Petshop 3","Petshop 4"};
        for (int i = 0; i < 1; i++) {
            ContentValues values = new ContentValues();
            values.put(_ID, i);
            values.put(COLUMN_NAME_NOME, nomes[i]);
            values.put(COLUMN_NAME_TELEFONE, "(83)3333-3333");
            values.put(COLUMN_NAME_RUA, "Floriano Peixoto, 321");
            values.put(COLUMN_NAME_CIDADE, "Campina Grande");
            values.put(COLUMN_NAME_ESTADO, "PB");
            values.put(COLUMN_NAME_CEP, "55555-555");
            values.put(COLUMN_NAME_NUMERO, "1");

            db.insert(TABLE_NAME, null, values);
        }
    }

    public static PetShop getPorID(SQLiteDatabase db, Integer id) {
        String[] projection = {
                _ID,
                COLUMN_NAME_NOME,
                COLUMN_NAME_TELEFONE,
                COLUMN_NAME_RUA,
                COLUMN_NAME_CIDADE,
                COLUMN_NAME_ESTADO,
                COLUMN_NAME_COMPLEMENTO,
                COLUMN_NAME_NUMERO,
                COLUMN_NAME_CEP
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
        PetShop petShop = null;
        if (cursor.getCount() != 0) {
            cursor.moveToNext();
            petShop = converteResultado(db, cursor);
        }
        cursor.close();
        return petShop;
    }

    public static List<PetShop> getTodosPetShops(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME,null);
        Log.d("READ", "Foram lidas "+Integer.toString(cursor.getCount())+" linhas da tabela "+TABLE_NAME);

        List<PetShop> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            Log.d("READ", "Foi lido uma linha do banco de dados");
            items.add(converteResultado(db, cursor));
        }
        cursor.close();
        return items;
    }

    private static PetShop converteResultado(SQLiteDatabase db, Cursor cursor) {
        Integer id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
        String nome = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_NOME));
        String telefone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_TELEFONE));
        String rua = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_RUA));
        String cidade = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_CIDADE));
        String estado = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_ESTADO));
        String complemento = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_COMPLEMENTO));
        String numero = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_NUMERO));
        String cep = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_CEP));
        return new PetShop(id, nome, rua, cidade, estado, complemento, numero, cep, telefone);
    }

}
