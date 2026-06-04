package br.pucpr.rpg.controller;

import br.pucpr.rpg.model.Personagem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PersonagemController {

    private ObservableList<Personagem> personagens =
            FXCollections.observableArrayList();

    private int proximoId = 1;

    public ObservableList<Personagem> listar() {
        return personagens;
    }

    public void cadastrar(Personagem personagem) {
        personagem.setId(proximoId);
        proximoId++;

        personagens.add(personagem);
    }

    public void editar(Personagem selecionado, String nome, int nivel, int vida, int mana) {
        if (selecionado != null) {
            selecionado.setNome(nome);
            selecionado.setNivel(nivel);
            selecionado.setVida(vida);
            selecionado.setMana(mana);
        }
    }

    public void excluir(Personagem personagem) {
        personagens.remove(personagem);
    }
}