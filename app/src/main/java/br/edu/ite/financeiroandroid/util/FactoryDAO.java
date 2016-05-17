package br.edu.ite.financeiroandroid.util;

import android.content.Context;

import br.edu.ite.financeiroandroid.dao.ILancamentoDAO;
import br.edu.ite.financeiroandroid.dao.IPessoaDAO;
import br.edu.ite.financeiroandroid.dao.LancamentoDAO;
import br.edu.ite.financeiroandroid.dao.PessoaDAO;

public class FactoryDAO {

    public static IPessoaDAO createPessoaDao(Context context){
        return new PessoaDAO(context);
    }

    public static ILancamentoDAO createLancamentoDao(Context context){
        return new LancamentoDAO(context);
    }
}
