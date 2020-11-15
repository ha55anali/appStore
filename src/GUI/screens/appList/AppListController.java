package GUI.screens.appList;

import GUI.screens.appDetails.AppDetailsController;
import GUI.screens.categoriesList.CategoriesListController;
import GUI.screens.userProfile.UserProfileController;
import blInterface.App;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class AppListController<string> {
    @FXML
    Text headerText;

    @FXML
    ListView<String> listView;

    @FXML
    TextField userID_text;

    @FXML
    ImageView userProfileIcon;

    @FXML
    ImageView backIcon;

    int userID;
    String category;
    Scene previousScene;

    @FXML
    public void initialize(){
        listView.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent mouseEvent) {
                String selectedAppString = listView.getSelectionModel().getSelectedItem();
                StringTokenizer tokenizer= new StringTokenizer(selectedAppString,":");
                String selectedAppIDString = tokenizer.nextToken();
                int selectedAppID = Integer.parseInt(selectedAppIDString);

                Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();


                FXMLLoader appDetailsLoader= new FXMLLoader(getClass().getResource("../appDetails/appDetails.fxml"));
                Parent root_appDetails = null;
                try {
                    root_appDetails = appDetailsLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                AppDetailsController appDetailsController = appDetailsLoader.getController();
                appDetailsController.setAppID(selectedAppID);
                appDetailsController.setUserID(userID);
                appDetailsController.setPreviousScene(((Node)mouseEvent.getSource()).getScene());
                appDetailsController.populateAppData();

                Scene appList = new Scene(root_appDetails);

                //appListController.setCategory("Education");

                window.setScene(appList);
                window.show();
            }
        });

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

    public void setUserID(int userID){
        this.userID=userID;
    }

    public void setCategory(String category){
        this.category = category;
        this.headerText.setText(category);

        blInterface.AppCollectionInterface appCollectionInterface = businessLayer.blFactory.getAppCollectionObject();

        List<App> appList = new ArrayList<App>();

        List<String> appListWithIDs = new ArrayList<String>();

        if(category.equals("Games")){
            appList = appCollectionInterface.showAppsinCategory("Games");
        }
        else if(category.equals("Education")){
            appList = appCollectionInterface.showAppsinCategory("Education");
        }
        else if(category.equals("Communication")){
            appList = appCollectionInterface.showAppsinCategory("Communication");
        }
        else if(category.equals("Productivity")){
            appList = appCollectionInterface.showAppsinCategory("Productivity");
        }
        else if(category.equals("All Apps")){
            appList = appCollectionInterface.showAllApps();
        }

        for(int i=0; i<appList.size(); i++){
            appListWithIDs.add(appList.get(i).AppID+": "+appList.get(i).Name);
        }
        listView.getItems().addAll(appListWithIDs);
        listView.setFixedCellSize(75);
    }

    public void backIconPressed(MouseEvent event) throws IOException {
        FXMLLoader categoriesListLoader= new FXMLLoader(getClass().getResource("../categoriesList/categoriesList.fxml"));
        Parent root_categoriesList = categoriesListLoader.load();
        CategoriesListController categoriesListController = categoriesListLoader.getController();
        categoriesListController.setUserID(this.userID);
        //categoriesListController.setCategory("All Apps");
        Scene appList = new Scene(root_categoriesList);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(appList);
        window.show();
    }
}
