package br.edu.ite.financeiroandroid.dao;

import java.util.List;

import br.edu.ite.financeiroandroid.model.Pessoa;
import br.edu.ite.financeiroandroid.util.DadosUtil;

public class PessoaDAO implements IPessoaDAO{

    @Override
    public Integer nextValue() {
        return DadosUtil.pessoaList.size()+1;
    }

    @Override
    public Pessoa findById(Integer pk) {
        return DadosUtil.pessoaList.get(pk);
    }

    public void save(Pessoa entidade){
        DadosUtil.addPessoa(entidade);
    }

    @Override
    public void delete(Integer pk) {
        DadosUtil.pessoaList.remove(pk);
    }

    @Override
    public List<Pessoa> findAll() {
        return DadosUtil.pessoaList;
    }
}
