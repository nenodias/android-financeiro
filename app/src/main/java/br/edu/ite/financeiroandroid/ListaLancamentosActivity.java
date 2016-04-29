package br.edu.ite.financeiroandroid;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ListaLancamentosActivity extends BaseActivity {

    private Context context = ListaLancamentosActivity.this;
    private Integer origem;

    private Button btnNovo;

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_lancamentos);

        btnNovo = (Button) findViewById( R.id.lan_btn_novo );
        lista = (ListView) findViewById( R.id.lan_lista );
        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(ActivitiesUtil.CADASTRO_LANCAMENTO);
                finish();
            }
        });
    }

}
