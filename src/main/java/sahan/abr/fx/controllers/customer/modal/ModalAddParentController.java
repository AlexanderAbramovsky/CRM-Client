package sahan.abr.fx.controllers.customer.modal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModalAddParentController {

    @FXML
    private TextField textFieldSurname;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldMiddleName;

    @FXML
    private TextArea textFieldPassport;

    @FXML
    private TextField textFieldPhoneNumber;

    @FXML
    private TextField textFieldContactPhoneNumber;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private Button saveButton;

    @FXML
    void saveParent(ActionEvent event) {

        /* customer = new Customer(new Parent(0, textFieldSurname.getText(), textFieldName.getText(),
                textFieldMiddleName.getText(), textFieldPassport.getText(), textFieldPhoneNumber.getText(),
                textFieldContactPhoneNumber.getText(), textFieldEmail.getText(), null));

        observableListCustomers.add(customer);
*/
        // get a handle to the stage
        Stage stage = (Stage) saveButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

}
