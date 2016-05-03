package br.edu.ite.financeiroandroid.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ite.financeiroandroid.model.Lancamento;
import br.edu.ite.financeiroandroid.model.Pessoa;

public class DadosUtil {

    public static List<Pessoa> pessoaList = new ArrayList<>();

    public static List<Lancamento> lancamentoList = new ArrayList<>();

    public static void addLancamento(Lancamento entidade) {
        if( lancamentoList.size() > entidade.getCodigo() ){
            int cont = 0;
            while(cont < lancamentoList.size()){
                Lancamento objeto = lancamentoList.get(cont);
                if(objeto.getCodigo().equals(entidade.getCodigo())){
                    lancamentoList.remove(cont);
                }else{
                    cont++;
                }
            }
            lancamentoList.add(entidade.getCodigo(), entidade);
        }else{
            entidade.setCodigo( lancamentoList.size() );
            lancamentoList.add(entidade);
        }
        Collections.sort(lancamentoList);
    }

    public static void addPessoa(Pessoa entidade) {
        if( pessoaList.size() > entidade.getCodigo() ){
            int cont = 0;
            while(cont < pessoaList.size()){
                Pessoa objeto = pessoaList.get(cont);
                if(objeto.getCodigo().equals(entidade.getCodigo())){
                    pessoaList.remove(cont);
                }else{
                    cont++;
                }
            }
            pessoaList.add(entidade.getCodigo(), entidade);
        }else{
            entidade.setCodigo( pessoaList.size() );
            pessoaList.add(entidade);
        }
        Collections.sort(pessoaList);
    }


}
