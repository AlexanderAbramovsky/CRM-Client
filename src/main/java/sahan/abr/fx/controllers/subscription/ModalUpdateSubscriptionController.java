package sahan.abr.fx.controllers.subscription;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sahan.abr.entities.Subscription;

import java.sql.SQLException;

import static sahan.abr.Main.subscriptionRepository;

public class ModalUpdateSubscriptionController {

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
    private Button updateButton;

    private Subscription subscription;

    private SubscriptionController subscriptionController;

    public ModalUpdateSubscriptionController(Subscription subscription, SubscriptionController subscriptionController) {
        this.subscription = subscription;
        this.subscriptionController = subscriptionController;
    }

    @FXML
    private void initialize() {
        textFieldTitleSubscription.setText(subscription.getTitleSubscription());
        textFieldPriceSubscription.setText(subscription.getPriceSubscription() + "");

        int validity = subscription.getValidity();

        switch (validity) {
            case  (7):
                radioButtonWeek.setSelected(true);
                break;
            case (30):
                radioButtonMonth.setSelected(true);
                break;
            case (365):
                radioButtonYear.setSelected(true);
                break;
            default:
                radioButtonDays.setSelected(true);
                break;
        }

        textFieldNumberClasses.setText(subscription.getNumberClasses() + "");

        textFieldValidity.setText(validity + "");
    }

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
    void updateSubscription(ActionEvent event) throws SQLException {

        subscription.setTitleSubscription(textFieldTitleSubscription.getText());
        subscription.setPriceSubscription(Double.parseDouble(textFieldPriceSubscription.getText()));
        subscription.setNumberClasses(Integer.parseInt(textFieldNumberClasses.getText()));
        subscription.setValidity(Integer.parseInt(textFieldValidity.getText()));

        subscriptionController.getTableViewSubscriptions().refresh();

        // get a handle to the stage
        Stage stage = (Stage) updateButton.getScene().getWindow();
        // do what you have to do

        subscriptionRepository.update(subscription);
        stage.close();

    }


}




