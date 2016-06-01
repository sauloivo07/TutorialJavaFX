package fx.enderecos.visao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fx.enderecos.modelo.Person;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Saulo
 */
public class MainApp extends Application {
    
    private Stage primaryStage;
    private BorderPane FormPrincipal;
    
    @Override
    public void start(Stage primaryStage) {
       this.primaryStage = primaryStage;
       primaryStage.setTitle("Aplicativo de Endereços");
       
       primaryStage.setResizable(false);
       
       initLayoutPrincipal();
       mostraPessoa();
    }
    
    public void initLayoutPrincipal(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("FormPrincipal.fxml"));
            FormPrincipal = (BorderPane) loader.load();
            
            Scene cena = new Scene(FormPrincipal);
            primaryStage.setScene(cena);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void mostraPessoa(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("Pessoa.fxml"));
            AnchorPane pessoa = loader.load();
            
            FormPrincipal.setCenter(pessoa);
            
            PessoaControle controle = loader.getController();
            controle.setMainApp(this);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean mostraDialogEditarPessoa(Person pessoa){
        try {
            //Carrega o arquivo FXML e cria um novo Stage para a janela popup
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("PessoaEditarDialog.fxml"));
            AnchorPane page = loader.load();
            
            //cria o stage dialogStage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Pessoa");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene cena = new Scene(page);
            dialogStage.setScene(cena);
            
            // Define a pessoa no controller.
        PessoaEditarDialogControle controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setPessoa(pessoa);

        // Mostra a janela e espera até o usuário fechar.
        dialogStage.showAndWait();

        return controller.isOkClicked();
        
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }
    
    /**
     * Os dados como uma observable list de Persons.
     */
    private ObservableList<Person> personData = FXCollections.observableArrayList();

    /**
     * Construtor
     */
    public MainApp() {
        // Add some sample data
        personData.add(new Person("Hans", "Muster"));
        personData.add(new Person("Ruth", "Mueller"));
        personData.add(new Person("Heinz", "Kurz"));
        personData.add(new Person("Cornelia", "Meier"));
        personData.add(new Person("Werner", "Meyer"));
        personData.add(new Person("Lydia", "Kunz"));
        personData.add(new Person("Anna", "Best"));
        personData.add(new Person("Stefan", "Meier"));
        personData.add(new Person("Martin", "Mueller"));
    }

    /**
     * Retorna os dados como uma observable list de Persons. 
     * @return
     */
    public ObservableList<Person> getPersonData() {
        return personData;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
