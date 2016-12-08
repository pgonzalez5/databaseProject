package fishbytes.view;

import fishbytes.model.Bait;
import fishbytes.model.FishSpecies;
import fishbytes.model.FishSub;
import fishbytes.model.Location;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Anthony on 12/4/2016.
 */
public class RenameController {

    @FXML private TextField Rename;
    @FXML private Label label;
    private Location l;
    private FishSub fsub;
    private FishSpecies fspec;
    private Bait bait;
    private Stage stage;
    private boolean isclicked = false;

    public RenameController(){}

    public void setDialogStage(Stage g){ stage = g;}

    public void setLocation(Location o){ l = o;}
    public void setBait(Bait o){ bait = o;}
    public void setFishSpec(FishSpecies f){ label.setText("Enter new fish species");fspec = f;}
    public boolean isOkClicked(){
        return isclicked;
    }
    @FXML private void cancelClick(){
        stage.close();
    }

    @FXML private void onOK(){
        if(l != null){
            l.setName(Rename.getText());
        }else if(fsub != null){
            fsub.setName(Rename.getText());
        }else if(fspec != null){
            fspec.setName(Rename.getText());
        }else if(bait != null){
            bait.setName(Rename.getText());
        }
        isclicked = true;
        stage.close();
    }
}
