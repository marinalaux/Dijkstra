package dijkstra;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Janela principal
 * 
 * @author Marina
 */
public class JanelaPrincipal extends Application {

    /** Painel principal */
    private BorderPane pane;
    /** Leitor das distâncias dos nodos */
    private LeitorDistancias leitor;
    /** Matriz de distâncias de todos os nodos */
    private TabelaDistancias tabelaDistancias;
    /** Algoritmo Dijkstra */
    private Dijkstra algoritmo;
    /** Nodo de origem */
    private ComboBox origem;
    /** Nodo de destino */
    private ComboBox destino;
    /** Resultado do algoritmo */
    private TextArea resultado;
    /** Botão para calcular a menor distância */
    private Button calcular;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        leitor = new LeitorDistancias();
        tabelaDistancias = leitor.lerDistancias();
        
        inicializaComponentes();
        inicializaListners();
        stage.setTitle("Algoritmo Dijkstra");
        stage.setMaximized(true);
        stage.setScene(new Scene(pane));
        stage.getIcons().add(new Image(Principal.class.getResourceAsStream("/icon.png")));
        stage.show();
    }

    /**
     * Inicializa os componentes da interface
     */
    private void inicializaComponentes() {
        pane = new BorderPane();
        pane.setTop(criaPainelParametros());
        pane.setCenter(criaPainelPrincipal());
    }
    
    /**
     * Inicializa listners
     */
    private void inicializaListners() {
        calcular.setOnAction((ActionEvent e) -> {
            if (origem.getValue() == destino.getValue()) {
                Alert alerta = new Alert(Alert.AlertType.WARNING,
                        "Selecione cidades diferentes como origem e destino!");
                alerta.showAndWait();
            } else {
                algoritmo = new Dijkstra();
                algoritmo.buscaCustoMinimo(tabelaDistancias, 
                                           origem.getSelectionModel().selectedIndexProperty().get(),
                                           destino.getSelectionModel().selectedIndexProperty().get());
                resultado.setText(algoritmo.getResultado());
            }
        });
    }

    /**
     * Cria painel principal da janela
     * 
     * @return Painel principal
     */
    private Node criaPainelPrincipal() {
        BorderPane painelPrincipal = new BorderPane();
        
        painelPrincipal.setStyle("-fx-background-color:#FFF; -fx-border-color:#CCC; -fx-border-width:0 1 0 0;");
        painelPrincipal.setPadding(new Insets(5));
        
        painelPrincipal.setCenter(resultado = new TextArea());
        resultado.setEditable(false);
        resultado.setStyle("-fx-font: 11 Consolas;");
        return painelPrincipal;
    }
    
    public static void run(String[] args) {
        launch(args);
    }

    private Node criaPainelParametros() {
        GridPane gridPar = new GridPane();
        gridPar.setHgap(15);
        gridPar.setVgap(5);
        gridPar.setPadding(new Insets(15, 15, 15, 15));
        
        Label labelOrigem = new Label("Origem");
        origem = new ComboBox();
        origem.getItems().addAll(tabelaDistancias.getNodos());
        origem.setValue(tabelaDistancias.getNodos()[0]);
        
        Label labelDestino = new Label("Destino");
        destino = new ComboBox();
        destino.getItems().addAll(tabelaDistancias.getNodos());
        destino.setValue(tabelaDistancias.getNodos()[0]);
        
        calcular = new Button("Calcular!",
                new ImageView(new Image(getClass().getResourceAsStream("/caminho.png"))));
        calcular.setStyle("-fx-font-size: 18;");
        
        GridPane.setConstraints(labelOrigem, 0, 0);
        GridPane.setConstraints(origem, 1, 0);
        GridPane.setConstraints(labelDestino, 0, 1);
        GridPane.setConstraints(destino, 1, 1);
        GridPane.setConstraints(calcular, 3, 0, 1, 2);
        
        gridPar.getChildren().add(labelOrigem);
        gridPar.getChildren().add(origem);
        gridPar.getChildren().add(labelDestino);
        gridPar.getChildren().add(destino);
        gridPar.getChildren().add(calcular);
        
        return gridPar;
    }
    
}
