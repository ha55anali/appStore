package GUI.screens.categoriesList;

import GUI.screens.appList.AppListController;
import GUI.screens.userProfile.UserProfileController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class CategoriesListController {

    @FXML ImageView allAppsIcon;
    @FXML Text allAppsText;

    @FXML ImageView gamesIcon;
    @FXML Text gamesText;

    @FXML ImageView productivityIcon;
    @FXML Text productivityText;

    @FXML ImageView educationIcon;
    @FXML Text educationText;

    @FXML ImageView communicationIcon;
    @FXML Text communicationText;


    int userID;

    @FXML
    public void initialize(){
        //System.out.println("User id in categories list init: "+ userID);
    }

    public void setUserID(int userID_input){
        this.userID = userID_input;
        System.out.println("User id in categories list set user id: "+ userID);
    }

    public void allAppsClicked (MouseEvent event) throws IOException {
        FXMLLoader appListLoader= new FXMLLoader(getClass().getResource("../appList/appList.fxml"));
        Parent root_appList = appListLoader.load();
        AppListController appListController = appListLoader.getController();
        appListController.setUserID(this.userID);

        Scene appList = new Scene(root_appList);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        appListController.setCategory("All Apps");

        window.setScene(appList);
        window.show();
    }

    public void gamesClicked(MouseEvent event) throws IOException{
        FXMLLoader appListLoader= new FXMLLoader(getClass().getResource("../appList/appList.fxml"));
        Parent root_appList = appListLoader.load();
        AppListController appListController = appListLoader.getController();
        appListController.setUserID(this.userID);

        Scene appList = new Scene(root_appList);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        appListController.setCategory("Games");

        window.setScene(appList);
        window.show();
    }

    public void educationClicked(MouseEvent event) throws IOException{
        FXMLLoader appListLoader= new FXMLLoader(getClass().getResource("../appList/appList.fxml"));
        Parent root_appList = appListLoader.load();
        AppListController appListController = appListLoader.getController();
        appListController.setUserID(this.userID);

        Scene appList = new Scene(root_appList);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        appListController.setCategory("Education");

        window.setScene(appList);
        window.show();
    }

    public void communicationClicked(MouseEvent event) throws IOException{
        FXMLLoader appListLoader= new FXMLLoader(getClass().getResource("../appList/appList.fxml"));
        Parent root_appList = appListLoader.load();
        AppListController appListController = appListLoader.getController();
        appListController.setUserID(this.userID);

        Scene appList = new Scene(root_appList);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        appListController.setCategory("Communication");

        window.setScene(appList);
        window.show();
    }

    public void productivityClicked(MouseEvent event) throws IOException{
        FXMLLoader appListLoader= new FXMLLoader(getClass().getResource("../appList/appList.fxml"));
        Parent root_appList = appListLoader.load();
        AppListController appListController = appListLoader.getController();
        appListController.setUserID(this.userID);

        Scene appList = new Scene(root_appList);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        appListController.setCategory("Productivity");

        window.setScene(appList);
        window.show();
    }

    public void userProfileIconClicked(MouseEvent event) throws IOException {
        FXMLLoader userDetailsLoader = new FXMLLoader(getClass().getResource("../userProfile/userProfile.fxml"));
        Parent root_userProfile = userDetailsLoader.load();
        UserProfileController userProfileController = userDetailsLoader.getController();
        userProfileController.setUserID(this.userID);
        userProfileController.setPreviousScene(((Node)event.getSource()).getScene());

        Scene userProfileScene = new Scene(root_userProfile);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(userProfileScene);
        window.show();

        userProfileController.populateData();
    }
}
