package br.edu.ite.financeiroandroid.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static br.edu.ite.financeiroandroid.util.ApplicationUtil.*;

/**
 * Created by SI on 16/05/2016.
 */
public class DatabaseDAO extends SQLiteOpenHelper {

    public DatabaseDAO(Context context){
        super(context, APP_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ PessoaDAO.TABELA +" (id INTEGER PRIMARY KEY, nome VARCHAR(255) );");
        db.execSQL("CREATE TABLE "+ LancamentoDAO.TABELA +
                        " ("+
                        "id INTEGER PRIMARY KEY,"+
                        " descricao VARCHAR(255), "+
                        " id_tipo VARCHAR(2), "+
                        " valor VARCHAR(255), "+
                        " data_vencimento LONG, "+
                        " data_pagamento LONG, "+
                        " id_pessoa INTEGER "+
                        ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
