package br.edu.ite.financeiroandroid.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import br.edu.ite.financeiroandroid.activity.BaseActivity;
import br.edu.ite.financeiroandroid.dao.GenericDao;

public class ActivitiesUtil {

    public static final String CAMPO_REQUERIDO = "Campo requerido";

    public static boolean isValidField( Spinner component ){
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

    public static boolean isValidField(EditText component){
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

}
