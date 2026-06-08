package br.pucpr.rpg.view;

import br.pucpr.rpg.controller.PersonagemController;
import br.pucpr.rpg.model.Personagem;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TelaPersonagem extends VBox {

    private PersonagemController controller;

    public TelaPersonagem(PersonagemController controller) {
        this.controller = controller;

        setSpacing(12);
        setStyle("""
            -fx-padding: 24px;
            -fx-background-color: #1e1e2f;
        """);

        Label titulo = new Label("Cadastro de Personagens");
        titulo.setStyle("""
            -fx-text-fill: white;
            -fx-font-size: 22px;
            -fx-font-weight: bold;
        """);

        TextField txtNome = new TextField();
        txtNome.setPromptText("Nome");

        TextField txtNivel = new TextField();
        txtNivel.setPromptText("Nível");

        TextField txtVida = new TextField();
        txtVida.setPromptText("Vida");

        TextField txtMana = new TextField();
        txtMana.setPromptText("Mana");

        Button btnCadastrar = new Button("Cadastrar");
        Button btnEditar = new Button("Editar");
        Button btnExcluir = new Button("Excluir");
        Button btnLimpar = new Button("Limpar");
        Button btnAtualizar = new Button("Atualizar");

        TableView<Personagem> tabela = new TableView<>();
        atualizarTabela(tabela);

        TableColumn<Personagem, Number> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getId())
        );

        TableColumn<Personagem, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getNome())
        );

        TableColumn<Personagem, Number> colNivel = new TableColumn<>("Nível");
        colNivel.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getNivel())
        );

        TableColumn<Personagem, Number> colVida = new TableColumn<>("Vida");
        colVida.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getVida())
        );

        TableColumn<Personagem, Number> colMana = new TableColumn<>("Mana");
        colMana.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getMana())
        );

        tabela.getColumns().addAll(colId, colNome, colNivel, colVida, colMana);

        tabela.getSelectionModel().selectedItemProperty().addListener(
                (obs, antigo, selecionado) -> {
                    if (selecionado != null) {
                        txtNome.setText(selecionado.getNome());
                        txtNivel.setText(String.valueOf(selecionado.getNivel()));
                        txtVida.setText(String.valueOf(selecionado.getVida()));
                        txtMana.setText(String.valueOf(selecionado.getMana()));
                    }
                }
        );

        btnCadastrar.setOnAction(event -> {
            Personagem personagem = new Personagem();

            personagem.setNome(txtNome.getText());
            personagem.setNivel(Integer.parseInt(txtNivel.getText()));
            personagem.setVida(Integer.parseInt(txtVida.getText()));
            personagem.setMana(Integer.parseInt(txtMana.getText()));

            controller.cadastrar(personagem);
            atualizarTabela(tabela);

            txtNome.clear();
            txtNivel.clear();
            txtVida.clear();
            txtMana.clear();
        });

        btnEditar.setOnAction(event -> {
            Personagem selecionado = tabela.getSelectionModel().getSelectedItem();

            controller.editar(
                    selecionado,
                    txtNome.getText(),
                    Integer.parseInt(txtNivel.getText()),
                    Integer.parseInt(txtVida.getText()),
                    Integer.parseInt(txtMana.getText())
            );

            atualizarTabela(tabela);
        });

        btnExcluir.setOnAction(event -> {
            Personagem selecionado = tabela.getSelectionModel().getSelectedItem();
            controller.excluir(selecionado);
            atualizarTabela(tabela);
        });

        btnLimpar.setOnAction(event -> {
            txtNome.clear();
            txtNivel.clear();
            txtVida.clear();
            txtMana.clear();
            tabela.getSelectionModel().clearSelection();
        });

        btnAtualizar.setOnAction(event -> atualizarTabela(tabela));

        HBox botoes = new HBox(10, btnCadastrar, btnEditar, btnExcluir, btnLimpar, btnAtualizar);

        getChildren().addAll(
                titulo,
                txtNome,
                txtNivel,
                txtVida,
                txtMana,
                botoes,
                tabela
        );
    }

    private void atualizarTabela(TableView<Personagem> tabela) {
        tabela.getItems().setAll(controller.listar());
    }
}
