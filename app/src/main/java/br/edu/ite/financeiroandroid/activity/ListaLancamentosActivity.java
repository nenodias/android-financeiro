package br.edu.ite.financeiroandroid.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ite.financeiroandroid.R;
import br.edu.ite.financeiroandroid.adapter.LancamentoListAdapter;
import br.edu.ite.financeiroandroid.adapter.dto.ItemAdapterDTO;
import br.edu.ite.financeiroandroid.model.Lancamento;
import br.edu.ite.financeiroandroid.model.Pessoa;
import br.edu.ite.financeiroandroid.util.ActivitiesUtil;
import br.edu.ite.financeiroandroid.util.DadosUtil;

public class ListaLancamentosActivity extends BaseActivity {

    private Context context = ListaLancamentosActivity.this;
    private Integer origem;

    private Button btnNovo;

    private ListView lista;

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
                setResult(ActivitiesUtil.CADASTRO_LANCAMENTO);
                finish();
            }
        });
        lista.setOnItemLongClickListener(this.excluirItem);
        lista.setOnItemSelectedListener(this.editarItem);
        List<ItemAdapterDTO> items = new ArrayList<>();
        for(Lancamento lancamento : DadosUtil.lancamentoList){
            ItemAdapterDTO dto = new ItemAdapterDTO();
            dto.setLancameto(lancamento);
            items.add( dto  );
        }

        lista.setAdapter( new LancamentoListAdapter( context, items) );
    }


    @Override
    protected void excluir(View v, int position, Long id) {
        super.excluir(v, position, id);
    }
}
