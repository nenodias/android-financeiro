package br.edu.ite.financeiroandroid.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import br.edu.ite.financeiroandroid.R;
import br.edu.ite.financeiroandroid.util.ActivitiesUtil;

public class ListaPessoasActivity extends BaseActivity {

    private Context context = ListaPessoasActivity.this;
    private Integer origem;

    private Button btnNovo;

    private ListView lista;

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
                setResult(ActivitiesUtil.CADASTRO_PESSOA);
                finish();
            }
        });
    }


}
