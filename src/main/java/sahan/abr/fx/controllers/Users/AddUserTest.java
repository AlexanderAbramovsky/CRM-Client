package sahan.abr.fx.controllers.Users;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sahan.abr.entities.Parent;

import java.io.IOException;

import static sahan.abr.Main.MAIN_STAGE;

public class AddUserTest {

    public static VBox getUser(Parent parent) {

        VBox vBoxUser = new VBox();
        vBoxUser.setFillWidth(true);
        vBoxUser.setSpacing(10);

        // Создаём информацию о родителе
        Label labelParent = new Label("Родитель:");

        HBox hBoxParent = new HBox();
        hBoxParent.setAlignment(Pos.CENTER_LEFT);
        hBoxParent.setSpacing(10);

        TextField textFieldInitialsParent = new TextField();
        textFieldInitialsParent.setPrefSize(175, 30);
        Label labelInitialsParent = new Label("Ф.И.О.");
        TextField textFieldPhoneNumber = new TextField();
        textFieldPhoneNumber.setPrefSize(175, 30);
        Label labelPhoneNumber = new Label("Номер телефона для связи");

        Button buttonInformationParent = new Button("Дополнительная информация");

        // Добавляем слушателя к кнопке Дополнительной информации для Родителя
        buttonInformationParent.setOnAction(new EventHandler<ActionEvent>()  {

            @Override
            public void handle(ActionEvent event)  {

                FXMLLoader loader = null;
                Stage newWindow = new Stage(StageStyle.DECORATED);
                newWindow.setTitle("Second Stage");

                try {
                    loader = new FXMLLoader(getClass().getResource("/fxml/userParent.fxml"));

                    loader.setController(new UserParent(parent));

                    newWindow.setTitle("Second Stage");
                    newWindow.setScene(new Scene(loader.load()));
                    newWindow.setResizable(false);

                } catch (IOException exception) {
                    System.out.println(exception.getMessage());
                }

                // Specifies the modality for new window.
                newWindow.initModality(Modality.WINDOW_MODAL);


                // Specifies the owner Window (parent) for new window
                newWindow.initOwner(MAIN_STAGE);

                // Set position of second window, related to primary window.
                newWindow.setX(MAIN_STAGE.getX() + 200);
                newWindow.setY(MAIN_STAGE.getY() + 100);

                newWindow.show();
            }
        });

        hBoxParent.getChildren().addAll(
                textFieldInitialsParent,
                labelInitialsParent,
                textFieldPhoneNumber,
                labelPhoneNumber,
                buttonInformationParent);

        // Создаём информацию о ребенке
        Label labelChild = new Label("Ребенок:");

        HBox hBoxChild = new HBox();
        hBoxChild.setAlignment(Pos.CENTER_LEFT);
        hBoxChild.setSpacing(10);

        TextField textFieldInitialsChild = new TextField();
        textFieldInitialsChild.setPrefSize(175, 30);
        Label labelInitialsChild = new Label("Ф.И.О.");
        TextField textFieldPhoneChild = new TextField();
        textFieldPhoneNumber.setPrefSize(175, 30);
        Button buttonInformationChild = new Button("Дополнительная информация");

        // Добавляем слушателя к кнопке Дополнительной информации для Ребенка
        buttonInformationChild.setOnAction(new EventHandler<ActionEvent>()  {

            @Override
            public void handle(ActionEvent event)  {

                FXMLLoader loader = null;
                Stage newWindow = new Stage(StageStyle.DECORATED);
                newWindow.setTitle("Second Stage");

                try {
                    loader = new FXMLLoader(getClass().getResource("/fxml/userChild.fxml"));

                   // loader.setController(new UserParent(1));

                    newWindow.setTitle("Second Stage");
                    newWindow.setScene(new Scene(loader.load()));
                    newWindow.setResizable(false);

                } catch (IOException exception) {
                    System.out.println(exception.getMessage());
                }

                // Specifies the modality for new window.
                newWindow.initModality(Modality.WINDOW_MODAL);


                // Specifies the owner Window (parent) for new window
                newWindow.initOwner(MAIN_STAGE);

                // Set position of second window, related to primary window.
                newWindow.setX(MAIN_STAGE.getX() + 200);
                newWindow.setY(MAIN_STAGE.getY() + 100);

                newWindow.show();
            }
        });

        hBoxChild.getChildren().addAll(
                textFieldInitialsChild,
                labelInitialsChild,
                textFieldPhoneChild,
                buttonInformationChild);

        vBoxUser.getChildren().add(labelParent);
        vBoxUser.getChildren().add(hBoxParent);
        vBoxUser.getChildren().add(labelChild);
        vBoxUser.getChildren().add(hBoxChild);

        return vBoxUser;
    }

}
