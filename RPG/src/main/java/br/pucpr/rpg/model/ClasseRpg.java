package br.pucpr.rpg.model;

public class ClasseRpg extends Entidade {

    private String nome;
    private String descricao;
    private String atributoPrincipal;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAtributoPrincipal() {
        return atributoPrincipal;
    }

    public void setAtributoPrincipal(String atributoPrincipal) {
        this.atributoPrincipal = atributoPrincipal;
    }
}