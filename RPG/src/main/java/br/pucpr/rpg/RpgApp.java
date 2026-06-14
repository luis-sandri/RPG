package br.pucpr.rpg;

import br.pucpr.rpg.controller.MissaoController;
import br.pucpr.rpg.controller.PersonagemController;
import br.pucpr.rpg.view.TelaMissao;
import br.pucpr.rpg.view.TelaPersonagem;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RpgApp extends Application {

    @Override
    public void start(Stage stage) {
        PersonagemController controller = new PersonagemController();
        TelaPersonagem tela = new TelaPersonagem(controller);

        MissaoController controllerM = new MissaoController();
        TelaMissao telaMissao = new TelaMissao(controllerM);

        Scene scene = new Scene(tela, 800, 600);


        stage.setScene(scene);
        stage.setTitle("RPG");
        stage.show();
    }
}