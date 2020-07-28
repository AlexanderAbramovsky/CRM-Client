package sahan.abr.fx.controllers.schedule;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sahan.abr.entities.*;
import sahan.abr.fx.controllers.Navigator;
import sahan.abr.fx.controllers.customer.AddClientController;
import sahan.abr.fx.controllers.employees.ModalAddEntryEmployeeTimeTable;
import sahan.abr.lib.LibSRM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import static sahan.abr.Main.*;

public class TimeTable {

    @FXML
    private Button buttonThisDay;

    @FXML
    private Button buttonLastDay;

    @FXML
    private Button buttonNextDay;

    @FXML
    private DatePicker datePickerCalendar;

    @FXML
    private VBox test;

    @FXML
    private Label labelDayOfWeek;

    @FXML
    private VBox vBoxClientNote;

    @FXML
    private GridPane gridPaneTimeTable;

    HashMap<String, ArrayList<VBox>> hashMapVBox = new HashMap<>();

    ArrayList<String> arrayListTime = new ArrayList<>();

    Calendar calendar;
    Date date;

    @FXML
    private void initialize() throws SQLException {

        arrayListTime.add("");
        arrayListTime.add("09:00");
        arrayListTime.add("09:30");
        arrayListTime.add("10:00");
        arrayListTime.add("10:30");
        arrayListTime.add("11:00");
        arrayListTime.add("11:30");
        arrayListTime.add("12:00");
        arrayListTime.add("12:30");
        arrayListTime.add("13:00");
        arrayListTime.add("13:30");
        arrayListTime.add("14:00");
        arrayListTime.add("14:30");
        arrayListTime.add("15:00");
        arrayListTime.add("15:30");
        arrayListTime.add("16:00");
        arrayListTime.add("16:30");
        arrayListTime.add("17:00");
        arrayListTime.add("17:30");
        arrayListTime.add("18:00");
        arrayListTime.add("18:30");
        arrayListTime.add("19:00");
        arrayListTime.add("19:30");
        arrayListTime.add("20:00");

        thisDay();
        satCalendar();
        setGridPaneTimeTable(getDate());

        if (TIME_TABLE_DATE != null) {
            setDay(TIME_TABLE_DATE);
            TIME_TABLE_DATE = null;
        }
    }


    public void outputLabelDayWeek() {

        String dayOfWeek = "";

        String day = ((calendar.get(Calendar.DAY_OF_MONTH) < 10) ? "0" : "") + calendar.get(Calendar.DAY_OF_MONTH);
        String month = (((calendar.get(Calendar.MONTH) + 1) < 10) ? "0" : "") + (calendar.get(Calendar.MONTH) + 1);
        String year = "" + calendar.get(Calendar.YEAR);
        String[] masYear = year.split("");
        year = masYear[masYear.length - 2] + masYear[masYear.length - 1];

        if (date.getDay() == 1) dayOfWeek = "Понедельник";
        if (date.getDay() == 2) dayOfWeek = "Вторник";
        if (date.getDay() == 3) dayOfWeek = "Среда";
        if (date.getDay() == 4) dayOfWeek = "Четверг";
        if (date.getDay() == 5) dayOfWeek = "Пятница";
        if (date.getDay() == 6) dayOfWeek = "Суббота";
        if (date.getDay() == 0) dayOfWeek = "Воскресенье";

        labelDayOfWeek.setText(dayOfWeek + " " + LibSRM.getDateStringFormat(calendar));
    }

    public void thisDay() throws SQLException {
        calendar = Calendar.getInstance();
        date = calendar.getTime();
        LocalDate localDate = LocalDate.now();
        datePickerCalendar.setValue(localDate);
        outputLabelDayWeek();
        setGridPaneTimeTable(getDate());
    }

    public void nextDay() throws SQLException {
        date.setDate(date.getDate() + 1);
        calendar.setTime(date);
        outputLabelDayWeek();
        setGridPaneTimeTable(getDate());
    }

    public void lasDay() throws SQLException {
        date.setDate(date.getDate() - 1);
        calendar.setTime(date);
        outputLabelDayWeek();
        setGridPaneTimeTable(getDate());
    }

    private void setDay(String day) throws SQLException {
        int[] dateInt = LibSRM.splitDay(day);

        assert dateInt != null;
        date.setDate(dateInt[0]);
        date.setMonth(dateInt[1] - 1);
        date.setYear(dateInt[2]);

        calendar.setTime(date);
        outputLabelDayWeek();
        setGridPaneTimeTable(getDate());
    }

    public String getDate() {
        String day = ((calendar.get(Calendar.DAY_OF_MONTH) < 10) ? "0" : "") + calendar.get(Calendar.DAY_OF_MONTH);
        String month = (((calendar.get(Calendar.MONTH) + 1) < 10) ? "0" : "") + (calendar.get(Calendar.MONTH) + 1);
        String year = "" + calendar.get(Calendar.YEAR);
        String[] masYear = year.split("");
        year = masYear[masYear.length - 2] + masYear[masYear.length - 1];
        return day + "." + month + "." + year;
    }

    private void satCalendar() {
        datePickerCalendar.setDisable(true);
        datePickerCalendar.setStyle("-fx-opacity: 1");
        datePickerCalendar.getEditor().setStyle("-fx-opacity: 1");

        datePickerCalendar.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
            }
        });

        datePickerCalendar.valueProperty().addListener((observable, oldValue, newValue) -> {
        });

        DatePickerSkin datePickerSkin = new DatePickerSkin(datePickerCalendar);
        Node popupContent = datePickerSkin.getPopupContent();

        LocalDate selectedDate = datePickerCalendar.getValue();

        test.getChildren().add(popupContent);
    }

    private void setGridPaneTimeTable(String date) throws SQLException {

        //Заполнение заметок на неделю
        addClientNote();

//        List<Schedule> schedulesEmployee = scheduleRepository.getAllByDateAndRole(date, ScheduleRole.EMPLOYEE);
//        List<Schedule> schedulesClient = scheduleRepository.getAllByDateAndRole(date, ScheduleRole.CLIENT);
//
//        List<Employee> trainers = new ArrayList<>();
//        List<Client> clients = new ArrayList<>();
//
//        //получаем список всех тренеров на текущий день недели
//        for (Schedule schedule : schedulesEmployee) {
//            Employee employee = employeeRepository.getById(schedule.getIdRole());
//            if (employee.getPosition().equals(EmployeePosition.TRAINER)) {
//                trainers.add(employee);
//            }
//        }
//
//        //получаем всех клиентов на 3 бассейна на день недели
//        for (Schedule schedule : schedulesClient) {
//            clients.add(clientRepository.getById(schedule.getIdRole()));
//        }

        VBox[][] vBoxes = new VBox[25][3];

        for (int i = 0; i < observableListPoll.size(); i++) {

            // настройка названия бассейна
            VBox boxNamePoll = new VBox();
            boxNamePoll.setStyle("-fx-background-color:  #e6f3fc; -fx-border-color: #59a7ff");
            boxNamePoll.setAlignment(Pos.CENTER);
            Label labelNamePoll = new Label(observableListPoll.get(i));
            labelNamePoll.getStyleClass().add("labelNamePoll");

            boxNamePoll.getChildren().add(labelNamePoll);

            vBoxes[0][i] = boxNamePoll;
            gridPaneTimeTable.add(boxNamePoll, i + 1, 0);

            // вставляем таблицы во все ячейки разных цветов
            for (int j = 1; j < 24; j++) {
                VBox box = new VBox();

                box.setPadding(new Insets(10, 10, 10, 10));
                box.setSpacing(10);
                box.setFillWidth(true);

                if (j % 2 == 0) {
                    box.setStyle("-fx-background-color: #e6f3fc; -fx-border-color: #59a7ff");
                } else {
                    box.setStyle("-fx-background-color: #9cd3ff; -fx-border-color: #59a7ff");
                }

                box.setAlignment(Pos.CENTER);
                vBoxes[j][i] = box;
                gridPaneTimeTable.add(box, i + 1, j);
            }

            for (Schedule schedule : scheduleRepository.getAllByDateAndRoleAndPoll(date, ScheduleRole.EMPLOYEE, observableListPoll.get(i))) {
                Employee employee = employeeRepository.getById(schedule.getIdRole());


                if (employee.getPosition().equals(EmployeePosition.ADMINISTRATOR)) continue;

                int start = arrayListTime.indexOf(schedule.getSTime());
                int end = arrayListTime.indexOf(schedule.getETime());
                // выставляем время работы для каждого сотрудника
                for (int j = start; j <= end; j++) {
                    Label label = new Label("Тренер - " + LibSRM.getFIO(employee));
                    label.getStyleClass().add("labelEmployee");
                    vBoxes[j][i].getChildren().add(label);
                }
            }

            int iFinal = i;

            for (Schedule schedule : scheduleRepository.getAllByDateAndRoleAndPoll(date, ScheduleRole.CLIENT, observableListPoll.get(i))) {

                Client client = clientRepository.getById(schedule.getIdRole());

                if (client == null) continue;

                Child child = childRep.getById(client.getIdChild());

                ActiveSubscription activeSubscription;
                if (client.getIdActiveSubscription() == null) {
                    activeSubscription = null;
                } else {
                    activeSubscription = activeSubscriptionRep.getById(client.getIdActiveSubscription());
                }

                int start = arrayListTime.indexOf(schedule.getSTime());

                HBox hBoxMain = new HBox();
                HBox hBoxButton = new HBox();
                VBox vBoxLabel = new VBox();
                VBox vBoxButtonWas = new VBox();

                hBoxMain.setAlignment(Pos.CENTER);
                hBoxButton.setAlignment(Pos.CENTER);
                vBoxLabel.setAlignment(Pos.CENTER);
                vBoxButtonWas.setAlignment(Pos.CENTER);
                hBoxButton.setPadding(new Insets(0, 0, 0, 20));

                Label labelFioClient = new Label("Клиент: " + LibSRM.getFIO(client));
                Label labelCountClasses = new Label("Занятия: " + ((client.getIdActiveSubscription() != null) ? activeSubscription.getClassesSubscription() : "Нет абонемента"));
                labelFioClient.getStyleClass().add("labelEmployee");
                labelCountClasses.getStyleClass().add("labelEmployee");

                Button buttonInformClient = new Button();
                Button buttonDelete = new Button();
                Button buttonWas = new Button("Был");
                Button buttonWasNot = new Button("Не был");

                buttonWas.setPrefSize(70, 25);
                buttonWasNot.setPrefSize(70, 25);

                buttonDelete.setOnAction(event -> {
                    DeleteSchedule controller = new DeleteSchedule(schedule);
                    Navigator.getModalWindow("SRM", Navigator.MODAL_DELETE_SCHEDULE, controller);
                });

                buttonInformClient.setOnAction((ActionEvent event) -> {
                    AddClientController controller = new AddClientController(client, true);
                    Navigator.loadVista(Navigator.ADD_CUSTOMERS, controller);
                    TIME_TABLE_DATE = LibSRM.getDateStringFormat(calendar);
                });

                buttonWas.setOnAction((ActionEvent event) -> {
                    try {
                        if (client.getIdActiveSubscription() == null) return;
                        activeSubscription.setClassesSubscription(activeSubscription.getClassesSubscription() - 1);
                        activeSubscriptionRep.update(activeSubscription);
                        scheduleRepository.updateWas(schedule.getId(), "Y");
                        labelCountClasses.setText("Занятия: " + activeSubscription.getClassesSubscription());
                        hBoxMain.setStyle("-fx-background-color: #C1FF64;");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });

                buttonWasNot.setOnAction((ActionEvent event) -> {
                    if (client.getIdActiveSubscription() == null) return;
                    try {
                        activeSubscription.setClassesSubscription(activeSubscription.getClassesSubscription() - 1);
                        activeSubscriptionRep.update(activeSubscription);
                        scheduleRepository.updateWas(schedule.getId(), "N");
                        labelCountClasses.setText("Занятия: " + activeSubscription.getClassesSubscription());
                        hBoxMain.setStyle("-fx-background-color: #DCDCDC;");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });

                //если клиент был меняем фон
                if (schedule.getWas() != null) {
                    if (schedule.getWas().equals("Y")) hBoxMain.setStyle("-fx-background-color: #C1FF64;");
                    if (schedule.getWas().equals("N")) hBoxMain.setStyle("-fx-background-color: #DCDCDC;");
                }

                //парные занятия
                Button buttonAdd = null;
                if (child.getPairLessons() != null && child.getPairLessons().equals("Y")) {
                    if (vBoxes[start][i].getChildren().size() == 1) {
                        buttonAdd = new Button("");
                        buttonAdd.getStyleClass().add("toggle-button-square");

                        int finalJ = start;
                        int finalI = i;
                        buttonAdd.setOnAction(event -> {
                            String time = arrayListTime.get(finalJ);
                            String poll = observableListPoll.get(finalI);
                            // только парные занятия
                            AddSchedule controller = new AddSchedule(date, time, time, poll, true);
                            Navigator.getModalWindow("SRM", Navigator.MODAL_ADD_SCHEDULE, controller);
                        });
                    } else {
                        vBoxes[start][i].getChildren().remove(2);
                    }
                }

                //если осталось два последних занятия
                if (activeSubscription.getClassesSubscription() <= 2) {
                    hBoxMain.setStyle("-fx-background-color: #F4FF49;");
                }

                //если абонемент заблакирован
                if (activeSubscription.getFreezeSubscription() != null && activeSubscription.getFreezeSubscription().equals("Y")) {
                    hBoxMain.setStyle("-fx-background-color: #E48C8C;");
                    buttonWas.setDisable(true);
                    buttonWasNot.setDisable(true);
                }

                buttonInformClient.getStyleClass().add("toggle-button-journal");
                buttonDelete.getStyleClass().add("toggle-button-delete");
                buttonWas.getStyleClass().add("toggle-button");
                buttonWasNot.getStyleClass().add("toggle-button");

                hBoxMain.setSpacing(10);
                vBoxButtonWas.setSpacing(5);
                hBoxButton.setSpacing(10);

                vBoxButtonWas.getChildren().addAll(buttonWas, buttonWasNot);
                hBoxButton.getChildren().addAll(buttonDelete, buttonInformClient);
                vBoxLabel.getChildren().addAll(labelFioClient, labelCountClasses);
                hBoxMain.getChildren().addAll(vBoxButtonWas, vBoxLabel, hBoxButton);

                vBoxes[start][i].getChildren().add(hBoxMain);

                if (buttonAdd != null) {
                    vBoxes[start][i].getChildren().add(buttonAdd);
                }
            }

            for (int j = 1; j < 24; j++) {
                if (vBoxes[j][i].getChildren().size() == 1) {
                    Button button = new Button("");
                    button.getStyleClass().add("toggle-button-square");

                    int finalJ = j;
                    int finalI = i;
                    button.setOnAction(event -> {
                        String time = arrayListTime.get(finalJ);
                        String poll = observableListPoll.get(finalI);
                        AddSchedule controller = new AddSchedule(date, time, time, poll);
                        Navigator.getModalWindow("SRM", Navigator.MODAL_ADD_SCHEDULE, controller);
                        TIME_TABLE_DATE = LibSRM.getDateStringFormat(calendar);
                    });

                    vBoxes[j][i].getChildren().add(button);
                }
            }

//            for (Schedule schedule : scheduleRepository.getAllByDateAndRoleAndPool(date, ScheduleRole.EMPLOYEE, observableListPoll.get(i))) {
//
//                VBox box = new VBox();
//                box.setStyle("-fx-background-color: white; -fx-border-color: #59a7ff");
//
//                if (schedule.getRole().equals(ScheduleRole.EMPLOYEE)) {
//                    Employee employee = employeeRepository.getById(schedule.getIdRole());
//                    Label labelEmployee = new Label(LibSRM.getFIO(employee));
//                    labelEmployee.getStyleClass().add("labelEmployee");
//                    box.getChildren().add(labelEmployee);
//                    box.setStyle("-fx-background-color: #BCFF70; -fx-border-color: #59a7ff");
//
//                    arrayList.add(box);
//                    gridPaneTimeTable.add(box, i + 1, arrayListTime.indexOf(schedule.getSTime()));
//                } else {
//
//                }
//            }

//            for (int j = 1; j < 24; j++) {
//
//                VBox box = new VBox();
//                box.setStyle("-fx-background-color: white; -fx-border-color: #59a7ff");
//
//                for (Employee employee : observableListEmployees) {
//
//                    DateJobEmployee dateJobEmployee = null;
//
////                    if (date.getDay() != 0) {
////                         dateJobEmployee = employee.getTimetable().get(date.getDay());
////                    } else {
////                         dateJobEmployee = employee.getTimetable().get(7);
////                    }
//
//
//                    if (dateJobEmployee == null) continue;
//
//                    if (dateJobEmployee.getDate().equals(LibSRM.getDateStringFormat(calendar))
//                            && dateJobEmployee.getPool().equals(observableListPoll.get(i))
//                            && (dateJobEmployee.getStartTime().compareTo(arrayListTime.get(j)) <= 0)
//                            && (dateJobEmployee.getEndTime().compareTo(arrayListTime.get(j)) >= 0)) {
//                        Label labelEmployee = new Label(LibSRM.getFIO(employee));
//                        labelEmployee.getStyleClass().add("labelEmployee");
//                        box.getChildren().add(labelEmployee);
//                        box.setStyle("-fx-background-color: #BCFF70; -fx-border-color: #59a7ff");
//                    } else {
//
//                    }
//
//                }
//
//                box.setAlignment(Pos.CENTER);
//
//                arrayList.add(box);
//                gridPaneTimeTable.add(box, i + 1, j);
//            }

//            hashMapVBox.put(observableListPoll.get(i), arrayList);
        }

        // gridPaneTimeTable.setGridLinesVisible(true);
    }

    public static void setVBoxAddEntry(Employee employee, Schedule schedule, int countRow, VBox vBox, String dateJob) {

        Button button = new Button("");
        button.getStyleClass().add("toggle-button-add-round");
        button.setOnAction(event -> {
//            ModalAddEntryEmployeeTimeTable controller = new ModalAddEntryEmployeeTimeTable(vBox, employee, schedule, countRow, dateJob);
//            Navigator.getModalWindow("SRM", Navigator.MODAL_ADD_ENTRY_EMPLOYEE, controller);
        });

        vBox.getChildren().add(button);
    }

    @FXML
    void actionLastDay(ActionEvent event) throws SQLException {
        lasDay();
    }

    @FXML
    void actionNextDay(ActionEvent event) throws SQLException {
        nextDay();
    }

    @FXML
    void actionThisDay(ActionEvent event) throws SQLException {
        thisDay();
    }

    private void addClientNote() throws SQLException {

        clientNoteObservableList = FXCollections.observableArrayList();

        if (this.date.getDay() != 0) {
            clientNoteObservableList.addAll(clientNoteRep.getAllByWeek((this.date.getDay() - 1) + ""));
        } else {
            clientNoteObservableList.addAll(clientNoteRep.getAllByWeek(6 + ""));
        }

        vBoxClientNote.getChildren().clear();

        for (int i = 0; i < clientNoteObservableList.size(); i++) {

            HBox hBoxMain = new HBox();

            HBox hBoxFIO = new HBox();
            VBox vBoxFIO = new VBox();

            VBox vBoxNote = new VBox();

            vBoxFIO.setPrefSize(200, 50);
            vBoxNote.setPrefSize(200, 50);

            if (i % 2 == 0) {
                hBoxMain.setStyle("-fx-background-color: #e6f3fc; -fx-border-color: #59a7ff");
            } else {
                hBoxMain.setStyle("-fx-background-color: #9cd3ff; -fx-border-color: #59a7ff");
            }

            ClientNote clientNote = clientNoteObservableList.get(i);

            Button buttonInformClient = new Button();
            buttonInformClient.getStyleClass().add("toggle-button-journal");

            buttonInformClient.setOnAction((ActionEvent event) -> {
                Client client = null;
                try {
                    client = clientRepository.getById(clientNote.getIdClient());
                    if (client.getIdActiveSubscription() != null) {
                        ActiveSubscription activeSubscription = activeSubscriptionRep.getById(client.getIdActiveSubscription());
                        if (activeSubscription.getFreezeSubscription() != null && activeSubscription.getFreezeSubscription().equals("Y")) {
                            hBoxMain.setStyle("-fx-background-color: #E48C8C;");
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                TIME_TABLE_DATE = LibSRM.getDateStringFormat(calendar);
                AddClientController controller = new AddClientController(client, true);
                Navigator.loadVista(Navigator.ADD_CUSTOMERS, controller);
            });

            TextArea textArea = new TextArea();
            textArea.setWrapText(true);
            textArea.setText(clientNote.getNote());
            textArea.setEditable(false);
            vBoxNote.getChildren().add(textArea);


            Client client = null;
            try {
                client = clientRepository.getById(clientNote.getIdClient());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (client == null) continue;

            Label fio = new Label(LibSRM.getFIO(client));
            Label phone = new Label(client.getContactPhoneNumber());
            fio.getStyleClass().add("labelEmployee");
            phone.getStyleClass().add("labelEmployee");
            vBoxFIO.getChildren().addAll(fio, phone);

            hBoxFIO.getChildren().addAll(vBoxFIO, buttonInformClient);

            hBoxMain.getChildren().addAll(hBoxFIO, vBoxNote);

            vBoxClientNote.getChildren().add(hBoxMain);
        }
    }

    @FXML
    void actionDataPicker(ActionEvent event) throws SQLException {

        date.setDate(datePickerCalendar.getValue().getDayOfMonth());
        date.setMonth(datePickerCalendar.getValue().getMonthValue() - 1);
        date.setYear(datePickerCalendar.getValue().getYear());

        calendar.setTime(date);

        setDay(LibSRM.getDateStringFormat(calendar));
    }

//    private void addTextAreaToTable() {
//        TableColumn colBtn = new TableColumn("Заметка");
//
//        Callback<TableColumn<ClientNote, Void>, TableCell<ClientNote, Void>> cellFactory =
//                new Callback<TableColumn<ClientNote, Void>, TableCell<ClientNote, Void>>() {
//                    @Override
//                    public TableCell<ClientNote, Void> call(final TableColumn<ClientNote, Void> param) {
//                        final TableCell<ClientNote, Void> cell = new TableCell<ClientNote, Void>() {
//
//                            private final HBox hBox = new HBox();
//
//                            {
//                                hBox.setSpacing(10);
//                                hBox.setAlignment(Pos.CENTER);
//
//                                ClientNote clientNote = getTableView().getItems().get(getIndex());
//
//                                System.out.println("note - " + clientNote.getNote());
//
//                                TextArea textArea = new TextArea();
//                                textArea.setWrapText(true);
//                                textArea.setText(clientNote.getNote());
//
//                                hBox.getChildren().add(textArea);
//                            }
//
//                            @Override
//                            public void updateItem(Void item, boolean empty) {
//                                super.updateItem(item, empty);
//                                if (empty) {
//                                    setGraphic(null);
//                                } else {
//                                    setGraphic(hBox);
//                                }
//                            }
//                        };
//                        return cell;
//                    }
//                };
//
//        colBtn.setCellFactory(cellFactory);
//
//        tableViewNoteClient.getColumns().add(colBtn);
//
//    }
//
//    private void addLabelsToTable() {
//        TableColumn colBtn = new TableColumn("Клиент");
//
//        Callback<TableColumn<ClientNote, Void>, TableCell<ClientNote, Void>> cellFactory =
//                new Callback<TableColumn<ClientNote, Void>, TableCell<ClientNote, Void>>() {
//                    @Override
//                    public TableCell<ClientNote, Void> call(final TableColumn<ClientNote, Void> param) {
//                        final TableCell<ClientNote, Void> cell = new TableCell<ClientNote, Void>() {
//
//                            private final VBox vBox = new VBox();
//
//                            {
//                                vBox.setSpacing(10);
//                                vBox.setAlignment(Pos.CENTER);
//
//                                ClientNote clientNote = getTableView().getItems().get(getIndex());
//                                Client client = null;
//                                try {
//                                    client = clientRepository.getById(clientNote.getIdClient());
//                                } catch (SQLException e) {
//                                    e.printStackTrace();
//                                }
//
//                                Label fio = new Label(LibSRM.getFIO(client));
//                                Label phone = new Label(client.getContactPhoneNumber());
//
//                                System.out.println("fio - " + LibSRM.getFIO(client));
//
//                                fio.getStyleClass().add("labelEmployee");
//                                phone.getStyleClass().add("labelEmployee");
//
//                                vBox.getChildren().addAll(fio, phone);
//                            }
//
//                            @Override
//                            public void updateItem(Void item, boolean empty) {
//                                super.updateItem(item, empty);
//                                if (empty) {
//                                    setGraphic(null);
//                                } else {
//                                    setGraphic(vBox);
//                                }
//                            }
//                        };
//                        return cell;
//                    }
//                };
//
//        colBtn.setCellFactory(cellFactory);
//
//        tableViewNoteClient.getColumns().add(colBtn);
//
//    }
}
