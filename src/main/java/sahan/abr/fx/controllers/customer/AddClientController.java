package sahan.abr.fx.controllers.customer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sahan.abr.fx.controllers.Navigator;
import sahan.abr.entities.*;
import sahan.abr.fx.controllers.active_subscription.AddActiveSubscription;
import sahan.abr.fx.controllers.active_subscription.Freeze;
import sahan.abr.lib.LibSRM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import static sahan.abr.Main.*;

public class AddClientController {

    @FXML
    private Button save1;

    @FXML
    private Button save2;

    @FXML
    private Label labelOperation;

    @FXML
    private TextField textFieldSurnameParent;

    @FXML
    private TextField textFieldNameParent;

    @FXML
    private TextField textFieldMiddleNameParent;

    @FXML
    private TextField textFieldPassportSeries;

    @FXML
    private TextField textFieldPassportNumber;

    @FXML
    private TextField textFieldPassportDate;

    @FXML
    private TextField textFieldPassportIssuedBy;

    @FXML
    private TextField textFieldPassportAddress;

    @FXML
    private TextField textFieldContractNumber;

    @FXML
    private TextField textFieldContractDate;

    @FXML
    private TextField textFieldPhoneNumber;

    @FXML
    private TextField textFieldContactPhoneNumber;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldVK;

    @FXML
    private CheckBox checkBoxNotCall;

    @FXML
    private CheckBox checkBoxNotEmail;

    @FXML
    private CheckBox checkBoxNotVK;

    @FXML
    private TextField textFieldSurnameChild;

    @FXML
    private TextField textFieldNameChild;

    @FXML
    private TextField textFieldMiddleNameChild;

    @FXML
    private RadioButton radioButtonBoy;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton radioButtonGirl;

    @FXML
    private TextField textFieldDateOfBirth;

    @FXML
    private Label labelYears;

    @FXML
    private Label labelSubscriptionName;

    @FXML
    private Label labelSubscriptionStartDate;

    @FXML
    private Label labelSubscriptionEndDate;

    @FXML
    private TextArea textAreaNote;

    @FXML
    private TextArea textAriaNoteWeek0;

    @FXML
    private TextArea textAriaNoteWeek1;

    @FXML
    private TextArea textAriaNoteWeek2;

    @FXML
    private TextArea textAriaNoteWeek3;

    @FXML
    private TextArea textAriaNoteWeek4;

    @FXML
    private TextArea textAriaNoteWeek5;

    @FXML
    private TextArea textAriaNoteWeek6;


    @FXML
    private ComboBox<Subscription> comboBoxTypeSubscription;

    @FXML
    private DatePicker datePickerSubscriptionStart;

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
    private Label labelNumberClasses;

    @FXML
    private TextField certificateValidityDateStart;

    @FXML
    private TextField certificateValidityDateEnd;

    @FXML
    private HBox test1;

    @FXML
    private HBox test2;

    @FXML
    private Button freezeButton;

    @FXML
    private CheckBox checkBoxPairLessons;

    private LocalDate dateOfBirth;
    private LocalDate dateStartSubscription;
    private String dateEndSubscriptionStr;
    private String dateStartSubscriptionStr;
    private String dateOfBirthStr;

    private Employee employee;
    private Subscription subscription;
    private String genderStr;
    private String pairLessons = "N";

    private String typeOperation;

    private Client client;

    private boolean informTimeTable;
    private boolean informClient;

    public AddClientController() {
    }

    public AddClientController(Client client) {
        this.client = client;
    }

    public AddClientController(Client client, String typeOperation) {
        this.client = client;
        this.typeOperation = typeOperation;
    }

    public AddClientController(Client client, boolean informTimeTable) {
        this.client = client;
        this.informTimeTable = informTimeTable;
    }

    public AddClientController(Client client, boolean informTimeTable, boolean informClient) {
        this.client = client;
        this.informTimeTable = informTimeTable;
        this.informClient = informClient;
    }

    @FXML
    private void initialize() throws SQLException {

//        test1.setVisible(false);
//        test2.setVisible(false);

        if (client != null) {

            Passport passport = passportRep.getById(client.getIdPassport());
            Contract contract = contractRepository.getById(client.getIdContract());
            Child child = childRep.getById(client.getIdChild());
            ActiveSubscription activeSubscription  = activeSubscriptionRep.getById(client.getIdActiveSubscription());

            textFieldSurnameParent.setText(client.getSurname());
            textFieldNameParent.setText(client.getName());
            textFieldMiddleNameParent.setText(client.getMiddleName());

            textFieldPassportSeries.setText(passport.getSeries() + "");
            textFieldPassportNumber.setText(passport.getNumber() + "");
            textFieldPassportAddress.setText(passport.getAddress());
            textFieldPassportIssuedBy.setText(passport.getIssuedBy());
            textFieldPassportDate.setText(passport.getDate());

            textFieldContractNumber.setText(contract.getNumber());
            textFieldContractDate.setText(contract.getDate());

            textFieldPhoneNumber.setText(client.getPhoneNumber());
            textFieldContactPhoneNumber.setText(client.getContactPhoneNumber());

            certificateValidityDateStart.setText(child.getReferenceSTime());
            certificateValidityDateEnd.setText(child.getReferenceETime());

            textAreaNote.setText(child.getNote());
            textFieldDateOfBirth.setText(child.getDateOfBirth());

            textFieldSurnameChild.setText(child.getSurname());
            textFieldNameChild.setText(child.getName());
            textFieldMiddleNameChild.setText(child.getMiddleName());

            setClientNote();

            if (activeSubscription == null) {
                labelSubscriptionStartDate.setText("С ");
                labelSubscriptionEndDate.setText("По ");
                labelSubscriptionName.setText("Тип ");
                labelNumberClasses.setText("оставшее кол-во занятий ");
            } else {
                labelSubscriptionStartDate.setText("С " + activeSubscription.getSTimeSubscription());
                labelSubscriptionEndDate.setText("По " + activeSubscription.getETimeSubscription());
                labelSubscriptionName.setText("Тип " + activeSubscription.getTitleSubscription());
                labelNumberClasses.setText("оставшее кол-во занятий " + activeSubscription.getClassesSubscription());

                if (activeSubscription.getFreezeSubscription() != null && activeSubscription.getFreezeSubscription().equals("Y")) {
                    freezeButton.setText("Разморозить");
                }
            }

            if (child.getPairLessons() != null && child.getPairLessons().equals("Y")) {
                checkBoxPairLessons.setSelected(true);
            }

            if (child.getGender().equals(Gender.Boy)) {
                radioButtonBoy.setSelected(true);
            } else {
                radioButtonGirl.setSelected(true);
            }

            textFieldDateOfBirth.setText(child.getDateOfBirth());

            labelOperation.setText("Обновление клиента");
        }

        if (typeOperation != null && typeOperation.equals("INFORM")) {
            labelOperation.setText("Информация о клиенте");

            save1.setVisible(false);
            save2.setVisible(false);
        }
    }

    @FXML
    void back(ActionEvent event) {

        if (typeOperation != null && typeOperation.equals("INFORM")) {
            Stage stage = (Stage) freezeButton.getScene().getWindow();
            stage.close();
            return;
        }

        if (informTimeTable && !informClient) {
            Navigator.loadVista(Navigator.TIMETABLE);
        } else {
            Navigator.loadVista(Navigator.CUSTOMERS);
        }
    }

    @FXML
    void saveCustomer(ActionEvent event) throws SQLException {

        if (radioButtonBoy.isSelected()) {
            genderStr = Gender.Boy.name();
        } else {
            genderStr = Gender.Girl.name();
        }

        Passport passport = new Passport(
                null,
                Integer.parseInt(textFieldPassportSeries.getText()),
                Integer.parseInt(textFieldPassportNumber.getText()),
                textFieldPassportDate.getText(),
                textFieldPassportIssuedBy.getText(),
                textFieldPassportAddress.getText());

        Contract contract = new Contract(
                null,
                textFieldContractNumber.getText(),
                textFieldContractDate.getText()
        );

        Child child = new Child(
                null,
                null,
                textFieldSurnameChild.getText(),
                textFieldNameChild.getText(),
                textFieldMiddleNameChild.getText(),
                Gender.valueOf(genderStr),
                textFieldDateOfBirth.getText(),
                textAreaNote.getText(),
                certificateValidityDateStart.getText(),
                certificateValidityDateEnd.getText(),
                pairLessons
        );

        Client newClient = new Client(
                null,
                null,
                null,
                null,
                textFieldSurnameParent.getText(),
                textFieldNameParent.getText(),
                textFieldMiddleNameParent.getText(),
                textFieldPhoneNumber.getText(),
                textFieldContactPhoneNumber.getText());

        if (client != null) {

            //Обновляю клиента, паспорт, контракт, ребенка
            clientObservableList.remove(client);

            // Если на клиенте есть активный абонемент вставляем его
            if (client.getIdActiveSubscription() != null) {
                newClient.setIdActiveSubscription(client.getIdActiveSubscription());
            }

            newClient.setId(client.getId());
            newClient.setIdPassport(client.getIdPassport());
            newClient.setIdContract(client.getIdContract());
            newClient.setIdChild(client.getIdChild());

            passport.setId(client.getIdPassport());
            passport.setIdClient(client.getId());

            contract.setId(client.getIdContract());
            contract.setIdClient(client.getId());

            child.setId(client.getIdChild());
            child.setIdClient(client.getId());

            passportRep.update(passport);
            contractRepository.update(contract);
            childRep.update(child);
            clientRepository.update(newClient);

            client = newClient;
            saveClientNote();

            clientObservableList.add(newClient);
        } else {

            //Сохраняю клиента, паспорт, контракт, ребенка

            passport.setId(passportRep.save(passport));
            contract.setId(contractRepository.save(contract));
            child.setId(childRep.save(child));

            newClient.setIdChild(child.getId());
            newClient.setIdPassport(passport.getId());
            newClient.setIdContract(contract.getId());

            newClient.setId(clientRepository.save(newClient));

            passport.setIdClient(newClient.getId());
            contract.setIdClient(newClient.getId());
            child.setIdClient(newClient.getId());

            passportRep.update(passport);
            contractRepository.update(contract);
            childRep.update(child);

            client = newClient;
            saveClientNote();

            clientObservableList.add(newClient);
        }

        if (informTimeTable) {
            Navigator.loadVista(Navigator.TIMETABLE);
        } else {
            Navigator.loadVista(Navigator.CUSTOMERS);
        }

    }

    @FXML
    void setSubscription(ActionEvent event) {
        subscription = comboBoxTypeSubscription.getValue();
        labelNumberClasses.setText("Кол-во занятий - " + subscription.getNumberClasses());
    }

    @FXML
    void setGender(ActionEvent event) {
        if (radioButtonBoy.isSelected()) {
            genderStr = Gender.Boy.name();
        } else {
            genderStr = Gender.Girl.name();
        }
    }

    @FXML
    void extendSubscription(ActionEvent event) {
        AddActiveSubscription controller = new AddActiveSubscription(client);
        Navigator.getModalWindow("SRM", Navigator.ADD_ACTIVE_SUBSCRIPTION, controller);
    }

    @FXML
    void freezeSubscription(ActionEvent event) {
        Freeze controller = new Freeze(client);
        Navigator.getModalWindow("SRM", Navigator.FREEZE_ACTIVE_SUBSCRIPTION, controller);
    }

    @FXML
    void actionPairLessons(ActionEvent event) {
        if (checkBoxPairLessons.isSelected()) {
            pairLessons = "Y";
        } else {
            pairLessons = "N";
        }
    }

    /*
    0 - понедельник
    ...
    6 - воскресенье
     */
    void saveClientNote() throws SQLException {
        for (int i = 0; i <= 6; i++) {
            ClientNote clientNote = clientNoteRep.getByIdClientAndWeek(""+i, client.getId());

            String text = "";

            if (i == 0) text = textAriaNoteWeek0.getText();
            if (i == 1) text = textAriaNoteWeek1.getText();
            if (i == 2) text = textAriaNoteWeek2.getText();
            if (i == 3) text = textAriaNoteWeek3.getText();
            if (i == 4) text = textAriaNoteWeek4.getText();
            if (i == 5) text = textAriaNoteWeek5.getText();
            if (i == 6) text = textAriaNoteWeek6.getText();

            if (clientNote != null) {
                if (text == null || text.equals("")) {
                    clientNoteRep.deleteById(clientNote.getId());
                } else {
                    clientNote.setNote(text);
                    clientNoteRep.update(clientNote);
                }
            } else {
                if (text == null || text.equals("")) {
                    continue;
                }
                clientNote = new ClientNote(client.getId(), text, ""+i);
                clientNoteRep.save(clientNote);
            }
        }
    }

    void setClientNote()throws SQLException {
        for (int i = 0; i <= 6; i++) {
            ClientNote clientNote = clientNoteRep.getByIdClientAndWeek(""+i, client.getId());

            if (clientNote != null) {
                if (i == 0) textAriaNoteWeek0.setText(clientNote.getNote());
                if (i == 1) textAriaNoteWeek1.setText(clientNote.getNote());
                if (i == 2) textAriaNoteWeek2.setText(clientNote.getNote());
                if (i == 3) textAriaNoteWeek3.setText(clientNote.getNote());
                if (i == 4) textAriaNoteWeek4.setText(clientNote.getNote());
                if (i == 5) textAriaNoteWeek5.setText(clientNote.getNote());
                if (i == 6) textAriaNoteWeek6.setText(clientNote.getNote());
            }
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
            date.setDate(date.getDate() + subscription.getValidity());
            dateStartSubscriptionStr = dateStartSubscription.getDayOfMonth() + "." +
                    dateStartSubscription.getMonthValue() + "." + dateStartSubscription.getYear();
            dateEndSubscriptionStr = date.getDate() + "." + date.getMonth() + "." + date.getYear();
            labelSubscriptionEndDate.setText("по " + date.getDate() + "." + date.getMonth() + "." + date.getYear());
        }
    }

    //    private class DeterminingDateOfBirth implements ChangeListener<Boolean> {
//        @Override
//        public void changed(ObservableValue<? extends Boolean> ov, Boolean oldb, Boolean newb) {
//            dateOfBirth = datePickerDateOfBirth.getValue();
//            LocalDate localDate = LocalDate.now();
//            labelYears.setText("?????? ??? - " + (localDate.getYear() - dateOfBirth.getYear()));
//            dateOfBirthStr = dateOfBirth.getDayOfMonth() + "." +
//                    dateOfBirth.getMonthValue() + "." + dateOfBirth.getYear();
//        }
//    }
}
