package com.example.projetoempsoft.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.projetoempsoft.models.TipoVacina;
import com.example.projetoempsoft.models.Vacina;

/**
 * Created by lucasfnf on 20/03/17.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Database.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        createDatabase(db);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO
    }

    private void createDatabase(SQLiteDatabase db) {
        UserDatabaseTable.createTable(db);
        VeterinarioDatabaseTable.createTable(db);
        PetShopDatabaseTable.createTable(db);
        ItemDatabaseHelper.createDatabase(db);
        PetsDatabaseTable.createTable(db);
        TipoVacinaDatabaseTable.createTable(db);
        VacinaDatabaseTable.createTable(db);
        AgendamentoDatabaseTable.createTable(db);
    }
}