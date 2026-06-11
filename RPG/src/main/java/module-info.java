module br.pucpr.rpg {
    requires javafx.controls;

    exports br.pucpr.rpg;
    exports br.pucpr.rpg.model;
    exports br.pucpr.rpg.view;
    exports br.pucpr.rpg.controller;

    opens br.pucpr.rpg.model to javafx.base;
}