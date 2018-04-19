/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author User
 */
public class Actor {
    private int id;
    private String nome;
    private String ultimonome;
    private String ultimatualizacao;

    public String getUltimatualizacao() {
        return ultimatualizacao;
    }

    public void setUltimatualizacao(String ultimatualizacao) {
        this.ultimatualizacao = ultimatualizacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUltimonome() {
        return ultimonome;
    }

    public void setUltimonome(String ultimonome) {
        this.ultimonome = ultimonome;
    }

}
