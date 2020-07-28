package sahan.abr.fx.controllers.authorization;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import sahan.abr.fx.controllers.MainController;
import sahan.abr.fx.controllers.Navigator;

import static sahan.abr.Main.*;

import java.io.IOException;
import java.sql.SQLException;


public class Authorization {
    @FXML
    private TextField textFieldLogin;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private Button authorization;

    @FXML
    private Label text;

    @FXML
    private void initialize() throws SQLException {

    }

    @FXML
    void authorization(ActionEvent event) throws IOException, SQLException {

        SECURITY_ADMINISTRATOR = authorizationRep.getByPasswordAndLogin(textFieldPassword.getText(), textFieldLogin.getText());

        if (SECURITY_ADMINISTRATOR != null) {
            MAIN_STAGE.setScene(createScene(loadMainPane()));
            MAIN_STAGE.setMaximized(true);
            MAIN_STAGE.setResizable(true);
            MAIN_STAGE.show();
        } else {
            text.setText("Вы ввели не верные данные");
        }
    }

    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = loader.load(
                getClass().getResourceAsStream(Navigator.MAIN)
        );

        MainController mainController = loader.getController();

        Navigator.setMainController(mainController);
        Navigator.loadVista(Navigator.TIMETABLE);

        return mainPane;
    }

    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(mainPane);
        scene.getStylesheets().add("./css/bootstrap3.css");
        scene.getStylesheets().add("./css/buttons.css");
        scene.getStylesheets().add("./css/titledPane.css");
        return scene;
    }
}
