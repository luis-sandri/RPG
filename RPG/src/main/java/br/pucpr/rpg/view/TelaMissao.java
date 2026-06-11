package br.pucpr.rpg.view;

import br.pucpr.rpg.controller.MissaoController;
import br.pucpr.rpg.model.Missao;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TelaMissao extends VBox {
    private MissaoController controller;
    private TextField txttitulo;
    private TextField txtdescricao;
    private TextField txtstatus;
    private TextField txtrecompensa;
    private TableView<Missao> tabela;

    public TelaMissao(MissaoController controller){
        this.controller = controller;

        configurarTela();

        Label titulo = criarTitulo();
        criarCampos();

        Button btnCadastrar = new Button("Cadastrar");
        Button btnEditar = new Button("Editar");
        Button btnExcluir = new Button("Excluir");
        Button btnLimpar = new Button("Limpar");
        Button btnAtualizar = new Button("Atualizar");

        tabela = new TableView<Missao>();
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
                txttitulo,
                txtdescricao,
                txtstatus,
                txtrecompensa,
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
        Label titulo = new Label("Cadastro de Missões");
        titulo.setStyle("""
            -fx-text-fill: white;
            -fx-font-size: 22px;
            -fx-font-weight: bold;
        """);

        return titulo;
    }

    private void criarCampos(){
        txttitulo = new TextField();
        txttitulo.setPromptText("Titulo");
        txtdescricao = new TextField();
        txtdescricao.setPromptText("Descrição");
        txtstatus = new TextField();
        txtstatus.setPromptText("Status");
        txtrecompensa = new TextField();
        txtrecompensa.setPromptText("Recompensa");
    }
    private void configurarTabela() {
        TableColumn<Missao, Integer> colid = new TableColumn<Missao, Integer>("Id");
        colid.setCellValueFactory(new PropertyValueFactory<Missao, Integer>("id"));

        TableColumn<Missao, String> coltitulo = new TableColumn<Missao, String>("Titulo");
        coltitulo.setCellValueFactory(new PropertyValueFactory<Missao, String>("titulo"));

        TableColumn<Missao, String> coldescricao = new TableColumn<Missao, String>("Descrição");
        coldescricao.setCellValueFactory(new PropertyValueFactory<Missao, String>("descricao"));

        TableColumn<Missao, String> colstatus = new TableColumn<Missao, String>("Status");
        colstatus.setCellValueFactory(new PropertyValueFactory<Missao, String>("status"));

        TableColumn<Missao, String> colrecompensa = new TableColumn<Missao, String>("Recompensa");
        colrecompensa.setCellValueFactory(new PropertyValueFactory<Missao, String>("recompensa"));


        tabela.getColumns().addAll(colid,coltitulo, coldescricao,colstatus,colrecompensa);


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
        Missao missao = criarMissaoDoFormulario();

        controller.cadastrar(missao);
        atualizarTabela();
        limparFormulario();
    }
    private void editar() {
        Missao selecionado = tabela.getSelectionModel().getSelectedItem();

        if (selecionado != null) {
            Missao missao = criarMissaoDoFormulario();
            missao.setId(selecionado.getId());

            controller.editar(missao);

            atualizarTabela();
            limparFormulario();
        }
    }

    private void excluir() {
        Missao selecionado = tabela.getSelectionModel().getSelectedItem();

        controller.excluir(selecionado);
        atualizarTabela();
        limparFormulario();
    }

    private Missao criarMissaoDoFormulario() {
        Missao missao = new Missao();

        missao.setDescricao(txtdescricao.getText());
        missao.setTitulo(txttitulo.getText());
        missao.setStatus(txtstatus.getText());
        missao.setRecompensa(txtrecompensa.getText());


        return missao;
    }

    private void preencherFormulario(Missao missao) {
        txttitulo.setText(missao.getTitulo());
        txtdescricao.setText(String.valueOf(missao.getDescricao()));
        txtstatus.setText(String.valueOf(missao.getStatus()));
        txtrecompensa.setText(String.valueOf(missao.getRecompensa()));
    }

    private void limparFormulario() {
        txttitulo.clear();
        txtdescricao.clear();
        txtstatus.clear();
        txtrecompensa.clear();
        tabela.getSelectionModel().clearSelection();
    }

    private void atualizarTabela() {
        tabela.getItems().setAll(controller.listar());
    }
}

