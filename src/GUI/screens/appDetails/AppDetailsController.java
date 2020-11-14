package GUI.screens.appDetails;

import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class AppDetailsController {
    @FXML
    Spinner<Integer> ratingSpinner;

    public void initialize() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5,3);
        ratingSpinner.setValueFactory(valueFactory);
        ratingSpinner.setEditable(false);
    }

}
