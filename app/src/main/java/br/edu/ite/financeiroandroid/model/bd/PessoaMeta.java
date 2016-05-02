package br.edu.ite.financeiroandroid.model.bd;

/**
 * Created by SI on 02/05/2016.
 */
public class PessoaMeta {

    public static final String TABELA = "pessoas";
    private static final String ID = "_id";
    private static final String NOME = "nome";

    public static String getCreateTable(){
        StringBuilder builder = new StringBuilder("CREATE TABLE");
        builder.append(TABELA)
                .append("(")
                .append( ID )
                .append(" integer primary key autoincrement,")
                .append(NOME )
                .append(" varchar(255) not null")
                .append(")");
        return builder.toString();
    };

}
