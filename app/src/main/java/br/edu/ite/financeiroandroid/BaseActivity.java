package br.edu.ite.financeiroandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class BaseActivity extends AppCompatActivity {

    protected Context getContext(){
        return BaseActivity.this;
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        resolverRetorno(resultCode);
    }

    protected void resolverRetorno(int id){
        Intent i = null;
        switch ( id ){
            case ActivitiesUtil.LISTAR_PESSOA:
            case R.id.menu_btn_listar_cad:
                i = new Intent(getContext(), ListaPessoasActivity.class);
                break;
            case ActivitiesUtil.CADASTRO_PESSOA:
            case R.id.menu_btn_cadastrar_cad:
                i = new Intent(getContext(), CadastroPessoaActivity.class);
                break;
            case ActivitiesUtil.LISTAR_LANCAMENTO:
            case R.id.menu_btn_listar_lan:
                i = new Intent(getContext(), ListaLancamentosActivity.class);
                break;
            case ActivitiesUtil.CADASTRO_LANCAMENTO:
            case R.id.menu_btn_cadastrar_lan:
                i = new Intent(getContext(), CadastroLancamentoActivity.class);
                break;
        }
        if(i != null) {
            startActivityForResult(i, ActivitiesUtil.MAIN);
        }
    }
}