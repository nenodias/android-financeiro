package br.edu.ite.financeiroandroid.adapter.dto;

import android.widget.ListView;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import br.edu.ite.financeiroandroid.model.Lancamento;
import br.edu.ite.financeiroandroid.model.Pessoa;

public class ItemAdapterDTO {

    private Map<String, String> valores = new HashMap<>();

    public Map<String, String> getValores() {
        return valores;
    }

    public void setValores(Map<String, String> valores) {
        this.valores = valores;
    }

    public void setPessoa(Pessoa pessoa){
        getValores().put("codigo", pessoa.getCodigo() != null ? String.valueOf(pessoa.getCodigo()) : String.valueOf(BigDecimal.ZERO) );
        getValores().put("nome", pessoa.getNome());
    };

    public void setLancameto(Lancamento lancamento){
        getValores().put("codigo", lancamento.getCodigo() != null ? String.valueOf(lancamento.getCodigo()) : String.valueOf(BigDecimal.ZERO) );
        getValores().put("descricao", lancamento.getDescricao());
        getValores().put("valor", String.valueOf( lancamento.getValor() ) );
        getValores().put("tipo", lancamento.getTipo().getDescricao() );
        getValores().put("pessoa", lancamento.getPessoa().toString() );
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        getValores().put("dataVencimento", format.format( lancamento.getDataVencimento() ) );
        getValores().put("dataPagamento", lancamento.getDataPagamento() != null ?  format.format( lancamento.getDataPagamento() ) : StringUtils.EMPTY );
    };

    public static Integer getPkFromList(ListView lista,int position){
        ItemAdapterDTO item = (ItemAdapterDTO)lista.getItemAtPosition(position);
        Integer pk = Integer.valueOf( item.getValores().get("codigo") );
        return pk;
    }
}
