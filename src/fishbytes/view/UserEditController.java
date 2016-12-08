package fishbytes.view;

import fishbytes.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Anthony on 12/2/2016.
 */
public class UserEditController {

    @FXML
    TextField firstnameField;
    @FXML
    TextField lastnameField;
    @FXML
    TextField usernameField;
    @FXML
    TextField cityField;
    @FXML
    TextField emailField;

    private Stage dialogStage;
    private boolean okClicked = false;
    private User editUser;

    @FXML
    private void initialize() {
    }

    public UserEditController(){

    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setDialogStage(Stage s){
        dialogStage= s;
    }

    public void setUser(User u){
        editUser = u;
        firstnameField.setText(editUser.getFirstName());
        lastnameField.setText(editUser.getLastName());
        usernameField.setText(editUser.getUsername());
        cityField.setText(editUser.getCity());
        emailField.setText(editUser.getEmail());
    }

    @FXML
    private void onCancelClick(){
        dialogStage.close();
    }

    @FXML
    private void onOkClick(){
        if(validInput()){
            editUser.setUserName(usernameField.getText());
            editUser.setFirstname(firstnameField.getText());
            editUser.setLastname(lastnameField.getText());
            editUser.setCity(cityField.getText());
            editUser.setEmail(emailField.getText());
            okClicked = true;
            dialogStage.close();
        }
    }

    private boolean validInput(){
        return true;
    }
}
