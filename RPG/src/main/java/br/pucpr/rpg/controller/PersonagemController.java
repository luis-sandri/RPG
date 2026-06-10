package br.pucpr.rpg.controller;

import br.pucpr.rpg.model.Personagem;

public class PersonagemController extends CrudController<Personagem> {

    public PersonagemController() {
        super("personagens.obj");
    }
}
