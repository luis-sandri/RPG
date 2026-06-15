package br.pucpr.rpg.view;

import br.pucpr.rpg.controller.NpcController;
import br.pucpr.rpg.model.Npc;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TelaNpc extends VBox {
    private NpcController controller;
    private TextField txtNome;
    private TextField txtTipo;
    private TextField txtDescricao;
    private TextField txtNivel;
    private TableView<Npc> tabela;

    public TelaNpc(NpcController controller) {
        this.controller = controller;

        configurarTela();

        Label titulo = criarTitulo();
        criarCampos();

        Button btnCadastrar = new Button("Cadastrar");
        Button btnEditar = new Button("Editar");
        Button btnExcluir = new Button("Excluir");
        Button btnLimpar = new Button("Limpar");
        Button btnAtualizar = new Button("Atualizar");

        tabela = new TableView<Npc>();
        configurarTabela();
        configurarSelecaoTabela();
        atualizarTabela();

        btnCadastrar.setOnAction(event -> cadastrar());
        btnEditar.setOnAction(event -> editar());
        btnExcluir.setOnAction(event -> excluir());
        btnLimpar.setOnAction(event -> limparFormulario());
        btnAtualizar.setOnAction(event -> atualizarTabela());

        HBox botoes = new HBox(10, btnCadastrar, btnEditar, btnExcluir, btnLimpar, btnAtualizar);

        getChildren().addAll(
                titulo,
                txtNome,
                txtTipo,
                txtDescricao,
                txtNivel,
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
        Label titulo = new Label("Cadastro de NPCs");
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

        txtTipo = new TextField();
        txtTipo.setPromptText("Tipo");

        txtDescricao = new TextField();
        txtDescricao.setPromptText("Descricao");

        txtNivel = new TextField();
        txtNivel.setPromptText("Nivel");
    }

    private void configurarTabela() {
        TableColumn<Npc, Integer> colId = new TableColumn<Npc, Integer>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<Npc, Integer>("id"));

        TableColumn<Npc, String> colNome = new TableColumn<Npc, String>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<Npc, String>("nome"));

        TableColumn<Npc, String> colTipo = new TableColumn<Npc, String>("Tipo");
        colTipo.setCellValueFactory(new PropertyValueFactory<Npc, String>("tipo"));

        TableColumn<Npc, String> colDescricao = new TableColumn<Npc, String>("Descricao");
        colDescricao.setCellValueFactory(new PropertyValueFactory<Npc, String>("descricao"));

        TableColumn<Npc, Integer> colNivel = new TableColumn<Npc, Integer>("Nivel");
        colNivel.setCellValueFactory(new PropertyValueFactory<Npc, Integer>("nivel"));

        tabela.getColumns().addAll(colId, colNome, colTipo, colDescricao, colNivel);
    }

    private void configurarSelecaoTabela() {
        tabela.getSelectionModel().selectedItemProperty().addListener(
                (obs, antigo, selecionado) -> {
                    if (selecionado != null) {
                        preencherFormulario(selecionado);
                    }
                }
        );
    }

    private void cadastrar() {
        Npc npc = criarNpcDoFormulario();

        controller.cadastrar(npc);
        atualizarTabela();
        limparFormulario();
    }

    private void editar() {
        Npc selecionado = tabela.getSelectionModel().getSelectedItem();

        if (selecionado != null) {
            Npc npc = criarNpcDoFormulario();
            npc.setId(selecionado.getId());

            controller.editar(npc);

            atualizarTabela();
            limparFormulario();
        }
    }

    private void excluir() {
        Npc selecionado = tabela.getSelectionModel().getSelectedItem();

        controller.excluir(selecionado);
        atualizarTabela();
        limparFormulario();
    }

    private Npc criarNpcDoFormulario() {
        Npc npc = new Npc();

        npc.setNome(txtNome.getText());
        npc.setTipo(txtTipo.getText());
        npc.setDescricao(txtDescricao.getText());
        npc.setNivel(Integer.parseInt(txtNivel.getText()));

        return npc;
    }

    private void preencherFormulario(Npc npc) {
        txtNome.setText(npc.getNome());
        txtTipo.setText(npc.getTipo());
        txtDescricao.setText(npc.getDescricao());
        txtNivel.setText(String.valueOf(npc.getNivel()));
    }

    private void limparFormulario() {
        txtNome.clear();
        txtTipo.clear();
        txtDescricao.clear();
        txtNivel.clear();
        tabela.getSelectionModel().clearSelection();
    }

    private void atualizarTabela() {
        tabela.getItems().setAll(controller.listar());
    }
}
