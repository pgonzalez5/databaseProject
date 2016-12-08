package fishbytes;

import fishbytes.model.*;
import fishbytes.utility.Connect;
import fishbytes.view.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    private Connect database;
    private Stage primaryStage;
    //public static Connect database;
    @Override
    public void start(Stage primaryStage) throws Exception{

        this.primaryStage = primaryStage;
        FXMLLoader l = new FXMLLoader(getClass().getResource("/fishbytes/view/main.fxml"));
        Parent root = l.load();
        primaryStage.setTitle("FishBytes DB");
        primaryStage.getIcons().add(new Image("/imgs/icon_fishbytes.png"));
        primaryStage.setScene(new Scene(root, 860, 600));
        primaryStage.show();

        database = new Connect();

        Controller c = l.getController();
        c.setMainApp(this, database);
    }


    public boolean showUserEditDialog(User edit)
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/fishbytes/view/EditUser.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit User: " + edit.getUsername());
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            System.out.println(edit.getUsername());

            UserEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setUser(edit);

            dialogStage.showAndWait();
            return controller.isOkClicked();

        } catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean showNewUserDialog(User e) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/fishbytes/view/NewUser.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("New User");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            System.out.println(e.getUsername());

            NewUserController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setUser(e);

            dialogStage.showAndWait();

           // if(controller.isOkClicked()) {
           //     database.insertUser(e);
            //    System.out.println("INSERTING GUY");
           // }

            return controller.isOkClicked();

        } catch(IOException s){
            s.printStackTrace();
            return false;
        }
    }


    public boolean showNewLocationDialog(Location e){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/fishbytes/view/NewLocation.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("New Location");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            System.out.println(e.getName());

            NewLocationController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setLocation(e);

            dialogStage.showAndWait();

            // if(controller.isOkClicked()) {
            //     database.insertUser(e);
            //    System.out.println("INSERTING GUY");
            // }

            return controller.isOkClicked();

        } catch(IOException s) {
            s.printStackTrace();
            return false;
        }
    }

    public boolean showLocationEditDialog(Location edit){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/fishbytes/view/Rename.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Location: " + edit.getName());
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            System.out.println(edit.getName());

            RenameController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setLocation(edit);

            dialogStage.showAndWait();
            return controller.isOkClicked();

        } catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean showNewBaitDialog(Bait e) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/fishbytes/view/NewBait.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("New Bait");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // System.out.println(e.getName());

            NewBaitController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setBait(e);

            dialogStage.showAndWait();

            // if(controller.isOkClicked()) {
            //     database.insertUser(e);
            //    System.out.println("INSERTING GUY");
            // }

            return controller.isOkClicked();

        } catch (IOException s) {
            s.printStackTrace();
            return false;
        }
    }


    public boolean showBaitEditDialog(Bait edit){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/fishbytes/view/Rename.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Location: " + edit.getName());
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            System.out.println(edit.getName());

            RenameController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setBait(edit);

            dialogStage.showAndWait();
            return controller.isOkClicked();

        } catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean showNewFishSpecDialog(FishSpecies edit){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/fishbytes/view/Rename.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("New Species!");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            System.out.println(edit.getName());

            RenameController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setFishSpec(edit);

            dialogStage.showAndWait();
            return controller.isOkClicked();

        } catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean showNewFishSubDialog(ObservableList<FishSpecies> data, FishSub newFish){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/fishbytes/view/NewFishSub.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("New Subspecies!");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            //System.out.println(edit.getName());

            NewFishSubController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setFishSub(data, newFish);

            dialogStage.showAndWait();
            return controller.isOkClicked();

        } catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean showLoginScreen()
    {
        try {
            FXMLLoader loginscreen = new FXMLLoader();
            loginscreen.setLocation(Main.class.getResource("view/loginWindow.fxml"));
            AnchorPane screen = (AnchorPane) loginscreen.load();

            Stage dialog = new Stage();
            dialog.setTitle("Login");
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.initOwner(primaryStage);
            dialog.getIcons().add(new Image("/imgs/user.png"));
            Scene newScene = new Scene(screen);
            dialog.setScene(newScene);

            LoginController controller = loginscreen.getController();
            controller.setController(dialog,database, primaryStage);
            dialog.showAndWait();

            return controller.ok();
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

}
