package br.edu.ite.financeiroandroid.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

import br.edu.ite.financeiroandroid.R;
import br.edu.ite.financeiroandroid.util.ActivitiesUtil;

public abstract class BaseActivity extends AppCompatActivity {

    public static final String CAMPO_REQUERIDO = "Campo requerido";

    protected Context getContext(){
        return BaseActivity.this;
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        resolverRetorno(resultCode, data);
    }

    protected void resolverRetorno(int id, Intent data){
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
            if (isModel(data)){
                Serializable model = data.getExtras().getSerializable("model");
                i.putExtra("model", model);
            }
            startActivityForResult(i, ActivitiesUtil.MAIN);
        }
    }

    protected boolean isModel(Intent data) {
        return data != null && hasModel(data.getExtras());
    }

    protected boolean hasModel(Bundle data) {
        return data != null && data.getSerializable("model") != null;
    }

    protected boolean isValidField( Spinner component ){
        if(component != null){
            if(component.getSelectedItem() == null ){
                TextView errorText = (TextView)component.getSelectedView();
                errorText.setError(CAMPO_REQUERIDO);
                return false;
            }else{
                return true;
            }
        }
        return true;
    }

    protected boolean isValidField(EditText component){
        if(component != null){
            if(StringUtils.isBlank(component.getText())){
                component.setError(CAMPO_REQUERIDO);
                return false;
            }else{
                return true;
            }
        }
        return true;
    }

    protected void excluir(View v, int position, Long id){}
    protected void editar(View v, int position, Long id){}
    protected void listar(){}
    protected void salvar(){}

    protected AdapterView.OnItemLongClickListener excluirItem = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
            final View v = view ;
            final long i = id;
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Excluir?");
            builder.setMessage("Deseja excluir o registro?");
            builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    //excluir
                    excluir(v, position, i);
                }
            });
            builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {}
            });
            builder.setCancelable(true);
            AlertDialog alerta = builder.create();
            alerta.show();
            return true;
        }
    };

    protected AdapterView.OnItemClickListener editarItem = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            editar(view, position, id);
        }
    };

    protected View.OnClickListener listarClick =  new View.OnClickListener () {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Ir para listagem?");
            builder.setMessage("Você perderá os dados não salvos");
            builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    listar();
                }
            });
            builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    listar();
                }
            });
            AlertDialog alerta = builder.create();
            alerta.show();
        }
    };

    protected void messageSave(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getAppContext());
        builder.setTitle("Sucesso");
        builder.setMessage("Registro salvo com sucesso!");
        builder.setCancelable(true);
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                listar();
            }
        });
        AlertDialog alerta = builder.create();
        alerta.show();

    }

    protected View.OnClickListener salvarClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            salvar();
        }
    };

    public abstract Context getAppContext();

    public Integer getAutoIncrement(EditText field, List<? extends Object> lista){
        Integer codigo = null;
        if(lista != null){
            codigo = StringUtils.isNotBlank(field.getText()) ? Integer.valueOf(field.getText().toString()) : lista.size();
        }else {
            codigo = StringUtils.isNotBlank(field.getText()) ? Integer.valueOf(field.getText().toString()) : 0;
        }
        return codigo;
    }
}
