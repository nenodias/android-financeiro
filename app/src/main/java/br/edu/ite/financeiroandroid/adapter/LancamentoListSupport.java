package br.edu.ite.financeiroandroid.adapter;

import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import br.edu.ite.financeiroandroid.R;
import br.edu.ite.financeiroandroid.adapter.dto.ItemAdapterDTO;

public class LancamentoListSupport {

    TextView codigo;

    TextView descricao;

    TextView valor;

    TextView tipo;

    TextView pessoa;

    TextView dataVencimento;

    TextView dataPagamento;

    public LancamentoListSupport(View v) {
        this.codigo = (TextView) v.findViewById(R.id.lan_txt_col_codigo);
        this.descricao = (TextView) v.findViewById(R.id.lan_txt_col_descricao);
        this.tipo = (TextView) v.findViewById(R.id.lan_txt_col_tipo);
        this.valor = (TextView) v.findViewById(R.id.lan_txt_col_valor);
        this.pessoa = (TextView) v.findViewById(R.id.lan_txt_col_pessoa);
        this.dataVencimento = (TextView) v.findViewById(R.id.lan_txt_col_data_vencimento);
        this.dataPagamento = (TextView) v.findViewById(R.id.lan_txt_col_data_pagamento);
    }

    public void setValues(ItemAdapterDTO values) {
        this.codigo.setText( values.getValores().get("codigo") );
        this.descricao.setText( values.getValores().get("descricao") );
        this.valor.setText( values.getValores().get("valor") );
        this.tipo.setText( values.getValores().get("tipo") );
        this.pessoa.setText( values.getValores().get("pessoa") );
        this.dataVencimento.setText( values.getValores().get("dataVencimento") );
        this.dataPagamento.setText( values.getValores().get("dataPagamento") );
    }
}
