package com.example.rastreio_how6.encomenda;

import java.util.Date;
import java.util.UUID;

public class Encomenda {
    private int id;
    private int id_loja;
    private int id_produto;
    private String guid;
    private String status;
    private String data_envio;
    private String data_alteracao;

    public Encomenda (int id_loja, int id_produto, String guid, String status, String data_envio) {
        this.id_loja = id_loja;
        this.id_produto = id_produto;
        this.guid = guid;
        this.status = status;
        this.data_envio = data_envio;
        this.data_alteracao = null;
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

    public void setId_loja(int id_loja) {
        this.id_loja = id_loja;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData_envio() {
        return data_envio;
    }

    public void setData_envio(String data_envio) {
        this.data_envio = data_envio;
    }

    public String getData_alteracao() {
        return data_alteracao;
    }

    public void setData_alteracao(String data_alteracao) {
        this.data_alteracao = data_alteracao;
    }
}
