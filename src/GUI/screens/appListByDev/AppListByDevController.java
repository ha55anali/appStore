package GUI.screens.appListByDev;

import GUI.screens.devAppDetails.DevAppDetailsController;
import blInterface.App;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class AppListByDevController {

    @FXML
    ListView<String> listView;

    int developerID;

    int mode;   //  0=delete, 1=update

    @FXML
    public void initialize(){
        listView.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent mouseEvent) {
                String selectedAppString = listView.getSelectionModel().getSelectedItem();
                StringTokenizer tokenizer= new StringTokenizer(selectedAppString,":");
                String selectedAppIDString = tokenizer.nextToken();
                int selectedAppID = Integer.parseInt(selectedAppIDString);

                if(mode==0 || mode==1)  //print menu only with update and new addition
                {
                    Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();


                    FXMLLoader devAppDetailsLoader= new FXMLLoader(getClass().getResource("../devAppDetails/devAppDetails.fxml"));
                    Parent root_devAppDetails = null;
                    try {
                        root_devAppDetails = devAppDetailsLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    DevAppDetailsController devAppDetailsController = devAppDetailsLoader.getController();
                    devAppDetailsController.setDeveloperID(developerID);
                    devAppDetailsController.setAppID(selectedAppID);
                    devAppDetailsController.setMode(mode);
                    devAppDetailsController.populateDevAppDetails();



                    Scene appList = new Scene(root_devAppDetails);

                    //appListController.setCategory("Education");

                    window.setScene(appList);
                    window.show();
                }
                else if(mode==2)    //deletion
                {

                    Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
                    //listView.getItems().clear();
                    blInterface.devInterface developer = businessLayer.blFactory.getDevObject();
                    developer.removeApp(developerID,selectedAppID);
                    //populateList();
                    window.close();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "App has been deleted!", ButtonType.OK);
                    alert.showAndWait();

                }

            }
        });
    }

    public void setDeveloperID(int devID){
        developerID=devID;
    }

    public void populateList(){
        //get apps from DB and populate the list
        blInterface.devInterface developer = businessLayer.blFactory.getDevObject();

        List<App> appList = developer.getDevApps(developerID);

        List<String> appListWithIDs = new ArrayList<String>();


        for(int i=0; i<appList.size(); i++){
            appListWithIDs.add(appList.get(i).AppID+": "+appList.get(i).Name);
        }

        listView.getItems().addAll(appListWithIDs);
        listView.setFixedCellSize(75);
    }

    public void setMode(int m){
        mode=m;
        // 0=new
        // 1=update;
        //  2=delete
    }
}
