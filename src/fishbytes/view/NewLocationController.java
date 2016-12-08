package fishbytes.view;

import fishbytes.model.Location;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Anthony on 12/4/2016.
 */
public class NewLocationController {
    private Location location;
    private Stage stage;
    private boolean isclicked = false;

    @FXML private TextField LocationNameField;
    @FXML private TextField AddressField;
    @FXML private TextField CityField;
    @FXML private TextField StateField;
    @FXML private TextField ZipField;

    public void setLocation(Location e) {
        this.location = e;
    }

    public void setDialogStage(Stage g) {
        stage = g;
    }

    @FXML void CancelClick(){
        stage.close();
    }

    public boolean isOkClicked(){
        return isclicked;
    }

    @FXML
    private void OKClicked(){
        location.setName(LocationNameField.getText());
        location.setCity(CityField.getText());
        location.setAddress(AddressField.getText());
        location.setState(StateField.getText());
        location.setZip(Integer.parseInt(ZipField.getText()));
        isclicked = true;
        stage.close();
    }
}
