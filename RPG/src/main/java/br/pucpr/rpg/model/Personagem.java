package br.pucpr.rpg.model;

import java.util.ArrayList;

public class Personagem {
    private int id;
    private String nome;
    private int nivel;
    private int vida;
    private int mana;
    private ArrayList<Item> itens = new ArrayList<>();

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

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public void subirNivel() {
        nivel++;
    }

    public void receberDano(int dano) {
        vida -= dano;
    }

    public void curarVida(int cura) {
        vida += cura;
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public void removerItem(Item item) {
        itens.remove(item);
    }
}