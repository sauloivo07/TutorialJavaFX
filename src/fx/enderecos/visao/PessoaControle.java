/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.enderecos.visao;

import fx.enderecos.modelo.Person;
import java.util.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;



/**
 *
 * @author Saulo
 */
public class PessoaControle {
   @FXML
    private TableView<Person> pessoaTabela;
    @FXML
    private TableColumn<Person, String> nomeColumn;
    @FXML
    private TableColumn<Person, String> sobrenomeColumn;

    @FXML
    private Label nomeLabel;
    @FXML
    private Label sobrenomeLabel;
    @FXML
    private Label ruaLabel;
    @FXML
    private Label CEPLabel;
    @FXML
    private Label cidadeLabel;
    @FXML
    private Label nascimentoLabel;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * O construtor.
     * O construtor é chamado antes do método inicialize().
     */
    public PessoaControle() {
    }
    
    //Mostra os detalhes da Pessoa nos Labels
    public void mostraDetalhesPessoa(Person pessoa){
        if(pessoa != null){
            nomeLabel.setText(pessoa.getFirstName());
            sobrenomeLabel.setText(pessoa.getLastName());
            ruaLabel.setText(pessoa.getStreet());
            cidadeLabel.setText(pessoa.getCity());
            CEPLabel.setText(Integer.toString(pessoa.getPostalCode()));
        }else{
            nomeLabel.setText("");
            sobrenomeLabel.setText("");
            ruaLabel.setText("");
            cidadeLabel.setText("");
            CEPLabel.setText("");
        }
    
    }
    
    
    

    /**
     * Inicializa a classe controller. Este método é chamado automaticamente
     *  após o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
        // Inicializa a tablea de pessoa com duas colunas.
        nomeColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        sobrenomeColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        
        mostraDetalhesPessoa(null);
        
        //Quando detecta mudanças na tabela adiciona os dados da celula selecionada nos labels
        
        pessoaTabela.getSelectionModel().selectedItemProperty().addListener(
                (Observable, oldValue, newValue) -> mostraDetalhesPessoa(newValue));
    }
    
    @FXML
    public void handleDeletaPessoa(){
        int indexSelecionado = pessoaTabela.getSelectionModel().getSelectedIndex();
        if(indexSelecionado >= 0){
            pessoaTabela.getItems().remove(indexSelecionado);
        }else{
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Nenhuma Seleção");
            alerta.setHeaderText("Nenhuma pessoa selecionada");
            alerta.setContentText("Por favor selecione uma pessoa na tabela");
            alerta.showAndWait();
        }
    }
    
    @FXML
    public void handleNovaPessoa(){
        Person pessoaTemp = new Person();
        boolean okClicked = mainApp.mostraDialogEditarPessoa(pessoaTemp);
        if(okClicked){
            mainApp.getPersonData().add(pessoaTemp);
        }
    }
    
    @FXML
    public void handleEditarPessoa(){
        Person pessoaSelecionada = pessoaTabela.getSelectionModel().getSelectedItem();
        if (pessoaSelecionada != null){
            boolean okClicked = mainApp.mostraDialogEditarPessoa(pessoaSelecionada);
            if(okClicked){
                mostraDetalhesPessoa(pessoaSelecionada);
            }
        }else{
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Nenhuma seleção");
            alerta.setHeaderText("Nenhuma pessoa selecionada");
            alerta.setContentText("Por favor, selecione uma pessoa na tabela");
            alerta.showAndWait();
        }
    }

    /**
     * É chamado pela aplicação principal para dar uma referência de volta a si mesmo.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Adiciona os dados da observable list na tabela
        pessoaTabela.setItems(mainApp.getPersonData());
    }
}
