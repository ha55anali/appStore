package GUI.screens.devProfile;

import GUI.screens.devPanel.DevPanelController;
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

public class DevProfileController {

    @FXML
    Text developerID_text;

    @FXML
    TextField name_field;

    @FXML
    TextField email_field;

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

    @FXML
    ImageView backIcon;

    int developerID;

    public void setDeveloperID(int devID){
        developerID = devID;
    }

    public void backIconPressed(MouseEvent event) throws IOException {
        FXMLLoader devPanelLoader = new FXMLLoader(getClass().getResource("../devPanel/devPanel.fxml"));
        Parent root_devPanel = devPanelLoader.load();
        DevPanelController devPanelController = devPanelLoader.getController();
        devPanelController.setDeveloperID(this.developerID);
        //devAppDetailController.setCategory("Productivity");
        Scene appList = new Scene(root_devPanel);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(appList);
        window.show();
    }

    public void signoutButtonClicked(ActionEvent event){
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to sign out?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                Parent root_login = FXMLLoader.load(getClass().getResource("../devLoginScreen/devLoginScreen.fxml"));
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

    public void populateData()
    {
        blInterface.devInterface developer = businessLayer.blFactory.getDevObject();
        blInterface.userDetails deets;
        try {
            //userID=user.addUser(deets);
            deets = developer.getDevDetails(developerID);

            //System.out.println(userID+" in userDetails populateData");
            //userID_text.setText(Integer.toString(deets.userID));
            developerID_text.setText(String.valueOf(developerID));
            email_field.setText(deets.email);
            name_field.setText(deets.Name);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProfileButtonClicked(ActionEvent event){
        //blInterface.devInterface developer = new businessLayer.Dev();
        blInterface.devInterface developer = businessLayer.blFactory.getDevObject();
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete your profile?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                //do stuff
                developer.removeDev(developerID);

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
