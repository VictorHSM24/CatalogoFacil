package com.example.catalogofacil.model;

import java.io.Serializable;
public class Pedido {

    private String codigoRoupa;
    private String nomeRoupa;
    private String cor;
    private String tamanho;
    private int quantidade;
    private String nomeCliente;
    private String telefone;
    private String situacaoPagamento;

    public Pedido() {
        // Construtor para serialização/desserialização do Firebase
    }

    public Pedido(String codigoRoupa, String nomeRoupa, String cor, String tamanho, int quantidade, String nomeCliente, String telefone, String situacaoPagamento) {
        this.codigoRoupa = codigoRoupa;
        this.nomeRoupa = nomeRoupa;
        this.cor = cor;
        this.tamanho = tamanho;
        this.quantidade = quantidade;
        this.nomeCliente = nomeCliente;
        this.telefone = telefone;
        this.situacaoPagamento = situacaoPagamento;
    }

    public String getCodigoRoupa() {
        return codigoRoupa;
    }

    public void setCodigoRoupa(String codigoRoupa) {
        this.codigoRoupa = codigoRoupa;
    }

    public String getNomeRoupa() {
        return nomeRoupa;
    }

    public void setNomeRoupa(String nomeRoupa) {
        this.nomeRoupa = nomeRoupa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSituacaoPagamento() {
        return situacaoPagamento;
    }

    public void setSituacaoPagamento(String situacaoPagamento) {
        this.situacaoPagamento = situacaoPagamento;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "codigoRoupa='" + codigoRoupa + '\'' +
                ", nomeRoupa='" + nomeRoupa + '\'' +
                ", nomeCliente='" + nomeCliente + '\'' +
                ", quantidade=" + quantidade +
                '}';
    }
}
