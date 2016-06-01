/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.enderecos.visao;

import fx.enderecos.modelo.Person;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Saulo
 */
public class PessoaEditarDialogControle extends Application {
    
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtSobrenome;
    @FXML
    private TextField txtRua;
    @FXML
    private TextField txtCidade;
    @FXML
    private TextField txtCep;
    @FXML
    private TextField txtNascimento;
    
    private Stage dialogStage;
    private Person Pessoa;
    private boolean okClick = false;
    
    @FXML
    public void initialize(){
    
    }
    
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }
    
    public void setPessoa(Person pessoa){
        this.Pessoa = pessoa;
        
        txtNome.setText(pessoa.getFirstName());
        txtSobrenome.setText(pessoa.getLastName());
        txtRua.setText(pessoa.getStreet());
        txtCep.setText(Integer.toString(pessoa.getPostalCode()));
        txtCidade.setText(pessoa.getCity());
    }
    
    //Retorna se clicar em OK.
    public boolean isOkClicked(){
        return okClick;
    }
    
    //Quando o usuario clica em OK
    private void handleOk(){
        if(isInputValid()){
            Pessoa.setFirstName(txtNome.getText());
            Pessoa.setLastName(txtSobrenome.getText());
            Pessoa.setStreet(txtRua.getText());
            Pessoa.setPostalCode(Integer.parseInt(txtCep.getText()));
            Pessoa.setCity(txtCidade.getText());
            //Pessoa.setBirthday(DateUtil.parse(birthdayField.getText()));
            
            okClick = true;
            dialogStage.close();
        }
    }
    
    //Validar entrada do usuario nos campos textos.
    private boolean isInputValid() {
        String menssagemErro = "";
        
        if (txtNome.getText() == null || txtNome.getText().length() == 0) {
            menssagemErro += "Nome inválido!\n"; 
        }
        if (txtSobrenome.getText() == null || txtSobrenome.getText().length() == 0) {
            menssagemErro += "Sobrenome inválido!\n"; 
        }
        if (txtRua.getText() == null || txtRua.getText().length() == 0) {
            menssagemErro += "Rua inválida!\n"; 
        }

        if (txtCep.getText() == null || txtCep.getText().length() == 0) {
            menssagemErro += "Código Postal inválido!\n"; 
        } else {
            // tenta converter o código postal em um int.
            try {
                Integer.parseInt(txtCep.getText());
            } catch (NumberFormatException e) {
                menssagemErro += "Código Postal inválido (deve ser um inteiro)!\n"; 
            }
        }

        if (txtCidade.getText() == null || txtCidade.getText().length() == 0) {
            menssagemErro += "Cidade inválida!\n"; 
        }
        
        if(menssagemErro.length() == 0){
            return true;
        } else{
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Campos Invalidos");
            alerta.setHeaderText("Por favor, corrija os campos inválidos.");
            alerta.setContentText(menssagemErro);
            
            alerta.showAndWait();
            
            return false;
        }
    }
    
    
    
    
    
    @Override
    public void start(Stage primaryStage) {
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    
    
}
