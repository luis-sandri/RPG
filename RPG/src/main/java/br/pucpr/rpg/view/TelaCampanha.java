package br.pucpr.rpg.view;

import br.pucpr.rpg.controller.CampanhaController;
import br.pucpr.rpg.model.Campanha;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TelaCampanha extends VBox {

    private CampanhaController controller;
    
    // ATRIBUTOS CORRIGIDOS PARA CAMPANHA
    private TextField txtNome;
    private TextField txtDescricao;
    private TextField txtNivelMinimo;
    private TextField txtStatus;
    
    private TableView<Campanha> tabela;

    public TelaCampanha(CampanhaController controller) {
        this.controller = controller;

        configurarTela();

        Label titulo = criarTitulo();
        criarCampos();

        Button btnCadastrar = new Button("Cadastrar");
        Button btnEditar = new Button("Editar");
        Button btnExcluir = new Button("Excluir");
        Button btnLimpar = new Button("Limpar");
        Button btnAtualizar = new Button("Atualizar");

        tabela = new TableView<Campanha>();
        configurarTabela();
        configurarSelecaoTabela();
        atualizarTabela();

        btnCadastrar.setOnAction(event -> cadastrar());
        btnEditar.setOnAction(event -> editar());
        btnExcluir.setOnAction(event -> excluir());
        btnLimpar.setOnAction(event -> limparFormulario());
        btnAtualizar.setOnAction(event -> atualizarTabela());

        HBox botoes = new HBox(10, btnCadastrar, btnEditar, btnExcluir, btnLimpar, btnAtualizar);

        // ATRIBUTOS ATUALIZADOS AQUI NA TELA
        getChildren().addAll(
                titulo,
                txtNome,
                txtDescricao,
                txtNivelMinimo,
                txtStatus,
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
        Label titulo = new Label("Cadastro de Campanhas");
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

        txtNivelMinimo = new TextField();
        txtNivelMinimo.setPromptText("Nível Mínimo");

        txtStatus = new TextField();
        txtStatus.setPromptText("Status");
    }

    private void configurarTabela() {
        TableColumn<Campanha, Integer> colId = new TableColumn<Campanha, Integer>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<Campanha, Integer>("id"));

        TableColumn<Campanha, String> colNome = new TableColumn<Campanha, String>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<Campanha, String>("nome"));

        TableColumn<Campanha, String> colDescricao = new TableColumn<Campanha, String>("Descrição");
        colDescricao.setCellValueFactory(new PropertyValueFactory<Campanha, String>("descricao"));

        TableColumn<Campanha, Integer> colNivelMinimo = new TableColumn<Campanha, Integer>("Nível Mínimo");
        colNivelMinimo.setCellValueFactory(new PropertyValueFactory<Campanha, Integer>("nivelMinimo"));

        TableColumn<Campanha, String> colStatus = new TableColumn<Campanha, String>("Status");
        colStatus.setCellValueFactory(new PropertyValueFactory<Campanha, String>("status"));

        tabela.getColumns().addAll(colId, colNome, colDescricao, colNivelMinimo, colStatus);
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
        Campanha campanha = criarCampanhaDoFormulario();

        controller.cadastrar(campanha);
        atualizarTabela();
        limparFormulario();
    }

    private void editar() {
        Campanha selecionado = tabela.getSelectionModel().getSelectedItem();

        if (selecionado != null) {
            Campanha campanha = criarCampanhaDoFormulario();
            campanha.setId(selecionado.getId());

            controller.editar(campanha);

            atualizarTabela();
            limparFormulario();
        }
    }

    private void excluir() {
        Campanha selecionado = tabela.getSelectionModel().getSelectedItem();

        if (selecionado != null) {
            controller.excluir(selecionado);
            atualizarTabela();
            limparFormulario();
        }
    }

    private Campanha criarCampanhaDoFormulario() {
        Campanha campanha = new Campanha();

        campanha.setNome(txtNome.getText());
        campanha.setDescricao(txtDescricao.getText());
        
        try {
            campanha.setNivelMinimo(Integer.parseInt(txtNivelMinimo.getText()));
        } catch (NumberFormatException e) {
            campanha.setNivelMinimo(0);
        }
        
        campanha.setStatus(txtStatus.getText());

        return campanha;
    }

    private void preencherFormulario(Campanha campanha) {
        txtNome.setText(campanha.getNome());
        txtDescricao.setText(campanha.getDescricao());
        txtNivelMinimo.setText(String.valueOf(campanha.getNivelMinimo()));
        txtStatus.setText(campanha.getStatus());
    }

    private void limparFormulario() {
        txtNome.clear();
        txtDescricao.clear();
        txtNivelMinimo.clear();
        txtStatus.clear();
        tabela.getSelectionModel().clearSelection();
    }

    private void atualizarTabela() {
        tabela.getItems().setAll(controller.listar());
    }
}
