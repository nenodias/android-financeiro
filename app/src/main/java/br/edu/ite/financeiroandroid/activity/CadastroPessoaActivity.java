package br.edu.ite.financeiroandroid.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import br.edu.ite.financeiroandroid.R;
import br.edu.ite.financeiroandroid.model.Pessoa;
import br.edu.ite.financeiroandroid.model.TipoLancamento;
import br.edu.ite.financeiroandroid.util.ActivitiesUtil;
import br.edu.ite.financeiroandroid.util.DadosUtil;

public class CadastroPessoaActivity extends BaseActivity {

    private Context context = CadastroPessoaActivity.this;
    private Integer origem;
    private Pessoa entidade = new Pessoa();

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

        initialize(savedInstanceState);
    }

    private void initialize(Bundle savedInstanceState) {
        btnListar.setOnClickListener(listarClick);
        btnSalvar.setOnClickListener(salvarClick);
        if( isModel(getIntent()) ){
            Pessoa pessoa = (Pessoa)getIntent().getExtras().getSerializable("model");
            this.codigo.setText( pessoa.getCodigo().toString() );
            this.nome.setText( pessoa.getNome() );
        }
    }

    protected void salvar() {
        if(isValid()){
            Integer codigo = getInteger(this.codigo);
            entidade.setCodigo(codigo);
            entidade.setCodigo(codigo);
            entidade.setNome(this.nome.getText().toString());
        }
        //Persiste
        DadosUtil.pessoaList.add(entidade);
        messageSave();
        listar();
    }

    @Override
    protected void listar() {
        setResult(ActivitiesUtil.LISTAR_PESSOA);
        finish();
    }

    private boolean isValid() {
        boolean validacao = true;
        if( !isValidField(nome) ){
            return false;
        }
        return validacao;
    }

    @Override
    public Context getAppContext() {
        return context;
    }

}
