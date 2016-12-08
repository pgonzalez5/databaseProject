package fishbytes.view;

import fishbytes.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Anthony on 12/3/2016.
 */
public class NewUserController {

    @FXML
    private TextField newFirstName;
    @FXML
    private TextField newLastName;
    @FXML
    private TextField newUsername;
    @FXML
    private TextField newEmail;
    @FXML
    private PasswordField newPassword;

    private User newUser;
    private Stage dialogStage;
    private boolean okToCreate = false;

    @FXML
    private void initalize(){

    }

    public void setUser(User e){ newUser = e;}

    public NewUserController(){

    }

    public boolean isOkClicked(){
        return okToCreate;
    }

    public void setDialogStage(Stage g){ dialogStage = g;}

    @FXML
    private void cancelButton(){
        dialogStage.close();
    }

    @FXML
    private void clearEntry(){

    }

    @FXML
    private void CreateUser(){
        newUser.setUserName(newUsername.getText());
        newUser.setFirstname(newFirstName.getText());
        newUser.setLastname(newLastName.getText());
        newUser.setEmail(newEmail.getText());
        newUser.setPassword(newPassword.getText());
        newUser.setCity("Bakersfield");
        okToCreate = true;
        dialogStage.close();
    }


}
