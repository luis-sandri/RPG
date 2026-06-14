package br.pucpr.rpg;

import br.pucpr.rpg.controller.CampanhaController;
import br.pucpr.rpg.controller.ClasseRpgController;
import br.pucpr.rpg.controller.ItemController;
import br.pucpr.rpg.controller.JogadorController;
import br.pucpr.rpg.controller.MissaoController;
import br.pucpr.rpg.controller.NpcController;
import br.pucpr.rpg.controller.PersonagemController;
import br.pucpr.rpg.controller.RacaController;
import br.pucpr.rpg.view.TelaCampanha;
import br.pucpr.rpg.view.TelaClasseRpg;
import br.pucpr.rpg.view.TelaItem;
import br.pucpr.rpg.view.TelaJogador;
import br.pucpr.rpg.view.TelaMissao;
import br.pucpr.rpg.view.TelaNpc;
import br.pucpr.rpg.view.TelaPersonagem;
import br.pucpr.rpg.view.TelaRaca;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RpgApp extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane layoutPrincipal = new BorderPane();

        HBox menu = new HBox(10);
        menu.setStyle("""
                    -fx-padding: 12px;
                    -fx-background-color: #151522;
                """);

        Button btnPersonagens = new Button("Personagens");
        Button btnJogadores = new Button("Jogadores");
        Button btnClasses = new Button("Classes");
        Button btnRacas = new Button("Raças");
        Button btnItens = new Button("Itens");
        Button btnMissoes = new Button("Missões");
        Button btnNpcs = new Button("NPCs");
        Button btnCampanhas = new Button("Campanhas");

        menu.getChildren().addAll(
                btnPersonagens,
                btnJogadores,
                btnClasses,
                btnRacas,
                btnItens,
                btnMissoes,
                btnNpcs,
                btnCampanhas);

        layoutPrincipal.setTop(menu);

        PersonagemController personagemController = new PersonagemController();
        JogadorController jogadorController = new JogadorController();
        ClasseRpgController classeRpgController = new ClasseRpgController();
        RacaController racaController = new RacaController();
        ItemController itemController = new ItemController();
        MissaoController missaoController = new MissaoController();
        NpcController npcController = new NpcController();
        CampanhaController campanhaController = new CampanhaController();

        btnPersonagens.setOnAction(event -> {
            layoutPrincipal.setCenter(new TelaPersonagem(personagemController));
        });
        /*
         * btnJogadores.setOnAction(event -> {
         * layoutPrincipal.setCenter(new TelaJogador(jogadorController));
         * });
         * 
         * btnClasses.setOnAction(event -> {
         * layoutPrincipal.setCenter(new TelaClasseRpg(classeRpgController));
         * });
         * 
         * btnRacas.setOnAction(event -> {
         * layoutPrincipal.setCenter(new TelaRaca(racaController));
         * });
         * 
         * btnMissoes.setOnAction(event -> {
         * layoutPrincipal.setCenter(new TelaMissao(missaoController));
         * });
         * 
         * btnNpcs.setOnAction(event -> {
         * layoutPrincipal.setCenter(new TelaNpc(npcController));
         * });
         */
        
        btnItens.setOnAction(event -> {
            layoutPrincipal.setCenter(new TelaItem(itemController));
        });
        btnCampanhas.setOnAction(event -> {
            layoutPrincipal.setCenter(new TelaCampanha(campanhaController));
        });

        layoutPrincipal.setCenter(new TelaPersonagem(personagemController));

        Scene scene = new Scene(layoutPrincipal, 900, 600);


        stage.setScene(scene);
        stage.setTitle("RPG Manager");
        stage.show();
    }
}