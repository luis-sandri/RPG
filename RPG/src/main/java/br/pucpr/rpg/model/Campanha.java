package br.pucpr.rpg.model;

import java.util.ArrayList;

public class Campanha extends Entidade {
    /*
     * - nome
     * - descricao
     * - nivelMinimo
     * - status
     */
    private String nome;
    private String descricao;
    private int nivelMinimo;
    private String status;

    private ArrayList<Personagem> personagensParticipantes = new ArrayList<>();
    private ArrayList<Missao> missoesDaCampanha = new ArrayList<>();

    public ArrayList<Personagem> getPersonagensParticipantes() {
        return personagensParticipantes;
    }

    public void setPersonagensParticipantes(ArrayList<Personagem> personagensParticipantes) {
        this.personagensParticipantes = personagensParticipantes;
    }

    public void adicionarPersonagem(Personagem personagem) {
        this.personagensParticipantes.add(personagem);
    }

    public void removerPersonagem(Personagem personagem) {
        this.personagensParticipantes.remove(personagem);
    }

    public void adicionarMissao(Missao missao) {
        this.missoesDaCampanha.add(missao);
    }

    public void removerMissao(Missao missao) {
        this.missoesDaCampanha.remove(missao);
    }

    public ArrayList<Missao> getMissoesDaCampanha() {
        return missoesDaCampanha;
    }

    public void setMissoesDaCampanha(ArrayList<Missao> missoesDaCampanha) {
        this.missoesDaCampanha = missoesDaCampanha;
    }

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

    public int getNivelMinimo() {
        return nivelMinimo;
    }

    public void setNivelMinimo(int nivelMinimo) {
        this.nivelMinimo = nivelMinimo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
