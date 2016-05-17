package br.edu.ite.financeiroandroid.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import org.apache.commons.lang3.StringUtils;

import br.edu.ite.financeiroandroid.R;
import br.edu.ite.financeiroandroid.dao.GenericDao;
import br.edu.ite.financeiroandroid.dao.IPessoaDAO;
import br.edu.ite.financeiroandroid.factory.ActivityFactory;
import br.edu.ite.financeiroandroid.model.Pessoa;
import br.edu.ite.financeiroandroid.util.ActivitiesUtil;
import br.edu.ite.financeiroandroid.util.FactoryDAO;

public class CadastroPessoaActivity extends BaseActivity {

    private Context context = CadastroPessoaActivity.this;
    private Integer origem;
    private Pessoa entidade = new Pessoa();

    private EditText codigo;
    private EditText nome;

    private Button btnListar;
    private Button btnSalvar;

    private IPessoaDAO pessoaDao = FactoryDAO.createPessoaDao(CadastroPessoaActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);
        setTitle("Cadastrar Pessoas");

        btnListar = (Button) findViewById( R.id.cad_btn_listar );
        btnSalvar = (Button) findViewById( R.id.cad_btn_salvar );

        codigo = (EditText) findViewById( R.id.cad_txt_codigo );
        codigo.setEnabled(false);
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
            this.entidade = pessoa;
        }
    }

    protected void salvar() {
        if(isValid()){
            Integer codigo = StringUtils.isNotBlank( this.codigo.getText() ) ? Integer.valueOf( this.codigo.getText().toString() ) : null;
            entidade.setCodigo(codigo);
            entidade.setNome(this.nome.getText().toString());
            //Persiste
            pessoaDao.save(entidade);
            messageSave();
        }
    }

    @Override
    protected void listar() {
        setResult(ActivityFactory.LISTAR_PESSOA);
        finish();
    }

    private boolean isValid() {
        boolean validacao = true;
        if( !ActivitiesUtil.isValidField(nome) ){
            return false;
        }
        return validacao;
    }

    @Override
    public Context getAppContext() {
        return context;
    }

}
