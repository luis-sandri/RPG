package br.pucpr.rpg.model;

public class Missao implements Entidade {
    private static final long serialVersionUID = 1L;

    private int id;
    private String titulo;
    private String descricao;
    private String status;
    private String recompensa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo(){return titulo;}
    public void setTitulo(String titulo){this.titulo = titulo;}

    public String getDescricao(){return descricao;}
    public void setDescricao(String descricao){this.descricao = descricao;}

    public String getStatus(){return status;}
    public void setStatus(String status){this.status = status;}

    public String getRecompensa(){return recompensa;}
    public void setRecompensa(String recompensa){this.recompensa = recompensa;}




}
