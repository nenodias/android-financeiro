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
import br.edu.ite.financeiroandroid.factory.ActivityFactory;
import br.edu.ite.financeiroandroid.util.ActivitiesUtil;

public abstract class BaseActivity extends AppCompatActivity {

    protected Context getContext(){
        return BaseActivity.this;
    };

    private ActivityFactory factory = new ActivityFactory( getContext() );

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        resolverRetorno(resultCode, data);
    }

    protected void resolverRetorno(int id, Intent data){
        Intent i = factory.createActivity(id);
        if(i != null) {
            if (isModel(data)){
                Serializable model = data.getExtras().getSerializable("model");
                i.putExtra("model", model);
            }
            startActivityForResult(i, ActivityFactory.MAIN);
        }
    }

    protected boolean isModel(Intent data) {
        return data != null && hasModel(data.getExtras());
    }

    protected boolean hasModel(Bundle data) {
        return data != null && data.getSerializable("model") != null;
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

}
