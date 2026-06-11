package br.pucpr.rpg.model;

import java.io.Serializable;

public abstract class Entidade implements Serializable {
    private int id;
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
}