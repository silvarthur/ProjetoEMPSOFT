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
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Database.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        createDatabase(db);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over


        String SQL_DELETE_VACINA =
                "DROP TABLE IF EXISTS " + VacinaDatabaseTable.TABLE_NAME;
        db.execSQL(SQL_DELETE_VACINA);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private void createDatabase(SQLiteDatabase db) {
        EnderecoDatabaseTable.createTable(db);
        VeterinarioDatabaseTable.createTable(db);
        TipoVacinaDatabaseTable.createTable(db);
        VacinaDatabaseTable.createTable(db);
    }
}