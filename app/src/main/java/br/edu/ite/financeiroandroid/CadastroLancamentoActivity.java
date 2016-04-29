package br.edu.ite.financeiroandroid;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import br.edu.ite.financeiroandroid.model.TipoLancamento;

public class CadastroLancamentoActivity extends BaseActivity {

    private Context context = CadastroLancamentoActivity.this;
    private Integer origem;

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

        ArrayAdapter<TipoLancamento> arrayAdapter = new ArrayAdapter<TipoLancamento>(context, android.R.layout.simple_list_item_1, TipoLancamento.values() );
        tipo.setAdapter(arrayAdapter);
    }

}
