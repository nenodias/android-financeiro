package br.edu.ite.financeiroandroid.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import br.edu.ite.financeiroandroid.R;
import br.edu.ite.financeiroandroid.model.Lancamento;
import br.edu.ite.financeiroandroid.model.Pessoa;
import br.edu.ite.financeiroandroid.model.TipoLancamento;
import br.edu.ite.financeiroandroid.util.ActivitiesUtil;
import br.edu.ite.financeiroandroid.util.DadosUtil;

public class CadastroLancamentoActivity extends BaseActivity {

    private Context context = CadastroLancamentoActivity.this;
    private Integer origem;

    private Lancamento entidade = new Lancamento();

    private EditText codigo;
    private EditText descricao;
    private EditText valor;
    private Spinner tipo;
    private Spinner pessoa;
    private EditText dataVencimento;
    private EditText dataPagamento;

    private Button btnListar;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_lancamento);
        setTitle("Cadastrar Lançamentos");

        btnListar = (Button) findViewById( R.id.lan_btn_listar );
        btnSalvar = (Button) findViewById( R.id.lan_btn_salvar );

        codigo = (EditText) findViewById( R.id.lan_txt_codigo );
        descricao = (EditText) findViewById( R.id.lan_txt_descricao );
        valor = (EditText) findViewById( R.id.lan_txt_valor );
        tipo = (Spinner) findViewById( R.id.lan_txt_tipo );
        pessoa = (Spinner) findViewById( R.id.lan_txt_tipo );
        dataVencimento = (EditText) findViewById( R.id.lan_txt_data_vencimento );
        dataPagamento = (EditText) findViewById( R.id.lan_txt_data_pagamento );

        initialize(savedInstanceState);
    }

    private void initialize(Bundle savedInstanceState) {
        btnListar.setOnClickListener(listarClick);
        btnSalvar.setOnClickListener(salvarClick);

        ArrayAdapter<TipoLancamento> tipoAdapter = new ArrayAdapter<TipoLancamento>(context, android.R.layout.simple_list_item_1, TipoLancamento.values() );
        tipo.setAdapter(tipoAdapter);

        ArrayAdapter<Pessoa> pessoaAdapter = new ArrayAdapter<Pessoa>(context, android.R.layout.simple_list_item_1, DadosUtil.pessoaList );
        pessoa.setAdapter(pessoaAdapter);

        if( isModel(getIntent()) ){
            Lancamento lancamento = (Lancamento)getIntent().getExtras().getSerializable("model");
            this.codigo.setText(lancamento.getCodigo());
            this.descricao.setText(lancamento.getDescricao());
            this.valor.setText( String.valueOf( lancamento.getValor() ) );
            for (int i = 0 ; i < this.tipo.getAdapter().getCount() ; i++ ){
                TipoLancamento tipoItem = (TipoLancamento)this.tipo.getAdapter().getItem(i);
                if(tipoItem.equals(lancamento.getTipo())){
                    tipo.setSelection(i);
                    break;
                }
            }
            for (int i = 0 ; i < this.pessoa.getAdapter().getCount() ; i++ ){
                Pessoa pessoaItem = (Pessoa) this.pessoa.getAdapter().getItem(i);
                if( pessoaItem.getCodigo().equals(lancamento.getCodigo()) ){
                    pessoa.setSelection(i);
                    break;
                }
            }
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            this.dataVencimento.setText( format.format(lancamento.getDataVencimento() ) );
            if( lancamento .getDataPagamento() != null) {
                this.dataVencimento.setText(format.format(lancamento.getDataPagamento()));
            }
        }
    }

    protected void salvar() {
        if(isValid()){
            Integer codigo = getInteger(this.codigo);
            entidade.setCodigo(codigo);
            entidade.setDescricao(this.descricao.getText().toString());
            entidade.setTipo( (TipoLancamento) this.tipo.getSelectedItem() );
            entidade.setPessoa( (Pessoa) this.pessoa.getSelectedItem() );
            try {
                entidade.setValor(new BigDecimal(this.valor.getText().toString()));
            }catch (Exception ex){
                valor.setError("Valor inválido");
                return;
            }
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                entidade.setDataVencimento( format.parse( dataVencimento.getText().toString() ) );
            }catch (Exception ex){
                dataVencimento.setError( "Data Vencimento inválida" );
                return;
            }

            if(StringUtils.isNotBlank(dataPagamento.getText())) {
                try {
                    entidade.setDataPagamento(format.parse(dataPagamento.getText().toString()));
                } catch (Exception ex) {
                    dataPagamento.setError( "Data Pagamento inválida" );
                    return;
                }
            }
        }
        //Persiste
        DadosUtil.lancamentoList.add(entidade);
        messageSave();
    }

    private boolean isValid() {
        boolean validacao = true;
        boolean descricaoValid = isValidField(descricao);
        boolean valorValid = isValidField(valor);
        boolean tipoValid = isValidField(tipo);
        boolean pessoaValid = isValidField(pessoa);
        boolean dataVencimentoValid = isValidField(dataVencimento);
        validacao = descricaoValid && valorValid && tipoValid && pessoaValid && dataVencimentoValid;
        return validacao;
    }

    @Override
    protected void listar() {
        setResult(ActivitiesUtil.LISTAR_LANCAMENTO);
        finish();
    }

    @Override
    public Context getAppContext() {
        return context;
    }

}
