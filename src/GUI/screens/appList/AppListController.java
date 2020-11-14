package GUI.screens.appList;

import GUI.classes.App;
import GUI.screens.categoriesList.CategoriesListController;
import GUI.screens.userProfile.UserProfileController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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



        //listView = new ListView<String>(apps);
        //listView.getItems().addAll("HELLO","WORLD");
        //System.out.println(this.userID+" in appListInitialise");



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
        //System.out.println(this.userID+" in appList setUserID");
        //initialize();

        //FXMLLoader userDetailsLoader = new FXMLLoader(getClass().getResource("../"))
    }

    public void setCategory(String category){
        this.category = category;
        this.headerText.setText(category);

        //get apps according to the list and update the list
        App app1 = new App("App1","Desc1",userID);
        App app2 = new App("App2","Desc2",40);


        ObservableList<String> apps = FXCollections.observableArrayList("App1","App2");

        listView.setItems(apps);
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
//    public void clickText(javafx.event.ActionEvent actionEvent) {
//        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
//        int userID = (int)window.getUserData();
//
//        //userID_text.setText(String.valueOf(userID));
//        System.out.println(userID);
//    }
}
