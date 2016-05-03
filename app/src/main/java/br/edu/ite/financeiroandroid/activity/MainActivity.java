package br.edu.ite.financeiroandroid.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import br.edu.ite.financeiroandroid.R;

public class MainActivity extends BaseActivity {

    private Context context = MainActivity.this;

    private Button btnListarPessoas;
    private Button btnCadastrarPessoa;
    private Button btnListarLancamentos;
    private Button btnCadastrarLancamento;

    private Button.OnClickListener menuListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            resolverRetorno(id, null);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Android Financeiro V1.0");

        btnListarPessoas = (Button) findViewById( R.id.menu_btn_listar_cad );
        btnCadastrarPessoa = (Button) findViewById( R.id.menu_btn_cadastrar_cad );
        btnListarLancamentos = (Button) findViewById( R.id.menu_btn_listar_lan );
        btnCadastrarLancamento = (Button) findViewById( R.id.menu_btn_cadastrar_lan );

        btnListarPessoas.setOnClickListener(menuListener);
        btnCadastrarPessoa.setOnClickListener(menuListener);
        btnListarLancamentos.setOnClickListener(menuListener);
        btnCadastrarLancamento.setOnClickListener(menuListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Context getAppContext() {
        return context;
    }
}
