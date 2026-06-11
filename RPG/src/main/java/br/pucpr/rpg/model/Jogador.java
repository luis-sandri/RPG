package br.pucpr.rpg.model;

import java.util.ArrayList;

public class Jogador extends Entidade {
    private String nome;
    private String apelido;
    private String email;
    private ArrayList<Personagem> personagens = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Personagem> getPersonagens() {
        return personagens;
    }

    public void adicionarPersonagem(Personagem personagem) {
        personagens.add(personagem);
    }

    public void removerPersonagem(Personagem personagem) {
        personagens.remove(personagem);
    }
}
