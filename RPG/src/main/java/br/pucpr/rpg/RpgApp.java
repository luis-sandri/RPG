package br.pucpr.rpg;

import br.pucpr.rpg.controller.PersonagemController;
import br.pucpr.rpg.view.TelaPersonagem;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RpgApp extends Application {

    @Override
    public void start(Stage stage) {
        PersonagemController controller = new PersonagemController();
        TelaPersonagem tela = new TelaPersonagem(controller);

        Scene scene = new Scene(tela, 800, 600);

        stage.setScene(scene);
        stage.setTitle("RPG Manager");
        stage.show();
    }
}