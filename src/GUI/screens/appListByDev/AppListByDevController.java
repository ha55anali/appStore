package GUI.screens.appListByDev;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class AppListByDevController {

    @FXML
    ListView<String> listView;

    int developerID;

    int mode;   //  0=delete, 1=update

    public void setDeveloperID(int devID){
        developerID=devID;
    }

    public void populateList(){
        //get apps from DB and populate the list

        listView.getItems().addAll("App1","App2","App3");
        listView.setFixedCellSize(75);
    }

    public void setMode(int m){
        mode=m;
    }
}
