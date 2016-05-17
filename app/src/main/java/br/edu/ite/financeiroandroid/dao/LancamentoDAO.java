package br.edu.ite.financeiroandroid.dao;

import java.util.List;

import br.edu.ite.financeiroandroid.model.Lancamento;
import br.edu.ite.financeiroandroid.model.Pessoa;
import br.edu.ite.financeiroandroid.util.DadosUtil;

public class LancamentoDAO implements ILancamentoDAO{

    @Override
    public Integer nextValue() {
        return DadosUtil.lancamentoList.size()+1;
    }

    @Override
    public Lancamento findById(Integer pk) {
        return DadosUtil.lancamentoList.get(pk);
    }

    public void save(Lancamento entidade){
        DadosUtil.addLancamento(entidade);
    }

    @Override
    public void delete(Integer pk) {
        DadosUtil.lancamentoList.remove(pk);
    }

    @Override
    public List<Lancamento> findAll() {
        return DadosUtil.lancamentoList;
    }
}
