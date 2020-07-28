package sahan.abr.fx.controllers.active_subscription;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSOutput;
import sahan.abr.entities.ActiveSubscription;
import sahan.abr.entities.Client;
import sahan.abr.entities.Employee;
import sahan.abr.entities.SubscriptionsReport;
import sahan.abr.fx.controllers.Navigator;
import sahan.abr.fx.controllers.customer.AddClientController;
import sahan.abr.lib.LibSRM;

import java.sql.SQLException;
import java.util.Calendar;

import static sahan.abr.Main.*;

public class Freeze {

    @FXML
    private TextField textFieldSTime;

    @FXML
    private TextField textFieldETime;

    @FXML
    private TextArea textAriaNote;

    @FXML
    private Button freezeButton;

    @FXML
    private Button cancel;

    @FXML
    private Label mainLabel;

    private boolean defrosting;

    private Client client;
    private Calendar calendar = Calendar.getInstance();
    ActiveSubscription activeSubscription;

    public Freeze(Client client) {
        this.client = client;
    }

    @FXML
    private void initialize() throws SQLException {

        activeSubscription = activeSubscriptionRep.getById(client.getIdActiveSubscription());

        if (activeSubscription.getFreezeSubscription() != null && activeSubscription.getFreezeSubscription().equals("Y")) {
            textFieldSTime.setText(activeSubscription.getSTimeFreeze());
            textFieldETime.setText(activeSubscription.getETimeFreeze());
            textAriaNote.setText(activeSubscription.getNoteFreeze());

            mainLabel.setText("Разморозка абонемента");
            freezeButton.setText("Разморозить");
        } else {
            textFieldSTime.setText(LibSRM.getDateStringFormat(calendar));
        }

    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void freeze(ActionEvent event) throws SQLException {

        if (activeSubscription.getFreezeSubscription() != null && activeSubscription.getFreezeSubscription().equals("Y")){
            activeSubscription.setFreezeSubscription("N");
            activeSubscriptionRep.update(activeSubscription);

            Employee employee = employeeRepository.getById(SECURITY_ADMINISTRATOR.getIdEmployee());
            SubscriptionsReport subscriptionsReport = new SubscriptionsReport(
                    client.getId(),
                    employee.getId(),
                    LibSRM.getFIO(employee),
                    LibSRM.getFIO(client),
                    activeSubscription.getTitleSubscription(),
                    LibSRM.getDateStringFormat(Calendar.getInstance()),
                    null,
                    "Разморозка абонемента"
            );
            subscriptionsReportRep.save(subscriptionsReport);

        } else {
            activeSubscription.setFreezeSubscription("Y");
            activeSubscription.setNoteFreeze(textAriaNote.getText());
            activeSubscription.setSTimeFreeze(textFieldSTime.getText());
            activeSubscription.setETimeFreeze(textFieldETime.getText());
            activeSubscriptionRep.update(activeSubscription);

            Employee employee = employeeRepository.getById(SECURITY_ADMINISTRATOR.getIdEmployee());
            SubscriptionsReport subscriptionsReport = new SubscriptionsReport(
                    client.getId(),
                    employee.getId(),
                    LibSRM.getFIO(employee),
                    LibSRM.getFIO(client),
                    activeSubscription.getTitleSubscription(),
                    LibSRM.getDateStringFormat(Calendar.getInstance()),
                    null,
                    "Заморозка абонемента"
            );
            subscriptionsReportRep.save(subscriptionsReport);
        }


        Stage stage = (Stage) freezeButton.getScene().getWindow();
        stage.close();

        AddClientController controller = new AddClientController(client);
        Navigator.loadVista(Navigator.ADD_CUSTOMERS, controller);
    }

}
