module br.pucpr.rpg {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.pucpr.rpg to javafx.fxml;
    exports br.pucpr.rpg;
}