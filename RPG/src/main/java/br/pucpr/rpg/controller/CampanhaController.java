package br.pucpr.rpg.controller;

import br.pucpr.rpg.model.Campanha;

public class CampanhaController extends CrudController<Campanha> {

    public CampanhaController() {
        super("campanhas.obj");
    }
}
