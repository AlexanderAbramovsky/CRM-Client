package sahan.abr.fx.controllers.Users;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sahan.abr.entities.Parent;

public class UserParent {

    @FXML
    private TextField textFieldSurname;

    @FXML
    private TextField textFieldPhoneNumber;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldContactPhoneNumber;

    @FXML
    private TextField textFieldMiddleName;

    @FXML
    private TextField textFieldPassportSeries;

    @FXML
    private TextField textFieldPassportID;

    @FXML
    private TextField textFieldPassportIssuedBy;

    @FXML
    private TextField textFieldPassportIssueDate;

    @FXML
    private TextField textFieldUnitCode;

    @FXML
    private TextField textFieldDateContract;

    @FXML
    private TextField textFieldContractNumber;

    private int idParent;

    private Parent parent;

    UserParent(Parent parent){
        this.parent = parent;
        idParent = parent.getId();
    }

    @FXML
    private void initialize() {
        textFieldSurname.setText(parent.getSurname());
        textFieldName.setText(parent.getName());
        textFieldMiddleName.setText(parent.getMiddleName());
        textFieldPhoneNumber.setText(parent.getPhoneNumber());
        textFieldContactPhoneNumber.setText(parent.getContactPhoneNumber());
       // textFieldPassportSeries.setText(parent.getPassportSeries());
       // textFieldPassportID.setText(parent.getPassportID());
       // textFieldPassportIssuedBy.setText(parent.getPassportIssuedBy());
       // textFieldPassportIssueDate.setText(parent.getPassportIssueDate());
       // textFieldUnitCode.setText(parent.getUnitCode());
       // textFieldDateContract.setText(parent.getDateContract());
       // textFieldContractNumber.setText(parent.getContractNumber());
    }

    @FXML
    void updateData(ActionEvent event) {
        System.out.println("JOB1");
    }

    @FXML
    void saveData(ActionEvent event) {
        System.out.println("JOB2");
    }
}
