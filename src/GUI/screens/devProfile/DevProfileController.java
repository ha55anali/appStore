package GUI.screens.devProfile;

import GUI.screens.devPanel.DevPanelController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
}
