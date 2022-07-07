package com.example.rastreio_how6.encomenda;

import java.util.Date;
import java.util.UUID;

public class Encomenda {
    private int id;
    private int id_loja;
    private int id_produto;
    private UUID guid;
    private String status;
    private Date data_envio;
    private Date data_alteracao;

    public Encomenda (int id_loja, int id_produto, UUID guid, String status, Date data_envio) {
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

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getData_envio() {
        return data_envio;
    }

    public void setData_envio(Date data_envio) {
        this.data_envio = data_envio;
    }

    public Date getData_alteracao() {
        return data_alteracao;
    }

    public void setData_alteracao(Date data_alteracao) {
        this.data_alteracao = data_alteracao;
    }
}
