package br.edu.ite.financeiroandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.edu.ite.financeiroandroid.R;
import br.edu.ite.financeiroandroid.adapter.LancamentoListAdapter;
import br.edu.ite.financeiroandroid.adapter.PessoaListAdapter;
import br.edu.ite.financeiroandroid.adapter.dto.ItemAdapterDTO;
import br.edu.ite.financeiroandroid.dao.PessoaDAO;
import br.edu.ite.financeiroandroid.factory.ActivityFactory;
import br.edu.ite.financeiroandroid.model.Pessoa;
import br.edu.ite.financeiroandroid.util.ActivitiesUtil;
import br.edu.ite.financeiroandroid.util.DadosUtil;

public class ListaPessoasActivity extends BaseActivity {

    private Context context = ListaPessoasActivity.this;
    private Integer origem;

    private Button btnNovo;

    private ListView lista;

    private PessoaDAO dao = new PessoaDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pessoas);
         setTitle("Listar Pessoas");

        btnNovo = (Button) findViewById( R.id.cad_btn_novo );
        lista = (ListView) findViewById( R.id.cad_lista );
        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(ActivityFactory.CADASTRO_PESSOA);
                finish();
            }
        });
        lista.setOnItemLongClickListener(this.excluirItem);
        lista.setOnItemClickListener(this.editarItem);
        List<ItemAdapterDTO> items = new ArrayList<>();
        for(Pessoa pessoa : dao.findAll() ){
            ItemAdapterDTO dto = new ItemAdapterDTO();
            dto.setPessoa(pessoa);
            items.add( dto  );
        }

        lista.setAdapter(new PessoaListAdapter(context, items) );
    }


    @Override
    protected void excluir(View v, int position, Long id) {
        super.excluir(v, position, id);
        //Excluir Dado
        dao.delete(position);
        listar();
    }

    @Override
    protected void listar() {
        setResult(ActivityFactory.LISTAR_PESSOA);
        finish();
    }

    @Override
    protected void editar(View v, int position, Long id) {
        super.editar(v, position, id);
        Pessoa pessoa = dao.findById(position);
        Bundle data = new Bundle();
        data.putSerializable("model", (Serializable) pessoa);
        Intent intent = new Intent();
        intent.putExtras(data);
        setResult(ActivityFactory.CADASTRO_PESSOA, intent );
        finish();
    }

    @Override
    public Context getAppContext() {
        return context;
    }
}
