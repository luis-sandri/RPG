package br.pucpr.rpg.controller;

import br.pucpr.rpg.model.Missao;

public class MissaoController extends CrudController<Missao> {

    public MissaoController() {
        super("missoes.obj");
    }
}
