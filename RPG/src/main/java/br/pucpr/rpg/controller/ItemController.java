package br.pucpr.rpg.controller;

import br.pucpr.rpg.model.Item;

public class ItemController extends CrudController<Item> {

    public ItemController() {
        super("itens.obj");
    }
}
