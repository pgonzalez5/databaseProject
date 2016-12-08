package fishbytes.view;


import fishbytes.Main;
import fishbytes.model.*;
import fishbytes.utility.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.stage.StageStyle;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;


public class Controller {

    private ObservableList<User> userData;
    private ObservableList<Location> locationData;
    private ObservableList<Bait> baitData;
    private ObservableList<FishSub> fsubData;
    private ObservableList<FishSpecies> fspecData;

    private SortedList<User> sortedUserData;
    private SortedList<Location> sortedLocationData;
    private SortedList<Bait> sortedBaitData;
    private SortedList<FishSub> sortedFishSubData;

    @FXML
    private TextField UserFilterUsername;
    @ FXML private TextField LocationFilterName;
    @ FXML private TextField BaitFilterName;
    //@ FXML private TextField FishSubFitlerName;


    @FXML
    private TabPane tab;
    private Main main;
    private Connect database;

    @FXML
    private Tab UserTab;
    @FXML
    private TableView<User> UserTable;
    @FXML
    private TableColumn<User, String> UserNameColumn;
    @FXML
    private TableColumn<User, String> FirstNameColumn;
    @FXML
    private TableColumn<User, String> LastNameColumn;
    @FXML
    private TableColumn<User, LocalDate> RegDateColumn;
    @FXML
    private TableColumn<User, Number> CommentsColumn;
    @FXML
    private TableColumn<User, Number> CatchPostsColumn;

   // @FXML
    //private Tab
    @FXML
    private TableView<Location> LocationTable;
    @FXML
    private TableColumn<Location, String> LocationNameColumn;
    @FXML
    private TableColumn<Location, String> LocationCityColumn;
    @FXML
    private TableColumn<Location, String> LocationStateColumn;
    @FXML
    private TableColumn<Location, Number> LocationCatchesColumn;


    @FXML
    private TableView<Bait> BaitTable;
    @FXML
    private TableColumn<Bait, String> BaitNameColumn;
    @FXML
    private TableColumn<Bait, String> BaitTypeColumn;
    @FXML
    private TableColumn<Bait, Number> BaitCatchColumn;

    @FXML TableView<FishSub> FishSubTable;
    @FXML TableColumn<FishSub, String> FishSubNameColumn;
    @FXML TableColumn<FishSub, Number> FishSubCatchesColumn;
    @FXML TableColumn<FishSub, Number> FishSubAvgWColumn;
    @FXML TableColumn<FishSub, Number> FishSubAvgLColumn;

    @FXML TableView FishSpeciesTable;
    @FXML TableColumn<FishSpecies, String> FishSpeciesNameColumn;
    @FXML TableColumn<FishSpecies, Number> NumSubColumn;
    //
    @FXML
    private ImageView BaitImage1;
    @FXML
    private ImageView FishImage1;
    @FXML
    private ImageView BaitImage;
    @FXML
    private ImageView FishImage;
    @FXML
    private ImageView UserImage;
    @FXML
    private ImageView LocationImage;
    @FXML
    private ImageView ReportImage;

    // Ratio buttons report tab panel
    @FXML
    private RadioButton YearlyRadioButton;
    @FXML
    private RadioButton MonthlyRadioButton;
    @FXML
    private RadioButton WeeklyRadioButton;

    // comboboxes
    @FXML
    private ComboBox<Integer> YearComboBox;
    @FXML
    private ComboBox<String> MonthComboBox;

    //@FXML private ComboBox<String> FilterStateComboBox;
    //@FXML private ComboBox<String> FilterTypeComboBox;
    @FXML private ComboBox<String> FilterSpecComboBox;

    @FXML
    private Label UserSelectLabel;
    @FXML private Label LocationSelectLabel;

    private int YEAR_SELECTED;
    private int MONTH_SELECTED;
    String [] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};


    public void loadTabs()
    {
        // user table values
        FirstNameColumn.setCellValueFactory(FirstNameColumn->FirstNameColumn.getValue().getFirstNameProperty());
        LastNameColumn.setCellValueFactory(cellData->cellData.getValue().getLastNameProperty());
        UserNameColumn.setCellValueFactory(cellData->cellData.getValue().getUserNameProperty());
        RegDateColumn.setCellValueFactory(cellData-> cellData.getValue().getRegDateProperty());
        CommentsColumn.setCellValueFactory(cellData->cellData.getValue().getCommentCountProperty());
        CatchPostsColumn.setCellValueFactory(cellData->cellData.getValue().getCatchCountProperty());

        // location table values
        LocationNameColumn.setCellValueFactory(cellData->cellData.getValue().getNameProperty());
        LocationCityColumn.setCellValueFactory(cellData->cellData.getValue().getCityProperty());
        LocationStateColumn.setCellValueFactory(cellData->cellData.getValue().getStateProperty());
        LocationCatchesColumn.setCellValueFactory(cellData->cellData.getValue().getCatchCount());

        // load bait table values
        BaitNameColumn.setCellValueFactory(cellData->cellData.getValue().getNameProperty());
        BaitTypeColumn.setCellValueFactory(cellData->cellData.getValue().getTypeProerty());
        BaitCatchColumn.setCellValueFactory(cellData->cellData.getValue().getCatchesProperty());

        // fish sub table values
        FishSubNameColumn.setCellValueFactory(cellData->cellData.getValue().getName());
        FishSubCatchesColumn.setCellValueFactory(cellData->cellData.getValue().getCatches());
        FishSubAvgWColumn.setCellValueFactory(cellData->cellData.getValue().getAvgWeight());
        FishSubAvgLColumn.setCellValueFactory(cellData->cellData.getValue().getAvgLength());

        // fish species table values
        FishSpeciesNameColumn.setCellValueFactory(cellData->cellData.getValue().getNameProperty());
        NumSubColumn.setCellValueFactory(cellData->cellData.getValue().numOfSubs());
    }

    @FXML
    private void UserTableClicked(){
        User edit = UserTable.getSelectionModel().getSelectedItem();
        if(edit != null)
            UserSelectLabel.setText("User Selected: " + edit.getUsername());
        else
            UserSelectLabel.setText("User Selected: none");
    }

    @FXML
    private void LocationTableClicked(){
        Location edit = LocationTable.getSelectionModel().getSelectedItem();
        if(edit != null)
            LocationSelectLabel.setText("User Selected: " + edit.getName());
        else
            LocationSelectLabel.setText("User Selected: none");
    }

    public void loadComboBoxes()
    {
        ObservableList<String> months = FXCollections.observableArrayList(
                "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        );
        MonthComboBox.setItems(months);
        MonthComboBox.setDisable(true);
        //MonthComboBox.valueProperty().addListener(new);

        ObservableList<Integer> years = FXCollections.observableArrayList(
               2011,2012,2013,2014,2015,2016
        );
        YearComboBox.setItems(years);
        YearComboBox.setDisable(true);

        YearComboBox.getSelectionModel().selectLast();
        MonthComboBox.getSelectionModel().selectFirst();


        ArrayList<String> fishSpecsNames = new ArrayList<>();
        fishSpecsNames.add("All species");
        for(int i = 0; i < fspecData.size(); ++i)
            fishSpecsNames.add(fspecData.get(i).getName());
        ObservableList<String> species = FXCollections.observableArrayList(fishSpecsNames);
        FilterSpecComboBox.setItems(species);
        FilterSpecComboBox.getSelectionModel().selectFirst();

/*
        ArrayList<String> stateLocations = new ArrayList<>();
        stateLocations.add("All States");
        HashMap<String, Integer> h = new HashMap<String, Integer>();
        for(int i = 0; i < locationData.size(); ++i)
            if(h.containsKey(locationData.get(i).getState()) == false) {
                stateLocations.add(locationData.get(i).getState());
                h.put(locationData.get(i).getState(),1);
            }
        ObservableList<String> locations = FXCollections.observableArrayList(stateLocations);
        FilterStateComboBox.setItems(locations);

        ObservableList<String> type = FXCollections.observableArrayList(
                "All types", "Real", "Artifical"
        );
        FilterTypeComboBox.setItems(type);


        FilterStateComboBox.getSelectionModel().selectFirst();
        FilterTypeComboBox.getSelectionModel().selectFirst();
        */

    }



    private Button iconToButton(String s)
    {
        Button r = new Button();
        ImageView g = new ImageView(new Image(getClass().getResource(s).toExternalForm()));
        g.setFitHeight(14);
        g.setFitWidth(14);
        r.getStyleClass().add("tab-button");
        r.setGraphic(g);
        r.setMinWidth(Region.USE_PREF_SIZE);
        return r;
    }


    private void DatabaseConnetion()
    {

        database.getLocations();
        userData = database.getUsers();
        locationData = database.getLocations();
        baitData = database.getBait();
        fsubData = database.getFishSub();
        fspecData = database.getFishSpecies();
        //database.insertUser(new User("Gor", "tor", "timmy2", 0, "asdf@asdf", "12312", null, "portland", 0, 0));
    }


    public void setMainApp(Main main, Connect database)
    {
        this.main = main;
        this.database = database;
        DatabaseConnetion();

        UserTable.setItems(userData);
        LocationTable.setItems(locationData);
        BaitTable.setItems(baitData);
        FishSubTable.setItems(fsubData);
        FishSpeciesTable.setItems(fspecData);

        loadTabs();
        loadComboBoxes();

        // search feature filtering for USER
        FilteredList<User> filtUserData = new FilteredList<User>(userData, p->true);
        UserFilterUsername.textProperty().addListener((observable, oldValue, newValue)-> {
            filtUserData.setPredicate(user-> {
                if (newValue == null || newValue.isEmpty())
                    return true;

                String lowerCaseTest = newValue.toLowerCase();
                if(user.getUsername().toLowerCase().contains(lowerCaseTest))
                    return true;
                return false;
            });
        });
        sortedUserData = new SortedList<>(filtUserData);
        sortedUserData.comparatorProperty().bind(UserTable.comparatorProperty());
        UserTable.setItems(sortedUserData);

        // search feature filtering for LOCATION
        FilteredList<Location> filLocationData = new FilteredList<Location>(locationData, p->true);
        LocationFilterName.textProperty().addListener((observable, oldValue, newValue)-> {
            filLocationData.setPredicate(location-> {
                if (newValue == null || newValue.isEmpty())
                    return true;

                String lowerCaseTest = newValue.toLowerCase();
                if(location.getName().toLowerCase().contains(lowerCaseTest))
                    return true;
                return false;
            });
        });
        sortedLocationData = new SortedList<>(filLocationData);
        sortedLocationData.comparatorProperty().bind(LocationTable.comparatorProperty());
        LocationTable.setItems(sortedLocationData);

        // search feature filtering for bait
        FilteredList<Bait> filBaitData = new FilteredList<Bait>(baitData, p->true);
        BaitFilterName.textProperty().addListener((observable, oldValue, newValue)-> {
            filBaitData.setPredicate(bait-> {
                if (newValue == null || newValue.isEmpty())
                    return true;

                String lowerCaseTest = newValue.toLowerCase();
                if(bait.getName().toLowerCase().contains(lowerCaseTest))
                    return true;
                return false;
            });
        });
        sortedBaitData = new SortedList<>(filBaitData);
        sortedBaitData.comparatorProperty().bind(BaitTable.comparatorProperty());
        BaitTable.setItems(sortedBaitData);

        // search feature filtering for FishSub
       /* FilteredList<FishSub> filFishSub = new FilteredList<FishSub>(fsubData, p->true);
        FishSubFitlerName.textProperty().addListener((observable, oldValue, newValue)-> {
            filFishSub.setPredicate(fishsub-> {
                if (newValue == null || newValue.isEmpty())
                    return true;

                String lowerCaseTest = newValue.toLowerCase();
                if(fishsub.getFishSubName().toLowerCase().contains(lowerCaseTest))
                    return true;
                return false;
            });
        });
        sortedFishSubData = new SortedList<>(filFishSub);
        sortedFishSubData.comparatorProperty().bind(FishSubTable.comparatorProperty());
        FishSubTable.setItems(sortedFishSubData);*/

        FilteredList<FishSub> filsub = new FilteredList<FishSub>(fsubData, p->true);
        FilterSpecComboBox.valueProperty().addListener((observable, oldValue, newValue)-> {
            filsub.setPredicate(fishsub-> {
                if (newValue == null || newValue.isEmpty() || newValue.equals("All species"))
                    return true;

                int fid = -1;
                for(int i = 0; i < fspecData.size(); ++i)
                    if(fspecData.get(i).getName().equals(newValue))
                    {
                        fid = fspecData.get(i).getID();
                        break;
                    }

                if(fishsub.getFspecID() == fid)
                    return true;
                return false;
            });
        });
        sortedFishSubData = new SortedList<>(filsub);
        sortedFishSubData.comparatorProperty().bind(FishSubTable.comparatorProperty());
        FishSubTable.setItems(sortedFishSubData);
    }

    public Controller() {

      //  loadTabImages();
    }

    @FXML
    private void initialize()
    {
        // connect to database and load tables



        // load up images to their respective image views
        BaitImage.setImage(new Image("/imgs/hook-512.png"));
        FishImage.setImage(new Image("/imgs/fish.png"));
        BaitImage1.setImage(new Image("/imgs/hook-512.png"));
        FishImage1.setImage(new Image("/imgs/fish.png"));
        UserImage.setImage(new Image("imgs/user.png"));
        LocationImage.setImage(new Image("/imgs/map.png"));
        ReportImage.setImage(new Image("/imgs/report.png"));

    }

    @FXML
    private void handleEditPerson(){
        User edit = UserTable.getSelectionModel().getSelectedItem();
        if(edit == null)
        {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.initOwner(main.getPrimaryStage());
            a.initStyle(StageStyle.UTILITY);
            a.setTitle("No User selected");
            a.setHeaderText(null);
            a.setContentText("Select a user first then click the edit button");
            a.showAndWait();
            return;
        }

        boolean onClicked = main.showUserEditDialog(edit);

        if(onClicked) {
            database.updateUser(edit); //here update user/send changes to database
        }
    }

    @FXML void createUser()
    {

        java.util.Date input = new java.util.Date();
        LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        User newguy = new User();
        newguy.setDate(date);
        newguy.setUserName("NewUser");

        boolean onClicked = main.showNewUserDialog(newguy);

        if(onClicked)
            database.insertUser(newguy, -1);
        else
            return;
        // search feature filtering for USER
        userData.add(newguy);

        FilteredList<User> filtUserData = new FilteredList<User>(userData, p->true);
        UserFilterUsername.textProperty().addListener((observable, oldValue, newValue)-> {
            filtUserData.setPredicate(user-> {
                if (newValue == null || newValue.isEmpty())
                    return true;

                String lowerCaseTest = newValue.toLowerCase();
                if(user.getUsername().toLowerCase().contains(lowerCaseTest))
                    return true;
                return false;
            });
        });
        sortedUserData = new SortedList<>(filtUserData);
        sortedUserData.comparatorProperty().bind(UserTable.comparatorProperty());
        UserTable.setItems(sortedUserData);

    }

    @FXML
    private void createFishReport(){
        createReport("fishstats");
    }

    @FXML
    private void createBaitReport(){
        createReport("baitstats");
    }

    private void createReport(String type)
    {
        //"src/fishbytes/reports/fishstats_yearly.jasper" WORKS
        try {
            String report = "src/fishbytes/reports/" + type + ".jasper";
            JasperReport j; //= (JasperReport) JRLoader.loadObject(new File(report));
            //JasperReport j = JasperCompileManager.compileReport(report);//"src/fishbytes/reports/fishstats_yearly.jrxml");;
            Map<String, Object> p = new HashMap<String, Object>();
            //Map args = new HashMap();

            String pathimage = this.getClass().getResource("/fishbytes/reports/icon_fishbytes.png").getPath();
            InputStream image = this.getClass().getResourceAsStream("/fishbytes/reports/icon_fishbytes.png");
            p.put("IMAGE", image);//"file:"+ pathimage);
            System.out.println(pathimage);
            //c.testYear()

            if(YearlyRadioButton.isSelected()) {

                int year = YearComboBox.getValue();
                InputStream report2 = this.getClass().getResourceAsStream("/fishbytes/reports/" + type + "_yearly.jasper");//.getPath();

                j = (JasperReport) JRLoader.loadObject(report2);//JasperCompileManager.compileReport("src/fishbytes/reports/fishstats_yearly.jrxml");
                if(type.equals("fishstats"))
                    p.put("REPORT_TITLE", "Fish Stats: Yearly (" + year + ") Report");
                else
                    p.put("REPORT_TITLE", "Bait Stats: Yearly (" + year + ") Report");
                p.put("REPORT_YEAR", year);
            }
            else if(MonthlyRadioButton.isSelected()){
               // j =  JasperCompileManager.compileReport("src/fishbytes/reports/fishstats_monthly.jrxml");
                InputStream report2 = this.getClass().getResourceAsStream("/fishbytes/reports/" + type + "_monthly.jasper");//.getPath();

                j = (JasperReport) JRLoader.loadObject(report2);//JasperCompileM
                int year = YearComboBox.getValue();
                int month = getMonthNum(MonthComboBox.getValue());
                System.out.print(month + " " + year);
                p.put("REPORT_YEAR", year);
                p.put("REPORT_MONTH", month);
                p.put("REPORT_TITLE", "Fish Stats: Monthly (" + months[month - 1] + "/" + year + ") Report");
            }
            else if(WeeklyRadioButton.isSelected()){
                int startDay = 1;
                int endDay = 7;
                int year = 2016;
                int month = 12;
                System.out.println("weekly report!");
                p.put("REPORT_YEAR", year);
                p.put("REPORT_MONTH", month);
                p.put("REPORT_DAY", startDay);
                p.put("REPORT_ENDDAY", endDay);
                InputStream report2 = this.getClass().getResourceAsStream("/fishbytes/reports/"+ type +"_weekly.jasper");//.getPath();

                j = (JasperReport) JRLoader.loadObject(report2);//JasperCompileM
                //j = JasperCompileManager.compileReport("src/fishbytes/reports/fishstats_weekly.jrxml");
                p.put("REPORT_TITLE", "Fish Stats: Weekly Report");
            } else{
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.initOwner(main.getPrimaryStage());
                a.initStyle(StageStyle.UTILITY);
                a.setTitle("Input needed!");
                a.setHeaderText(null);
                a.setContentText("Select a yearly/monthly/weekly option!");
                a.showAndWait();
                return;
            }


            JRDataSource data = new JREmptyDataSource();

            JasperPrint print = JasperFillManager.fillReport(j, p, database.con);
            List<JRPrintPage> pages = print.getPages();
            if(pages.size() == 0) {
                System.out.println("warning no data found for such query!");
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.initOwner(main.getPrimaryStage());
                a.initStyle(StageStyle.UTILITY);
                a.setTitle("No data found!");
                a.setHeaderText(null);
                a.setContentText("There's no data available for that year/month/week sorry.");
                a.showAndWait();
                return;

            }
            JasperViewer v = new JasperViewer(print, false);
            v.setVisible(true);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private int getMonthNum(String s){
        for(int i = 0; i < 12; ++i)
            if(months[i].equals(s))
                return i + 1;
        return 1;
    }

    @FXML
    void radioButtonClickYear()
    {
        MonthComboBox.setDisable(true);
        //YearComboBox.getSelectionModel().selectLast();
        YearComboBox.setDisable(false);
    }

    @FXML
    void radioButtonClickMonth()
    {
        YearComboBox.setDisable(false);

        MonthComboBox.setDisable(false);

    }

    @FXML
    void radioButtonClickWeekly()
    {
        YearComboBox.setDisable(true);
        MonthComboBox.setDisable(true);
    }

    private void updateDatabase(User e){

    }

    @FXML
    private void deleteUser(){
        int index = UserTable.getSelectionModel().getSelectedIndex();
        System.out.println("deleting user at index: " + index);
        if(index >= 0)
        {
            Alert warning = new Alert(Alert.AlertType.CONFIRMATION);
            warning.initOwner(main.getPrimaryStage());

            warning.setHeaderText(null);
            warning.setTitle("Delete User?");
            warning.setContentText("Are you sure you want to delete the user?");
            warning.initStyle(StageStyle.UTILITY);

            Optional<ButtonType> r = warning.showAndWait();
            // need to delete main data source list first because index is not really the correct data from sorted list
            if (r.get() == ButtonType.OK) {
                int sourceIndex = sortedUserData.getSourceIndexFor(userData,index);
                database.deleteUser(userData.get(sourceIndex));
                userData.remove(sourceIndex);
                //UserTable.getItems().remove(index);
            }
            else
                return;
        } else {
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.initOwner(main.getPrimaryStage());
            warning.setTitle("No user selected!");
            warning.setHeaderText("Warning!");
            warning.setContentText("When deleting select user on table!");
            warning.show();
        }
    }

    @FXML void newLocation()
    {
        Location newPlace = new Location();
        boolean onClicked = main.showNewLocationDialog(newPlace);
        if(onClicked){
            database.newLocation(newPlace, -1);
        } else
        return;
        // search feature filtering for USER
        locationData.add(newPlace);

        FilteredList<Location> filtUserData = new FilteredList<Location>(locationData, p->true);
        LocationFilterName.textProperty().addListener((observable, oldValue, newValue)-> {
            filtUserData.setPredicate(user-> {
                if (newValue == null || newValue.isEmpty())
                    return true;

                String lowerCaseTest = newValue.toLowerCase();
                if(user.getName().toLowerCase().contains(lowerCaseTest))
                    return true;
                return false;
            });
        });
        sortedLocationData = new SortedList<>(filtUserData);
        sortedLocationData.comparatorProperty().bind(LocationTable.comparatorProperty());
        LocationTable.setItems(sortedLocationData);
    }

    @FXML void editLocation()
    {
        Location edit = LocationTable.getSelectionModel().getSelectedItem();
        if(edit == null)
        {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.initOwner(main.getPrimaryStage());
            a.initStyle(StageStyle.UTILITY);
            a.setTitle("No User selected");
            a.setHeaderText(null);
            a.setContentText("Select a Location first then click the edit button");
            a.showAndWait();
            return;
        }

        boolean onClicked = main.showLocationEditDialog(edit);
        if(onClicked){
            database.updateLocation(edit);
        }
    }

    @FXML void newBait()
    {
        Bait bait = new Bait();
        boolean onClicked = main.showNewBaitDialog(bait);
        if(onClicked){
            database.insertBait(bait, - 1);
        } else
            return;
        // search feature filtering for USER
        baitData.add(bait);

        FilteredList<Bait> filBaitData = new FilteredList<Bait>(baitData, p->true);
        BaitFilterName.textProperty().addListener((observable, oldValue, newValue)-> {
            filBaitData.setPredicate(baits-> {
                if (newValue == null || newValue.isEmpty())
                    return true;

                String lowerCaseTest = newValue.toLowerCase();
                if(baits.getName().toLowerCase().contains(lowerCaseTest))
                    return true;
                return false;
            });
        });
        sortedBaitData = new SortedList<>(filBaitData);
        sortedBaitData.comparatorProperty().bind(BaitTable.comparatorProperty());
        BaitTable.setItems(sortedBaitData);
    }

    @FXML void editBait()
    {
        Bait edit = BaitTable.getSelectionModel().getSelectedItem();
        if(edit == null)
        {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.initOwner(main.getPrimaryStage());
            a.initStyle(StageStyle.UTILITY);
            a.setTitle("No Bait selected");
            a.setHeaderText(null);
            a.setContentText("Select a bait first then click the edit button");
            a.showAndWait();
            return;
        }

        boolean onClicked = main.showBaitEditDialog(edit);
        if(onClicked)
            database.updateBait(edit);
    }

    @FXML void newFishSub()
    {
        FishSub fsub = new FishSub();
        boolean onClicked = main.showNewFishSubDialog(fspecData, fsub);
        if(onClicked){
            database.insertSubFish(fsub, -1);
        } else
            return;


        fsubData.add(fsub);
        // search feature filtering for FishSub
       /* FilteredList<FishSub> filFishSub = new FilteredList<FishSub>(fsubData, p->true);
        FishSubFitlerName.textProperty().addListener((observable, oldValue, newValue)-> {
            filFishSub.setPredicate(fishsub-> {
                if (newValue == null || newValue.isEmpty())
                    return true;

                String lowerCaseTest = newValue.toLowerCase();
                if(fishsub.getFishSubName().toLowerCase().contains(lowerCaseTest))
                    return true;
                return false;
            });
        });
        sortedFishSubData = new SortedList<>(filFishSub);
        sortedFishSubData.comparatorProperty().bind(FishSubTable.comparatorProperty());
        FishSubTable.setItems(sortedFishSubData);
*/
        fspecData = database.getFishSpecies();
        FishSpeciesTable.setItems(fspecData);
    }

    @FXML void newFishSpec()
    {
        FishSpecies fspec = new FishSpecies();
        boolean onClicked = main.showNewFishSpecDialog(fspec);
        if(onClicked){
            database.insertFishSpec(fspec);
        } else{
            return;
        }
        fspecData.add(fspec);
    }

    @FXML void editFishType(){

    }

    @FXML void login(){
        main.showLoginScreen();
    }

}
