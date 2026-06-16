package br.pucpr.rpg.view;

import br.pucpr.rpg.controller.RacaController;
import br.pucpr.rpg.model.Raca;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TelaRaca extends VBox {

    private RacaController controller;

    private TextField txtNome;
    private TextField txtDescricao;
    private TextField txtBonus;

    private TableView<Raca> tabela;

    public TelaRaca(RacaController controller) {
        this.controller = controller;

        configurarTela();

        Label titulo = criarTitulo();
        criarCampos();

        Button btnCadastrar = new Button("Cadastrar");
        Button btnEditar = new Button("Editar");
        Button btnExcluir = new Button("Excluir");
        Button btnLimpar = new Button("Limpar");
        Button btnAtualizar = new Button("Atualizar");

        tabela = new TableView<>();

        configurarTabela();
        configurarSelecaoTabela();
        atualizarTabela();

        btnCadastrar.setOnAction(e -> cadastrar());
        btnEditar.setOnAction(e -> editar());
        btnExcluir.setOnAction(e -> excluir());
        btnLimpar.setOnAction(e -> limparFormulario());
        btnAtualizar.setOnAction(e -> atualizarTabela());

        HBox botoes = new HBox(
                10,
                btnCadastrar,
                btnEditar,
                btnExcluir,
                btnLimpar,
                btnAtualizar
        );

        getChildren().addAll(
                titulo,
                txtNome,
                txtDescricao,
                txtBonus,
                botoes,
                tabela
        );
    }

    private void configurarTela() {
        setSpacing(12);
        setStyle("""
                -fx-padding: 24px;
                -fx-background-color: #1e1e2f;
                """);
    }

    private Label criarTitulo() {
        Label titulo = new Label("Cadastro de Raças");

        titulo.setStyle("""
                -fx-text-fill: white;
                -fx-font-size: 22px;
                -fx-font-weight: bold;
                """);

        return titulo;
    }

    private void criarCampos() {
        txtNome = new TextField();
        txtNome.setPromptText("Nome");

        txtDescricao = new TextField();
        txtDescricao.setPromptText("Descrição");

        txtBonus = new TextField();
        txtBonus.setPromptText("Bônus");
    }

    private void configurarTabela() {

        TableColumn<Raca, Integer> colId =
                new TableColumn<>("ID");
        colId.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        TableColumn<Raca, String> colNome =
                new TableColumn<>("Nome");
        colNome.setCellValueFactory(
                new PropertyValueFactory<>("nome"));

        TableColumn<Raca, String> colDescricao =
                new TableColumn<>("Descrição");
        colDescricao.setCellValueFactory(
                new PropertyValueFactory<>("descricao"));

        TableColumn<Raca, String> colBonus =
                new TableColumn<>("Bônus");
        colBonus.setCellValueFactory(
                new PropertyValueFactory<>("bonus"));

        tabela.getColumns().addAll(
                colId,
                colNome,
                colDescricao,
                colBonus
        );
    }

    private void configurarSelecaoTabela() {
        tabela.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, antigo, selecionado) -> {
                    if (selecionado != null) {
                        preencherFormulario(selecionado);
                    }
                });
    }

    private void cadastrar() {
        controller.cadastrar(criarRacaDoFormulario());
        atualizarTabela();
        limparFormulario();
    }

    private void editar() {
        Raca selecionado =
                tabela.getSelectionModel().getSelectedItem();

        if (selecionado != null) {
            Raca raca = criarRacaDoFormulario();
            raca.setId(selecionado.getId());

            controller.editar(raca);

            atualizarTabela();
            limparFormulario();
        }
    }

    private void excluir() {
        Raca selecionado =
                tabela.getSelectionModel().getSelectedItem();

        if (selecionado != null) {
            controller.excluir(selecionado);
            atualizarTabela();
            limparFormulario();
        }
    }

    private Raca criarRacaDoFormulario() {
        Raca raca = new Raca();

        raca.setNome(txtNome.getText());
        raca.setDescricao(txtDescricao.getText());
        raca.setBonus(txtBonus.getText());

        return raca;
    }

    private void preencherFormulario(Raca raca) {
        txtNome.setText(raca.getNome());
        txtDescricao.setText(raca.getDescricao());
        txtBonus.setText(raca.getBonus());
    }

    private void limparFormulario() {
        txtNome.clear();
        txtDescricao.clear();
        txtBonus.clear();

        tabela.getSelectionModel().clearSelection();
    }

    private void atualizarTabela() {
        tabela.getItems().setAll(controller.listar());
    }
}