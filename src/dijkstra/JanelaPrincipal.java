package dijkstra;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Janela principal
 * 
 * @author Marina
 */
public class JanelaPrincipal extends Application {

    private BorderPane pane;
    
    @Override
    public void start(Stage stage) throws Exception {
        initGui();
        stage.setTitle("Algoritmo Dijkstra");
        stage.setScene(new Scene(pane));
        stage.show();
    }

    /**
     * Inicializa os componentes da interface
     */
    private void initGui() {
        pane = new BorderPane();
        pane.setCenter(criaPainelPrincipal());
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
        
        return painelPrincipal;
    }
    
    public static void run(String[] args) {
        launch(args);
    }
    
}
