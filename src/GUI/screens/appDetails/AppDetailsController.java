package GUI.screens.appDetails;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AppDetailsController {
    @FXML
    Spinner<Integer> ratingSpinner;

    @FXML
    Text headerText;

    @FXML
    Text appName;

    @FXML
    Text version;

    @FXML
    Text averageRating;

    @FXML
    ListView<String> commentsListView;

    @FXML
    Button updateButton;

    @FXML
    Button installButton;

    @FXML
    Button submitRatingButton;

    @FXML
    Button submitCommentButton;

    @FXML
    TextArea commentBox;

    int appID;
    Scene previousScene;
    int userID;



    public void initialize() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5,3);
        ratingSpinner.setValueFactory(valueFactory);
        ratingSpinner.setEditable(false);

        //populateAppData();
    }

    public void setAppID(int id){
        appID=id;
    }

    public void setUserID(int id){
        userID=id;
    }

    public void setPreviousScene(Scene previousScene) {
        this.previousScene = previousScene;
    }

    public void populateAppData(){


        blInterface.individualAppInterface appInterface = businessLayer.blFactory.getIndividualAppObject();
        blInterface.App appObject = appInterface.showDetails(appID);


        int installedVersion = appInterface.checkAppInstalled(appID,userID);


        headerText.setText(appObject.Name);
        appName.setText(appObject.Name);
        //version.setText(String.valueOf(installedVersion));
        averageRating.setText(String.valueOf(appObject.avgRatings));
        commentsListView.getItems().addAll(appObject.Reviews);


        if(installedVersion==-1){

            installButton.setText("Install");
            updateButton.setVisible(false);
            submitCommentButton.setVisible(false);
            submitRatingButton.setVisible(false);
            version.setText(String.valueOf(appObject.Version));
//            ratingSpinner.setVisible(false);
//            commentsListView.setVisible(false);
        }
        else
        {

            updateButton.setVisible(true);
            submitCommentButton.setVisible(true);
            submitRatingButton.setVisible(true);
            installButton.setText("Uninstall");
            version.setText(String.valueOf(installedVersion));
        }
    }

    public void installButtonClicked(ActionEvent event){
        commentsListView.getItems().clear();
        if(installButton.getText().equals("Install")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "App has been installed!", ButtonType.OK);
            alert.showAndWait();
            //populateAppData();

            blInterface.individualAppInterface appInterface = businessLayer.blFactory.getIndividualAppObject();
            appInterface.installApp(appID,userID);

            appName.getScene().getWindow().setWidth(appName.getScene().getWidth()+0.0001);
        }
        else if(installButton.getText().equals("Uninstall")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "App has been uninstalled!", ButtonType.OK);
            alert.showAndWait();
            //populateAppData();

            blInterface.individualAppInterface appInterface = businessLayer.blFactory.getIndividualAppObject();
            appInterface.removeApp(appID,userID);

            appName.getScene().getWindow().setWidth(appName.getScene().getWidth()+0.0001);
        }

        populateAppData();
    }

    public void submitRatingButtonClicked(ActionEvent event){
        commentsListView.getItems().clear();
        blInterface.individualAppInterface appInterface = businessLayer.blFactory.getIndividualAppObject();
        appInterface.addRating(appID,userID,ratingSpinner.getValue());
        appName.getScene().getWindow().setWidth(appName.getScene().getWidth()+0.0001);
        populateAppData();
    }

    public void submitCommentButtonClicked(ActionEvent event){
        commentsListView.getItems().clear();
        //appName.getScene().getWindow().setWidth(appName.getScene().getWidth()-0.0001);
        blInterface.individualAppInterface appInterface = businessLayer.blFactory.getIndividualAppObject();
        appInterface.addReview(appID,userID,commentBox.getText());
        appName.getScene().getWindow().setWidth(appName.getScene().getWidth()+0.0001);
        populateAppData();
    }

    public void backButtonClicked(MouseEvent event){
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(previousScene);
        window.show();
    }

    public void updateButtonClicked(ActionEvent event){
        blInterface.individualAppInterface appInterface = businessLayer.blFactory.getIndividualAppObject();

        blInterface.App appObject = appInterface.showDetails(appID);

        int latestVersion=appObject.Version;
        int installedVersion = appInterface.checkAppInstalled(appID,userID);

        if(latestVersion == installedVersion){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "App is already up to date!", ButtonType.OK);
            alert.showAndWait();
        }
        else{
            commentsListView.getItems().clear();
            int res = appInterface.updateApp(appID,userID);
            if(res==1)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "App updated!", ButtonType.OK);
                alert.showAndWait();
            }

            populateAppData();
        }
    }


}
