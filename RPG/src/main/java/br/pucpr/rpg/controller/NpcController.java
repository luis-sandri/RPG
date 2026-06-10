package br.pucpr.rpg.controller;

import br.pucpr.rpg.model.Npc;

public class NpcController extends CrudController<Npc> {

    public NpcController() {
        super("npcs.obj");
    }
}
