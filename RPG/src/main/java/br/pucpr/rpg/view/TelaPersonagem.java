package br.pucpr.rpg.view;

import br.pucpr.rpg.controller.PersonagemController;
import br.pucpr.rpg.model.Personagem;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TelaPersonagem extends VBox {

    private PersonagemController controller;
    private TextField txtNome;
    private TextField txtNivel;
    private TextField txtVida;
    private TextField txtMana;
    private TableView<Personagem> tabela;

    public TelaPersonagem(PersonagemController controller) {
        this.controller = controller;

        configurarTela();

        Label titulo = criarTitulo();
        criarCampos();

        Button btnCadastrar = new Button("Cadastrar");
        Button btnEditar = new Button("Editar");
        Button btnExcluir = new Button("Excluir");
        Button btnLimpar = new Button("Limpar");
        Button btnAtualizar = new Button("Atualizar");

        tabela = new TableView<Personagem>();
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
                txtNivel,
                txtVida,
                txtMana,
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
        Label titulo = new Label("Cadastro de Personagens");
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

        txtNivel = new TextField();
        txtNivel.setPromptText("Nível");

        txtVida = new TextField();
        txtVida.setPromptText("Vida");

        txtMana = new TextField();
        txtMana.setPromptText("Mana");
    }

    private void configurarTabela() {
        TableColumn<Personagem, Integer> colId = new TableColumn<Personagem, Integer>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<Personagem, Integer>("id"));

        TableColumn<Personagem, String> colNome = new TableColumn<Personagem, String>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<Personagem, String>("nome"));

        TableColumn<Personagem, Integer> colNivel = new TableColumn<Personagem, Integer>("Nível");
        colNivel.setCellValueFactory(new PropertyValueFactory<Personagem, Integer>("nivel"));

        TableColumn<Personagem, Integer> colVida = new TableColumn<Personagem, Integer>("Vida");
        colVida.setCellValueFactory(new PropertyValueFactory<Personagem, Integer>("vida"));

        TableColumn<Personagem, Integer> colMana = new TableColumn<Personagem, Integer>("Mana");
        colMana.setCellValueFactory(new PropertyValueFactory<Personagem, Integer>("mana"));

        tabela.getColumns().addAll(colId, colNome, colNivel, colVida, colMana);
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
        Personagem personagem = criarPersonagemDoFormulario();

        controller.cadastrar(personagem);
        atualizarTabela();
        limparFormulario();
    }

    private void editar() {
        Personagem selecionado = tabela.getSelectionModel().getSelectedItem();

        if (selecionado != null) {
            Personagem personagem = criarPersonagemDoFormulario();
            personagem.setId(selecionado.getId());

            controller.editar(personagem);

            atualizarTabela();
            limparFormulario();
        }
    }

    private void excluir() {
        Personagem selecionado = tabela.getSelectionModel().getSelectedItem();

        controller.excluir(selecionado);
        atualizarTabela();
        limparFormulario();
    }

    private Personagem criarPersonagemDoFormulario() {
        Personagem personagem = new Personagem();

        personagem.setNome(txtNome.getText());
        personagem.setNivel(Integer.parseInt(txtNivel.getText()));
        personagem.setVida(Integer.parseInt(txtVida.getText()));
        personagem.setMana(Integer.parseInt(txtMana.getText()));

        return personagem;
    }

    private void preencherFormulario(Personagem personagem) {
        txtNome.setText(personagem.getNome());
        txtNivel.setText(String.valueOf(personagem.getNivel()));
        txtVida.setText(String.valueOf(personagem.getVida()));
        txtMana.setText(String.valueOf(personagem.getMana()));
    }

    private void limparFormulario() {
        txtNome.clear();
        txtNivel.clear();
        txtVida.clear();
        txtMana.clear();
        tabela.getSelectionModel().clearSelection();
    }

    private void atualizarTabela() {
        tabela.getItems().setAll(controller.listar());
    }
}
