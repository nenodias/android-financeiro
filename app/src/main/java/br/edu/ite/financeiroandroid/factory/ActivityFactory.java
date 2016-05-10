package br.edu.ite.financeiroandroid.factory;

import android.content.Context;
import android.content.Intent;

import br.edu.ite.financeiroandroid.R;
import br.edu.ite.financeiroandroid.activity.BaseActivity;
import br.edu.ite.financeiroandroid.activity.CadastroLancamentoActivity;
import br.edu.ite.financeiroandroid.activity.CadastroPessoaActivity;
import br.edu.ite.financeiroandroid.activity.ListaLancamentosActivity;
import br.edu.ite.financeiroandroid.activity.ListaPessoasActivity;
import br.edu.ite.financeiroandroid.util.ActivitiesUtil;

/**
 * Created by SI on 09/05/2016.
 */
public class ActivityFactory {

    public static final int MAIN = 0;
    public static final int CADASTRO_PESSOA = 1;
    public static final int CADASTRO_LANCAMENTO = 2;
    public static final int LISTAR_PESSOA = 3;
    public static final int LISTAR_LANCAMENTO = 4;

    private Context context;

    public ActivityFactory(Context context){
        this.context = context;
    }

    public Intent createActivity(int id){
        Intent i = null;
        switch ( id ){
            case ActivityFactory.LISTAR_PESSOA:
            case R.id.menu_btn_listar_cad:
                i = new Intent(context, ListaPessoasActivity.class);
                break;
            case ActivityFactory.CADASTRO_PESSOA:
            case R.id.menu_btn_cadastrar_cad:
                i = new Intent(context, CadastroPessoaActivity.class);
                break;
            case ActivityFactory.LISTAR_LANCAMENTO:
            case R.id.menu_btn_listar_lan:
                i = new Intent(context, ListaLancamentosActivity.class);
                break;
            case ActivityFactory.CADASTRO_LANCAMENTO:
            case R.id.menu_btn_cadastrar_lan:
                i = new Intent(context, CadastroLancamentoActivity.class);
                break;
        }
        return i;
    }

}
