package fishbytes.view;

import fishbytes.model.FishSpecies;
import fishbytes.model.FishSub;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;


import java.util.ArrayList;

/**
 * Created by Anthony on 12/4/2016.
 */
public class NewFishSubController {
    private FishSub newfish;
    private Stage stage;
    private ObservableList<FishSpecies> data;
    private boolean isclicked = false;
    @FXML private javafx.scene.control.TextField FishSubTextField2;
    @FXML private ComboBox<String> fishSpecComboBox;


    public void setDialogStage(Stage g) {
        stage = g;
    }

    public void setFishSub(ObservableList<FishSpecies> d, FishSub e){
        data = d; newfish = e;
        ArrayList<String> fishSpecsNames = new ArrayList<>();
        for(int i = 0; i < data.size(); ++i)
            fishSpecsNames.add(data.get(i).getName());
        ObservableList<String> species = FXCollections.observableArrayList(fishSpecsNames);
        fishSpecComboBox.setItems(species);
    }

    @FXML void CancelClick(){
        stage.close();
    }

    public boolean isOkClicked(){
        return isclicked;
    }

    @FXML
    private void OKClicked(){
        newfish.setName(FishSubTextField2.getText());
        String fSpec = fishSpecComboBox.getSelectionModel().getSelectedItem();
        for(int i = 0; i < data.size(); ++i)
            if(data.get(i).getName().equals(fSpec))
                newfish.setFspecID(data.get(i).getID());
        isclicked = true;
        stage.close();
    }
}
