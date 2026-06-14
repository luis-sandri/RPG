package br.pucpr.rpg.view;

import br.pucpr.rpg.controller.ItemController;
import br.pucpr.rpg.model.Item;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TelaItem extends VBox {

    private ItemController controller;

    private TextField txtNome;
    private TextField txtTipo;
    private TextField txtRaridade;
    private TextField txtValor;

    private TableView<Item> tabela;

    public TelaItem(ItemController controller) {
        this.controller = controller;

        configurarTela();

        Label titulo = criarTitulo();
        criarCampos();

        Button btnCadastrar = new Button("Cadastrar");
        Button btnEditar = new Button("Editar");
        Button btnExcluir = new Button("Excluir");
        Button btnLimpar = new Button("Limpar");
        Button btnAtualizar = new Button("Atualizar");

        tabela = new TableView<Item>();
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
                txtRaridade,
                txtValor,
                botoes,
                tabela);
    }

    private void configurarTela() {
        setSpacing(12);
        setStyle("""
                    -fx-padding: 24px;
                    -fx-background-color: #1e1e2f;
                """);
    }

    private Label criarTitulo() {
        Label titulo = new Label("Cadastro de Itens");
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
        txtTipo.setPromptText("Tipo (ex: Arma, Armadura, Poção)");

        txtRaridade = new TextField();
        txtRaridade.setPromptText("Raridade (ex: Comum, Raro, Épico)");

        txtValor = new TextField();
        txtValor.setPromptText("Valor (Ouro)");
    }

    private void configurarTabela() {
        TableColumn<Item, Integer> colId = new TableColumn<Item, Integer>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<Item, Integer>("id"));

        TableColumn<Item, String> colNome = new TableColumn<Item, String>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<Item, String>("nome"));

        TableColumn<Item, String> colTipo = new TableColumn<Item, String>("Tipo");
        colTipo.setCellValueFactory(new PropertyValueFactory<Item, String>("tipo"));

        TableColumn<Item, String> colRaridade = new TableColumn<Item, String>("Raridade");
        colRaridade.setCellValueFactory(new PropertyValueFactory<Item, String>("raridade"));

        TableColumn<Item, Integer> colValor = new TableColumn<Item, Integer>("Valor");
        colValor.setCellValueFactory(new PropertyValueFactory<Item, Integer>("valor"));

        tabela.getColumns().addAll(colId, colNome, colTipo, colRaridade, colValor);
    }

    private void configurarSelecaoTabela() {
        tabela.getSelectionModel().selectedItemProperty().addListener(
                (obs, antigo, selecionado) -> {
                    if (selecionado != null) {
                        preencherFormulario(selecionado);
                    }
                });
    }

    private void cadastrar() {
        Item item = criarItemDoFormulario();

        controller.cadastrar(item);
        atualizarTabela();
        limparFormulario();
    }

    private void editar() {
        Item selecionado = tabela.getSelectionModel().getSelectedItem();

        if (selecionado != null) {
            Item item = criarItemDoFormulario();
            item.setId(selecionado.getId());

            controller.editar(item);

            atualizarTabela();
            limparFormulario();
        }
    }

    private void excluir() {
        Item selecionado = tabela.getSelectionModel().getSelectedItem();

        if (selecionado != null) {
            controller.excluir(selecionado);
            atualizarTabela();
            limparFormulario();
        }
    }

    private Item criarItemDoFormulario() {
        Item item = new Item();

        item.setNome(txtNome.getText());
        item.setTipo(txtTipo.getText());
        item.setRaridade(txtRaridade.getText());

        try {
            item.setValor(Integer.parseInt(txtValor.getText()));
        } catch (NumberFormatException e) {
            item.setValor(0);
        }

        return item;
    }

    private void preencherFormulario(Item item) {
        txtNome.setText(item.getNome());
        txtTipo.setText(item.getTipo());
        txtRaridade.setText(item.getRaridade());
        txtValor.setText(String.valueOf(item.getValor()));
    }

    private void limparFormulario() {
        txtNome.clear();
        txtTipo.clear();
        txtRaridade.clear();
        txtValor.clear();
        tabela.getSelectionModel().clearSelection();
    }

    private void atualizarTabela() {
        tabela.getItems().setAll(controller.listar());
    }
}
