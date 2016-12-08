package fishbytes.view;

import fishbytes.model.Bait;
import fishbytes.model.Location;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Anthony on 12/4/2016.
 */
public class NewBaitController {
    private Bait bait;
    private Stage stage;
    private boolean isclicked = false;

    @FXML
    private TextField NewBaitName;
    @FXML private RadioButton fakeBait;
    @FXML private RadioButton realBait;


    public void setBait(Bait e) {
        this.bait = e;
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
        bait.setName(NewBaitName.getText());
        if(realBait.isSelected())
            bait.setType("Real");
        else if(fakeBait.isSelected())
            bait.setType("Artifical");
        isclicked = true;
        stage.close();
    }
}


