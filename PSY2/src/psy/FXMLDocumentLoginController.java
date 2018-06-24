/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import psy.admin.AdminMain;
import psy.bean.Admin;
import psy.dao.AdminDao;
import psy.dao.StudentDao;

/**
 *
 * @author LYZ
 */
public class FXMLDocumentLoginController implements Initializable {

    @FXML
    private TextField textId;
    @FXML
    private TextField textPwd;
    @FXML
    private Label labelTipId;
    @FXML
    private Label labelTipPwd;
    @FXML
    private Label labelTipLogin;
    @FXML
    private ChoiceBox choiceLogin;
    @FXML
    private Button buttonLogin;
    @FXML
    private Font x1;
    @FXML
    private Button buttonGoRegister;

    /*
    *登录按钮点击事件
     */
    @FXML
    private void handleButtonLoginAction(ActionEvent event) {
        initLabel();
        //System.out.println("You clicked me!");
        String id = textId.getText();
        String pwd = textPwd.getText();

        if (!checkBeforeLogin(id, pwd)) {
            return;
        }

        int loginState = 0;
        final int type; //标记登录者类型，管理员为0，学生为1
        if (choiceLogin.getValue().equals("管理员")) {
            loginState = AdminDao.login(id, pwd);
            type = 0;
        } else {
            loginState = StudentDao.login(id, pwd);
            type = 1;
        }

        switch (loginState) {
            case 0:
                labelTipLogin.setText("用户不存在！");
                break;
            case 1:
                labelTipLogin.setText("用户名或者密码错误！");
                break;
            case 2:
                labelTipLogin.setText("登录成功！");
                Platform.runLater(() -> {
                    try {
                        if(type==0){
                            Admin admin = AdminDao.findById(id);
                            AdminMain main = AdminMain.getSingleton(admin);
                            main.start(new Stage());
                            Login.stage.close();
                        }
                        else if(type==1){
                            Main main = new Main(textId.getText(),type);
                            if (Main.stage == null) {
                                main.start(new Stage());
                            } else {
                                main.start(Main.stage);
                            }
                            Login.stage.close();
                        }
                        
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void initLabel() {
        labelTipId.setText("");
        labelTipPwd.setText("");
        labelTipLogin.setText("");
    }

    private boolean checkBeforeLogin(String id, String pwd) {
        boolean continueLogin = true;
        if (id == null || id.equals("")) {
            labelTipId.setText("用户名不能为空！");
            continueLogin = false;
        }
        if (pwd == null || pwd.equals("")) {
            labelTipPwd.setText("密码不能为空！");
            continueLogin = false;
        }
        return continueLogin;
    }

    @FXML
    private void handleButtonGoRegisterAction(ActionEvent event) {
        Platform.runLater(() -> {
            try {
                new Register().start(new Stage());
                Login.stage.hide();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
