package UserInterface.DeviceConfigurationWindow;


import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeviceConfigurationWindow {

    //all member attributes
    //textfields
    TextField mDeviceNameTF;
    TextField DeviceCategoryTF;
    TextField mDeviceConsumptionTF;

    //buttons
    Button mSaveProfileButton;
    Button mDeleteProfileButton;
    Button mSelectProfileButton;
    Button mCloseWindowButton;

    //comboboxes
    ComboBox mSwitchableComboBox;
    
    //ListViews
    ListView mExistingDevicesLV;
    
    
    //todo: Exception when deleting a ConnectionProfile
    

    public void start(Stage stage) throws Exception {

        //get layout ressources
        Parent root = FXMLLoader.load(getClass().getResource("DeviceConfigurationWindow.fxml"));
        //add layout to Scene (window)
        Scene scene = new Scene(root);
        //display scene
        stage.setScene(scene);

        //get listview from screen
        this.mExistingDevicesLV = (ListView) scene.lookup("#existing_profiles_list");

        //get Textfields from scene to be able to import information
        this.mDeviceNameTF = (TextField) scene.lookup("#connection_alias_name_textfield");
        this.DeviceCategoryTF = (TextField) scene.lookup("#connection_sever_adress_textfield");
        this.mDeviceConsumptionTF = (TextField) scene.lookup("#connection_sever_port_textfield");

        //get empty ComboBox from Screen
        this.mSwitchableComboBox = (ComboBox) scene.lookup("#connection_provider_combobox");

        //get Buttons from scene
        this.mSaveProfileButton = (Button) scene.lookup("#save_connection_profile_button");
        this.mDeleteProfileButton= (Button) scene.lookup("#delete_connection_profile_button");
        this.mSelectProfileButton= (Button)scene.lookup("#select_connection_profile_button");
        this.mCloseWindowButton = (Button) scene.lookup("#database_connection_cancel_button");


        
        
        
        //default value for a new device
        //this.mSwitchableComboBox.getSelectionModel().select("True");

        
        
        
        //fill ListView with existing profiles
        this.refreshExistingDevicesLV();

        /*
        
        
        //add change Listener  to show attributes for selected connectionprofile
        this.mExistingDevicesLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //do not execute this if a list item is delted
                
                //check if new selected value isn´t the same than it was before
                    ConnectionProfile searchedProfile = null;
                    for (ConnectionProfile tempConnProfile : DatabaseController.savedConnectionProfiles) {
                        if (newValue.equals(tempConnProfile.getmConnectionProfileAliasName())) {
                            searchedProfile = tempConnProfile;
                            break;
                        }
                    }
                    if (searchedProfile == null) {
                        System.out.println("Error while searching Profile");
                    } else {
                        //todo:this does not work so far, no text is displayded on screen --> maybe profile isn´t found
                        //fill up all fields on the screen with corresponding values
                        mDeviceNameTF.setText(searchedProfile.getmConnectionProfileAliasName());
                        DeviceCategoryTF.setText(searchedProfile.getmAdress());
                        mDeviceConsumptionTF.setText(searchedProfile.getmPort());
                        mConnectionUsernameTF.setText(searchedProfile.getmUsername());
                        mConnectionUserPasswordPF.setText(searchedProfile.getmPassword());
                        mSwitchableComboBox.getSelectionModel().select(searchedProfile.getmProvider());
                    }
              }
        });


*/

        //Create Labels that should be displayed in ComboBox
        String selectionLaundry = "Laundry";
        String selectionDishwasher ="Dishwasher";

        //add content to ComboBox
        this.mSwitchableComboBox.getItems().addAll(selectionLaundry, selectionDishwasher);

        //set default value
        this.mSwitchableComboBox.getSelectionModel().select(selectionLaundry);

        //assign actions to buttons
        this.mSaveProfileButton.setOnAction(new EventHandler<ActionEvent>() {
            //save all entries and then close stage
            //todo: ask if changes should be applied and then close window
            public void handle(ActionEvent event) {

                try {
                    //get values out of Layout
                    String connectionAlias = mDeviceNameTF.getText();
                    String serverAdress = DeviceCategoryTF.getText();
                    String serverPort = mDeviceConsumptionTF.getText();
                    String provider = mSwitchableComboBox.getSelectionModel().getSelectedItem().toString();

                    
                    
                    
                    //todo:
                    //create Device

                    
                    
                    
                    
                    //refresh ListView that new entry is displayed
                    refreshExistingDevicesLV();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        
        //define action for clicking "close window" Button
        this.mCloseWindowButton.setOnAction(new EventHandler<ActionEvent>() {
            //todo: ask if changes should be applied and then close window
            //discard changes and close the window
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
        
        
        
        //todo: create possibility to click on existing device
        /*
        //define action for clicking "select" Button
        this.mSelectProfileButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event) {
                String searchTerm = (String) mExistingDevicesLV.getSelectionModel().getSelectedItem();
                ConnectionProfile selectedProfile = null;
                //search for profile
                 for (ConnectionProfile tempConnProfile : DatabaseController.savedConnectionProfiles) {
                        if (searchTerm.equals(tempConnProfile.getmConnectionProfileAliasName())) {
                            selectedProfile = tempConnProfile;
                            break;
                        }
                    }
                    //set selectedProfile if Profile is found
                    if(selectedProfile!=null){
                        DatabaseController.setSelectedProfile(selectedProfile);                  
                    }else{
                        System.out.println("Error while applying Profile: Profile not found!");
                    }
            }
        });
        
        
                //define action for clicking "delete" Button
        this.mDeleteProfileButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event) {
                String searchTerm = (String) mExistingDevicesLV.getSelectionModel().getSelectedItem();
                ConnectionProfile profileToDelete = null;
                //search for profile
                 for (ConnectionProfile tempConnProfile : DatabaseController.savedConnectionProfiles) {
                        if (searchTerm.equals(tempConnProfile.getmConnectionProfileAliasName())) {
                            profileToDelete = tempConnProfile;
                            break;
                        }           
                    }
                 //delete ConnectionProfile if Profile is found
                    if(profileToDelete!=null){
                        //deselect profile
                        mExistingDevicesLV.getSelectionModel().clearSelection();
                        //remove ConnectionProfile
                        DatabaseController.savedConnectionProfiles.remove(profileToDelete);
                        //derefer ConnectionProfile and let GC do the rest
                        profileToDelete=null;
                        //refresh List of existing profiles
                        refreshExistingDevicesLV();

                    }else{
                        System.out.println("Error while deleteing Profile: Profile not found!");
                    }
                
            }
        });
        
*/
    }

    
    private void refreshExistingDevicesLV(){
    //reset LV
    this.mExistingDevicesLV.getItems().clear();
    
    
    //refill LV
    ArrayList<String> contentForLV = new ArrayList();
     //iterate through all available connection profiles
     
     //todo create possibility to refill list
     /*
        for (ConnectionProfile tempConnProfile : DatabaseController.savedConnectionProfiles) {
            //add Connection alias into ListView
            contentForLV.add(tempConnProfile.getmConnectionProfileAliasName());
        }
*/
        this.mExistingDevicesLV.getItems().addAll(contentForLV);
    }
}
