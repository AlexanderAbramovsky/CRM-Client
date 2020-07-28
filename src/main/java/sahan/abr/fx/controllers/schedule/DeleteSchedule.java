package sahan.abr.fx.controllers.schedule;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import sahan.abr.entities.Schedule;
import sahan.abr.fx.controllers.Navigator;

import java.sql.SQLException;

import static sahan.abr.Main.*;

public class DeleteSchedule {

    @FXML
    private CheckBox checkBoxDeleteAll;

    @FXML
    private Button delete;

    @FXML
    private Button cancel;

    private Schedule schedule;

    public DeleteSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @FXML
    void cancel(ActionEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) cancel.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void delete(ActionEvent event) throws SQLException {

        if (checkBoxDeleteAll.isSelected()) {
            scheduleRepository.deleteAllByIdRoleAndDate(schedule.getIdRole(), schedule.getDate());
        } else {
            scheduleRepository.deleteById(schedule.getId());
        }

        // get a handle to the stage
        Stage stage = (Stage) delete.getScene().getWindow();
        // do what you have to do
        stage.close();

        Navigator.loadVista(Navigator.TIMETABLE);
    }
}
