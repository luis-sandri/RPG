package br.pucpr.rpg.controller;

import br.pucpr.rpg.model.Jogador;

public class JogadorController extends CrudController<Jogador> {

    public JogadorController() {
        super("jogadores.obj");
    }
}
