package br.edu.ite.financeiroandroid.util;

import android.content.Context;

import br.edu.ite.financeiroandroid.dao.ILancamentoDAO;
import br.edu.ite.financeiroandroid.dao.IPessoaDAO;
import br.edu.ite.financeiroandroid.dao.LancamentoDAOBanco;
import br.edu.ite.financeiroandroid.dao.PessoaDAOBanco;

public class FactoryDAO {

    public static IPessoaDAO createPessoaDao(Context context){
        return new PessoaDAOBanco(context);
    }

    public static ILancamentoDAO createLancamentoDao(Context context){
        return new LancamentoDAOBanco(context);
    }
}
