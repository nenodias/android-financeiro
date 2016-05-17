package br.edu.ite.financeiroandroid.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ite.financeiroandroid.model.Pessoa;

public class PessoaDAO extends DatabaseDAO implements IPessoaDAO {

    public static final String TABELA = "pessoa";

    public PessoaDAO(Context context){
        super(context);
    }

    @Override
    public Integer nextValue() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT MAX(id) FROM "+ TABELA;
        Cursor cursor = db.rawQuery(sql, null);
        Integer max = 0;
        if(cursor.moveToFirst()){
            max = cursor.getInt(0);
        }
        max++;
        return max;
    }

    @Override
    public Pessoa findById(Integer pk) {
        Pessoa entidade = null;
        try{
            SQLiteDatabase db = getReadableDatabase();
            String sql = "SELECT * FROM "+ TABELA+ " WHERE id = ? ";
            Cursor cursor = db.rawQuery(sql, new String[]{ pk.toString() } );
            if(cursor.moveToFirst()) {
                entidade = createPessoaByCursos(cursor);
            }
        }catch (Exception ex){

        }
        return entidade;
    }

    @Override
    public void save(Pessoa entidade) {
        if(entidade.getCodigo() == null){
            saveNew(entidade);
        }else{
            update(entidade);
        }
    }

    private void update(Pessoa entidade) {
        if(entidade != null && entidade.getCodigo() != null) {
            SQLiteDatabase db = getWritableDatabase();
            String sql = "UPDATE " + TABELA + " SET nome = ? WHERE id = ? ";
            db.execSQL(sql, new String[]{entidade.getNome(), entidade.getCodigo().toString()});
        }
    }

    private void saveNew(Pessoa entidade){
        entidade.setCodigo( nextValue() );
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("id", entidade.getCodigo());
        valores.put("nome", entidade.getNome());
        db.insert(TABELA, null, valores);
    }

    @Override
    public void delete(Integer pk) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABELA, "id = ?", new String[]{ pk.toString() } );
    }

    @Override
    public List<Pessoa> findAll() {
        List<Pessoa> retorno = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM "+ TABELA;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                Pessoa p = createPessoaByCursos(cursor);
                retorno.add(p);
            }while(cursor.moveToNext());
        }
        return retorno;
    }

    private Pessoa createPessoaByCursos(Cursor cursor) {
        Pessoa p = new Pessoa();
        p.setCodigo( cursor.getInt(0) );
        p.setNome(cursor.getString(1));
        return p;
    }

}
