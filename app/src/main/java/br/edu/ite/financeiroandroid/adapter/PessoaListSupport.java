package br.edu.ite.financeiroandroid.adapter;

import android.view.View;
import android.widget.TextView;

import br.edu.ite.financeiroandroid.R;
import br.edu.ite.financeiroandroid.adapter.dto.ItemAdapterDTO;

public class PessoaListSupport {

    TextView codigo;

    TextView nome;

    public PessoaListSupport(View v) {
        this.codigo = (TextView) v.findViewById(R.id.cad_txt_col_codigo);
        this.nome = (TextView) v.findViewById(R.id.cad_txt_col_nome);
    }

    public void setValues(ItemAdapterDTO values) {
        this.codigo.setText( values.getValores().get("codigo") );
        this.nome.setText( values.getValores().get("nome") );
    }
}

