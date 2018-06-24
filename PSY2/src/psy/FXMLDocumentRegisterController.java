/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author LYZ
 */
public class FXMLDocumentRegisterController implements Initializable {

    @FXML
    private TextField textId;
    @FXML
    private PasswordField textPwd;
    @FXML
    private PasswordField textRePwd;
    @FXML
    private TextField textClass;
    @FXML
    private TextField textQQ;
    @FXML
    private TextField textTel;
    @FXML
    private Button buttonRegister;
    @FXML
    private Button buttonBackLogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonRegisterAction(ActionEvent event) {
        
    }

    @FXML
    private void handleButtonBackLoginAction(ActionEvent event) {
        Register.stage.close();
        Login.stage.show();
    }
    
}
