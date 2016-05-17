package br.edu.ite.financeiroandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.edu.ite.financeiroandroid.R;
import br.edu.ite.financeiroandroid.adapter.LancamentoListAdapter;
import br.edu.ite.financeiroandroid.adapter.dto.ItemAdapterDTO;
import br.edu.ite.financeiroandroid.dao.ILancamentoDAO;
import br.edu.ite.financeiroandroid.dao.LancamentoDAOBanco;
import br.edu.ite.financeiroandroid.factory.ActivityFactory;
import br.edu.ite.financeiroandroid.model.Lancamento;
import br.edu.ite.financeiroandroid.util.FactoryDAO;

public class ListaLancamentosActivity extends BaseActivity {

    private Context context = ListaLancamentosActivity.this;
    private Integer origem;

    private Button btnNovo;

    private ListView lista;

    private ILancamentoDAO dao = FactoryDAO.createLancamentoDao(ListaLancamentosActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_lancamentos);
        setTitle("Listar Lançamentos");

        btnNovo = (Button) findViewById( R.id.lan_btn_novo );
        lista = (ListView) findViewById( R.id.lan_lista );
        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(ActivityFactory.CADASTRO_LANCAMENTO);
                finish();
            }
        });
        lista.setOnItemLongClickListener(this.excluirItem);
        lista.setOnItemClickListener(this.editarItem);
        List<ItemAdapterDTO> items = new ArrayList<>();
        for(Lancamento lancamento : dao.findAll()){
            ItemAdapterDTO dto = new ItemAdapterDTO();
            dto.setLancameto(lancamento);
            items.add( dto  );
        }

        lista.setAdapter( new LancamentoListAdapter( context, items) );
    }


    @Override
    protected void excluir(View v, int position, Long id) {
        super.excluir(v, position, id);
        Integer pk = ItemAdapterDTO.getPkFromList(lista, position);
        dao.delete(pk);
        listar();
    }

    @Override
    protected void editar(View v, int position, Long id) {
        super.editar(v, position, id);
        Integer pk = ItemAdapterDTO.getPkFromList(lista, position);
        Lancamento lancamento = dao.findById(pk);
        Bundle data = new Bundle();
        data.putSerializable("model", (Serializable) lancamento);
        Intent intent = new Intent();
        intent.putExtras(data);
        setResult(ActivityFactory.CADASTRO_LANCAMENTO, intent);
        finish();
    }

    @Override
    protected void listar() {
        setResult(ActivityFactory.LISTAR_LANCAMENTO);
        finish();
    }

    @Override
    public Context getAppContext() {
        return context;
    }

}
