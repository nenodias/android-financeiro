package br.edu.ite.financeiroandroid.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class Pessoa implements Serializable, Comparable<Pessoa>{

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

    @Override
    public String toString() {
        return getCodigo() + "-" + getNome();
    }

    @Override
    public int compareTo(Pessoa another) {
        if(another != null && another.getCodigo() != null && getCodigo() != null && another.getCodigo().equals(getCodigo())){
            return 0;
        }else if(another != null){
            return getCodigo() <= another.getCodigo() ? -1 : 1;
        }else{
            return -1;
        }
    }
}
