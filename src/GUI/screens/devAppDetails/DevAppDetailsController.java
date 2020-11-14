package GUI.screens.devAppDetails;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DevAppDetailsController {

    @FXML
    TextField nameField;

    @FXML
    Spinner<Integer> versionSpinner;

    @FXML
    ComboBox<String> categoryComboBox;

    @FXML
    TextArea descriptionArea;

    @FXML
    Button submitAppButton;

    int developerID;

    @FXML
    public void initialize(){
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100);
        versionSpinner.setValueFactory(valueFactory);
        versionSpinner.setEditable(false);

        String categories[]={"Communication","Education","Games","Productivity"};
        categoryComboBox.setItems(FXCollections.observableArrayList(categories));
    }

    public void setAppDetails(String appName, int version, String description){
        nameField.setText(appName);

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(version,100);
        versionSpinner.setValueFactory(valueFactory);

        descriptionArea.setText(description);
    }

    public void setDeveloperID(int devID){
        developerID = devID;
    }
}
