package com.example.rastreio_how6.produto;

public class Produto {

    private int id;
    private int id_loja;
    private String nome;
    private String valor;
    private String descricao;

    public Produto(int id_loja, String nome, String valor, String descricao) {
        this.id_loja = id_loja;
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_loja() {
        return id_loja;
    }

    public void setId_loja(int idLoja) {
        this.id_loja = idLoja;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
