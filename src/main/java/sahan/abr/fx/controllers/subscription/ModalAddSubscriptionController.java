package sahan.abr.fx.controllers.subscription;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import sahan.abr.entities.Subscription;

import java.sql.SQLException;

import static sahan.abr.Main.observableListSubscriptions;
import static sahan.abr.Main.subscriptionRepository;

public class ModalAddSubscriptionController {

    @FXML
    private TextField textFieldTitleSubscription;

    @FXML
    private TextField textFieldPriceSubscription;

    @FXML
    private TextField textFieldNumberClasses;

    @FXML
    private TextField textFieldValidity;

    @FXML
    private RadioButton radioButtonDays;

    @FXML
    private ToggleGroup validity;

    @FXML
    private RadioButton radioButtonWeek;

    @FXML
    private RadioButton radioButtonMonth;

    @FXML
    private RadioButton radioButtonYear;

    @FXML
    private Button saveButton;

    @FXML
    void addValidity(ActionEvent event) {

        textFieldValidity.setEditable(false);

        if (event.getSource().equals(radioButtonDays)) {
            textFieldValidity.setText("1");
            textFieldValidity.setEditable(true);
        }

        if (event.getSource().equals(radioButtonWeek)) {
            textFieldValidity.setText("7");
        }

        if (event.getSource().equals(radioButtonMonth)) {
            textFieldValidity.setText("30");
        }

        if (event.getSource().equals(radioButtonYear)) {
            textFieldValidity.setText("365");
        }
    }

    @FXML
    void saveSubscription(ActionEvent event) throws SQLException {
        // get a handle to the stage
        Stage stage = (Stage) saveButton.getScene().getWindow();

        Subscription subscription = new Subscription(
                textFieldTitleSubscription.getText(),
                Double.parseDouble(textFieldPriceSubscription.getText()),
                Integer.parseInt(textFieldValidity.getText()),
                Integer.parseInt(textFieldNumberClasses.getText())
        );

        subscriptionRepository.save(subscription);
        observableListSubscriptions.add(subscription);

        // do what you have to do
        stage.close();
    }

}
