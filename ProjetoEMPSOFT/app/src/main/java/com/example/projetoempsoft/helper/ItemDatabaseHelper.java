package com.example.projetoempsoft.helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.projetoempsoft.models.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucasfnf on 29/03/17.
 */

public final class ItemDatabaseHelper implements BaseColumns {
    static final String TABLE_NAME = "item";
    static final String COLUMN_NAME_TITULO = "titulo";
    static final String COLUMN_NAME_PRECO = "preco";
    static final String COLUMN_NAME_DESCRICAO = "descricao";

    private ItemDatabaseHelper() { }

    static void createDatabase(SQLiteDatabase db) {
        String SQL_CREATE_ITEM =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        _ID + " INTEGER NOT NULL," +
                        COLUMN_NAME_TITULO + " VARCHAR2(100)," +
                        COLUMN_NAME_PRECO + " NUMBER(7,2)," +
                        COLUMN_NAME_DESCRICAO + " VARCHAR2(500)," +
                        "CONSTRAINT PK_Item PRIMARY KEY(" + _ID + "))";
        db.execSQL(SQL_CREATE_ITEM);

        ContentValues values;
        values = new ContentValues();
        values.put(_ID, 0);
        values.put(COLUMN_NAME_TITULO, "Whiskas 300g");
        values.put(COLUMN_NAME_PRECO, 15.00d);
        values.put(COLUMN_NAME_DESCRICAO, "Ração para gatos cheio de nutrientes que vão fazer o monstro sair da jaula");
        db.insert(TABLE_NAME, null, values);

        values.put(_ID, 1);
        values.put(COLUMN_NAME_TITULO, "Pedigree");
        values.put(COLUMN_NAME_PRECO, 15.00d);
        values.put(COLUMN_NAME_DESCRICAO, "Raçao para caes e gatos");
        db.insert(TABLE_NAME, null, values);

        values.put(_ID, 2);
        values.put(COLUMN_NAME_TITULO, "Whiskas 800g");
        values.put(COLUMN_NAME_PRECO, 20.00d);
        values.put(COLUMN_NAME_DESCRICAO, "Ração para gatos cheio de nutrientes que vão fazer o monstro sair da jaula");
        db.insert(TABLE_NAME, null, values);
    }

    public static Item getPorId(SQLiteDatabase db, Integer id) {
        String[] projection = {
                _ID,
                COLUMN_NAME_TITULO,
                COLUMN_NAME_PRECO,
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
        Item item = null;
        if (cursor.getCount() != 0) {
            cursor.moveToNext();
            item = converteResultado(db, cursor);
        }
        cursor.close();
        return item;
    }

    public static List<Item> getTodosItems(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME,null);
        Log.d("READ", "Foram lidas "+Integer.toString(cursor.getCount())+" linhas da tabela "+TABLE_NAME);

        List<Item> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            Log.d("READ", "Foi lido uma linha do banco de dados");
            items.add(converteResultado(db, cursor));
        }
        cursor.close();
        return items;
    }

    private static Item converteResultado(SQLiteDatabase db, Cursor cursor) {
        Integer id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
        String titulo  = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_TITULO));
        Double preco = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_NAME_PRECO));
        String descricao = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_DESCRICAO));
        return new Item(id, titulo, preco, descricao);
    }
}
