package br.pucpr.rpg.view;

import br.pucpr.rpg.controller.JogadorController;
import br.pucpr.rpg.model.Jogador;
import br.pucpr.rpg.model.Personagem;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class TelaJogador extends VBox {

    private JogadorController controller;
    private TextField txtNome;
    private TextField txtApelido;
    private TextField txtEmail;
    private TextField txtPersonagens;
    private TableView<Jogador> tabela;

    public TelaJogador(JogadorController controller) {
        this.controller = controller;

        configurarTela();

        Label titulo = criarTitulo();
        criarCampos();

        Button btnCadastrar = new Button("Cadastrar");
        Button btnEditar = new Button("Editar");
        Button btnExcluir = new Button("Excluir");
        Button btnLimpar = new Button("Limpar");
        Button btnAtualizar = new Button("Atualizar");

        tabela = new TableView<Jogador>();
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
                txtApelido,
                txtEmail,
                txtPersonagens,
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
        Label titulo = new Label("Cadastro de Jogadores");
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

        txtEmail = new TextField();
        txtEmail.setPromptText("Email");

        txtApelido = new TextField();
        txtApelido.setPromptText("Apelido");

        txtPersonagens = new TextField();
        txtPersonagens.setPromptText("Personagens");
    }

    private void configurarTabela() {
        TableColumn<Jogador, Integer> colId = new TableColumn<Jogador, Integer>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<Jogador, Integer>("id"));

        TableColumn<Jogador, String> colNome = new TableColumn<Jogador, String>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<Jogador, String>("nome"));

        TableColumn<Jogador, String> colEmail = new TableColumn<Jogador, String>("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory<Jogador, String>("email"));

        TableColumn<Jogador, String> colApelido = new TableColumn<Jogador, String>("Apelido");
        colApelido.setCellValueFactory(new PropertyValueFactory<Jogador, String>("apelido"));

        TableColumn<Jogador, String> colPersonagens = new TableColumn<Jogador, String>("Personagens");
        colPersonagens.setCellValueFactory(cellData ->
                new SimpleStringProperty(formatarPersonagens(cellData.getValue().getPersonagens())));

        tabela.getColumns().addAll(colId, colNome, colEmail, colApelido, colPersonagens);
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
        Jogador jogador = criarJogadorDoFormulario();

        controller.cadastrar(jogador);
        atualizarTabela();
        limparFormulario();
    }

    private void editar() {
        Jogador selecionado = tabela.getSelectionModel().getSelectedItem();

        if (selecionado != null) {
            Jogador jogador = criarJogadorDoFormulario();
            jogador.setId(selecionado.getId());

            controller.editar(jogador);

            atualizarTabela();
            limparFormulario();
        }
    }

    private void excluir() {
        Jogador selecionado = tabela.getSelectionModel().getSelectedItem();

        controller.excluir(selecionado);
        atualizarTabela();
        limparFormulario();
    }

    private Jogador criarJogadorDoFormulario() {
        Jogador jogador = new Jogador();

        jogador.setNome(txtNome.getText());
        jogador.setEmail(txtEmail.getText());
        jogador.setApelido(txtApelido.getText());
        jogador.setPersonagens(criarPersonagensDoTexto(txtPersonagens.getText()));

        return jogador;
    }

    private void preencherFormulario(Jogador jogador) {
        txtNome.setText(jogador.getNome());
        txtEmail.setText(String.valueOf(jogador.getEmail()));
        txtApelido.setText(String.valueOf(jogador.getApelido()));
        txtPersonagens.setText(formatarPersonagens(jogador.getPersonagens()));
    }

    private void limparFormulario() {
        txtNome.clear();
        txtEmail.clear();
        txtApelido.clear();
        txtPersonagens.clear();
        tabela.getSelectionModel().clearSelection();
    }

    private void atualizarTabela() {
        tabela.getItems().setAll(controller.listar());
    }

    private ArrayList<Personagem> criarPersonagensDoTexto(String texto) {
        ArrayList<Personagem> personagens = new ArrayList<Personagem>();

        if (texto == null || texto.trim().isEmpty()) {
            return personagens;
        }

        String[] nomes = texto.split(",");

        for (int i = 0; i < nomes.length; i++) {
            String nome = nomes[i].trim();

            if (!nome.isEmpty()) {
                Personagem personagem = new Personagem();
                personagem.setNome(nome);
                personagens.add(personagem);
            }
        }

        return personagens;
    }

    private String formatarPersonagens(ArrayList<Personagem> personagens) {
        if (personagens == null || personagens.isEmpty()) {
            return "";
        }

        StringBuilder texto = new StringBuilder();

        for (int i = 0; i < personagens.size(); i++) {
            Personagem personagem = personagens.get(i);

            if (personagem != null && personagem.getNome() != null && !personagem.getNome().trim().isEmpty()) {
                if (texto.length() > 0) {
                    texto.append(", ");
                }

                texto.append(personagem.getNome());
            }
        }

        return texto.toString();
    }
}
