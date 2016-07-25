package UserInterface.DashboardWindow;

import Helper.MainController;
import UserInterface.HouseholdConfigurationWindow.HouseholdConfigurationWindow;
import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Interfaces.ResultPresentationInterface;
import UserInterface.DeviceConfigurationWindow.DeviceConfigurationWindow;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;


public class DashboardWindow extends Application {

    //Fields
    private ResultPresentationInterface mPresentation;
    private StackPane mGraphPane;
    private MenuBar mMenuBar;
    private ListView<String> mDevicesList;
    private VBox mOptionsVBox, mParamVBox;
    private Label mEventCounterLabel, mConnectionStatusLabel, mExecutionTimeLabel, mDisplayCategory;
    private Button mGenerateGraphicBTN, mEditParamsBTN;

    @Override
    public void start(Stage stage) throws Exception {

        //get views from XML file and cast to objects
        Parent root = FXMLLoader.load(getClass().getResource("DashboardWindow.fxml"));

        //add views to scene (container)
        Scene scene = new Scene(root);

        //add scene (container filled with views) to screen
        stage.setScene(scene);
        stage.setTitle("Smart Home Dashboard");

        //to make scene visible
        stage.show();

        //create ListView that will be added to Vbox
        mDevicesList = new ListView<>();
        //todo: add algorithms
        ObservableList<String> optionsList = FXCollections.observableArrayList("Devices", "Energy Production", "Energy Consumption");
        mDevicesList.setItems(optionsList);

        //VBox to embed fields for the params needed by the algorithms
        mOptionsVBox = (VBox) scene.lookup("#dashboard_algorithms");
        mOptionsVBox.getChildren().addAll(mDevicesList);

        mParamVBox = (VBox) scene.lookup("#param_box");

        //Get Pane for displaying graphs
        mGraphPane = (StackPane) scene.lookup("#pane_graph");
        //Diagramm im Pane zentrieren
        mGraphPane.setAlignment(Pos.CENTER);

        //get Label for selected Profile and connected 
        mEventCounterLabel = (Label) scene.lookup("#event_counter_label");
        mConnectionStatusLabel = (Label) scene.lookup("#connection_status_label");
        mExecutionTimeLabel = (Label) scene.lookup("#exec_time_label");
        mDisplayCategory = (Label) scene.lookup("#computed_algorithm_label");

        mGenerateGraphicBTN = (Button) scene.lookup("#btn_generate_graph");
        mEditParamsBTN = (Button) scene.lookup("#edit_devices_btn");

        
        
        
        //CAUTION!!!!!
        //Handle graphical representation when pushing the button
        
        
        
        
        //listener to generate graphics button
        mGenerateGraphicBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ResultSet rawData;
                long startTime = System.currentTimeMillis();
                try {
                    //drop old content from graphPane
                    mGraphPane.getChildren().clear();
                    
                    
                    
                    
                    
                    //Caution: Give an interface of class that can handle making results displayable
                    //execute Algorithm
                    mPresentation.plotGraph();
                    
                    
                    //print name of executed algorithm to label
                    mDisplayCategory.setText(mDevicesList.getSelectionModel().getSelectedItem());
                } catch (Exception ex) {
                    //print error msg to console
                    System.out.println(ex.getMessage());
                }

                //get Execution time and convert to Minutes:Seconds
                long endTime = System.currentTimeMillis();
                long executionTime = endTime - startTime;
                System.out.println("Time for Execution: " + String.valueOf(executionTime));
                String execTime = String.format("%02d min, %02d sec", TimeUnit.MILLISECONDS.toMinutes(executionTime),
                        TimeUnit.MILLISECONDS.toSeconds(executionTime)
                        - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(executionTime)));
                mExecutionTimeLabel.setText(execTime);
            }
        });

        
        //set onClickListener for algorithmlist
        mDevicesList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //check if same item is selected again
                if (!newValue.equals(oldValue)) {
                    //clean up
                    mGraphPane.getChildren().clear();
                    mParamVBox.getChildren().clear();
                    
                    switch (newValue) {
                        case "Devices":
                            MainController.getInstance().devicesOption();
                            break;
                        case "Energy Production":
                            MainController.getInstance().creationOption();
                            break;
                        case "Energy Consumption":
                            MainController.getInstance().consumptionOption();
                            break;
                    }
                }
            }
        });

        //Menu handling
        //get MenuBar from Layout
        mMenuBar = (MenuBar) scene.lookup("#menu");
        //create File menu
        //todo: File-Menu is not supported yet
        Menu fileMenu = new Menu("File");
        
        
        //create items for this menu
        MenuItem startMenuItem = new MenuItem("Start Computation");
        startMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainController.getInstance().startComputation();
            }
        });
        
        MenuItem pushDemoDataMenuItem = new MenuItem("Push demo events");
        pushDemoDataMenuItem.setOnAction(new EventHandler(){
            @Override
            public void handle(Event event) {
                MainController.getInstance().startPushing();
            }
        });
        
        MenuItem generateDemoDataMenuItem = new MenuItem("Generate demo data");
        generateDemoDataMenuItem.setOnAction(new EventHandler(){
            @Override
            public void handle(Event event) {
                MainController.getInstance().createMockData();
            }
        });

        
        MenuItem exportMenuItem = new MenuItem("Export to Excel");
        exportMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            MainController.getInstance().exportToExcel();
            }
        });
        //add new created MenuItems to Menu
        fileMenu.getItems().addAll(startMenuItem, generateDemoDataMenuItem, pushDemoDataMenuItem, exportMenuItem);

        //create Database Menu
        Menu editMenu = new Menu("Edit");
        
                //create MenuItem for managing households
        MenuItem manageDeviceMenuItem = new MenuItem("Manage Device");
        manageDeviceMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DeviceConfigurationWindow dbManage = new DeviceConfigurationWindow();
                    //create new Window (Stage)
                    Stage secondStage = new Stage();
                    secondStage.show();
                try {
                    //Display content in second window
                    dbManage.start(secondStage);
                } catch (Exception ex) {
                    Logger.getLogger(DashboardWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            
        });

        
        //create MenuItem for managing households
        MenuItem manageHouseholdMenuItem = new MenuItem("Manage Households");
        manageHouseholdMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HouseholdConfigurationWindow dbManage = new HouseholdConfigurationWindow();
                try {
                    //create new Window (Stage)
                    Stage secondStage = new Stage();
                    secondStage.show();
                    //Display content in second window
                    dbManage.start(secondStage);
                } catch (Exception ex) {
                    System.out.println("Open Manage Connection failed");
                }
            }
        });
        //add all MenuItems to Menu
        editMenu.getItems().addAll(manageDeviceMenuItem, manageHouseholdMenuItem);

        //add all menues to the MenuBar
        mMenuBar.getMenus().addAll(fileMenu, editMenu);

        //set default selection to first item
        this.mDevicesList.getSelectionModel().select(0);
    }

    public static void main(String[] args) {
        launch(args);
    }

    //Getter
    public Label getmSelectedConnectionProfileLabel() {
        return this.mEventCounterLabel;
    }

    public Label getmConnectionStatusLabel() {
        return this.mConnectionStatusLabel;
    }
}
