package GUI.screens.devAppDetails;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class DevAppDetailsController {

    @FXML
    TextField nameField;

    @FXML
    Spinner<Integer> versionSpinner;

    @FXML
    ComboBox<String> categoryComboBox;

    @FXML
    TextArea descriptionArea;

    @FXML
    Button publishAppButton;

    int developerID;
    int appID;
    int mode;

    @FXML
    public void initialize(){
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100);
        versionSpinner.setValueFactory(valueFactory);
        versionSpinner.setEditable(false);

        String categories[]={"Communication","Education","Games","Productivity"};
        categoryComboBox.setItems(FXCollections.observableArrayList(categories));
    }

    public void setMode(int m){
        mode=m;
        // 0=new
        // 1=update;
        //  2=delete
    }

    public void setDeveloperID(int devID){
        developerID = devID;
    }

    public void setAppID(int selectedAppID) {
        appID=selectedAppID;
    }

    public void publishAppButtonClicked(ActionEvent event){

        if(mode==0) //new app
        {
            blInterface.devInterface developer = businessLayer.blFactory.getDevObject();
            ArrayList<Integer> userRatings = new ArrayList<Integer>();
            ArrayList<String> userReviews = new ArrayList<String>();
            blInterface.App app = new blInterface.App(0,nameField.getText(),descriptionArea.getText(),versionSpinner.getValue(),categoryComboBox.getValue(),userRatings,0,userReviews);
            developer.addApp(developerID,app);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "App has been added!", ButtonType.OK);
            alert.showAndWait();
        }
        else if(mode==1) //update
        {
            blInterface.devInterface developer = businessLayer.blFactory.getDevObject();
            ArrayList<Integer> userRatings = new ArrayList<Integer>();
            ArrayList<String> userReviews = new ArrayList<String>();
            blInterface.App app = new blInterface.App(appID,nameField.getText(),descriptionArea.getText(),versionSpinner.getValue(),categoryComboBox.getValue(),userRatings,0,userReviews);
            developer.updateApp(developerID,app);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "App has been updated!", ButtonType.OK);
            alert.showAndWait();
        }


        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    public void populateDevAppDetails() {
        blInterface.individualAppInterface appInterface = businessLayer.blFactory.getIndividualAppObject();
        blInterface.App appObject = appInterface.showDetails(appID);

        nameField.setText(appObject.Name);


        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(appObject.Version,100);
        versionSpinner.setValueFactory(valueFactory);
        versionSpinner.setEditable(false);

        categoryComboBox.getSelectionModel().select(appObject.Category);

        descriptionArea.setText(appObject.Description);
    }


}
