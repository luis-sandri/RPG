package br.pucpr.rpg.controller;

import br.pucpr.rpg.model.Raca;

public class RacaController extends CrudController<Raca> {

    public RacaController() {
        super("racas.obj");
    }
}
