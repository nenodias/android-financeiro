package br.edu.ite.financeiroandroid.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.ite.financeiroandroid.model.Lancamento;
import br.edu.ite.financeiroandroid.util.ApplicationUtil;
import br.edu.ite.financeiroandroid.util.FactoryDAO;

public class LancamentoDAO extends DatabaseDAO implements ILancamentoDAO {

    public static final String TABELA = "lancamento";

    private IPessoaDAO daoPessoa;

    public LancamentoDAO(Context context){
        super(context);
        daoPessoa = FactoryDAO.createPessoaDao(context);
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
    public Lancamento findById(Integer pk) {
        Lancamento entidade = null;
        try{
            SQLiteDatabase db = getReadableDatabase();
            String sql = "SELECT * FROM "+ TABELA+ " WHERE id = ? ";
            Cursor cursor = db.rawQuery(sql, new String[]{ pk.toString() } );
            if(cursor.moveToFirst()) {
                entidade = createEntidadeByCursos(cursor, true);
            }
        }catch (Exception ex){

        }
        return entidade;
    }

    @Override
    public void save(Lancamento entidade) {
        if(entidade.getCodigo() == null){
            saveNew(entidade);
        }else{
            update(entidade);
        }
    }

    private void update(Lancamento entidade) {
        if(entidade != null && entidade.getCodigo() != null) {
            SQLiteDatabase db = getWritableDatabase();
            String sql = "UPDATE " + TABELA + " SET descricao = ?, id_tipo = ?, valor = ?, data_vencimento = ?, data_pagamento = ?, id_pessoa = ?   WHERE id = ? ";
            db.execSQL(sql, new String[]{
                    entidade.getDescricao(),
                    entidade.getIdTipo(),
                    entidade.getValor().toString(),
                    entidade.getDataVencimento() != null ? String.valueOf( entidade.getDataVencimento().getTime()) : null,
                    entidade.getDataPagamento() != null ? String.valueOf( entidade.getDataPagamento().getTime()) : null,
                    String.valueOf( entidade.getIdPessoa() ),
                    entidade.getCodigo().toString()
            });
        }
    }

    private void saveNew(Lancamento entidade){
        entidade.setCodigo( nextValue() );
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("id", entidade.getCodigo());
        valores.put("descricao", entidade.getDescricao());
        valores.put("id_tipo", entidade.getIdTipo());
        valores.put("valor", entidade.getValor().toString());
        valores.put("data_vencimento", entidade.getDataVencimento() != null ? entidade.getDataVencimento().getTime() : null );
        valores.put("data_pagamento", entidade.getDataPagamento() != null ? entidade.getDataPagamento().getTime() : null  );
        valores.put("id_pessoa", entidade.getIdPessoa());
        db.insert(TABELA, null, valores);
    }

    @Override
    public void delete(Integer pk) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABELA, "id = ?", new String[]{ pk.toString() } );
    }

    @Override
    public List<Lancamento> findAll() {
        List<Lancamento> retorno = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM "+ TABELA;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                Lancamento p = createEntidadeByCursos(cursor, true);
                retorno.add(p);
            }while(cursor.moveToNext());
        }
        return retorno;
    }

    private Lancamento createEntidadeByCursos(Cursor cursor){
        return createEntidadeByCursos(cursor, false);
    }

    private Lancamento createEntidadeByCursos(Cursor cursor, boolean eager) {
        Lancamento p = new Lancamento();
        p.setCodigo( cursor.getInt(0) );
        p.setDescricao(cursor.getString(1));
        p.setIdTipo(cursor.getString(2));
        if( !cursor.isNull(3) ) {
            p.setValor(new BigDecimal(cursor.getString(3)));
        }
        if( !cursor.isNull(4) ) {
            Date dataVencimento = new Date();
            dataVencimento.setTime(cursor.getLong(4));
            p.setDataVencimento(dataVencimento);
        }
        if( !cursor.isNull(5) ) {
            Date dataPagamento = new Date();
            dataPagamento.setTime(cursor.getLong(5));
            p.setDataPagamento(dataPagamento);
        }
        if(eager && daoPessoa != null){
            Integer fkPessoa = cursor.getInt(6);
            p.setPessoa( daoPessoa.findById( fkPessoa ) );
            p.setIdPessoa( fkPessoa );
        }else{
            p.setIdPessoa( cursor.getInt(6) );
        }
        return p;
    }

}
