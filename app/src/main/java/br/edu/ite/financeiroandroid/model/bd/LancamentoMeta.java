package br.edu.ite.financeiroandroid.model.bd;

/**
 * Created by SI on 02/05/2016.
 */
public class LancamentoMeta {

    public static final String TABELA = "lancamentos";
    private static final String ID = "_id";
    private static final String DESCRICAO = "descricao";
    private static final String VALOR = "valor";
    private static final String TIPO = "tipo";
    private static final String DATA_VENCIMENTO = "data_vencimento";
    private static final String DATA_PAGAMENTO = "data_pagamento";

    public static String getCreateTable(){
        StringBuilder builder = new StringBuilder("CREATE TABLE");
        builder.append(TABELA)
                .append("(")
                .append( ID )
                .append(" integer primary key autoincrement,")
                .append(DESCRICAO )
                .append(" varchar(255) not null,")
                .append(VALOR )
                .append(" real not null,")
                .append(TIPO)
                .append(" varchar(2) not null,")
                .append(DATA_VENCIMENTO)
                .append(" date not null,")
                .append(DATA_PAGAMENTO)
                .append(" date")
                .append(")");
        return builder.toString();
    };

}
