package br.edu.ite.financeiroandroid.activity;

import android.content.Context;
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
import br.edu.ite.financeiroandroid.util.ColorUtils;

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

        initialize();
    }

    private void initialize() {
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(ActivitiesUtil.LISTAR_LANCAMENTO);
                finish();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

        ArrayAdapter<TipoLancamento> arrayAdapter = new ArrayAdapter<TipoLancamento>(context, android.R.layout.simple_list_item_1, TipoLancamento.values() );
        tipo.setAdapter(arrayAdapter);
    }

    private void salvar() {
        if(isValid()){
            entidade.setDescricao(this.descricao.getText().toString());
            entidade.setTipo( (TipoLancamento) this.tipo.getSelectedItem() );
            entidade.setPessoa( (Pessoa) this.pessoa.getSelectedItem() );
            try {
                entidade.setValor(new BigDecimal(this.valor.getText().toString()));
            }catch (Exception ex){
                valor.setBackgroundColor( ColorUtils.VERMELHO );
                return;
            }
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                entidade.setDataVencimento( format.parse( dataVencimento.getText().toString() ) );
            }catch (Exception ex){
                dataVencimento.setBackgroundColor( ColorUtils.VERMELHO );
                return;
            }

            if(StringUtils.isNotBlank(dataPagamento.getText())) {
                try {
                    entidade.setDataPagamento(format.parse(dataPagamento.getText().toString()));
                } catch (Exception ex) {
                    dataPagamento.setBackgroundColor(ColorUtils.VERMELHO);
                    return;
                }
            }
        }
    }

    private boolean isValid() {
        boolean validacao = true;
        //Descricao
        if( !isValidField(descricao) ){
            return false;
        }
        if( !isValidField(valor) ){
            return false;
        }
        if( !isValidField(tipo) ){
            return false;
        }
        if( !isValidField(pessoa) ){
            return false;
        }
        if( !isValidField(dataVencimento) ){
            return false;
        }
        return validacao;
    }

}
