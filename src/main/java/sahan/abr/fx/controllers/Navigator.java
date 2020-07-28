package sahan.abr.fx.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import static sahan.abr.Main.MAIN_STAGE;

public class Navigator {

    public static final String MAIN = "/fxml/main.fxml";


    public static final String AUTHORIZATION = "/fxml/authorization.fxml";

    public static final String TIMETABLE = "/fxml/timetableTEST.fxml";

    public static final String REPORTS = "/fxml/reports.fxml";

    public static final String CUSTOMERS = "/fxml/customers/customers.fxml";
    public static final String ADD_CUSTOMERS = "/fxml/customers/add-customers.fxml";
    public static final String ADD_ACTIVE_SUBSCRIPTION = "/fxml/active_subscription/add-active-sub.fxml";
    public static final String FREEZE_ACTIVE_SUBSCRIPTION = "/fxml/active_subscription/freeze.fxml";
    public static final String MODAL_ADDITIONAL_INFORMATION_PARENT_CUSTOMER = "/fxml/customers/additional-information-customer-parent.fxml";
    public static final String MODAL_ADD_CUSTOMER = "/fxml/customers/add-customers-parent.fxml";
    public static final String MODAL_ADD_CHILD = "/fxml/customers/add-customers-child.fxml";
    public static final String MODAL_UPDATE_CUSTOMER = "/fxml/customers/update-customers-parent.fxml";
    public static final String CUSTOMER_INFORMATION_PARENT = "/fxml/customers/customer-information-parent.fxml";
    public static final String CUSTOMER_INFORMATION_CHILD = "/fxml/customers/customer-information-child.fxml";

    public static final String SUBSCRIPTION = "/fxml/subscription/subscription.fxml";
    public static final String MODAL_ADD_SUBSCRIPTION = "/fxml/subscription/add-subscription.fxml";
    public static final String MODAL_UPDATE_SUBSCRIPTION = "/fxml/subscription/update-subscription.fxml";

    public static final String EMPLOYEE = "/fxml/employee/employee.fxml";
    public static final String EMPLOYEE_TIME_TABLE = "/fxml/employee/employee-time-table.fxml";
    public static final String MODAL_ADD_EMPLOYEE = "/fxml/employee/add-employee.fxml";
    public static final String MODAL_ADD_ENTRY_EMPLOYEE = "/fxml/employee/add-entry-employee-time-table.fxml";
    public static final String MODAL_ADD_SCHEDULE = "/fxml/schedule/add-schedule.fxml";
    public static final String MODAL_DELETE_SCHEDULE = "/fxml/schedule/delete-schedule.fxml";
    public static final String MODAL_UPDATE_EMPLOYEE = "/fxml/employee/update-employee.fxml";

    private static MainController mainController;

    public static void setMainController(MainController mainController) {
        Navigator.mainController = mainController;
    }

    public static void getModalWindow(String title, String fxml) {
        try {

            FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(fxml));
            Stage newWindow = new Stage(StageStyle.DECORATED);
            newWindow.setTitle(title);

            newWindow.setScene(new Scene(loader.load()));
            newWindow.setResizable(false);

            // Specifies the modality for new window.
            newWindow.initModality(Modality.WINDOW_MODAL);

            // Specifies the owner Window (parent) for new window
            newWindow.initOwner(MAIN_STAGE);

            // Set position of second window, related to primary window.
            newWindow.setX(MAIN_STAGE.getX() + 200);
            newWindow.setY(MAIN_STAGE.getY() + 100);

            newWindow.show();

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static boolean getModalWindow(String title, String fxml, Object controller) {
        try {

            FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(fxml));
            Stage newWindow = new Stage(StageStyle.DECORATED);
            newWindow.setTitle(title);

            loader.setController(controller);

            newWindow.setScene(new Scene(loader.load()));
            newWindow.setResizable(false);

            // Specifies the modality for new window.
            newWindow.initModality(Modality.WINDOW_MODAL);

            // Specifies the owner Window (parent) for new window
            newWindow.initOwner(MAIN_STAGE);

            // Set position of second window, related to primary window.
            newWindow.setX(MAIN_STAGE.getX() + 200);
            newWindow.setY(MAIN_STAGE.getY() + 100);

            newWindow.show();

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        return true;
    }

    public static void loadVista(String fxml) {
        try {
            mainController.setVista(FXMLLoader.load(Navigator.class.getResource(fxml)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadVista(String fxml, Object controller) {
        try {

            FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(fxml));
            loader.setController(controller);

            mainController.setVista(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static VBox getVBox(String title, String fxml, Object controller) {

        VBox scene = null;

        try {

            FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(fxml));
            loader.setController(controller);

            scene = new VBox((VBox) loader.load());


        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        return scene;
    }

    public static VBox getVBox(String title, String fxml) {

        VBox scene = null;

        try {

            FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(fxml));
            //loader.setController();

            scene = new VBox((VBox) loader.load());


        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        return scene;
    }

}