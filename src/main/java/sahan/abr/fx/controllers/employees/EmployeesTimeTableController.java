package sahan.abr.fx.controllers.employees;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sahan.abr.entities.Employee;
import sahan.abr.entities.Schedule;
import sahan.abr.fx.controllers.Navigator;
import sahan.abr.lib.LibSRM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static sahan.abr.Main.*;

@Slf4j
public class EmployeesTimeTableController {

    @FXML
    private Button copyNextWeek;

    @FXML
    private Label labelWeek;

    @FXML
    private Label labelMonday;

    @FXML
    private Label labelTuesday;

    @FXML
    private Label labelWednesday;

    @FXML
    private Label labelThursday;

    @FXML
    private Label labelFriday;

    @FXML
    private Label labelSaturday;

    @FXML
    private Label labelSunday;

    @FXML
    private Button buttonThisWeek;

    @FXML
    private ComboBox<String> comboBoxPosition;

    @FXML
    private TextField textFieldSearchTitle;

    @FXML
    private GridPane gridPaneEmployeesTimeTable;

    private Calendar calendar;
    private Date date;
    private String[] strWeek = new String[8];

    @FXML
    private void initialize() throws SQLException {
        thisWeek();
        comboBoxPosition.setItems(observableListPosition);
    }

    private void addEntry(Employee employee, int countRow) throws SQLException {
        gridPaneEmployeesTimeTable.add(getVBoxEmployee(employee, countRow), 0, countRow);

//        Schedule schedule = null;

        VBox[] vBox = new VBox[8];

        for (int i = 1; i <= 7; i++) {
            vBox[i] = new VBox();

            if (countRow % 2 == 1) {
                vBox[i].setStyle("-fx-background-color : #e6f3fc");
            } else {
                vBox[i].setStyle("-fx-background-color : #9cd3ff");
            }
            vBox[i].getStyleClass().add("border");
            vBox[i].setAlignment(Pos.CENTER);

//            gridPaneEmployeesTimeTable.add(getVBoxAddEntryEmployee(employee, countRow, i, strWeek[i]), i, countRow);

            List<Schedule> schedules = scheduleRepository.getByIdRoleAndDate(employee.getId(), strWeek[i]);

            for (Schedule schedule : schedules) {

                vBox[i].getChildren().add(getVBoxEntryEmployee(employee, schedule, countRow, i, strWeek[i]));
            }

//            if ((schedule =  != null) {
//                //если нет расписания на день недели
//
////                gridPaneEmployeesTimeTable.add(getVBoxEntryEmployee(employee, schedule, countRow, i, strWeek[i]), i, countRow);
//            }

            vBox[i].getChildren().add(getVBoxAddEntryEmployee(employee, countRow, i, strWeek[i]));

            gridPaneEmployeesTimeTable.add(vBox[i], i, countRow);

        }
    }

    private VBox getVBoxEmployee(Employee employee, int countRow) {
        VBox vBoxEmployee = new VBox();
        vBoxEmployee.setAlignment(Pos.CENTER);
        vBoxEmployee.setPadding(new Insets(0,0, 5,5));

        if (countRow % 2 == 1) {
            vBoxEmployee.setStyle("-fx-background-color : #e6f3fc");
        } else {
            vBoxEmployee.setStyle("-fx-background-color :  #9cd3ff");
        }

        vBoxEmployee.getStyleClass().add("border");
        HBox hBoxSurname = new HBox();
        Label labelSurname = new Label(LibSRM.getFIO(employee));
        labelSurname.getStyleClass().add("labelEmployee");
        hBoxSurname.getChildren().add(labelSurname);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        Label labelPosition = new Label(employee.getPosition());
        labelPosition.getStyleClass().add("labelEmployee");
        HBox hBoxButton = new HBox();
        hBoxButton.setPadding(new Insets(0,5, 0,0));
        hBoxButton.setAlignment(Pos.CENTER_RIGHT);
        Button button = new Button("");
        button.getStyleClass().add("toggle-button-journal");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ModalUpdateEmployeeController controller = new ModalUpdateEmployeeController(employee, true);
                Navigator.getModalWindow("SRM", Navigator.MODAL_UPDATE_EMPLOYEE, controller);
            }
        });
        hBoxButton.getChildren().add(button);
        hBox.getChildren().add(labelPosition);
        hBox.getChildren().add(hBoxButton);

        HBox.setHgrow(hBoxButton, Priority.ALWAYS);
        vBoxEmployee.getChildren().addAll(hBoxSurname, hBox);

        return vBoxEmployee;
    }

    public static VBox getVBoxAddEntryEmployee(Employee employee, int countRow, int dayOfWeek, String dateJob) {

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);

        vBox.setPadding(new Insets(10, 0, 10, 0));

        if (countRow % 2 == 1) {
            vBox.setStyle("-fx-background-color : #e6f3fc");
        } else {
            vBox.setStyle("-fx-background-color :  #9cd3ff");
        }

        Button button = new Button("");
        button.getStyleClass().add("toggle-button-add-round");
        button.setOnAction(event -> {
            ModalAddEntryEmployeeTimeTable controller = new ModalAddEntryEmployeeTimeTable(vBox, employee, null, countRow, dateJob);
            Navigator.getModalWindow("SRM", Navigator.MODAL_ADD_ENTRY_EMPLOYEE, controller);
        });

        vBox.getChildren().add(button);

        return vBox;
    }

//    public static void setVBoxAddEntryEmployee(Employee employee, Schedule schedule, int countRow, VBox vBox, String dateJob) {
//
//        Button button = new Button("");
//        button.getStyleClass().add("toggle-button-add-round");
//        button.setOnAction(event -> {
//            ModalAddEntryEmployeeTimeTable controller = new ModalAddEntryEmployeeTimeTable(vBox, employee, schedule, countRow, dateJob);
//            Navigator.getModalWindow("SRM", Navigator.MODAL_ADD_ENTRY_EMPLOYEE, controller);
//        });
//
//        vBox.getChildren().add(button);
//    }

    public static VBox getVBoxEntryEmployee(Employee employee, Schedule schedule, int countRow, int dayOfWeek, String dateJob) {

        VBox vBoxEmployee = new VBox();
        vBoxEmployee.setAlignment(Pos.CENTER);
        vBoxEmployee.setPadding(new Insets(0,0, 5,0));

        Label labelTime = new Label("C " + schedule.getSTime() + " по "
                + schedule.getETime());
        labelTime.getStyleClass().add("labelEmployee");

        Label labelPool = new Label("Бассейн - " + schedule.getPoll());
        labelPool.getStyleClass().add("labelEmployee");

        HBox hBoxButtons = new HBox();
        hBoxButtons.setAlignment(Pos.CENTER);
        hBoxButtons.setSpacing(10);

        Button buttonDelete = new Button("");
        buttonDelete.getStyleClass().add("toggle-button-delete");
        buttonDelete.setOnAction(event -> {

            vBoxEmployee.getChildren().removeAll(vBoxEmployee.getChildren());

            //setVBoxAddEntryEmployee(employee, schedule, countRow, vBoxEmployee, dateJob);

            try {
                scheduleRepository.deleteById(schedule.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        Button buttonUpdate = new Button("");
        buttonUpdate.getStyleClass().add("toggle-button-pencil");
        buttonUpdate.setOnAction(event -> {
            ModalAddEntryEmployeeTimeTable controller =
                    new ModalAddEntryEmployeeTimeTable(vBoxEmployee, employee, schedule, countRow, dateJob,true);
            Navigator.getModalWindow("SRM", Navigator.MODAL_ADD_ENTRY_EMPLOYEE, controller);
        });

        hBoxButtons.getChildren().addAll(buttonUpdate, buttonDelete);

        vBoxEmployee.getChildren().addAll(labelTime, labelPool, hBoxButtons);

        return vBoxEmployee;
    }

//    public static void setVBoxEntryEmployee(Employee employee, Schedule schedule, int countRow, VBox vBoxEmployee, String dateJob) {
//        Label labelTime = new Label("C " + schedule.getSTime() + " по "
//                + schedule.getETime());
//        labelTime.getStyleClass().add("labelEmployee");
//
//        Label labelPool = new Label("Бассейн - " + schedule.getPoll());
//        labelPool.getStyleClass().add("labelEmployee");
//
//        HBox hBoxButtons = new HBox();
//        hBoxButtons.setAlignment(Pos.CENTER);
//        hBoxButtons.setSpacing(10);
//
//        Button buttonDelete = new Button("");
//        buttonDelete.getStyleClass().add("toggle-button-delete");
//        buttonDelete.setOnAction(event -> {
//
//            vBoxEmployee.getChildren().removeAll(vBoxEmployee.getChildren());
//
//            setVBoxAddEntryEmployee(employee, schedule, countRow, vBoxEmployee, dateJob);
//
//            try {
//                scheduleRepository.deleteById(schedule.getId());
//            } catch (SQLException e) {
//                log.error("Я сломался (удаление расписания сотрудника) Сотрудник [{}]", schedule);
//                e.printStackTrace();
//            }
//        });
//
//        Button buttonUpdate = new Button("");
//        buttonUpdate.getStyleClass().add("toggle-button-pencil");
//        buttonUpdate.setOnAction(event -> {
//            ModalAddEntryEmployeeTimeTable controller =
//                    new ModalAddEntryEmployeeTimeTable(vBoxEmployee, employee, schedule, countRow, dateJob, true);
//            Navigator.getModalWindow("SRM", Navigator.MODAL_ADD_ENTRY_EMPLOYEE, controller);
//        });
//
//        hBoxButtons.getChildren().addAll(buttonUpdate, buttonDelete);
//
//        vBoxEmployee.getChildren().addAll(labelTime, labelPool, hBoxButtons);
//
//        vBoxEmployee.setPadding(new Insets(0,0, 5,0));
//    }

    public void dateOutput(){
        String day = "";
        String month = "";
        String year = "";

        for (int i = 1; i <= 6; i++) {

            day = ((calendar.get(Calendar.DAY_OF_MONTH) < 10) ? "0" : "") + calendar.get(Calendar.DAY_OF_MONTH);
            month = (((calendar.get(Calendar.MONTH) + 1) < 10) ? "0" : "") + (calendar.get(Calendar.MONTH) + 1);
            year = "" + calendar.get(Calendar.YEAR);
            String[] masYear = year.split("");
            year = masYear[masYear.length - 2] + masYear[masYear.length - 1];

            if (i == 1) labelMonday.setText(day + "." + month + "." + year);
            if (i == 2) labelTuesday.setText(day + "." + month + "." + year);
            if (i == 3) labelWednesday.setText(day + "." + month + "." + year);
            if (i == 4) labelThursday.setText(day + "." + month + "." + year);
            if (i == 5) labelFriday.setText(day + "." + month + "." + year);
            if (i == 6) labelSaturday.setText(day + "." + month + "." + year);

            date.setDate(date.getDate() + 1);
            calendar.setTime(date);
        }

        day = ((calendar.get(Calendar.DAY_OF_MONTH) < 10) ? "0" : "") + calendar.get(Calendar.DAY_OF_MONTH);
        month = (((calendar.get(Calendar.MONTH) + 1) < 10) ? "0" : "") + (calendar.get(Calendar.MONTH) + 1);
        year = "" + calendar.get(Calendar.YEAR);
        String[] masYear = year.split("");
        year = masYear[masYear.length - 2] + masYear[masYear.length - 1];

        labelSunday.setText(day + "." + month + "." + year);

        labelWeek.setText(labelMonday.getText() + " - " + labelSunday.getText());

        strWeek[7] = labelSunday.getText();
        strWeek[1] = labelMonday.getText();
        strWeek[2] = labelTuesday.getText();
        strWeek[3] = labelWednesday.getText();
        strWeek[4] = labelThursday.getText();
        strWeek[5] = labelFriday.getText();
        strWeek[6] = labelSaturday.getText();
    }

    public void thisWeek() throws SQLException {
        calendar = Calendar.getInstance();

        date = calendar.getTime();

        date.setDate(date.getDate() - date.getDay() + 1);
        calendar.setTime(date);

        dateOutput();
        screenUpdate();
    }

    public void nextWeek() throws SQLException {
        date.setDate(date.getDate() - date.getDay() + 1);
        calendar.setTime(date);

        dateOutput();
        screenUpdate();
    }

    public void lastWeek() throws SQLException {
        date.setDate(date.getDate() - 13);
        calendar.setTime(date);

        dateOutput();
        screenUpdate();
    }

    private void screenUpdate() throws SQLException {
        observableListEmployees.removeAll();
        observableListEmployees.setAll(employeeRepository.getAll());

        for (int i = 0; i < observableListEmployees.size(); i++) {
            addEntry(observableListEmployees.get(i), i);
        }
    }

    @FXML
    void actionLastWeek(ActionEvent event) throws SQLException {
        lastWeek();
    }

    @FXML
    void actionNextWeek(ActionEvent event) throws SQLException {
        nextWeek();
    }

    @FXML
    void actionThisWeek(ActionEvent event) throws SQLException {
        thisWeek();
    }

    @FXML
    void copyNextWeek(ActionEvent event) throws SQLException {
//        List<Schedule> thisWeek = new ArrayList<>();
//
//        calendar = Calendar.getInstance();
//        date = calendar.getTime();
//        date.setDate(date.getDate() - date.getDay() + 1);
//        calendar.setTime(date);
//
//        for (int i = 0; i < observableListEmployees.size(); i++) {
//
//            dateOutput();
//
//            thisWeek.addAll(scheduleRepository.getByIdRoleAndDate(observableListEmployees.get(i).getId(), strWeek[i]));
//        }
//
//        List<Schedule> nextWeek = new ArrayList<>();


    }
}
