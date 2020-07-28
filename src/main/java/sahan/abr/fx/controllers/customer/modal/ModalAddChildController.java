package sahan.abr.fx.controllers.customer.modal;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.time.LocalDate;
import java.util.Date;

public class ModalAddChildController {

    @FXML
    private TextField textFieldSurname;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldShortName;

    @FXML
    private TextField textFieldMiddleName;

    @FXML
    private RadioButton radioButtonBoy;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton radioButtonGirl;

    @FXML
    private DatePicker datePickerDateOfBirth;

    @FXML
    private Label labelYears;

    @FXML
    private ComboBox<?> comboBoxEmployees;

    @FXML
    private TextArea textAreaNote;

    @FXML
    private ComboBox<?> comboBoxTypeSubscription;

    @FXML
    private DatePicker datePickerSubscriptionStart;

    @FXML
    private Label labelSubscriptionEndDate;

    @FXML
    private CheckBox checkBoxFreeEntrance;

    @FXML
    private ComboBox<?> comboBoxMonday;

    @FXML
    private ComboBox<?> comboBoxTuesday;

    @FXML
    private ComboBox<?> comboBoxWednesday;

    @FXML
    private ComboBox<?> comboBoxThursday;

    @FXML
    private ComboBox<?> comboBoxFriday;

    @FXML
    private ComboBox<?> comboBoxSaturday;

    @FXML
    private ComboBox<?> comboBoxSunday;

    @FXML
    private Button buttonSaveChild;

    private LocalDate dateOfBirth;
    private LocalDate dateStartSubscription;

    @FXML
    private void initialize() {
        datePickerDateOfBirth.focusedProperty().addListener(new DeterminingDateOfBirth());
        datePickerSubscriptionStart.focusedProperty().addListener(new DeterminingDateEndSubscription());
    }

    @FXML
    void save(ActionEvent event) {

    }

    private class DeterminingDateOfBirth implements ChangeListener<Boolean> {
        @Override
        public void changed(ObservableValue<? extends Boolean> ov, Boolean oldb, Boolean newb) {
            dateOfBirth = datePickerDateOfBirth.getValue();
            LocalDate localDate = LocalDate.now();
            labelYears.setText("Полных лет - " + (localDate.getYear() - dateOfBirth.getYear()));
        }
    }

    private class DeterminingDateEndSubscription implements ChangeListener<Boolean> {
        @Override
        public void changed(ObservableValue<? extends Boolean> ov, Boolean oldb, Boolean newb) {
            dateStartSubscription = datePickerSubscriptionStart.getValue();

            Date date = new Date();
            date.setDate(dateStartSubscription.getDayOfMonth());
            date.setMonth(dateStartSubscription.getMonthValue());
            date.setYear(dateStartSubscription.getYear());

            date.setDate(date.getDate() + 7);

            System.out.println(date.getDay() + "." + date.getMonth() + "." + date.getYear());

            /*dateStartSubscription = LocalDate.of(dateStartSubscription.getYear(),
                    dateStartSubscription.getMonth(), dateStartSubscription.getDayOfMonth() + 7);

            System.out.println(dateStartSubscription.lengthOfMonth());

            labelSubscriptionEndDate.setText(dateStartSubscription.getDayOfMonth() + "."
                    + dateStartSubscription.getMonthValue() + "." + dateStartSubscription.getYear());
                    */

        }
    }
}
