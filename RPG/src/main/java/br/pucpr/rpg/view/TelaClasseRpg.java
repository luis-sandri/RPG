package br.pucpr.rpg.view;

import br.pucpr.rpg.controller.ClasseRpgController;
import br.pucpr.rpg.model.ClasseRpg;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TelaClasseRpg extends VBox {

    private ClasseRpgController controller;

    private TextField txtNome;
    private TextField txtDescricao;
    private TextField txtAtributoPrincipal;

    private TableView<ClasseRpg> tabela;

    public TelaClasseRpg(ClasseRpgController controller) {
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
                txtAtributoPrincipal,
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
        Label titulo = new Label("Cadastro de Classes");

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

        txtAtributoPrincipal = new TextField();
        txtAtributoPrincipal.setPromptText("Atributo Principal");
    }

    private void configurarTabela() {

        TableColumn<ClasseRpg, Integer> colId =
                new TableColumn<>("ID");
        colId.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        TableColumn<ClasseRpg, String> colNome =
                new TableColumn<>("Nome");
        colNome.setCellValueFactory(
                new PropertyValueFactory<>("nome"));

        TableColumn<ClasseRpg, String> colDescricao =
                new TableColumn<>("Descrição");
        colDescricao.setCellValueFactory(
                new PropertyValueFactory<>("descricao"));

        TableColumn<ClasseRpg, String> colAtributo =
                new TableColumn<>("Atributo Principal");
        colAtributo.setCellValueFactory(
                new PropertyValueFactory<>("atributoPrincipal"));

        tabela.getColumns().addAll(
                colId,
                colNome,
                colDescricao,
                colAtributo
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
        controller.cadastrar(criarClasseDoFormulario());
        atualizarTabela();
        limparFormulario();
    }

    private void editar() {
        ClasseRpg selecionado =
                tabela.getSelectionModel().getSelectedItem();

        if (selecionado != null) {
            ClasseRpg classe = criarClasseDoFormulario();
            classe.setId(selecionado.getId());

            controller.editar(classe);

            atualizarTabela();
            limparFormulario();
        }
    }

    private void excluir() {
        ClasseRpg selecionado =
                tabela.getSelectionModel().getSelectedItem();

        if (selecionado != null) {
            controller.excluir(selecionado);
            atualizarTabela();
            limparFormulario();
        }
    }

    private ClasseRpg criarClasseDoFormulario() {
        ClasseRpg classe = new ClasseRpg();

        classe.setNome(txtNome.getText());
        classe.setDescricao(txtDescricao.getText());
        classe.setAtributoPrincipal(
                txtAtributoPrincipal.getText());

        return classe;
    }

    private void preencherFormulario(ClasseRpg classe) {
        txtNome.setText(classe.getNome());
        txtDescricao.setText(classe.getDescricao());
        txtAtributoPrincipal.setText(
                classe.getAtributoPrincipal());
    }

    private void limparFormulario() {
        txtNome.clear();
        txtDescricao.clear();
        txtAtributoPrincipal.clear();

        tabela.getSelectionModel().clearSelection();
    }

    private void atualizarTabela() {
        tabela.getItems().setAll(controller.listar());
    }
}