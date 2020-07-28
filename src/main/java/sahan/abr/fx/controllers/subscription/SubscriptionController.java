package sahan.abr.fx.controllers.subscription;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import sahan.abr.fx.controllers.Navigator;
import sahan.abr.entities.Subscription;

import java.sql.SQLException;

import static sahan.abr.Main.observableListSubscriptions;
import static sahan.abr.Main.subscriptionRepository;

public class SubscriptionController {

    @FXML
    private TableView<Subscription> tableViewSubscriptions;

    @FXML
    private TableColumn<Subscription, String> tableColumnTitleSubscription;

    @FXML
    private TableColumn<Subscription, Double> tableColumnPriceSubscription;

    @FXML
    private TableColumn<Subscription, Integer> tableColumnValiditySubscription;

    @FXML
    private TableColumn<Subscription, Integer> tableColumnNumberClassesSubscription;

    @FXML
    private TextField textFieldSearchTitle;

    private SubscriptionController subscriptionController = this;

    @FXML
    private void initialize() throws SQLException {
        observableListSubscriptions = FXCollections.observableArrayList();
        observableListSubscriptions.addAll(subscriptionRepository.getAll());
        tableViewSubscriptions.refresh();
        tableViewSubscriptions.setItems(observableListSubscriptions);

        tableColumnTitleSubscription.setCellValueFactory(new PropertyValueFactory<Subscription, String>("titleSubscription"));
        tableColumnPriceSubscription.setCellValueFactory(new PropertyValueFactory<Subscription, Double>("priceSubscription"));
        tableColumnValiditySubscription.setCellValueFactory(new PropertyValueFactory<Subscription, Integer>("validity"));
        tableColumnNumberClassesSubscription.setCellValueFactory(new PropertyValueFactory<Subscription, Integer>("numberClasses"));

        addButtonsToTableSubscriptions();

        tableViewSubscriptions.setVisible(true);
        tableViewSubscriptions.setEditable(true);
    }

    @FXML
    void addSubscription(ActionEvent event) {
        Navigator.getModalWindow("SRM", Navigator.MODAL_ADD_SUBSCRIPTION);
    }

    @FXML
    void searchSubscription(ActionEvent event) {

        String valueTitle = textFieldSearchTitle.getText();

        if (valueTitle == null || valueTitle.isEmpty()){
            tableViewSubscriptions.setItems(observableListSubscriptions);
        } else {
            ObservableList<Subscription> subscriptionsTMP = FXCollections.observableArrayList();

            for (Subscription subscription : observableListSubscriptions) {
                if (subscription.getTitleSubscription().equals(valueTitle)){
                    subscriptionsTMP.add(subscription);
                }
            }

            tableViewSubscriptions.setItems(subscriptionsTMP);
        }
    }

    @FXML
    void clearFilterSearchSubscriptions(ActionEvent event) {
        textFieldSearchTitle.setText("");
        tableViewSubscriptions.setItems(observableListSubscriptions);
    }

    public TableView<Subscription> getTableViewSubscriptions() {
        return tableViewSubscriptions;
    }

    private void addButtonsToTableSubscriptions() {
        TableColumn colBtn = new TableColumn("Действия");

        Callback<TableColumn<Subscription, Void>, TableCell<Subscription, Void>> cellFactory =
                new Callback<TableColumn<Subscription, Void>, TableCell<Subscription, Void>>() {
            @Override
            public TableCell<Subscription, Void> call(final TableColumn<Subscription, Void> param) {
                final TableCell<Subscription, Void> cell = new TableCell<Subscription, Void>() {

                    private final HBox hBox = new HBox();
                    {
                        hBox.setSpacing(10);
                        hBox.setAlignment(Pos.CENTER);

                        Button buttonDelete = new Button("Удалить");
                        buttonDelete.setPrefWidth(93);
                        buttonDelete.setAlignment(Pos.CENTER_RIGHT);
                        buttonDelete.getStyleClass().add("toggle-button-delete-left");

                        buttonDelete.setOnAction((ActionEvent event) -> {
                            Subscription data = getTableView().getItems().get(getIndex());
                            try {
                                subscriptionRepository.deleteById(data.getId());
                                observableListSubscriptions.remove(data);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });

                        Button buttonUpdate = new Button("Обновить");
                        buttonUpdate.setPrefWidth(103);
                        buttonUpdate.setAlignment(Pos.CENTER_RIGHT);
                        buttonUpdate.getStyleClass().add("toggle-button-update-left");

                        buttonUpdate.setOnAction((ActionEvent event) -> {
                            Subscription data = getTableView().getItems().get(getIndex());
                            ModalUpdateSubscriptionController controller = new ModalUpdateSubscriptionController(data, subscriptionController);
                            Navigator.getModalWindow("SRM", Navigator.MODAL_UPDATE_SUBSCRIPTION, controller);
                        });

                        hBox.getChildren().add(buttonDelete);
                        hBox.getChildren().add(buttonUpdate);
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(hBox);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        tableViewSubscriptions.getColumns().add(colBtn);

    }
}
