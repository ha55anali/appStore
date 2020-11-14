package GUI.screens.devPanel;

import GUI.screens.appListByDev.AppListByDevController;
import GUI.screens.devAppDetails.DevAppDetailsController;
import GUI.screens.devProfile.DevProfileController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class DevPanelController {

    @FXML
    ImageView developerProfileButton;

    int developerID;

    public void setDeveloperID(int devID){
        developerID=devID;
    }

    public void publishNewApp(MouseEvent event) throws IOException {
        FXMLLoader devAppDetailsLoader = new FXMLLoader(getClass().getResource("../devAppDetails/devAppDetails.fxml"));
        Parent root_devAppDetails = devAppDetailsLoader.load();
        DevAppDetailsController devAppDetailsController = devAppDetailsLoader.getController();
        devAppDetailsController.setDeveloperID(this.developerID);
        //devAppDetailController.setCategory("Productivity");
        Scene appList = new Scene(root_devAppDetails,1000,800);

        Stage window = new Stage();

        window.setScene(appList);
        window.show();
    }

    public void updateApp(MouseEvent event) throws IOException {
        FXMLLoader appListByDevLoader = new FXMLLoader(getClass().getResource("../appListByDev/appListByDev.fxml"));
        Parent root_appListByDev = appListByDevLoader.load();
        AppListByDevController appListByDevController = appListByDevLoader.getController();
        appListByDevController.setDeveloperID(this.developerID);
        appListByDevController.setMode(1);
        appListByDevController.populateList();  //this function gets the apps from DB and populates the list on its own


        Scene appList = new Scene(root_appListByDev,1000,800);

        Stage window = new Stage();

        window.setScene(appList);
        window.show();
    }

    public void removeApp(MouseEvent event) throws IOException{
        FXMLLoader appListByDevLoader = new FXMLLoader(getClass().getResource("../appListByDev/appListByDev.fxml"));
        Parent root_appListByDev = appListByDevLoader.load();
        AppListByDevController appListByDevController = appListByDevLoader.getController();
        appListByDevController.setDeveloperID(this.developerID);
        appListByDevController.setMode(0);
        appListByDevController.populateList();  //this function gets the apps from DB and populates the list on its own


        Scene appList = new Scene(root_appListByDev,1000,800);

        Stage window = new Stage();

        window.setScene(appList);
        window.show();
    }

    public void developerProfileButtonPressed(MouseEvent event) throws IOException {
        FXMLLoader devDetailsLoader = new FXMLLoader(getClass().getResource("../devProfile/devProfile.fxml"));
        Parent root_devDetails = devDetailsLoader.load();
        DevProfileController devProfileController = devDetailsLoader.getController();
        devProfileController.setDeveloperID(this.developerID);
        //devAppDetailController.setCategory("Productivity");
        Scene appList = new Scene(root_devDetails);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(appList);
        window.show();
    }
}
