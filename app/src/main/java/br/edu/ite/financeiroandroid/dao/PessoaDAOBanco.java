package br.edu.ite.financeiroandroid.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.edu.ite.financeiroandroid.model.Pessoa;
import br.edu.ite.financeiroandroid.util.ApplicationUtil;

public class PessoaDAOBanco extends SQLiteOpenHelper implements GenericDao<Pessoa> {

    private static final String TABELA = "pessoa";

    public PessoaDAOBanco(Context context){
        super(context, ApplicationUtil.APP_NAME, null, ApplicationUtil.VERSION);
    }

    @Override
    public Pessoa findById(Integer pk) {
        return null;
    }

    @Override
    public void save(Pessoa entidade) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("codigo", entidade.getCodigo());
        valores.put("nome", entidade.getNome());
        db.insert(TABELA, null, valores);
    }

    @Override
    public void delete(Integer pk) {

    }

    @Override
    public List<Pessoa> findAll() {
        List<Pessoa> retorno = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM "+TABELA;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                Pessoa p = new Pessoa();
                p.setCodigo( cursor.getInt(0) );
                p.setNome(cursor.getString(1));
                retorno.add(p);
            }while(cursor.moveToNext());
        }
        return retorno;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
