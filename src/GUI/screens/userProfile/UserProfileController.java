package GUI.screens.userProfile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class UserProfileController {

    @FXML
    Text userID_text;

    @FXML
    TextField email_field;

    @FXML
    TextField name_field;

    @FXML
    TextField password_field;

    @FXML
    TextField confirmPassword_field;

    @FXML
    Button updateProfileButton;

    @FXML
    Button signoutButton;

    @FXML
    Button deleteProfileButton;

    int userID;

    @FXML
    ImageView backIcon;

    Scene previousScene;

    @FXML
    public void initialize()
    {
        System.out.println(this.userID + " in user details initialise");
    }

    public void setUserID(int userID){
        this.userID = userID;
        System.out.println(this.userID + " in user details setUserID");
    }

    public void setPreviousScene (Scene prevScene){
        this.previousScene=prevScene;
    }

    public void populateData()
    {
        blInterface.userInterface user = businessLayer.blFactory.getUserObject();
        blInterface.userDetails deets;
        try {
            //userID=user.addUser(deets);
            deets = user.getUserDetails(userID);

            System.out.println(userID+" in userDetails populateData");
            userID_text.setText(Integer.toString(deets.userID));
            email_field.setText(deets.email);
            name_field.setText(deets.Name);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backIconClicked(MouseEvent event) throws IOException {
        //FXMLLoader appListLoader= new FXMLLoader(getClass().getResource("../appList/appList.fxml"));
        //Parent root_appList = appListLoader.load();
        //AppListController appListController = appListLoader.getController();
        //appListController.setUserID(this.userID);
        //Scene appList = new Scene(root_appList);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        System.out.println(userID+" in user details backButtonClicked");
        //window.setUserData(userID);

        window.setScene(previousScene);
        window.show();
    }

    public void deleteProfileButtonClicked(ActionEvent event){
        blInterface.userInterface user = businessLayer.blFactory.getUserObject();
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete your profile?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                //do stuff
                user.removeUser(userID);

                Parent root_login = FXMLLoader.load(getClass().getResource("../loginScreen/loginScreen.fxml"));
                Scene loginScene = new Scene(root_login);

                //This line gets the Stage information
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                window.setScene(loginScene);
                window.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signoutButtonClicked(ActionEvent event){
        blInterface.userInterface user = businessLayer.blFactory.getUserObject();
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to sign out?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                Parent root_login = FXMLLoader.load(getClass().getResource("../loginScreen/loginScreen.fxml"));
                Scene loginScene = new Scene(root_login);

                //This line gets the Stage information
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                window.setScene(loginScene);
                window.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
