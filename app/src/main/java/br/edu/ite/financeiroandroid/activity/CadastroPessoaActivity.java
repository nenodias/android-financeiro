package br.edu.ite.financeiroandroid.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.edu.ite.financeiroandroid.R;
import br.edu.ite.financeiroandroid.util.ActivitiesUtil;

public class CadastroPessoaActivity extends BaseActivity {

    private Context context = CadastroPessoaActivity.this;
    private Integer origem;

    private EditText codigo;
    private EditText nome;

    private Button btnListar;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);
        setTitle("Cadastrar Pessoas");

        btnListar = (Button) findViewById( R.id.cad_btn_listar );
        btnSalvar = (Button) findViewById( R.id.cad_btn_salvar );

        codigo = (EditText) findViewById( R.id.cad_txt_codigo );
        nome = (EditText) findViewById( R.id.cad_txt_nome );

        initialize();
    }

    private void initialize() {
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(ActivitiesUtil.LISTAR_PESSOA);
                finish();
            }
        });
    }

}
