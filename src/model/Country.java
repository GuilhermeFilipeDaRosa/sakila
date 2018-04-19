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
public class Country {
    private int Id;
    private String Nome;
    private String Ultimatualizacao;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getUltimatualizacao() {
        return Ultimatualizacao;
    }

    public void setUltimatualizacao(String Ultimatualizacao) {
        this.Ultimatualizacao = Ultimatualizacao;
    }
}
