package br.edu.ite.financeiroandroid.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Lancamento implements Serializable, Comparable<Lancamento> {

    private Integer codigo;

    private String descricao;

    private String idTipo;

    private TipoLancamento tipo;

    private BigDecimal valor;

    private Date dataVencimento;

    private Date dataPagamento;

    private Integer idPessoa;

    private Pessoa pessoa;

    @Override
    public boolean equals(Object o) {
        if(o instanceof  Lancamento){
            Lancamento other = (Lancamento)o;
            return new EqualsBuilder()
                    .append(codigo, other.codigo)
                    .append(descricao, other.descricao)
                    .append(idTipo, other.idTipo)
                    .append(dataPagamento, other.dataPagamento)
                    .append(dataVencimento, other.dataVencimento)
                    .append(idPessoa, other.idPessoa)
                    .append(valor, other.valor)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(codigo)
                .append(descricao)
                .append(idTipo)
                .append(dataPagamento)
                .append(dataVencimento)
                .append(idPessoa)
                .append(valor)
                .toHashCode();
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(String idTipo) {
        this.idTipo = idTipo;
        if(idTipo != null){
            this.tipo = TipoLancamento.findById(idTipo);
        }
    }

    public TipoLancamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoLancamento tipo) {
        this.tipo = tipo;
        if(tipo != null){
            this.idTipo = tipo.getCodigo();
        }
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        if(pessoa != null){
            this.idPessoa = pessoa.getCodigo();
        }
    }

    @Override
    public int compareTo(Lancamento another) {
        if(another != null && another.getCodigo() != null && getCodigo() != null && another.getCodigo().equals(getCodigo())){
            return 0;
        }else if(another != null){
            return getCodigo() <= another.getCodigo() ? -1 : 1;
        }else{
            return -1;
        }
    }
}
