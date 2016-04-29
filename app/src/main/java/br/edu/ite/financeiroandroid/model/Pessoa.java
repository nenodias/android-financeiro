package br.edu.ite.financeiroandroid.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Pessoa {

    private Integer codigo;

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof  Pessoa){
            Pessoa other = (Pessoa)o;
            return new EqualsBuilder()
                    .append(codigo, other.codigo)
                    .append(nome, other.nome)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(codigo)
                .append(nome)
                .toHashCode();
    }
}
