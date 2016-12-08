package fishbytes.view;

import fishbytes.utility.Connect;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sun.security.util.Password;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by Anthony on 11/17/2016.
 */
public class LoginController {
    private Stage stage;
    private Connect database;
    private Stage primaryStage;
    @FXML
    private TextField loginText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Label messageLabel;

    public boolean isok = false;
    public LoginController(){}

    @FXML
    private void OkClicked()
    {
        if(database == null) {
            database = new Connect();
            System.out.println("connected!");
        }
        String username = loginText.getText();
        String password = passwordText.getText();

        if(database.testAdminUser(username, password) == true) {
            primaryStage.setTitle("FishBytes DB     Welcome " + username);
            isok = true;
            stage.close();
            return;
        }
        else
        {
            String msg = "Error: username or password";
            System.out.println(msg);
            messageLabel.setText(msg);
            messageLabel.setTextFill(Color.web("#FF0000"));
            isok = false;
           //Dialog error = new Dialog();
        }
    }

    public boolean ok(){
        return isok;
    }

    @FXML
    private void CancelClicked()
    {
        stage.close();
    }

    public void setController(Stage g, Connect database, Stage c)
    {
        this.stage = g;
        this.primaryStage = c;
        this.database = database;
    }

    @FXML
    private void initialize()
    {
        loginText.setText("admin");
        passwordText.setText("fish");
    }
}
