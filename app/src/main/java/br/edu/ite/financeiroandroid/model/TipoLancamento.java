package br.edu.ite.financeiroandroid.model;

/**
 * Created by nenodias on 28/04/16.
 */
public enum TipoLancamento {
    RECEITA("R", "Receita"),
    DESPESA("D", "Despesa");

    private String codigo;

    private String descricao;

    TipoLancamento(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoLancamento findById(String codigo){
        for (TipoLancamento tipo : TipoLancamento.values()){
            if(tipo.codigo.equals(codigo)){
                return tipo;
            }
        }
        return null;
    }
}
